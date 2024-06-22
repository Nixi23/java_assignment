package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Connectivity.DBConnectivity;
import model.User;

public class UserDao {
	public static void insertUser(User u)
	{
		try
		{
			Connection conn=DBConnectivity.createConnection();
			String sql="insert into student(fname,lname,email,mobile,address,gender,password) values(?,?,?,?,?,?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, u.getFname());
			pst.setString(2, u.getLname());
			pst.setString(3, u.getEmail());
			pst.setLong(4, u.getContact());
			pst.setString(5,u.getAddress());
			pst.setString(6, u.getGender());
			pst.setString(7, u.getPassword());
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
			String sql="select * from student where email=?";
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
	public static User LoginUser(User u)
	{
		User u1=null;
		try
		{
			Connection conn=DBConnectivity.createConnection();
			String sql="select * from student where email=? and password=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,u.getEmail());
			pst.setString(2,u.getPassword());
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				u1=new User();
				u1.setId(rs.getInt("id"));
				u1.setFname(rs.getString("fname"));
				u1.setLname(rs.getString("lname"));
				u1.setEmail(rs.getString("email"));
				u1.setContact(rs.getLong("mobile"));
				u1.setAddress(rs.getString("address"));
				u1.setGender(rs.getString("gender"));
			}
			//System.out.println(u1);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return u1;
	}
	public static List<User> getallDetails(int id)
	{
		List<User> list=new ArrayList<User>();
		try
		{
			Connection conn=DBConnectivity.createConnection();
			String sql="select * from student where id=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				User u=new User();
				u.setId(rs.getInt("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setGender(rs.getString("gender"));
				u.setEmail(rs.getString("email"));
				u.setContact(rs.getLong("mobile"));
				u.setAddress(rs.getString("address"));
				list.add(u);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	public static void updatePassword(String pswd,String email)
	{
		try
		{
			Connection conn=DBConnectivity.createConnection();
			String sql="update student set password=? where email=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, pswd);
			pst.setString(2, email);
			pst.executeUpdate();
			System.out.println("Password changed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static boolean checkPassword(String op)
	{
		boolean flag=false;
		try
		{
			Connection conn=DBConnectivity.createConnection();
			String sql="select * from student where password=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, op);
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
}
