package lab2.test;

import lab2.server.ElectionServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * ElectionServerTest:
 * <brief description of class>
 */
public class ElectionServerTest {

    ElectionServer server;

    @Before
    public void setUp() throws Exception {
        server = new ElectionServer();
    }

    @After
    public void tearDown() throws Exception {
        server = null;
    }

    @Test
    public void testRun() throws Exception {
        server.run();
    }
}