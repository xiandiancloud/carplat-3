package com.dhl.web;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhl.cons.CommonConstant;
import com.dhl.dao.Page;
import com.dhl.domain.Car;
import com.dhl.service.CarService;
import com.dhl.util.UtilTools;

/**
 * 
 * @see
 * @since
 */
@Controller
public class CarController {
	/**
	 * 自动注入
	 */
	@Autowired
	private CarService caseService;

	/**
	 * 取得所有车辆信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getAllCar")
	public void getAllCar(HttpServletRequest request,HttpServletResponse response, int pindex,int role,String username) {
		try {			
			Page list = null;// = caseService.getAllList(pindex, CommonConstant.PAGE_SIZE);
			if (role == CommonConstant.ADMIN_ROLE)
			{
				list = caseService.getAllList(pindex, CommonConstant.PAGE_SIZE);
			}
			else// if (role == CommonConstant.TEARCHER_ROLE)
			{
				list = caseService.getAllList(pindex, CommonConstant.PAGE_SIZE, username);
			}
			PrintWriter out = response.getWriter();
			String str = javatojson(list);
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/qqgetAllCar")
	public void qqgetAllCar(HttpServletRequest request,HttpServletResponse response, int pindex,int role,String username) {
		try {	
			List<Car> list;
			/*Page list = null;// = caseService.getAllList(pindex, CommonConstant.PAGE_SIZE);
*/			if (role == CommonConstant.ADMIN_ROLE)
			{
				 list = caseService.qqgetAllList(pindex, CommonConstant.PAGE_SIZE);
			}
			else// if (role == CommonConstant.TEARCHER_ROLE)
			{
				 list = caseService.qqgetAllList(pindex, CommonConstant.PAGE_SIZE, username);
			}
			PrintWriter out = response.getWriter();
			String str = javatojson(list);
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 取得车辆信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getCar")
	public void getCar(HttpServletRequest request,HttpServletResponse response, int id) {
		try {
			Car car = caseService.get(id);
			PrintWriter out = response.getWriter();
			String str = "{'card':'" + car.getCard()
					+ "','caruser':'" + car.getCaruser()+"','tel':'"+car.getTel()+"'}";
			str = str.replaceAll("null", "");
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存车辆
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/saveCar")
	public void saveCar(HttpServletRequest request,HttpServletResponse response, Car car) {
		try {
			String str = caseService.save(car);
			PrintWriter out = response.getWriter();
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存车辆
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/updateCar")
	public void updateCar(HttpServletRequest request,HttpServletResponse response, Car car) {
		try {
			String str = caseService.update(car);
			PrintWriter out = response.getWriter();
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除车辆
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/delCar")
	public void delCar(HttpServletRequest request,HttpServletResponse response, int id) {
		try {
			String str = caseService.del(id);
			PrintWriter out = response.getWriter();
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * list中有set，没有找到合适的转json方法，先拼接个用着
	 * 
	 * @param list
	 * @return
	 */
	private String javatojson(Page list) 
	{
		StringBuffer buffer = new StringBuffer();
		long count = list.getTotalCount();
		buffer.append("{\"total\":" + count + ",\"rows\":[");
		List<Car> data = list.getResult();
		Iterator<Car> it = data.iterator();
		while (it.hasNext()) 
		{
			Car p = it.next();
			buffer.append("{");
			buffer.append("\"id\":");
			buffer.append("\"" + p.getId() + "\"");
			buffer.append(",\"code\":");
			buffer.append("\"" + UtilTools.getNumber("C", p.getId()) + "\"");
			buffer.append(",\"card\":");
			buffer.append("\"" + p.getCard().trim() + "\"");
			buffer.append(",\"caruser\":");
			buffer.append("\"" + p.getCaruser() + "\"");
			buffer.append(",\"carposition\":");
			buffer.append("\"" + p.getCarposition() + "\"");			
			buffer.append(",\"tel\":");
			buffer.append("\"" + p.getTel() + "\"");
			buffer.append("},");
		}
		if (count > 0) 
		{
			String str = buffer.substring(0, buffer.length() - 1) + "]}";
			str = str.replaceAll("null", "");
			return str;
		} 
		else 
		{
			String str = buffer.toString() + "]}";
			str = str.replaceAll("null", "");
			return str;
		}
	}
	
	private String javatojson(List<Car> list) 
	{
		StringBuffer buffer = new StringBuffer();
		int count = list.size();
		buffer.append("{\"total\":" + count + ",\"rows\":[");
		/*List<Car> data = list.getResult();*/
		/*Iterator<Car> it = data.iterator();*/
		/*while (it.hasNext()) */
		for (Car p:list) 
		{
			/*Car p = it.next();*/
			buffer.append("{");
			buffer.append("\"id\":");
			buffer.append("\"" + p.getId() + "\"");
			buffer.append(",\"code\":");
			buffer.append("\"" + UtilTools.getNumber("C", p.getId()) + "\"");
			buffer.append(",\"card\":");
			buffer.append("\"" + p.getCard().trim() + "\"");
			buffer.append(",\"caruser\":");
			buffer.append("\"" + p.getCaruser() + "\"");
			buffer.append(",\"carposition\":");
			buffer.append("\"" + p.getCarposition() + "\"");			
			buffer.append(",\"tel\":");
			buffer.append("\"" + p.getTel() + "\"");
			buffer.append("},");
		}
		if (count > 0) 
		{
			String str = buffer.substring(0, buffer.length() - 1) + "]}";
			str = str.replaceAll("null", "");
			return str;
		} 
		else 
		{
			String str = buffer.toString() + "]}";
			str = str.replaceAll("null", "");
			return str;
		}
	}
	
	
}
