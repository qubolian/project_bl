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
 * 消息类型
 * 
 * @since 1.0.0 2017年9月29日
 */
@Entity // 实体
public class NewsType implements Serializable {
	private static final long serialVersionUID = 1L;

	public NewsType() {
		super();
	}

	public NewsType(Long id, String messageType) {
		super();
		this.id = id;
		this.messageType = messageType;
	}

	@Id // 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
	private Long id; // 用户的唯一标识

	@NotEmpty(message = "信息类别不能为空")
	@Size(min = 2, max = 64, message = "信息类别必须在2到64之间")
	@Column(nullable = false, length = 64) // 映射为字段，值不能为空
	private String messageType;

	private int dr;

	public int getDr() {
		return dr;
	}

	public void setDr(int dr) {
		this.dr = dr;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
