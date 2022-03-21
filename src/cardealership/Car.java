/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealership;

import java.io.*;


public class Car implements Serializable{
    String SPZ;
    String výrobce;
    String model;
    int rok;
    int km;
    float cena;
    
    Car() {};
    Car(String SPZ, String výrobce, String model, int rok, int km, float cena) {
        this.SPZ = SPZ;
        this.výrobce = výrobce;
        this.model = model;
        this.rok = rok;
        this.km = km;
        this.cena = cena;
    }
    public boolean addNewCar(String SPZ, String výrobce, String model, int rok, int km, float cena) {
        this.SPZ = SPZ;
        this.výrobce = výrobce;
        this.model = model;
        this.rok = rok;
        this.km = rok;
        this.cena = cena;
        return true;
    }

    public String getSPZ() {
        return SPZ;
    }

    public String getMake() {
        return výrobce;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return rok;
    }

    public int getMileage() {
        return km;
    }

    public float getPrice() {
        return cena;
    }
    
    
   
}
