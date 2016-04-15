package array;

public class Sort{
	public static void main(String args[]){
		Number[] list={5,12,3,1,16};
		Number[] newList=insertSort(list);
		printArray(newList);
	}
	public static<T extends Number> T[] insertSort(T[] raw){
		int size=raw.length;
		for(int end=1;end<size;end++){
			int pos=end-1;
			T toNum=raw[end];
			if(toNum.doubleValue()<raw[pos].doubleValue()){
				System.out.println("number "+toNum.toString()+" inserting:");
				while(raw[pos].doubleValue()>toNum.doubleValue()){
					raw=swap(raw,pos,pos+1);
					System.out.println("swap "+raw[pos].toString()+" and "+raw[pos+1].toString());
					pos--;
					if(pos<0) break;		//到头了
				}
			}else{
				System.out.println(toNum.toString()+" is already in position!");
				//toNum is already the max
			}
		}
		return raw;
	}
	public static<T extends Number> T[] bubbleSort(T[] raw){
		int size=raw.length;
		for(int end=size;end>=0;end--){
			int max=0;
			for(int j=1;j<end;j++){
				System.out.print(raw[max].toString());
				System.out.print("和");
				System.out.print(raw[j].toString());
				//for generic cannot compare value,have to compare the double value of each.
				if(raw[j].doubleValue()<raw[max].doubleValue()){
					System.out.println("交换！");
					raw=swap(raw,j,max);
				}else{
					System.out.println("不交换！");
					
				}
				max=j;				//交换与否都要更新max位置
				printArray(raw);
			}
		}
		return raw;
	}
	public static<T extends Number> T[] selectSort(T[] raw){
		int size=raw.length;
		for(int end=size-1;end>0;end--){
			//每趟默认最大的在最后一个，跟默认第一个区别不清楚
			// TODO test the influence of default max value position
			int max=end;
			for(int i=0;i<=end;i++){
				System.out.print(raw[i].toString());
				System.out.print("和");
				System.out.print(raw[max].toString());
				if(raw[i].doubleValue()>raw[max].doubleValue()){
					System.out.println(": "+raw[i].toString()+"更大");
					max=i;
				}else{
					System.out.println(": "+raw[max].toString()+"更大");
				}
			}
			System.out.println("这趟最大的在"+max);
			if(max==end){
				System.out.println("max is on position!");
			}else{
				raw=swap(raw,max,end);
				System.out.println("swap to end!");
			}
			printArray(raw);
			
			
		}
		return raw;
	}
	public static<T extends Number> T[] swap(T[] arr,int a,int b){
		T at=arr[a];
		T bt=arr[b];
		arr[a]=bt;
		arr[b]=at;
		return arr;
	}
	public static<T extends Number> void printArray(T[] arr){
		int size=arr.length-1;
		for(int i=0;i<size;i++){
			System.out.print(arr[i]+",");
		}
		System.out.println(arr[size]);
	}
}
