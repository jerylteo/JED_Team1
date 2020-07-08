/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nyp;
/**
 *
 * @Yuling User
 */
public class CustomerRecord {
    private int Customerid;
    private String name;
    private String address;
    private int postalcode;
    private int contactnumber;
    private String email;
    
    public CustomerRecord(){}
    
    public int getCusid(){
        return Customerid;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress(){
        return address;
    }
    
    public void setPostalcode(int postalcode) {
        this.postalcode = postalcode;
    }

    public int getPostalcode(){
        return postalcode;
    }
    
    public void setcontactnumber(int contactnumber) {
        this.contactnumber = contactnumber;
    }

    public int getcontactnumber(){
        return contactnumber;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }
}
