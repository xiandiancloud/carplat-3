package com.dhl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhl.dao.CarInOutViewDao;
import com.dhl.dao.Page;
import com.dhl.domain.CarInOutView;

/**
 * 
 *
 */
@Service
public class CarInOutViewService {
	
	@Autowired
	private CarInOutViewDao carInOutDao;
	
	public Page getAllCarInOut(int pageNo,int pageSize,String indate,int status,String outdate)
	{
		return carInOutDao.getAllCarInOut(pageNo, pageSize, indate,status,outdate);
	}
	public List<CarInOutView> qqgetAllCarInOut(int pageSize,String indate,int status,String outdate)
	{
		return carInOutDao.qqgetAllCarInOut(pageSize, indate,status,outdate);
	}
	public List<String[]> getCountList(String indate,String outdate)
	{
		return  carInOutDao.getCountList(indate,outdate);
	}
}
