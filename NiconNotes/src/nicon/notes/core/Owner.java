/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.notes.core;

import java.io.Serializable;

/**
 *
 * @author frederick
 */
public class Owner implements Serializable{
    
    private final static long serialVersionUID = 1L;
    public final static String OWNER_FILE_CONFIG = "Owner.conf";
    
    private String name;
    private String lastName;
    private String email;

    public Owner(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Owner{" + "name=" + name + ", lastName=" + lastName + ", email=" + email + '}';
    }
}
