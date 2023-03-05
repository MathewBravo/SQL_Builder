package org.example.helpers;

import java.util.HashMap;

public class OutputHandler {

    public void tableCreate(String name, HashMap<String, String> columnMap){
        String output = "CREATE TABLE [IF NOT EXISTS] " + name + " (\n";

    }
}
