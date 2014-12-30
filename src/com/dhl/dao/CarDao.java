package com.dhl.dao;
import java.util.List;

import org.jfree.chart.block.CenterArrangement;
import org.springframework.stereotype.Repository;

import com.dhl.domain.Car;

@Repository
public class CarDao extends BaseDao<Car> {
	
	public Page getAllList(int pageNo,int pageSize)
	{
		String hql = "from Car";
		return pagedQuery(hql, pageNo, pageSize);
//    	return find(hql);
	}
	
	public Page getAllList(int pageNo,int pageSize,String username)
	{
		String hql = "from Car where caruser = '"+username+"'";
		return pagedQuery(hql, pageNo, pageSize);
//    	return find(hql);
		/*return getHibernateTemplate().find(hql);*/
	}
	
	public List<Car> qqgetAllList(int pageNo,int pageSize)
	{
		String hql = "from Car";
		/*return pagedQuery(hql, pageNo, pageSize);*/
//    	return find(hql);
		return getHibernateTemplate().find(hql);
	}
	
	
	public List<Car> qqgetAllList(int pageNo,int pageSize,String username)
	{
		String hql = "from Car where caruser = '"+username+"'";
		/*return pagedQuery(hql, pageNo, pageSize);*/
//    	return find(hql);
		return getHibernateTemplate().find(hql);
	}
	
	public List<Car> getCarByCard(String card)
	{
		String hql = "from Car where card = '"+card+"'";
    	return find(hql);
	}
	
	public List<Car> getAllCar()
	{
		String hql = "from Car";
    	return find(hql);
	}
}
