package com.web.curation.dto;

public class BoardDto {
	private int b_idx;
	private int b_type;
	private int b_good;
	private int b_bookmard;
	private int a_idx;
	private int v_idx;
	
	public int getB_idx() {
		return b_idx;
	}
	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}
	public int getB_type() {
		return b_type;
	}
	public void setB_type(int b_type) {
		this.b_type = b_type;
	}
	public int getB_good() {
		return b_good;
	}
	public void setB_good(int b_good) {
		this.b_good = b_good;
	}
	public int getB_bookmard() {
		return b_bookmard;
	}
	public void setB_bookmard(int b_bookmard) {
		this.b_bookmard = b_bookmard;
	}
	public int getA_idx() {
		return a_idx;
	}
	public void setA_idx(int a_idx) {
		this.a_idx = a_idx;
	}
	public int getV_idx() {
		return v_idx;
	}
	public void setV_idx(int v_idx) {
		this.v_idx = v_idx;
	}
	@Override
	public String toString() {
		return "BoardDto [b_idx=" + b_idx + ", b_type=" + b_type + ", b_good=" + b_good + ", b_bookmard=" + b_bookmard
				+ ", a_idx=" + a_idx + ", v_idx=" + v_idx + "]";
	}
	
	
	
}
