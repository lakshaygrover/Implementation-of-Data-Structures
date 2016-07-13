package data_structures;

import java.util.ArrayList;

public class priorityQueues<V> {


	ArrayList<PQNode<V>> data;
	int index = 0;

	public priorityQueues() {
		data = new ArrayList<>();
	}
	public boolean isEmpty(){

		return data.size()==0;
	}
	
	public V getmin(){
		return data.get(0).value;
	}

	public void insertData(int priority, V value){
		PQNode<V> newNode = new PQNode<>();
		newNode.priority = priority;
		newNode.value = value;
		data.add(newNode);
		index++;
		upHeapify(newNode, index);


	}
	
	public int size(){
		return data.size();
	}

	private void upHeapify(PQNode<V> given, int index){
		int childindex = data.size()-1;
		int parentindex = (childindex-1)/2;
		while(childindex>0){
			if(data.get(childindex).priority<data.get(parentindex).priority){
				return;
			} 
			else {
				PQNode<V> temp = data.get(childindex);
				data.set(childindex, data.get(parentindex));
				data.set(parentindex, temp);
				childindex = parentindex;
				parentindex = (childindex-1)/2;
			}
		}
	}
	
	public V remove() {
		V value = data.get(0).value;
		data.set(0, data.get(data.size()-1));
		if(data.size()>0)
		downHeapify(0);
		
		return value;
		
	}
	
	private void downHeapify(int index){
		int leftindex = 2*index+1;
		int rightindex = 2*index+2;
		if(leftindex>data.size()-1){
			return;
		}
		int minNodeIndex = index;
		if(data.get(leftindex).priority<data.get(minNodeIndex).priority){
			minNodeIndex = leftindex;
		}
		if(rightindex<=data.size()-1&&data.get(rightindex).priority<data.get(minNodeIndex).priority){
			minNodeIndex = rightindex;
		}
		if(minNodeIndex!=index){
			PQNode<V> temp = data.get(minNodeIndex);
			data.set(minNodeIndex, data.get(index));
			data.set(index, temp);
			downHeapify(minNodeIndex);
			
		}
	}
}
