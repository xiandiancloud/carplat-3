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
import com.dhl.domain.CarInOutView;
import com.dhl.service.CarInOutViewService;
import com.dhl.util.UtilTools;

/**
 * 
 * @see
 * @since
 */
@Controller
public class CarInOutViewController {
	/**
	 * 自动注入
	 */
	@Autowired
	private CarInOutViewService carInOutService;

	/**
	 * 取得所有车辆进出信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/carInOut")
	public void carInOut(HttpServletRequest request,HttpServletResponse response,int pindex, String indate,int status,String outdate) {
		try {
//			Date date = UtilTools.StringToDate(indate);
			Page list = carInOutService.getAllCarInOut(pindex, CommonConstant.PAGE_SIZE,indate,status,outdate);
			PrintWriter out = response.getWriter();
			String str = javatojson(list,status);
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 出入统计
	 * @param request
	 * @param response
	 * @param pindex
	 * @param indate
	 * @param status
	 */
	@RequestMapping("/carInOutCount")
	public void carInOutCount(HttpServletRequest request,HttpServletResponse response,String indate,String outdate) {
		try {
//			Date date = UtilTools.StringToDate(indate);
			List<String[]> list = carInOutService.getCountList(indate,outdate);
			PrintWriter out = response.getWriter();
			String str = javatojson(list);
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String javatojson(List<String[]> list) {
		StringBuffer buffer = new StringBuffer();
		int count = list.size();
		buffer.append("{\"total\":" + count + ",\"rows\":[");
	
		for (int i=0;i<count;i++)
		{
			String[] p = list.get(i);
			buffer.append("{");
			buffer.append("\"count1\":");
			buffer.append("\"" + p[0] + "\"");
			buffer.append(",\"count2\":");
			buffer.append("\"" + p[1] + "\"");
			buffer.append(",\"count3\":");
			buffer.append("\"" + p[2] + "\"");
			buffer.append(",\"count4\":");
			buffer.append("\"" + p[3] + "\"");
			buffer.append(",\"count5\":");
			buffer.append("\"" + p[4] + "\"");
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
	
	/**
	 * list中有set，没有找到合适的转json方法，先拼接个用着
	 * 
	 * @param list
	 * @return
	 */
	private String javatojson(Page list,int status) {
		StringBuffer buffer = new StringBuffer();
		long count = list.getTotalCount();
		buffer.append("{\"total\":" + count + ",\"rows\":[");
		List<CarInOutView> data = list.getResult();
		Iterator<CarInOutView> it = data.iterator();
		//上班时间
		String time = UtilTools.getConfig().getProperty("TIME");
		String leavetime = UtilTools.getConfig().getProperty("TIME");
		while (it.hasNext()) {
			CarInOutView p = it.next();
			buffer.append("{");
			buffer.append("\"id\":");
			buffer.append("\"" + p.getId() + "\"");
			buffer.append(",\"card\":");
			buffer.append("\"" + p.getCard().trim() + "\"");
			buffer.append(",\"status\":");
			buffer.append("\"" + p.getStatus() + "\"");
			buffer.append(",\"indate\":");
			String indate = UtilTools.timeTostrall(p.getIndate());
			buffer.append("\"" +indate+ "\"");
			buffer.append(",\"code\":");
			//buffer.append("\"" + p.getCode() + "\"");
			String alldate = p.getAlldate();
			Integer code = p.getCode();
			if (alldate != null)
			{
				buffer.append("\"" + UtilTools.getNumber("F", code==null?0:code) + "\"");			
			}
			else
			{
				buffer.append("\"" + UtilTools.getNumber("C", code==null?0:code) + "\"");
			}
			buffer.append(",\"caruser\":");
			buffer.append("\"" + p.getCaruser() + "\"");
			buffer.append(",\"tel\":");
			buffer.append("\"" + p.getTel() + "\"");
			buffer.append(",\"time\":");
			String strtime = "正常";
			String hms = UtilTools.strtohms(indate);
			if (status == 0)
			{
				int result = UtilTools.compiletime(hms,time);
				strtime = result < 1 ? "正常" : "迟到";
			}
			else
			{
				int result = UtilTools.compiletime(hms,leavetime);
				strtime = result < 1 ? "正常" : "早退";
			}
			buffer.append("\"" +strtime+ "\"");
			buffer.append(",\"alldate\":");
			String tmp = p.getAlldate() == null ? "固定":"临时";
			buffer.append("\"" +tmp+ "\"");
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
