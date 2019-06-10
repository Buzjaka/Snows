import java.util.Arrays;

public class Application {

    private final static int LINE_LENGTH = 200;

    public static void main(String[] args) {

        while (true) {
            char[] snowLine = new char[LINE_LENGTH];
            int snowflakesQuantityInLine = (int) (Math.random() * (LINE_LENGTH - 1) + 1);
            int[] snowflakesPlaces = new int[snowflakesQuantityInLine];


            for (int i = 0; i < snowflakesQuantityInLine; i++) {
                snowflakesPlaces[i] = (int) (Math.random() * (LINE_LENGTH));

                for (int j = 0; j < snowflakesPlaces.length; j++) {
                    if (i != 0 && snowflakesPlaces[i] == snowflakesPlaces[j]) {
                        snowflakesPlaces[i] = (int) (Math.random() * (LINE_LENGTH));
                    }
                }
            }

            Arrays.sort(snowflakesPlaces);

            int j = 0;
            for (int i = 0; i < snowLine.length; i++) {
                if (i == snowflakesPlaces[j] && j < snowflakesPlaces.length - 1) {
                    snowLine[i] = '*';
                    j++;
                } else if (j != 0 && snowflakesPlaces[j] == snowflakesPlaces[j - 1] && j < snowflakesPlaces.length - 1) {
                    j++;
                } else {
                    snowLine[i] = ' ';
                }
            }

            System.out.println(snowLine);

        }
    }
}
