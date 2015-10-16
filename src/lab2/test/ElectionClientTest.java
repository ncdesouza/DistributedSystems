package lab2.test;

import lab2.Client.ElectionClient;
import lab2.Server.ElectionServer;
import org.junit.*;

import java.net.MalformedURLException;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static org.junit.Assert.*;

/**
 * ElectionClientTest:
 * <brief description of class>
 */
public class ElectionClientTest {
    static ElectionServer server;
    ElectionClient client;


    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @Before
    public void setUp() throws Exception {
        server = new ElectionServer();
        server.run();
        client = new ElectionClient();
    }

    @After
    public void tearDown() throws Exception {
        client = null;
        server.stop();
        server = null;
    }

    @AfterClass
    public static void tearDownClass() throws Exception {

    }

    @Test
    public void testVote() throws Exception {
        client.vote("Nicholas", 1);
        client.vote("Nicholas", 2);
        client.vote("Bob", 3);
        client.vote("Sam", 4);
        client.vote("Sam", 1);
        client.result();
        System.out.println("\n");
    }

    @Test
    public void testMultiVote() throws Exception {
        CyclicBarrier gate = new CyclicBarrier(5);
        Voter c1 = new Voter(gate, "Nicholas", 1);
        Voter c2 = new Voter(gate, "Bob", 1);
        Voter c3 = new Voter(gate, "Nicholas", 2);
        Voter c4 = new Voter(gate, "Bob", 3);

        c1.start();
        c2.start();
        c3.start();
        c4.start();

        gate.await();


    }

//        ShutdownServer shutdownServer = new ShutdownServer(server);

//        server.stop();
//        server = null;
//        System.gc();
//        shutdownServer.start();
//        voter.start();
//        Vote voter = new Vote(client, "Nicholas", 2);

//        while (shutdownServer.isAlive() || voter.isAlive()) {
    // wait for threads to finish
//            System.out.println("waiting");
//        }


//    @Test
//    public void testResult() throws Exception {
//        client.result();
//    }

    class Voter extends Thread {
        ElectionClient client;
        String candiate;
        int voterNum;
        CyclicBarrier gate;

        Voter(CyclicBarrier gate, String candidate, int voterNum) {
            this.gate = gate;

            try {
                this.client = new ElectionClient();
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
            this.candiate = candidate;
            this.voterNum = voterNum;
        }

        @Override
        public void run() {
            try {
                gate.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            client.vote(candiate, voterNum);
            try {
                client.result();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

//    class ShutdownServer extends Thread {
//        ElectionServer server;
//
//        ShutdownServer(ElectionServer server) {
//            this.server = server;
//        }
//
//        public void run() {
//            try {
//                server.stop();
//                sleep(10000);
//                server.run();
//            } catch (NoSuchObjectException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }

}