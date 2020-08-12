package com.web.curation.dto;

import lombok.Data;

@Data
public class Pagenation {
	private int idx;
	private int type;
	private int page;
	private int lastpage;
}
