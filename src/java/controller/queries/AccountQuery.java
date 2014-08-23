/*
 * Třída obsahující metody, jenž se dotazují na Account uživatele. 
 */

package controller.queries;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import model.HibernateUtil;


public class AccountQuery {
    
    /*
    Vyhledá v databázi kombinaci email/heslo. Pokud je nalezen jedem záznam, 
    vrátí true a zaznamená ID do session (TO DO)
    */
    public static boolean login(String email, String password) {
    List<model.Account> logedAccount = null;
    try {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("from Account where password ='"+ password +"' and email ='"+ email +"'");
        logedAccount = (List<model.Account>) q.list();
        session.close();
        return logedAccount.size() == 1;             
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return false;
}
    
}
