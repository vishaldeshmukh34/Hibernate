package com.takl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tak.helper.HibernateUtil;
import com.tak.model.Student;

public class MainApp {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Student s = new Student("Vishak", "Jalna");
        session.save(s);

        tx.commit();
        session.close();

        System.out.println("Data Inserted Successfully!");
    }
}
