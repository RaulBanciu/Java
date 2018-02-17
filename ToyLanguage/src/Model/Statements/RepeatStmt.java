package Model.Statements;

import Model.Expressions.BooleanExpression;
import Model.Expressions.ConstExpression;
import Model.Expressions.Expression;
import Model.Expressions.NotExpression;
import Model.MyException;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Utils.MyIStack;
import Model.Utils.PrgState;

public class RepeatStmt implements IStmt{
    IStmt s;
    Expression e;

    public RepeatStmt(IStmt s,Expression e){
        this.s=s;
        this.e=e;
    }

    @Override
    public String toString() {
        return "repeat "+s.toString()+" until "+e.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String,Integer> dict=state.getSymTable();
        MyIHeap heap=state.getHeap();
        MyIStack<IStmt> stack=state.getExeStack();
        IStmt n=new CompStmt(
                s,new WhileStmt(new NotExpression(e),s));
        stack.push(n);
        return null;
    }
}
