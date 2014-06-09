/**
 * 
 */
package com.jm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jm.dao.BlackListDao;
import com.jm.model.BlackList;
import com.jm.service.spi.BlackListService;

/**
 * @author LuZheqi
 * 
 */
@Service("blackListService")
public class BlackListServiceImpl implements BlackListService {

	@Autowired
	private BlackListDao blackListDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jm.service.spi.BlackListService#saveBlackList(com.jm.model.BlackList)
	 */
	@Override
	@Transactional
	public void saveBlackList(BlackList blackList) {
		blackListDao.save(blackList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jm.service.spi.BlackListService#findBlackListById(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public BlackList findBlackListById(String id) {
		return blackListDao.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jm.service.spi.BlackListService#updateBlackList(com.jm.model.BlackList
	 * )
	 */
	@Override
	public void updateBlackList(BlackList blackList) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jm.service.spi.BlackListService#deleteBlackListById(java.lang.String)
	 */
	@Override
	public void deleteBlackListById(String id) {
		// TODO Auto-generated method stub

	}

}
