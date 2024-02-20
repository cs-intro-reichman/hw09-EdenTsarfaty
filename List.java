/** A linked list of character data objects.
 *  (Actually, a list of Node objects, each holding a reference to a character data object.
 *  However, users of this class are not aware of the Node objects. As far as they are concerned,
 *  the class represents a list of CharData objects. Likwise, the API of the class does not
 *  mention the existence of the Node objects). */
public class List {

    // Points to the first node in this list
    private Node first;

    // The number of elements in this list
    private int size;
	
    /** Constructs an empty list. */
    public List() {
        first = null;
        size = 0;
    }

    public void main (String[] args){
        String test = "committee ";
        for (int i = test.length() - 1; i >= 0; i--) {
            update(test.charAt(i));
        }
        System.out.println(this);
        remove('e');
        System.out.println(this);
        System.out.println(get(2));
    }

    /** Returns the number of elements in this list. */
    public int getSize() {
 	      return size;
    }

    /** Returns the first element in the list */
    public CharData getFirst() {
        return first.cd;
    }

    /** GIVE Adds a CharData object with the given character to the beginning of this list. */
    public void addFirst(char chr) {
        Node temp = first;
        first = new Node(new CharData(chr), temp);
        size++;
    }
    
    /** GIVE Textual representation of this list. */
    public String toString() {
        ListIterator iterator = listIterator(0);
        String output = "(";
        while (iterator.hasNext()) {
            output += iterator.next();
        }
        return output + ")";
    }

    /** Returns the index of the first CharData object in this list
     *  that has the same chr value as the given char,
     *  or -1 if there is no such object in this list. */
    public int indexOf(char chr) {
        ListIterator iterator = listIterator(0);
        int i = 0;
        if (size > 0) { //If the list is not empty
            while (iterator.hasNext()) {
                CharData comparedChar = iterator.next();
                if (comparedChar.chr == chr) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    /** If the given character exists in one of the CharData objects in this list,
     *  increments its counter. Otherwise, adds a new CharData object with the
     *  given chr to the beginning of this list. */
    public void update(char chr) {
        int search = indexOf(chr);
        //If not found, adds a new CharData the beginning of the list.
        if (search == -1) { 
            addFirst(chr);
        }
        //If found, increments the counter of the letter by 1.
        else { 
            ListIterator iterator = listIterator(search);
            iterator.current.cd.count++;
            size++;
        }
    }

    /** GIVE If the given character exists in one of the CharData objects
     *  in this list, removes this CharData object from the list and returns
     *  true. Otherwise, returns false. */
    public boolean remove(char chr) {
        int indexOfChar = indexOf(chr);
        //If the character was not found returns false
        if (indexOfChar == -1) {
            return false;
        }
        ListIterator iterator = listIterator(indexOfChar - 1);
        Node temp = iterator.current;
        //If the char exists in the first node, sets first as the second node.
        if (temp == first) {
            first = temp.next;
        }
        else {
            iterator.next();
            //If the char is the char on the list, sets the n-1 node .next property to null.
            if (iterator.hasNext()) {
                temp.next = iterator.current.next;
            }
            else {
                temp.next = null;
            }
        }
        size--;
        return true;
    }

    /** Returns the CharData object at the specified index in this list. 
     *  If the index is negative or is greater than the size of this list, 
     *  throws an IndexOutOfBoundsException. */
    public CharData get(int index)  {
        if (!(index < 0 || index > size)) {
            ListIterator iterator = listIterator(index);
            CharData CharDataAtIndex = iterator.current.cd;
            return CharDataAtIndex;
        }
        else {
            IndexOutOfBoundsException exception = new IndexOutOfBoundsException();
            throw exception;
        }
    }

/** Returns an array of CharData objects, containing all the CharData objects in this list. */
public CharData[] toArray() {
	    CharData[] arr = new CharData[size];
	    Node current = first;
	    int i = 0;
        while (current != null) {
    	    arr[i++]  = current.cd;
    	    current = current.next;
        }
        return arr;
    }

    /** Returns an iterator over the elements in this list, starting at the given index. */
    public ListIterator listIterator(int index) {
	    // If the list is empty, there is nothing to iterate   
	    if (size == 0) return null;
	    // Gets the element in position index of this list
	    Node current = first;
	    int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        // Returns an iterator that starts in that element
	    return new ListIterator(current);
    }
}
