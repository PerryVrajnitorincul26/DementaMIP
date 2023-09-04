package com.example.mipexfr;
import com.sun.tools.javac.Main;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.awt.*;

@Controller
@SpringBootApplication
public class MainMenu extends JFrame {
    MainMenu(AppUserRepo repo, AppTableRepo repo2){
        this.add(new JButton("helpme"));
        this.setSize(new Dimension(600,600));
        repo.save(new AppUser("Good","Night"));

        AppUser user = new AppUser("salut", "matei");
        AppTable table1 = new AppTable();
        AppTable table2 = new AppTable();
        //user.setTables(table1);
        //user.setTables(table2);

        repo2.save(table1);
    }
}