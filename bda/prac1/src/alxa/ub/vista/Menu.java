package alxa.ub.vista;


import java.util.Scanner;

public class Menu<T> {

    private final T[] llistaOpcions;
    private final String titol;
    private final String[] llistaDescripcions;

    public Menu(String titol, T[] llistaOpcions, String[]  llistaDescripcions) {
        this.titol = titol;
        this.llistaOpcions = llistaOpcions;
        this.llistaDescripcions = llistaDescripcions;
    }

    public void mostrarMenu() {
        System.out.println("\n*******************************");
        System.out.println(titol.toUpperCase());
        System.out.println("*******************************\n");
        for (int i = 0; i < getMaxLen(); i++) {
            System.out.println((i+1)+".- "+llistaDescripcions[i]);
        }
    }

    public T getOpcio(Scanner sc) {
        int op = 0;
        boolean sortir = false;
        while(!sortir) {
            System.out.print("\nEscull una opció: ");
            op = sc.nextInt();
            sc.nextLine();
            if(op>getMaxLen() || op<=0) {
                System.err.println("Aquest nombre no està en aquest menú");
            }else{
                sortir = true;
            }
        }
        return llistaOpcions[op-1];
    }

    private int getMaxLen() {
        return llistaOpcions.length;
    }
}