package com.yorijory.webprj.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yorijory.webprj.vo.Member;

public class MemberDao {
	
		public List<Member> getMembers() throws SQLException{
			return getMembers(1);
		
		}
		public List<Member> getMembers(int page) throws SQLException{
			List<Member> list = new ArrayList<Member>();
			
			int start = 1+(page-1)*10;
			int end = page*10;
			String sql = "SELECT *FROM ("
					+ "SELECT ROW_NUMBER() OVER (ORDER BY REGDATE DESC) NUM, *FROM YJMember)A "
					+ "WHERE NUM BETWEEN " +start+ " AND " +end; 

			//String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
			String url = "jdbc:sqlserver://211.238.142.251:1433;databaseName=yorijorydb;";
			Connection con = DriverManager.getConnection(url,"yojo","yjoriy1511");
			
			//Connection con = DriverManager.getConnection(url,"c##sist","dclass"); // 연결하고
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);// 실행하고
		

			Member member = null;
		
					
			while(rs.next()){
				member = new Member();
				
				member.setMid(rs.getString("mid"));
				member.setName(rs.getString("name"));
				
				list.add(member);
			}
			rs.close();
			st.close();
			con.close();
		
			return list;
		}
	
}
