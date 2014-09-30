 package com.ub.edu.bda;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.StringType;

public class TestHB {

	/**
	 * @param args
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Session session = null;
	        Transaction tx = null;
	        Articulo art = new Articulo("Lavadora AEG", 23.4);
	        
	        try {
	            session = ConnectorHB.getSession();
	            tx = session.beginTransaction();
	            session.save(art);
	            //El objecto art esta enlazado
	            
	            art.setDescripcion("Seat Leon"); //Esto se modificara en la BD no los datos iniciales.
	            tx.commit();
	            
	            List<Catalogo> listado = new ArrayList<Catalogo>();
	            Query q = session.createQuery("from Catalogo");
	            listado = q.list();
	            
	            for (Catalogo catalogo : listado) {
	            	System.out.println(catalogo.getDescripcion());
	            	for(Articulo articulo: catalogo.getArticulos())
	                System.out.println(articulo.getDescripcion());
	            }
	            System.out.println("Proceso finalizado...");
	            //US DE QUERY SQL PER TROBAR OBJECTES
	           List <Articulo> articulos = session.createSQLQuery("SELECT * FROM ARTICULO").addEntity(Articulo.class).list();
	            for(Articulo articulo: articulos)
	                System.out.println(articulo.getDescripcion());
	            
	  
	            Query q1= session.createSQLQuery("SELECT descripcion from ARTICULO WHERE ID=1").addScalar("descripcion",StringType.INSTANCE);
	          System.out.println("HE OBTINGUT LA DESCRIPCIO: "+q1.list().get(0));
	            
	        } catch (HibernateException e) {
	            if(tx!=null && tx.isActive()) tx.rollback();
	            e.printStackTrace();
	        } finally {
	            if(session!=null) session.close();
	        }

	}

}
