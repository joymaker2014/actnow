/**
 * 
 */
package com.jm.service.spi;

import com.jm.model.Goods;
import java.util.List;

/**
 * @author LuZheqi
 *
 */
public interface GoodsService {

	void saveGoods(Goods goods);

	Goods findGoodsById(String id);

	void updateGoods(Goods goods);

	void deleteGoodsById(String id);
	
	int countCardNumGroupByTypeAndValue(int type, int value);
	
	List<String> selectCardsByTypeAndValue(int type, int value);
	
	Goods selectCoodsByCardNum( int cardNum);
	
	int deleteCoodsByCardNum(int cardNum);
}
