/**
 * 
 */
package com.jm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jm.model.OriginalEvent;

/**
 * @author LuZheqi
 * 
 */
public interface OriginalEventDao extends CrudRepository<OriginalEvent, String> {
	@Query("select o from OriginalEvent o where o.category=?1 and o.type=?2 and o.district=?3 and o.businessCircle=?4")
	public List<OriginalEvent> findSimilarEvents(int category, int type,
			int district, int businessCircle);

	@Query("select o from OriginalEvent o where o.user.openid=?1")
	public List<OriginalEvent> findEvents(String openid);
}
