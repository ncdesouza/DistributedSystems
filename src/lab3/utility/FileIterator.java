package lab3.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * FileIterator:
 * <brief description of class>
 */
public class FileIterator implements Iterable<Line>{
    private BufferedReader fileReader;

    public FileIterator(String filename, int repeat) throws FileNotFoundException {
        fileReader = new BufferedReader(new FileReader(filename));
    }

    protected void finalize() throws Throwable {
        fileReader.close();
    }

    @Override
    public Iterator<Line> iterator() {
        return new Iterator<Line>() {

            private Line line;
            private int count = 0;

            @Override
            public boolean hasNext() {
                try {
                    String str = fileReader.readLine();
                    if (str != null) {
                        line = new Line(str, count);
                        count++;
                        return true;
                    } else {
                        fileReader.close();
                        return false;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Line next() {
                return line;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}