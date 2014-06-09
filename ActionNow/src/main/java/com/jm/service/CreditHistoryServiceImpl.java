/**
 * 
 */
package com.jm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jm.dao.CreditHistoryDao;
import com.jm.model.CreditHistory;
import com.jm.service.spi.CreditHistoryService;

/**
 * @author LuZheqi
 * 
 */
@Service("creditHistoryService")
public class CreditHistoryServiceImpl implements CreditHistoryService {

	@Autowired
	private CreditHistoryDao creditHistoryDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jm.service.spi.CreditHistoryService#saveCreditHistory(com.jm.model
	 * .CreditHistory)
	 */
	@Override
	@Transactional
	public void saveCreditHistory(CreditHistory creditHistory) {
		creditHistoryDao.save(creditHistory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jm.service.spi.CreditHistoryService#findCreditHistoryById(java.lang
	 * .String)
	 */
	@Override
	@Transactional(readOnly = true)
	public CreditHistory findCreditHistoryById(String id) {
		return creditHistoryDao.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jm.service.spi.CreditHistoryService#updateCreditHistory(com.jm.model
	 * .CreditHistory)
	 */
	@Override
	@Transactional
	public void updateCreditHistory(CreditHistory creditHistory) {
		creditHistoryDao.save(creditHistory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jm.service.spi.CreditHistoryService#deleteCreditHistoryById(java.
	 * lang.String)
	 */
	@Override
	@Transactional
	public void deleteCreditHistoryById(String id) {
		creditHistoryDao.delete(id);
	}

}
