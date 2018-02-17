//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Model.Statements;

import Model.Expressions.VariableExpression;
import Model.MyException;
import Model.Utils.*;
import java.io.BufferedReader;

public class ReadFile implements IStmt {
    VariableExpression exp_file_id;
    String var_name;

    public ReadFile(VariableExpression exp_file_id, String var_name) {
        this.exp_file_id = exp_file_id;
        this.var_name = var_name;
    }

    public PrgState execute(PrgState state) throws MyException {
        int value = this.exp_file_id.eval(state.getSymTable(),state.getHeap());
        MyIFileTable fileTable = state.getFileTable();
        BufferedReader reader = (BufferedReader)fileTable.lookup(value).getRight();
        int res = 0;

        try {
            String s = reader.readLine();
            if (s != "") {
                res = Integer.parseInt(s);
            }
        } catch (Exception var7) {
            throw new MyException("The number couldn't be read!");
        }

        state.getSymTable().add(this.var_name, res);
        return null;
    }

    public String toString() {
        return "readFile(" + this.exp_file_id + "," + this.var_name + ")";
    }
}
