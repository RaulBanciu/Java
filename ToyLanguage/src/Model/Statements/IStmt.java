package Model.Statements;

import Model.MyException;
import Model.Utils.PrgState;

public interface IStmt {
    String toString();
    PrgState execute(PrgState prg) throws MyException;
}
