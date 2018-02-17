package Model.Statements;

import Model.MyException;
import Model.Utils.MyDictionary;
import Model.Utils.MyStack;
import Model.Utils.PrgState;

import java.util.Map;

public class forkStmt implements IStmt {
    IStmt st;
    public forkStmt(IStmt s){st=s;}

    @Override
    public PrgState execute(PrgState prg) throws MyException {
        PrgState newstate=new PrgState(

                new MyStack(),
                new MyDictionary<>(prg.getSymTable().cloned()),
                prg.getOut(),
                prg.getFileTable(),
                prg.getHeap(),
                st,
                prg.getID()*10

        );
        return newstate;
    }

    @Override
    public String toString() {
        return "fork("+st+")";
    }
}
