package Model.Statements;

import Model.Expressions.Expression;
import Model.MyException;
import Model.Utils.MyIList;
import Model.Utils.PrgState;

public class PrintStmt implements IStmt{
    Expression expr;

    public PrintStmt(Expression exp) {
        this.expr = exp;
    }

    public String toString(){
        return "Print(" + expr +")";
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIList<Integer> list = state.getOut();
        list.add(expr.eval(state.getSymTable(),state.getHeap()));
        return null;
    }
}
