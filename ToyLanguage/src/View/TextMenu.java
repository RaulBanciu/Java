package View;

import Model.MyException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class TextMenu {
    private HashMap<String, Command> commands = new HashMap();

    public TextMenu() {
    }

    public void addCommand(Command c) {
        this.commands.put(c.getKey(), c);
    }

    private void printMenu() {
        Iterator var2 = this.commands.values().iterator();

        while(var2.hasNext()) {
            Command com = (Command)var2.next();
            String line = String.format("%4s : %s", com.getKey(), com.getDescription());
            System.out.println(line);
        }

    }

    public void show() {
        Scanner scanner = new Scanner(System.in);

        try {
            while(true) {
                this.printMenu();
                System.out.printf("Input the option: ");
                String key = scanner.nextLine();
                Command com = (Command)this.commands.get(key);
                if (com != null) {
                    com.execute();
                    throw new MyException();
                }

                System.out.println("Invalid Option");
            }
        } catch (MyException var7) {
            ;
        } finally {
            scanner.close();
        }

    }
}
