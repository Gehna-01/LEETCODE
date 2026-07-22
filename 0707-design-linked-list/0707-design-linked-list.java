class MyLinkedList {

    class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    Node head;
    int size;

    public MyLinkedList() {
        head = null;
        size = 0;
    }

    public int get(int index) {

        if(index < 0 || index >= size)
            return -1;

        Node curr = head;

        for(int i = 0; i < index; i++)
            curr = curr.next;

        return curr.val;
    }

    public void addAtHead(int val) {

        Node node = new Node(val);
        node.next = head;
        head = node;
        size++;
    }

    public void addAtTail(int val) {

        Node node = new Node(val);

        if(head == null){
            head = node;
            size++;
            return;
        }

        Node curr = head;

        while(curr.next != null)
            curr = curr.next;

        curr.next = node;
        size++;
    }

    public void addAtIndex(int index, int val) {

        if(index < 0 || index > size)
            return;

        if(index == 0){
            addAtHead(val);
            return;
        }

        if(index == size){
            addAtTail(val);
            return;
        }

        Node prev = head;

        for(int i = 0; i < index - 1; i++)
            prev = prev.next;

        Node node = new Node(val);

        node.next = prev.next;
        prev.next = node;

        size++;
    }

    public void deleteAtIndex(int index) {

        if(index < 0 || index >= size)
            return;

        if(index == 0){
            head = head.next;
            size--;
            return;
        }

        Node prev = head;

        for(int i = 0; i < index - 1; i++)
            prev = prev.next;

        prev.next = prev.next.next;

        size--;
    }
}