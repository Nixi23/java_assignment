package com.core.HibernetCore;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SessionFactory sf = new Configuration().configure("com/core/HibernetCore/hibernate.cfg.xml").buildSessionFactory();
        Session session =sf.openSession();
        Transaction tx=session.beginTransaction();
        User u=new User(2,"java",12384590);
        session.save(u);
        tx.commit();
        session.close();
        sf.close();
    }
}
