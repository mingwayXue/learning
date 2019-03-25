import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/** 异步的servlet
 * Created by mingway on Date:2018-11-30 16:23.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@WebServlet(name = "AsyncServlet", urlPatterns = "/AsyncServlet", asyncSupported = true)	//开启异步支持
public class AsyncServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long t1 = System.currentTimeMillis();

		//开启异步,类似于数据库操作时的事务，先开启事务，后面还要关闭事务
		AsyncContext asyncContext = request.startAsync();

		//执行业务代码，需要使用到CompletableFuture
		CompletableFuture.runAsync(() -> doSomeThing(asyncContext, asyncContext.getRequest(), asyncContext.getResponse()));

		System.out.println("async used: " + (System.currentTimeMillis() - t1));
	}

	private void doSomeThing(AsyncContext asyncContext, ServletRequest request, ServletResponse response) {
		//模拟耗时操作
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			response.getWriter().append("done");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//业务代码执行完毕，通知结束
		asyncContext.complete();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
