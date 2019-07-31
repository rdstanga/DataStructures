import java.util.Iterator;
import java.util.NoSuchElementException;


// author - Ross Stanga
// v.2 - Sept 26, 9:31 PM 2017
// v.final - Sept 29, 6:39 PM 2017


public class SinglyLinkedList<Anytype> implements Iterable<Anytype> {
    
    public Node head;
    public Node tail;

    // we need a constructor to build an empty SinglyLinkedList
    // make the tail before the head so head will know it's referencing tail
    public SinglyLinkedList(){
        tail = new Node(null, null);
        head = new Node(null, tail);
        
    }

    // inner class to build our nodes, changed it from static
    private class Node{
        private Anytype data;
        private Node next;

        //node constructor. Node knows it has an element inside and a pointer to its next neighbor
        public Node(Anytype data, Node next){

            this.data = data;
            this.next = next;
        }
        //gets Node's neighbor
        public Node getNext(){
            return this.next;
        }
        //gets Node's element
        public Anytype getData(){
            return this.data;

        }
        //changes Node's neighbor to what we desire
        public void setNext(Node next){
            this.next = next;
        }
        //let's us change what's in Node
        public void setData(Anytype data){
            this.data = data;
        }
    }

    public void add(Anytype element){
        //make a new node but don't assign it a neighbor just yet
        Node appendedNode = new Node(element, null);
        //make a new node and set it to the first node's position
        Node temporary;
        temporary = head;
        /**
         * check to see if the list is empty
         * if it's not, set temporary's next to our new node
         * then set our newly added node's pointer to tail
         * thus, we've added a node :)
         */
        while(temporary.getNext() != tail){    
            temporary = temporary.getNext();
        } 
        temporary.setNext(appendedNode);
        appendedNode.setNext(tail);
    }



    public void remove(Anytype element){
       
        //make a new node and set it to head's position
        //make a new node and set it head's neighbor
        Node previousNode = head;
        Node currentNode = head.getNext();
        //if the current node isn't tail
        while(currentNode != tail){
            if(currentNode.data.equals(element)){
                //set head to current node's next
                previousNode.setNext(currentNode.next);
                //current nodes next doesn't exist, meaning it's the tail
                currentNode.next = null;

                return;

            }

            previousNode = previousNode.next;
            currentNode = currentNode.next;
        }

    }


    //empty your list
    //we can do this by simply setting the head's next to point to tail
    //once the garbage collector comes, all nodes between our ends will be removed, so we have an empty list
    public void clear(){
        head.setNext(tail);
    }

    public Anytype getNthToLast(int n){
        //make two different iterators
        SinglyLinkedListIterator one = new SinglyLinkedListIterator();
        SinglyLinkedListIterator two = new SinglyLinkedListIterator();

        int count = 0; 
        /*
        *iterator one starts moving before iterator two
        *we keep track of the distance between them because
        *they move in tandem
        */
        while(one.hasNext()){
            one.next();
            count++;
            if(count >= n+1){
                two.next();
            }
            
        }

        return two.next();
    }

    public SinglyLinkedListIterator iterator() {
        return new SinglyLinkedListIterator();
    }

    public class SinglyLinkedListIterator implements Iterator<Anytype>{

        private Node previousNode;
        private Node currentNode;

        public SinglyLinkedListIterator(){

            currentNode = head;
        }
        //if our current node's next points to tail, we don't have any other nodes
        //but if it doesn't, we have at least another node
        public boolean hasNext(){

            if(currentNode.next == tail){
                return false;
            }
            return true;
        }
        //if hasNext tells us there is at least one more
        //move to the neighbor
        //but if we don't have another node
        //we'll throw an exception because we're asking for what doesn't exists
        public Anytype next(){

            if(hasNext()){
            currentNode = currentNode.next;
            
           }
           else{
            throw new NoSuchElementException();
           }
           return currentNode.data;
        }

        public void add(Anytype element){

            // this is our new node to add in
            //importantly, it assumes currentNode's neighbor as its pointer
            Node appendedNode = new Node(element, currentNode.next);

            //appendedNode.setNext(currentNode.next); --- this is wrong
            
            //set currentNode(the head)'s next to be our new node
            currentNode.setNext(appendedNode);
        }

        public void remove(){
            //when you're at the tail
            if(!hasNext())
            {
                throw new IllegalStateException("You've attempted to remove an element after the tail which cannot exist.");
            }

            previousNode = head;
                while(previousNode.next != currentNode){
                    previousNode = previousNode.next;
            }

            previousNode.next = currentNode.next;
            currentNode.next = null;
            currentNode = previousNode;
        }

    }

}