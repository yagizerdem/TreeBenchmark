package CODE;

import DATA.Database;
import GUİ.MainFrame;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Tree tree = Tree.GetInstance(); // initilized


        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new MainFrame();
            }
        });

    }
}