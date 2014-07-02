package com.dhl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhl.dao.ParkDao;
import com.dhl.domain.Park;

/**
 * 
 *
 */
@Service
public class ParkService {
	
	@Autowired
	private ParkDao parkDao;
	
	/**
	 * 取得park信息
	 * @return
	 */
	public List<Park> getAllList()
	{
		return parkDao.getAllPark();
	}
}
