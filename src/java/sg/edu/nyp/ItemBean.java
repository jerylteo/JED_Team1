package sg.edu.nyp;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;

/**
 *
 * @author klmch
 */
@Stateless
public class ItemBean {
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
}
