package alxa.ub;

import com.ub.edu.bda.ConnectorHB;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TestHB {

	/**
	 * @param args
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Session session = null;
	        Transaction tx = null;
	        
	        try {
	            session = ConnectorHB.getSession();
	            
	            List<Article> listado = new ArrayList<>();
	            Query q = session.createQuery("from Article");
                    
	            listado = q.list();
	            
                    
                    
	            for (Article article : listado) {
	            	System.out.println(article.getMarca().getNom());
	            	for(Usuari usuari: article.getUsuaris())
                            System.out.println(usuari.getNom());
                        System.out.println("\n");
	            }
	            System.out.println("Proceso finalizado...");
	            //US DE QUERY SQL PER TROBAR OBJECTES
	            
	        } catch (HibernateException e) {
                    e.printStackTrace();
	            if(tx!=null && tx.isActive()) tx.rollback();
	        } finally {
	            if(session!=null) session.close();
	        }

	}

}
