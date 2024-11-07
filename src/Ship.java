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

//BEHÖVER LÄGGA TILL SÅ ATT SKEPP INTE PLACERAS PÅ VARANDRA

public class Ship {

    Random random = new Random();

    private String name;
    private int size;
    private boolean isAlive;
    private int[][] position;
    boolean isVertical;


    // Konstruktor
    public Ship(int size) {

        this.size = size;
        giveNameAfterSize();
        this.isAlive = true;
        this.isVertical = random.nextBoolean();
        this.position = new int[2][size];
        setRandomPosition();

        System.out.println(name + " skapad med storlek " + size + " på position ");
        printPositions();
        System.out.println("isVertical=" + isVertical);
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

    public int[][] getPosition() {
        return position;
    }

    public void setPosition(int[][] position) {
        this.position = position;
    }


    // Metoder

    public int[] getRandomPosition() {
        return new int[]{random.nextInt(10), random.nextInt(10)};
    }

    public void setRandomPosition() {
        int[] randomStartPosition = getRandomPosition();
        int startRow = randomStartPosition[0]; // slumpad rad
        int startCol = randomStartPosition[1]; // slumpad kolumn


        //BEHÖVER LÄGGA TILL SÅ ATT SKEPP INTE PLACERAS PÅ VARANDRA

        // Kolla om båten får plats på kartan
        if (isVertical) {
            // Kontrollera Y
            if (startRow + size > 10) {
                startRow = 10 - size;
            }

            // Placera båt vertikalt
            for (int i = 0; i < size; i++) {
                position[0][i] = startRow + i;
                position[1][i] = startCol;
            }
        } else {
            // Kontrollera X
            if (startCol + size > 10) {
                startCol = 10 - size;
            }

            // Placera båt horisontellt
            for (int i = 0; i < size; i++) {
                position[0][i] = startRow;
                position[1][i] = startCol + i;
            }
        }
    }

        public void printPositions(){
            for (int i = 0; i < size; i++) {
                System.out.println("Rad: " + position[0][i] + ", Kolumn: " + position[1][i]);
            }
        }

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

        // toString
        @Override
        public String toString () {
            return "Ship{" +
                    "name='" + name + '\'' +
                    ", size=" + size +
                    ", isAlive=" + isAlive +
                    ", position=" + position +
                    '}';
        }
    }
