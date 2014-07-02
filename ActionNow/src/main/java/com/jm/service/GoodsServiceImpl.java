/**
 * 
 */
package com.jm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jm.dao.GoodsDao;
import com.jm.model.Goods;
import com.jm.service.spi.GoodsService;

/**
 * @author LuZheqi
 * 
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsDao goodsDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jm.service.spi.GoodsService#saveGoods(com.jm.model.Goods)
	 */
	@Override
	@Transactional
	public void saveGoods(Goods goods) {
		goodsDao.save(goods);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jm.service.spi.GoodsService#findGoodsById(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Goods findGoodsById(String id) {
		return goodsDao.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jm.service.spi.GoodsService#updateGoods(com.jm.model.Goods)
	 */
	@Override
	@Transactional
	public void updateGoods(Goods goods) {
		goodsDao.save(goods);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jm.service.spi.GoodsService#deleteGoodsById(java.lang.String)
	 */
	@Override
	@Transactional
	public void deleteGoodsById(String id) {
		goodsDao.delete(id);
	}

	@Override
	@Transactional
	public long countCardNumGroupByTypeAndValue(int type, int value) {
		return goodsDao.countCardNumGroupByTypeAndValue(type, value);
	}
	@Override
	@Transactional
	public List<Integer> selectCardsByTypeAndValue(int type, int value) {
		return goodsDao.selectCardsByTypeAndValue(type, value);
	}

	@Override
	@Transactional
	public Goods selectCoodsByCardNum(int cardNum) {
		return goodsDao.selectCoodsByCardNum(cardNum);
	}	
}
