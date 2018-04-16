package src;

import java.util.Scanner;

public class InputUtils {
    private static Scanner inputScanner;

    public static Scanner getScanner() {
        return inputScanner;
    }

    public static void init() {
        inputScanner = new Scanner(System.in);
    }

    public static void finish() {
        inputScanner.close();
    }
}
