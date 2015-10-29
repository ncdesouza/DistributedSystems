package lab3.utility;

/**
 * Line:
 * <brief description of class>
 */
public class Line {
    private String content;
    private long lineNumber;

    public Line(String content, long lineNumber) {
        this.content = content;
        this.lineNumber = lineNumber;
    }

    //TODO: Validate correctness
    public boolean isEnd() {
        return lineNumber < 0;
    }

    public String getContent() {
        return content;
    }
}
