/**
 * 
 */
package com.jm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jm.dao.OriginalEventDao;
import com.jm.model.OriginalEvent;
import com.jm.service.spi.OriginalEventService;

/**
 * @author LuZheqi
 * 
 */
@Service("originalEventService")
public class OriginalEventServiceImpl implements OriginalEventService {

	@Autowired
	private OriginalEventDao originalEventDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jm.service.spi.OriginalEventService#saveOriginalEvent(com.jm.model
	 * .OriginalEvent)
	 */
	@Override
	@Transactional
	public void saveOriginalEvent(OriginalEvent originalEvent) {
		originalEventDao.save(originalEvent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jm.service.spi.OriginalEventService#findOriginalEventById(java.lang
	 * .String)
	 */
	@Override
	@Transactional(readOnly = true)
	public OriginalEvent findOriginalEventById(String id) {
		return originalEventDao.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jm.service.spi.OriginalEventService#updateOriginalEvent(com.jm.model
	 * .OriginalEvent)
	 */
	@Override
	@Transactional
	public void updateOriginalEvent(OriginalEvent originalEvent) {
		originalEventDao.save(originalEvent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jm.service.spi.OriginalEventService#deleteOriginalEventById(java.
	 * lang.String)
	 */
	@Override
	@Transactional
	public void deleteOriginalEventById(String id) {
		originalEventDao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OriginalEvent> getSimilarEvents(int category, int type,
			int district, int businessCircle) {
		return originalEventDao.findSimilarEvents(category, type, district,
				businessCircle);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OriginalEvent> getEvents(String openid) {
		return (List<OriginalEvent>) originalEventDao.findEvents(openid);
	}

}
