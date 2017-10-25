package com.spring.boot.blog.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.github.rjeschke.txtmark.Processor;

/**
 * Blog 实体
 * 
 * @since 1.0.0 2017年4月7日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@Entity // 实体
public class LeaderMemberResponsibility implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id // 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
	private Long id; // 用户的唯一标识
	
	@NotEmpty(message = "组长的责任不能为空")
	@Size(min=2, max=2048)
	@Column(nullable = false, length = 2048) // 映射为字段，值不能为空
	private String LeaderResponsibility;
	
	@NotEmpty(message = "组员的责任不能为空")
	@Size(min=2, max=2048)
	@Column(nullable = false, length = 2048) // 映射为字段，值不能为空
	private String MemberResponsibility;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLeaderResponsibility() {
		return LeaderResponsibility;
	}

	public void setLeaderResponsibility(String leaderResponsibility) {
		LeaderResponsibility = leaderResponsibility;
	}

	public String getMemberResponsibility() {
		return MemberResponsibility;
	}

	public void setMemberResponsibility(String memberResponsibility) {
		MemberResponsibility = memberResponsibility;
	}

	

	
 
}
