package org.example.views;


import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Launch implements ApplicationViews {
    Terminal terminal;
    Screen screen;
    BasicWindow  window;

    public Launch(Terminal terminal, Screen screen){
        this.terminal = terminal;
        this.screen = screen;
    }



    @Override
    public void init() {
        Panel panel = new Panel();
        panel.addComponent(new Label("Welcome To The Database Build Tool"));
        Button continueButton =new Button("Continue", new Runnable() {
            @Override
            public void run() {
                System.out.println("Called");
                try {
                    loadNextPage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        panel.addComponent(continueButton);
        window = new BasicWindow();
        window.setComponent(panel);
        MultiWindowTextGUI gui = new MultiWindowTextGUI(this.screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
        gui.addWindowAndWait(window);

    }

    public void loadNextPage() throws IOException {
        screen.clear();
        terminal.clearScreen();
        BasicInformation basicInformation = new BasicInformation(this.terminal, this.screen);
        basicInformation.init();
    }
}
