package Model.Expressions;

import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;

public class ConstExpression extends Expression{
    int number;

    public ConstExpression(int number){this.number=number;}

    @Override
    public int eval(MyIDictionary<String,Integer> dict, MyIHeap heap){return number;}

    public String toString(){return Integer.toString(number);}
}
