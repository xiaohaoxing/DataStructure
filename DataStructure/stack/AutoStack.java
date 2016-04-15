package stack;

public class AutoStack<T> implements Stack<T> {
	private T[] stack;
	private int position;
	/**
	 * default size of the stack is 100
	 */
	@SuppressWarnings("unchecked")
	public AutoStack(){
		stack=(T[]) new Object[100];
		position=-1;
	}
	/**
	 * constructor with a size of stack
	 * @param size	the size of the stack
	 */
	@SuppressWarnings("unchecked")
	public AutoStack(int size){
		stack=(T[]) new Object[size];
		position=-1;
	}
	/**
	 * insert a value to the top of the stack
	 * 
	 * @param value
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void push(T value){
		if(position>=stack.length-1){
			int size=stack.length;
			T[] newStack=(T[])new Object[size*2];
			stack=copyArray(stack,newStack);
			System.out.println("the size of stack is too small,expand to "+stack.length);
			push(value);
		}else{
			position++;
			stack[position]=value;
		}
	}
	/**
	 * get the top value of the stack
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T pop(){
		if(!isEmpty()){
			if(position<stack.length/4){
				int size=stack.length;
				T[] newStack=(T[])new Object[Math.round(size/2)];
				stack=copyArray(stack,newStack);
				System.out.println("the size is too large,shrink to "+stack.length);
			}
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
	private static<T> T[] copyArray(T[] raw,T[] target){
		int size=Math.min(raw.length, target.length);
		for(int i=0;i<size;i++){
			target[i]=raw[i];
		}
		return target;
	}
}
