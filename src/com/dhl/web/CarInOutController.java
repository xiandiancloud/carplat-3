package com.dhl.web;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhl.dao.Page;
import com.dhl.domain.CarInOut;
import com.dhl.service.CarInOutService;
import com.dhl.util.UtilTools;

/**
 * 
 * @see
 * @since
 */
@Controller
public class CarInOutController {
	/**
	 * 自动注入
	 */
	@Autowired
	private CarInOutService carInOutService;

//	/**
//	 * 取得所有车辆进出信息
//	 * 
//	 * @param request
//	 * @param response
//	 */
//	@RequestMapping("/carInOut")
//	public void carInOut(HttpServletRequest request,HttpServletResponse response,int pindex, String indate) {
//		try {
////			Date date = UtilTools.StringToDate(indate);
//			Page list = carInOutService.getAllCarInOut(pindex, CommonConstant.PAGE_SIZE,indate);
//			PrintWriter out = response.getWriter();
//			String str = javatojson(list);
//			out.write(str);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 取得所有车辆进出信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/savecarInOut")
	public void savecarInOut(HttpServletRequest request,HttpServletResponse response,int pindex, String indate) {
		try {
			Date date = UtilTools.StringToDate(indate);
			CarInOut inout = new CarInOut();
			inout.setCard("1111");
			inout.setIndate(date);
			inout.setStatus("出");
			carInOutService.save(inout);
			PrintWriter out = response.getWriter();
			String str = "";//javatojson(list);
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
		List<CarInOut> data = list.getResult();
		Iterator<CarInOut> it = data.iterator();
		while (it.hasNext()) {
			CarInOut p = it.next();
			buffer.append("{");
			buffer.append("\"id\":");
			buffer.append("\"" + p.getId() + "\"");
			buffer.append(",\"status\":");
			buffer.append("\"" + p.getStatus() + "\"");
			buffer.append(",\"indate\":");
			buffer.append("\"" + p.getIndate() + "\"");
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
