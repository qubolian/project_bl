package com.spring.boot.blog.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
@Entity
public class WhatsNew  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id // 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
	private Long id; // 用户的唯一标识
	
	@NotEmpty(message = "公告标题不能为空")
	@Size(min=2, max=1024, message="公告标题长度必须在2到1024之间")
	@Column(nullable = false, length = 256) // 映射为字段，值不能为空
	private String events;
	
	@NotEmpty(message = "内容说明不能为空")
	@Size(min=2, max=2048, message="内容说明长度必须在2到2048之间")
	@Column(nullable = false, length = 2048) // 映射为字段，值不能为空
	private String note;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departmentId")
	@NotNull(message = "必须选择发布单位")
	private DepartmentList departmentList;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "newsTypeId")
	@NotNull(message = "必须选择信息类别")
	private NewsType newsType;
	
	
	public DepartmentList getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(DepartmentList departmentList) {
		this.departmentList = departmentList;
	}

	public NewsType getNewsType() {
		return newsType;
	}

	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEvents() {
		return events;
	}

	public void setEvents(String events) {
		this.events = events;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@NotEmpty(message = "消息曝光开始时间不能为空")
	@Size(min=2, max=10, message="消息曝光开始时间长度必须在2到10之间")
	@Column(nullable = false, length = 10) // 映射为字段，值不能为空
	private String startTime;
	
	@NotEmpty(message = "消息曝光结束时间不能为空")
	@Size(min=2, max=10, message="消息曝光结束时间长度必须在2到10之间")
	@Column(nullable = false, length = 10) // 映射为字段，值不能为空
	private String endTime;
	
	
	
	
	
	
	
}
