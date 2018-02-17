package Model.Expressions;
import Model.Utils.MyIDictionary;
import Model.MyException;
import Model.Utils.MyIHeap;

public abstract class Expression {
    public abstract int eval(MyIDictionary<String,Integer> dict, MyIHeap heap) throws MyException;
    public abstract String toString();
}
