/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.entities;


/**
 *
 * @author balti
 */
public class workout {
    private int id;
    private int nbr_series;
    private int duree_serie;
    private String body_part;
    private String description;
    private String name;

    public workout() {
    }

    public workout(int id, int nbr_series, int duree_serie, String body_part, String description,String name) {
        this.id = id;
        this.nbr_series = nbr_series;
        this.duree_serie = duree_serie;
        this.body_part = body_part;
        this.description = description;
        this.name = name;
    }

    
    public String getBody_part() {
        return body_part;
    }

    public void setName(String name) {
        this.name = name;
    }

   public String getDescription() {
        return description;
    }

    public int getDuree_serie() {
        return duree_serie;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public int getNbr_series() {
        return nbr_series;
    }

    public void setBody_part(String body_part) {
        this.body_part = body_part;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuree_serie(int duree_serie) {
        this.duree_serie = duree_serie;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setNbr_series(int nbr_series) {
        this.nbr_series = nbr_series;
    }

    @Override
    public String toString() {
        return "workout{" + "id=" + id + ", nbr_series=" + nbr_series + ", duree_serie=" + duree_serie + ", body_part=" + body_part + ", description=" + description + ", name=" + name + '}';
    }


  
    
    
    
}
