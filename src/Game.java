import java.util.Scanner;

public class Game {

	public void start() {
		int x;
		int y;
		boolean blackTurn;

		blackTurn = true;
		System.out.println("�Q�[���X�^�[�g�I");
		Board board = new Board();
		CPU cpu = new CPU();
		int inputCPU;
		board.show();
		Scanner scan = new Scanner(System.in);
		//���C�����[�v
		while(true){
			r:if(!blackTurn) {
				System.out.println("���͂��Ă��������B(CPU)����");
				sleep(1000);
				while(true) {

					for(int i=0; i<8; i++) {
						for(int j=0; j<8; j++) {
							inputCPU = cpu.masuC[i][j];
							x = inputCPU / 10;
							y = inputCPU % 10;

							if(!flipAll(x, y, board, blackTurn)) {
								continue;
							}else {
								System.out.println((y+1)+""+(x+1));
								board.show();
								//��Ԃ̌��
								blackTurn = !blackTurn;
								break r;
							}
						}
					}

				}

			}
		if(blackTurn){
			System.out.println("���͂��Ă��������B����");

			int input = scan.nextInt();
			if(input==999) {
				System.out.println("�I�����܂��B");
				break;
			}
			y = input / 10 - 1;
			x = input % 10 - 1;

			if(!flipAll(x, y, board, blackTurn)) {
				System.out.println("�R�}���u���܂���B��蒼���Ă��������B��");
				board.show();
				continue;
			}
			board.show();
			//��Ԃ̌��
			blackTurn = !blackTurn;
		}
		}
	}

	private boolean flipAll(int x, int y, Board board, boolean player1) {
		//�R�}�𗠕Ԃ������ǂ���
		boolean isOK = false;

		if(board.masu[x][y] != 0) { return isOK;}


		if(checkLine(-1, 0, x, y, board, player1)) {
			flip(-1, 0,x, y, board, player1); isOK = true;}
		if(checkLine(-1, -1, x, y, board, player1)) {
			flip(-1, -1,x, y, board, player1); isOK = true;}
		if(checkLine(0, -1, x, y, board, player1)) {
			flip(0, -1,x, y, board, player1); isOK = true;}
		if(checkLine(1, -1, x, y, board, player1)) {
			flip(1, -1,x, y, board, player1); isOK = true;}
		if(checkLine(1, 0, x, y, board, player1)) {
			flip(1, 0,x, y, board, player1); isOK = true;}
		if(checkLine(1, 1, x, y, board, player1)) {
			flip(1, 1,x, y, board, player1); isOK = true;}
		if(checkLine(0, 1, x, y, board, player1)) {
			flip(0, 1,x, y, board, player1); isOK = true;}
		if(checkLine(-1, 1, x, y, board, player1)) {
			flip(-1, 1,x, y, board, player1); isOK = true;}

		return isOK;

	}
	/**
	 *
	 * @param directionX
	 * @param directionY
	 * @param x
	 * @param y
	 * @param board
	 * @param blackTurn
	 * @return �R�}�𗠕Ԃ��邩�ǂ���
	 */
	private boolean checkLine(int directionX, int directionY, int x, int y, Board board, boolean blackTurn) {
		int myColor;
		int opponentColor;
		boolean foundMyColor = false;//������ɒT���Ă������Ƃ��A�ŏI�I�Ɏ����̐F�̃R�}����������

		if(blackTurn) { myColor = 1; opponentColor = 2; }else { myColor = 2; opponentColor = 1; }


		//�����v�m�F
		try {

			int count = 0;//�m�F�����R�}�̐�
			if(checkNext(directionX, directionY, x, y, board, blackTurn)==opponentColor) {
				int i = 1;
				foundMyColor = false;
				while(true) {
					if(checkNext(directionX + directionX*i, directionY + directionY*i, x, y, board, blackTurn)==opponentColor) {
						count++;
						i++;
						continue;
					} else if(checkNext(directionX + directionX*i, directionY + directionY*i, x, y, board, blackTurn)==myColor) {

						foundMyColor =  true;
						break;
					}else {
						break;
					}
					//					count++;
					//					i++;
				}

			}else { foundMyColor = false;}} catch (ArrayIndexOutOfBoundsException e) {
				foundMyColor = false;
			}
			return foundMyColor;
	}
	/**
	 *
	 * @param directionX
	 * @param directionY
	 * @param x
	 * @param y
	 * @param board
	 * @param blackTurn
	 * @return ����̐F�̃R�}����������opponentColor�A�����̐F�̃R�}����������myColor�A���������Ȃ�Ȃ�O��Ԃ�
	 */
	private int checkNext(int directionX, int directionY, int x, int y, Board board, boolean blackTurn) {
		int myColor;
		int opponentColor;

		if(blackTurn) { myColor = 1; opponentColor = 2; }else { myColor = 2; opponentColor = 1; }

		try {
			if(board.masu[x+directionX][y+directionY]==opponentColor) {
				return opponentColor;
			}else if(board.masu[x+directionX][y+directionY]==myColor) {
				return myColor;
			}else return 0;
		} catch (ArrayIndexOutOfBoundsException e) {
			//
			return 0;
		}
	}


	/**
	 *
	 * @param directionX
	 * @param directionY
	 * @param x
	 * @param y
	 * @param board
	 * @param player1
	 */
	private void flip(int directionX, int directionY,int x, int y, Board board, boolean blackTurn) {
		int i = 1;
		int myColor;
		int opponentColor;
		if(blackTurn) { myColor = 1; opponentColor = 2; }else { myColor = 2; opponentColor = 1;}

		while(true) {
			//�Ђ�����Ԃ�����
			if(board.masu[x+directionX*i][y+directionY*i]==opponentColor) {
				board.masu[x+directionX*i][y+directionY*i] = myColor;
				i++;
			} else {
				break;
			}
		}
		board.masu[x][y] = myColor;
	}



	private void sleep(int millsec) {
		try {
			Thread.sleep(millsec);
		} catch (InterruptedException e) {
			//
			e.printStackTrace();
		}
	}
}


