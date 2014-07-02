/**
 * 
 */
package com.jm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jm.model.Goods;
import com.jm.model.User;

/**
 * @author LuZheqi
 *
 */
public interface GoodsDao extends CrudRepository<Goods, String> {
	@Query("select count(*) from Goods g where g.type = ?1 and g.value = ?2")
	public int countCardNumGroupByTypeAndValue(int type, int value);
	
	@Query("select cardNum from Goods g where g.type = ?1 and g.value = ?2")
	public List<String> selectCardsByTypeAndValue(int type, int value);
	
	@Query("select g from Goods g where g.cardNum = ?1")
	public Goods selectCoodsByCardNum(int cardNum);
	
	@Query("delete from Goods g where g.cardNum = ?1")
	public int deleteCoodsByCardNum(int cardNum);
	
}
