package org.studyeasy;
import org.hibernate.cfg.Configuration;
import org.studyeasy.entity.Users;

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
			//Create Object of entity class type
//			Users user = new Users("username","password","first_name","last_name");
			Users user = new Users();
			
			//Start Transaction 
//			session.beginTransaction();
			//Perform operation
//			session.save(user);
			user = session.get(Users.class,1);
			user.setPassword("satyamsingh");
			//Commit the transaction
//			session.getTransaction().commit();
			tx.commit();
			System.out.println(user);
		}catch (Exception e) {
		    tx.rollback();  // Rollback in case of an error
		    e.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
		
		
		
		
	}

}
