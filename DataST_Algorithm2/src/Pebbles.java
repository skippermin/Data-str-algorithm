
public class Pebbles {
	int [][] peb;
	int [][] memo;
	int [][] nextPattern = { // 행이 p일 때 나올 수 있는 다음 Pattern의 경우의 수
			{0, 0, 0},
			{2, 3, 0},  // 패턴 1을 사용하는 경우는 행이 2, 3 
			{1, 3, 4},  // 패턴 2를 사용하는 경우는 행이 1, 3, 4
			{1, 2, 0},  // 패턴 3을 사용하는 경우는 행이 1, 2
			{2, 0, 0}}; // 패턴 4를 사용하는 경우는 행이 2
	int nCols;
	int count;
	
	public Pebbles(int[][] input) {
		peb = input;
		nCols = peb[0].length;
		memo = new int[5][nCols];
	}
	void reset() {
		count = 0;
		for(int mi = 0; mi<5; mi++) {
			for(int mj = 0; mj<nCols; mj++) {
				memo[mi][mj] = -99999;
			}
		}
	}
	int getCount() {
		return count;
	}
	public int maxPebble(int n) {
		int max = -99999;
		for(int j = 1; j<=4; j++) { // 패턴 1 ~ 4인 경우
			max = Math.max(pebble(n, j), max);
		}
		return max;
	}
	private int pebble(int i, int p) {
		count++;
		if(i == 1) {
			return aPatternValue(i, p);
		}else {
			int max = -99999;
			int k = 0; 
			while(k<3&&nextPattern[p][k]!=0) { // 패턴 p를 사용할 때, k: 패턴에서 나올 수 있는 경우의 수
				int q = nextPattern[p][k];
				max = Math.max(pebble(i-1, q), max);  /// 패턴에서 나올 수 있는 모든 경우의 수를 따진다.
				k++;
			}
			return aPatternValue(i, p) + max;
		}
	}
	
	private int aPatternValue(int i, int p) {
		int retVal = 0;
		switch(p) {
		case 1: retVal = peb[1][i]; // 첫 번째 패턴일 때의 input 값
		break;
		case 2: retVal = peb[2][i]; // 두 번째 패턴일 때의 input 값
		break;
		case 3: retVal = peb[3][i]; // 세 번째 패턴일 때의 input 값
		break;
		case 4: retVal = peb[1][i] + peb[3][i]; // 네 번째 패턴일 때의 input 값
		break;
		}
		return retVal;
	}
		
	public static void main(String[] args) {
		int [][] input = {
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 6, 7, 12, -5, 5, 3, 11, 3, 7, -2, 5, 4},
				{0, -8, 10, 14, 9, 7, 13, 8, 5, 6, 1, 3, 9},
				{0, 11, 12, 7, 4, 8, -2, 9, 4, -4, 3, 7, 10}};
			Pebbles myPeb = new Pebbles(input);
			for(int i = 1; i<input[0].length ; i++) {
				myPeb.reset();
				System.out.printf(">>> %3d : [Recursion] %3d Count = %-10d", i, myPeb.maxPebble(i), myPeb.getCount());
			}

	}

}
