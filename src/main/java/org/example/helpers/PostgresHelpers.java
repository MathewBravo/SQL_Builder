package org.example.helpers;

import java.util.*;

public class PostgresHelpers {

    public static HashMap<String, List<String>> parseColumnInfo(String databaseInfo) {
        LinkedHashMap<String, List<String>> columnInfo = new LinkedHashMap<>();
        String[] lines = databaseInfo.split("\\r?\\n");
        for (String line : lines) {
            String[] splitLine = line.split(",");
            if (splitLine.length > 2) {
                List<String> typeWithContraints = new ArrayList<>();
                typeWithContraints.add(splitLine[1]);
                typeWithContraints.addAll(typeAnyConstraintParse(Arrays.asList(splitLine).subList(2, splitLine.length)));
                columnInfo.put(splitLine[0], typeWithContraints);
            } else {
                columnInfo.put(splitLine[0], Arrays.asList(splitLine[1], null, null, null));
            }
        }
        return columnInfo;
    }

    public static List<String> typeAnyConstraintParse(List<String> toParse) {
        List<String> restraints = Arrays.asList(null, null, null);
        for (String param : toParse) {
            if (param.equals("PK")) {
                restraints.set(0, "PK");
            }
            if (param.equals("NOTNULL") || param.equals("NOT NULL")) {
                restraints.set(1, "NOT NULL");
            }
            if (param.equals("UNIQUE")) {
                restraints.set(2, "UNIQUE");
            }
        }
        return restraints;
    }

    public static String buildCreateTableString(LinkedHashMap<String, List<String>> tableInfo, String tableName) {
        StringBuilder creationString = new StringBuilder("CREATE TABLE [IF NOT EXISTS] " + tableName + " (\n");
        creationString.append("\t"+tableName+"_id serial PRIMARY KEY,\n");
        tableInfo.forEach((key, value) -> {
            creationString.append("\t" + key);
            for (String val : value) {
                if(!(val == null)){
                    creationString.append(" " + val);
                }
            }
            creationString.append(",\n");
        });
        String asString = creationString.toString();
        asString = asString.substring(0, asString.length()-2);
        asString += "\n);";
        return asString;
    }
}
