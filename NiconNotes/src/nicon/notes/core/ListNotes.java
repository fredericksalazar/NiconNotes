/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.notes.core;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import nicon.notes.core.util.NiconIO;

/**
 *
 * @author frederick
 */

public class ListNotes implements Serializable{
    
    private final static long serialVersionUID = 1L;
    
    private int listID;
    private String listName;
    private LocalDate date;
    private List listNotes;
    private NiconIO niconIO;

    public ListNotes(int listID, String listName) {
        this.listID = listID;
        this.listName = listName;
        date = LocalDate.now();
        listNotes = new ArrayList();
    }

    public int getListID() {
        return listID;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List getListNotes() {
        return listNotes;
    }

    public void setListNotes(List listNotes) {
        this.listNotes = listNotes;
    }
    
    
    /**
     * Este método permite agregar una nueva nota dentro del listado de notas
     * actual,recibie un <b> NiconNote </b> y lo agrega al stack
     * @param note
     */
    
    public void addNote(NiconNote note){
        genNoteID();
        listNotes.add(note);
    }
    
    /**
     * Permite eliminar una nota del stack de notas actual, recibe como parametro
     * el int index donde se encuentra la nota dentro del stack
     * @param index 
     */
    
    public void removeNote(int index){
        listNotes.remove(index);
    }
    
    /**
     * Retorna una nota almacenada dentro de la lista de notas
     * @param index
     * @return 
     */
    public NiconNote getNote(int index){
        return (NiconNote) listNotes.get(index);
    }
    
    /**
     * Este metodo permite guardar el listado de notas actual en un archivo de
     * lista de notas dentro del disco duro, para ello hacemos uso de 
     * NiconIO para las operaciones de escritura y lectura.
     */
    
    public void saveList(){
        niconIO.writeListNote(this);
    }

    
    /**
     * Este metodo se encargará de asignar un NoteID a una nota que será creada
     * dentro de la lista de notas, este noteID correspondera a un int aleatorio
     * entre 0 y 1000 y no puede ser repetido en la lista
     */
    private void genNoteID() {
       int noteID = listNotes.size()+1;
    }
    
}
