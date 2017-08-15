package com.zzb.cm.entity;

import com.cninsure.core.dao.domain.BaseEntity;
import com.cninsure.core.dao.domain.Identifiable;


public class INSBLegalrightclaim extends BaseEntity implements Identifiable {
	private static final long serialVersionUID = 1L;

	/**
	 * 任务id
	 */
	private String taskid;

	/**
	 * 人员表id
	 */
	private String personid;
	
	/**
	 * 是否法定
	 */
	private String isLegal;
	
	/**
	 * 受益顺序
	 */
	private Integer orderNum;
	
	/**
	 * 受益比例
	 */
	private Double ratio;
	
	/**
	 * 与车主关系
	 */
	private String relation;

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getIsLegal() {
		return isLegal;
	}

	public void setIsLegal(String isLegal) {
		this.isLegal = isLegal;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

}