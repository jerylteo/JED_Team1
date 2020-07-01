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
    public CatalogueRecord(){}
    
    public int getItemid(String name){
        if(name==itemname){
            return Itemid;
        }
        else{
            return 0;
        }
    }
}
