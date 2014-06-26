package com.dhl.dao;
import org.springframework.stereotype.Repository;

import com.dhl.domain.CarInOut;

@Repository
public class CarInOutDao extends BaseDao<CarInOut> {
	
	public Page getAllCarInOut(int pageNo,int pageSize,String indate)
	{
//		String hql = "from CarInOut where bet = "+indate;
//		String hql = "from CarInOut where indate between '"+indate+"' and '2015-01-01'";
		String hql = "from CarInOut where indate = '"+indate+"'";
		return pagedQuery(hql, pageNo, pageSize);
	}
}
