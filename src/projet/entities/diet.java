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
public class diet {
    int id;
    String breakfast;
    String lunch;
    String dinner;
    String snacks;
    String calories;

    
    public diet() {
    }

    public diet(int id, String breakfast, String lunch, String dinner, String snacks, String calories) {
        this.id = id;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.snacks = snacks;
        this.calories = calories;
    }

    public int getId() {
        return id;
    }
    
    public String getBreakfast() {
        return breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public String getSnacks() {
        return snacks;
    }

    public String getCalories() {
        return calories;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public void setSnacks(String snacks) {
        this.snacks = snacks;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }
        
}
