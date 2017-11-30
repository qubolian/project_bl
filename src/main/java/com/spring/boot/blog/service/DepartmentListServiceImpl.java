package com.spring.boot.blog.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.boot.blog.domain.DepartmentList;
import com.spring.boot.blog.repository.DepartmentListRepository;

@Service
public class DepartmentListServiceImpl implements DepartmentListService {

	@Autowired
	DepartmentListRepository DepartmentListRepository;

	@Override
	@Transactional
	public DepartmentList saveDepartmentList(DepartmentList DepartmentList) {
		// TODO Auto-generated method stub
		return DepartmentListRepository.save(DepartmentList);
	}

	@Override
	@Transactional
	public void removeDepartmentList(Long id) {
		// TODO Auto-generated method stub
		DepartmentList departmentList = DepartmentListRepository.findOne(id);
		departmentList.setDr(1);
		DepartmentListRepository.save(departmentList);
	}

	@Override
	@Transactional
	public void removeDepartmentListsInBatch(List<DepartmentList> DepartmentLists) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public DepartmentList updateDepartmentList(DepartmentList DepartmentList) {
		// TODO Auto-generated method stub
		return DepartmentListRepository.save(DepartmentList);
	}

	@Override
	@Transactional
	public DepartmentList getDepartmentListById(Long id) {
		// TODO Auto-generated method stub
		return DepartmentListRepository.findOne(id);
	}

	@Override
	@Transactional
	public List<DepartmentList> listDepartmentLists() {
		// TODO Auto-generated method stub
		return DepartmentListRepository.findAll();
	}

	@Override
	@Transactional
	public Page<DepartmentList> listDepartmentListsByDepartmentLike(String department, Pageable pageable) {
		// TODO Auto-generated method stub
		department = "%" + department + "%";
		Page<DepartmentList> DepartmentLists = DepartmentListRepository.findByDepartmentLikeAndDr(department, 0,
				pageable);
		return DepartmentLists;
	}

}
