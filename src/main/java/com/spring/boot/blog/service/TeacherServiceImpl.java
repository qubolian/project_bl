package com.spring.boot.blog.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.spring.boot.blog.domain.Teacher;
import com.spring.boot.blog.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	TeacherRepository TeacherRepository;
	
	@Override
	@Transactional 
	public Teacher saveTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return TeacherRepository.save(teacher);
	}

	@Override
	@Transactional
	public void removeTeacher(Long id) {
		// TODO Auto-generated method stub
		TeacherRepository.delete(id);

	}

	@Override
	@Transactional
	public void removeTeachersInBatch(List<Teacher> Teachers) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public Teacher updateTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return TeacherRepository.save(teacher);
	}

	@Override
	@Transactional
	public Teacher getTeacherById(Long id) {
		// TODO Auto-generated method stub
		return TeacherRepository.findOne(id);
	}

	@Override
	@Transactional
	public List<Teacher> listTeachers() {
		// TODO Auto-generated method stub
		return TeacherRepository.findAll();
	}

	@Override
	@Transactional
	public Page<Teacher> listTeacherByTeacherNameLike(String teacherName, Pageable pageable) {
		// TODO Auto-generated method stub
		teacherName= "%"+teacherName+"%";
		Page<Teacher> teacher = TeacherRepository.findByTeacherNameLike(teacherName, pageable);
		return teacher;
	}

}
