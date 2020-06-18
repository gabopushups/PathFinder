import sun.awt.image.ImageWatched;

public class LinkedList<E> {

    private Node head;
    private Node tail;
    private int size;

    public LinkedList(){
        this.head=null;
        this.tail=null;
        this.size=0;
    }

    public LinkedList(Node head){
        head.setNext(null);
        head.setPrevious(null);
        this.head= head;
        this.tail= null;            // Aquí van a haber problemas
        this.size=1;
    }

    public LinkedList(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
        this.size=2;
    }

    public boolean isEmpty(){
        if (this.head==null){
            return true;
        } else return false;
    }

    public void pushHead(Node node){
        Node myNode = new Node(node.getKey());
        if (!this.isEmpty()){
            myNode.setPrevious(null);
            myNode.setNext(this.head);
            this.head.setPrevious(myNode);
            this.setHead(myNode);
        } else {
            this.setTail(myNode);
            this.setHead(myNode);
        }
        this.size=this.size+1;
    }

    public Node popHead(){
        if(this.isEmpty()){
            return null;
        }
        else if(this.size>2) {
            Node oldHead = new Node(this.head.getKey(), null, null);
            Node newHead = new Node(this.head.getNext().getKey(), this.head.getNext().getNext(), null);
            this.head=newHead;
            this.size=this.size-1;
            return oldHead;
        }  else if(this.size==2){
            Node oldHead = new Node(this.head.getKey(), this.head.getNext(), this.head.getPrevious());
            Node newHead = new Node(this.head.getNext().getKey(), this.head.getNext().getNext(), null);
            this.tail.setPrevious(null);
            this.head=newHead;
            this.size=1;
            return oldHead;
        } else if(this.size==1){
            Node oldHead = new Node(this.head.getKey(), this.head.getNext(), this.head.getPrevious());
            this.head=null;
            this.tail=null;
            this.size=0;
            return oldHead;
        }
        else return null;
    }

    public Node peekHead(){
        if(!(this.isEmpty())){
            return this.head;
        }
        else return null;
    }

    public void pushTail(Node node){
        Node myNode = new Node(node.getKey());
        if (this.isEmpty()){
            this.head=myNode;
            this.tail=myNode;
            this.size=1;
        } else if(this.size==1){
            myNode.setPrevious(this.head);
            myNode.setNext(null);
            this.head.setNext(myNode);
            this.tail=myNode;
            this.size++;
        }
        else {
            myNode.setPrevious(this.tail);
            myNode.setNext(null);
            this.tail.setNext(myNode);
            this.tail=myNode;
            this.size=this.size+1;
        }

    }

    public Node popTail(){
        if(this.size>1){
            Node oldTail = new Node(this.tail.getKey(),null,null);
            Node newTail = new Node(this.tail.getPrevious().getKey(),null,this.tail.getPrevious().getPrevious());
            this.tail=newTail;
            this.size=this.size-1;
            return oldTail;
        }
        else if (this.size==1){
            Node oldTail = new Node(this.tail.getKey(),this.tail.getNext(),this.tail.getPrevious());
            this.tail=null;
            this.head=null;
            this.size=0;
            return oldTail;
        }

        else return null;
    }

    public Node getHead() {
        Node head = this.head;
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        Node tail = this.tail;
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public int getSize(){
        return this.size;
    }
}
