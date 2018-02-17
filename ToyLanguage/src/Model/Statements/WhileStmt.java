package Model.Statements;

import Model.Expressions.Expression;
import Model.MyException;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Utils.MyIStack;
import Model.Utils.PrgState;

public class WhileStmt implements IStmt{


    Expression e;
    IStmt s;
    public WhileStmt(Expression ee,IStmt ss){e=ee;s=ss;}

    @Override
    public String toString() {
        return "while("+e.toString()+") "+s.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String,Integer> dict=state.getSymTable();
        MyIHeap heap=state.getHeap();
        MyIStack<IStmt> stack=state.getExeStack();
        if(e.eval(dict,heap)!=0){ stack.push(this);
            stack.push(s);}
        return null;
    }
}
