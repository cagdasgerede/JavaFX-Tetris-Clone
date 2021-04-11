package com.quirko.logic.bricks;

import com.quirko.logic.MatrixOperations;

import java.util.ArrayList;
import java.util.List;

final class ChangeBrick implements Brick {

    private final List<int[][]> brickMatrix = new ArrayList<>();

    public ChangeBrick() {
        brickMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {0, 10, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });
    }

    @Override
    public List<int[][]> getShapeMatrix() {
        return MatrixOperations.deepCopyList(brickMatrix);
    }

}
