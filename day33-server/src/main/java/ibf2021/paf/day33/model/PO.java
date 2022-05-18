package ibf2021.paf.day33.model;

import jakarta.json.JsonObject;

public class PO {
    private int ordId;
    private String name;
    private String email;

    public static PO create(JsonObject jo) {
        final PO newPo = new PO();
        try {
            newPo.ordId = jo.getInt("ordId");
        } catch (Exception e) {
            System.out.println(">>>>> There is no ordId for incoming PO");;
        }
        newPo.name = jo.getString("name");
        newPo.email = jo.getString("email");
        return newPo;
    }
    
    public int getOrdId() { return ordId; }
    public void setOrdId(int ordId) { this.ordId = ordId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
}
