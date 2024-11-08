import java.util.Random;

public class Board {

    private int[][] map;
    private Random random = new Random();

    // Konstruktor
    public Board() {
        this.map = new int[10][10];
    }


    // Getters och Setters
    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }



    // Metoder

    public void placeShip(Ship ship) {
        int size = ship.getSize();
        boolean placed = false;

        while (!placed) {
            int startRow = random.nextInt(10);
            int startCol = random.nextInt(10);
            boolean isVertical = random.nextBoolean();

            // Kolla om skeppet får plats
            if (canPlaceShip(startRow, startCol, size, isVertical)) {
                if (isVertical) {
                    for (int i = 0; i < size; i++) {
                        map[startRow + i][startCol] = size; // Vertikal = adderar rader
                    }
                } else {
                    for (int i = 0; i < size; i++) {
                        map[startRow][startCol + i] = size;  // Horisontell = adderar kolumner
                    }
                }
                placed = true;
            }
        }
    }

    // Kolla om skeppet inte placeras för nära andra skepp
    private boolean canPlaceShip(int startRow, int startCol, int size, boolean isVertical) {
        if (isVertical) {
            if (startRow + size > map.length) return false; // Skeppet får inte plats

            // Kolla alla rutor för skeppet och kringliggande rutor
            for (int i = -1; i <= size; i++) {
                for (int j = -1; j <= 1; j++) {
                    int row = startRow + i;
                    int col = startCol + j;
                    if (isWithinBounds(row, col) && map[row][col] != 0) {
                        return false; // Upptagen ruta eller för nära ett annat skepp
                    }
                }
            }
        } else {
            if (startCol + size > map[0].length) return false; // Skeppet får inte plats

            // Kolla alla rutor för skeppet och kringliggande rutor
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= size; j++) {
                    int row = startRow + i;
                    int col = startCol + j;
                    if (isWithinBounds(row, col) && map[row][col] != 0) {
                        return false; // Rutan är upptagen eller för nära annat skepp
                    }
                }
            }
        }
        return true; // Godkänd placering
    }

    // Kolla om position är inom kartan
    private boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < map.length && col >= 0 && col < map[0].length;
    }

    // Skriv ut kartan
    public void printMap() {
        System.out.println("test printMap");
        System.out.print("  ");
        for (int col = 0; col < map[0].length; col++) {
            System.out.print(col + " ");
        }
        System.out.println();

        for (int row = 0; row < map.length; row++) {
            System.out.print((char) ('A' + row) + " ");
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] == 0) {
                    System.out.print("~ ");
                } else {
                    System.out.print(map[row][col] + " ");
                }
            }
            System.out.println();
        }
    }
}
