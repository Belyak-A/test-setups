package test.setups;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static java.lang.String.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ClassToBeTestedTest {

    @Before
    public void setUp() {
    }

    @Test
    public void loadTestFileFromResources() throws Exception {
        String fileContent = fileFromResourcesAsString("test.json");
        assertEquals("{\n" +
                "  \"fieldName\": 1234\n" +
                "}", fileContent);
    }

    @Test
    public void parseJsonString() {
        String jsonString = "{\n" +
                "  \"fieldName\": 1234\n" +
                "}";
        Map jsonAsMap =  jsonAsMap(jsonString);
        assertEquals(1234., jsonAsMap.get("fieldName"));
    }

    private Map jsonAsMap(String jsonString) {
        return new Gson().fromJson(jsonString, Map.class);
    }

    private String fileFromResourcesAsString(String filePath) throws Exception {
        URL url = this.getClass()
                .getClassLoader()
                .getResource(filePath);
        assertNotNull(format("Test file %s doesn't exist", filePath), url);

        return new String(Files.readAllBytes(Paths.get(url.toURI())));
    }
}