
import java.util.Arrays;

public class Fibonacci {
	int count;
	int [] memo = new int [30];
	
	public void reset() {
		count = 0;
		Arrays.fill(memo, 0);
		memo[1] = 1;
		memo[2] = 1;
	}
	public int getCount() {
		return count;
	}
	public int iter(int n) {
		int fa = 1, fb = 1;
		for (int i = 3; i < n; i++) {
			count++; //
			int temp = fa;
			fa = fb;
			fb = fb + temp;
		}
		return fb;
	}
	
	public int rec(int n) {
		count++;
		if (n<=2) return 1;
		else return rec(n-1) + rec(n-2);
	}
	
	public int DP(int n) {
		count++;
		if (memo[n] != 0) return memo[n];
		else {
			memo[n] = DP(n-1) + DP(n-2);
			return memo[n];
		}
	}

	public static void main(String[] args) {
		Fibonacci f = new Fibonacci();
		int c1 = 0, c2 = 0, c3 = 0;
		for (int i = 3; i < 30; i++) {
			f.reset();
			f.iter(i);
			c1 = f.getCount();
			
			f.reset();
			f.rec(i);
			c2 = f.getCount();
			
			f.reset();
			f.DP(i);
			c3 = f.getCount();
		}
		System.out.printf("Iteration : %10d    =>   Recursion : %10d    =>   DP : %10d\n", c1, c2, c3);
	}

}
