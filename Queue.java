public class Queue <E> {
    private LinkedList<E> queue;
    private Node top;
    private int size;


    public Queue(LinkedList<E> queue) {
        this.queue = queue;
    }

    public Queue(){
        this.queue= new LinkedList<E>();
        this.size=0;
    }

    public void push(Node node){
        Node myNode= new Node(node.getKey());
        this.queue.pushTail(myNode);
        this.size=queue.getSize();
    }

    public Node peek(){
        return this.queue.peekHead();
    }

    public Node pop(){
        Node myNode=this.queue.popHead();
        this.size=queue.getSize();
        return myNode ;
    }

    public int getSize(){
        return this.size;

    }

    public boolean isEmpty(){
        return this.size==0;
    }
}
