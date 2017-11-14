package com.spring.boot.blog.domain;

import java.util.ArrayList;
import java.util.Collection;
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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
public class User implements UserDetails{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id // 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
	private Long id; // 用户的唯一标识

	@NotEmpty(message = "姓名不能为空")
	@Size(min=2, max=20, message="姓名长度必须在2到20之间")
	@Column(nullable = false, length = 20) // 映射为字段，值不能为空
	private String name;

	@NotEmpty(message = "邮箱不能为空")
	@Size(max=50, message="邮箱长度必须在2到50之间")
	@Email(message= "邮箱格式不对" ) 
	@Column(nullable = false, length = 50, unique = true)
	private String email;

	@NotEmpty(message = "账号不能为空")
	@Size(min=3, max=20, message="账号长度必须在2到20之间")
	@Column(nullable = false, length = 20, unique = true)
	private String userName; // 用户账号，用户登录时的唯一标识

	@NotEmpty(message = "密码不能为空")
	@Size(max=100, message="密码长度不能大于100")
	@Column(length = 100)
	private String password; // 登录时密码
	
	@Column(length = 200)
	private String avatar; // 头像图片地址

   
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
	private List<Authority> authorities;
	
	protected User() {
		
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", userName=" + userName + ", password="
				+ password + ", avatar=" + avatar + "]";
	}

	public User(String name, long id, String email,String userName) {
		super();
		this.name = name;
		this.id = id;
		this.email = email;
		this.userName=userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setAuthorities(List<Authority> authorities) {
		// TODO Auto-generated method stub
		this.authorities =authorities;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	//  需将 List<Authority> 转成 List<SimpleGrantedAuthority>，否则前端拿不到角色列表名称
			List<SimpleGrantedAuthority> simpleAuthorities = new ArrayList<>();
			for(GrantedAuthority authority : this.authorities){
				simpleAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
			}
			return simpleAuthorities;
	}
	

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	public void setEncodePassword(String password) {
		PasswordEncoder  encoder = new BCryptPasswordEncoder();
		String encodePasswd = encoder.encode(password);
		this.password = encodePasswd;
	}
	
	
}
