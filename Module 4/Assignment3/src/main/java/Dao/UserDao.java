package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Connectivity.DBConnectivity;
import model.Message;
import model.User;

public class UserDao {
	public static void insertUser(User u)
	{
		try
		{
			Connection conn=DBConnectivity.createConnection();
			String sql="insert into user(fname,lname,email,mobile,password) values(?,?,?,?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, u.getFname());
			pst.setString(2, u.getLname());
			pst.setString(3, u.getEmail());
			pst.setLong(4, u.getMobile());
			pst.setString(5, u.getPassword());
			pst.executeUpdate();
			System.out.println("Data inserted");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static boolean checkEmail(String email)
	{
		boolean flag=false;
		try
		{
			Connection conn=DBConnectivity.createConnection();
			String sql="select * from user where email=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, email);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				flag=true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}
	public static User loginUser(String email,String password)
	{
		User u1=null;
		try
		{
			Connection conn=DBConnectivity.createConnection();
			String sql="select * from user where email=? and password=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				u1=new User();
				u1.setId(rs.getInt("uid"));
				u1.setFname(rs.getString("fname"));
				u1.setLname(rs.getString("lname"));
				u1.setEmail(rs.getString("email"));
				u1.setMobile(rs.getLong("mobile"));
				u1.setPassword(rs.getString("password"));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return u1;
	}
	public static void insertMessage(Message m)
	{
		try
		{
			Connection conn=DBConnectivity.createConnection();
			String sql="insert into message(u_id,from_email,to_email,msg) values(?,?,?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, m.getUid());
			pst.setString(2, m.getFrom());
			pst.setString(3, m.getTo());
			pst.setString(4, m.getMsg());
			pst.executeUpdate();
			System.out.println("Data inserted");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void updateUser(User u)
	{
		try
		{
			Connection conn=DBConnectivity.createConnection();
			String sql="update user set fname=?,lname=?,email=?,mobile=? where uid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, u.getFname());
			pst.setString(2, u.getLname());
			pst.setString(3, u.getEmail());
			pst.setLong(4, u.getMobile());
			pst.setInt(5, u.getId());
			pst.executeUpdate();
			System.out.println("Data Updated");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static List<Message> getAllMessageByEmail(String email)
	{
		System.out.println(email);
		List<Message> l1=new ArrayList<Message>();
		try
		{
			Connection conn=DBConnectivity.createConnection();
			String sql="select * from message where to_email=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, email);
			ResultSet rs=pst.executeQuery();
			while(rs.next());
			{
				Message m1=new Message();
				m1.setUid(rs.getInt("u_id"));
				m1.setFrom(rs.getString("from_email"));
				m1.setTo(rs.getString("to_email"));
				m1.setMsg(rs.getString("msg"));
				l1.add(m1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return l1;
	}
	
}
