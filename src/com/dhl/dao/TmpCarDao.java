package com.dhl.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dhl.domain.TmpCar;

@Repository
public class TmpCarDao extends BaseDao<TmpCar> {
	
	public Page getAllList(int pageNo,int pageSize)
	{
		String hql = "from TmpCar";
		return pagedQuery(hql, pageNo, pageSize);
//    	return find(hql);
	}
	
	public Page getAllList(int pageNo,int pageSize,String username)
	{
		String hql = "from TmpCar where caruser = '"+username+"'";
		return pagedQuery(hql, pageNo, pageSize);
//    	return find(hql);
	}
	
	public List<TmpCar> getCarByCode(String card)
	{
		String hql = "from TmpCar where card = '"+card+"'";
    	return find(hql);
	}
	
	public List<TmpCar> getCarByCard(String card)
	{
		String hql = "from TmpCar where card = '"+card+"'";
    	return find(hql);
	}
	
	public List<TmpCar> getAllCar()
	{
		String hql = "from TmpCar";
    	return find(hql);
	}
}
