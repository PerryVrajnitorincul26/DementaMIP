package com.example.mipexfr;

import com.sun.tools.javac.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.management.remote.JMXConnectorFactory;
import javax.swing.*;

@SpringBootApplication
public class AccessingDataJpaApplication{

    private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    public static void main(String[] args) {
        //SpringApplication.run(AccessingDataJpaApplication.class);
        ConfigurableApplicationContext context = new SpringApplicationBuilder(AccessingDataJpaApplication.class).headless(false).run(args);
        SwingUtilities.invokeLater(()->{
            JFrame MMenu = context.getBean(MainMenuUI.class);
            MMenu.setVisible(true);
        });
    }

    //@Bean
    //public CommandLineRunner demo(AppUserRepo repository) {
    //    return (args) -> {
    //        // save a few customers
    //        repository.save(new AppUser("Jack", "Bauer"));
    //        repository.save(new AppUser("Chloe", "O'Brian"));
    //        repository.save(new AppUser("Kim", "Bauer"));
    //        repository.save(new AppUser("David", "Palmer"));
    //        repository.save(new AppUser("Michelle", "Dessler"));

    //        // fetch all customers
    //        log.info("AppUsers found with findAll():");
    //        log.info("-------------------------------");
    //        for (AppUser customer : repository.findAll()) {
    //            log.info(customer.toString());
    //        }
    //        log.info("");

    //        // fetch an individual customer by ID
    //        AppUser usr = repository.findById(1L);
    //        log.info("AppUser found with findById(1L):");
    //        log.info("--------------------------------");
    //        log.info(usr.toString());
    //        log.info("");

    //        // fetch customers by last name
    //        log.info("AppUser found with findByLastName('Bauer'):");
    //        log.info("--------------------------------------------");
    //        repository.findByUsername("Bauer").forEach(bauer -> {
    //            log.info(bauer.toString());
    //        });
    //        // for (AppUser bauer : repository.findByLastName("Bauer")) {
    //        //  log.info(bauer.toString());
    //        // }
    //        log.info("");
    //    };
    //}

}
