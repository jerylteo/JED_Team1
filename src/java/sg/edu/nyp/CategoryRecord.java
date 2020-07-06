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
public class CategoryRecord {
    private int id;
    private String description;
    
    public CategoryRecord(){}
    
    public int getId(){
        return id;
    }
    
    public String getDescription(){
        return description;
    }
}
