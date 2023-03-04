package org.example.views;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public interface ApplicationViews {
    public void init();
    public void loadNextPage() throws IOException;

}
