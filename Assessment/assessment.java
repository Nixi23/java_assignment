import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
class DBConn
{
	public static Connection createConnection()
	{
		Connection conn=null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "2384");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}
}
class swing_eg extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,l4,l5;
	JTextField t1,t2,t3,t4,t5;
	JButton b1,b2,b3,b4;
	swing_eg()
	{
		setTitle("My Swing Example");
		setSize(400,400);
		setVisible(true);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		l1=new JLabel("Id");
		l1.setBounds(20,20,50,20);
		l2=new JLabel("First Name");
		l2.setBounds(20,50,70,20);
		l3=new JLabel("Last Name");
		l3.setBounds(20,80,70,20);
		l4=new JLabel("Email");
		l4.setBounds(20,110,70,20);
		l5=new JLabel("Mobile");
		l5.setBounds(20,140,70,20);
		t1=new JTextField(20);
		t1.setBounds(90,20,120,20);
		t2=new JTextField(20);
		t2.setBounds(90,50,120,20);
		t3=new JTextField(20);
		t3.setBounds(90,80,120,20);
		t4=new JTextField(20);
		t4.setBounds(90,110,120,20);
		t5=new JTextField(20);
		t5.setBounds(90,140,120,20);
		b1=new JButton("Insert");
		b1.setBounds(20,170,90,20);
		b2=new JButton("Search");
		b2.setBounds(120,170,90,20);
		b3=new JButton("Update");
		b3.setBounds(20,200,90,20);
		b4=new JButton("Delete");
		b4.setBounds(120,200,90,20);
		add(l1);
		add(t1);
		add(l2);
		add(t2);
		add(l3);
		add(t3);
		add(l4);
		add(t4);
		add(l5);
		add(t5);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			String fname,lname,email;
			Long mob;
			fname=t2.getText();
			lname=t3.getText();
			email=t4.getText();
			mob=Long.parseLong(t5.getText());
			System.out.println("Name: "+fname+" "+lname);
			System.out.println("Email: "+email);
			System.out.println("Mobile: "+mob);
			System.out.println("Insert button pressed");
			try
			{
			
				Connection con=DBConn.createConnection();
				String sql="insert into stu(fname,lname,email,mobile) values(?,?,?,?)";
				PreparedStatement pst=con.prepareStatement(sql);
				pst.setString(1,fname);
				pst.setString(2,lname);
				pst.setString(3,email);
				pst.setLong(4,mob);
				pst.executeUpdate();
				System.out.println("Data inserted");
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");	
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		else if(e.getSource()==b2)
		{
			int id;
			id=Integer.parseInt(t1.getText());
			System.out.println("Id: "+id);
			System.out.println("Search button pressed");
			try
			{
				Connection con=DBConn.createConnection();
				String sql="select * from stu where id=?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1,id);
				ResultSet rs=pst.executeQuery();
				if(rs.next())
				{
					int id1=rs.getInt("id");
					String name=rs.getString("fname");
					String l_name=rs.getString("lname");
					String email=rs.getString("email");
					long contact=rs.getLong("mobile");
					System.out.println("Id :"+id1);
					System.out.println("Fname :"+name);
					System.out.println("Lname :"+l_name);
					System.out.println("Email :"+email);
					System.out.println("Contact :"+contact);
				}
				else
				{
					System.out.println("Data not found");
					t1.setText("");
					t2.setText("");
					t3.setText("");
					t4.setText("");
					t5.setText("");	
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}

		}
		else if(e.getSource()==b3)
		{
			String fname,lname,email;
			Long mob;
			int id;
			id=Integer.parseInt(t1.getText());
			fname=t2.getText();
			lname=t3.getText();
			email=t4.getText();
			mob=Long.parseLong(t5.getText());
			System.out.println("Id : "+id);
			System.out.println("Name: "+fname+" "+lname);
			System.out.println("Email: "+email);
			System.out.println("Mobile: "+mob);
			System.out.println("Update button pressed");
			try
			{
				Connection con=DBConn.createConnection();
				String sql="update stu set fname=?,lname=?,email=?,mobile=? where id=?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1,fname);
				pst.setString(2,lname);
				pst.setString(3,email);
				pst.setLong(4,mob);
				pst.setInt(5,id);
				pst.executeUpdate();
				System.out.println("data updated");
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");	
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}

		}
		else if(e.getSource()==b4)
		{
			int id;
			id=Integer.parseInt(t1.getText());
			System.out.println("Id: "+id);
			System.out.println("Delete button pressed");
			try
			{
				Connection con=DBConn.createConnection();
				String sql="delete from stu where id=?";
				PreparedStatement pst=con.prepareStatement(sql);
				pst.setInt(1,id);
				System.out.println("Data deleted");
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");	
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
}
public class assessment
{
	public static void main(String[] args) {
		new swing_eg();
	}
}