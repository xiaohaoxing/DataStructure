package stack;

import list.LinkedList;
import list.UniNode;
/**
 * a stack with operations push and pop realized by linkedlist
 * 好处1：长度不限制，不像数组创建的时候规定大小，因此必须要
 * 		@see stack.AutoStack#pop()
 * 		@see stack.AutoStack#push(Object)
 * 		两种方法，达到限制的时候修改内置对象的大小。
 * 好处2：创建更加直接，如果使用array，泛型T不能直接创建数组，只能通过创建Object[]。
 * 		存在转型隐患。
 * @author Star
 *
 * @param <T>
 */
public class LinkedStack<T>{
	private LinkedList<T> stack;
	public LinkedStack(){
		stack=new LinkedList<T>();
	}
	public T pop(){
		UniNode<T> v=stack.root;
		if(stack.isEmpty()){
			System.out.println("empty stack!");
			return null;
		}
		if(stack.deleteFirst()){
			return v.value;
		}else{
			System.out.println("get value error!");
			return null;
		}
	}
	public boolean push(T v){
		try{
			stack.insertFirst(new UniNode<T>(v));
			return true;
		}
		catch(Exception e){
			System.out.println("unknown error!");
			return false;
		}
	}
	public String toString(){
		return stack.toString();
	}

	public static void main(String args[]){
		LinkedStack<String> test=new LinkedStack<String>();
		//error test
		test.pop();
		
		test.push("first");
		test.push("second");
		test.push("third");
		System.out.println(test.pop());
		System.out.println(test.pop());
		System.out.println(test.pop());
		
	}
}
