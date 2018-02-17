package Model.Expressions;

import Model.MyException;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;

public class VariableExpression extends Expression {
    String id;
    public VariableExpression(String id){this.id=id;}
    public int eval(MyIDictionary<String,Integer> dict, MyIHeap heap) throws MyException {
        if(dict.lookup(id)==null)
            throw new MyException("Variable does not exist in SymTable!");
        return dict.lookup(id);}
    public String toString(){return id;}
}
