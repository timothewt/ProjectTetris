package model;
import java.util.Random;

/**
 * Generates a random piece among the 7 available
 */
public class PieceGenerator {

    private static final Random r = new Random(); // new Random object

    /**
     * Generates a random Tetrominoe
     * @return a Tetrominoe of a certain type
     */
    public static Tetrominoe generate() {
        return switch (r.nextInt(7)) { // Generates a random integer in [0,6] and picks a Tetrominoe
            case 0 -> new LShape();
            case 1 -> new JShape();
            case 2 -> new IShape();
            case 3 -> new OShape();
            case 4 -> new ZShape();
            case 5 -> new SShape();
            case 6 -> new TShape();
            default -> null;
        };
    }
}