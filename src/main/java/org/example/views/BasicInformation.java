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

import java.awt.*;
import java.io.IOException;

public class BasicInformation implements ApplicationViews{
    Terminal terminal;
    Screen screen;

    ComboBox<String> comboBox = new ComboBox<String>();

    TextBox databaseName;
    BasicInformation(Terminal terminal, Screen screen){
        this.terminal = terminal;
        this.screen = screen;
        comboBox.addItem("Postgres");
        comboBox.addItem("MySQL");
        comboBox.addItem("MongoDB");
    }
    @Override
    public void init() {
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(1));

        // Row 1
        final Label instructions = new Label("Please fill in the following basic information about your database\nbefore continuing");
        panel.addComponent(instructions);

        // Row 2
        Panel databaseTypePanel = new Panel();
        databaseTypePanel.addComponent(comboBox);
        panel.addComponent(databaseTypePanel.withBorder(Borders.singleLine("Type")));

        // Row 3
        databaseName = new TextBox(new TerminalSize(35,1));
        panel.addComponent(databaseName.withBorder(Borders.singleLine("Name")));

        // Row 4
        Button continueButton = new Button("Continue", new Runnable() {
            @Override
            public void run() {
                loadNextPage();
            }
        });
        panel.addComponent(continueButton);

        BasicWindow window = new BasicWindow();
        window.setComponent(panel);
        MultiWindowTextGUI gui = new MultiWindowTextGUI(this.screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
        gui.addWindowAndWait(window);
    }

    private void loadNextPage(){
        switch (comboBox.getSelectedIndex()) {
            case 0 -> {
                PostgresInfo postgresInfo = new PostgresInfo(databaseName.getText());
                postgresInfo.init();
            }
            case 1 -> System.out.println(databaseName.getText());
            case 2 -> System.out.println(databaseName.getText());
        }
    }
}

