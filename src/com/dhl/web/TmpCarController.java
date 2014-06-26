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
import com.dhl.domain.TmpCar;
import com.dhl.service.TmpCarService;
import com.dhl.util.UtilTools;

/**
 * 
 * @see
 * @since
 */
@Controller
public class TmpCarController {
	/**
	 * 自动注入
	 */
	@Autowired
	private TmpCarService caseService;

	/**
	 * 取得所有车辆信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/tmpgetAllCar")
	public void tmpgetAllCar(HttpServletRequest request,HttpServletResponse response, int pindex) {
		try {
			Page list = caseService
					.getAllList(pindex, CommonConstant.PAGE_SIZE);
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
	@RequestMapping("/tmpgetCar")
	public void tmpgetCar(HttpServletRequest request,HttpServletResponse response, int id) {
		try {
			TmpCar car = caseService.get(id);
			PrintWriter out = response.getWriter();
			String str = "{'card':'" + car.getCard()+ "','indate':'"+ car.getIndate() 
					+ "','caruser':'" + car.getCaruser() + "','address':'" + car.getAddress()+"','tel':'"+car.getTel()+"'}";
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
	@RequestMapping("/tmpsaveCar")
	public void tmpsaveCar(HttpServletRequest request,HttpServletResponse response, TmpCar car) {
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
	@RequestMapping("/tmpupdateCar")
	public void tmpupdateCar(HttpServletRequest request,HttpServletResponse response, TmpCar car) {
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
	@RequestMapping("/tmpdelCar")
	public void tmpdelCar(HttpServletRequest request,HttpServletResponse response, int id) {
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
	private String javatojson(Page list) {
		StringBuffer buffer = new StringBuffer();
		long count = list.getTotalCount();
		buffer.append("{\"total\":" + count + ",\"rows\":[");
		List<TmpCar> data = list.getResult();
		Iterator<TmpCar> it = data.iterator();
		while (it.hasNext()) {
			TmpCar p = it.next();
			buffer.append("{");
			buffer.append("\"id\":");
			buffer.append("\"" + p.getId() + "\"");
			buffer.append(",\"code\":");
			buffer.append("\"" + UtilTools.getNumber("F", p.getId()) + "\"");
			buffer.append(",\"card\":");
			buffer.append("\"" + p.getCard() + "\"");		
			buffer.append(",\"caruser\":");
			buffer.append("\"" + p.getCaruser() + "\"");
			buffer.append(",\"address\":");
			buffer.append("\"" + p.getAddress()+ "\"");
			buffer.append(",\"indate\":");
			buffer.append("\"" + p.getIndate() + "\"");
			buffer.append(",\"tel\":");
			buffer.append("\"" + p.getTel() + "\"");
			buffer.append("},");
		}
		if (count > 0) {
			String str = buffer.substring(0, buffer.length() - 1) + "]}";
			str = str.replaceAll("null", "");
			return str;
		} else {
			String str = buffer.toString() + "]}";
			str = str.replaceAll("null", "");
			return str;
		}
	}
}
