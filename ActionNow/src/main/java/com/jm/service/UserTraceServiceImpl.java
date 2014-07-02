/**
 * 
 */
package com.jm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jm.dao.UserTraceDao;
import com.jm.model.UserTrace;
import com.jm.service.spi.UserTraceService;

/**
 * @author LuZheqi
 * 
 */
@Service("userTraceService")
public class UserTraceServiceImpl implements UserTraceService {
	@Autowired
	private UserTraceDao userTraceDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jm.service.spi.UserTraceService#saveUserTrace(com.jm.model.UserTrace)
	 */
	@Override
	@Transactional
	public void saveUserTrace(UserTrace userTrace) {
		userTraceDao.save(userTrace);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jm.service.spi.UserTraceService#findUserTraceById(long)
	 */
	@Override
	@Transactional(readOnly = true)
	public UserTrace findUserTraceById(long id) {
		return userTraceDao.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jm.service.spi.UserTraceService#updateUserTrace(com.jm.model.UserTrace
	 * )
	 */
	@Override
	@Transactional
	public void updateUserTrace(UserTrace userTrace) {
		userTraceDao.save(userTrace);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jm.service.spi.UserTraceService#deleteUserTraceById(java.lang.String)
	 */
	@Override
	@Transactional
	public void deleteUserTraceById(long id) {
		userTraceDao.delete(id);
	}

}
