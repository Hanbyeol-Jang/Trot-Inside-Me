package com.web.curation.dto;

public class BroadCastingDto {
	private int bc_idx;
	private String bc_company;
	private String bc_date;
	private String bc_time;
	private String bc_member;
	private String bc_title;
	
	public int getBc_idx() {
		return bc_idx;
	}
	public void setBc_idx(int bc_idx) {
		this.bc_idx = bc_idx;
	}
	public String getBc_company() {
		return bc_company;
	}
	public void setBc_company(String bc_company) {
		this.bc_company = bc_company;
	}
	public String getBc_date() {
		return bc_date;
	}
	public void setBc_date(String bc_date) {
		this.bc_date = bc_date;
	}
	public String getBc_time() {
		return bc_time;
	}
	public void setBc_time(String bc_time) {
		this.bc_time = bc_time;
	}
	public String getBc_member() {
		return bc_member;
	}
	public void setBc_member(String bc_member) {
		this.bc_member = bc_member;
	}
	public String getBc_title() {
		return bc_title;
	}
	public void setBc_title(String bc_title) {
		this.bc_title = bc_title;
	}
	@Override
	public String toString() {
		return "BroadCastingDto [bc_idx=" + bc_idx + ", bc_company=" + bc_company + ", bc_date=" + bc_date
				+ ", bc_time=" + bc_time + ", bc_member=" + bc_member + ", bc_title=" + bc_title + "]";
	}
	
	
}
