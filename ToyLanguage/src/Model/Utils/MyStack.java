package Model.Utils;

import java.util.List;

public class MyStack<T> implements MyIStack<T> {
    private java.util.Stack<T> stack=new java.util.Stack<T>();
    public void push(T t){
        stack.push(t);
    };
    public T pop(){
        return stack.pop();
    };
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public List<T> getStack(){
        return stack;
    }

    public String toString() {
        String s = "";

        for(int i = this.stack.size() - 1; i >= 0; --i) {
            s = s + this.stack.get(i);
            s = s + "\n";
        }

        return s;
    }
}
