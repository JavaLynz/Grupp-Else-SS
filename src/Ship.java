import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Spelarens flotta består av
// ett hangarfartyg (5x1 ruta)
// två slagskepp (4x1 ruta)
// tre kryssare (3x1 ruta)
// fyra ubåtar (2x1 ruta)

//Y = A-J
//X = 0-9

public class Ship {

    Random random = new Random();

    private String name;
    private int size;
    private boolean isAlive;
    private List<int[]> positions;
    private boolean isVertical;
    private int hits;
    private int startRow;
    private int startCol;




    // Konstruktor
    public Ship(int size) {

        this.size = size;
        giveNameAfterSize();
        this.positions = new ArrayList<>();
        this.isAlive = true;
        this.hits = 0;
        this.isVertical = random.nextBoolean();
        this.hits = 0;

        System.out.println(name + " skapad med storlek " + size);
    }

    // Getters och Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public void setVertical(boolean vertical) {
        isVertical = vertical;
    }

    public List<int[]> getPositions() {
        return positions;
    }

    public void setPositions(List<int[]> positions) {
        this.positions = positions;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public void setStartCol(int startCol) {
        this.startCol = startCol;
    }

    public void setOrientation(boolean isVertical){
        this.isVertical = isVertical;
    }

    public void setStartPosition(int startRow, int startCol){
        this.startRow = startRow;
        this.startCol = startCol;
    }


    public int[] getRandomPosition() {
        System.out.println("Size: " + size);
        return new int[]{random.nextInt(10-size), random.nextInt(10-size)};

    }



    // Metoder

    public void setPosition() {
        if(isVertical) {
            for (int i = 0; i < size ; i++) {
                positions.add(new int[]{startRow + i, startCol});
            }
        }
        else {
            for (int i = 0; i < size; i++) {
                positions.add(new int[]{startRow, startCol + i});
            }
        }
    }

public void printPositions() {
    System.out.println(name);
        for(int[] pos : positions) {
            System.out.println("Rad: " + pos[0] + " Kolumn: " + pos[1]);
        }
}

//    public void addPosition(int row, int col) {
//        positions.add(new int[]{row,col});
//    }


//        public void sinkShip(){
//        if(hits == size) {
//            isAlive = false;
//            System.out.println(name + " sank!");
//            // Ta bort skepp från listan fleet ( Flytta denna metod till board?)
//
//        }
//        }

//        public void addHit(){
//            System.out.println("hits++ på " + name + "(Ship.addHit)");
//        hits++;
//        if(hits == size){
//            System.out.println("Sänkt! (Ship.addHit)");
//        }
//        }
//
//        public void hitShip() {
//        hits++;
//            System.out.println(name + " was hit!");
//        }


        public void giveNameAfterSize(){
        switch (size) {
            case 2: this.name = "Ubåt";
            break;
            case 3: this.name = "Kryssare";
            break;
            case 4: this.name = "Slagskepp";
            break;
            case 5: this.name ="Hangarfartyg";
            break;
            default: this.name = "Okänt fartyg";
        }
    }




    @Override
    public String toString() {
        return "Ship{" +
                "random=" + random +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", isAlive=" + isAlive +
                ", positions=" + positions +
                ", isVertical=" + isVertical +
                ", hits=" + hits +
                '}';
    }
}
