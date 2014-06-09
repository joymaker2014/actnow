/**
 * 
 */
package com.jm.service.spi;

import com.jm.model.Deal;

/**
 * @author LuZheqi
 * 
 */
public interface DealService {

	void saveDeal(Deal deal);

	Deal findDealById(String id);

	void updateDeal(Deal deal);

	void deleteDealById(String id);
}
