package Model.Statements;

import Model.Expressions.Expression;
import Model.MyException;
import Model.Utils.MyIFileTable;
import Model.Utils.PrgState;

import java.io.BufferedReader;

public class CloseRFile implements IStmt {
    Expression var_file_id;
    public CloseRFile(Expression id){var_file_id=id;}
    @Override
    public PrgState execute(PrgState prg) throws MyException {
        MyIFileTable fileTable = prg.getFileTable();
        int value = this.var_file_id.eval(prg.getSymTable(),prg.getHeap());

        try {
            BufferedReader reader = (BufferedReader)fileTable.lookup(value).getRight();
            prg.getSymTable().del(this.var_file_id.toString());
            fileTable.del(value);
            reader.close();
            return null;
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "closeRFile("+var_file_id.toString()+")";
    }
}
