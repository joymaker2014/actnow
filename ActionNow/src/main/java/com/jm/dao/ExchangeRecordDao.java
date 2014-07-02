/**
 * 
 */
package com.jm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jm.model.ExchangeRecord;
import com.jm.model.Goods;
import com.jm.model.User;

/**
 * @author mzl
 * 
 */
public interface ExchangeRecordDao extends
		CrudRepository<ExchangeRecord, String> {
	@Query("select e from ExchangeRecord e where e.openid=?1")
	public List<ExchangeRecord> findExchangeRecordByOpenid(String openid);
}
