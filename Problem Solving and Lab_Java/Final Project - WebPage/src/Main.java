import gui.LoginGui;
import user.User;

import java.io.File;
import java.io.*;


public class Main {
    public static void main(String[] args) {
        LoginGui gui = new LoginGui();
        gui.init();
        gui.addListener();
        gui.display();
    }
}