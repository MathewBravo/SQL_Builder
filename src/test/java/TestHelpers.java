import org.example.helpers.Helpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TestHelpers {


    @Test
    void databaseInfoParseTest() {
        String testString = "username,varchar(255)\npassword,varchar(255)";
        HashMap<String, String> columnInfo = new HashMap<>();
        columnInfo.put("username", "varchar(255)");
        columnInfo.put("password", "varchar(255)");
        assertEquals(columnInfo, Helpers.parseColumnInfo("username,varchar(255)\npassword,varchar(255)"), "Expected return:\n {username=varchar(255), password=varchar(255)");
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

}
