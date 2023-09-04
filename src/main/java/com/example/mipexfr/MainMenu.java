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
    MainMenu(AppUserRepo repo){
        this.add(new JButton("helpme"));
        this.setSize(new Dimension(600,600));
        repo.save(new AppUser("Good","Night"));
    }
}
