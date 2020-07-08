package sg.edu.nyp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 *
 * @author klmch
 */
@Stateless
public class ItemBean {
    
    @Resource(name="jdbc/jed_team1")
    private DataSource dsCart;
    
    public double[] getTotals(Map map) {
        Map<Integer, CatalogueRecord> itemMap = new HashMap<Integer, CatalogueRecord>();
        
        double[] totals = new double[2];
        
        if (map != null) {
            itemMap = map;

            for (int key : itemMap.keySet()) {
                CatalogueRecord item = itemMap.get(key);
                
                totals[0] += item.ppu * item.qty;
                totals[1] += item.qty;
            }

        }
        else {
            // cart should check if null
            itemMap = null;
            totals[0] = 0;
            totals[1] = 0;
        }
        
        return totals;
    }
    
    public Map addToCart(Map itemMap, int itemId, int itemQty) {
        CatalogueRecord item = getItem(itemId);
 
        if (itemMap.containsKey(item.id)) {
            CatalogueRecord temp = (CatalogueRecord) itemMap.get(item.id);
            itemMap.put(item.id, temp);
        }
        else {
            item.qty = itemQty;
            itemMap.put(item.id, item);
        }
        return itemMap;    
    }
    
    public CatalogueRecord getItem(int itemID) {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;
        CatalogueRecord item = null;
        
        try {
            connection = dsCart.getConnection();
            prepStatement = connection.prepareStatement("SELECT * FROM catalogue WHERE itemid = ?");
            prepStatement.setInt(1, itemID);
            
            resultSet = prepStatement.executeQuery();
            if (resultSet.next()) {
                item = new CatalogueRecord();
                item.id = resultSet.getInt(1);
                item.name = resultSet.getString(3);
                item.ppu = resultSet.getDouble(4);
                
                int categoryId = resultSet.getInt(2);
                prepStatement = connection.prepareStatement("SELECT * FROM category WHERE id = ?");
                prepStatement.setInt(1, categoryId);
                
                resultSet = prepStatement.executeQuery();
                if (resultSet.next()) {
                    item.category = resultSet.getString(2);
                }
                
                return item;
            }
            
            resultSet.close();
            prepStatement.close();
            connection.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(CartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return item;
    }
    
    public CatalogueRecord allItems(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        CatalogueRecord item = null;
        
        try{
            connection = dsCart.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM catalogue");
            resultset = preparedStatement.executeQuery();
            
            while (resultset.next()) {
                item = new CatalogueRecord();
                item.id = resultset.getInt(1);
                item.name = resultset.getString(3);
                item.ppu = resultset.getDouble(4);
                int categoryId = resultset.getInt(2);
                
                preparedStatement = connection.prepareStatement("SELECT * FROM category WHERE id = ?");
                preparedStatement.setInt(1, categoryId);
                resultset = preparedStatement.executeQuery();
                if (resultset.next()) {
                    item.category = resultset.getString(2);
                }
                
                return item;
            }
            resultset.close();
            preparedStatement.close();
            connection.close();
        }catch (SQLException ex) {
            Logger.getLogger(CartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item;
    }
    
}
