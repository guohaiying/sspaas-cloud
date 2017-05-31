package com.sspaas.sspaascloud.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageCrt {

	Integer page; //当前页
	Integer rows; // 每页显示条数
	Integer records; // 数据的总条数
	List<?> list; // 实际数据
	Integer index;
	Integer total; //共分为多少页
	String sidx;//排序字段
	String sord;//排序规则
	Map<String, Object> map = new HashMap<String, Object>();
	@Override
	public String toString() {
		return "PageCrt [page=" + page + ", rows=" + rows + ", records=" + records + ", list=" + list + ", index="
				+ index + ", total=" + total + ", sidx=" + sidx + ", sord=" + sord + ", map=" + map + "]";
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord == null ? "desc": sord;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public Integer getRecords() {
		return records;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public void setRecords(Integer records) {
		this.index =(page - 1) * rows;
		this.records = records;
		this.total = records % rows == 0 ? records / rows : records / rows + 1;
	}
	
}
