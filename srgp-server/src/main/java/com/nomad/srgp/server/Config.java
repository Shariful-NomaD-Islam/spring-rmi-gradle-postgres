package com.nomad.srgp.server;

import com.nomad.srgp.exporter.BookImpl;
import com.nomad.srgp.exporter.PersonImpl;
import com.nomad.srgp.model.Book;
import com.nomad.srgp.model.Person;
import com.nomad.srgp.rmi.BookInterface;
import com.nomad.srgp.rmi.PersonInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.remoting.support.RemoteExporter;

/**
 * @author Shariful Islam
 */
@Configuration
public class Config {

    @Autowired
    private Environment environment;

    @Value("${rmi.host}")
    private String rmiHost;

    @Value("${rmi.port}")
    private Integer rmiPort;

    @Autowired
    private PersonImpl person;

    @Autowired
    private BookImpl book;


    @Bean
    RemoteExporter registerPersonRMIExporter() {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName(PersonInterface.class.getSimpleName());
        exporter.setServiceInterface(PersonInterface.class);
        exporter.setService(person);
//        exporter.setRegistryHost(rmiHost);
        exporter.setRegistryPort(rmiPort);
        return exporter;
    }

    @Bean
    RemoteExporter registerBookRMIExporter() {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName(BookInterface.class.getSimpleName());
        exporter.setServiceInterface(BookInterface.class);
        exporter.setService(book);
//        exporter.setRegistryHost(rmiHost);
        exporter.setRegistryPort(rmiPort);
        return exporter;
    }
}
