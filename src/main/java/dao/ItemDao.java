package dao;

import entity.Bmw;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class ItemDao extends Dao {

    public List<Bmw> get() {
//        Session s = openSessionAndBeginTransaction();
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        Criteria c = s.createCriteria(Bmw.class);
        List<Bmw> out = c.list();
        s.getTransaction().commit();
        s.close();
//        commitTransactionAndCloseSession(s);
        return out;
    }

    public List<Bmw> getByCat(String cat) {
        return getData("SELECT * FROM bmwitem WHERE cat='" + cat + "'");
    }

    public Bmw getById(int id) {
        List<Bmw> items = getData("SELECT * FROM bmwitem WHERE id=" + id);
        if (!items.isEmpty()) {
            return items.get(0);
        } else {
            return new Bmw();
        }
    }

    private List<Bmw> getData(String query) {
        Session s = openSessionAndBeginTransaction();
        Query q = s.createQuery(query);
        List<Bmw> out = q.list();
        commitTransactionAndCloseSession(s);
        return out;

    }

}
