package Model.Expressions;

import Model.MyException;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;

public class rH_Exp extends Expression {
    String var_name;
    public rH_Exp(String var_name){this.var_name=var_name;}
    @Override
    public int eval(MyIDictionary<String,Integer> tbl, MyIHeap hp) throws MyException{
        if(hp.isDefined(tbl.lookup((var_name)))){
            if(tbl.isDefined(var_name))
                return hp.getVal(tbl.lookup(var_name));
            else throw new MyException("Variable is not defined in SymTable!");
        }
        else throw new MyException("Variable is not defined in heap!");
    }
    @Override
    public String toString(){
        return "rH("+var_name+")";
    }
}
