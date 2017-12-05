package com.spring.boot.blog.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.spring.boot.blog.domain.Student;
import com.spring.boot.blog.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository StudentRepository;
	
	@Override
	@Transactional 
	public Student saveStudent(Student Student) {
		// TODO Auto-generated method stub
		return StudentRepository.save(Student);
	}

	@Override
	@Transactional
	public void removeStudent(Long id) {
		// TODO Auto-generated method stub
		StudentRepository.delete(id);

	}

	@Override
	@Transactional
	public void removeStudentsInBatch(List<Student> Students) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public Student updateStudent(Student Student) {
		// TODO Auto-generated method stub
		return StudentRepository.save(Student);
	}

	@Override
	@Transactional
	public Student getStudentById(Long id) {
		// TODO Auto-generated method stub
		return StudentRepository.findOne(id);
	}

	@Override
	@Transactional
	public List<Student> listStudents() {
		// TODO Auto-generated method stub
		return StudentRepository.findAll();
	}

	@Override
	@Transactional
	public Page<Student> listStudentByNameLike(String Name, Pageable pageable) {
		// TODO Auto-generated method stub
		Name= "%"+Name+"%";
		Page<Student> Student = StudentRepository.findByNameLikeOrderByStatusIntAsc(Name, pageable);
		return Student;
	}

}
