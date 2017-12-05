package com.spring.boot.blog.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.boot.blog.domain.HowtoTeamUp;
import com.spring.boot.blog.domain.WhatsNew;
import com.spring.boot.blog.repository.WhatsNewRepository;

@Service
public class WhatsNewServiceImpl implements WhatsNewService {

	@Autowired
	WhatsNewRepository WhatsNewRepository;
	
	@Override
	@Transactional
	public WhatsNew saveWhatsNew(WhatsNew WhatsNew) {
		// TODO Auto-generated method stub
		return WhatsNewRepository.save(WhatsNew);
	}

	@Override
	@Transactional
	public void removeWhatsNew(Long id) {
		// TODO Auto-generated method stub
		WhatsNew WhatsNew = WhatsNewRepository.findOne(id);
		WhatsNew.setDr(1);
		WhatsNewRepository.save(WhatsNew);

	}

	@Override
	@Transactional
	public void removeWhatsNewsInBatch(List<WhatsNew> WhatsNews) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public WhatsNew updateWhatsNew(WhatsNew WhatsNew) {
		// TODO Auto-generated method stub
		return WhatsNewRepository.save(WhatsNew);
	}

	@Override
	@Transactional
	public WhatsNew getWhatsNewById(Long id) {
		// TODO Auto-generated method stub
		return WhatsNewRepository.findOne(id);
	}

	@Override
	@Transactional
	public List<WhatsNew> listWhatsNews() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Page<WhatsNew> listWhatsNewsByEventsLike(String events, Pageable pageable) {
		// TODO Auto-generated method stub
		events= "%"+events+"%";
		Page<WhatsNew> WhatsNews = WhatsNewRepository.findByEventsLikeAndDrOrderByStartTimeDesc(events, 0, pageable);
		return WhatsNews;
	}

}
