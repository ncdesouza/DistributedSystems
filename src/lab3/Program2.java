package lab3;

import lab3.consumer.LineConsumer;
import lab3.producer.LineProducer;
import lab3.utility.FileIterator;
import lab3.utility.Line;
import lab3.utility.MessageQueue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Program2:
 * <brief description of class>
 */
public class Program2 {

    public static void main(String[] args) throws IOException {
        String inputFileName  = "data/lab3/text.in";
        int repeat = 1;
        String outputFileName = "data/lab3/program2.out";
        int    numWorkers     = 100;
        FileIterator lines = null;
        try {
            lines = new FileIterator(inputFileName, 1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<Thread> threads = new ArrayList<Thread>();

        ArrayList<MessageQueue<Line>> msgQs = new ArrayList<MessageQueue<Line>>(numWorkers);

        msgQs.add(new MessageQueue<Line>(20));

        // create the producer thread
        threads.add(new Thread(new LineProducer(lines, msgQs.get(0))));
        // create the LineConsumer threads

        for(int i=0; i < numWorkers; i++) {
            msgQs.add(new MessageQueue<Line>(20));
            threads.add(new Thread(new LineConsumer(msgQs.get(i), msgQs.get(i+1))));
        }
        // create the ResultConsumer threads
        threads.add(new Thread(new LineConsumer(msgQs.get(msgQs.size() - 1), outputFileName)));

        long start = System.currentTimeMillis();

        // start the threads
        for(Thread t : threads) {
            t.start();
        }

        // wait for the threads to complete
        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long duration = System.currentTimeMillis() - start;

        System.out.println("Total Duration: " + duration + " ms.");
    }


}