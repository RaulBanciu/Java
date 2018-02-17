package sample;

import Controller.MyController;
import Model.MyException;
import Model.Statements.IStmt;
import Model.Utils.*;
import Repository.MyIRepository;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.*;
import java.util.stream.Collectors;

public class RunFormController{
    private MyController controller;
    private PrgState prg1;
    MyIRepository repo1;
    int id;
    IStmt stmt;

    @FXML
    private TableView<Pair<Integer, Integer>> heapTableView;

    @FXML
    private TableColumn<Pair<Integer, Integer>, Integer> heapAddressColumn;

    @FXML
    private TableColumn<Pair<Integer, Integer>, Integer> heapValueColumn;

    @FXML
    private TableView<Pair<Integer, String>> fileTableView;

    @FXML
    private TableColumn<Pair<Integer, String>, Integer> fileIdentifierColumn;

    @FXML
    private TableColumn<Pair<Integer, String>, String> fileNameColumn;

    @FXML
    private TableView<Pair<String, Integer>> symbolTableView;

    @FXML
    private TableColumn<Pair<String, Integer>, String> symbolTableVariableColumn;

    @FXML
    private TableColumn<Pair<String, Integer>, Integer> symbolTableValueColumn;

    @FXML
    private ListView<Integer> outputListView;

    @FXML
    private ListView<Integer> programStateListView;

    @FXML
    private ListView<String> executionStackListView;

    @FXML
    private TextField numberOfProgramStatesTextField;

    @FXML
    private Button executeOneStepButton;

    public Integer index=0;

    public void setController(MyController controller){
        this.controller = controller;
        populateProgramStateIdentifiers();
    }

    public PrgState getCurrentProgram(int id){
        for(PrgState prg : repo1.getPrgList())
            if(prg.getID() == id)
                return prg;
        return null;
    }

    private void populateProgramStateIdentifiers() {
        List<PrgState> programStates = controller.getRepo().getPrgList();
        List<Integer> ids = new ArrayList<Integer>();

        for(PrgState state : programStates){
            ids.add(state.getID());
        }

        programStateListView.setItems(FXCollections.observableArrayList(ids));
        programStateListView.refresh();

        numberOfProgramStatesTextField.setText(programStates.size() + "");
    }

    public void populateHeapTableView(PrgState prg){
        heapTableView.setItems(FXCollections.observableArrayList(controller.getHeapTablePairList()));
    }

    private void populateFileTable(PrgState currentProgramState) {
        fileTableView.setItems(FXCollections.observableArrayList(controller.getFileTablePairList()));
    }
    private void populateSymbolTable(PrgState currentProgramState) {
        symbolTableView.setItems(FXCollections.observableArrayList( controller.getSymTablePairList(index)));
    }

    public void populateOutListView(PrgState prg){
        List<Integer> out = prg.getOut().getOut();

        outputListView.setItems(FXCollections.observableArrayList(out));
        outputListView.refresh();
    }

    private void populateExecutionStack(PrgState currentProgramState) {
        List<String> stack = new ArrayList<>();
        for(IStmt stmt : currentProgramState.getExeStack().getStack()){
            stack.add(stmt.toString());
        }

        executionStackListView.setItems(FXCollections.observableArrayList(stack));
        executionStackListView.refresh();
    }


    public void initialize(){
        symbolTableVariableColumn.setCellValueFactory(new PropertyValueFactory<>("left"));
        symbolTableValueColumn.setCellValueFactory(new PropertyValueFactory<>("right"));
        symbolTableView.getColumns().setAll(symbolTableVariableColumn,symbolTableValueColumn);

        fileIdentifierColumn.setCellValueFactory(new PropertyValueFactory<>("left"));
        fileNameColumn.setCellValueFactory(new PropertyValueFactory<>("right"));
        fileTableView.getColumns().setAll(fileIdentifierColumn,fileNameColumn);

        heapAddressColumn.setCellValueFactory(new PropertyValueFactory<>("left"));
        heapValueColumn.setCellValueFactory(new PropertyValueFactory<>("right"));
        heapTableView.getColumns().setAll(heapAddressColumn,heapValueColumn);


        programStateListView.setOnMouseClicked(mouseEvent -> { changeProgramState(getCurrentProgramState()); });

        executeOneStepButton.setOnAction(actionEvent -> { executeOneStep(); });
    }

    private void executeOneStep() {
        if(controller == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "The program was not selected", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        boolean programStateLeft = getCurrentProgramState().getExeStack().isEmpty();
        if(programStateLeft){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nothing left to execute", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        controller.executeOneStep();

        changeProgramState(getCurrentProgramState());
        populateProgramStateIdentifiers();


    }
    private void changeProgramState(PrgState currentProgramState) {
        if(currentProgramState == null)
            return;
        populateExecutionStack(currentProgramState);
        populateSymbolTable(currentProgramState);
        populateOutListView(currentProgramState);
        populateFileTable(currentProgramState);
        populateHeapTableView(currentProgramState);
    }
    private PrgState getCurrentProgramState(){
        if(programStateListView.getSelectionModel().getSelectedIndex() == -1)
            return null;

        int currentId = programStateListView.getSelectionModel().getSelectedItem();
        return controller.getRepo().getProgramStateWithId(currentId);
    }
}

