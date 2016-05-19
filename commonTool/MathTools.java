package commonTool;

import java.util.ConcurrentModificationException;

public class MathTools {
	public static int getNextPrime(int num){
		int next=num;
		while(true){
			if(isPrime(next)){
				return next;
			}
			next++;
		}
	}
	@SuppressWarnings("unchecked")
	private static boolean isPrime(int num){
		for(int i=2;i<num/2;i++){
			if(num%i==0){
				return false;
			}
		}
		return true;
	}
	public static void main(String args[]){
		System.out.println(getNextPrime(24));
	}
	public void test(){
		try{
			
		}catch(Exception e){
			throw new ConcurrentModificationException();
		}
	}
}
