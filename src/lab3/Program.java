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
 * Program:
 * <brief description of class>
 */
public class Program {
    public static void main(String[] args) {
        String inputFileName  = "data/lab3/text.in";
        int    repeat         = 1;
        String outputFileName = "data/lab3/program.out";

        //setup the data flow
        MessageQueue<Line> q1 = new MessageQueue<Line>(20);

        FileIterator lines = null;
        try {
            lines = new FileIterator(inputFileName,repeat);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        LineProducer p1 = new LineProducer(lines, q1);
        LineConsumer c1 = null;
        try {
            c1 = new LineConsumer(q1, outputFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // place the producer and consumer in thread containers

        List<Thread> threads = new ArrayList<Thread>();
        threads.add(new Thread(p1));
        threads.add(new Thread(c1));
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
