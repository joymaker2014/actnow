/**
 * 
 */
package com.jm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jm.dao.AccountHistoryDao;
import com.jm.model.AccountHistory;
import com.jm.service.spi.AccountHistoryService;

/**
 * @author LuZheqi
 * 
 */
@Service("accountHistoryService")
public class AccountHistoryServiceImpl implements AccountHistoryService {

	@Autowired
	private AccountHistoryDao accountHistoryDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jm.service.spi.AccountHistoryService#saveAccountHistory(com.jm.model
	 * .AccountHistory)
	 */
	@Override
	@Transactional
	public void saveAccountHistory(AccountHistory accountHistory) {
		accountHistoryDao.save(accountHistory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jm.service.spi.AccountHistoryService#findAccountHistoryById(java.
	 * lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public AccountHistory findAccountHistoryById(String id) {
		return accountHistoryDao.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jm.service.spi.AccountHistoryService#updateAccountHistory(com.jm.
	 * model.AccountHistory)
	 */
	@Override
	@Transactional
	public void updateAccountHistory(AccountHistory accountHistory) {
		accountHistoryDao.save(accountHistory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jm.service.spi.AccountHistoryService#deleteAccountHistoryById(java
	 * .lang.String)
	 */
	@Override
	@Transactional
	public void deleteAccountHistoryById(String id) {
		accountHistoryDao.delete(id);
	}

}
