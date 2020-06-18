public class Node <O> {
    private O key;
    private Node <O> next;
    private Node <O> previous;

    public Node(){
        this.key= null;
        this.next=null;
        this.previous=null;
    }

    public Node(O key){
        this.key= key;
        this.next=null;
        this.previous=null;
    }

    public Node(O key,Node<O> next, Node<O> previous) {
        this.key=key;
        this.next = next;
        this.previous = previous;
    }

    @Override
    public String toString(){
        key = this.key;
        if (key==null){
            return "Null Node";
        } else {
            return key.toString();
        }
    }

    public O getKey() {
        O key1 = key;
        return key1;
    }

    public void setKey(O key) {
        this.key = key;
    }

    public Node<O> getNext() {
        return next;
    }

    public void setNext(Node<O> next) {
        this.next = next;
    }

    public Node<O> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<O> previous) {
        this.previous = previous;
    }
}
