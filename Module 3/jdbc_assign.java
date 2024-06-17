	package dbms;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

	public class jdbc_assign {

		private JFrame frame;
		private 	JTextField t1,t2,t3,t4,t5;
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						jdbc_assign window = new jdbc_assign();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		
		public jdbc_assign() {
			initialize();
			connect();
			loadData();
		}

		Connection cn;
		public JTable table;
		private JTextField uid;
		public void connect()
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","2384");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void loadData()
		{
			
			try {
				PreparedStatement ps = cn.prepareStatement("select * from stu");
				ResultSet rs = ps.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		private void initialize() {
			frame = new JFrame();
			frame.setBounds(100, 100, 679, 426);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Student Registration");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel.setBounds(226, 11, 210, 40);
			frame.getContentPane().add(lblNewLabel);
			
			JPanel panel = new JPanel();
			panel.setBounds(27, 76, 272, 195);
			frame.getContentPane().add(panel);
			panel.setLayout(null);
			
			JLabel l1=new JLabel("Id");
			l1.setBounds(20,20,50,20);
			panel.add(l1);
			
			JLabel l2=new JLabel("First Name");
			l2.setBounds(20,50,70,20);
			panel.add(l2);
			
			JLabel l3=new JLabel("Last Name");
			l3.setBounds(20,80,70,20);
			panel.add(l3);

			JLabel l4=new JLabel("Email");
			l4.setBounds(20,110,70,20);
			panel.add(l4);

			JLabel l5=new JLabel("Mobile");
			l5.setBounds(20, 170, 74, 23);
			panel.add(l5);
			
			t1=new JTextField(20);
			t1.setBounds(90,20,120,20);
			panel.add(t1);

			t2=new JTextField(20);
			t2.setBounds(90,50,120,20);
			panel.add(t2);

			t3=new JTextField(20);
			t3.setBounds(90,80,120,20);
			panel.add(t3);

			t4=new JTextField(20);
			t4.setBounds(90,110,120,20);
			panel.add(t4);

			t5=new JTextField(20);
			t5.setBounds(90, 170, 120, 20);
			panel.add(t5);

			
			
			JButton btnNewButton = new JButton("Reset");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					t1.setText("");
					t2.setText("");
					t3.setText("");
					t4.setText("");
					t5.setText("");
					
				}
			});
			btnNewButton.setBounds(21, 138, 74, 23);
			panel.add(btnNewButton);
			
			JButton btnSubmit = new JButton("Submit");
			btnSubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String fname = t2.getText();
					String lname = t3.getText();
					String email = t4.getText();
					long mobile=Long.parseLong(t5.getText());
					
					try {
						PreparedStatement ps = cn.prepareStatement("insert into stu(fname,lname,email,mobile) values(?,?,?,?)");
						ps.setString(1, fname);
						ps.setString(2, lname);
						ps.setString(3, email);
						ps.setLong(4, mobile);
						
						int i = ps.executeUpdate();
						if(i>0)
						{
							JOptionPane.showMessageDialog(frame,"Data Inserted successfully !!!!");
							t1.setText("");
							t2.setText("");
							t3.setText("");
							t4.setText("");
							t5.setText("");
							loadData();
						
						}
					
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			});
			btnSubmit.setBounds(105, 138, 74, 23);
			panel.add(btnSubmit);
			
			JButton edit = new JButton("Edit");
			edit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					  
					int did = Integer.parseInt(uid.getText());
					String fname = t2.getText();
					String lname = t3.getText();
					String email = t4.getText();
					long mobile=Long.parseLong(t5.getText());
					
					try {
						PreparedStatement ps = cn.prepareStatement("update stu set fname=?,lname=?, email=?,mobile=? where id=?");
						ps.setString(1, fname);
						ps.setString(2, lname);
						ps.setString(3, email);
						ps.setLong(4, mobile);
						ps.setInt(5, did);
						
						int i = ps.executeUpdate();
						
						if(i>0)
						{
							JOptionPane.showMessageDialog(frame, "Data updated !!!");
							loadData();
						}
						
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			});
			edit.setBounds(189, 138, 74, 23);
			panel.add(edit);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(309, 76, 344, 195);
			frame.getContentPane().add(scrollPane);
			
			JPanel panel_1 = new JPanel();
			scrollPane.setViewportView(panel_1);
			panel_1.setLayout(new FlowLayout());
			
			JMenuBar bar=new JMenuBar();
			setJMenuBar(bar);
			bar.setBounds(200, 50, 50, 20);
			JMenu m1=new JMenu("Id");
			bar.add(m1);
			panel_1.add(bar);
			
			JMenu m2=new JMenu("Fname");
			bar.add(m2);
			
			JMenu m3=new JMenu("Lname");
			bar.add(m3);
			
			JMenu m4=new JMenu("Email");
			bar.add(m4);
			
			JMenu m5=new JMenu("Contact");
			bar.add(m5);
			
			table = new JTable();
			panel_1.add(table, BorderLayout.SOUTH);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(27, 282, 626, 94);
			frame.getContentPane().add(panel_2);
			panel_2.setLayout(null);
			
			JLabel lblNewLabel_2 = new JLabel("Student Id");
			lblNewLabel_2.setBounds(26, 44, 74, 14);
			panel_2.add(lblNewLabel_2);
			
			uid = new JTextField();
			uid.setBounds(92, 33, 137, 37);
			panel_2.add(uid);
			uid.setColumns(10);
			
			
			JButton btnNewButton_01 = new JButton("Refresh");
			btnNewButton_01.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int did = Integer.parseInt(uid.getText());
					
					try {
						PreparedStatement ps = cn.prepareStatement("select * from stu");
						ResultSet rs = ps.executeQuery();
						while(rs.next())
						{
							int id=rs.getInt("id");
							String fname=rs.getString("fname");
							String lname=rs.getString("lname");
							String email=rs.getString("email");
							long mobile=rs.getLong("mobile");
							String data=id+" "+fname+" "+lname+" "+email+" "+mobile;
							JMenuItem i1=new JMenuItem(data);
							panel_1.add(i1);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			});
			btnNewButton_01.setBounds(289, -5, 89, 38);
			panel_2.add(btnNewButton_01);
			
			
			JButton btnNewButton_1 = new JButton("Update");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int did = Integer.parseInt(uid.getText());
					
					try {
						PreparedStatement ps = cn.prepareStatement("select * from stu where id=?");
						ps.setInt(1, did);
						ResultSet rs = ps.executeQuery();
						if(rs.next())
						{
							t2.setText(rs.getString(2));
							t3.setText(rs.getString(3));
							t4.setText(rs.getString(4));
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "Data not found");
						}
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			});
			btnNewButton_1.setBounds(289, 32, 89, 38);
			panel_2.add(btnNewButton_1);
			
			JButton btnNewButton_1_1 = new JButton("Delete");
			btnNewButton_1_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int did = Integer.parseInt(uid.getText());
					try {
						PreparedStatement ps = cn.prepareStatement("delete from stu where id = ?");
						ps.setInt(1, did);
						int i = ps.executeUpdate();
						if(i>0)
						{
							JOptionPane.showMessageDialog(frame, "Data deleted!!!");
							loadData();
						}
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
				}
			});
			btnNewButton_1_1.setBounds(417, 32, 89, 38);
			panel_2.add(btnNewButton_1_1);
		}


		private void setJMenuBar(JMenuBar bar) {
			// TODO Auto-generated method stub
			
		}
	}