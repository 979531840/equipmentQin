package com.equipment.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.equipment.dao.UserDao;
import com.equipment.model.PageBean;
import com.equipment.model.User;
import com.equipment.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	@Override
	public User login(User user) {
		return userDao.login(user);
	}

	@Override
	public List<User> find(PageBean pageBean, User s_user) {
		return userDao.find(pageBean, s_user);
	}

	@Override
	public int count(User s_user) {
		return userDao.count(s_user);
	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
	}

	@Override
	public void add(User user) {
		userDao.add(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public User loadById(int id) {
		return userDao.loadById(id);
	}

	@Override
	public boolean existUserByDeptId(int deptId) {
		return userDao.existUserByDeptId(deptId);
	}

}
