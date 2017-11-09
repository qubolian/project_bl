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
import javax.persistence.ManyToOne;
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
public class Teacher implements Serializable {
	


	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	@Id // 主键
	@Column(nullable = false)
	private Long id;
	
	@NotEmpty(message = "教师名称不能为空")
	@Size(min=2, max=32)
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private String teacherName;
	
	@NotEmpty(message = "请选择教师性别")
	@Column(nullable = false) // 映射为字段，值不能为空
	private String sex;
	
	@NotEmpty(message = "教师类型不能为空")
	@Size(min=2, max=32)
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private String teacherType;
	
	@NotEmpty(message = "学院不能为空")
	@Size(min=2, max=32)
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private String college;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departmentId")
	@NotNull(message = "必须选择系部")
	private DepartmentList department;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER) 
	@JoinTable(name = "CourseSupervisor", 
				joinColumns = {@JoinColumn(name = "supervisorId", referencedColumnName = "id")}, 
				inverseJoinColumns = {@JoinColumn(name = "courseId", referencedColumnName = "id")}) 
	private List<Course> course;
	
	public List<Course> getCourse() {
		return course;
	}

	public void setCourse(List<Course> course) {
		this.course = course;
	}

	public DepartmentList getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentList department) {
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTeacherType() {
		return teacherType;
	}

	public void setTeacherType(String teacherType) {
		this.teacherType = teacherType;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}







	


	





	  
	
 
}
