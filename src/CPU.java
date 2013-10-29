
public class CPU {
	int [][] masuC = new int[8][8];
	public CPU() {
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				masuC[i][j] = i*10+j;
			}
		}
	}

}
