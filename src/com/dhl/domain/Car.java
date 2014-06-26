package com.dhl.domain;

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
@Table(name = "t_name")
public class Car extends BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	// 编号
	// private String code;
	// 车牌
	private String card;
	// 车型
	// private String cartype;
	// 车主
	private String caruser;
	// 类型
	// private String tpye;
	// 车位
	private String carposition;
	// 缴费类型
	// private String paytype;
	// 联系方式
	private String tel;
	// 有效时间
	private String indate;
	//接待地址
	private String address;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
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

	public String getCarposition() {
		return carposition;
	}

	public void setCarposition(String carposition) {
		this.carposition = carposition;
	}
}
