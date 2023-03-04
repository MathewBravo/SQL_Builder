package org.example.views;

public class PostgresInfo implements ApplicationViews {
    String databaseName;

    PostgresInfo(String name){
        this.databaseName = name;
    }

    @Override
    public void init() {
        System.out.println("Postgres Called");
    }
}
