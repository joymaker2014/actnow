/**
 * 
 */
package com.jm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jm.model.Goods;

/**
 * @author LuZheqi
 *
 */
public interface GoodsDao extends CrudRepository<Goods, String> {
	@Query("select count(*) from Goods g where g.type = ?1 and g.value = ?2")
	public long countCardNumGroupByTypeAndValue(int type, int value);
	
	@Query("select cardNum from Goods g where g.type = ?1 and g.value = ?2")
	public List<Integer> selectCardsByTypeAndValue(int type, int value);
	
	@Query("select g from Goods g where g.cardNum = ?1")
	public Goods selectCoodsByCardNum(int cardNum);
}
