/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.notes.core;

import java.util.Date;
import nicon.notes.core.util.NiconIO;

/**
 *
 * @author frederick
 */
public class Init {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        NiconIO niconIO = NiconIO.getInstance();
            if(!niconIO.validateOwnerFile()){
                System.out.println("Owner file is not exists");
                niconIO.createOwnerFile(new Owner("Frederick Adolfo","Salazar Sanchez","fredefass01@gmail.com"));
            }else{
                System.out.println("Reading a owner.conf file ...");
                System.out.println(niconIO.readOwnerFile().toString());
//                ListNotes lis = new ListNotes(1,"default");
//                niconIO.writeListNote(lis);
                ListNotes readListNote = niconIO.readListNote("default");
                System.out.println("Total notas en default.lst: "+readListNote.getListNotes().size());
                NiconNote note = new NiconNote("nota de prueba","Esta es una prueba");
//                readListNote.addNote(note);
//                System.out.println("Total notas en default.lst: "+readListNote.getListNotes().size());
//                System.out.println(note.toString());
//                niconIO.writeListNote(readListNote);
            }
    }
    
}
