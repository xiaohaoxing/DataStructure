package graph;

public class MyPoint <T>{
	private T data;
	private boolean flag;
	public MyPoint(T t){
		data=t;
		flag=false;
	}
	public T getData(){
		return data;
	}
	public boolean getFlag(){
		return flag;
	}
	public void resetFlag(){
		flag=false;
	}
	public void visitFlag(){
		flag=true;
	}
	public String toString(){
		return data.toString();
	}
}
