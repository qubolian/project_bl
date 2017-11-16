package com.spring.boot.blog.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.boot.blog.domain.SubmitFile;
import com.spring.boot.blog.repository.SubmitFileRepository;

@Service
public class SubmitFileServiceImpl implements SubmitFileService {

	@Autowired
	SubmitFileRepository SubmitFileRepository;
	
	@Override
	@Transactional
	public SubmitFile saveSubmitFile(SubmitFile SubmitFile) {
		// TODO Auto-generated method stub
		return SubmitFileRepository.save(SubmitFile);
	}

	@Override
	@Transactional
	public void removeSubmitFile(Long id) {
		// TODO Auto-generated method stub
		SubmitFileRepository.delete(id);

	}

	@Override
	@Transactional
	public void removeSubmitFilesInBatch(List<SubmitFile> SubmitFiles) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public SubmitFile updateSubmitFile(SubmitFile SubmitFile) {
		// TODO Auto-generated method stub
		return SubmitFileRepository.save(SubmitFile);
	}

	@Override
	@Transactional
	public SubmitFile getSubmitFileById(Long id) {
		// TODO Auto-generated method stub
		return SubmitFileRepository.findOne(id);
	}

	@Override
	@Transactional
	public List<SubmitFile> listSubmitFiles() {
		// TODO Auto-generated method stub
		return null;
	}


	
}
