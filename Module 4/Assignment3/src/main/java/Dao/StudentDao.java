package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Connectivity.DBConnectivity;
import model.Student;

public class StudentDao {
	public static void insertStudent(Student s)
	{
		try
		{
			Connection conn=DBConnectivity.createConnection();
			String sql="insert into students(fname,lname,email,mobile,gender,password) values(?,?,?,?,?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, s.getFname());
			pst.setString(2, s.getLname());
			pst.setString(3, s.getEmail());
			pst.setLong(4, s.getMobile());
			pst.setString(5, s.getGender());
			pst.setString(6, s.getPassword());
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
			String sql="select * from students where email=?";
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
	public static Student login(String email,String password)
	{
		Student s1=null;
		try
		{
			Connection conn=DBConnectivity.createConnection();
			String sql="select * from students where email=? and password=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				s1=new Student();
				s1.setId(rs.getInt("id"));
				s1.setFname(rs.getString("fname"));
				s1.setLname(rs.getString("lname"));
				s1.setMobile(rs.getLong("mobile"));
				s1.setGender(rs.getString("gender"));
				s1.setPassword(rs.getString("password"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return s1;
	}
	public static List<Student> listAllStudents()
	{
		List<Student> list=new ArrayList<Student>();
		try
		{
			Connection conn=DBConnectivity.createConnection();
			String sql="select * from students";
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				Student s=new Student();
				s.setId(rs.getInt("id"));
				s.setFname(rs.getString("fname"));
				s.setLname(rs.getString("lname"));
				s.setEmail(rs.getString("email"));
				s.setMobile(rs.getLong("mobile"));
				s.setGender(rs.getString("gender"));
				list.add(s);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	public static Student getStudentById(int id)
	{
		Student s1=null;
		try
		{
			Connection conn=DBConnectivity.createConnection();
			String sql="select * from students where id=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				s1=new Student();
				s1.setId(rs.getInt("id"));
				s1.setFname(rs.getString("fname"));
				s1.setLname(rs.getString("lname"));
				s1.setEmail(rs.getString("email"));
				s1.setMobile(rs.getLong("mobile"));
				s1.setGender(rs.getString("gender"));
				s1.setPassword(rs.getString("password"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return s1;
	}
	public static void updateRecord(Student s)
	{
		try
		{
			Connection conn=DBConnectivity.createConnection();
			String sql="update students set fname=?,lname=?,email=?,mobile=?,gender=?,password=? where id=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, s.getFname());
			pst.setString(2, s.getLname());
			pst.setString(3, s.getEmail());
			pst.setLong(4, s.getMobile());
			pst.setString(5, s.getGender());
			pst.setString(6, s.getPassword());
			pst.setInt(7, s.getId());
			pst.executeUpdate();
			System.out.println("Data Updated");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void insertRecord(Student s)
	{
		try
		{
			Connection conn=DBConnectivity.createConnection();
			String sql="insert into deleted_data(fname,lname,email,mobile,gender,password) values(?,?,?,?,?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, s.getFname());
			pst.setString(2, s.getLname());
			pst.setString(3, s.getEmail());
			pst.setLong(4,s.getMobile());
			pst.setString(5, s.getGender());
			pst.setString(6,s.getPassword());
			pst.executeUpdate();
			System.out.println("Data inserted to other table");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void deleteRecord(int id)
	{
		try
		{
			Connection conn=DBConnectivity.createConnection();
			String sql="delete from students where id=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
			System.out.println("Data deleted");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
