import java.util.Scanner;

public class Game {

	public void start() {
		int x;
		int y;
		boolean blackTurn;

		blackTurn = true;
		System.out.println("ゲームスタート！");
		Board board = new Board();
		CPU cpu = new CPU();
		int inputCPU;
		board.show();
		Scanner scan = new Scanner(System.in);
		//メインループ
		while(true){
			r:if(!blackTurn) {
				System.out.println("入力してください。(CPU)＞白");
				while(true) {
					
					for(int i=0; i<8; i++) {
						for(int j=0; j<i; j++) {
							inputCPU = cpu.masuC[i][j];
							x = inputCPU / 10;
							y = inputCPU % 10;
							
							if(!flipAll(x, y, board, blackTurn)) {
								continue;
							}else {
									System.out.println((x+1)+""+(y+1));
								board.show();
								//手番の交代
								blackTurn = !blackTurn;
								break r;
							}
						}
					}
					
				}
				
			}else {
				System.out.println("入力してください。＞黒");
			
			int input = scan.nextInt();
			if(input==999) {
				System.out.println("終了します。");
				break;
			}
			x = input / 10 - 1;
			y = input % 10 - 1;

			if(!flipAll(x, y, board, blackTurn)) {
				System.out.println("コマが置けません。やり直してください。＞");
				board.show();
				continue;
			}
			board.show();
			//手番の交代
			blackTurn = !blackTurn;
			}
		}
	}

	private boolean flipAll(int x, int y, Board board, boolean player1) {
		//コマを裏返したかどうか
		boolean isOK = false;

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
	 * @return コマを裏返せるかどうか
	 */
	private boolean checkLine(int directionX, int directionY, int x, int y, Board board, boolean blackTurn) {
		int myColor;
		int opponentColor;
		boolean foundMyColor = false;//直線上に探していったとき、最終的に自分の色のコマを見つけたか

		if(blackTurn) { myColor = 1; opponentColor = 2; }else { myColor = 2; opponentColor = 1; }

		int count = 0;//確認したコマの数
		if(checkNext(directionX, directionY, x, y, board, blackTurn)==opponentColor) {
			int i = 1;

			//ここ要確認
			try {
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
			} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}


		}else { foundMyColor = false;}
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
	 * @return 相手の色のコマがあったら２、自分の色のコマがあったら１、何も無いならなら０を返す
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
			//ひっくり返す処理
			if(board.masu[x+directionX*i][y+directionY*i]==opponentColor) {
				board.masu[x+directionX*i][y+directionY*i] = myColor;
				i++;
			} else {
				break;
			}
		}
		board.masu[x][y] = myColor;
	}
}


