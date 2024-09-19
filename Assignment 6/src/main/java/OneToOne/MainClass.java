package OneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainClass {
	public static void main(String args[])
	{
		SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session =sf.openSession();
		Transaction tx = session.beginTransaction();
		
		Student s1=new Student();
		s1.setStu_id(1);
		s1.setName("java");
		
		Student s2=new Student();
		s2.setStu_id(2);
		s2.setName("php");
		
		Address a1=new Address();
		a1.setAddress_id(1);
		a1.setStreet("happy street");
		a1.setCity("ahmedabad");
		a1.setState("gujarat");
		a1.setZipcode(380006);
		
		Address a2=new Address();
		a2.setAddress_id(2);
		a2.setStreet("happy street");
		a2.setCity("ahmedabad");
		a2.setState("gujarat");
		a2.setZipcode(380006);
		
		
		s1.setAddress(a1);
		s2.setAddress(a2);
		
		a1.setStudent(s1);
		a2.setStudent(s2);
		
		session.save(a1);
		session.save(a2);
		session.save(s1);
		session.save(s2);
		
		session.update(a2);
		session.delete(s1);
		tx.commit();
		session.close();
		sf.close();
	}
}
