package com.dhl.web;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhl.domain.Park;
import com.dhl.service.ParkService;

/**
 * 
 * @see
 * @since
 */
@Controller
public class ParkController {
	/**
	 * 自动注入
	 */
	@Autowired
	private ParkService parkService;

	/**
	 * 取得所有车辆信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getAllPark")
	public void getAllCar(HttpServletRequest request,HttpServletResponse response) {
		try {			
			List<Park> list = parkService.getAllList();
			PrintWriter out = response.getWriter();
			String str = javatojson(list);
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
	private String javatojson(List<Park> list) {
		StringBuffer buffer = new StringBuffer();
		int count = list.size();
		buffer.append("{\"total\":" + count + ",\"rows\":[");
//		List<Car> data = list.getResult();
//		Iterator<Car> it = data.iterator();
//		while (it.hasNext())
		for (int i=0;i<count;i++)
		{
			Park p = list.get(i);
			buffer.append("{");
			buffer.append("\"id\":");
			buffer.append("\"" + p.getId() + "\"");
			buffer.append(",\"allcount\":");
			buffer.append("\"" + p.getAllCount() + "\"");
			buffer.append(",\"left\":");
			buffer.append("\"" + p.getLeftCount() + "\"");
			buffer.append(",\"pos\":");
			buffer.append("\"" + p.getPos() + "\"");
			buffer.append(",\"info\":");
			buffer.append("'" + p.getInfo() + "'");
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
