public class MyDoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void clear() {
        head = null;
        tail = head;
        size = 0;
    }

    public int size() {
        return size;
    }

    MyDoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void addLast(T data) {
        if (head == null) {
            head = new Node<>(data);
            tail = head;
            size++;
            return;
        }

        Node<T> temp = new Node<>(data, tail, null);
        tail.setNext(temp);
        tail = temp;
        size++;
    }

    public void addFirst(T data) {
        if (head == null) {
            head = new Node<>(data);
            tail = head;
            size++;
            return;
        }
        Node<T> temp = new Node<>(data, null, head);
        head.setPrevious(temp);
        head = temp;
        size++;

    }

    public void removeFirst() {
        if (size == 0) {
            System.out.println("Already Empty");
            return;
        }

        if (size == 1) {
            head = null;
            tail = head;
            size--;
            return;
        }

        Node<T> temp = head;
        head = head.getNext();
        head.setPrevious(null);
        temp.setNext(null);
        size--;
        System.gc();

    }

    public void removeLast() {
        if (size == 0) {
            System.out.println("Already Empty");
            return;
        }

        if (size == 1) {
            head = null;
            tail = head;
            size--;
            return;
        }

        Node<T> temp = tail;
        tail = tail.getPrevious();
        tail.setNext(null);
        temp.setPrevious(null);
        size--;
        System.gc();
    }

    public void addAtIndex(int index, T data) {
        if (index > size) {
            System.out.println("Out of Bounds..");
            return;
        }
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            addLast(data);
            return;
        }

        if (index > size / 2) {
            Node<T> toAdd = new Node<>(data);
            Node<T> temp;
            temp = tail;
            int currentIndex = size - 1;
            while (currentIndex > index) {
                temp = temp.getPrevious();
                currentIndex--;
            }
            Node<T> temp2 = temp.getPrevious();
            temp2.setNext(toAdd);
            temp.setPrevious(toAdd);
            toAdd.setNext(temp);
            toAdd.setPrevious(temp2);
            size++;
        } else {
            Node<T> toAdd = new Node<>(data);
            Node<T> temp;
            temp = head;
            int currentIndex = 0;
            while (currentIndex < index) {
                temp = temp.getNext();
                currentIndex++;
            }
            Node<T> temp2 = temp.getPrevious();
            temp2.setNext(toAdd);
            temp.setPrevious(toAdd);
            toAdd.setNext(temp);
            toAdd.setPrevious(temp2);
            size++;
        }
    }

    public void removeAtIndex(int index) {
        if (index >= size) {
            System.out.println("Out of Bounds");
            size--;
            return;
        }
        if (index == 0) {
            removeFirst();
            return;
        }
        if (index == size-1) {
            removeLast();
            return;
        }

        if (size / 2 < index) {
            Node<T> temp;
            temp = tail;
            int currentIndex = size - 1;
            while (currentIndex > index) {
                temp = temp.getPrevious();
                currentIndex--;
            }
            Node<T> prevNode = temp.getPrevious();
            Node<T> nextNode = temp.getNext();

            prevNode.setNext(nextNode);
            nextNode.setPrevious(prevNode);
            temp=null;
            size--;

        }else{
            Node<T> temp;
            temp=head;
            int c = 0;
            while(c<index){
                temp=temp.getNext();
                c++;
            }
            
            Node<T> prevNode = temp.getPrevious();
            Node<T> nextNode = temp.getNext();

            prevNode.setNext(nextNode);
            nextNode.setPrevious(prevNode);
            temp=null;
            size--;
        }
    }

    public void printReverse() {

        if (size == 0) {
            System.out.println("Empty");
            return;
        }

        Node<T> temp = tail;
        System.out.print(temp.getData() + " ");

        while (temp.getPrevious() != null) {
            temp = temp.getPrevious();
            System.out.print(temp.getData() + " ");
        }
    }

    public String toString() {
        if (size == 0) {
            return "{" + "}";
        }
        Node<T> temp = head;
        String s = "{ " + temp.getData();
        while (temp.getNext() != null) {
            temp = temp.getNext();
            s += " " + temp.getData();
        }
        return s + " }";
    }

    public static void main(String[] args) {
        MyDoublyLinkedList<Integer> l = new MyDoublyLinkedList<>();
        l.addFirst(23);
        l.addAtIndex(0, 9);
        l.addAtIndex(l.size(), 7);
        l.addAtIndex(2, 23);
        l.addAtIndex(1, 67);
        l.addAtIndex(0, 3);
        l.addAtIndex(3, 5);
        l.addFirst(11);
        l.addLast(92);
        System.out.println(l+"\n");
        l.removeAtIndex(0);
        l.removeAtIndex(1);
        l.removeAtIndex(l.size()-1);
        l.removeAtIndex(3);

        System.out.println(l);

    }
}

class Node<T> {
    private T data;
    private Node<T> next;
    private Node<T> previous;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    public Node(T data) {
        this.data = data;
        next = null;
        previous = null;
    }

    public Node(T data, Node<T> prev, Node<T> next) {
        this.data = data;
        this.previous = prev;
        this.next = next;
    }

}