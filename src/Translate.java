public class Translate {
/*
    Skepsarraynyckel.
    0:  Vatten
    1-10: individuella båtar
    11: hit
    12: miss
    13: cant be enemy ship*/


    public static void main(String[] args) {
        //translateReceived();
        translateToSend();
    }

    public static void translateReceived() {

        String s1 = "i shot 5b"; // init
        String s2 = "h shot 7c"; // hit + kordinat
        String s3 = "m shot 6g"; // miss + kordinat
        String s4 = "game over"; // game over
        String s5 = "s shot 5b"; // insänkt + kordinat
        String received = s4;
        Boolean receivedGameStatus = true;
        int enemyShipsDown = 0;
        int enemyState = 33;
        int cordAToAssess = 0;
        int cordBToAssess = 0;
        String toTextWindow = "";

        System.out.println(received);

        switch (received.charAt(0)) {
            case 'i':
                enemyState = 0;
                break;                      //måste man breaka?
            case 'h':
                enemyState = 11;
                toTextWindow = "... it was a hit. /n";
                break;
            case 'm':
                enemyState = 12;
                toTextWindow = "... it was a miss. /n";
                break;
            case 'g':
                enemyState = 11;
                receivedGameStatus = false;
                toTextWindow = "... it was a hit, and you sank the final ship!!!";
                break;
            case 's':
                enemyState = 11;
                enemyShipsDown = 1;
                toTextWindow = "... it was a hit, and you sank a ship. /n";
                break;
        }

        if (received.charAt(0) != 'g') {
             Integer.parseInt(received.substring(7, 8));
            cordBToAssess = (received.charAt(8) - 97);     //a=97
        }

/*        System.out.println(": " + );
        System.out.println("cordBToAssess: " + cordBToAssess);
        System.out.println("enemyState: " + enemyState);
        System.out.println("enemyShipsDown: " + enemyShipsDown);
        System.out.println("receivedGameStatus: " + receivedGameStatus);*/
    }

    public static void receivedToString() {


    }

    public static void SendToString() {


    }

    public static void translateToSend() {

        Boolean gameOverTrue = false;
/*      Boolean shipDown = false;
        Boolean firstShot = false;
        Boolean hit = false;
        Boolean miss = false;*/
        int cordNum = 9; // borde kunna ta in direkt.
        int cordLetter =9;
       // int[] ourGuess;
        String shot = " shot ";
        String send = "";
        int playerState = 11;

        if (gameOverTrue) {
            send = "game over";
        } else {
            switch (playerState) {
                case 0:
                    send = "i";
                    break; //måste man breaka?
                case 11:
                    send = "h";
                    break;
                case 12:
                    send = "m";
                    break;
                case 88:
                    send = "s";
                    break;
            }

            cordLetter=cordLetter+97;
            char c=(char)cordLetter;
            send = send + shot + c  + cordNum;

            //System.out.println(send);
        }

    }
}
