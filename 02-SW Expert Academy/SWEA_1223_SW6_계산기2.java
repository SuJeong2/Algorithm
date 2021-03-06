package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA_1223_SW6_계산기2 {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static Stack<String> stack = new Stack<String>();
	static Stack<Integer> stack2 = new Stack<Integer>();
	static StringBuilder sb = new StringBuilder();
	static int n;
	static String line;
	
	public static void main(String[] args) throws IOException {
		
		
		for(int tc=1; tc<=10; tc++) {
			
			n = Integer.parseInt(reader.readLine());
			
			line = reader.readLine();
			
			//후위계산식 만들기, 출력
			getPostfix();
			System.out.println(sb);
			
			//계산하기
			int result = Calculate();
			System.out.println("#"+tc+" "+result);
		}
		
	}
	
	public static void getPostfix() {
		for(int i=0; i<n; i++) {
			
			char c = line.charAt(i);
			if(c=='+' || c=='*') {
				if(stack.isEmpty()) {
					stack.push(c+"");
					continue;
				}
				
				char top = stack.peek().charAt(0);
				//우선순위 부여하기
				int top_ = (top=='*')? 1: 0;
				int c_ = (c=='*')? 1: 0;
				
				//top엔 *, 현재 부호는 +일 경우
				if(top_>c_) {
					while(!stack.isEmpty()) {
						sb.append(stack.pop()+" ");							
					}
					stack.push(c+"");
				}else {
					stack.push(c+"");
				}
			}
			
			else {
				sb.append(c+" ");
			}
		}
		
		while(!stack.isEmpty())
			sb.append(stack.pop()+" ");
	}
	
	public static int Calculate() {
		n = sb.length();
		int cal = 0;
		
		for(int i=0; i<n; i+=2) {
			char c = sb.charAt(i);
			if(c!='+' && c!='*') {
				int v = c -(int)'0';
				stack2.push(v);
			}
			else {
				int A = stack2.pop();
				int B = stack2.pop();

				if(c=='+') {
					cal=B+A;
					stack2.push(cal);
				}
				else {
					cal=B*A;
					stack2.push(cal);
				}
			}
		}
		
		return stack2.pop();
	}
}
