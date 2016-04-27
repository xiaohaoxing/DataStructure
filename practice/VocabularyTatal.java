package practice;

import java.util.Scanner;

public class VocabularyTatal {

	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		int[] lengths=new int[20];
		String spaces	=	"  ";
		String blank	=	"   ";
		String contain	=	"***";
		for(int i=0;i<20;i++){
			lengths[i]=0;
		}
		System.out.println("Please input vocabularys:(enter to exit input)");
		String line=in.nextLine();
		while(true){
			lengths[line.length()]++;
			line=in.nextLine();
			if(line.equals("")){
				break;
			}
		}
		System.out.println("input finished");
		//get the max length and the max times
		int longest=19; 
		for(int i=longest;i>0;i--){
			if(lengths[i]>0)
				break;
			longest--;
		}
		int times=0;
		for(int i=longest;i>0;i--){
			if(lengths[i]>times)
				times=lengths[i];
		}
		for(int i=times;i>0;i--){				//逐行向下
			StringBuilder sb=new StringBuilder(i+spaces);
			for(int x=1;x<=longest;x++){		//逐列向后
				sb.append(lengths[x]>=i?contain:blank);
				sb.append(spaces);
			}
			System.out.println(sb.toString());
		}
		
	}

}
