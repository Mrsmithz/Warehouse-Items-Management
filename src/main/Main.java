package main;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkContrastIJTheme;
import controller.LoginController;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        startProgram();
    }
    public static void startProgram(){
        try{
            UIManager.setLookAndFeel(new FlatAtomOneDarkContrastIJTheme());
            new LoginController();
        }
        catch(UnsupportedLookAndFeelException e){
            e.printStackTrace();
        }
    }
}
