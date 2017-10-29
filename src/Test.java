import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;
public class Test {
    public static void main(String[] args) throws EmptyStackException {
        
        Scanner sc = new Scanner(System.in);
        Stack<Integer> nums = new Stack<Integer>(); 
        Stack<Character> opes = new Stack<Character>(); 
        String string = sc.nextLine();
        sc.close();
        int n = 0;
        char[] cs = string.toCharArray();
        ArrayList<Object> arr = new ArrayList<Object>();
        for (int i = 0; i < cs.length; i++) {
            char temp = cs[i];
            if (Character.isDigit(cs[i])) {
            	n = 10*n + Integer.parseInt(String.valueOf(cs[i])); 
            } 
            else {
                if (n != 0) {
                    nums.push(n);
                    arr.add(n);
                
                    n = 0;
                }
                if (temp == '(') {
                    opes.push(temp);
                } 
                else if (temp == ')') {
                    while (opes.peek() != '(') { 
                    	arr.add(opes.peek());
                        int t = cal(nums.pop(), nums.pop(), opes.pop());
                         nums.push(t);
                    }
                   opes.pop();
                 } 
                else if (isType(temp) > 0) {
                    if (opes.isEmpty()) { 
                        opes.push(temp);
                    } 
                    else {
                
                        if (isType(opes.peek()) >= isType(temp)) {
                            int t = cal(nums.pop(), nums.pop(), opes.pop());
                            nums.push(t);
                        }
                        opes.push(temp);
                    }
                }
            }
        }
       
        if (n != 0) {
            nums.push(n);
            arr.add(n);
        }
        while (!opes.isEmpty()) {
        	arr.add(opes.peek());
            int t = cal(nums.pop(), nums.pop(), opes.pop());
            nums.push(t);
        }
        System.out.println(nums.pop());
        for (int i=0;i<arr.size();i++) {
        	System.out.print(arr.get(i));
        }
    }
  
    public static int isType(char c) {
        if (c == '+' || c == '-') {
            return 1;
        }
        else if (c == '*' || c == '/') {
            return 2;
        } 
        else {
            return 0;
        }
    }

  
    public static int cal(int m, int n, char c) {
       int sum = 0;
       switch (c) {
       case '+' :
    	   sum = m+n;
    	   break;
       case '-' :
    	   sum = m-n;
    	   break;
       case '*' :
    	   sum = m*n;
    	   break;
       case '/' :
    	   sum = m/n;
    	   break;
       }
       return sum;
    }
}