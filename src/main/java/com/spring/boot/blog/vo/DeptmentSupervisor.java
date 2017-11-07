package com.spring.boot.blog.vo;
import java.util.List;

import com.spring.boot.blog.domain.Teacher;;
/**
 * 部门老师对象.
 * 
 * @since 1.0.0 2017年4月4日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public class DeptmentSupervisor {
	
	
	private Long deptId;
	private List<Teacher> teachers;
	private Long teacherSelect;
	
	

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Long getTeacherSelect() {
		return teacherSelect;
	}

	public void setTeacherSelect(Long teacherSelect) {
		this.teacherSelect = teacherSelect;
	}

	public DeptmentSupervisor(Long deptId, Long teacherSelect) {
		this.deptId = deptId;
		this.teacherSelect = teacherSelect;
	}
	
	public DeptmentSupervisor(Long deptId, List<Teacher> teachers, Long teacherSelect) {
		this.deptId = deptId;
		this.teachers = teachers;
		this.teacherSelect = teacherSelect;
	}

}
