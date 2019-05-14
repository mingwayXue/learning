package com.xue.jfinal.config;

import com.jfinal.plugin.activerecord.ActiveRecordException;
import com.jfinal.plugin.activerecord.Config;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.NestedTransactionHelpException;
import com.jfinal.plugin.activerecord.tx.TxConfig;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by mingway on Date:2019-04-13 11:51.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Component
@Scope
@Aspect
@Order(1)
@Slf4j
public class JFinalTxAspect {
	@Pointcut("@annotation(com.xue.jfinal.annotation.JFinalTransactional)")
		public void JFinalTxAspect() {

	}

	@Around("JFinalTxAspect()")
	public Object around(ProceedingJoinPoint joinPoint) {
		Object obj = null;
		Config config = getConfigWithTxConfig(joinPoint);
		if (config == null)
			config = DbKit.getConfig();

		Connection conn = config.getThreadLocalConnection();
		if (conn != null) { // Nested transaction support
			try {
				if (conn.getTransactionIsolation() < getTransactionLevel(config))
					conn.setTransactionIsolation(getTransactionLevel(config));
				try {
					obj = joinPoint.proceed();
				} catch (Throwable e) {
					log.error(e.getMessage(), e);
				}

			} catch (SQLException e) {
				throw new ActiveRecordException(e);
			}
		}

		Boolean autoCommit = null;
		try {
			conn = config.getConnection();
			autoCommit = conn.getAutoCommit();
			config.setThreadLocalConnection(conn);
			conn.setTransactionIsolation(getTransactionLevel(config)); // conn.setTransactionIsolation(transactionLevel);
			conn.setAutoCommit(false);
			obj = joinPoint.proceed();
			conn.commit();
			log.info("Committing JDBC transaction on Connection [" + conn + "]");
		} catch (NestedTransactionHelpException e) {
			if (conn != null)
				try {
					conn.rollback();
					log.info("Rolling back JDBC transaction on Connection [" + conn + "]");
				} catch (Exception e1) {
					log.error(e1.getMessage(), e1);
				}
		} catch (Throwable t) {
			if (conn != null)
				try {
					conn.rollback();
					log.info("Rolling back JDBC transaction on Connection [" + conn + "]");
				} catch (Exception e1) {
					log.error(e1.getMessage(), e1);
				}
			throw t instanceof RuntimeException ? (RuntimeException) t : new ActiveRecordException(t);
		} finally {
			try {
				if (conn != null) {
					if (autoCommit != null)
						conn.setAutoCommit(autoCommit);
					log.info("Releasing JDBC Connection [" + conn + "] after transaction");
					conn.close();

				}
			} catch (Throwable t) {
				log.error(t.getMessage(), t); // can not throw exception
				// here, otherwise the more
				// important exception in
				// previous catch block can
				// not be thrown
			} finally {
				config.removeThreadLocalConnection(); // prevent memory leak

			}

		}
		return obj;
	}

	public static Config getConfigWithTxConfig(ProceedingJoinPoint joinPoint) {
		TxConfig txConfig = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(TxConfig.class);
		if (txConfig == null)
			txConfig = joinPoint.getTarget().getClass().getAnnotation(TxConfig.class);

		if (txConfig != null) {
			Config config = DbKit.getConfig(txConfig.value());
			if (config == null)
				throw new RuntimeException("Config not found with TxConfig: " + txConfig.value());
			return config;
		}
		return null;
	}

	protected int getTransactionLevel(Config config) {
		return config.getTransactionLevel();
	}
}
