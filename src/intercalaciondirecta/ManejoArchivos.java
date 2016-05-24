/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package intercalaciondirecta;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 *
 * @author root
 */
public class ManejoArchivos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int rango;
        int lenSubListas=1;
        Scanner sc=new Scanner(System.in);
        System.out.print("Ingrese el valor: ");
        rango=sc.nextInt();
        ArrayList datos = new ArrayList();
        
        for (int i = 0; i < rango; i++) {
            datos.add(i+1);
        }
        Collections.shuffle(datos);
        MezclaDirecta.crearArchivo(datos,rango);
        
        System.out.println("\n\n\nINTERCALACION DIRECTA CON "+rango+" NUMEROS");
        
        while(lenSubListas<rango){
            MezclaDirecta.repartir(lenSubListas);
            
            System.out.println("\n\n********PASO "+ lenSubListas+"********");
            
            MezclaDirecta.ordenar(lenSubListas);
            MezclaDirecta.mostar(lenSubListas);
            
            lenSubListas*=2;
        }     
    }
}

