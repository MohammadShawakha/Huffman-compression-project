package rarapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class RarApp {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        readfile rf = new readfile();
        Scanner input = new Scanner(System.in);
        String chose = "";
        boolean stop = false;
        while (!(stop)) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nWelcome to RaR App");
            System.out.println("===================");
            System.out.println("1-Compres File");
            System.out.println("2-unCompres File");
            System.out.println("3-Exit");
            chose = input.nextLine();

            switch (chose) {
                case "1":
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\nEnter file path:");
                    String filePath = input.nextLine();
                    String compresedFilePath = filePath.substring(0, filePath.length() - 4) + "RAR.txt";
                    File compresedFile = new File(compresedFilePath);
                    if (compresedFile.exists() == false) {
                        compresedFile.createNewFile();
                    }
                    rf.compres(filePath, compresedFilePath);
                    System.out.print("\n\n**************************\n");
                    System.out.print("*   Your file is ready   *");
                    System.out.print("\n**************************\n\n");
                    System.out.println("Press Enter to Continue......");
                    input.nextLine();
                    break;
                case "2":
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\nEnter file path:");
                    String filePath2 = input.nextLine();
                    String unCompresedFilePath = filePath2.substring(0, filePath2.length() - 7) + "2.txt";
                    File unCompresedFile = new File(unCompresedFilePath);
                    if (unCompresedFile.exists() == false) {
                        unCompresedFile.createNewFile();
                    }
                    rf.unCompres(filePath2, unCompresedFilePath);
                    System.out.print("\n\n**************************\n");
                    System.out.print("*   Your file is ready   *");
                    System.out.print("\n**************************\n\n");
                    System.out.println("Press Enter to Continue......");
                    input.nextLine();
                    break;
                case "3":
                    stop = true;
                    break;
                default:
                    System.out.println("Wrong Input");
            }
        }

    }
}
