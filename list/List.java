package list;

public abstract class List<T> {
	public UniNode<T> root;
	public abstract void append(UniNode<T> n);
	public abstract void insertFirst(UniNode<T> n);
	public abstract boolean deleteFirst();
	public abstract boolean delete(T value);
	public abstract int deleteAll(T value);
	public abstract boolean isEmpty();
	public abstract Object[] find(T value);
	public abstract UniNode<T> findFirst(T value);
}
