package list;

public class SimpleList {
	public Node head;
	public SimpleList(){
		head=null;
	}
	public int getMax(){
		int max=head.value;
		Node temp=head;
		while(temp!=null){
			if(temp.value>max)
				max=temp.value;
			temp=temp.next;
		}
		return max;
	}
	/**
	 * 删除范围内所有元素：Range=(min,max);
	 * 要求链表是按照大小顺序排序的。
	 * @param min
	 * @param max
	 */
	public void delRange(int min,int max){
		Node before=null;
		Node temp=head;
		//1.先在链表中找最小值
		while(temp.next.value<min&&temp!=null){
			temp=temp.next;
		}
		//2.如果到最后都没发现大于等于min的值
		if(temp==null){
			System.out.println("所有元素都比"+min+"小，找不到！");
			return;
		}
		//3.标记第一个大于min的值
		before=temp;		//这是第一个比min大的元素的位置
		//4.再链表min位置开始找比最大值大的元素的位置
		while(temp.value<max&&temp!=null){
			temp=temp.next;
		}
		//可能找到了最后（null），可能找到了第一个比max大的元素（temp），都只要把before的next设置成temp就行了
		before.next=temp;
		return;
	}
	public String toString(){
		Node temp=head;
		StringBuilder sb=new StringBuilder("");
		while(temp!=null){
			sb.append(temp.value+"-");
			temp=temp.next;
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	public static void main(String args[]){
		Node a=new Node(100);
		Node b=new Node(200);
		Node c=new Node(300);
		Node d=new Node(310);
		a.next=b;
		b.next=c;
		c.next=d;
		SimpleList list=new SimpleList();
		list.head=a;
		System.out.println(list.toString());
		list.delRange(210, 310);
		System.out.println(list.toString());
		
		
		System.out.println(list.getMax());
		//(head)a(100)->b(200)->c(300)
	}
}
class Node{
	public Node(int value){
		this.value=value;
	}
	public int value;
	public Node next;
}
