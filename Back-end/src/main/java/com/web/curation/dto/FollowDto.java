package com.web.curation.dto;

public class FollowDto {
	private String u_email;
	private int s_idx;
	
	public String getU_email() {
		return u_email;
	}
	public void setU_email(String u_email) {
		this.u_email = u_email;
	}
	public int getS_idx() {
		return s_idx;
	}
	public void setS_idx(int s_idx) {
		this.s_idx = s_idx;
	}
	@Override
	public String toString() {
		return "FollowDto [u_email=" + u_email + ", s_idx=" + s_idx + "]";
	}
	
}
