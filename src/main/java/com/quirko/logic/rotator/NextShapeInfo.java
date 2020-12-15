package com.quirko.logic.rotator;

import com.quirko.logic.MatrixOperations;

public final class NextShapeInfo {

    private static int[][] shape;
    private static int position;

    public NextShapeInfo(int[][] s, int p) {
        shape = s;
        position = p;
    }

    public int[][] getShape() {
        return MatrixOperations.copy(shape);
    }

    public int getPosition() {
        return position;
    }

    public static void setShape(int[][] s) {
        shape = s;
    }
}
