package com.dhl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhl.cons.CommonConstant;
import com.dhl.dao.CarDao;
import com.dhl.dao.Page;
import com.dhl.domain.Car;

/**
 * 
 *
 */
@Service
public class CarService {
	
	@Autowired
	private CarDao caseDao;
	
	/**
	 * 取得car列表
	 * @return
	 */
	public Page getAllList(int pageNo,int pageSize)
	{
		return caseDao.getAllList(pageNo, pageSize);
	}
	
	/**
	 * 取得个人car列表
	 * @return
	 */
	public Page getAllList(int pageNo,int pageSize,String username)
	{
		return caseDao.getAllList(pageNo, pageSize, username);
	}
	
	/**
	 * 跟据id取得案例 
	 * @return
	 */
	public Car get(int id)
	{
		return caseDao.get(id);
	}
	
	public String save(Car car)
	{
		String str = "sucess";
		List<Car> list = caseDao.getCarByCard(car.getCard());
		if (list != null && list.size() > 0)
		{
			return CommonConstant.ERROR_1;
		}
		caseDao.save(car);
		return str;
	}
	
	/**
	 * 导入csv文件的时候调用
	 * @param car
	 * @return
	 */
	public String save(String[] strs)
	{
		String str = "sucess";
		int len = strs.length;
		for (int i=0;i<len;i++)
		{
			List<Car> list = caseDao.getCarByCard(strs[0]);
			if (list != null && list.size() > 0)
			{
				continue;
			}
			Car car = new Car();
			car.setCard(strs[0]);
			car.setCaruser(strs[1]);
			car.setCarposition(strs[2]);
			car.setTel(strs[3]);
			caseDao.save(car);
		}
		return str;
	}
	
	public String update(Car car)
	{
		String str = "sucess";
		caseDao.update(car);
		return str;
	}
	
	public String del(int id)
	{
		String str = "sucess";
		caseDao.remove(get(id));
		return str;
	}
}
