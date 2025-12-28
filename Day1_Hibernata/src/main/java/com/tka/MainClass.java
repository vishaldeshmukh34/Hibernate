package com.tka;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainClass {

	public static void main(String[] args) {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Employee.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		Employee e = new Employee();
		e.setEmpId(125);
		e.setEmpName("sunil");
		e.setCity("nsk");
		e.setSalary("12LPA");
		
		e.setEmpId(126);
		e.setEmpName("anil");
		e.setCity("nsk");
		e.setSalary("14LPA");
		
		e.setEmpId(127);
		e.setEmpName("vishal");
		e.setCity("pune");
		e.setSalary("15LPA");

//		ss.save(e);
		ss.persist(e);

		tr.commit();
		ss.close();
		System.out.println("Data is Inserted ... !");
	}

}