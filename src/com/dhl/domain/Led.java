package com.dhl.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 显示屏管理 表
 * 
 * @author dong
 * 
 */
@Entity
@Table(name = "t_ledScreen")
public class Led extends BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	private int pos;
	private int line;
	private String context;
	private int Modify;
	private int isLoopShow;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public int getModify() {
		return Modify;
	}
	public void setModify(int modify) {
		Modify = modify;
	}
	public int getIsLoopShow() {
		return isLoopShow;
	}
	public void setIsLoopShow(int isLoopShow) {
		this.isLoopShow = isLoopShow;
	}
}
