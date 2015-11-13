package lab3.producer;

import lab3.utility.FileIterator;
import lab3.utility.Line;
import lab3.utility.MessageQueue;
import lab3.utility.Util;

import java.util.Iterator;

/**
 * LineProducer:
 * <brief description of class>
 */
public class LineProducer implements Runnable {
    public FileIterator input;
    public MessageQueue<Line> q1;
    public Util util;

    public LineProducer(FileIterator input, MessageQueue<Line> q1) {
        this.input = input;
        this.q1 = q1;
        util = new Util();
    }

    /** * puts strings from q1*/
    public void run() {
        String queryword = "aute";
        try {
            for (Line anInput : input) {
                StringBuilder builder = new StringBuilder();
                Line line = anInput;
                String[] words = util.words(line.getContent());
                for (String word : words) {
                    if (util.editDistance(queryword, word) < 5)
                        builder.append(word +  " ");
                    anInput.setContent(builder.toString());
                }
                q1.put(anInput);
            }
            q1.put(new Line("END-OF-FILE", -1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}