package com.paramAnnotation.beans;

import javax.ws.rs.MatrixParam;
import javax.ws.rs.QueryParam;

public class ParamBeans {

	
	private @QueryParam("year") int year;
	private @QueryParam("start") int start; 
	private @QueryParam("size") int size;
	private @MatrixParam("year") int yy;
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getYy() {
		return yy;
	}
	public void setYy(int yy) {
		this.yy = yy;
	}
}
