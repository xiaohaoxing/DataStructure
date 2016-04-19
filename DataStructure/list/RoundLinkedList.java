package list;

public class RoundLinkedList<T> extends List<T>{
	public UniNode<T> last;
	public RoundLinkedList(){
		root=null;
		last=null;
	}
	public RoundLinkedList(UniNode<T> n){
		root = n;
		last = n;
	}

	@Override
	public void append(UniNode<T> n) {
		if (root == null) {
			root=n;
			last=n;
		} else {
			UniNode<T> temp = root;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = n;
			last = n;
		}
	}

	@Override
	public void insertFirst(UniNode<T> n) {
		if(root==null){
			last=n;
		}
		n.next=root;
		root=n;
	}

	@Override
	public boolean deleteFirst() {
		if(root==null){
			return false;
		}else{
			root=root.next;
			return true;
		}
	}

	@Override
	public boolean isEmpty() {
		return root==null;
	}

	@Override
	public UniNode<T>[] find(T value) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean delete(T value) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int deleteAll(T value) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public UniNode<T> findFirst(T value) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
