package user;

import java.util.Scanner;

public class JavaConsoleDelegate {
    public void printToConsole(String text) {
        System.out.println(text);
    }

    public String readFromConsole() {
        Scanner in = new Scanner(System.in);
        return in.next();
    }
}
