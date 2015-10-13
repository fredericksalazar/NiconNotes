/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.notes.core.util;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import nicon.notes.core.ListNotes;
import nicon.notes.core.NiconNote;
import nicon.notes.core.Owner;

/**
 * Esta clase creará un único objeto que tendrá como finalidad poder hacer las 
 * operaciones de escritura y lectora de archivos, administrará todas las
 * operaciones en disco y se encargará de crear los archivos NiconNotes.note
 * 
 * @author frederick
 */

public class NiconIO {
    
    private static NiconIO instance;
    
    private FileInputStream fis;
    private FileOutputStream fos;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    
    private String pathNotes;
    private String pathConfig;
    private String pathOwnerFile;
    
    private boolean state;
    
    private File filer;    
    private Owner author;
    private NiconNote niconNote;
    private ListNotes listNote;
    
    private NiconIO(){
        pathNotes = "./Notes";
        pathConfig = "./Config";
    }

    
    public String getPathNotes() {
        return pathNotes;
    }

    public void setPathNotes(String pathNotes) {
        this.pathNotes = pathNotes;
    }

    public String getPathConfig() {
        return pathConfig;
    }

    public void setPathConfig(String pathConfig) {
        this.pathConfig = pathConfig;
    }
    
        
    /**
     * Valida la existencia de los diferentes directorios que componen la estruc
     * ura básica de configuracion, en caso de no encontrar las carpetas basicas
     * deberá enviar una notificación y crear la nueva estructura de directorios
     * 
     * @return 
     */
    
    public boolean validatePathConfig(){
        System.out.println("NiconIO: validating directory path config ...");
            filer = new File(pathConfig);
        return filer.exists();
    }
    
    
    /**
     * Crea el directorio de configuración en caso de ser necesario.
     */
    
    public void createConfigPath(){
        filer = new File(pathConfig);
        filer.mkdir();
    }
    
    
    /**
     * Valida si el directorio de almacenamiento de Notas se encuentra disponible
     * @return boolean state
     */  
    
    public boolean validatePathNote(){
        System.out.println("NiconIO: validating directory path notes ...");
            filer = new File(pathNotes);
        return filer.exists();
    }
    
    
    /**
     * Crea el directorio de notas en caso de no existir
     */
    
    public void createNotesPath(){
        filer = new File(pathNotes);
        filer.mkdir();
    }
    
    
    /**
     * Este metodo valida que el archivo de configuración de author y propietario
     * exista dentro del directorio de configuraciones.
     * @return 
     */
    
    public boolean validateOwnerFile(){        
        if(validatePathConfig()){
            filer = new File(pathConfig+"/"+Owner.OWNER_FILE_CONFIG);
            state = filer.exists();
        }        
        return state;
    }
    
    
    /**
     * Este metodo permite crear el archivo básico de configuración en caso de 
     * que este no exista
     * @param owner 
     */
    public void createOwnerFile(Owner owner){
        if(!validateOwnerFile()){ 
            createConfigPath();
            try {
                System.out.println("NiconIO: creating onwer.conf");
                filer = new File(pathConfig+"/"+Owner.OWNER_FILE_CONFIG);
                fos = new FileOutputStream(filer);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(owner);
                oos.close();
                fos.close();
                System.out.println("NiconIO: owner.conf has created ...");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NiconIO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NiconIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    /**
     * Este metodo permite leer el archivo básico de configuración en caso de 
     * owner.conf en caso de existir
     * @return 
     */
    public Owner readOwnerFile(){
        if(validateOwnerFile()){
            try{
               System.out.println("NiconIO: reading Owner.conf ...");
               fis = new FileInputStream(pathConfig+"/"+Owner.OWNER_FILE_CONFIG);
               ois = new ObjectInputStream(fis);
               author = (Owner) ois.readObject();
               fis.close();
               ois.close();
            }catch(IOException | ClassNotFoundException e){
               System.out.println(e);
            }
        }
        return author;
    }
    
    
    
    /**
     * Este metodo permite crear dentro del disco duro un objeto de tipo
     * ListNote para ser recuperado posteriormente
     * @param list 
     */
    
    public void writeListNote(ListNotes list){
        if(validatePathNote()){
            try {
                System.out.println("NiconIO: escribiendo lista en disco");
                filer = new File(pathNotes+"/"+list.getListName()+".lst");
                fos = new FileOutputStream(filer);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(list);
                oos.close();
                fos.close();
                System.out.println("NiconIO: La lista ha sido escrita correctamente");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NiconIO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NiconIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    /**
     * Permite leer un archivo de lista de notas almacenado en el disco, recibe
     * como parametros el nombre de la lista a leer y retorna un objeto ListNotes
     * con el objeto recuperado
     * 
     * @param nameList
     * @return 
     */
    
    public ListNotes readListNote(String nameList){
        try{
            if(validatePathNote()){
               System.out.println("NiconIO: reading ListNote: "+nameList+".lst");
               fis = new FileInputStream(pathNotes+"/"+nameList+".lst");
               ois = new ObjectInputStream(fis);
               listNote = (ListNotes) ois.readObject();
               fis.close();
               ois.close(); 
            }else{
               JOptionPane.showMessageDialog(null, "Directorio no exise");
            }
        }catch(IOException | ClassNotFoundException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Ocurrio el siguiente Error, NO se puede leer la lista:\n"+e);
            Logger.getLogger(NiconIO.class.getName()).log(Level.SEVERE, null, e);
        }
        return listNote;
    }
    
        
    public static NiconIO getInstance(){
        if(instance==null){
            instance = new NiconIO();
        }
        return instance;
    }
    
}
