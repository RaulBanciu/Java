package Repository;

import Model.Utils.MyIList;
import Model.Utils.PrgState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyRepository implements MyIRepository{
    private List<PrgState> elems = new ArrayList();
    int current = 0;
    private String logFilePath;

    public MyRepository() {
        elems = new ArrayList<>();
        logFilePath = "";
    }

    public MyRepository(String logFilePath){
        elems = new ArrayList<>();
        this.logFilePath = logFilePath;

        PrintWriter writer;
        try{
            writer = new PrintWriter(logFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("Could not open the log file!");
        }
    }


    @Override
    public PrgState getProgramStateWithId(int currentId) {
        for(PrgState pr : elems)
            if(pr.getID() == currentId)
                return pr;
        return null;
    }

    public MyRepository(List<PrgState> programStateList, String logFilePath){
        this.elems = programStateList;
        this.logFilePath = logFilePath;
    }

    @Override
    public List<PrgState> getPrgList() {
        return elems;
    }

    @Override
    public void setPrgList(List prgList) {
        this.elems=prgList;
    }

    public void addPrgState(PrgState prg) {
        this.elems.add(prg);
    }

    public void setLogFilePath(String S) {
        this.logFilePath = S;
    }

    public void logPrgStateExec(PrgState prog) {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
            logFile.println(prog);
            logFile.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
