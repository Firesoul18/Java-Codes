public class CircularLinkedList {
    Node head,tail;
    int size;

    //I have not added addAtIndex and removeAtIndex Bcoz we can add them same as we added in Normal LinkedList.
    //other methods will also be identical.

    CircularLinkedList(){
        head=null;
        tail=head;
        size=0;
    }

    public void addFirst(int data){
        if(size==0){
            head=new Node(data);
            tail=head;
            tail.setNext(head);
            size++;
            return;
        }

        Node temp = new Node(data);
        tail.setNext(temp);
        temp.setNext(head);
        head=temp;
        size++;

    }

    public void addLast(int data){
        if(size==0){
            addFirst(data);
            return;
        }
        
        Node temp=new Node(data);
        temp.setNext(head);
        tail.setNext(temp);
        tail=temp;
        size++;
    }

    public void removeFirst(){
        if(size==0){
            System.out.println("Already Empty");
            return;
        }

        tail.setNext(head.getNext());
        head=head.getNext();
        size--;
    }

    public void removeLast(){
        if(size==0){
            System.out.println("Already Empty");
            return;
        }

        Node temp = head;
        Node temp2 = temp;
        while(temp.getNext()!=head){
            temp2=temp;
            temp=temp.getNext();
        }
        temp2.setNext(head);
        temp=null;
        size--;

    }

    public void print(){
        if(size==0){
            System.out.println("Empty");
            return;
        }

        Node temp=head;
        System.out.print(temp.getData()+" ");
        while(temp.getNext()!=head){
            temp=temp.getNext();
            System.out.print(temp.getData()+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircularLinkedList l = new CircularLinkedList();
        l.addFirst(0);
        l.addFirst(2);
        l.addFirst(5);

        l.print();
    }

}

class Node{
    private int data;
    private Node next;

    public Node(int data) {
        this.data = data;
    }
    
    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }
    public Node getNext() {
        return next;
    }
    public void setNext(Node next) {
        this.next = next;
    }
    
}
