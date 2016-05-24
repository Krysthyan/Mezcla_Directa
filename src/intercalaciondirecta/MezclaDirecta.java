/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intercalaciondirecta;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class MezclaDirecta {

    public MezclaDirecta() {
    }
    
    public static void repartir(int lenSubListas){
        DataOutputStream archivoEscribir1=null;
        DataOutputStream archivoEscribir2=null;
        int alterno=0;
        try {
            String ruta=("src/intercalaciondirecta/");
            DataInputStream archivoLeerPrincipal=new DataInputStream(new FileInputStream(new File(ruta,"archivo.dat")));
            archivoEscribir1 = new DataOutputStream(new FileOutputStream(new File(ruta,"archivoAux1.dat")));
            archivoEscribir2=new DataOutputStream(new FileOutputStream(new File(ruta,"archivoAux2.dat")));
            
            while(archivoLeerPrincipal.available()!=0){
                alterno=0;
                while (alterno<lenSubListas && archivoLeerPrincipal.available()!=0) {                    
                    archivoEscribir1.writeInt(archivoLeerPrincipal.readInt());
                    alterno++;
                }
                alterno=0;
                while (alterno<lenSubListas && archivoLeerPrincipal.available()!=0) { 
                    System.out.println("alterno "+alterno);
                    archivoEscribir2.writeInt(archivoLeerPrincipal.readInt());
                    alterno++;
                }
       
                
                
            }
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                archivoEscribir1.close();
                archivoEscribir2.close();
                
            } catch (IOException ex) {
                Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public static void ordenar(int lenSubListas){
        DataOutputStream archivoEscribirPrincipal=null;
        DataInputStream archivoLeerAux1=null;
        DataInputStream archivoLeerAux2=null;
        try {
            String ruta="src/intercalaciondirecta/";
            archivoEscribirPrincipal = new DataOutputStream(new FileOutputStream(new File(ruta,"archivo.dat")));
            archivoLeerAux1=new DataInputStream(new FileInputStream(new File(ruta,"archivoAux1.dat")));
            archivoLeerAux2=new DataInputStream(new FileInputStream(new File(ruta,"archivoAux2.dat")));
            int datoA;
            int datoB;
            int alternoArchivo1;
            int alternoArchivo2;
            
            while(archivoLeerAux1.available()!=0 && archivoLeerAux2.available()!=0){
                datoA=archivoLeerAux1.readInt();
                datoB=archivoLeerAux2.readInt();
                alternoArchivo1=0;
                alternoArchivo2=0;
                while(alternoArchivo1 < lenSubListas && alternoArchivo2 < lenSubListas){
                    if(datoA<datoB){
                        archivoEscribirPrincipal.writeInt(datoA);
                        alternoArchivo1++;
                        if(archivoLeerAux1.available()==0 || alternoArchivo1>=lenSubListas){
                            archivoEscribirPrincipal.writeInt(datoB);
                            alternoArchivo2++;
                            while(alternoArchivo2<lenSubListas && archivoLeerAux2.available()!=0){
                                archivoEscribirPrincipal.writeInt(archivoLeerAux2.readInt());
                                alternoArchivo2++;
                            }
                            break;
                        }
                        datoA=archivoLeerAux1.readInt();
                    }else{
                        archivoEscribirPrincipal.writeInt(datoB);
                        alternoArchivo2++;
                        if(archivoLeerAux2.available()==0 || alternoArchivo2>=lenSubListas){
                            archivoEscribirPrincipal.writeInt(datoA);
                            alternoArchivo1++;
                            while(alternoArchivo1<lenSubListas && archivoLeerAux1.available()!=0){
                                archivoEscribirPrincipal.writeInt(archivoLeerAux1.readInt());
                                alternoArchivo1++;
                            }
                            break;
                        }
                        datoB=archivoLeerAux2.readInt();
                    }
                    
                }
                
            }
            while(archivoLeerAux1.available() != 0)
                archivoEscribirPrincipal.writeInt(archivoLeerAux1.readInt());
            while(archivoLeerAux2.available() != 0)
                archivoEscribirPrincipal.writeInt(archivoLeerAux2.readInt());
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                archivoEscribirPrincipal.close();
                archivoLeerAux1.close();
                archivoLeerAux2.close();
            } catch (IOException ex) {
                Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static void crearArchivo(ArrayList datos,int rango){
        DataOutputStream archivoEscribir=null;
        try {
            String ruta=("src/intercalaciondirecta/");
            File archivo =new File(ruta,"archivo.dat");
            archivoEscribir= new DataOutputStream(new FileOutputStream(archivo));
            for (int i = 0; i < rango; i++) {
                archivoEscribir.writeInt((int) datos.get(i));
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                archivoEscribir.close();
            } catch (IOException ex) {
                Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public static void mostar(int LenSubLista){
        DataInputStream archivoLeerPrincipal=null;
        DataInputStream archivoLeerAux1=null;
        DataInputStream archivoLeerAux2=null;
        try {
            String ruta="src/intercalaciondirecta/";
            archivoLeerAux1=new DataInputStream(new FileInputStream(new File(ruta,"archivoAux1.dat")));
            archivoLeerAux2=new DataInputStream(new FileInputStream(new File(ruta,"archivoAux2.dat")));
            archivoLeerPrincipal = new DataInputStream(new FileInputStream(new File(ruta,"archivo.dat")));
            System.out.println("\n=====LISTA PRINCIPAL=====");
            int separador=0;
            while(archivoLeerPrincipal.available()!=0){
                System.out.print(archivoLeerPrincipal.readInt()+" ");
                separador++;
                if(separador==LenSubLista){
                    System.out.print(",");
                    separador=0;
                }
                    
                
                
            }
            System.out.println("\n=====LISTA AUX 1=====");
            separador=0;
            while(archivoLeerAux1.available()!=0){
                System.out.print(archivoLeerAux1.readInt()+" ");
                separador++;
                if(separador==LenSubLista){
                    System.out.print(",");
                    separador=0;
                }
                
            }
            System.out.println("\n=====LISTA AUX 2=====");
            separador=0;
            while(archivoLeerAux2.available()!=0){
                System.out.print(archivoLeerAux2.readInt()+" ");
                separador++;
                if(separador==LenSubLista){
                    System.out.print(",");
                    separador=0;
                }
                
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                archivoLeerPrincipal.close();
                archivoLeerAux2.close();
                archivoLeerAux1.close();
            } catch (IOException ex) {
                Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
