package com.dhl.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dhl.domain.Car;
import com.dhl.domain.TmpCar;
import com.dhl.service.CarService;
import com.dhl.service.TmpCarService;

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
	@Autowired
	private TmpCarService tmpcaseService;
	
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
				if (strs.length < 3)
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
	
	@RequestMapping("/tmpupload")
	public void tmpupload(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="qqfile", required=true) MultipartFile file)
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
				if (strs.length < 3)
				{
					out.print("{\"success\": \"false\"}");
					return;
				}
				if (strs != null && strs[0] != null && strs[0].substring(0,1).equals("#"))
				{
					continue;
				}
				tmpcaseService.save(strs);
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
	/**
	 * 导出车辆信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/importcar")
	public void importcar(HttpServletRequest request,HttpServletResponse response) {
		try {
//			PrintWriter pout = response.getWriter();
			
			List<Car> list = caseService.getAllCar();
			
//			 List<String> dataList=new ArrayList<String>();
		        
			 String path = request.getSession().getServletContext().getRealPath("/export/");
			 File file = new File(path+"/人员信息.csv");
			 if (!file.exists())
			 {
				 file.createNewFile();
			 }
//				FileOutputStream fos = new FileOutputStream(upath+path);
//				fos.write(bytes); 
//				fos.close();
			 
			 FileOutputStream out=null;
		        OutputStreamWriter osw=null;
		        BufferedWriter bw=null;
		        try {
		            out = new FileOutputStream(file);
		            osw = new OutputStreamWriter(out);
		            bw =new BufferedWriter(osw);
		            bw.append("#").append("车牌,姓名,联系方式").append("\r");
		            if(list!=null && !list.isEmpty()){
		            	
		                for(Car car : list){
		                	bw.append(car.getCard()).append(",").append(car.getCaruser()).append(",").append(car.getTel()).append("\r");
		                }
		            }
		        } catch (Exception e) {
		        }finally{
		            if(bw!=null){
		                try {
		                    bw.close();
		                    bw=null;
		                } catch (IOException e) {
		                    e.printStackTrace();
		                } 
		            }
		            if(osw!=null){
		                try {
		                    osw.close();
		                    osw=null;
		                } catch (IOException e) {
		                    e.printStackTrace();
		                } 
		            }
		            if(out!=null){
		                try {
		                    out.close();
		                    out=null;
		                } catch (IOException e) {
		                    e.printStackTrace();
		                } 
		            }
		        }
		        

	            // 以流的形式下载文件。
	            InputStream fis = new BufferedInputStream(new FileInputStream(file));
	            byte[] buffer = new byte[fis.available()];
	            fis.read(buffer);
	            fis.close();
	            // 清空response
	            response.reset();
	            // 设置response的Header
	            response.addHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes("utf-8"),"ISO-8859-1"));
	            response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	            response.setContentType("application/octet-stream");
	            toClient.write(buffer);
	            toClient.flush();
	            toClient.close();
	            
//			pout.print("{\"success\": \"true\"}");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出临时车辆信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/tmpimportcar")
	public void tmpimportcar(HttpServletRequest request,HttpServletResponse response) {
		try {
//			PrintWriter pout = response.getWriter();
			
			List<TmpCar> list = tmpcaseService.getAllCar();
			
//			 List<String> dataList=new ArrayList<String>();
		        
			 String path = request.getSession().getServletContext().getRealPath("/export/");
			 File file = new File(path+"/临时人员信息.csv");
			 if (!file.exists())
			 {
				 file.createNewFile();
			 }
//				FileOutputStream fos = new FileOutputStream(upath+path);
//				fos.write(bytes); 
//				fos.close();
			 
			 FileOutputStream out=null;
		        OutputStreamWriter osw=null;
		        BufferedWriter bw=null;
		        try {
		            out = new FileOutputStream(file);
		            osw = new OutputStreamWriter(out);
		            bw =new BufferedWriter(osw);
		            bw.append("#").append("车牌,姓名,联系方式").append("\r");
		            if(list!=null && !list.isEmpty()){
		            	
		                for(TmpCar car : list){
		                	bw.append(car.getCard()).append(",").append(car.getCaruser()).append(",").append(car.getTel()).append("\r");
		                }
		            }
		        } catch (Exception e) {
		        }finally{
		            if(bw!=null){
		                try {
		                    bw.close();
		                    bw=null;
		                } catch (IOException e) {
		                    e.printStackTrace();
		                } 
		            }
		            if(osw!=null){
		                try {
		                    osw.close();
		                    osw=null;
		                } catch (IOException e) {
		                    e.printStackTrace();
		                } 
		            }
		            if(out!=null){
		                try {
		                    out.close();
		                    out=null;
		                } catch (IOException e) {
		                    e.printStackTrace();
		                } 
		            }
		        }
		        

	            // 以流的形式下载文件。
	            InputStream fis = new BufferedInputStream(new FileInputStream(file));
	            byte[] buffer = new byte[fis.available()];
	            fis.read(buffer);
	            fis.close();
	            // 清空response
	            response.reset();
	            // 设置response的Header
	            response.addHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes("utf-8"),"ISO-8859-1"));
	            response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	            response.setContentType("application/octet-stream");
	            toClient.write(buffer);
	            toClient.flush();
	            toClient.close();
	            
//			pout.print("{\"success\": \"true\"}");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
