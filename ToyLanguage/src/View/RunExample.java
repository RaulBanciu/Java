package View;

import Controller.MyController;

public class RunExample extends Command {
    private MyController ctr;

    public RunExample(String key, String desc, MyController ctr) {
        super(key, desc);
        this.ctr = ctr;
    }

    public void execute() {
        this.ctr.allStep();
    }
}
