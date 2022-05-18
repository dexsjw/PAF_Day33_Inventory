package ibf2021.paf.day33.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class POTotal {

    private int ordId;
    private String name;
    private float total;

    public static POTotal create(SqlRowSet rs) {
        final POTotal pot = new POTotal();
        pot.setOrdId(rs.getInt("ord_id"));
        pot.setName(rs.getString("name"));
        pot.setTotal(rs.getFloat("total"));
        return pot;
    }

    public JsonObject toJsonObj() {
        return Json.createObjectBuilder()
            .add("ordId", getOrdId())
            .add("name", getName())
            .add("total", getTotal())
            .build();
    }

    public int getOrdId() { return ordId; }
    public void setOrdId(int ordId) { this.ordId = ordId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public float getTotal() { return total; }
    public void setTotal(float total) { this.total = total; }
    
}
