package Model.Expressions;

import Model.MyException;
import Model.Statements.IStmt;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Utils.MyIStack;

public class NotExpression extends Expression  {
    Expression expression;
    public NotExpression(Expression e)
    {
        expression=e;
    }
    public int eval(MyIDictionary<String,Integer> dict, MyIHeap heap) throws MyException {
        if (expression.eval(dict,heap)==0)
            return 1;
        else return 0;
    }

    public String toString()  {
        return "!"+expression;
    }
}
