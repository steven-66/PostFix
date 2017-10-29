/**
 * 
 */
/**
 * @author Administrator
 *
 */
package postfix;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class PostFix{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		sc.close();
		Stack<Integer> num = new Stack<Integer>();
		Stack<Character> oper = new Stack<Character>();
		char[] ch = line.toCharArray();
		ArrayList<Object> arr=new ArrayList<Object>();
		int n = 0;
		for (int i=0; i<ch.length;i++) {
			char temp =ch[i];
			if (Character.isDigit(ch[i])) {
				n = n*10+Integer.parseInt(Character.toString(ch[i]));
			}
			else {
				if(n!=0) {
					num.push(n);
					arr.add(n);
					n=0;
				}
				if(temp=='(') {
					oper.push(temp);
			
				}
				else if (temp==')') {
					while (oper.peek()!=')') {
						arr.add(oper.peek());
						int	result=operation(num.pop(),num.pop(),oper.pop());
						num.push(result);
					
					}
					oper.pop();
				} 
				else if(operatorType(temp)>0) {
						if(oper.isEmpty()) {
							oper.push(temp);
						}
						else {
							if(operatorType(oper.peek())>=operatorType(temp)) {
							int result = operation(num.pop(),num.pop(),oper.pop());
							num.push(result);
							}
							oper.push(temp);
						}	
					}
				
			}
		}
		if (n!=0) {
			num.push(n);
			arr.add(n);
		}
		while(!oper.isEmpty()) {
			arr.add(oper.peek());
			int result = operation(num.pop(),num.pop(),oper.pop());
			num.push(result);

		}
			System.out.println(num.peek());
	}
			
				
		

	public	static int operatorType(char c) {
		if (c=='+'|| c=='-') {
			return 1;
		}
		else if (c == '*' || c == '/') {
	         return 2;
	        } 
	    else {
	         return 0;
	        }
	}
	public static int operation(int x,int y,char z) {
		   int sum = 0;
	       switch (z) {
	       case '+' :
	    	   sum = x+y;
	    	   break;
	       case '-' :
	    	   sum = x-y;
	    	   break;
	       case '*' :
	    	   sum = x*y;
	    	   break;
	       case '/' :
	    	   sum = x/y;
	    	   break;
	       }
	       return sum;
	    
	}
}	