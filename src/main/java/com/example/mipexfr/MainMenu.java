package com.example.mipexfr;
import com.sun.tools.javac.Main;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.*;

@Controller
public class MainMenu extends JFrame {
    MainMenu(){
        this.add(new JButton("helpme"));
    }
}
