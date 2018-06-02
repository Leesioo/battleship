package user;

import java.util.Scanner;

public class JavaConsoleDelegate {
    private Scanner scanner;

    public JavaConsoleDelegate() {
        this.scanner = new Scanner(System.in);
    }

    public void printToConsole(String text) {
        System.out.println(text);
    }

    public String readFromConsole() {
        return scanner.next();
    }
}
