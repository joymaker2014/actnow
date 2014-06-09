/**
 * 
 */
package com.jm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jm.dao.DealDao;
import com.jm.model.Deal;
import com.jm.service.spi.DealService;

/**
 * @author LuZheqi
 * 
 */
@Service("dealService")
public class DealServiceImpl implements DealService {

	@Autowired
	private DealDao dealDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jm.service.spi.DealService#saveDeal(com.jm.model.Deal)
	 */
	@Override
	@Transactional
	public void saveDeal(Deal deal) {
		dealDao.save(deal);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jm.service.spi.DealService#findDealById(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Deal findDealById(String id) {
		return dealDao.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jm.service.spi.DealService#updateDeal(com.jm.model.Deal)
	 */
	@Override
	@Transactional
	public void updateDeal(Deal deal) {
		dealDao.save(deal);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jm.service.spi.DealService#deleteDealById(java.lang.String)
	 */
	@Override
	@Transactional
	public void deleteDealById(String id) {
		dealDao.delete(id);
	}

}
