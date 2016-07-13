package data_structures;


public class QueueUsingLL<T> {

	private Node<T> front;
	private Node<T> rear;
	private int size;


	public int size(){
		return size;
	}

	public boolean isEmpty(){
		return size() == 0;
	}

	public T front() throws Exception{
		if(front == null){
			Exception e = new Exception();
			throw e;
		}
		return front.data;
	}

	public void enqueue(T element){
		Node<T> newNode = new Node<>();
		newNode.data = element;
		if(rear == null){
			front = newNode;
			rear = newNode;
		}
		else{
			rear.next = newNode;
			rear = newNode;
		}
		size++;

	}

	public T dequeue() throws Exception{
		if(front == null){
			Exception e = new Exception();
			throw e;
		}
		T temp = front.data;
		if(front.next == null){
			front = null;
			rear = null;
		}
		else{
			front = front.next;
		}
		size--;
		return temp;
	}
	
}