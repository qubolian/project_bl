package com.spring.boot.blog.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.boot.blog.domain.NewsType;
import com.spring.boot.blog.repository.NewsTypeRepository;

@Service
public class NewsTypeServiceImpl implements NewsTypeService {

	@Autowired
	NewsTypeRepository newsTypeRepository;

	@Override
	@Transactional
	public NewsType saveNewsType(NewsType newsType) {
		// TODO Auto-generated method stub
		return newsTypeRepository.save(newsType);
	}

	@Override
	@Transactional
	public void removeNewsType(Long id) {
		// TODO Auto-generated method stub
		NewsType newsType = newsTypeRepository.findOne(id);
		newsType.setDr(1);
		newsTypeRepository.save(newsType);
	}

	@Override
	@Transactional
	public void removeNewsTypesInBatch(List<NewsType> newsTypes) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public NewsType updateNewsType(NewsType newsType) {
		// TODO Auto-generated method stub
		return newsTypeRepository.save(newsType);
	}

	@Override
	@Transactional
	public NewsType getNewsTypeById(Long id) {
		// TODO Auto-generated method stub
		return newsTypeRepository.findOne(id);
	}

	@Override
	@Transactional
	public List<NewsType> listNewsTypes() {
		// TODO Auto-generated method stub
		return newsTypeRepository.findAll();
	}

	@Override
	@Transactional
	public Page<NewsType> listNewsTypesByMessageTypeLike(String messageType, Pageable pageable) {
		// TODO Auto-generated method stub
		messageType = "%" + messageType + "%";
		Page<NewsType> newsTypes = newsTypeRepository.findByMessageTypeLikeAndDr(messageType, 0, pageable);
		return newsTypes;
	}

}
