package Model.Statements;

import Model.Expressions.Expression;
import Model.MyException;
import Model.Utils.MyIDictionary;
import Model.Utils.PrgState;

public class AssignStmt implements IStmt {
    String id;
    Expression exp;

    public AssignStmt(String id,Expression exp){
        this.id=id;
        this.exp=exp;
    }

    public String toString(){return id+"="+exp;}

    public PrgState execute(PrgState state){
        MyIDictionary<String,Integer> symTbl= state.getSymTable();
        try
        {int val = exp.eval(symTbl,state.getHeap());

        if (symTbl.isDefined(id))
            symTbl.update(id, val);
        else
            symTbl.add(id,val);

        return null;
        }
        catch (MyException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

