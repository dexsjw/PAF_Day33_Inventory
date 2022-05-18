package ibf2021.paf.day33.model;

import java.util.List;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

public class LineItem {
    private int itemId;
    private String prodName;
    private int quantity;
    private float unitPrice;
    private int ordId;

    public static List<LineItem> createList(JsonArray ja) {
        final List<LineItem> liList = ja.stream()
            .map(liObj -> {
                final LineItem newLi = new LineItem();
                try {
                    newLi.itemId = ((JsonObject)liObj).getInt("itemId");
                } catch (Exception e) {
                   System.out.println(">>>>> There is no itemId for incoming PO");
                }
                newLi.prodName = ((JsonObject)liObj).getString("prodName");
                newLi.quantity = ((JsonObject)liObj).getInt("quantity");
                newLi.unitPrice = (float)((JsonObject)liObj).getJsonNumber("unitPrice").doubleValue();
                return newLi;
            })
            .toList();
        return liList;
    }
    
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public String getProdName() { return prodName; }
    public void setProdName(String prodName) { this.prodName = prodName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public float getUnitPrice() { return unitPrice; }
    public void setUnitPrice(float unitPrice) { this.unitPrice = unitPrice; }

    public int getOrdId() { return ordId; }
    public void setOrdId(int ordId) { this.ordId = ordId; }
}
