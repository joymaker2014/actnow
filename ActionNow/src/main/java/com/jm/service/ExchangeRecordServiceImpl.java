/**
 * 
 */
package com.jm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jm.dao.ExchangeRecordDao;
import com.jm.model.ExchangeRecord;
import com.jm.service.spi.ExchangeRecordService;

/**
 * @author mzl
 * 
 */
@Service("exchangeRecordService")
public class ExchangeRecordServiceImpl implements ExchangeRecordService {
	@Autowired
	private ExchangeRecordDao exchangeRecordDao;

	@Override
	@Transactional
	public void saveExchangeRecord(ExchangeRecord exchangeRecord) {
		exchangeRecordDao.save(exchangeRecord);
	}


	@Override
	@Transactional(readOnly = true)
	public ExchangeRecord findExchangeRecordById(String id) {
		return exchangeRecordDao.findOne(id);
	}


	@Override
	@Transactional
	public void updateExchangeRecord(ExchangeRecord exchangeRecord) {
		exchangeRecordDao.save(exchangeRecord);
	}

	@Override
	@Transactional
	public void deleteExchangeRecordById(String id) {
		exchangeRecordDao.delete(id);
	}
	
	@Override
	@Transactional
	public List<ExchangeRecord> findExchangeRecordByOpenid(String openid){
		exchangeRecordDao.findExchangeRecordByOpenid(openid);
		return null;
	}
}
