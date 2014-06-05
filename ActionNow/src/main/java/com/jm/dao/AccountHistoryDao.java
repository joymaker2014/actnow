/**
 * 
 */
package com.jm.dao;

import org.springframework.data.repository.CrudRepository;

import com.jm.model.AccountHistory;

/**
 * @author LuZheqi
 * 
 */
public interface AccountHistoryDao extends
		CrudRepository<AccountHistory, String> {

}
