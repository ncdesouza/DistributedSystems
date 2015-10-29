package lab3.consumer;

import lab3.utility.Line;
import lab3.utility.MessageQueue;

import java.io.*;

/**
 * LineConsumer:
 * <brief description of class>
 */
public class LineConsumer implements Runnable {

    MessageQueue<Line> q1;
    MessageQueue<Line> q2;

    PrintWriter fileWriter;

    public LineConsumer(MessageQueue<Line> q1, String outputFileName) throws IOException {
        this.q1 = q1;
        this.q2 = null;
        fileWriter = new PrintWriter(new FileWriter(outputFileName));
    }

    public LineConsumer(MessageQueue<Line> q1, MessageQueue<Line> q2) {
        this.q1 = q1;
        this.q2 = q2;
        fileWriter = null;
    }

    // starts the consumption of strings from q1
    public void run(){
        Line line;
        try {
            line = q1.take();
            while (!line.isEnd()) {
                if (q2 == null) {
                    fileWriter.println(line.getContent());
                } else {
                    q2.put(line);
                }
                line = q1.take();
            }
            if (q2 != null) {
                q2.put(line);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }
}
