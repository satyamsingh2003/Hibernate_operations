package org.studyeasy;
import org.hibernate.cfg.Configuration;
import org.studyeasy.entity.Users;

import java.util.List;

import javax.transaction.SystemException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
public class App {
	public static void main(String[] args) throws IllegalStateException, SystemException {
//		Configuration config = new Configuration().
//								configure("hibernate.cfg.xml").
//								addAnnotatedClass(Users.class);
//		SessionFactory factory = config.buildSessionFactory();
		
		/* we can write in this also*/
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Users.class)
								.buildSessionFactory();
		Session session = factory.getCurrentSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		try {
			List<Users> users = session.createQuery("from users").getResultList();
			for(Users temp: users) {
				System.out.println(temp);
			}
			
		}catch (Exception e) {
		    tx.rollback();  // Rollback in case of an error
		    e.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
		
		
		
		
	}

}
