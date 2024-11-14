import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
Skepsarraynyckel.
    0:  Vatten
    1-10: individuella båtar
    11: hit
    12: miss
    13: cant be enemy ship




*/

public class Board {

    private int[][] map;
    private Random random = new Random();
    private List<Ship> fleet;

    // Konstruktor
    public Board() {
        this.map = new int[10][10];
        for (int i = 0; i < 10 ; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = 0;
            }
        }
        this.fleet = new ArrayList<>();
        createShips();
        placeAllShips();
        printMap();

    }


    // Getters och Setters
    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public List<Ship> getFleet() {
        return fleet;
    }

    public void setFleet(List<Ship> fleet) {
        this.fleet = fleet;
    }



    // Metoder

    public void placeShip(Ship ship) {
        int size = ship.getSize();
        boolean placed = false;

        while(!placed) {
            int startRow = random.nextInt(10);
            int startCol = random.nextInt(10);
            boolean isVertical = random.nextBoolean();

            if (canPlaceShip(startRow, startCol, size, isVertical)) {
                ship.setOrientation(isVertical);
                ship.setStartPosition(startRow, startCol);
                ship.setPosition();

                for (int[] pos : ship.getPositions()) {
                    map[pos[0]][pos[1]] = size;
                }
                placed = true;
                ship.printPositions();
            }
        }
    }



//    public void placeShip(Ship ship) {
//        int size = ship.getSize();
//        boolean placed = false;
//
//        while (!placed) {
//            int startRow = random.nextInt(10);
//            int startCol = random.nextInt(10);
//            boolean isVertical = random.nextBoolean();
//
//            // Kolla om skeppet får plats
//            if (canPlaceShip(startRow, startCol, size, isVertical)) {
//                if (isVertical) {
//                    for (int i = 0; i < size; i++) {
//                        map[startRow + i][startCol] = size; // Vertikal = adderar rader
//                    }
//                } else {
//                    for (int i = 0; i < size; i++) {
//                        map[startRow][startCol + i] = size;  // Horisontell = adderar kolumner
//                    }
//                }
//                placed = true;
//            }
//        }
//    }

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

    public void placeAllShips(){
        for(Ship s : fleet){
            placeShip(s);
        }
    }

    public int checkNumberOfShipsLeft(){
        System.out.println("Antal skepp kvar i listan: " + checkNumberOfShipsLeft());
        return fleet.size();
    }

    public void createShips(){

        fleet.add(new Ship(5));
        fleet.add(new Ship(4));
        fleet.add(new Ship(4));
        fleet.add(new Ship(3));
        fleet.add(new Ship(3));
        fleet.add(new Ship(3));
        fleet.add(new Ship(2));
        fleet.add(new Ship(2));
        fleet.add(new Ship(2));
        fleet.add(new Ship(2));

        System.out.println("\nAlla skepp har skapats och placerats i listan.\n");
    }

//    public List<Ship> getShipList() {
//        System.out.println("List size " + ships.size());
//        return ships;
//    }

    public boolean hit(int row, int col) {
        for(Ship s : fleet) {
            for(int[] pos : s.getPositions()) {
                if(pos[0] == row && pos[1] == col) {
                    s.addHit();
                    map[row][col] = 1;
                    System.out.println("Träff! " + s.getName() + " på position " + row + col);
                    return true;
                }
            }
        }
        map[row][col] = 9;
        return false;

    }


    public void printBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();

        }
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
            System.out.print(row + " ");
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
//    public void printMap() {
//        System.out.println("test printMap");
//        System.out.print("  ");
//        for (int col = 0; col < map[0].length; col++) {
//            System.out.print(col + " ");
//        }
//        System.out.println();
//
//        for (int row = 0; row < map.length; row++) {
//            System.out.print((char) ('A' + row) + " ");
//            for (int col = 0; col < map[row].length; col++) {
//                if (map[row][col] == 0) {
//                    System.out.print("~ ");
//                } else {
//                    System.out.print(map[row][col] + " ");
//                }
//            }
//            System.out.println();
//        }
//    }


    @Override
    public String toString() {
        return "Board{" +
                "map=" + Arrays.toString(map) +
                ", random=" + random +
                ", ships=" + fleet +
                '}';
    }
}
