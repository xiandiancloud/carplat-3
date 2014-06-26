package com.dhl.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class UtilTools {

	public static String COFING_FILE = "/config/dhl.properties";
	public static Properties p;

	public static Properties getConfig() {
		if (p == null) {
			p = new Properties();
			try {
				p.load(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(COFING_FILE));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return p;
	}

	/**
	 * 默认是utf-8编码
	 * 
	 * @param str
	 * @return
	 */
	public static String converStr(String str) {
		return converStr(str, "UTF-8");
	}

	/**
	 * 
	 * @param str
	 * @param encode
	 * @return
	 */
	public static String converStr(String str, String encode) {
		if (str == null || str.equals("null")) {
			return "";
		}
		try {
			byte[] tmpbyte = str.getBytes("ISO8859_1");
			if (encode != null) {
				// 如果指定编码方式
				str = new String(tmpbyte, encode);
			} else {
				// 用系统默认的编码
				str = new String(tmpbyte);
			}
			return str;
		} catch (Exception e) {
		}
		return str;
	}

	public static String encodingFileName(String fileName) {
		String returnFileName = "";
		try {
			returnFileName = URLEncoder.encode(fileName, "UTF-8");
			returnFileName = StringUtils.replace(returnFileName, "+", "%20");
			if (returnFileName.length() > 150) {
				returnFileName = new String(fileName.getBytes("GB2312"),
						"ISO8859-1");
				returnFileName = StringUtils
						.replace(returnFileName, " ", "%20");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();

		}
		return returnFileName;
	}

	public static String timeTostr(Date date) {
		String strDate = "";
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			strDate = format.format(date);
		}
		return strDate;
	}

	public static String timeTostrall(Date date) {
		String strDate = "";
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			strDate = format.format(date);
		}
		return strDate;
	}
	
	public static Date StringToDate(String dateStr) {
		SimpleDateFormat dd = new SimpleDateFormat("dd-MM-yyyy");
		Date date = null;
		try {
			date = dd.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}

	public static String replaceStr(String str) {
		str = str.replaceAll("<br>", "\r\n");
		// String s = "\n   iii \n 44\r4";
		// String reg ="[\n-\r]";
		// Pattern p = Pattern.compile(reg);
		// Matcher m = p.matcher(s);
		// String beizhu = m.replaceAll("");
		return str;
	}

	public static int compiletime(String s1, String s2) {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(s1));
			c2.setTime(df.parse(s2));
		} catch (Exception e) {
			System.err.println("格式不正确");
		}
		return c1.compareTo(c2);
	}

	public static String strtohms(String s1) {
		String strs[] = s1.split(" ");
		if (strs.length == 1)
		{
			return "00:00:00";
		}
		else
		{
			return strs[1];
		}
	}
	
	/** 
     * 获得指定日期的后一天 
     *  
     * @param specifiedDay 
     * @return 
     */  
    public static String getSpecifiedDayAfter(String specifiedDay) {  
        Calendar c = Calendar.getInstance();  
        Date date = null;  
        try {  
            date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        c.setTime(date);  
        int day = c.get(Calendar.DATE);  
        c.set(Calendar.DATE, day + 1);  
  
        String dayAfter = new SimpleDateFormat("yyyy-MM-dd")  
                .format(c.getTime());  
        return dayAfter;  
    }  
    
	public static boolean compileDate() {
		try {
			String myString = "2014-10-11";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
					Locale.CHINA);
			Date d = sdf.parse(myString);

			Date nowdate = new Date();
			boolean flag = d.before(nowdate);
			if (flag)
				return false;
			else
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String listToJson(List list) {
		JSONArray array = JSONArray.fromObject(list);
		return array.toString();
	}

	public static String listToJson(List list, JsonConfig config) {
		JSONArray array = JSONArray.fromObject(list, config);
		return array.toString();
	}

	/**
	 * 
	 */
	public static String toJson(Object object) {

		ObjectMapper mapper = new ObjectMapper();
		// 设置输出时包含属性的风格
		mapper.setSerializationInclusion(Inclusion.NON_EMPTY);
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		mapper.configure(
				DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 禁止使用int代表Enum的order()來反序列化Enum,非常危險
		mapper.configure(
				DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS, true);

		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 编号的方法
	 * @param num
	 * @return
	 */
	public static String getNumber(String s,int num)
	{
//		num++;
		String result = "";
		switch ((num+"").length()) {
		case 1:
			result = s+"000" + num;
			break;
		case 2:
			result = s+"00" + num;
			break;
		case 3:
			result = s+"0" + num;
			break;
		case 4:
			result = s + num;
			break;
		default:
//			result = "0000";
			result = s+num;
			break;
		}
		return result;
	}
}
