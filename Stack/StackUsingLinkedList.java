package data_structures;

public class StackUsingLinkedList<T> {

	private Node<T> head;
	private int size;

	public int size(){

		return size;
	}

//isEmpty() check if the stack is empty or not
	public boolean isEmpty(){
		if(size == 0)
			return true;
		else
			return false;

	}

	public T top() throws StackEmptyException{
		if(size == 0){
			StackEmptyException e = new StackEmptyException();
			throw e;
		}
		return head.data;
	}
//to add a new element into stack
	public void push(T newElement){
		Node<T> newNode = new Node<T>();
		newNode.data = newElement;
		newNode.next = head;
		head = newNode;
		size++;	
	}
//to remove an element from stack
	public T pop() throws StackEmptyException{
		if(size == 0){
			StackEmptyException e = new StackEmptyException();
			throw e;
		}

		T toBeDeleted = head.data;
		head = head.next;
		size--;
		return toBeDeleted;
	}


}