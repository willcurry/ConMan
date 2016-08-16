import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConsoleUITests {
    private ConMan conMan;
    private InputStream stream;
    private Writer writer;
    private ConsoleUI consoleUI;

    @Before
    public void setUp() {
        stream = new ByteArrayInputStream("Will".getBytes());
        writer = new PrintWriter(System.out);
        consoleUI = new ConsoleUI(stream, writer);
        conMan = new ConMan(consoleUI);
    }
}
