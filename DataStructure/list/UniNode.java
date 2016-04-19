package list;

public class UniNode <T> {
	public T value;
	public UniNode<T> next;
	public UniNode(T v){
		value=v;
		next=null;
	}
	public UniNode(T v,UniNode<T> n){
		value=v;
		next=n;
	}
	public String toString(){
		return value.toString();
	}
}
