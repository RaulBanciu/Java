package Model.Expressions;

import Model.MyException;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;

public class ArithmeticExpression extends Expression {
    Expression e1;
    Expression e2;
    int op;

    public ArithmeticExpression(Expression e1,Expression e2,int op)
    {
        this.e1=e1;
        this.e2=e2;
        this.op=op;
    }

    public int eval(MyIDictionary<String,Integer> dict, MyIHeap heap) throws MyException {
        if(op==1)
            return (e1.eval(dict,heap)+e2.eval(dict,heap));
        else if(op==2)
            return (e1.eval(dict,heap)-e2.eval(dict,heap));
        else if(op==3)
            return (e1.eval(dict,heap)*e2.eval(dict,heap));
        else {
            if(e2.eval(dict,heap)==0)
                throw new MyException("Division by zero");
            return (e1.eval(dict,heap) / e2.eval(dict,heap));
        }

    }

    public String toString()  {
        switch(op) {
            case 1 : return e1 + "+" + e2;
            case 2 : return e1 + "-" + e2;
            case 3 : return e1 + "*" + e2;
            case 4 : return e1 + "/" + e2;
            default : return "";
        }
    }
}
