package stack;

import java.util.Scanner;

/**
 * RPN (Reverse Polish Notation) 逆波兰表达式
 * @author Star
 *
 */
public class RPN {
	/**
	 * 将一个中缀表达式转化成后缀表达式
	 * @param exp
	 * @return	String[]形式的后缀表达式
	 */
	public static String reverse(String exp){
		char[] in=exp.toCharArray();
		SimpleStack<Character> signal=new SimpleStack<>(10);
		signal.push('#');
		StringBuilder result=new StringBuilder();
		String toNum="";
		for(int i=0;i<in.length;i++){
			switch(getType(in[i])){
			case CType.NUMBER:
				toNum+=in[i];
				break;
			case CType.SIGNAL:
				if(!toNum.equals("")){
					result.append(" "+toNum);
					toNum="";
				}
				switch(in[i]){
				case '(':
					signal.push(in[i]);
					break;
				case ')':
					while(signal.top()!='('){					
						result.append(" "+signal.pop());
					}
				default:
					switch(signal.top()){
					case '(':
						signal.push(in[i]);
						break;
					default:
						if(getPriority(in[i])>getPriority(signal.top())){
							signal.push(in[i]);
						}else{
							result.append(" "+signal.pop());
							while(getPriority(in[i])>=getPriority(signal.top())&&signal.top()!='('&&signal.top()!='#'){
								result.append(" "+signal.pop());
							}
							signal.push(in[i]);
						}
					}
				}
			}
			
		}
		if(toNum!=""){
			result.append(" "+toNum);
		}
		if(!signal.isEmpty()){
			while(signal.top()!='#'){
				result.append(" "+signal.pop());
			}
		}
		return result.toString();
	}
	public static void main(String args[]){
		Scanner in=new Scanner(System.in);
		while(true){
			System.out.println("please input the expression:");
			String exp=in.nextLine();
			if(exp.length()==0){
				break;
			}
			String postExp=reverse(exp);
			System.out.println(postExp);
		}
		in.close();
	}
	/**
	 * 获取一个char的类型
	 * @param in	输入的字符
	 * @return		CType中NUMBER或SIGNAL
	 */
	public static int getType(char in){
		switch(in){
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
		case '.':
			return CType.NUMBER;
		case '+':
		case '-':
		case '*':
		case '/':
		case '(':
		case ')':
		case '^':
			return CType.SIGNAL;
		default:
			return CType.UNKNOWN;
		}
	}
	public static int getPriority(char in){
		switch(in){
		case '#':
			return 0;
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		default:
			return 1;
		}
	}
}
class CType{
	public static final int NUMBER=1;
	public static final int SIGNAL=2;
	public static final int UNKNOWN=3;
}