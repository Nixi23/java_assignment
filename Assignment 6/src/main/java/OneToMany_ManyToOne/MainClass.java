
package OneToMany_ManyToOne;

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
		
		Cart o1 = new Cart();
		o1.setId(1);
		o1.setName("java");
		o1.setTotal(1000);
		
		Item v1 = new Item();
		v1.setId(1);
		v1.setQuantity(100);
		v1.setItem_ttl(10*100);
		
		Item v2 = new Item();
		v2.setId(2);
		v2.setQuantity(50);
		v2.setItem_ttl(10*50);
		
		Item v3 = new Item();
		v3.setId(3);
		v3.setQuantity(20);
		v2.setItem_ttl(10*20);
		
		List<Item> vList = new ArrayList<Item>();
		vList.add(v1);
		vList.add(v2);
		vList.add(v3);

		o1.setItem(vList);
		v1.setCart(o1);
		v2.setCart(o1);
		v3.setCart(o1);
		
		session.save(o1);
		session.save(v1);
		session.save(v2);
		session.save(v3);
		
		session.update(v2);
		session.delete(v1);
		
		tx.commit();
		session.close();
		sf.close();
	}
}
