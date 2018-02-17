package sample;

import Controller.MyController;
import Model.Expressions.*;
import Model.Statements.*;
import Model.Utils.PrgState;
import Repository.MyIRepository;
import Repository.MyRepository;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SelectFormController {
    private List<IStmt> programStatements;
    private RunFormController mainWindowController;

    @FXML
    private ListView<String> programListView;

    @FXML
    private Button executeButton;

    public void setMainWindowController(RunFormController mainWindowController){
        this.mainWindowController = mainWindowController;
    }

    private void buildProgramStatements()
    {
        IStmt ex1 = new CompStmt(new OpenRFile("var_f", "test.in"), new CompStmt(new ReadFile(new VariableExpression("var_f"), "var_c"), new CompStmt(new PrintStmt(new VariableExpression("var_c")), new CompStmt(new IfStmt(new VariableExpression("var_c"), new CompStmt(new ReadFile(new VariableExpression("var_f"), "var_c"),new PrintStmt(new VariableExpression("var_c"))), new PrintStmt(new ConstExpression(0))), new CloseRFile(new VariableExpression("var_f"))))));
        IStmt ex2=new CompStmt(new AssignStmt("v",new ConstExpression(10)),new CompStmt(new NewStmt("v",new ConstExpression(20)),new CompStmt(new NewStmt("a",new ConstExpression(22)),new CompStmt(new wH_Stmt("a",new ConstExpression(30)),new CompStmt(new PrintStmt(new VariableExpression("a")),new CompStmt(new PrintStmt(new rH_Exp("a")),new AssignStmt("a",new ConstExpression(0))))))));
        IStmt ex3=new CompStmt(
                new AssignStmt("v",new ConstExpression(10)),new CompStmt(
                new NewStmt("a",new ConstExpression(22)),new CompStmt(new forkStmt(
                new CompStmt(new wH_Stmt("a",new ConstExpression(30)),new CompStmt(
                        new AssignStmt("v",new ConstExpression(32)),new CompStmt(
                        new PrintStmt(new VariableExpression("v")),new PrintStmt(new rH_Exp("a")))))), new CompStmt(
                new PrintStmt(new VariableExpression("v")),new PrintStmt(new rH_Exp("a"))))));
        IStmt ex4=new CompStmt(new AssignStmt("v",new ConstExpression(0)),
                new CompStmt(new RepeatStmt(new CompStmt(
                        new forkStmt(new CompStmt(
                                new PrintStmt(new VariableExpression("v")),
                                new AssignStmt("v",new ArithmeticExpression(new VariableExpression("v"),new ConstExpression(1),2)))),
                        new AssignStmt(
                                "v",
                                new ArithmeticExpression(new VariableExpression("v"),new ConstExpression(1),1))),
                        new BooleanExpression(new VariableExpression("v"),new ConstExpression(3),3)),
                new CompStmt(new AssignStmt("x",new ConstExpression(1)),new CompStmt(
                        new AssignStmt("y",new ConstExpression(2)),new CompStmt(
                                new AssignStmt("z",new ConstExpression(3)),new CompStmt(new AssignStmt("w",new ConstExpression(4)),new PrintStmt(new ArithmeticExpression(new VariableExpression("v"),new ConstExpression(10),3))))))));
        programStatements = new ArrayList<>(Arrays.asList(ex1, ex2, ex3,ex4));
    }

    private List<String> getStringRepresentations(){
        return programStatements.stream().map(IStmt::toString).collect(Collectors.toList());
    }

    public void initialize(){
        buildProgramStatements();
        programListView.setItems(FXCollections.observableArrayList(getStringRepresentations()));

        executeButton.setOnAction(actionEvent -> {
            int index = programListView.getSelectionModel().getSelectedIndex();

            if(index < 0)
                return;

            PrgState initialProgramState = new PrgState(programStatements.get(index));
            MyIRepository repository = new MyRepository("log" + index + ".txt");
            repository.addPrgState(initialProgramState);
            MyController ctrl = new MyController(repository);

            mainWindowController.setController(ctrl);
        });
    }
}
