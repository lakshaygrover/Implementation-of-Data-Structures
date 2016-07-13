package hash_maps;

import java.util.ArrayList;

public class hashmap<K,V> {
	K key;
	V value;
	ArrayList<Node<Map<K,V>>> buckets = new ArrayList<>();
	int size = buckets.size();

	public hashmap() {
		for(int i = 0;i<4;i++){
			buckets.add(null);
		}
	}

	private int getbucketIndex(K key){
		int hashCode  =key.hashCode();
		if(hashCode<0){
			hashCode = -hashCode;
		}
		int bucketindex  = hashCode/buckets.size();
		return bucketindex;

	}

	public int size(){
		return size;
	}

	public boolean isempty(){
		return size()==0;
	}

	public void put(K key, V value){
		int bucketIndex = getbucketIndex(key);
		Node<Map<K,V>> head = buckets.get(bucketIndex);
		Map<K, V> newMap = new Map<>();
		newMap.key = key;
		newMap.value = value;
		Node<Map<K,V>> newNode = new Node<Map<K,V>>(newMap);
		Node<Map<K,V>> temp = head, prev = null;
		if(head==null){
			buckets.set(bucketIndex, newNode);
			size++;
		}
		else{

			while(temp!=null){
				if(temp.data.key.equals(key)){
					temp.data.value = value;
					return;
				}
				prev = temp;
				temp = temp.next;
			}
			prev.next = newNode;
			size++;
		}
		if(size/buckets.size()>=0.75){
			rehash();
		}


		return;
	}

	private void rehash() {
		ArrayList<Node<Map<K,V>>> temp = buckets;
		buckets = new ArrayList<>();
		size = 0;
		for(int i =0;i<temp.size()*2;i++){
			buckets.add(null);
		}
		for(Node<Map<K,V>> head:temp){
			Node<Map<K,V>> currentNode = head;
			while(currentNode!=null){
				put(currentNode.data.key, currentNode.data.value);
				currentNode = currentNode.next;
			}
		}
	}


	public V get(K key){
		int bucketIndex = getbucketIndex(key);
		Node<Map<K,V>> head = buckets.get(bucketIndex);
		if(head == null){
			System.out.println("No such value exists");
			return null;
		}
		else{
			Node<Map<K,V>> temp = head;
			while(temp!=null){
				if(temp.data.key.equals(key)){
					return temp.data.value;
				}
				temp = temp.next;


			}
			return null;
		}
	}


	public V remove(K key){
		int bucketIndex = getbucketIndex(key);
		Node<Map<K,V>> head = buckets.get(bucketIndex);
		if(head==null){
			return null;
		}
		else{
			Node<Map<K,V>> temp = head, prev = null;
			while(temp!=null){
				if(temp.data.key.equals(key)){
					size--;
					V removedVALUE = value;
					if(prev == null){
						buckets.set(bucketIndex, temp.next);
						return removedVALUE;
					}
					else{
						prev.next = temp.next;
						return removedVALUE;
					}
				}
				prev = temp;
				temp= temp.next;
			}
			return null;
		}
	}
}