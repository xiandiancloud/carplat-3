package com.dhl.web;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhl.domain.Led;
import com.dhl.service.LedService;

/**
 * 
 * @see
 * @since
 */
@Controller
public class LedController {
	/**
	 * 自动注入
	 */
	@Autowired
	private LedService ledService;

	/**
	 * 取得所有车辆信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getLed")
	public void getLed(HttpServletRequest request,HttpServletResponse response,int pos) {
		try {			
			List<Led> list = ledService.getLedList(pos);
			PrintWriter out = response.getWriter();
			String str = javatojson(list);
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/updateLed")
	public void updateLed(HttpServletRequest request,HttpServletResponse response, int id,String con) {
		try {			
			Led led = ledService.getLed(id);
			led.setContext(con);
			led.setModify(1);
			ledService.update(led);
			PrintWriter out = response.getWriter();
			String str = "sucess";
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
	private String javatojson(List<Led> list) {
		StringBuffer buffer = new StringBuffer();
		int count = list.size();
		buffer.append("{\"total\":" + count + ",\"rows\":[");
//		List<Car> data = list.getResult();
//		Iterator<Car> it = data.iterator();
//		while (it.hasNext())
		for (int i=0;i<count;i++)
		{
			Led p = list.get(i);
			String con = p.getContext();
			if (con != null)
			{
				con = con.trim();
			}
//			if (con != null && !"".equals(con))
			{
				buffer.append("{");
				buffer.append("\"id\":");
				buffer.append("\"" + p.getId() + "\"");
	//			buffer.append(",\"con\":");
	//			buffer.append("\"" + p.getContext() + "\"");
				buffer.append(",\"con\":");
				buffer.append("'" + con + "'");
				buffer.append("},");
			}
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
