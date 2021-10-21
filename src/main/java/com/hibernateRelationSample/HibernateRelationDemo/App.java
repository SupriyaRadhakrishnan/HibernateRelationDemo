package com.hibernateRelationSample.HibernateRelationDemo;

import javax.swing.LayoutFocusTraversalPolicy;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
      
    Laptop laptop  = new Laptop();
    laptop.setLid(101);
    laptop.setLname("Dell");
   
    
    Student student = new Student();
    student.setRollno(1);
    student.setName("Peter");
    student.setMarks(50);
    student.getLaptop().add(laptop);
    
    laptop.getStud().add(student); 
    
    
    Configuration cfg = new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class);
    ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
    SessionFactory sf = cfg.buildSessionFactory(reg);
    Session s = sf.openSession();
    
    Transaction tx = s.beginTransaction();
    s.save(student);
    s.save(laptop);
    tx.commit();
   
    }
}
