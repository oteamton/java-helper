import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Choose your dice!\n1.Four face dice\n2.Eight face dice");
        try (Scanner scanner = new Scanner(System.in)) {
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    System.out.println("User selected 6 face dice, Bot choose 8 face dice");
                    break;
                case "2":
                    System.out.println("User selected 8 face dice, Bot choose 6 face dice");
                    break;
                default:
                    break;
            }
            int Maxroll = 0;
            int eightfaceDice = 0;
            int fourfaceDice = 0;
            int temp[] = new int[4];
            for (int i = 0; i < 1; i++) {
                fourfaceDice = 1 + (int) (Math.random() * 6);
                temp[i] = fourfaceDice;
                System.out.println(temp[i]);
                for (int j = 0; j < 1; j++) {
                    fourfaceDice = 1 + (int) (Math.random() * 6);
                    temp[j] = fourfaceDice;
                    System.out.println(temp[j]);
                    if (temp[i] > temp[j]) {
                        fourfaceDice = temp[i];
                    } else {
                        fourfaceDice = temp[j];
                    }
                }
            }

            for (int k = 0; k < 4; k++) {
                eightfaceDice = 1 + (int) (Math.random() * 8);
                temp[k] = eightfaceDice;
                if (eightfaceDice > Maxroll) {
                    Maxroll = eightfaceDice;
                }else if (temp[k] > Maxroll) {
                    eightfaceDice = temp[k];
                }else{
                    eightfaceDice = Maxroll;
                }
                System.out.println(eightfaceDice);
            }

            if (fourfaceDice > eightfaceDice) {
                System.out.println("WIN. :) [" + fourfaceDice + " : " + eightfaceDice + "]");
            } else if (fourfaceDice == eightfaceDice) {
                System.out.println("DRAW. :| [" + fourfaceDice + " : " + eightfaceDice + "]");
            } else {
                System.out.println("LOSE. :( [" + fourfaceDice + " : " + eightfaceDice + "]");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
