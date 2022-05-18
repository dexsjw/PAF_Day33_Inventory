package ibf2021.paf.day33.repository;

import static ibf2021.paf.day33.repository.SQL.*;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2021.paf.day33.model.PO;
import ibf2021.paf.day33.model.POTotal;

@Repository
public class PORepo {

    @Autowired
    private JdbcTemplate template;

    public int insertPo(PO po) {
        KeyHolder key = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_INTO_PO, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, po.getName());
            ps.setString(2, po.getEmail());
            return ps;
        },
        key);
        System.out.println(">>>>>>Key: %d".formatted(key.getKey()));
        return ((BigInteger)key.getKey()).intValue();
    }

    public List<POTotal> getPOTotal() {
        List<POTotal> potList = new ArrayList<>();
        final SqlRowSet rs = template.queryForRowSet(SQL_GET_PO_TOTAL);
        while (rs.next()) {
            potList.add(POTotal.create(rs));
        }
        return potList;
    }
    
}
