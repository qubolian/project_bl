package com.spring.boot.blog.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.spring.boot.blog.domain.HowtoTeamUp;
import com.spring.boot.blog.repository.HowtoTeamUpRepository;

@Service
public class HowtoTeamUpServiceImpl implements HowtoTeamUpService {

	@Autowired
	HowtoTeamUpRepository HowtoTeamUpRepository;
	
	@Override
	@Transactional
	public HowtoTeamUp saveHowtoTeamUp(HowtoTeamUp HowtoTeamUp) {
		// TODO Auto-generated method stub
		return HowtoTeamUpRepository.save(HowtoTeamUp);
	}

	@Override
	@Transactional
	public void removeHowtoTeamUp(Long id) {
		// TODO Auto-generated method stub
		HowtoTeamUpRepository.delete(id);

	}

	@Override
	@Transactional
	public void removeHowtoTeamUpInBatch(List<HowtoTeamUp> HowtoTeamUp) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public HowtoTeamUp updateHowtoTeamUp(HowtoTeamUp HowtoTeamUp) {
		// TODO Auto-generated method stub
		return HowtoTeamUpRepository.save(HowtoTeamUp);
	}

	@Override
	@Transactional
	public HowtoTeamUp getHowtoTeamUpById(Long id) {
		// TODO Auto-generated method stub
		return HowtoTeamUpRepository.findOne(id);
	}

	@Override
	@Transactional
	public List<HowtoTeamUp> listHowtoTeamUp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Page<HowtoTeamUp> listHowtoTeamUpByGradeIdLike(String gradeId, Pageable pageable) {
		// TODO Auto-generated method stub
		gradeId= "%"+gradeId+"%";
		Page<HowtoTeamUp> HowtoTeamUp = HowtoTeamUpRepository.findByGradeIdLike(gradeId, pageable);
		return HowtoTeamUp;
	}

}
