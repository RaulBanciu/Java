package Model.Statements;

import Model.Expressions.Expression;
import Model.Expressions.VariableExpression;
import Model.MyException;
import Model.Utils.MyIHeap;
import Model.Utils.PrgState;

public class wH_Stmt implements IStmt {
    String var_name;
    Expression exp;

    public wH_Stmt(String var_name,Expression e){
        this.var_name=var_name;
        exp=e;
    }

    @Override
    public PrgState execute(PrgState prg) throws MyException {
        int addr=prg.getSymTable().lookup(var_name);
        int v=exp.eval(prg.getSymTable(),prg.getHeap());
        MyIHeap hp=prg.getHeap();
        if(hp.isDefined(addr)){
            hp.update(addr,v);
            return null;
        }
        else throw new MyException("Invalid address!");
    }

    @Override
    public String toString() {
        return "wH("+var_name+","+exp+")";
    }
}
