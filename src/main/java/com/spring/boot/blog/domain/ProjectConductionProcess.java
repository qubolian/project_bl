package com.spring.boot.blog.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity // 实体
public class ProjectConductionProcess  implements Serializable{

	/**
	 * 专题流程
	 */
	private static final long serialVersionUID = 1L;
	@Id // 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
	private Long semesterId; // 用户的唯一标识
	
	@NotEmpty(message = "学期功能不能为空")
	@Size(min=2, max=1024, message="学期功能长度必须在2到1024之间")
	@Column(nullable = false, length = 1024) // 映射为字段，值不能为空
	private String semesterFunction;
	
	@NotEmpty(message = "课程名称不能为空")
	@Size(min=2, max=256, message="课程名称长度必须在2到256之间")
	@Column(nullable = false, length = 256) // 映射为字段，值不能为空
	private String courseTitle;
	
	
	@NotEmpty(message = "课程大纲不能为空")
	@Size(min=2, max=1024, message="课程大纲长度必须在2到1024之间")
	@Column(nullable = false, length = 1024) // 映射为字段，值不能为空
	private String courseOutline;


	public Long getSemesterId() {
		return semesterId;
	}


	public void setSemesterId(Long semesterId) {
		this.semesterId = semesterId;
	}


	public String getSemesterFunction() {
		return semesterFunction;
	}


	public void setSemesterFunction(String semesterFunction) {
		this.semesterFunction = semesterFunction;
	}


	public String getCourseTitle() {
		return courseTitle;
	}


	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}


	public String getCourseOutline() {
		return courseOutline;
	}


	public void setCourseOutline(String courseOutline) {
		this.courseOutline = courseOutline;
	}
	
	
	
	
	
	
}
