package encryptdecrypt;

import java.io.*;
import java.util.Scanner;


public class Main {

    public static String encryptCaesar(String s, int n) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (arr[i] >= 'a' && arr[i] <= 'z') {
                arr[i] = (char) (((int) arr[i] + n - 97) % 26 + 97);
            }
            else if (arr[i] >= 'A' && arr[i] <= 'Z') {
                arr[i] = (char) (((int) arr[i] + n - 65) % 26 + 65);
            }
        }
        return String.valueOf(arr);
    }

    public static String decryptCaesar(String s, int n) {
        n = 26 - (n % 26);
        char[] arr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (arr[i] >= 'a' && arr[i] <= 'z') {
                arr[i] = (char) (((int) arr[i] + n - 97) % 26 + 97);
            }
            else if (arr[i] >= 'A' && arr[i] <= 'Z') {
                arr[i] = (char) (((int) arr[i] + n - 65) % 26 + 65);
            }
        }
        return String.valueOf(arr);
    }

    public static String enc(String s, int n) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            arr[i] = (char) (((int) arr[i] + n) % 127);
        }
        return String.valueOf(arr);
    }

    public static String dec(String s, int n) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            arr[i] = (char) (((int) arr[i] - n) % 127);
        }
        return String.valueOf(arr);
    }

    public static void main(String[] args) {
        String data = "";
        String mode = "enc";
        String alg = "shift";
        int n = 0;
        String inFile = null;
        String outFile = null;
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-mode"))
                mode = args[i + 1];
            if (args[i].equals("-data"))
                data = args[i + 1];
            if (args[i].equals("-key"))
                n = Integer.parseInt(args[i + 1]);
            if (args[i].equals("-in"))
                inFile = args[i + 1];
            if (args[i].equals("-out"))
                outFile = args[i + 1];
            if (args[i].equals("-alg"))
                alg = args[i + 1];
        }
        File inF = new File(inFile);
        try {
            Scanner inScanner = new Scanner(inF);
            if (data.equals("")) {
                data = inScanner.nextLine();
            }
            inScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
        if (mode.equals("enc")) {
            if (outFile == null)
                System.out.println(alg.equals("unicode") ? enc(data, n) : encryptCaesar(data, n));
            else {
                File outF = new File(outFile);
                try {
                    FileWriter writer = new FileWriter(outF);
                    writer.write(alg.equals("unicode") ? enc(data, n) : encryptCaesar(data, n));
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error");
                }
            }
        }
        else if (mode.equals("dec")) {
            if (outFile == null)
                System.out.println(alg.equals("unicode") ? dec(data, n) : decryptCaesar(data, n));
            else {
                File outF = new File(outFile);
                try {
                    FileWriter writer = new FileWriter(outF);
                    writer.write(alg.equals("unicode") ? dec(data, n) : decryptCaesar(data, n));
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error");
                }
            }
        }
    }
}