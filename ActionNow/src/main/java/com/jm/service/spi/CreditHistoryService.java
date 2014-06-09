/**
 * 
 */
package com.jm.service.spi;

import com.jm.model.CreditHistory;

/**
 * @author LuZheqi
 * 
 */
public interface CreditHistoryService {

	void saveCreditHistory(CreditHistory creditHistory);

	CreditHistory findCreditHistoryById(String id);

	void updateCreditHistory(CreditHistory creditHistory);

	void deleteCreditHistoryById(String id);
}
