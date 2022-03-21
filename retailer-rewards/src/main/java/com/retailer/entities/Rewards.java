package com.retailer.entities;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
/**
 * 
 * @author Janak Patel
 */
@Data
@Entity
public class Rewards {
	@Id
	private int id;
	private String customerId;
	private String customerName;
	private BigDecimal trnAmnt;
	private Date date;
	
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getCustomerId() {
//		return customerId;
//	}
//	public void setCustomerId(String customerId) {
//		this.customerId = customerId;
//	}
//	public String getCustomerName() {
//		return customerName;
//	}
//	public void setCustomerName(String customerName) {
//		this.customerName = customerName;
//	}
//	public BigDecimal getTrnAmnt() {
//		return trnAmnt;
//	}
//	public void setTrnAmnt(BigDecimal trnAmnt) {
//		this.trnAmnt = trnAmnt;
//	}
//	public Date getDate() {
//		return date;
//	}
//	public void setDate(Date date) {
//		this.date = date;
//	}
//	@Override
//	public String toString() {
//		return "Rewards [id=" + id + ", customerId=" + customerId + ", customerName=" + customerName + ", trnAmnt="
//				+ trnAmnt + ", date=" + date + "]";
//	}
	
}
