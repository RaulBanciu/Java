package Model.Utils;

public class Pair <L,R> {
    L left;
    R right;

    public Pair(L l, R r) {
        left = l;
        right = r;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    public void setLeft(L left) {
        this.left = left;
    }

    public void setRight(R right) {
        this.right = right;
    }

    public String toString() {
        return left.toString();
    }
}
