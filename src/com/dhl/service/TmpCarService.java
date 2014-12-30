package com.dhl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhl.cons.CommonConstant;
import com.dhl.dao.Page;
import com.dhl.dao.TmpCarDao;
import com.dhl.domain.TmpCar;

/**
 * 
 *
 */
@Service
public class TmpCarService {
	
	@Autowired
	private TmpCarDao tmpCarDao;
	
	/**
	 * 取得car列表
	 * @return
	 */
	public Page getAllList(int pageNo,int pageSize)
	{
		return tmpCarDao.getAllList(pageNo, pageSize);
	}
	public List<TmpCar> qqgetAllList(int pageNo,int pageSize)
	{
		return tmpCarDao.qqgetAllList(pageNo, pageSize);
	}
	public List<TmpCar> getAllCar()
	{
		return tmpCarDao.getAllCar();
	}
	/**
	 * 跟据id取得案例 
	 * @return
	 */
	public TmpCar get(int id)
	{
		return tmpCarDao.get(id);
	}
	
	public String save(TmpCar car)
	{
		String str = "sucess";
		List<TmpCar> list = tmpCarDao.getCarByCode(car.getCard());
		if (list != null && list.size() > 0)
		{
			return CommonConstant.ERROR_1;
		}
		tmpCarDao.save(car);
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
			List<TmpCar> list = tmpCarDao.getCarByCard(strs[0]);
			if (list != null && list.size() > 0)
			{
				continue;
			}
			TmpCar car = new TmpCar();
			car.setCard(strs[0]);
			car.setCaruser(strs[1]);
			car.setCarposition(strs[2]);
//			car.setTel(strs[3]);
			tmpCarDao.save(car);
		}
		return str;
	}
	
	/**
	 * 导入csv文件的时候调用
	 * @param car
	 * @return
	 */
//	public String save(String[] strs)
//	{
//		String str = "sucess";
//		int len = strs.length;
//		for (int i=0;i<len;i++)
//		{
//			List<TmpCar> list = tmpCarDao.getCarByCC(strs[0], strs[1]);
//			if (list != null && list.size() > 0)
//			{
//				continue;
//			}
//			TmpCar car = new TmpCar();
//			car.setCode(strs[0]);
//			car.setCard(strs[1]);
//			car.setCartype(strs[2]);
//			car.setCaruser(strs[3]);
//			car.setTpye(strs[4]);
//			car.setCarposition(strs[5]);
//			car.setPaytype(strs[6]);
//			car.setTel(strs[7]);
////			car.setIndate(strs[8]);
//			tmpCarDao.save(car);
//		}
//		return str;
//	}
	
	public String update(TmpCar car)
	{
		String str = "sucess";
		tmpCarDao.update(car);
		return str;
	}
	
	public String del(int id)
	{
		String str = "sucess";
		tmpCarDao.remove(get(id));
		return str;
	}
}
