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
import model.Item;
import model.Method;
import model.Person;
import model.Rate;
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

            if (logedAccount != null) {

                //Get HTML Session and put there user's id as attribute "logedid"
                HttpSession s = HttpSessionUtil.getSession();
                s.setAttribute("logedid", logedAccount.getId());
                //
                return true;
            } else {
                return false;
            }
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

    public static List<Rate> getRates() {
        List<Rate> rates = null;
        Session session = sessionFactory.openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();

            Query q = session.createQuery("from Rate ");
            rates = (List<Rate>) q.list();

            return rates;
        } catch (Exception e) {
        } finally {
            session.close();
        }

        return rates;
    }

    public static Invoice getInvoiceAtId(int id) {

        Session session = sessionFactory.openSession();
        Invoice result = null;

        try {
            Transaction tx = session.beginTransaction();

            Query q = session.createQuery("from Invoice where "
                    + "id ='" + id + "'");
            result = (Invoice) q.uniqueResult();
            return result;
        } catch (HibernateException e) {
        } finally {
            session.close();
        }
        return result;
    }

    public static List<Person> getPersonsAtAccountId(String idaccount) {

        Session session = sessionFactory.openSession();
        List<Person> result = null;

        try {
            Transaction tx = session.beginTransaction();

            Query q = session.createQuery("from Person where "
                    + "account_idaccount ='" + idaccount + "' and isowner=false");
            result = (List<Person>) q.list();

            return result;
        } catch (HibernateException e) {
        } finally {
            session.close();
        }
        return result;
    }

    public static State getStateAtId(int id) {

        Session session = sessionFactory.openSession();
        State result = null;

        try {
            Transaction tx = session.beginTransaction();

            Query q = session.createQuery("from State where "
                    + "id ='" + id + "'");
            result = (State) q.uniqueResult();
            return result;
        } catch (HibernateException e) {
        } finally {
            session.close();
        }
        return result;
    }

    public static Rate getRateAtId(int id) {

        Session session = sessionFactory.openSession();
        Rate result = null;

        try {
            Transaction tx = session.beginTransaction();

            Query q = session.createQuery("from Rate where "
                    + "id ='" + id + "'");
            result = (Rate) q.uniqueResult();
            return result;
        } catch (HibernateException e) {
        } finally {
            session.close();
        }
        return result;
    }

    public static String getName(int idInvoice) {

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
            personId = (InvoiceHasPerson) q.uniqueResult();
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

    public static int createAccount(Account newAccount) {

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
            Query q = session.createQuery("from Account where password ='"
                    + newAccount.getPassword() + "' and email ='" + newAccount.getEmail() + "'");
            newAccount = (Account) q.uniqueResult();
            session.close();

            return newAccount.getId();

        } catch (HibernateException e) {
        }
        return 0;
    }

    public static int createPerson(Person newPerson) {

        Session session = null;
        State result = null;
        int personId;

        try {

            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(newPerson);
            session.flush();
            personId = newPerson.getId();
            session.close();

            return personId;

        } catch (HibernateException e) {
        }
        return 0;
    }

    public static int createInvoice(Invoice selectedInvoice) {
        Session session = null;
        State result = null;
        int invoiceId;

        try {
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(selectedInvoice);
            session.flush();
            invoiceId = selectedInvoice.getId();
            session.close();

            return invoiceId;

        } catch (HibernateException e) {
        }
        return 0;
    }

    public static List<Item> getItemsAtAccountId(String logedID) {
        Session session = sessionFactory.openSession();
        List<Item> result = null;

        try {
            Transaction tx = session.beginTransaction();

            Query q = session.createQuery("from Item where "
                    + "account_idaccount ='" + logedID + "'");
            result = (List<Item>) q.list();

            return result;
        } catch (HibernateException e) {
        } finally {
            session.close();
        }
        return result;
    }

    public static List<Method> getMethods() {
        List<Method> methods = null;
        Session session = sessionFactory.openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Method ");
            methods = (List<Method>) q.list();
            return methods;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return methods;
    }

}
