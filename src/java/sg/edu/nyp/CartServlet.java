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

    // hii
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
        
        Map<Integer, CatalogueRecord> itemMap = null;
        
        if (request.getSession().getAttribute("itemMap") != null) {
            itemMap = (Map) request.getSession().getAttribute("itemMap");
            
            itemMap = itemBean.addToCart(itemMap, Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("itemQty")));
            
            request.getSession().setAttribute("cartMsg", "Successfully added item to Cart.");   // Diyanah, this is for your shop page.
        }
        
        request.getSession().setAttribute("itemMap", itemMap);
        response.sendRedirect(this.getServletContext().getContextPath() + "/shopping.jsp");
    }
    
    @Override
    public String getServletInfo() {
        return "doGet - retrieve cart. doPost - confirmation";
    }// </editor-fold>

}
