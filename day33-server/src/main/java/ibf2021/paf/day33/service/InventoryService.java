package ibf2021.paf.day33.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ibf2021.paf.day33.model.LineItem;
import ibf2021.paf.day33.model.PO;
import ibf2021.paf.day33.model.POTotal;
import ibf2021.paf.day33.repository.LineItemRepo;
import ibf2021.paf.day33.repository.PORepo;

@Service
public class InventoryService {

    @Autowired
    private PORepo poRepo;

    @Autowired
    private LineItemRepo liRepo;

    @Transactional
    public int createPO(final PO po, final List<LineItem> liList) {
        int ordId = poRepo.insertPo(po);
        liRepo.insertLineItem(ordId, liList);
        return ordId;
    }

    public List<POTotal> getPOTotal() {
        return poRepo.getPOTotal();
    }
    
}
