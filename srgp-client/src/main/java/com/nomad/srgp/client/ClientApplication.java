package com.nomad.srgp.client;

import com.nomad.srgp.model.Person;
import com.nomad.srgp.rmi.BookInterface;
import com.nomad.srgp.rmi.PersonInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import java.util.List;

/**
 * @author Md Shariful Islam
 */
@SpringBootApplication
public class ClientApplication {

    @Autowired
    private Environment environment;

    @Value("${rmi.host}")
    private String rmiHost;

    @Value("${rmi.port}")
    private Integer rmiPort;

    @Bean
    RmiProxyFactoryBean rmiPersonProxy() {
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(PersonInterface.class);
        bean.setServiceUrl("rmi://" + rmiHost + ":" + rmiPort + "/" + PersonInterface.class.getSimpleName());
        return bean;
    }

    @Bean
    RmiProxyFactoryBean rmiBookProxy() {
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(BookInterface.class);
        bean.setServiceUrl("rmi://" + rmiHost + ":" + rmiPort + "/" + BookInterface.class.getSimpleName());
        return bean;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(ClientApplication.class, args);
        PersonInterface personInterface = configurableApplicationContext.getBean(PersonInterface.class);
        BookInterface bookInterface = configurableApplicationContext.getBean(BookInterface.class);

        System.out.println("================ Client Side ========================");
        System.out.println("person-get-basic-info: " + personInterface.getBasicInfo("p1").toString());
        System.out.println("person-name: " + personInterface.getName("p1"));
        personInterface.getAll().forEach( p -> System.out.println( "person-all: " + p.toString()));

        System.out.println("book-full-data: " + bookInterface.getFullData("b1"));
    }

}
