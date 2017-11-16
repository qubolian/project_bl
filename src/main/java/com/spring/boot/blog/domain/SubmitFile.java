package com.spring.boot.blog.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 提交大纲，教学进度表
 * 
 * @since 1.0.0 2017年4月7日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@Entity // 实体
public class SubmitFile implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id // 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
	private Long id; // 用户的唯一标识

	@NotEmpty(message = "大纲原文件名不能为空")
	@Column(nullable = false) // 映射为字段，值不能为空
	private String outlineName;

	@NotEmpty(message = "大纲保存文件名不能为空")
	@Size(min = 1, message = "大纲保存文件名长度必须在1到50之间")
	@Column(nullable = false) // 映射为字段，值不能为空
	private String outlineSaveName;

	@NotEmpty(message = "教学进度表原文件名不能为空")
	@Column(nullable = false) // 映射为字段，值不能为空
	private String scheduleName;

	@NotEmpty(message = "教学进度表保存文件名不能为空")
	@Size(min = 1, message = "教学进度表保存文件名长度必须在1到50之间")
	@Column(nullable = false) // 映射为字段，值不能为空
	private String scheduleSaveName;

	@NotEmpty(message = "大纲上传时间不能为空")
	@Size(min = 1, message = "大纲上传时间长度必须在1到50之间")
	@Column(nullable = false) // 映射为字段，值不能为空
	private String outlineUpdateTime;

	@NotEmpty(message = "教学进度表上传时间不能为空")
	@Size(min = 1, message = "教学进度表上传时间长度必须在1到50之间")
	@Column(nullable = false) // 映射为字段，值不能为空
	private String scheduleUpdateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOutlineName() {
		return outlineName;
	}

	public void setOutlineName(String outlineName) {
		this.outlineName = outlineName;
	}

	public String getOutlineSaveName() {
		return outlineSaveName;
	}

	public void setOutlineSaveName(String outlineSaveName) {
		this.outlineSaveName = outlineSaveName;
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public String getScheduleSaveName() {
		return scheduleSaveName;
	}

	public void setScheduleSaveName(String scheduleSaveName) {
		this.scheduleSaveName = scheduleSaveName;
	}

	public String getOutlineUpdateTime() {
		return outlineUpdateTime;
	}

	public void setOutlineUpdateTime(String outlineUpdateTime) {
		this.outlineUpdateTime = outlineUpdateTime;
	}

	public String getScheduleUpdateTime() {
		return scheduleUpdateTime;
	}

	public void setScheduleUpdateTime(String scheduleUpdateTime) {
		this.scheduleUpdateTime = scheduleUpdateTime;
	}
	
	

}
