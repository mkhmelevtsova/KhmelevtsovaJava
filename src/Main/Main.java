package Main;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

        SmartHome smartHome = new SmartHome();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Smart Home");
        System.out.println("Type \"help\" for more info.");
        for (;;) {
            String cmd = scanner.nextLine();
            System.out.println("--------------------");
            smartHome.performCommand(cmd);
            System.out.println("--------------------");
        }
    }
}
