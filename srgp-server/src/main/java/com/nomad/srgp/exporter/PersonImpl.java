package com.nomad.srgp.exporter;

import com.nomad.srgp.model.Person;
import com.nomad.srgp.rmi.PersonInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Shariful Islam
 */
@Repository
public class PersonImpl implements PersonInterface {

    private static final Logger log = LoggerFactory.getLogger(PersonImpl.class);

    @Autowired
    @Qualifier("jdbc")
    private JdbcTemplate jdbc;

    private LobHandler lobHandler;
    private LobCreator lobCreator;
    private Lock dongleRegisterLock;

    @PostConstruct
    private void init() {
        lobHandler = new DefaultLobHandler();
        lobCreator = lobHandler.getLobCreator();
        dongleRegisterLock = new ReentrantLock();
    }

    @Override
    @Transactional("pgsqlTxManager")
    public boolean create(Person p) {
        //TODO
        return false;
    }

    @Override
    public Person getBasicInfo(String id) {
        log.info("Getting person data for id: {}", id);
        String sql = "SELECT id, name, address FROM person WHERE id = ?";

        return jdbc.queryForObject(sql, (rs, i) -> new Person()
                .setId(rs.getString("id"))
                .setName(rs.getString("name"))
                .setAddress(rs.getString("address"))
        , id);
    }

    @Override
    public String getName(String id) {
        log.info("Getting person-name for id: {}", id);
        String sql = "SELECT name FROM person WHERE id = ?";
        return jdbc.queryForObject(sql, String.class, id);
    }

    @Override
    public List<Person> getAll() {
        log.info("Getting person list");
        String sql = "SELECT id, name, address FROM person";
        return jdbc.query(sql, (rs, i) -> new Person()
                .setId(rs.getString("id"))
                .setName(rs.getString("name"))
                .setAddress(rs.getString("address")));
    }
}
