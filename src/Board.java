
public class Board {
	int [][] masu = new int[8][8];

	public Board() {
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				masu[i][j] = 0;
			}
		}
		//‰Šú”z’u
		masu[3][3] = 1; masu[4][3] = 2;
		masu[3][4] = 2; masu[4][4] = 1;
	}

	public void show() {
		System.out.println("  1 2 3 4 5 6 7 8");
		for(int i=0; i<8; i++) {
			System.out.print(i+1);
			for(int j=0; j<8; j++) {
				switch(masu[i][j]) {
				case 0:	System.out.print(" +"); break;
				case 1:	System.out.print(" œ"); break;
				case 2:	System.out.print(" ›"); break;
				}
			}
			System.out.println();
		}
	}

}
