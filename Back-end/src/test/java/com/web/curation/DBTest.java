package com.web.curation;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;

public class DBTest {

	@Test
	public void test() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver"); // 마리아DB
		// Class.forName("com.mysql.jdbc.Driver"); MySQL

		Connection con = DriverManager.getConnection("jdbc:mariadb://i3b202.p.ssafy.io:3306/ssafy_trot", "trot", "trot");// 마리아DB
		System.out.println(con);
	}
}
