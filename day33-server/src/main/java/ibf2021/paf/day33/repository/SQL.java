package ibf2021.paf.day33.repository;

public class SQL {

    public static final String SQL_INSERT_INTO_PO = 
        "insert into po (name, email) values (?, ?)";
    public static final String SQL_INSERT_INTO_LINE_ITEM = 
        "insert into line_item (prod_name, quantity, unit_price, ord_id) values (?, ?, ?, ?)";
    public static final String SQL_GET_PO_TOTAL = 
        "select * from po_total order by ord_id";
        
}
