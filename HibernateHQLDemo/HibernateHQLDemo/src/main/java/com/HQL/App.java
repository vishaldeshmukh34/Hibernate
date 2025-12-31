package com.HQL;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while(true){

            System.out.println("\n===== HQL DEMO =====");
            System.out.println("1. Insert Student");
            System.out.println("2. Get All Students (HQL)");
            System.out.println("3. Get Students Above 80 Marks (HQL)");
            System.out.println("4. Update Marks Using HQL");
            System.out.println("5. Delete Using HQL");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");

            int ch = sc.nextInt();

            switch(ch){

                case 1: insert(sc); break;
                case 2: fetchAll(); break;
                case 3: fetchTopStudents(); break;
                case 4: updateMarks(sc); break;
                case 5: deleteStudent(sc); break;
                case 6: System.exit(0);
                default: System.out.println("Invalid Choice");
            }
        }
    }

    static void insert(Scanner sc){
        System.out.print("Enter Name: ");
        String name = sc.next();

        System.out.print("Enter City: ");
        String city = sc.next();

        System.out.print("Enter Marks: ");
        int marks = sc.nextInt();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Student s = new Student(name, city, marks);
        session.save(s);

        tx.commit();
        session.close();
        System.out.println("Student Inserted!");
    }

    static void fetchAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Student> list =
                session.createQuery("from Student", Student.class).list();

        for(Student s : list){
            System.out.println(s.getId()+" "+s.getName()+" "+s.getCity()+" "+s.getMarks());
        }

        session.close();
    }

    static void fetchTopStudents(){
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Student> list =
                session.createQuery("from Student where marks > 80", Student.class).list();

        for(Student s : list){
            System.out.println(s.getName()+" "+s.getMarks());
        }

        session.close();
    }

    static void updateMarks(Scanner sc){

        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        System.out.print("Enter New Marks: ");
        int marks = sc.nextInt();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        String 	HQL = "update Student set marks = :m where id = :i";

        int count = session.createQuery(HQL)
                .setParameter("m", marks)
                .setParameter("i", id)
                .executeUpdate();

        tx.commit();
        session.close();

        System.out.println(count+" Record Updated");
    }

    static void deleteStudent(Scanner sc){

        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        String HQL = "delete from Student where id = :i";

        int count = session.createQuery(HQL)
                .setParameter("i", id)
                .executeUpdate();

        tx.commit();
        session.close();

        System.out.println(count+" Record Deleted");
    }
}
