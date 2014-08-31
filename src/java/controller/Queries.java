/*
 * Třída obsahující metody, jenž se dotazují na Account uživatele. 
 */
package controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import model.Account;
import model.HibernateUtil;
import model.Invoice;
import model.InvoiceHasPerson;
import model.Person;
import model.State;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Queries {

    static SessionFactory sessionFactory = null;

    public static void init() {
        sessionFactory = HibernateUtil.getSessionFactory();

    }

    /*
     Vyhledá v databázi kombinaci email/heslo. Pokud je nalezen jedem záznam, 
     vrátí true a zaznamená ID do session (TO DO)
     */
    public static boolean login(String email, String password) {
        Account logedAccount = null;
        Session session = sessionFactory.openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Account where password ='" + password + "' and email ='" + email + "'");
            logedAccount = (Account) q.uniqueResult();

            if (logedAccount != null){
                
                //Get HTML Session and put there user's id as attribute "logedid"
                HttpSession s = HttpSessionUtil.getSession();
                s.setAttribute("logedid", logedAccount.getId());
                //
                return true;
            } else
            return false;
        } catch (Exception e) {
        } finally {
            session.close();
        }

        return false;

    }

    public static List<Invoice> getInvoices(int logedUserId) {
        List<Invoice> invoices = null;
        Session session = sessionFactory.openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();

            Query q = session.createQuery("from Invoice where account_idaccount ='" + logedUserId + "'");
            invoices = (List<Invoice>) q.list();

            return invoices;
        } catch (Exception e) {
        } finally {
            session.close();
        }

        return invoices;
    }
    
    public static Invoice getInvoiceAtId(int id){
        
        Session session = sessionFactory.openSession();
        Invoice result = null;
        
        try {      
            Transaction tx = session.beginTransaction();

            Query q = session.createQuery("from Invoice where "
                    + "id ='" + id + "'");
            result= (Invoice) q.uniqueResult();     
            return result;
        } catch (HibernateException e) {
        } finally {
           session.close(); 
        }
        return result;
    }
    
    public static List<Person> getPersonsAtAccountId(String idaccount){
        
       Session session = sessionFactory.openSession();
       List<Person> result = null;
        
        try {      
            Transaction tx = session.beginTransaction();

            Query q = session.createQuery("from Person where "
                    + "account_idaccount ='" + idaccount + "' and isowner=false");
            result= (List<Person>) q.list();     
            return result;
        } catch (HibernateException e) {
        } finally {
           session.close(); 
        }
        return result;
    }
    
    public static State getStateAtId(int id){
        
        Session session = sessionFactory.openSession();
        State result = null;
        
        try {      
            Transaction tx = session.beginTransaction();

            Query q = session.createQuery("from State where "
                    + "id ='" + id + "'");
            result= (State) q.uniqueResult();     
            return result;
        } catch (HibernateException e) {
        } finally {
           session.close(); 
        }
        return result;
    }
    
    public static String getName(int idInvoice){
        
        InvoiceHasPerson personId;
        Person person;
        
        String name = null;
        int userId;
        
        try {
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            Query q = session.createQuery("from InvoiceHasPerson where "
                    + "invoice_idinvoice ='" + idInvoice + "' "
                    + "and relation ='" + 1 + "'");
            personId = (InvoiceHasPerson)q.uniqueResult();
            userId = personId.getId();
            session.close();
            
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            q = session.createQuery("from Person where id ='" + userId + "'");
            person = (Person) q.uniqueResult();
            session.close();
            
            name = person.getName() + " " + person.getLastname();

            return name;
        } catch (HibernateException e) {
        } 
        return name;
    }
    
    public static int createAccount(Account newAccount){
        
        Session session = null;
        State result = null;
        
        try {      
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(newAccount);
            tx.commit();
            session.close();
            
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Query q = session.createQuery("from Account where password ='" + 
                    newAccount.getPassword() + "' and email ='" + newAccount.getEmail() + "'");
            newAccount = (Account) q.uniqueResult();
            session.close();
            
            return newAccount.getId();

        } catch (HibernateException e) {
        } 
        return 0;
    }
    
    public static void createPerson(Person newPerson){
        
        Session session = null;
        State result = null;
        
        try {      
            
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(newPerson);
            tx.commit();
            session.close();

        } catch (HibernateException e) {
        } 
    }
    
        
        
    
    

}
