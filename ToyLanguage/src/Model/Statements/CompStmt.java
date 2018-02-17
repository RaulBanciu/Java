package Model.Statements;
import Model.Utils.MyIStack;
import Model.Utils.PrgState;

public class CompStmt implements IStmt{
    IStmt first;
    IStmt second;

    public CompStmt(IStmt first, IStmt second){
        this.first  = first;
        this.second = second;
    }

    public String toString() {
        return "("+first.toString() + ";" + second.toString()+")";
    }
    public PrgState execute(PrgState state){
        MyIStack<IStmt> stk=state.getExeStack();
        stk.push(second);
        stk.push(first);
        return null;
    }
}
