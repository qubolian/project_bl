package com.spring.boot.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.boot.blog.domain.LeaderMemberResponsibility;
import com.spring.boot.blog.repository.LeaderMemberResponsibilityRepository;

@Service
public class LeaderMemberResponsibilityServiceImpl implements LeaderMemberResponsibilityService {

	@Autowired
	LeaderMemberResponsibilityRepository LeaderMemberResponsibilityRepository;
	
	@Override
	public LeaderMemberResponsibility getLeaderMemberResponsibilityById(Long id) {
		return LeaderMemberResponsibilityRepository.findOne(id);
	}
	
	@Override
	public LeaderMemberResponsibility saveLeaderMemberResponsibility(LeaderMemberResponsibility leaderMemberResponsibility) {
		// TODO Auto-generated method stub
		LeaderMemberResponsibility lmr = LeaderMemberResponsibilityRepository.save(leaderMemberResponsibility);
		return lmr;
	}

	
}
