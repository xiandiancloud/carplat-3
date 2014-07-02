package com.dhl.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dhl.service.CarService;

/**
 * upload
 * 
 * @see
 * @since
 */
@Controller
public class UploadController {

	/**
	 * 自动注入
	 */
	@Autowired
	private CarService caseService;
	
	@RequestMapping("/upload")
	public void upload(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="qqfile", required=true) MultipartFile file)
	{
		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			out.print("{\"success\": \"false\"}");
		}
		try
		{
			if (!file.isEmpty()) {
//				byte[] bytes = file.getBytes();
//				String upath = request.getSession().getServletContext().getRealPath("/");
//				String path =  "upload/" + file.getOriginalFilename();
//				FileOutputStream fos = new FileOutputStream(upath+path);
//				fos.write(bytes); 
//				fos.close();
//				
//				File csv = new File("D:\\writers.csv"); // CSV文件
//				InputStreamReader is = new InputStreamReader(null,"UTF-8"); 
				
				BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(),"GBK"));

				// 读取直到最后一行 
				String line = ""; 
				while ((line = br.readLine()) != null) { 
				// 把一行数据分割成多个字段 
//				StringTokenizer st = new StringTokenizer(line, ",");
				String[] strs = line.split(",");
				System.out.println("---------- "+strs.length);
				if (strs.length < 4)
				{
					out.print("{\"success\": \"false\"}");
					return;
				}
				if (strs != null && strs[0] != null && strs[0].substring(0,1).equals("#"))
				{
					continue;
				}
				caseService.save(strs);
//				while (st.hasMoreTokens()) { 
//				// 每一行的多个字段用TAB隔开表示 
//				System.out.print(st.nextToken() + "\t"); 
//				} 
//				System.out.println(); 
				} 
				br.close();
				
				out.print("{\"success\": \"true\"}");
//				out.write("<script>parent.callback('sucess')</script>");
			}
			else
			{
				out.print("{\"success\": \"false\"}");
			}
			
		}
		catch(Exception e)
		{
			out.print("{\"success\": \"false\"}");
		}
	}
	
}
