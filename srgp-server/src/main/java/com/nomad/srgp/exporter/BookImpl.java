package com.nomad.srgp.exporter;

import com.nomad.srgp.model.Book;
import com.nomad.srgp.rmi.BookInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Shariful Islam
 */
@Repository
public class BookImpl implements BookInterface {
    private static final Logger log = LoggerFactory.getLogger(BookImpl.class);

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
    public Book getFullData(String id) {
        log.info("Getting book data for id: {}", id);
        String sql = "SELECT id, name, author_name FROM book WHERE id = ?";

        return jdbc.queryForObject(sql, (rs, i) -> new Book()
                .setId(rs.getString("id"))
                .setName(rs.getString("name"))
                .setAuthorName(rs.getString("author_name"))
        , id);
    }
}
