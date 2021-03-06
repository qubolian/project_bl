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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
@Entity
public class HowtoTeamUp  implements Serializable {
	
	public HowtoTeamUp(Long id, String gradeId, String teamUpStage, String seekAdvisorStage, String projectConductionStage) {
		super();
		this.id = id;
		this.gradeId = gradeId;
		this.teamUpStage = teamUpStage;
		this.seekAdvisorStage = seekAdvisorStage;
		this.projectConductionStage = projectConductionStage;
	}

	public HowtoTeamUp() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	@Id // 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
	private Long id; // 用户的唯一标识
	
	@NotEmpty(message = "年级不能为空")
	@Size(min=2, max=10, message="年级长度必须在2到10之间")
	@Column(nullable = false, length = 10) // 映射为字段，值不能为空
	private String gradeId;
	
	@NotEmpty(message = "组队期不能为空")
	@Size(min=2, max=32, message="组队期长度必须在2到32之间")
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private String teamUpStage;

	@NotEmpty(message = "寻找指导老师期不能为空")
	@Size(min=2, max=32, message="寻找指导老师期长度必须在2到32之间")
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private String seekAdvisorStage;
	
	@NotEmpty(message = "专题执行期不能为空")
	@Size(min=2, max=32, message="专题执行期长度必须在2到32之间")
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private String projectConductionStage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getTeamUpStage() {
		return teamUpStage;
	}

	public void setTeamUpStage(String teamUpStage) {
		this.teamUpStage = teamUpStage;
	}

	public String getSeekAdvisorStage() {
		return seekAdvisorStage;
	}

	public void setSeekAdvisorStage(String seekAdvisorStage) {
		this.seekAdvisorStage = seekAdvisorStage;
	}

	public String getProjectConductionStage() {
		return projectConductionStage;
	}

	public void setProjectConductionStage(String projectConductionStage) {
		this.projectConductionStage = projectConductionStage;
	}
	
	
	
	
	
	
	
}
