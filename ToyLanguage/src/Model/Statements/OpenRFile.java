package Model.Statements;

import Model.MyException;
import Model.Utils.MyIFileTable;
import Model.Utils.Pair;
import Model.Utils.PrgState;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class OpenRFile implements IStmt {
    String var_file_id;
    String filename;

    public OpenRFile(String var_file_id, String filename) {
        this.var_file_id = var_file_id;
        this.filename = filename;
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIFileTable fileTable = state.getFileTable();
        Iterator var4 = fileTable.getFileNames().iterator();

        while(var4.hasNext()) {
            Pair<String, BufferedReader> elem = (Pair) var4.next();
            if (elem.getLeft() == this.filename) {
                throw new MyException("The file is already open!");
            }
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.filename));
            Pair<String, BufferedReader> tupple = new Pair(this.filename, reader);
            int fd = state.getFileTable().add(tupple);
            state.getSymTable().add(this.var_file_id, fd);
            return null;
        } catch (IOException var6) {
            throw new MyException(var6.getMessage());
        }
    }

    public String toString() {
        return "openRFile(" + this.var_file_id + ")";
    }
}
