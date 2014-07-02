/**
 * 
 */
package com.jm.service.spi;

import com.jm.model.UserTrace;

/**
 * @author LuZheqi
 * 
 */
public interface UserTraceService {

	void saveUserTrace(UserTrace userTrace);

	UserTrace findUserTraceById(long id);

	void updateUserTrace(UserTrace userTrace);

	void deleteUserTraceById(long id);
}
