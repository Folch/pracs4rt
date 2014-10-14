/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alxa.ub.vista;

import alxa.ub.model.Article;
import alxa.ub.model.Campanya;
import alxa.ub.model.Familia;
import alxa.ub.model.SubFamilia;
import alxa.ub.model.Usuari;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Albert i Xavi
 */
public class PrivaliaUB {

    private static final String MAIN_MENU_TITOL = "PRIVALIA UB - ELS MILLORS ARTICLES, AL MILLOR PREU";
    private static final String[] MAIN_MENU_DESC = {"Campanya", "Article", "Família", "Subfamília", "Sortir"};

    private static enum MainMenuOps {

        CAMPANYA, ARTICLE, FAMILIA, SUBFAMILIA, SORTIR
    };

    private static final String CRUD_MENU_TITOL = "Operacions CRUD";
    private static final String[] CRUD_MENU_DESC = {"Insertar", "Obtenir", "Actualitzar", "Eliminar", "Tornar enrere"};

    private static enum CRUDMenuOps {

        INSERTAR, OBTENIR, ACTUALITZAR, ELIMINAR, SORTIR
    };

    private Session session;
    private Transaction tx;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void main(String[] args) {
        System.out.println("Benvingut a Privalia UB");
        Scanner sc = new Scanner(System.in);
        new PrivaliaUB().showLogin(sc);
    }
    /**
     * Constructor
     */
    public PrivaliaUB() {
        session = ConnectorHB.getSession();
        tx = session.getTransaction();

    }
    /**
     * Mètode per 'logar-se' en el programa, si s'equivoca 3 vegades sortirà del programa
     * @param sc 
     */
    private void showLogin(Scanner sc) {

        String user, pass;
        Usuari u = null;
        short times = 0;

        System.out.println("Usuari: userX");
        System.out.println("Pass: passX");
        System.out.println("on X varia en enters de 1 a 5");
        do {
            System.out.println("usuari:");
            user = sc.nextLine();
            System.out.println("contrasenya:");
            pass = sc.nextLine();

            Query q = session.createQuery("from Usuari where nom = :user and contrasenya = :pass");
            q.setString("user", user);
            q.setString("pass", pass);
            u = (Usuari) q.uniqueResult();

            times++;

            if (u == null) {
                System.out.println("usuari i/o contrasenya incorrectes. Torna a provar-ho.");
            }
            if (times == 3) {
                System.out.println("Ho has provat ja tres vegades. Adeu.");
                System.exit(0);
            }

        } while (u == null);

        System.out.println("Benvingut " + u.getNom());

        showMainMenu(sc);
    }
    /**
     * Mètode que mostra les entitats que l'usuari pot manipular
     * @param sc 
     */
    private void showMainMenu(Scanner sc) {
        Menu<MainMenuOps> mainMenu = new Menu<>(MAIN_MENU_TITOL, MainMenuOps.values(), MAIN_MENU_DESC);
        MainMenuOps op;
        do {
            mainMenu.mostrarMenu();
            op = mainMenu.getOpcio(sc);
            switch (op) {
                case ARTICLE:
                    showCRUDMenu(sc, MainMenuOps.ARTICLE);
                    break;
                case CAMPANYA:
                    showCRUDMenu(sc, MainMenuOps.CAMPANYA);
                    break;
                case FAMILIA:
                    showCRUDMenu(sc, MainMenuOps.FAMILIA);
                    break;
                case SUBFAMILIA:
                    showCRUDMenu(sc, MainMenuOps.SUBFAMILIA);
                    break;
            }
        } while (op != MainMenuOps.SORTIR);
    }
    /**
     * Mètode per mostrar les operacions CRUD de cadascuna de les entitats demanades
     * @param sc
     * @param type 
     */
    private void showCRUDMenu(Scanner sc, MainMenuOps type) {
        Menu<CRUDMenuOps> crudMenu = new Menu<>(CRUD_MENU_TITOL, CRUDMenuOps.values(), CRUD_MENU_DESC);

        CRUDMenuOps op;

        do {
            crudMenu.mostrarMenu();
            op = crudMenu.getOpcio(sc);
            switch (op) {
                case INSERTAR:
                    switch (type) {
                        case ARTICLE:
                            showInsertarArticle(sc);
                            break;
                        case CAMPANYA:
                            showInsertarCampanya(sc);
                            break;
                        case FAMILIA:
                            showInsertarFamilia(sc);
                            break;
                        case SUBFAMILIA:
                            showInsertarSubFamilia(sc);
                            break;
                    }
                    break;
                case OBTENIR:
                    switch (type) {
                        case ARTICLE:
                            showObtenirArticle(sc);
                            break;
                        case CAMPANYA:
                            showObtenirCampanya(sc);
                            break;
                        case FAMILIA:
                            showObtenirFamilia(sc);
                            break;
                        case SUBFAMILIA:
                            showObtenirSubFamilia(sc);
                            break;
                    }
                    break;
                case ACTUALITZAR:
                    switch (type) {
                        case ARTICLE:
                            showActualitzarArticle(sc);
                            break;
                        case CAMPANYA:
                            showActualitzarCampanya(sc);
                            break;
                        case FAMILIA:
                            showActualitzarFamilia(sc);
                            break;
                        case SUBFAMILIA:
                            showActualitzarSubFamilia(sc);
                            break;
                    }
                    break;
                case ELIMINAR:
                    switch (type) {
                        case ARTICLE:
                            showEliminarArticle(sc);
                            break;
                        case CAMPANYA:
                            showEliminarCampanya(sc);
                            break;
                        case FAMILIA:
                            showEliminarFamilia(sc);
                            break;
                        case SUBFAMILIA:
                            showEliminarSubFamilia(sc);
                            break;
                    }
                    break;
            }
        } while (op != CRUDMenuOps.SORTIR);
    }
    /**
     * Mètode per insertar un nou article amb tots els seus atributs excepte les claus foranies
     * @param sc 
     */
    private void showInsertarArticle(Scanner sc) {
        try {
            tx = session.beginTransaction();
            System.out.println("talla:");
            String talla = sc.nextLine();
            System.out.println("color:");
            String color = sc.nextLine();
            System.out.println("stock:");
            int stock = sc.nextInt();
            System.out.println("preu:");
            float preu = sc.nextFloat();
            System.out.println("iva:");
            float iva = sc.nextFloat();

            Article a = new Article(talla, color, stock, preu, iva, null, null);
            session.save(a);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

    }
    /**
     * Mètode que fa un select de tots els atributs d'un article
     * @param sc 
     */
    private void showObtenirArticle(Scanner sc) {
        List<Article> articles = new ArrayList<Article>();
        Query q = session.createQuery("from Article");

        articles = q.list();

        for (Article article : articles) {
            System.out.println(article);
        }
    }
    /**
     * Mètode per obtenir una instancia article donat el seu id
     * @param id
     * @return 
     */
    private Article getArticle(int id) {
        Query q = session.createQuery("from Article where idArticle = :id");
        q.setInteger("id", id);
        return (Article) q.uniqueResult();
    }
    /**
     * Mètode per obtenir una instancia campanya donada el seu id
     * @param id
     * @return 
     */
    private Campanya getCampanya(int id) {
        Query q = session.createQuery("from Campanya where idCampanya = :id");
        q.setInteger("id", id);
        return (Campanya) q.uniqueResult();
    }
    /**
     * Mètode per obtenir una instancia familia donada el seu id
     * @param id
     * @return 
     */
    private Familia getFamilia(int id) {
        Query q = session.createQuery("from Familia where idFamilia = :id");
        q.setInteger("id", id);
        return (Familia) q.uniqueResult();
    }
    /**
     * Mètode per obtenir una instancia subfamilia donada el seu id
     * @param id
     * @return 
     */
    private SubFamilia getSubFamilia(int id) {
        Query q = session.createQuery("from SubFamilia where idSubfamilia = :id");
        q.setInteger("id", id);
        return (SubFamilia) q.uniqueResult();
    }
    /**
     * Mètode per actualitzar tots els atributs d'un article donat el seu id
     * @param sc 
     */
    private void showActualitzarArticle(Scanner sc) {
        showObtenirArticle(sc);
        try {
            tx = session.beginTransaction();
            System.out.println("Dona'm l'idArticle:");
            int id = sc.nextInt();

            Article a = getArticle(id);

            System.out.println("talla:");
            sc.nextLine();
            String talla = sc.nextLine();
            System.out.println("color:");
            String color = sc.nextLine();
            System.out.println("stock:");
            int stock = sc.nextInt();
            System.out.println("preu:");
            float preu = sc.nextFloat();
            System.out.println("iva:");
            float iva = sc.nextFloat();

            a.setTalla(talla);
            a.setColor(color);
            a.setStock(stock);
            a.setPreu(preu);
            a.setIva(iva);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

    }
    /**
     * Mètode per eliminar un article donat el seu id
     * @param sc 
     */
    private void showEliminarArticle(Scanner sc) {
        showObtenirArticle(sc);
        try {
            tx = session.beginTransaction();
            System.out.println("Dona'm l'idArticle:");
            int id = sc.nextInt();
            Article a = getArticle(id);
            session.delete(a);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }
    /**
     * Mètode per insertar una nova campanya amb tots els seus atributs
     * @param sc 
     */
    private void showInsertarCampanya(Scanner sc) {
        try {
            tx = session.beginTransaction();
            System.out.println("nom:");
            String nom = sc.nextLine();
            System.out.println("data_fi[dd/mm/aaaa]:");
            String df = sc.nextLine();
            Date data_fi = new Date();
            data_fi.setDate(Integer.parseInt(df.substring(0, 2)));
            data_fi.setMonth(Integer.parseInt(df.substring(3, 5)) - 1);
            data_fi.setYear(Integer.parseInt(df.substring(6, 10)) - 1900);
            System.out.println("data_inici[dd/mm/aaaa]:");
            String di = sc.nextLine();
            Date data_inici = new Date();
            data_inici.setDate(Integer.parseInt(di.substring(0, 2)));
            data_inici.setMonth(Integer.parseInt(di.substring(3, 5)) - 1);
            data_inici.setYear(Integer.parseInt(di.substring(6, 10)) - 1900);
            System.out.println("numArticles:");
            int numArticles = sc.nextInt();
            System.out.println("import:");
            float imprt = sc.nextFloat();

            Campanya camp = new Campanya(nom, data_fi, data_inici, numArticles, imprt);
            session.save(camp);
            tx.commit();
        } catch (StringIndexOutOfBoundsException ex) {
            System.err.println("Les dates han de tenir el format dd/mm/aaaa");
            tx.rollback();
        } catch (Exception e) {

            tx.rollback();
        }
    }
    /**
     * Mètode que fa un select de tots els atributs d'una campanya
     * @param sc 
     */
    private void showObtenirCampanya(Scanner sc) {
        List<Campanya> campanyes = new ArrayList<Campanya>();
        Query q = session.createQuery("from Campanya");

        campanyes = q.list();

        for (Campanya camp : campanyes) {
            System.out.println(camp);
        }
    }
    /**
     * Mètode per actualitzar tots els atributs d'una campanya donada el seu id
     * @param sc 
     */
    private void showActualitzarCampanya(Scanner sc) {
        showObtenirCampanya(sc);
        try {
            tx = session.beginTransaction();
            System.out.println("Dona'm l'idCampanya:");
            int id = sc.nextInt();

            Campanya a = getCampanya(id);

            System.out.println("nom:");
            sc.nextLine();
            String nom = sc.nextLine();
            System.out.println("data_fi[dd/mm/aaaa]:");
            String df = sc.nextLine();
            Date data_fi = new Date();
            data_fi.setDate(Integer.parseInt(df.substring(0, 2)));
            data_fi.setMonth(Integer.parseInt(df.substring(3, 5)) - 1);
            data_fi.setYear(Integer.parseInt(df.substring(6, 10)) - 1900);
            System.out.println("data_inici[dd/mm/aaaa]:");
            String di = sc.nextLine();
            Date data_inici = new Date();
            data_inici.setDate(Integer.parseInt(di.substring(0, 2)));
            data_inici.setMonth(Integer.parseInt(di.substring(3, 5)) - 1);
            data_inici.setYear(Integer.parseInt(di.substring(6, 10)) - 1900);
            System.out.println("numArticles:");
            int numArticles = sc.nextInt();
            System.out.println("import:");
            float imprt = sc.nextFloat();

            a.setNom(nom);
            a.setData_fi(data_fi);
            a.setData_inici(data_inici);
            a.setNumArticles(numArticles);
            a.setImprt(imprt);

            tx.commit();
        } catch (StringIndexOutOfBoundsException ex) {
            System.err.println("Les dates han de tenir el format dd/mm/aaaa");
            tx.rollback();
        } catch (Exception e) {
            tx.rollback();
        }
    }
    /**
     * Mètode per eliminar una campanya donada el seu id
     * @param sc 
     */
    private void showEliminarCampanya(Scanner sc) {
        showObtenirCampanya(sc);
        try {
            tx = session.beginTransaction();
            System.out.println("Dona'm l'idCampanya:");
            int id = sc.nextInt();
            Campanya a = getCampanya(id);
            session.delete(a);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }
    /**
     * Mètode per insertar una nova familia amb tots els seus atributs
     * @param sc 
     */
    private void showInsertarFamilia(Scanner sc) {
        try {
            tx = session.beginTransaction();
            System.out.println("nom:");
            String nom = sc.nextLine();

            Familia familia = new Familia(nom);
            session.save(familia);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }
    /**
     * Mètode que fa un select de tots els atributs d'una familia
     * @param sc 
     */
    private void showObtenirFamilia(Scanner sc) {
        List<Familia> families = new ArrayList<Familia>();
        Query q = session.createQuery("from Familia");

        families = q.list();

        for (Familia fam : families) {
            System.out.println(fam);
        }
    }
    /**
     * Mètode per actualitzar tots els atributs d'una familia donada el seu id
     * @param sc 
     */
    private void showActualitzarFamilia(Scanner sc) {
        showObtenirFamilia(sc);
        try {
            tx = session.beginTransaction();
            System.out.println("Dona'm l'ifFamilia:");
            int id = sc.nextInt();

            Familia a = getFamilia(id);
            System.out.println("nom:");
            sc.nextLine();
            String nom = sc.nextLine();
            a.setNom(nom);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }
    /**
     * Mètode per eliminar una familia donada el seu id
     * @param sc 
     */
    private void showEliminarFamilia(Scanner sc) {
        showObtenirFamilia(sc);
        try {
            tx = session.beginTransaction();
            System.out.println("Dona'm l'idFamilia:");
            int id = sc.nextInt();
            Familia a = getFamilia(id);
            session.delete(a);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }
    /**
     * Mètode per insertar una nova subfamilia amb tots els seus atributs
     * @param sc 
     */
    private void showInsertarSubFamilia(Scanner sc) {
        try {
            tx = session.beginTransaction();
            System.out.println("nom:");
            String nom = sc.nextLine();

            SubFamilia familia = new SubFamilia(nom, null);
            session.save(familia);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }
    /**
     * Mètode que fa un select de tots els atributs d'una subfamilia
     * @param sc 
     */
    private void showObtenirSubFamilia(Scanner sc) {
        List<SubFamilia> subfamilies = new ArrayList<SubFamilia>();
        Query q = session.createQuery("from SubFamilia");

        subfamilies = q.list();

        for (SubFamilia subfamilia : subfamilies) {
            System.out.println(subfamilia);
        }
    }
    /**
     * Mètode per actualitzar tots els atributs d'una subfamilia donada el seu id
     * @param sc 
     */
    private void showActualitzarSubFamilia(Scanner sc) {
        showObtenirSubFamilia(sc);
        try {
            tx = session.beginTransaction();
            System.out.println("Dona'm l'idSubfamilia:");
            int id = sc.nextInt();

            SubFamilia a = getSubFamilia(id);

            System.out.println("nom:");
            sc.nextLine();
            String nom = sc.nextLine();
            a.setNom(nom);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }
    /**
     * Mètode per eliminar una subfamilia donada el seu id
     * @param sc 
     */
    private void showEliminarSubFamilia(Scanner sc) {
        showObtenirSubFamilia(sc);
        try {
            tx = session.beginTransaction();
            System.out.println("Dona'm l'idSubFamilia:");
            int id = sc.nextInt();
            SubFamilia a = getSubFamilia(id);
            session.delete(a);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

}
