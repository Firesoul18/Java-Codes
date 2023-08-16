public class MyLinkedList {
    private Node head;
    private int size;

    public int size() {
        return this.size;
    }

    public MyLinkedList() {
        head = null;
        size = 0;
    }

    public void addLast(int data) {
        if (head == null) {
            head = new Node(data, null);
            size++;
            return;
        }
        Node temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(new Node(data));
        size++;
    }

    public void removeLast() {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        } else if (head.getNext() == null) {
            head = null;
            size--;
            return;
        }

        Node temp = head;
        while (temp.getNext().getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(null);
        size--;
    }

    public void printAll() {
        if (head == null) {
            System.out.print("Empty");
            return;
        }
        Node temp = head;
        System.out.print(temp.getData() + " ");
        while (temp.getNext() != null) {
            temp = temp.getNext();
            System.out.print(temp.getData() + " ");
        }
    }

    public void removeFirst() {
        if (head == null) {
            System.out.println("Already Empty");
            return;
        }

        head = head.getNext();
        size--;

    }

    public void addFirst(int data) {
        Node newNode = new Node(data);
        newNode.setNext(head);
        head = newNode;
        size++;
    }

    public void addAtIndex(int index, int data){
        if(index>size){
            //we can throw error too. But I want to make the code as user friendly as possible
            System.out.println("Index out of Bound");
        }
        else if(index==0){
            addFirst(data);
        }
        else{
            int currentIndex=1;
            Node temp=head;
            while(currentIndex!=index){
                temp=temp.getNext();
                currentIndex++;
            }
            Node addNode = new Node(data, temp.getNext());
            temp.setNext(addNode);
            size++;
        }
    }

    public void removeAtIndex(int index){
        if(index>=size){
            System.out.println("Index out of Bounds");
            return;
        }
        else if(index==0){
            removeFirst();
        }
        else{
            int currentIndex=1;
            Node temp=head;
            while(currentIndex!=index){
                temp=temp.getNext();
                currentIndex++;
            }
            temp.setNext(temp.getNext().getNext());
            size--;
        }

    }

    public void replaceAtIndex(int index,int data){
        if(index>=size){
            System.out.println("Index out of Bounds");
            return;
        }else if(index==size-1){
            removeLast();
            addLast(data);
            return;
        }
        Node temp = head;
        int currentIndex=0;
        while(currentIndex!=index){
            temp=temp.getNext();
            currentIndex++;
        }
        temp.setData(data);
    }

    public static void main(String args[]) {
        MyLinkedList l = new MyLinkedList();
        l.addAtIndex(0, 0);
        l.addAtIndex(0, 5);
        l.addAtIndex(2, 6);
        l.addAtIndex(2, 88);
        l.addAtIndex(2, 76);
        // l.addAtIndex(12, 76);
        l.addAtIndex(5, 60);
        l.addAtIndex(5, 0);
        l.printAll();
        System.out.println("\nAfter Replace:->");
        l.replaceAtIndex(0, 2);
        l.replaceAtIndex(l.size(), 889);
        l.replaceAtIndex(l.size()-1, 786);
        l.replaceAtIndex(1, 10);
        l.replaceAtIndex(3, 53);
        l.printAll();
    }

}

class Node {
    private int data;
    private Node next;

    public Node(int data) {
        this(data, null);
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
