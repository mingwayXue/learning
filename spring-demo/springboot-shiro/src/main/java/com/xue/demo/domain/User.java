package com.xue.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mingway on Date:2019-01-10 15:46.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String name;

	private Integer password;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	private List<Role> roles;
}
