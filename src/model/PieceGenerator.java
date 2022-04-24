package model;

import java.util.Random;

public class PieceGenerator {

    private static final Random r = new Random();

    public static Tetrominoe generate() {
        return switch (r.nextInt(7)) {
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