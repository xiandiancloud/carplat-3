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
@Table(name = "carinoutview")
public class CarInOutView extends BaseDomain {

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
	
	private Integer code;
	private String carposition;
	private String caruser;
	private String tel;
	//有效时间
	private String alldate;
	
	public Integer getCode() {		
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getAlldate() {
		return alldate;
	}
	public void setAlldate(String alldate) {
		this.alldate = alldate;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
//	public String getCode() {
//		return code;
//	}
//	public void setCode(String code) {
//		this.code = code;
//	}
	public String getCarposition() {
		return carposition;
	}
	public void setCarposition(String carposition) {
		this.carposition = carposition;
	}
	public String getCaruser() {
		return caruser;
	}
	public void setCaruser(String caruser) {
		this.caruser = caruser;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
