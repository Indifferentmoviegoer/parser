import com.jmb.parser.Parser;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ParserTest {

    @Test
    void run() throws IOException {
        Parser parser = new Parser("https://www.simbirsoft.com/");
        parser.run();
    }

    @Test()
    void runError() throws IOException {
        Parser parser = new Parser("sss");
        try {
            parser.run();
        } catch (java.lang.IllegalArgumentException exception) {
            System.out.println("Некорректный url");
        }
    }
}