package com.jmb.parser;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    public static final String TEST_HTML = "src/test/resources/example.html";

    @Test
    void parseFromUrl() throws IOException {
        Parser parser = new Parser("https://www.simbirsoft.com/");
        parser.parseFromUrl();
    }

    @Test
    void parseFromUrlError(){
        Parser parser = new Parser("sss");

        assertThrows(IllegalArgumentException.class, parser::parseFromUrl);
    }

    @Test
    void parseFromFile() throws IOException {
        Parser parser = new Parser(TEST_HTML);

        String actual = parser.parseFromFile();
        String expected = "блоке - 1 \nв - 1 \nтекст - 1 \nслово - 15 \nТекст - 1 \n";

        assertEquals(expected, actual);
    }

    @Test
    void parseFromFileError(){
        Parser parser = new Parser("https://www.simbirsoft.com/");

        assertThrows(FileNotFoundException.class, parser::parseFromFile);
    }
}