package Model.Expressions;

import Model.Expressions.Expression;
import Model.MyException;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;

public class BooleanExpression extends Expression {

    Expression e1;
    Expression e2;
    int op;
    //1 <, 2 <=, 3 ==, 4 !=, 5 >, 6>=

    public BooleanExpression(Expression ee1,Expression ee2, int i){e1=ee1;e2=ee2;op=i;}

    @Override
    public String toString() {
        switch(op){
            case 1:{
                return "("+e1+" < "+e2+")";
            }
            case 2: return "("+e1+" <= "+e2+")";
            case 3: return "("+e1+" == "+e2+")";
            case 4: return "("+e1+" != "+e2+")";
            case 5: return "("+e1+" > "+e2+")";
            case 6: return "("+e1+" >= "+e2+")";
            default: return "";
        }}

    @Override
    public int eval(MyIDictionary<String, Integer> tbl, MyIHeap heap) throws MyException {
        switch(op) {
            case 1: {
                if (e1.eval(tbl,heap)<e2.eval(tbl,heap)) return 1;
                else return 0;
            }
            case 2:{
                if (e1.eval(tbl,heap)<=e2.eval(tbl,heap)) return 1;
                else return 0;}
            case 3:{
                if (e1.eval(tbl,heap)==e2.eval(tbl,heap)) return 1;
                else return 0;}
            case 4:
            {
                if (e1.eval(tbl,heap)!=e2.eval(tbl,heap)) return 1;
                else return 0;}
            case 5:
            {
                if (e1.eval(tbl,heap)>e2.eval(tbl,heap)) return 1;
                else return 0;}
            case 6:
            {
                if (e1.eval(tbl,heap)>=e2.eval(tbl,heap)) return 1;
                else return 0;}
            default:
                return 0;
        }
    }
}