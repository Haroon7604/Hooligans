/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author mouhi
 */
public class Commentaire {
    private int id_comment;
    private String description;
    private int id_user;
    private int Publication;

    public Commentaire(int id_comment, int id_user, int Publication) {
        this.id_comment = id_comment;
        this.description = description;
        this.id_user = id_user;
        this.Publication = Publication;
    }

    public int getId_comment() {
        return id_comment;
    }

    public void setId_comment(int id_comment) {
        this.id_comment = id_comment;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getPublication() {
        return Publication;
    }

    public void setPublication(int Publication) {
        this.Publication = Publication;
    }


  
    
}
