/**
 * 
 */
package com.jm.service.spi;

import java.util.List;

import com.jm.model.ExchangeRecord;

/**
 * @author mzl
 *
 */
public interface ExchangeRecordService {

	void saveExchangeRecord(ExchangeRecord exchangeRecord);

	ExchangeRecord findExchangeRecordById(String id);

	void updateExchangeRecord(ExchangeRecord exchangeRecord);

	void deleteExchangeRecordById(String id);
	
	List<ExchangeRecord> findExchangeRecordByOpenid(String openid);
}
