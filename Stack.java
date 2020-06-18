public class Stack {

    private LinkedList stack;
    private Node top;
    private int size;


    public Stack(LinkedList stack) {
        this.stack = stack;
    }

    public Stack(){
        this.stack= new LinkedList();
    }

    public void push(Node node){
        Node myNode= new Node(node.getKey());
        this.stack.pushHead(myNode);
        this.size=stack.getSize();
    }

    public Node peek(){
        return this.stack.peekHead();
    }

    public Node pop(){
        Node myNode=this.stack.popHead();
        this.size=stack.getSize();
        return myNode ;
    }

    public int getSize(){
        return this.size;
    }
}
