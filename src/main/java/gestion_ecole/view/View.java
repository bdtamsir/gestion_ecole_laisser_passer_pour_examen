package gestion_ecole.view;

import java.util.Scanner;
import lombok.Setter;

public abstract class View {
    @Setter
    protected static Scanner scanner;

    public String askString(String message){
        String str;
        do {
            System.out.println(message);
            str= scanner.nextLine();
        } while (str.isEmpty());
        return str;
    }

    public int askInt(String message){
        int val;
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine();
            try {
                val = Integer.parseInt(input);
                return val;
            } catch(NumberFormatException e) {
                System.out.println("Veuillez entrer un entier.");
            }
        }
    }
}
