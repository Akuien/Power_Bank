package controllers;

import javax.xml.crypto.Data;
import java.util.Scanner;

public class UserInput { // As the title suggests, this is where we keep all of our user input functionality.


        public static Scanner input = new Scanner(System.in);

        // Method for taking integer-type input.
        public static int inputInt(String message){

            System.out.println(message);
            int value = input.nextInt();
            input.nextLine();
            return value;

        }
        // Method that takes String-type input.
        public static String inputString(String message){

            System.out.println(message);
            return input.nextLine();
        }

        // Method that takes double-type input.
        public static double inputDouble(String message){
            System.out.println(message);
            double value = input.nextDouble();
            input.nextLine();
            return value;
        }
        // Method that takes long-type input.

        public static long inputLong(String message){
            System.out.println(message);
            long value = input.nextLong();
            input.nextLine();
            return value;
        }




    }


