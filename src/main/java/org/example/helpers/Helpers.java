package org.example.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Helpers {

    static List<String> postgresDataTypes = Arrays.asList("smallint", "integer", "bigint", "decimal", "numeric", "real", "double", "smallserial", "serial", "bigserial", "money", "character", "text", "bytea", "timestamp", "date", "time ", "interval", "boolean", "point", "line", "lseg", "box", "path", "polygon", "circle", "cidr", "inet", "macaddr", "bit", "varbit", "tsvector", "tsquery", "uuid", "xml", "json", "jsonb", "int4range", "int8range", "numrange", "tsrange", "tstzrange", "daterange", "pg_lsn", "txid_snapshot", "xid", "cid", "oid", "regproc", "regprocedure", "regoper", "regoperator", "regclass", "regtype", "regrole", "regnamespace", "regconfig", "regdictionary", "name", "oidvector", "pg_ndistinct", "pg_dependencies", "pg_shseclabel", "varchar");

    public static boolean verifyDatabaseName(String databaseName) {
        System.out.println("Verify Name: " + databaseName);
        if (databaseName.isEmpty()) {
            return false;
        }
        String trimmedName = databaseName.trim();
        return !trimmedName.contains(" ");
    }

    public static boolean verifyDatabaseColumns(String databaseColumns) {
        if (databaseColumns.isEmpty()) {
            return false;
        }
        String[] lines = databaseColumns.split(System.lineSeparator());
        if (lines.length == 0) {
            return false;
        }
        for (String line : lines) {
            if (!line.contains(",")) {
                return false;
            }
            String[] splitLine = line.split(",");
            if (splitLine.length != 2) {
                return false;
            }
            if (splitLine[1].contains("(")) {
                String type = splitLine[1].substring(0, splitLine[1].indexOf("("));
                if (!postgresDataTypes.contains(type)) {
                    return false;
                }
            }else{
                if(!postgresDataTypes.contains(splitLine[1])){
                    return false;
                }
            }

        }
        return true;
    }

    public static HashMap<String, String> parseColumnInfo(String databaseInfo) {
        //username,varchar(255)
        System.out.println(databaseInfo);
        HashMap<String, String> columnInfo = new HashMap<>();
        String[] lines = databaseInfo.split(System.lineSeparator());
        for (String line : lines) {
            String[] splitNameType = line.split(",");
            columnInfo.put(splitNameType[0], splitNameType[1]);
        }
        return columnInfo;
    }
}
