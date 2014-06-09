/**
 * 
 */
package com.jm.service.spi;

import com.jm.model.BlackList;

/**
 * @author LuZheqi
 * 
 */
public interface BlackListService {

	void saveBlackList(BlackList blackList);

	BlackList findBlackListById(String id);

	void updateBlackList(BlackList blackList);

	void deleteBlackListById(String id);
}
