/**
 * 
 */
package com.jm.service.spi;

import com.jm.model.AccountHistory;

/**
 * @author LuZheqi
 * 
 */
public interface AccountHistoryService {

	void saveAccountHistory(AccountHistory accountHistory);

	AccountHistory findAccountHistoryById(String id);

	void updateAccountHistory(AccountHistory accountHistory);

	void deleteAccountHistoryById(String id);
}
