package com.student;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== STUDENT CRUD APP =====");
            System.out.println("1. Add Student");
            System.out.println("2. Get Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
            case 1:
                addStudent(sc);
                break;

            case 2:
                getStudent(sc);
                break;

            case 3:
                updateStudent(sc);
                break;

            case 4:
                deleteStudent(sc);
                break;

            case 5:
                System.exit(0);
                break;

            default:
                System.out.println("Invalid choice");
            }

            
        }
    }

    // ADD
    static void addStudent(Scanner sc) {
        System.out.print("Enter name: ");
        String name = sc.next();
        System.out.print("Enter city: ");
        String city = sc.next();
        System.out.print("Enter marks: ");
        int marks = sc.nextInt();

        Student s = new Student(name, city, marks);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(s);

        tx.commit();
        session.close();
        System.out.println("Student Added Successfully!");
    }

    // READ
    static void getStudent(Scanner sc) {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Student s = session.get(Student.class, id);

        if (s != null) {
            System.out.println("ID: " + s.getId());
            System.out.println("Name: " + s.getName());
            System.out.println("City: " + s.getCity());
            System.out.println("Marks: " + s.getMarks());
        } else {
            System.out.println("Student Not Found!");
        }

        session.close();
    }

    // UPDATE
    static void updateStudent(Scanner sc) {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Student s = session.get(Student.class, id);

        if (s != null) {
            System.out.print("Enter new city: ");
            s.setCity(sc.next());

            System.out.print("Enter new marks: ");
            s.setMarks(sc.nextInt());

            session.update(s);
            tx.commit();
            System.out.println("Student Updated!");
        } else {
            System.out.println("Student Not Found!");
        }

        session.close();
    }

    // DELETE
    static void deleteStudent(Scanner sc) {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Student s = session.get(Student.class, id);

        if (s != null) {
            session.delete(s);
            tx.commit();
            System.out.println("Student Deleted!");
        } else {
            System.out.println("Student Not Found!");
        }

        session.close();
    }
}
