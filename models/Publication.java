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
import java.time.LocalDate;
import java.time.LocalTime;
import models.user;

public class Publication {
   public int idPost;
    public String description;
    public int nbLikes;
    public int nbComments;
    public int idUser;
    
    public Publication(int idPublication, String description, int nbLikes, int nbComments, int idUser) {
        this.idPost = idPublication;
        this.description = description;
        this.nbLikes = nbLikes;
        this.nbComments = nbComments;
        this.idUser = idUser;
    }
 

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUser() {
        return idUser;
    }

    public void setUser(int user) {
        this.idUser = user;
    }

    @Override
    public String toString() {
        return "Publication{" + "idPost=" + idPost + ", description=" + description + ", user=" + idUser + '}';
    }


}
