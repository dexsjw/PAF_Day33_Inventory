package ibf2021.paf.day33.repository;

import static ibf2021.paf.day33.repository.SQL.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2021.paf.day33.model.LineItem;

/* 
Syntax for Java array is using curly braces {} to store array values instead of square brackets []
e.g. {1, 2, 3} not [1, 2, 3]
Some e.g. of declaration and initialization of Java Arrays:
1) int[] intArr = new int[10] -> 10 is the size of array
2) int[] intArr;
    intArr = new int[] {1, 2, 3}; -> correct syntax
    intArr = {1, 2, 3}; -> won't work, syntax error
3) int[] intArr = new int[] {1, 2, 3}
4) int[] intArr = {1, 2, 3}
*/

@Repository
public class LineItemRepo {

    @Autowired
    private JdbcTemplate template;

    public int[] insertLineItem(int ordId, List<LineItem> liList) {
        List<Object[]> batchArgs = liList.stream()
            .map(li -> {
                Object[] args = {li.getProdName(), li.getQuantity(), li.getUnitPrice(), ordId};
                /* // Another way to initialise Object[]
                Object[] args = new Object[4];
                args[0] = li.getProdName();
                args[1] = li.getQuantity();
                args[2] = li.getUnitPrice();
                args[3] = orderId;
                 */
                return args;
            })
            .toList();

/*         // Equivalent to the stream method above
        List<Object[]> batchArgs = new ArrayList<>();
        for (LineItem li: liList) {
            Object[] args = {li.getProdName(), li.getQuantity(), li.getUnitPrice(), orderId};
            batchArgs.add(args);
        }
 */        
        final int[] row = template.batchUpdate(SQL_INSERT_INTO_LINE_ITEM, batchArgs);
        return row;
    }
    
}
