package ManyToMany;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainClass {
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx  = session.beginTransaction();
		
		Reader e1 = new Reader();
		e1.setId(1);
		e1.setFname("java");
		e1.setLname("code");
		e1.setEmail("java@gmail.com");

		Reader e2 = new Reader();
		e2.setId(2);
		e2.setFname("python");
		e2.setLname("code");
		e2.setEmail("python@gmail.com");
		
		Subscription p1 = new Subscription();
		p1.setId(1);
		p1.setS_name("HRMS");
		

		Subscription p2 = new Subscription();
		p2.setId(2);
		p2.setS_name("BookS");
		
		List<Reader> emp_list = new ArrayList<Reader>();
		emp_list.add(e1);
		emp_list.add(e2);
		
		List<Subscription> pro_list = new ArrayList<Subscription>();
		pro_list.add(p1);
		pro_list.add(p2);
		
		e1.setSubscriptions(pro_list);
		e2.setSubscriptions(pro_list);
		
		p1.setReader(emp_list);
		p2.setReader(emp_list);
		
		
		session.save(e1);
		session.save(e2);
		session.save(p1);
		session.save(p2);
		
		session.saveOrUpdate(p1);
		session.delete(e2);
		
		tx.commit();
		session.close();
		sf.close();
	}
}