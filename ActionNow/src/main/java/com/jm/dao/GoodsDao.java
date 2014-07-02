/**
 * 
 */
package com.jm.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jm.model.Goods;

/**
 * @author LuZheqi
 *
 */
public interface GoodsDao extends CrudRepository<Goods, String> {
	@Query("select count(*) from Goods g where g.type = ?1 and g.value = ?2")
	public int countCardNumGroupByTypeAndValue(int type, int value);
}
