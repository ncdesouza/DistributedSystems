package asmt1.q4;

import java.io.Serializable;

/**
 * Couple:
 * <brief description of class>
 */
public class Couple implements Serializable {
    private Person one;
    private Person two;

    public Couple() {
        one = null;
        two = null;
    }

    public Couple(Person a) {
        one = a;
        two = null;
    }

    public Couple(Person a, Person b) {
        one = a;
        two = b;
    }

    public Person getOne() {
        return one;
    }

    public void setOne(Person one) {
        this.one = one;
    }

    public Person getTwo() {
        return two;
    }

    public void setTwo(Person two) {
        this.two = two;
    }

    @Override
    public String toString() {
        return "Couple {" + "\n" +
                "one=" + one + ",\n" +
                "two=" + two + "\n" +
                '}';
    }
}
