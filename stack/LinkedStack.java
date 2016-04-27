package stack;

import list.LinkedList;
import list.UniNode;
/**
 * a stack with operations push and pop realized by linkedlist
 * �ô�1�����Ȳ����ƣ��������鴴����ʱ��涨��С����˱���Ҫ
 * 		@see stack.AutoStack#pop()
 * 		@see stack.AutoStack#push(Object)
 * 		���ַ������ﵽ���Ƶ�ʱ���޸����ö���Ĵ�С��
 * �ô�2����������ֱ�ӣ����ʹ��array������T����ֱ�Ӵ������飬ֻ��ͨ������Object[]��
 * 		����ת��������
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
