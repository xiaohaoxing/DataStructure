package array;
/**
 * ����δ��Ҳ̫����
 * �������ˡ�����
 * @author Star
 *
 */
public class LowArray {
	private long[] a;
	/**
	 * constructor
	 * @param size
	 */
	public LowArray(int size){
		a=new long[size];
	}
	public void setElem(int index,long value){
		a[index]=value;
	}
	public long getElem(int index){
		return a[index];
	}
	
}
class LowArrayApp{
	public static void main(String[] args){
		LowArray arr;
		arr=new LowArray(100);
		arr.setElem(0, 77);
	}
}