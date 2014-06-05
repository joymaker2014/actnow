/**
 * 
 */
package com.jm.dao;

import org.springframework.data.repository.CrudRepository;

import com.jm.model.Goods;

/**
 * @author LuZheqi
 *
 */
public interface GoodsDao extends CrudRepository<Goods, String> {

}
