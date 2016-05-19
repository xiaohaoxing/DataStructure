package hashTable;

public class HashTable <T>{
	private int size;
	private T[] content;
	@SuppressWarnings("unchecked")
	public HashTable(){
		content=(T[]) new Object[100];
		size=100;
	}
	@SuppressWarnings("unchecked")
	public HashTable(int size){
		this.size=size;
		content=(T[])new Object[size];
	}
	public boolean insert(T element){
		int pos=hashFun(element);
		//位置被占用的时候
		if(content[pos]!=null){
			while(content[pos]!=null){
				pos++;
			}
			if(pos<size){
				content[pos]=element;
				return true;
			}
			else
				return false;
		}
		//没有占用：直接填入
		else{
			content[pos]=element;
			return true;
		}
	}
	/**
	 * hash映射函数
	 * 	Integer类型对象结果使用后两位
	 * 	String类型对象结果使用ASCII码后两位
	 * @param element
	 * @return
	 */
	public int hashFun(T element){
		if(element instanceof Integer){
			return (Integer)element%size;
		}else if(element instanceof String){
			char head=((String) element).charAt(0);
			return (int)head%size;
		}else{
			return -1;
		}
	}
	/**
	 * 获取一个哈希码的步长
	 * 
	 * @param code	
	 * @return	step∈(0,5]
	 */
	public int getStep(int code){
		return 5-(code%5);
	}
	public int search(T element){
		int pos=hashFun(element);
		while(pos<size){
			if(content[pos]==null){
				pos++;
				continue;
			}
			if(content[pos].equals(element)){
				break;
			}
			pos++;
		}
		if(pos==size){
			return -1;
		}else{
			return pos;	
		}
	}
	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<size;i++){
			if(content[i]!=null){
				sb.append(i+":"+content[i].toString()+"\n");
			}
		}
		return sb.toString();
	}
	/**
	 * 需要拓展hash储存的数组时，需要生成大小大约是原数组两倍大一点(质数)的数组，
	 * 并将原数组中的所有元素<B>重新哈希化</B>,耗时但是必须。
	 * 
	 */
	
	
	public static void main(String args[]){
		HashTable<String> table=new HashTable<String>();
		table.insert("aaa");
		table.insert("bbb");
		table.insert("123");
		table.insert("112");
		System.out.println(table.toString());
		int apos=table.search("aaa");
		System.out.println("aaa在"+apos);
		int cpos=table.search("ccc");
		System.out.println("ccc在"+cpos);
	}
}
