package com.xue.demo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;

/**
 * Created by mingway on Date:2019-01-10 15:48.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Entity
@Data
public class Permission {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String permission;

	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;
}
