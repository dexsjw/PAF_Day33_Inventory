package ibf2021.paf.day33.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2021.paf.day33.model.LineItem;
import ibf2021.paf.day33.model.PO;
import ibf2021.paf.day33.model.POTotal;
import ibf2021.paf.day33.service.InventoryService;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@RestController
@RequestMapping(path="/api/order", produces=MediaType.APPLICATION_JSON_VALUE)
public class PORestController {

    @Autowired
    private InventoryService invSvc;

    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postPO(@RequestBody String payload) {
        JsonObject resp;
        try {
            InputStream is = new ByteArrayInputStream(payload.getBytes());
            JsonReader jr = Json.createReader(is);
            JsonObject purOrd = jr.readObject();
            System.out.println(">>>>> Payload Received");
            JsonArray liArr = purOrd.getJsonArray("lineItems");
            PO po = PO.create(purOrd);
            List<LineItem> liList = LineItem.createList(liArr);
            int ordId = invSvc.createPO(po, liList);
            resp = Json.createObjectBuilder()
                .add("ordId", ordId)
                .build();
        } catch (Exception e) {
            e.printStackTrace();
            resp = Json.createObjectBuilder()
                .add("error", "Couldn't get PO values")
                .build();
        }
        return ResponseEntity.ok(resp.toString());
    }

    @GetMapping(path="/values")
    public ResponseEntity<String> getPOTotal() {
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        List<POTotal> potList = invSvc.getPOTotal();
        potList.stream()
            .map(pot -> pot.toJsonObj())
            .forEach(arrBuilder::add);
        return ResponseEntity.ok(arrBuilder.build().toString());
    }
    
}
