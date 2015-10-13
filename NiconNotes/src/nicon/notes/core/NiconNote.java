/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.notes.core;

import java.io.Serializable;
import java.time.LocalDate;
import nicon.notes.core.util.NiconIO;

/**
 *
 * @author frederick
 */
public class NiconNote implements Serializable{
    
    private int noteID;
    private String title;
    private String note;
    private LocalDate creationDate;
    private Owner author;
    
    /**
     * Este metodo permite crear una nueva NiconNote para ser almacenada dentro
     * de un ListNote, recibe como parámetros el titulo y la nota
     * 
     * @param noteID
     * @param title
     * @param note 
     */
    
    public NiconNote(String title, String note) {
        this.title = title;
        this.note = note;
        creationDate = LocalDate.now();
        author = NiconIO.getInstance().readOwnerFile();
    }

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }
        
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Owner getAuthor() {
        return author;
    }

    public void setAuthor(Owner author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "NiconNote{" + "title=" + title + ", note=" + note + ", creationDate=" + creationDate + ", author=" + author + '}';
    }
       
}
