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
public class ingredient {
    private int id;
    private String name;
    private String category;
    private String calories_category;

    public ingredient() {
    }

    public ingredient(String name, String category , String calories_category) {
        this.name = name;
        this.category = category;
        this.calories_category = calories_category;
    }
    
    public ingredient(int id, String name, String category, String calories_category) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.calories_category = calories_category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getCalories_category() {
        return calories_category;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCalories_category(String calories_category) {
        this.calories_category = calories_category;
    }
     
}
