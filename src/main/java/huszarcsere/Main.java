package huszarcsere;

import java.util.*;

public class Main {

    public static String[][] UploadTable() {
        String[][] table = new String[3][3];
        for(int i = 0; i < 3; i++) {
            table[0][i] = "f";						//fehér
        }

        for(int i = 0; i < 3; i++) {
            table[1][i] = "x";						//üres mezők
        }

        for(int i = 0; i < 3; i++) {
            table[2][i] = "s";						//szürke
        }
        return table;
    }

    public static void WriteTable(String[][] table) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void GoodStep(String[][] table, int old_row, int old_column, int new_row, int new_column) {
        if(new_row - old_row == 2 && new_column - old_column == 1) {
            table[new_row][new_column] = table[old_row][old_column];
            table[old_row][old_column] = "x";
        }
        else if(new_row - old_row == 1 && new_column - old_column == 2) {
            table[new_row][new_column] = table[old_row][old_column];
            table[old_row][old_column] = "x";
        }
        else if(new_row - old_row == -2 && new_column - old_column == -1) {
            table[new_row][new_column] = table[old_row][old_column];
            table[old_row][old_column] = "x";
        }
        else if(new_row - old_row == -1 && new_column - old_column == -2) {
            table[new_row][new_column] = table[old_row][old_column];
            table[old_row][old_column] = "x";
        }
        else if(new_row - old_row == -2 && new_column - old_column == 1) {
            table[new_row][new_column] = table[old_row][old_column];
            table[old_row][old_column] = "x";
        }
        else if(new_row - old_row == 2 && new_column - old_column == -1) {
            table[new_row][new_column] = table[old_row][old_column];
            table[old_row][old_column] = "x";
        }
        else if(new_row - old_row == -1 && new_column - old_column == 2) {
            table[new_row][new_column] = table[old_row][old_column];
            table[old_row][old_column] = "x";
        }
        else if(new_row - old_row == 1 && new_column - old_column == -2) {
            table[new_row][new_column] = table[old_row][old_column];
            table[old_row][old_column] = "x";
        }
    }

    public static boolean BadStep(String[][] table, int old_row, int old_column, int new_row, int new_column, String previous, String active) {
        boolean ErrorStep = false;

        if(old_row > 2 || old_column > 2 || old_row < 0 || old_column < 0) {
            ErrorStep = true;
        }
        else if(new_row > 2 || new_column > 2 || new_row < 0 || new_column < 0) {
            ErrorStep = true;
        }
        else if(table[new_row][new_column] == "s" || table[new_row][new_column] == "f"){
            ErrorStep = true;
        }
        else if(table[old_row][old_column] == "x") {
            ErrorStep = true;
        }
        else if(previous == active) {
            ErrorStep = true;
        }

        return ErrorStep;
    }

    public static boolean Goal(String[][] table) {
        boolean TheEnd = false;

        for (int i = 0; i < 3; i++) {
            if (table[0][i] == "s" && table[2][i] == "f") {
                TheEnd = true;
            } else {
                TheEnd = false;
                break;
            }
        }
        return TheEnd;
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub

        //deklarációk
        String[][] GameTable = new String[3][3];
        Scanner sc = new Scanner(System.in);
        int old_row = 0;
        int old_column = 0;
        int new_row = 0;
        int new_column = 0;
        boolean TheEnd = false;
        boolean ErrorStep = false;
        String previous = "x";
        String active;

        GameTable = UploadTable();

        System.out.println("=============");
        System.out.println(" Huszárcsere");
        System.out.println("=============");
        System.out.println();

        System.out.println("Kezdjük el a játékot!");
        System.out.println(">> A cél az, hogy a felső fekete huszárok átkerüljenek a legalsó sorba, a szürke huszárok pedig a felső sorba. ");
        System.out.println(">> Egyelőre csak egy konzolos játékról van szó, ahol 1 és 3 között kell beírnod, melyik huszárt hova szeretnéd "
                + "berakni. Elsőnek a választott huszárt kell ");
        System.out.println("kiválasztanod úgy, hogy a sort, majd az oszlopot adod meg. Ezután ugyanígy a választott helyet kell beütnöd, "
                + "hogy hova akarod tenni");
        System.out.println(">> Ne felejtsd, csak lóugrásban léphetsz, és felváltva kell használnod a huszárokat!");
        System.out.println();

        WriteTable(GameTable);

        while(TheEnd == false) {
            System.out.print("Válassz egy huszárt: ");
            old_row = sc.nextInt();
            old_column = sc.nextInt();
            System.out.println(old_row + ":" + old_column);
            old_row = old_row - 1;
            old_column = old_column -1;
            active = GameTable[old_row][old_column];

            System.out.print("Válassz egy új helyet: ");
            new_row = sc.nextInt();
            new_column = sc.nextInt();
            System.out.println(new_row + ":" + new_column);
            new_row = new_row - 1;
            new_column = new_column -1;

            //ellenőrzések
            ErrorStep = BadStep(GameTable, old_row, old_column, new_row, new_column, previous, active);
            if(ErrorStep == false) {
                //jó lépés
                GoodStep(GameTable,old_row,old_column,new_row,new_column);

                //ellenőrzés - cél állapot-e?
                TheEnd = Goal(GameTable);
            }
            else {
                System.err.println("Hiba, valamelyik szabályt megszegted! Válassz újra!");
            }

            //kiíratás
            System.out.println();
            WriteTable(GameTable);

            previous = active;
        }

        System.out.println("Nyertél!");

    }
}


