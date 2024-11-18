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
        System.out.println("Antal skepp kvar i listan: " + fleet.size());
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

    public Ship hit(int row, int col) {
        for(Ship s : fleet) {
            for(int[] pos : s.getPositions()) {
                if(pos[0] == row && pos[1] == col) {
                    s.setHits(s.getHits() + 1);
                    sinkShip(s);
                    map[row][col] = 11;
                    System.out.println("Träff! " + s.getName() + " på position " + row + ","+ col + "(Board.hit)");
                    
                    return s;
                }
            }
        }
        map[row][col] = 12;
        System.out.println("Miss!");
        return null;
    }

   public void sinkShip(Ship ship) {
        if(ship.getHits() == ship.getSize()) {
            fleet.remove(ship);
            System.out.println(ship.getName() +  " sjönk och blev borttaget från flottan! (Board.sinkShip)");
        }
   }

   public void updateCell(int x, int y, int value) {
        map[x][y] = value;
   }

   public int getCell(int x, int y) {
        return map[x][y];
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
        System.out.println("(Board.printMap)");
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

    @Override
    public String toString() {
        return "Board{" +
                "map=" + Arrays.toString(map) +
                ", random=" + random +
                ", ships=" + fleet +
                '}';
    }
}
