/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alxa.ub.vista;

import alxa.ub.model.Usuari;
import com.ub.edu.bda.ConnectorHB;
import java.util.ArrayList;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author zenbook
 */
public class PrivaliaUB {
    
    private static final String MAIN_MENU_TITOL = "PRIVALIA UB - ELS MILLORS ARTICLES, AL MILLOR PREU";
    private static final String[] MAIN_MENU_DESC = {"Campanya", "Article", "Família", "Subfamília"};
    private static enum MainMenuOps {CAMPANYA, ARTICLE, FAMILIA, SUBFAMILIA};
    
    private static final String CRUD_MENU_TITOL = "Operacions CRUD";
    private static final String[] CRUD_MENU_DESC = {"Insertar", "Obtenir", "Actualitzar", "Eliminar"};
    private static enum CRUDMenuOps {INSERTAR, OBTENIR, ACTUALITZAR, ELIMINAR};
    
    
    private Session session;
    private Transaction tx;
    
    public static void main(String[] args) {
        System.out.println("Benvingut a Privalia UB");
        Scanner sc = new Scanner(System.in);
        new PrivaliaUB().showLogin(sc);
    }
    
    public PrivaliaUB() {
        session = ConnectorHB.getSession();
        tx = session.beginTransaction();
    }
    
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
            u = (Usuari)q.uniqueResult();
            
            times++;
            
            if(u == null)
                System.out.println("usuari i/o contrasenya incorrectes. Torna a provar-ho.");
            if(times == 3) {
                System.out.println("Ho has provat ja tres vegades. Adeu.");
                System.exit(0);
            }
            
        }while(u == null);
        
        System.out.println("Benvingut "+u.getNom());
        
        showMainMenu(sc);
    }
    
    private void showMainMenu(Scanner sc) {
        Menu<MainMenuOps> mainMenu = new Menu<>(MAIN_MENU_TITOL, MainMenuOps.values(), MAIN_MENU_DESC);
        mainMenu.mostrarMenu();
        switch (mainMenu.getOpcio(sc)) {
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
    }
    
    private void showCRUDMenu(Scanner sc, MainMenuOps type) {
        Menu<CRUDMenuOps> mainMenu = new Menu<>(CRUD_MENU_TITOL, CRUDMenuOps.values(), CRUD_MENU_DESC);
        mainMenu.mostrarMenu();
        switch (mainMenu.getOpcio(sc)) {
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
    }
    
    private void showInsertarArticle(Scanner sc) {
        
    }
    
    private void showObtenirArticle(Scanner sc) {
        
    }
    
    private void showActualitzarArticle(Scanner sc) {
        
    }
    
    private void showEliminarArticle(Scanner sc) {
        
    }
    
    
    
    
    
    
    
    private void showInsertarCampanya(Scanner sc) {
        
    }
    
    private void showObtenirCampanya(Scanner sc) {
        
    }
    
    private void showActualitzarCampanya(Scanner sc) {
        
    }
    
    private void showEliminarCampanya(Scanner sc) {
        
    }
    
    
    
    
    
    
    
    
    
    
    
    private void showInsertarFamilia(Scanner sc) {
        
    }
    
    private void showObtenirFamilia(Scanner sc) {
        
    }
    
    private void showActualitzarFamilia(Scanner sc) {
        
    }
    
    private void showEliminarFamilia(Scanner sc) {
        
    }
    
    
    
    
    
    
    
    
    
    private void showInsertarSubFamilia(Scanner sc) {
        
    }
    
    private void showObtenirSubFamilia(Scanner sc) {
        
    }
    
    private void showActualitzarSubFamilia(Scanner sc) {
        
    }
    private void showEliminarSubFamilia(Scanner sc) {
        
    }
    
    
    
}
