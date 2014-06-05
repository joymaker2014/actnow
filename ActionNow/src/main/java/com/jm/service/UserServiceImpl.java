/**
 * 
 */
package com.jm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jm.dao.UserDao;
import com.jm.model.User;
import com.jm.service.spi.UserService;

/**
 * @author LuZheqi
 * 
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;// 注入UserDao

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jm.service.spi.UserService#saveUser(com.jm.model.User)
	 */
	@Override
	@Transactional
	public void saveUser(User user) {
		userDao.save(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jm.service.spi.UserService#findUserById(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public User findUserById(String id) {
		return userDao.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jm.service.spi.UserService#updateUser(com.jm.model.User)
	 */
	@Override
	@Transactional
	public void updateUser(User user) {
		userDao.save(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jm.service.spi.UserService#deleteUserById(java.lang.String)
	 */
	@Override
	@Transactional
	public void deleteUserById(String id) {
		userDao.delete(id);
	}

	@Override
	@Transactional
	public List<User> findUserOrderByNicknameDesc() {
		return userDao.findOrderByNameDesc();
	}

}
