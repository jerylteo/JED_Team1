/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nyp;
import javax.ejb.Stateless;
/**
 *
 * @Yuling User
 */
@Stateless
public class CatalogueRecord {
    private int Itemid;
    private String itemname;
    private double ppu;
    private int categoryid;
    private String description;
    
    public CatalogueRecord(){}
    
    public int getItemid(){
        return Itemid;
    }
    
    public String getItemname(){
        return itemname;
    }
    
    public int getCategoryid(){
        return categoryid;
    }
    
    public double getppu(){
        return ppu;
    }
    
    public String getDescription(){
        return description;
    }
}
