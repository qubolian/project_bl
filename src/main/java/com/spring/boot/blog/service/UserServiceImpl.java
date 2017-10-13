package com.spring.boot.blog.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService,UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Transactional
	public void removeUser(Long id) {
		// TODO Auto-generated method stub
		userRepository.delete(id);
	}

	@Transactional
	public void removeUsersInBatch(List<User> users) {
		// TODO Auto-generated method stub

	}

	@Transactional
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findOne(id);
	}

	public List<User> listUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<User> listUsersByNameLike(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		name= "%"+name+"%";
		Page<User> users = userRepository.findByNameLike(name, pageable);
		return users;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userRepository.findByUserName(username);
	}

}
