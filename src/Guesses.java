import java.util.*;

public class Guesses {

    private Random random;
    private Set <String> guessedPositions;
    private Queue<int []> guessCloseby; //Använder kö för att inte gissa random om man fått träff innan.

    public Guesses () {
        this.random = new Random(); //Random gissningar
        this.guessedPositions = new HashSet<>(); //Redan gissade positioner
        this.guessCloseby = new LinkedList<>(); //Om träffat innan, gissa intill
    }

    //Gissar position: närliggande eller random
    public int [] makeAGuess(String playerName) {
        int[] guess;

        //Se i kön om det finns prioriterade gissningar. Om finns
        if (!guessCloseby.isEmpty()) {
            guess = guessCloseby.poll(); //Gör gissning på första elementet i kön
            System.out.println(playerName + " gör taktiskt gissning nära tidigare gissning: Rad + " + guess[0] + ", " + guess[1] );
        } else { //Annars gör slumpmässig gissning.
            guess = makeRandomGuess(playerName);
        }
        //Lägger till gissningen i Set innehållande redan gissade positioner
        guessedPositions.add(guess[0] + "," + guess[1]);
        return guess;
    }


    //Gissar slumpmässig position
    public int[] makeRandomGuess(String playerName) {
        int row;
        int col;
        String guess;

        do {
            row = random.nextInt(10); //Slumpar rad 0-9
            col = random.nextInt(10); //Slumpar column 0-9
            guess = row + "," + col;
        } while (guessedPositions.contains(guess)); //Kontrollerar om gissningen redan gjorts

        System.out.println(playerName + " slump gissade på: Rad " + row + ", " + col);

        return new int[] {row, col};
    }

    // Lägger till i kön de 4 möjliga gissningarna vid en träff
    public void closebyGuesses(int [] positionHit) {
        int row = positionHit[0];
        int col = positionHit[1];

        // Lista över de 4 närliggande positionerna vid träff!
        int [][] nearByPositions = {
                {row - 1, col}, //Ovan gissning
                {row + 1, col}, //Under gissning
                {row, col - 1}, //Vänster om gissning
                {row, col + 1} //Höger om gissning
        };

        for (int [] position : nearByPositions) {
            int nearbyRow = position[0];
            int nearbyCol = position[1];

            if (positionIsValid(nearbyRow, nearbyCol)) {
                String guesses = nearbyRow +","+ nearbyCol;
                if (!guessedPositions.contains(guesses)) {
                    guessCloseby.offer(position);
                    System.out.println("Lägger till närliggande gissnining: Rad " + nearbyRow + "," + nearbyCol);
                }
            }
        }
    }

    //Ser till att gissningarna förblir innanför brädet
    public boolean positionIsValid(int row, int col) {
        return row >= 0 && row < 10 && col >= 0 && col <10;
    }

    //Metod för att återtställa gissningar vid nytt spel
    public void resetGuessesPositions() {
        guessedPositions.clear();
        guessCloseby.clear();
    }

}
