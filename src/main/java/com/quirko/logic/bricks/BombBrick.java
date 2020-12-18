package com.quirko.logic.bricks;

import com.quirko.logic.MatrixOperations;

import java.util.ArrayList;
import java.util.List;

final class BombBrick implements Brick {

    private final List<int[][]> brickMatrix = new ArrayList<>();

    public BombBrick() {
        brickMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 8, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });
    }

    @Override
    public List<int[][]> getShapeMatrix() {
        return MatrixOperations.deepCopyList(brickMatrix);
    }

}
