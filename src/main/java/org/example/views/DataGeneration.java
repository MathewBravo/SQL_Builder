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
import org.example.helpers.PostgresHelpers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class DataGeneration implements ApplicationViews {
    Terminal terminal;
    Screen screen;
    String columnInfo;
    String dataBaseName;

    DataGeneration(Terminal terminal, Screen screen, String columnInfo, String dataBaseName) {
        this.terminal = terminal;
        this.screen = screen;
        this.dataBaseName = dataBaseName;
        this.columnInfo = columnInfo;
    }

    @Override
    public void init() {
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(1));
        panel.addComponent(generateTypeForPanel());
        Panel buttonPanel = new Panel(new GridLayout(2));
        Button generateData = new Button("Generate Data", new Runnable() {
            @Override
            public void run() {

            }
        });
        Button skipGenerate = new Button("Skip", new Runnable() {
            @Override
            public void run() {

            }
        });


        BasicWindow window = new BasicWindow();
        window.setComponent(panel);
        MultiWindowTextGUI gui = new MultiWindowTextGUI(this.screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
        gui.addWindowAndWait(window);
    }

    @Override
    public void loadNextPage() throws IOException {

    }

    private Panel generateTypeForPanel(){
        HashMap<String, List<String>> columnParsed = PostgresHelpers.parseColumnInfo(columnInfo);
        Panel generateTypePanel = new Panel();
        generateTypePanel.setLayoutManager(new GridLayout(2));
        // TODO display column info
        return generateTypePanel;
    }
}
