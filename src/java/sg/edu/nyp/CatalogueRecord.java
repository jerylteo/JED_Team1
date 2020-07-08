/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nyp;

import java.io.Serializable;

public class CatalogueRecord implements Serializable{
    public static final long serialVersionUID = -1L;
    int id;
    String name;
    double ppu;             // this is the price per unit. PPU.
    String category;        // this is linked by catalogue.categoryid to category.id, retrieving 'description' column.
    int qty;                // this ia added on to specify each item's quantity.

    public CatalogueRecord(int id, String name, double ppu, String category, int qty) {
        this.id = id;
        this.name = name;
        this.ppu = ppu;
        this.category = category;
        this.qty = qty;
    }

    public CatalogueRecord() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPpu() {
        return ppu;
    }

    public void setPpu(double ppu) {
        this.ppu = ppu;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
    

}
