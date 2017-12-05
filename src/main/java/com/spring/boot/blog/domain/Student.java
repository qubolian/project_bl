package com.spring.boot.blog.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * 发布单位
 * 
 * @since 1.0.0 2017年4月7日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@Entity // 实体
public class Student implements Serializable {
	


	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	@Id // 主键
	@Column(nullable = false) // 映射为字段，值不能为空
	private Long id;
	
	@NotEmpty(message = "姓名不能为空")
	@Size(min=2, max=16, message="姓名长度必须在2到16之间")
	@Column(nullable = false, length = 16) // 映射为字段，值不能为空
	private String name;
	
	@NotEmpty(message = "请选择性别")
	@Column(nullable = false) // 映射为字段，值不能为空
	private String sex;
	
	@NotEmpty(message = "年级不能为空")
	@Size(min=2, max=10, message="年级长度必须在2到10之间")
	@Column(nullable = false, length = 10) // 映射为字段，值不能为空
	private String grade;
	
	@NotEmpty(message = "学制不能为空")
	@Size(min=1, max=5, message="学制长度必须在2到5之间")
	@Column(nullable = false, length = 5) // 映射为字段，值不能为空
	private String lengthOfSchooling;
	
	@NotEmpty(message = "培养层次不能为空")
	@Size(min=2, max=8, message="培养层次长度必须在2到6之间")
	@Column(nullable = false, length = 8) // 映射为字段，值不能为空
	private String trainingLevel;

	@NotEmpty(message = "院系不能为空")
	@Size(min=2, max=32, message="院系长度必须在2到32之间")
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private String department;
	
	@NotEmpty(message = "专业不能为空")
	@Size(min=2, max=32, message="专业长度必须在2到32之间")
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private String major;
	
	@NotEmpty(message = "入校时间不能为空")
	@Size(min=2, max=10, message="入校时间长度必须在2到10之间")
	@Column(nullable = false, length = 10) // 映射为字段，值不能为空
	private String entranceTime;
	
	@NotEmpty(message = "学籍状态不能为空")
	@Size(min=2, max=10, message="学籍状态长度必须在2到10之间")
	@Column(nullable = false, length = 10) // 映射为字段，值不能为空
	private String status;
	
	@NotEmpty(message = "班级不能为空")
	@Size(min=2, max=10, message="班级长度必须在2到10之间")
	@Column(nullable = false, length = 10) // 映射为字段，值不能为空
	private String classes;
	
	//0、注册学籍：正常在校，并经过了每年一次（一般在每年的9月份进行）的注册。
	//1、休学：在休学状态
	//2、退学：曾经在该校读书，现已退学
	//3、保留学籍：当兵入伍在部队
	@NotNull(message = "学籍状态不能为空")
	private int statusInt;
	
	public int getStatusInt() {
		return statusInt;
	}

	public void setStatusInt(int statusInt) {
		this.statusInt = statusInt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getLengthOfSchooling() {
		return lengthOfSchooling;
	}

	public void setLengthOfSchooling(String lengthOfSchooling) {
		this.lengthOfSchooling = lengthOfSchooling;
	}

	public String getTrainingLevel() {
		return trainingLevel;
	}

	public void setTrainingLevel(String trainingLevel) {
		this.trainingLevel = trainingLevel;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getEntranceTime() {
		return entranceTime;
	}

	public void setEntranceTime(String entranceTime) {
		this.entranceTime = entranceTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	
	
 
}
