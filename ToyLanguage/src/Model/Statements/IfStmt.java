package Model.Statements;

import Model.Expressions.Expression;
import Model.MyException;
import Model.Utils.MyIStack;
import Model.Utils.PrgState;

public class IfStmt implements IStmt {
    Expression expr;
    IStmt thenBr;
    IStmt elseBr;

    public IfStmt(Expression expr,IStmt thenBr,IStmt elseBr)
    {
        this.expr=expr;
        this.thenBr=thenBr;
        this.elseBr=elseBr;
    }

    public String toString(){return "If "+ expr +" then "+thenBr+" , else " +elseBr;}

    public PrgState execute(PrgState state){
        MyIStack<IStmt> stack=state.getExeStack();
        try {
            if (expr.eval(state.getSymTable(),state.getHeap()) !=0)
                stack.push(thenBr);
            else
                stack.push(elseBr);

            return null;
        }
        catch(MyException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
