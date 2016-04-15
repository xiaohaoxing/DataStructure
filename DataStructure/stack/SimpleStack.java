package stack;

public class SimpleStack <T> implements Stack<T>{
	private T[] stack;
	private int position;
	/**
	 * default size of the stack is 100
	 */
	@SuppressWarnings("unchecked")
	public SimpleStack(){
		stack=(T[]) new Object[100];
		position=-1;
	}
	/**
	 * constructor with a size of stack
	 * @param size	the size of the stack
	 */
	@SuppressWarnings("unchecked")
	public SimpleStack(int size){
		stack=(T[]) new Object[size];
		position=-1;
	}
	/**
	 * insert a value to the top of the stack
	 * (can think about to increase the size of the stack when overflow)
	 * @param value
	 */
	@Override
	public void push(T value){
		if(position>=stack.length-1){
			System.out.println("stack over flow!");
		}else{
			position++;
			stack[position]=value;
		}
	}
	/**
	 * get the top value of the stack
	 * (can think about to shrink the size of the stack when position less than a quarter of the size)
	 * @return
	 */
	@Override
	public T pop(){
		if(!isEmpty()){
			return stack[position--];
		}else{
			System.out.println("empty stack!");
			return null;
		}
	}
	@Override
	public boolean isEmpty(){
		return position<=-1?true:false;
	}
	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<position+1;i++){
			sb.append(stack[i].toString()+",");
		}
		sb.append(stack[position].toString());
		return sb.toString();
	}
	/**
	 * this ? signal shows that you have to clarify the type of the generic 
	 * @param stack		a created stack
	 */
	public static void printStack(SimpleStack<?> stack){
		System.out.println(stack.toString());
	}
}
