package com.yang.jdbctest.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yang.jdbctest.model.Goddess;
import com.yang.jdbctest.util.DBUtil;

public class GoddessDao {
	public void addGoddess(Goddess g) throws SQLException
	{
		Connection conn = DBUtil.getConnection();
		String sql = "insert into goddess"
				+ "(user_name,sex,age,birthday,email,mobile,create_user,create_date,update_user,"
				+ "update_date,isdel)"
				+ "values("
				+ "?,?,?,?,?,?,?,current_date(),?,current_date(),?"
				+ ")";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, g.getUser_name());
		ptmt.setInt(2, g.getSex());
		ptmt.setInt(3, g.getAge());
		ptmt.setDate(4, new Date(g.getBirthday().getTime()));
		ptmt.setString(5, g.getEmail());
		ptmt.setString(6, g.getMobile());
		ptmt.setString(7, g.getCreate_user());
		ptmt.setString(8, g.getUpdate_user());
		ptmt.setInt(9, g.getIsdel());
		ptmt.execute();
	}
	
	public void updateGoddess(Goddess g) throws SQLException
	{
		Connection conn = DBUtil.getConnection();
		String sql = "update goddess"
				+ " set user_name=?,sex=?,age=?,birthday=?,email=?,mobile=?,"
				+"update_user=?,update_date=current_date(),isdel=? "
				+ "where id=?;";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, g.getUser_name());
		ptmt.setInt(2, g.getSex());
		ptmt.setInt(3, g.getAge());
		ptmt.setDate(4, new Date(g.getBirthday().getTime()));
		ptmt.setString(5, g.getEmail());
		ptmt.setString(6, g.getMobile());
		ptmt.setString(7, g.getUpdate_user());
		ptmt.setInt(8, g.getIsdel());
		ptmt.setInt(9, g.getId());
		ptmt.execute();
	}
	
	public void delGoddess(Integer id) throws Exception
	{
		Connection conn = DBUtil.getConnection();
		String sql = "delete from goddess where id=?;";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ptmt.execute();
	}
	
	public List<Goddess>query(String name,String phoneNum) throws SQLException
	{
		List<Goddess>result = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from goddess ");
		sb.append("where user_name like ? and mobile like ? ");
		PreparedStatement ptmtp = conn.prepareStatement(sb.toString());
		ptmtp.setString(1, "%" + name + "%");
		ptmtp.setString(2, "%" + phoneNum + "%");
		Goddess g = null;
		ResultSet rs = ptmtp.executeQuery();
		while(rs.next())
		{
			g = new Goddess();
			g.setId(rs.getInt("id"));
			g.setUser_name(rs.getString("user_name"));
			g.setAge(rs.getInt("age"));
			g.setBirthday(rs.getDate("birthday"));
			g.setEmail(rs.getString("email"));
			g.setMobile(rs.getString("mobile"));
			g.setCreate_user(rs.getString("create_user"));
			g.setUpdate_user(rs.getString("update_user"));
			g.setUpdate_date(rs.getDate("update_date"));
			g.setCreate_date(rs.getDate("create_date"));
			
			result.add(g);
		}
		return result;
	}
	
	public List<Goddess>query(List<Map<String,Object>> params) throws SQLException
	{
		List<Goddess>result = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from goddess where 1=1 ");
		if(params!=null&&params.size() > 0)
		{
			for(int i=0;i < params.size();i++)
			{
				Map<String,Object>map = params.get(i);
				sb.append("and " + map.get("name") + " "+map.get("rela") + " " +map.get("value"));
			}
		}
		PreparedStatement ptmtp = conn.prepareStatement(sb.toString());
		Goddess g = null;
		ResultSet rs = ptmtp.executeQuery();
		while(rs.next())
		{
			g = new Goddess();
			g.setId(rs.getInt("id"));
			g.setUser_name(rs.getString("user_name"));
			g.setAge(rs.getInt("age"));
			g.setBirthday(rs.getDate("birthday"));
			g.setEmail(rs.getString("email"));
			g.setMobile(rs.getString("mobile"));
			g.setCreate_user(rs.getString("create_user"));
			g.setUpdate_user(rs.getString("update_user"));
			g.setUpdate_date(rs.getDate("update_date"));
			g.setCreate_date(rs.getDate("create_date"));
			
			result.add(g);
		}
		return result;
	}
	
	public Goddess get(Integer id) throws Exception
	{
		Goddess g = null;
		Connection conn = DBUtil.getConnection();
		String sql = "select * from goddess where id = ?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ResultSet rs = ptmt.executeQuery();
		while(rs.next())
		{
			g = new Goddess();
			g.setId(rs.getInt("id"));
			g.setUser_name(rs.getString("user_name"));
			g.setAge(rs.getInt("age"));
			g.setBirthday(rs.getDate("birthday"));
			g.setEmail(rs.getString("email"));
			g.setMobile(rs.getString("mobile"));
			g.setCreate_user(rs.getString("create_user"));
			g.setUpdate_user(rs.getString("update_user"));
			g.setUpdate_date(rs.getDate("update_date"));
			g.setCreate_date(rs.getDate("create_date"));
		}
		return g;
	}
}
