package com.spring.boot.blog.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.spring.boot.blog.domain.Course;
import com.spring.boot.blog.domain.LeaderMemberResponsibility;
import com.spring.boot.blog.domain.ProjectMission;
import com.spring.boot.blog.domain.Teacher;
import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.domain.WhatsNew;
import com.spring.boot.blog.service.CourseService;
import com.spring.boot.blog.service.LeaderMemberResponsibilityService;
import com.spring.boot.blog.service.ProjectMissionService;
import com.spring.boot.blog.service.TeacherService;
import com.spring.boot.blog.service.WhatsNewService;

public class HomePage {
	
	public ProjectMission ListProjectMission(ProjectMissionService projectMissionService){
		//宗旨
		ProjectMission pm = projectMissionService.getProjectMissionById(1L);
		return pm;
	}
	
	public Pageable pageable(){
		//分页
		int pageIndex = 0;
		int pageSize =5;
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		return pageable;
	}
	
	public List<WhatsNew> ListWhatsNew(WhatsNewService whatsNewService){
		//最新消息
		Page<WhatsNew> page = whatsNewService.listWhatsNewsByEventsLike("", pageable());
		List<WhatsNew> list = page.getContent();	// 当前所在页面数据列表
		return list;
	}
	
	public List<Course> ListCourse(CourseService courseService){
		//课程
		Page<Course> Cpage = courseService.listCoursesByNameLike("", "2", pageable());
		List<Course> courseList = Cpage.getContent();	// 当前所在页面数据列表
		return courseList;
	}
	
	public List<User> ListTeacher(TeacherService teacherService,UserDetailsService userDetailsService){
		Page<Teacher> Tpage = teacherService.listTeacherByTeacherNameLike("", pageable());
		List<Teacher> teacherList = Tpage.getContent();	// 当前所在页面数据列表
		List<User> teacher = new ArrayList<User>();
		for (Teacher t : teacherList) {
			String s = String.valueOf(t.getId());
			User user = (User)userDetailsService.loadUserByUsername(s);
			teacher.add(user);
		}
		return teacher;
	}
	
	public LeaderMemberResponsibility ListLeaderMemberResponsibility(LeaderMemberResponsibilityService leaderMemberResponsibilityService){
		//组长组员责任
		LeaderMemberResponsibility lmr = leaderMemberResponsibilityService.getLeaderMemberResponsibilityById(1L);
		return lmr;
	}
	
}
