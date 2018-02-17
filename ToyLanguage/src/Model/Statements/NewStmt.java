package Model.Statements;

import Model.Expressions.Expression;
import Model.Expressions.VariableExpression;
import Model.MyException;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Utils.PrgState;

public class NewStmt implements IStmt {
    String var_name;
    Expression exp;

    public NewStmt(String v, Expression e) {
        var_name = v;
        exp = e;
    }

    @Override
    public String toString() {
        return "new(" + var_name + "," + exp + ")";
    }

    @Override
    public PrgState execute(PrgState prg) throws MyException {
        MyIDictionary<String, Integer> dict = prg.getSymTable();
        MyIHeap hp = prg.getHeap();
        try {
            int v = exp.eval(dict, hp);
            int poz = hp.add(v);
            if (dict.isDefined(var_name))
                dict.update(var_name, poz);
            else
                dict.add(var_name, poz);
            return null;
        } catch (MyException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
