package model;

public class GameModel {
    private static final int SIZE = 4;
    private final int[][] values = new int[SIZE][SIZE];
    private int score = 0;

    public GameModel() {
        reset(); // начальная инициализация
    }

    public int[][] getValues() {
        return values;
    }

    public void reset() {

        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                values[y][x] = 0;
            }
        }
        score = 0;

        addRandomTile();
        addRandomTile();
    }


    private void addRandomTile() {
        int emptyCount = 0;
        for (int[] row : values) {
            for (int cell : row) {
                if (cell == 0) emptyCount++;
            }
        }

        if (emptyCount == 0) return;

        int target = (int)(Math.random() * emptyCount);
        int counter = 0;

        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (values[y][x] == 0) {
                    if (counter == target) {
                        values[y][x] = Math.random() < 0.9 ? 2 : 4;
                        return;
                    }
                    counter++;
                }
            }
        }
    }

    public int getSize() {
        return SIZE;
    }

    public boolean moveLeft() {
        boolean moved = false;

        for (int[] row : values) {
            boolean compressedBefore = compressRow(row);
            boolean merged = mergeRow(row);
            boolean compressedAfter = compressRow(row);
            if (compressedBefore || merged || compressedAfter) {
                moved = true;
            }
        }

        if (moved) {
            addRandomTile();
        }

        return moved;
    }

    public boolean moveRight() {
        rotate180();
        boolean moved = moveLeft();
        rotate180();
        return moved;
    }

    public boolean moveUp() {
        rotateCounterClockwise();
        boolean moved = moveLeft();
        rotateClockwise();
        return moved;
    }

    public boolean moveDown() {
        rotateClockwise();
        boolean moved = moveLeft();
        rotateCounterClockwise();
        return moved;
    }

    private boolean compressRow(int[] row) {
        boolean changed = false;
        int insertPos = 0;

        for (int i = 0; i < row.length; i++) {
            if (row[i] != 0) {
                if (i != insertPos) {
                    row[insertPos] = row[i];
                    row[i] = 0;
                    changed = true;
                }
                insertPos++;
            }
        }
        return changed;
    }

    private boolean mergeRow(int[] row) {
        boolean merged = false;

        for (int i = 0; i < row.length - 1; i++) {
            if (row[i] != 0 && row[i] == row[i + 1]) {
                row[i] *= 2;
                row[i + 1] = 0;
                merged = true;
                score += row[i];
                i++;
            }
        }
        return merged;
    }

    private void rotateClockwise() {
        int[][] rotated = new int[SIZE][SIZE];

        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                rotated[x][SIZE - 1 - y] = values[y][x];
            }
        }

        for (int y = 0; y < SIZE; y++) {
            System.arraycopy(rotated[y], 0, values[y], 0, SIZE);
        }
    }

    private void rotateCounterClockwise() {
        int[][] rotated = new int[SIZE][SIZE];

        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                rotated[SIZE - 1 - x][y] = values[y][x];
            }
        }

        for (int y = 0; y < SIZE; y++) {
            System.arraycopy(rotated[y], 0, values[y], 0, SIZE);
        }
    }

    private void rotate180() {
        rotateClockwise();
        rotateClockwise();
    }

    public boolean canUserMove() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (values[y][x] == 0) return true;

                // сравниваем с правой соседней плиткой
                if (x < SIZE - 1 && values[y][x] == values[y][x + 1]) return true;

                // сравниваем с нижней соседней плиткой
                if (y < SIZE - 1 && values[y][x] == values[y + 1][x]) return true;
            }
        }
        return false;
    }

    public boolean has2048Tile() {
        for (int[] row : values) {
            for (int cell : row) {
                if (cell == 2048) return true;
            }
        }
        return false;
    }


    public int getScore() {
        return score;
    }
}
