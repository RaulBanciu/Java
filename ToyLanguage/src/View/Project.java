package View;

import Controller.MyController;
import Model.Expressions.*;
import Model.Statements.*;
import Model.Utils.*;
import Repository.MyIRepository;
import Repository.MyRepository;

public class Project {
    public Project() {
    }

    public static void main(String[] args) {
        //IStmt ex1 = new CompStmt(new OpenRFile("var_f", "test.in"), new CompStmt(new ReadFile(new VariableExpression("var_f"), "var_c"), new CompStmt(new PrintStmt(new VariableExpression("var_c")), new CompStmt(new IfStmt(new VariableExpression("var_c"), new CompStmt(new ReadFile(new VariableExpression("var_f"), "var_c"),new PrintStmt(new VariableExpression("var_c"))), new PrintStmt(new ConstExpression(0))), new CloseRFile(new VariableExpression("var_f"))))));
        //IStmt ex1=new CompStmt(new AssignStmt("v",new ConstExpression(10)),new CompStmt(new NewStmt("v",new ConstExpression(20)),new CompStmt(new NewStmt("a",new ConstExpression(22)),new CompStmt(new wH_Stmt("a",new ConstExpression(30)),new CompStmt(new PrintStmt(new VariableExpression("a")),new CompStmt(new PrintStmt(new rH_Exp("a")),new AssignStmt("a",new ConstExpression(0))))))));
        IStmt ex1=new CompStmt(
                new AssignStmt("v",new ConstExpression(10)),new CompStmt(
                    new NewStmt("a",new ConstExpression(22)),new CompStmt(new forkStmt(
                        new CompStmt(new wH_Stmt("a",new ConstExpression(30)),new CompStmt(
                                new AssignStmt("v",new ConstExpression(32)),new CompStmt(
                                        new PrintStmt(new VariableExpression("v")),new PrintStmt(new rH_Exp("a")))))), new CompStmt(
                                                new PrintStmt(new VariableExpression("v")),new PrintStmt(new rH_Exp("a"))))));
        //IStmt ex1=new CompStmt(new OpenRFile("var_f","test.in"),new OpenRFile("var_g","test2.in"));

        PrgState prg1 = new PrgState(new MyStack(), new MyDictionary(), new MyList(), new MyFileTable(),new MyHeap(), ex1,1);
        MyIRepository repo1 = new MyRepository("log1.txt");
        repo1.addPrgState(prg1);
        MyController ctr1 = new MyController(repo1);
        ctr1.setPrintFlag();
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.show();
    }
}
