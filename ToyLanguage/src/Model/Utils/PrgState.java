package Model.Utils;

import Model.MyException;
import Model.Statements.IStmt;

import java.util.ArrayList;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Integer> symTable;
    private MyIList<Integer> out;
    private MyIFileTable fileTable;
    private IStmt originalProgram;
    private MyIHeap heap;
    private int id;

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Integer> symTable, MyIList<Integer> ot, MyIFileTable ft,MyIHeap heap, IStmt prg,int id) {
        this.exeStack = stk;
        this.symTable = symTable;
        this.out = ot;
        this.originalProgram = prg;
        this.fileTable = ft;
        this.heap=heap;
        this.id=id;
        stk.push(prg);
    }
    public String toString() {
        if(this.exeStack.isEmpty())
            return "ID:"+this.id+"\nExecution stack: \n" + this.exeStack.toString() + "\nTable of symbols:\n" + this.symTable.toString() + "\nOutput:\n" + this.out.toString() + "\nFile table:\n" + this.fileTable.toString()+ "\nHeap:\n"+this.heap.toString()+"\nDone\n";
        return "ID: "+this.id+"\nExecution stack: \n" + this.exeStack.toString() + "\nTable of symbols:\n" + this.symTable.toString() + "\nOutput:\n" + this.out.toString() + "\nFile table:\n" + this.fileTable.toString() + "\nHeap:\n"+this.heap.toString()+"\nNext step:\n";
    }

    public PrgState(IStmt originalProgram){
        exeStack = new MyStack<>();
        symTable = new MyDictionary<>();
        out = new MyList<>();
        fileTable = new MyFileTable();
        heap = new MyHeap();
        this.originalProgram = originalProgram;
        id=1;
        exeStack.push(originalProgram);
    }

    public MyIStack<IStmt> getExeStack() {
        return this.exeStack;
    }

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public MyIDictionary<String, Integer> getSymTable() {
        return this.symTable;
    }

    public void setSymTable(MyIDictionary<String, Integer> symTable) {
        this.symTable = symTable;
    }

    public MyIList<Integer> getOut() {
        return this.out;
    }

    public void setOut(MyIList<Integer> out) {
        this.out = out;
    }

    public MyIFileTable getFileTable() {
        return this.fileTable;
    }

    public PrgState oneStep() throws MyException {
        if (!exeStack.isEmpty()) {
            IStmt crtStmt = (IStmt) exeStack.pop();
            return crtStmt.execute(this);
        }
        else {
            throw new MyException("Empty stack!");
        }
    }

    public void setFileTable(MyIFileTable fileTable) {
        this.fileTable = fileTable;
    }

    public MyIHeap getHeap() {
        return this.heap;
    }

    public int getID(){
        return id;
    }

    public void setHeap(MyIHeap heap) {
        this.heap = heap;
    }

    public IStmt getOriginalProgram() {
        return this.originalProgram;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }

    public boolean isNotCompleted()
    {
        if(this.exeStack.isEmpty())
            return false;
        return true;
    }
}
