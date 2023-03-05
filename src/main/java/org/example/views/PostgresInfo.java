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


public class PostgresInfo implements ApplicationViews {
    Terminal terminal;
    Screen screen;
    String databaseName;
    TextBox databaseInfo;

    Boolean errorShowing = false;


    public PostgresInfo(Terminal terminal, Screen screen, String name) {
        this.terminal = terminal;
        this.screen = screen;
        this.databaseName = name;
    }

    @Override
    public void init() {
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));


        databaseInfo = new TextBox(new TerminalSize(35, 15));
        panel.addComponent(databaseInfo.withBorder(Borders.singleLine(databaseName)));

        Label instructions = new Label("Enter your column names\nand datatype.\n\nUse the following format:\n\n    [COLUMN NAME],[DATATYPE]\n\n    [COLUMN NAME],[DATATYPE]\n\n    [COLUMN NAME],[DATATYPE]\n\n\n\nYou can also add constraints via, ");
        panel.addComponent(instructions);

        Button continueButton = new Button("Continue", () -> {
            if (Helpers.verifyDatabaseColumns(databaseInfo.getText())) {
                try {
                    loadNextPage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                if (!errorShowing) {
                    panel.addComponent(new EmptySpace());
                    panel.addComponent(new Label("Invalid Database Types/Names\nVerify Input Against Example").setForegroundColor(TextColor.ANSI.RED));
                    errorShowing = true;
                }

            }

        });
        panel.addComponent(continueButton);
        BasicWindow window = new BasicWindow();
        window.setComponent(panel);
        MultiWindowTextGUI gui = new MultiWindowTextGUI(this.screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
        gui.addWindowAndWait(window);
    }

    @Override
    public void loadNextPage() throws IOException {
        screen.clear();
        terminal.clearScreen();
        DataGeneration dataGeneration = new DataGeneration(terminal, screen, databaseInfo.getText(), databaseName);
        dataGeneration.init();
    }

}
