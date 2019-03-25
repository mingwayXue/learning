package com.xue.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mingway on Date:2019-01-10 15:47.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Entity
@Data
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String roleName;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "role")
	private List<Permission> permissions;
}
