package lab3.consumer;

import lab3.utility.Line;
import lab3.utility.MessageQueue;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ResultConsumer:
 * <brief description of class>
 */
public class ResultConsumer implements Runnable {
    MessageQueue<Line> q2;
    String outputFileName;
    PrintWriter fileWriter;

    public ResultConsumer(MessageQueue<Line> q2, String outputFileName) throws IOException {
        this.q2 = q2;
        this.outputFileName = outputFileName;
        fileWriter = new PrintWriter(new FileWriter(outputFileName));
    }

    @Override
    public void run(){
        Line line;
        try {
            line = q2.take();
            while (!line.isEnd()) {
                fileWriter.println(line.getContent());
                line = q2.take();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            fileWriter.close();
        }
    }
}
