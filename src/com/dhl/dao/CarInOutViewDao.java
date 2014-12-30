package com.dhl.dao;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dhl.cons.CommonConstant;
import com.dhl.domain.CarInOutView;
import com.dhl.util.UtilTools;

@Repository
public class CarInOutViewDao extends BaseDao<CarInOutView> {
	
	public Page getAllCarInOut(int pageNo,int pageSize,String indate,int status,String outdate)
	{
//		String hql = "from CarInOut where bet = "+indate;
//		String hql = "from CarInOut where indate between '"+indate+"' and '2015-01-01'";
		String adate = UtilTools.getSpecifiedDayAfter(indate);
		if (indate.equals(outdate))
		{
			outdate = adate;
		}
		else
		{
			String adates = UtilTools.getSpecifiedDayAfter(outdate);
			outdate = adates;
		}
		String str = status == 0 ? CommonConstant.CAR_IN_STATUS1:CommonConstant.CAR_IN_STATUS2;
		String hql;// = "from CarInOutView where indate < '"+outdate+"' and indate >= '"+indate+"' and status = '"+str+"' order by indate desc";
		if(status == 0)
		{
			hql = "from CarInOutView where indate < '"+outdate+"' and indate >= '"+indate+"' and (status = '"+CommonConstant.CAR_IN_STATUS1+"' or status = '"+CommonConstant.CAR_IN_STATUS2+"') order by indate desc";
		}
		else
		{
			hql = "from CarInOutView where indate < '"+outdate+"' and indate >= '"+indate+"' and (status = '"+CommonConstant.CAR_OUT_STATUS1+"' or status = '"+CommonConstant.CAR_OUT_STATUS2+"') order by indate desc";
		}
//		String hql = "from CarInOutView where indate < '2024-06-08' and status = '"+str+"'";
		return pagedQuery(hql, pageNo, pageSize);
	}
	
	public List<CarInOutView> qqgetAllCarInOut(int pageSize,String indate,int status,String outdate)
	{
//		String hql = "from CarInOut where bet = "+indate;
//		String hql = "from CarInOut where indate between '"+indate+"' and '2015-01-01'";
		String adate = UtilTools.getSpecifiedDayAfter(indate);
		if (indate.equals(outdate))
		{
			outdate = adate;
		}
		else
		{
			String adates = UtilTools.getSpecifiedDayAfter(outdate);
			outdate = adates;
		}
		String str = status == 0 ? CommonConstant.CAR_IN_STATUS1:CommonConstant.CAR_IN_STATUS2;
		String hql;// = "from CarInOutView where indate < '"+outdate+"' and indate >= '"+indate+"' and status = '"+str+"' order by indate desc";
		if(status == 0)
		{
			hql = "from CarInOutView where indate < '"+outdate+"' and indate >= '"+indate+"' and (status = '"+CommonConstant.CAR_IN_STATUS1+"' or status = '"+CommonConstant.CAR_IN_STATUS2+"') order by indate desc";
		}
		else
		{
			hql = "from CarInOutView where indate < '"+outdate+"' and indate >= '"+indate+"' and (status = '"+CommonConstant.CAR_OUT_STATUS1+"' or status = '"+CommonConstant.CAR_OUT_STATUS2+"') order by indate desc";
		}
//		String hql = "from CarInOutView where indate < '2024-06-08' and status = '"+str+"'";
//		return qqpagedQuery(hql,1000);
		return getHibernateTemplate().find(hql);
	}
	
	
	public List<String[]> getCountList(String indate,String outdate)
	{
//		String queryString = "SELECT pos,count(*) from CarInOutView group by pos";
		List<String[]> endList = new ArrayList();
		String adate = UtilTools.getSpecifiedDayAfter(indate);
		if (indate.equals(outdate))
		{
			outdate = adate;
		}
		else
		{
			String adates = UtilTools.getSpecifiedDayAfter(outdate);
			outdate = adates;
		}
		String hql1 = "select pos,count(*) as num from CarInOutView where indate < '"+outdate+"' and indate >= '"+indate+"'" +"group by pos";
		List server = (List) getHibernateTemplate().find(hql1);
		int len = server.size();
		for (int i=0;i<len;i++)
		{
			String[] strs = new String[5];
			Object[] obj = (Object[])server.get(i);
			Long count = (Long)obj[1];
			strs[0] = ""+obj[0];
			//System.out.println("总数 ----------- "+obj[0]+"  ,  "+count);
			String hql2 = "select count(*) as num from CarInOutView where indate < '"+outdate+"' and indate >= '"+indate+"' and status='"+CommonConstant.CAR_IN_STATUS1+"' and pos = '"+obj[0]+"'";
			if (obj[0] == null)
			{
				//System.out.println("位置为空 ----------- "+obj[0]);
//				obj[0] = "";
				hql2 = "select count(*) as num from CarInOutView where indate < '"+outdate+"' and indate >= '"+indate+"' and status='"+CommonConstant.CAR_IN_STATUS1+"'";
			}
			//计算前门进入车辆
			List server2 = (List)getHibernateTemplate().find(hql2);
			if (server2 != null && server2.size() == 1)
			{
				Long incount = (Long)server2.get(0);
//				System.out.println("进入------------ "+incount);
				strs[1] = ""+incount;
//				strs[2] = ""+(count-incount);
			}
			else
			{
				strs[1] = "0";
//				strs[2] = ""+(count);
			}
			///////////////////
			String hql3 = "select count(*) as num from CarInOutView where indate < '"+outdate+"' and indate >= '"+indate+"' and status='"+CommonConstant.CAR_IN_STATUS2+"' and pos = '"+obj[0]+"'";
			if (obj[0] == null)
			{
				//System.out.println("位置为空 ----------- "+obj[0]);
//				obj[0] = "";
				hql3 = "select count(*) as num from CarInOutView where indate < '"+outdate+"' and indate >= '"+indate+"' and status='"+CommonConstant.CAR_IN_STATUS2+"'";
			}
			//计算后门进入车辆
			List server3 = (List)getHibernateTemplate().find(hql3);
			if (server3 != null && server3.size() == 1)
			{
				Long incount = (Long)server3.get(0);
//				System.out.println("进入------------ "+incount);
				strs[2] = ""+incount;
//				strs[2] = ""+(count-incount);
			}
			else
			{
				strs[2] = "0";
//				strs[2] = ""+(count);
			}
			////////////
			String hql4 = "select count(*) as num from CarInOutView where indate < '"+outdate+"' and indate >= '"+indate+"' and status='"+CommonConstant.CAR_OUT_STATUS1+"' and pos = '"+obj[0]+"'";
			if (obj[0] == null)
			{
				//System.out.println("位置为空 ----------- "+obj[0]);
//				obj[0] = "";
				hql4 = "select count(*) as num from CarInOutView where indate < '"+outdate+"' and indate >= '"+indate+"' and status='"+CommonConstant.CAR_OUT_STATUS1+"'";
			}
			//计算后门进入车辆
			List server4 = (List)getHibernateTemplate().find(hql4);
			if (server4 != null && server4.size() == 1)
			{
				Long incount = (Long)server4.get(0);
//				System.out.println("进入------------ "+incount);
				strs[3] = ""+incount;
//				strs[2] = ""+(count-incount);
			}
			else
			{
				strs[3] = "0";
//				strs[2] = ""+(count);
			}
			////////////
			String hql5 = "select count(*) as num from CarInOutView where indate < '"+outdate+"' and indate >= '"+indate+"' and status='"+CommonConstant.CAR_OUT_STATUS2+"' and pos = '"+obj[0]+"'";
			if (obj[0] == null)
			{
				//System.out.println("位置为空 ----------- "+obj[0]);
//					obj[0] = "";
				hql5 = "select count(*) as num from CarInOutView where indate < '"+outdate+"' and indate >= '"+indate+"' and status='"+CommonConstant.CAR_OUT_STATUS2+"'";
			}
			//计算后门进入车辆
			List server5 = (List)getHibernateTemplate().find(hql5);
			if (server5 != null && server5.size() == 1)
			{
				Long incount = (Long)server5.get(0);
//					System.out.println("进入------------ "+incount);
				strs[4] = ""+incount;
//					strs[2] = ""+(count-incount);
			}
			else
			{
				strs[4] = "0";
//					strs[2] = ""+(count);
			}
			endList.add(strs);
		}
		return endList;
	}
}
