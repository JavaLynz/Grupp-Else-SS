public class Translate {
/*
    Skepsarraynyckel.
    0:  Vatten
    1-10: individuella båtar
    11: hit
    12: miss
    13: cant be enemy ship*/
/*
        String s1 = "i shot 5b"; // init
        String s2 = "h shot 7c"; // hit + kordinat
        String s3 = "m shot 6g"; // miss + kordinat
        String s4 = "game over"; // game over
        String s5 = "s shot 5b"; // insänkt + kordinat
*/

    public static String translateReceived(Player player, String recived2) {
        String recived = "m shot 6g";       //////////Ändra när input funkar
        int cordAtoAccess = 0;
        int cordBtoAccess = 0;
        String toTextWindow = "";
        String enemyShotAt = "";

             //   System.out.println(recived);

        if (recived.charAt(0) != 'g') {
            cordAtoAccess = Integer.parseInt(recived.substring(7, 8));
            cordBtoAccess = (recived.charAt(8) - 97);   //a=97
            enemyShotAt = "The enemy shot at " + (recived.substring(7,9)) + "/n";
            player.setEnemyLastX(cordAtoAccess);
            player.setEnemyLastY(cordBtoAccess);
        }

        switch (recived.charAt(0)) {
            case 'i':
                //enemyState = 0;
                break;
            case 'h':
                player.getEnemyBoard().getMap()[player.getLastX()][player.getLastY()] = 11;
                toTextWindow = "... it was a hit. /n" + enemyShotAt ;
                player.setEnemyState(11);
                break;
            case 'm':
                player.getEnemyBoard().getMap()[player.getLastX()][player.getLastY()]= 12;
                toTextWindow = "... it was a miss. /n";
                player.setEnemyState(12);
                break;
            case 'g':
                player.getEnemyBoard().getMap()[player.getLastX()][player.getLastY()] = 11;
                player.setGameOn(false);
                toTextWindow = "... it was a hit, and you sank the final ship!!!";
                break;
            case 's':
                player.getEnemyBoard().getMap()[player.getLastX()][player.getLastY()] = 11;
                player.setEnemyShipsDown((player.getEnemyShipsDown())+1);
                player.setEnemyState(55);
                toTextWindow = "... it was a hit, and you sank a ship. /n";
                break;
        }

        toTextWindow = toTextWindow + enemyShotAt;

/*        System.out.println("cordAtoAccess: " + cordAtoAccess);
        System.out.println("cordBtoAccess: " + cordBtoAccess);
        System.out.println("enemyState: " + enemyState);
        System.out.println("enemyShipsDown: " + enemyShipsDown);
        System.out.println("recivedGameStatus: " + recivedGameStatus);*/

        return toTextWindow;
    }


    public static String translateToSend(Player player) {

        int cordNum = player.getLastX(); // borde kunna ta in direkt.
        int cordLetter = player.getLastY();
        String shot = " shot ";
        String send = "";
        int playerState = 11;

        if (player.getPlayerBoard().getFleet().size()==0) {
            send = "game over";
        } else {
            switch (playerState) {
                case 77:
                    send = "i";
                    break;
                case 11:
                    send = "h";
                    break;
                case 12:
                    send = "m";
                    break;
                case 55:
                    send = "s";
                    break;
            }

            cordLetter=cordLetter+97;
            char c=(char)cordLetter;
            send = send + shot + c  + cordNum;

            //System.out.println(send);
        }

        return send;
    }
}
