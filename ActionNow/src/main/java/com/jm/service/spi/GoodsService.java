/**
 * 
 */
package com.jm.service.spi;

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
}
