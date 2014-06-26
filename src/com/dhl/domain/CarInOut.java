package com.dhl.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 车辆管理 表
 * 
 * @author dong
 * 
 */
@Entity
@Table(name = "t_log")
public class CarInOut extends BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	// 车牌
	private String card;
	// 进出状态
	private String status;
	// 时间
	private Date indate;
	// 位置
	private String pos;

	//接待人，接待地址
	private String serveuser;
	private String serveaddress;
	
	public String getServeuser() {
		return serveuser;
	}
	public void setServeuser(String serveuser) {
		this.serveuser = serveuser;
	}
	public String getServeaddress() {
		return serveaddress;
	}
	public void setServeaddress(String serveaddress) {
		this.serveaddress = serveaddress;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public Date getIndate() {
		return indate;
	}
	public void setIndate(Date indate) {
		this.indate = indate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
