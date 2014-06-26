package com.dhl.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhl.dao.CarInOutDao;
import com.dhl.dao.Page;
import com.dhl.domain.CarInOut;

/**
 * 
 *
 */
@Service
public class CarInOutService {
	
	@Autowired
	private CarInOutDao carInOutDao;
	
	public Page getAllCarInOut(int pageNo,int pageSize,String indate)
	{
		
		return carInOutDao.getAllCarInOut(pageNo, pageSize, indate);
	}
	
	public void save(CarInOut carInout)
	{
		carInOutDao.save(carInout);
	}
}
