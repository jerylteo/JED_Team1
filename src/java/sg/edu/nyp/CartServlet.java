/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nyp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.ejb.EJB;

/**
 *
 * @author klmch
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    @EJB
    private ItemBean itemBean;

    @Resource(name="jdbc/jed_team1")
    private DataSource dsCart;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: replace this with EJB implementation once Yu Ling is done with creation
        // retrieve items from sessionStorage
        double[] totals = itemBean.getTotals((Map)request.getSession().getAttribute("itemMap"));
        
        request.getSession().setAttribute("totalPrice", totals[0]);
        request.getSession().setAttribute("totalQty", totals[1]);

        response.sendRedirect(this.getServletContext().getContextPath() + "/cart.jsp");
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        CatalogueRecord item = getItem(Integer.parseInt(request.getParameter("itemId")));   // method to retrieve item details from itemId
        int itemQty = Integer.parseInt(request.getParameter("itemQty"));
        
        Map<Integer, CatalogueRecord> itemMap = new HashMap<Integer, CatalogueRecord>();
        
        if (request.getSession().getAttribute("itemMap") != null) {
            itemMap = (Map) request.getSession().getAttribute("itemMap");
        }
        
        // if Map contains item, just add to quantity instead of adding a new item
        if (itemMap.containsKey(item.id)) {
            CatalogueRecord temp = itemMap.get(item.id);
            itemMap.put(item.id, temp);
        }
        else {
            item.qty = itemQty;
            itemMap.put(item.id, item);
            request.getSession().setAttribute("cartMsg", "Successfully added item to Cart.");   // Diyanah, this is for your shop page.
        }
        
        System.out.println(itemMap.get(item.id).qty);   //debug, check qty
        request.getSession().setAttribute("itemMap", itemMap);
        response.sendRedirect(this.getServletContext().getContextPath() + "/shopping.jsp");
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

    
    @Override
    public String getServletInfo() {
        return "doGet - retrieve cart. doPost - confirmation";
    }// </editor-fold>

}
