package org.example;


import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.example.views.BasicInformation;
import org.example.views.Launch;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();
        Launch launch = new Launch(terminal, screen);
        launch.init();
    }

}