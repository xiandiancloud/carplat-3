package com.dhl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhl.dao.LedDao;
import com.dhl.domain.Led;

/**
 * 
 *
 */
@Service
public class LedService {
	
	@Autowired
	private LedDao ledDao;
	
	public Led getLed(int id)
	{
		return ledDao.get(id);
	}
	
	public void update(Led entity)
	{
		ledDao.update(entity);
	}
	/**
	 * 取得park信息
	 * @return
	 */
	public List<Led> getLedList(int pos)
	{
		return ledDao.getLedList(pos);
	}
}
