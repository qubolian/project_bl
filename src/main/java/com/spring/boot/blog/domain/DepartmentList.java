package com.spring.boot.blog.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 发布单位
 * 
 * @since 1.0.0 2017年4月7日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@Entity // 实体
public class DepartmentList implements Serializable {
	public DepartmentList(Long id, String code, String department) {
		super();
		this.id = id;
		this.code = code;
		this.department = department;
	}

	public DepartmentList() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	@Id // 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
	private Long id; // 用户的唯一标识

	@NotEmpty(message = "代码不能为空")
	@Size(min = 2, max = 16, message = "代码长度必须在2到16之间")
	@Column(nullable = false, length = 16) // 映射为字段，值不能为空
	private String code;

	@NotEmpty(message = "发布单位不能为空")
	@Size(min = 2, max = 64, message = "发布单位长度必须在2到64之间")
	@Column(nullable = false, length = 64) // 映射为字段，值不能为空
	private String department;

	private int dr;

	public int getDr() {
		return dr;
	}

	public void setDr(int dr) {
		this.dr = dr;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
