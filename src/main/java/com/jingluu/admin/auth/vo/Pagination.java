package com.jingluu.admin.auth.vo;

import java.io.Serializable;
import java.util.List;

public class Pagination<T> implements Serializable {

	private static final long serialVersionUID = 5907233681592564300L;
	
	public static int PAGE_SIZE_DEFAULT = 10;

	private int pageNo = 1; //当前页
	private int pageCount; //总页数
	private int pageSize = PAGE_SIZE_DEFAULT; //每页显示记录数
    private int total = 0; //总记录数
    private List<T> items; //分页数据
    
    public Pagination(){ 
    }
    
    public Pagination(int pageNo,int total){
    	this(pageNo,total,PAGE_SIZE_DEFAULT,null);
    }

	public Pagination(int pageNo,int pageSize,int total){
		this(pageNo,total,pageSize,null);
	}
    
    public Pagination(int pageNo,int total,List<T> items){
    	this(pageNo,total,PAGE_SIZE_DEFAULT,items);
    }
    
    public Pagination(int pageNo,int total,int pageSize,List<T> items){
    	this.items = items;
    	this.total = total;
    	this.pageSize = pageSize;
    	this.pageNo = pageNo;
    	this.pageCount = (total - 1) / this.pageSize + 1;
    }

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageCount() {
		return pageCount;
	}
	
	public void setOffset(int offset) {
		 
	}
	
	public int getOffset() {
		return (this.pageNo - 1) * this.pageSize;
	}


	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}
	
	public static void main(String[] args) {
		System.out.println((8 - 1) / 10 + 1);
	}

	
    
}
