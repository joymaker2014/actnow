/**
 * 
 */
package com.jm.service.spi;

import java.util.List;

import com.jm.model.Goods;

/**
 * @author LuZheqi
 *
 */
public interface GoodsService {

	void saveGoods(Goods goods);

	Goods findGoodsById(String id);

	void updateGoods(Goods goods);

	void deleteGoodsById(String id);
	
	long countCardNumGroupByTypeAndValue(int type, int value);
	
	List<Integer> selectCardsByTypeAndValue(int type, int value);
	
	Goods selectCoodsByCardNum(int cardNum);
}
