package tree;

import java.util.Hashtable;

public class BinNode<T>{
	public T value;
	public BinNode<T> leftchild;
	public BinNode<T> rightchild;
	public BinNode(){
		value=null;
		leftchild=null;
		rightchild=null;
	}
	public BinNode(T value){
		this.value=value;
		leftchild=null;
		rightchild=null;
	}
	public BinNode(T value,BinNode<T> left,BinNode<T> right){
		this.value=value;
		leftchild=left;
		rightchild=right;
	}
	public void setLeftChild(BinNode<T> left){
			leftchild=left;
	}
	public void setRightChild(BinNode<T> right){
		rightchild=right;
	}
	public String toString(){
		return value==null?"":value.toString();
	}
}
