import org.example.helpers.Helpers;
import org.example.helpers.PostgresHelpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestHelpers {


    @Test
    void databaseInfoParseTest() {
        String testString = "username,varchar(255),PK,NOT NULL,UNIQUE\npassword,varchar(255)";
        System.out.println(testString);
        HashMap<String, List<String>> test1 = new HashMap<>();
        test1.put("username", Arrays.asList("varchar(255)", "PK", "NOT NULL", "UNIQUE"));
        test1.put("password", Arrays.asList("varchar(255)", null,null,null));
        System.out.println(test1.toString());
        assertEquals(test1, PostgresHelpers.parseColumnInfo(testString), "Expected return:\n");
    }

    @Test
    void databaseColumnVerification(){
        String testStringPass = "username,varchar(255)\npassword,varchar(255)\n";
        String testStringFailEmpty = "";
        String testStringFailInvalidType ="username,beepboop\npassword,varchar(255)";
        String testStringFailInvalidType2 ="username,varchar(255)\npassword,beepboop(255)";
        assertTrue(Helpers.verifyDatabaseColumns(testStringPass), "String was found invalid when it should be fine");
        assertFalse(Helpers.verifyDatabaseColumns(testStringFailEmpty), "An empty string was allowed to pass");
        assertFalse(Helpers.verifyDatabaseColumns(testStringFailInvalidType), "Invalid Type Passed validation when it shouldn't");
        assertFalse(Helpers.verifyDatabaseColumns(testStringFailInvalidType2), "Invalid Type Passed validation when it shouldn't");
    }

    @Test
    void creationString(){
        String shouldEqual = "CREATE TABLE [IF NOT EXISTS] user (\n\tuser_id serial PRIMARY KEY,\n\tuser_name varchar(255) NOT NULL,\n\tpassword varchar(255) NOT NULL\n);";
        String testDBName = "user";
        LinkedHashMap<String, List<String>> databaseInfo = new LinkedHashMap<>();
        databaseInfo.put("user_name", Arrays.asList("varchar(255)", "NOT NULL", null, null));
        databaseInfo.put("password", Arrays.asList("varchar(255)", "NOT NULL", null, null));
        System.out.println(PostgresHelpers.buildCreateTableString(databaseInfo, testDBName));
        assertEquals(shouldEqual,PostgresHelpers.buildCreateTableString(databaseInfo, testDBName), PostgresHelpers.buildCreateTableString(databaseInfo, testDBName));
    }

}
