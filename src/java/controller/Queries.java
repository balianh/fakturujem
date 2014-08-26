/*
 * Třída obsahující metody, jenž se dotazují na Account uživatele. 
 */
package controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import model.Account;
import model.HibernateUtil;
import model.Invoice;
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
        List<Account> logedAccount = null;
        Session session = sessionFactory.openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Account where password ='" + password + "' and email ='" + email + "'");
            logedAccount = (List<Account>) q.list();

            if (logedAccount.size() == 1){
                HttpSession s = HttpSessionUtil.getSession();
                Account loggedAcc =logedAccount.get(0);
                s.setAttribute("logedid", loggedAcc.getIdacount());
                return true;
            } else
            return false;
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
        } finally {
            session.close();
        }

        return invoices;
    }

}
