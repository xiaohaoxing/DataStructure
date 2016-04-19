package list;
/**
 * 链表类
 * 暂时使用UniNode作为节点，后可以修改为Node抽象类
 * @author Star
 *
 * @param <T>	节点数据类型 
 */
public class LinkedList<T> extends List<T>{
	private UniNode<T> tail;
	public LinkedList(){
		root=null;
		tail=null;
	}
	/**
	 * a constructor of the linkedlist with a head Node
	 * @param n		the head Node of the list.
	 */
	public LinkedList(UniNode<T> n){
		root=n;
		tail=root;
	}
	/**
	 * append a new Node at the end of the list
	 * @param n		the node to be inserted
	 */
	@Override
	public void append(UniNode<T> n){
		if(!isEmpty()){
			tail.next=n;
			tail=n;
		}
		else{
			root=n;
			tail=n;
		}
	}
	/**
	 * insert a Node to the head of the list
	 * @param n	the node to be inserted
	 */
	@Override
	public void insertFirst(UniNode<T> n){
		n.next=root;
		root=n;
	}
	/**
	 * delete the first Node of the list
	 * @return the result of delete operation.
	 */
	@Override
	public boolean deleteFirst(){
		if(isEmpty()){
			return false;
		}else{
			root=root.next;
			return true;
		}
	}
	/**
	 * decide whether the list is empty
	 * @return	the result
	 */
	@Override
	public UniNode<T> findFirst(T value){
		UniNode<T> temp=root;
		while(temp!=null){
			if(temp.value.equals(value)){
				return temp;
			}
			temp=temp.next;
		}
		return null;
	}
	/**
	 * delete the first element found has the value.
	 * @param value		the value contained element to be found
	 * @return	has found or not
	 */
	@Override
	public boolean delete(T value){
		UniNode<T> temp=root;
		while(temp!=null){
			if(temp.next.value.equals(value)){
				temp.next=temp.next.next;
				return true;
			}
			temp=temp.next;
		}
		return false;
	}
	/**
	 * delete all the elements contains the value.
	 * @param value	
	 * @return			how many times has delete happened.
	 */
	@Override
	public int deleteAll(T value){
		int times=0;
		UniNode<T> temp=root;
		while(temp.next!=null){
			if(temp.next.value.equals(value)){
				UniNode<T> node=temp.next;
				temp.next=node.next;
				times++;
			}
			temp=temp.next;
			//last element of the list can cause a null pointer exception here.So make a decide
			if(temp==null){
				break;
			}
		}
		return times;
	}
	/**
	 * delete the given node.
	 * <B>Only delete the node has the same reference instead of has the same value</B>
	 * @param node		the node to be deleted
	 * @return			success or not
	 */
	public boolean delete(UniNode<T> node){
		UniNode<T> temp=root;
		while(temp!=null){
			if(temp.next.equals(node)){
				temp.next=node.next;
				return true;
			}
			temp=temp.next;
		}
		return false;
	}
	@Override
	public UniNode<T>[] find(T value) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isEmpty(){
		return root==null;
	}
	/**
	 * override the method in Object class
	 * 
	 * @see java.lang.Object
	 */
	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder();
		UniNode<T> temp=root;
		while(temp.next!=null){
			sb.append(temp.toString()+"->");
			temp=temp.next;
		}
		sb.append(temp.toString());
		return sb.toString();
	}
	public static void main(String args[]){
		LinkedList<String> test=new LinkedList<String>();
		test.append(new UniNode<String>("first"));
//		test.append(new UniNode<String>("second"));
//		test.insertFirst(new UniNode<String>("third"));
//		System.out.println(test.toString());
//		test.deleteFirst();
//		System.out.println(test.toString());
//		System.out.println(test.find("second").toString());
//		test.append(new UniNode<String>("second"));
//		System.out.println(test.delete("second"));
//		System.out.println(test.toString());
		UniNode<String> a=new UniNode<String>("second");
		UniNode<String> b=new UniNode<String>("third");
		UniNode<String> c=new UniNode<String>("second");
		test.append(a);
		test.append(b);
		test.append(c);
		System.out.println(test.toString());
		test.delete("second");
		System.out.println(test.toString());
	}
}
