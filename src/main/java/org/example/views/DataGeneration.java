package org.example.views;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.*;
import org.example.helpers.Helpers;

import java.io.IOException;


public class DataGeneration implements ApplicationViews {
    Terminal terminal;
    Screen screen;
    String columnInfo;

    DataGeneration(Terminal terminal, Screen screen, String columnInfo) {
        this.terminal = terminal;
        this.screen = screen;
        this.columnInfo = columnInfo;
    }

    @Override
    public void init() {
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));



        BasicWindow window = new BasicWindow();
        window.setComponent(panel);
        MultiWindowTextGUI gui = new MultiWindowTextGUI(this.screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
        gui.addWindowAndWait(window);
    }

    @Override
    public void loadNextPage() throws IOException {

    }
}
