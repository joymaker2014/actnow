/**
 * 
 */
package com.jm.service.spi;

import com.jm.model.ExchangeRecord;
import com.jm.model.Goods;

import java.util.List;

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
