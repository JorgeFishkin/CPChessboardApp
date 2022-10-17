public class Piece {
	String name;
	String type;
	
	public Piece(String n, String t) {
		name = n;
		type = t;
	}
	
	//board array of pieces
	static Piece[][] board = new Piece[8][8];
	//black pieces that have been captured
	static Piece[] black = new Piece[16];
	//white pieces that have been captured
	static Piece[] white = new Piece[16];
	//move count
	static int moveCount = 0;
	
	static boolean kingInCheck = false;
	
	static int[][] cds = {{65,66,67,68,69,70,71,72},
							{73,74,75,76,77,78,79,80},
							{81,82,83,84,85,86,87,88},
							{89,90,91,92,93,94,95,96},
							{97,98,99,100,101,102,103,104},
							{105,106,107,108,109,110,111,112},
							{113,114,115,116,117,118,119,120},
							{121,122,123,124,125,126,127,128}};
	
	//white pieces
			static Piece wp1 = new Piece("wp1","pawn");
			static Piece wp2 = new Piece("wp2","pawn");
			static Piece wp3 = new Piece("wp3","pawn");
			static Piece wp4 = new Piece("wp4","pawn");
			static Piece wp5 = new Piece("wp5","pawn");
			static Piece wp6 = new Piece("wp6","pawn");
			static Piece wp7 = new Piece("wp7","pawn");
			static Piece wp8 = new Piece("wp8","pawn");
			static Piece wr1 = new Piece("wr1","rook");
			static Piece wr2 = new Piece("wr2","rook");
			static Piece wk1 = new Piece("wk1","knight");
			static Piece wk2 = new Piece("wk2","knight");
			static Piece wb1 = new Piece("wb1","bishop");
			static Piece wb2 = new Piece("wb2","bishop");
			static Piece wq = new Piece("wq","queen");
			static Piece wk = new Piece("wk","king");
			
			//black pieces
			static Piece bp1 = new Piece("bp1","pawn");
			static Piece bp2 = new Piece("bp2","pawn");
			static Piece bp3 = new Piece("bp3","pawn");
			static Piece bp4 = new Piece("bp4","pawn");
			static Piece bp5 = new Piece("bp5","pawn");
			static Piece bp6 = new Piece("bp6","pawn");
			static Piece bp7 = new Piece("bp7","pawn");
			static Piece bp8 = new Piece("bp8","pawn");
			static Piece br1 = new Piece("br1","rook");
			static Piece br2 = new Piece("br2","rook");
			static Piece bk1 = new Piece("bk1","knight");
			static Piece bk2 = new Piece("bk2","knight");
			static Piece bb1 = new Piece("bb1","bishop");
			static Piece bb2 = new Piece("bb2","bishop");
			static Piece bq = new Piece("bq","queen");
			static Piece bk = new Piece("bk","king");
	
	public static void main(String[] args) {
	
		/*insertPiece(wk1,"A",6,"N",0);
		insertPiece(bk,"C",2,"N",0);
		insertPiece(bq,"H",8,"N",0);
		insertPiece(wk1,"B",4,"A",6);
		insertPiece(bq,"H",1,"H",8);
		insertPiece(bk,"C",3,"C",2);*/
		//setup("black");
		printBoard();
		
	}
	
	public static String getPieceName(Piece p) {
		return p.name;
	}
	
	public static String getPieceType(Piece p) {
		return p.type;
	}
	
	public static String getSquare(Piece p) {
		String toReturn = "none";
		//loop through board
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				//number to letter
				String let = getLet(i+1);
				//if piece name is same as one in square
				if (getPiece(let,j).name.equals(getPieceName(p))) {
					int q = switchNum(j+1);
					//stick letter and num together
					toReturn = ""+let+String.valueOf(q);
				}
			}
		}
		return toReturn;
	}
	
	public static Piece getPiece(String a, int b) {
		//letter to number
		int x = getNum(a);
		b = switchNum(b);
		Piece p = new Piece("none","none");
		//make piece to return
		if (board[x-1][b-1]!=null) {
			p.name = board[x-1][b-1].name;
			p.type = board[x-1][b-1].type;
			p = board[x-1][b-1];
		}
		return p;
	}
	

	
	public static void insertPiece(Piece p, String a, int b, String prev1, int prev2b) {
		//increase move count
		moveCount+=1;
		//letter to number
		int x = getNum(a);
		int b2=switchNum(b);
		int prev2 = switchNum(prev2b);
		//piece check method
		boolean check = pieceCheck(p, a, b, prev1, prev2);
		//new piece
		if (prev1.equals("N")) {
			check = true;
			moveCount-=1;
		}
		//legal move
		if (check) {
			if (kingInCheck==true) {
				if (!p.type.equals("king")) {
					System.out.println("Invalid move, must move king out of check.");
				}
				else if (p.type.equals("king")) {
					kingInCheck = false;
				}
			}
			if (kingInCheck == false) {
				board[x-1][b2-1] = p;
				if (prev2b!=0) {
					//delete previous piece
					deletePiece(prev1,prev2b);
					boolean iKicn = false;
					if (!p.type.equals("king")) {
						iKicn = isKingInCheckNow(p,x,b2);
					}
					if (iKicn == true) {
						System.out.println("Opposing King is now in check");
					}// if iKicn=true
				} // if prev2b!=0
			}
		} // if check
		else if (!check) {
			System.out.println("Invalid move.");
			moveCount-=1;
		}
	}
	
	public static void deletePiece(String a, int b) {
		//deletes piece
		int x = getNum(a);
		b = switchNum(b);
		board[x-1][b-1]=null;
	}
	
	public static boolean pieceCheck(Piece p, String as, int b, String prev1s, int prev2) {
		boolean toReturn = false;
		//letters to numbers
		int a = getNum(as);
		int prev1 = getNum(prev1s);
		b=switchNum(b);
		/*System.out.println();
		System.out.println(a);
		System.out.println(b);
		System.out.println(prev1);
		System.out.println(prev2);
		System.out.println();*/
		if (p.type.equals("pawn")) {
			if ((a==prev1-1&&b==prev2-1)||(a==prev1+1&&b==prev2+1)) {
				//pawn diagonal
				if (board[a-1][b-1]!=null) {
					//make sure it can capture piece in square its moving to
					boolean ch = capture(p,as,b,board[a-1][b-1].name.charAt(0),prev1s,prev2);
					if (ch) {
						//move piece if can capture
						board[prev1-1][prev2-1]=null;
						board[a-1][b-1]=p;
						toReturn = true;
					}
				}
			}
			else if ((b == prev2-1)&&(a==prev1)) {
				//pawn straight up 1
					if (board[a-1][b-1]==null) {
						toReturn = true;
					}
				}
			else if ((b == prev2-2)&&(a==prev1)) {
				//pawn straight up 2
				if (moveCount == 1) {
					if (board[a-1][b-2]==null) {
						toReturn = true;
					}
				}
			}
		}
		else if (p.type.equals("king")) {
			boolean checkCheck = false;
			if (prev2 != 0) {
				checkCheck = movingIntoCheck(p,a,b,prev1,prev2);
			}
			//king diagonal
				if (((a==prev1-1)&&(b==prev2-1))||((a==prev1-1)&&(b==prev2+1))||((a==prev1+1)&&(b==prev2-1))||((a==prev1+1)&&(b==prev2+1))) {
					if (board[a-1][b-1]==null) {
						if (checkCheck == false) {
							toReturn = true;
						}
						else if (checkCheck == true){
							System.out.println("King will be in check");
							toReturn = false;
						}
					}
					else if (board[a-1][b-1]!=null) {
						boolean ch = capture(p,as,b,board[a-1][b-1].name.charAt(0),prev1s,prev2);
						if (ch) {
							if (checkCheck == false) {
								board[prev1-1][prev2-1]=null;
								board[a-1][b-1]=p;
								toReturn = true;
							}
							else if (checkCheck == true){
								System.out.println("King will be in check");
								toReturn = false;
							}
						}
					}
				}
				//king straight
				else if (((b==prev2-1)&&(a==prev1))||((b==prev2+1)&&(a==prev1))||((a==prev1-1)&&(b==prev2))||((a==prev1+1)&&(b==prev2))) {
					if (board[a-1][b-1]==null) {
						if (checkCheck == false) {
							toReturn = true;
						}
						else if (checkCheck == true){
							System.out.println("King will be in check");
							toReturn = false;
						}
					}
					else if (board[a-1][b-1]!=null) {
						boolean ch = capture(p,as,b,board[a-1][b-1].name.charAt(0),prev1s,prev2);
						if (ch) {
							if (checkCheck == false) {
								board[prev1-1][prev2-1]=null;
								board[a-1][b-1]=p;
								toReturn = true;
							}
							else if (checkCheck == true){
								System.out.println("King will be in check");
								toReturn = false;
							}
						}
					}
				}
		}
		else if (p.type.equals("knight")) {
			//knight logic
			if (((a==prev1+1)&&(b==prev2-2))||((a==prev1+2)&&(b==prev2-1))||((a==prev1+2)&&(b==prev2+1))||((a==prev1+1)&&(b==prev2+2))||((a==prev1-1)&&(b==prev2+2))||((a==prev1-2)&&(b==prev2+1))||((a==prev1-2)&&(b==prev2-1))||((a==prev1-1)&&(b==prev2-2))) {
				if (board[a-1][b-1]==null) {
					toReturn = true;
				}
				else if (board[a-1][b-1]!=null) {
					boolean ch = capture(p,as,b,board[a-1][b-1].name.charAt(0),prev1s,prev2);
					if (ch) {
						board[prev1-1][prev2-1]=null;
						board[a-1][b-1]=p;
						toReturn = true;
					}
				}
			}
		}
		else if (p.type.equals("rook")) {
			//rook logic
			//loop for multiple options in a direction
			for (int i = 0; i < 8; i++) {
				if (((a==prev1)&&(b==prev2-i))||((a==prev1+i)&&(b==prev2))||((a==prev1)&&(b==prev2+i))||((a==prev1-i)&&(b==prev2))) {
					if (board[a-1][b-1]==null) {
						toReturn = true;
						break;
					}
					else if (board[a-1][b-1]!=null) {
						boolean ch = capture(p,as,b,board[a-1][b-1].name.charAt(0),prev1s,prev2);
						if (ch) {
							board[prev1-1][prev2-1]=null;
							board[a-1][b-1]=p;
							toReturn = true;
							break;
						}
					}
				}
			}
		}
		else if (p.type.equals("bishop")) {
			//bishop logic
			for (int i = 0; i < 8; i++) {
				if (((a==prev1+i)&&(b==prev2+i))||((a==prev1-i)&&(b==prev2-i))||((a==prev1+i)&&(b==prev2-i))||((a==prev1-i)&&(b==prev2+i))) {
					if (board[a-1][b-1]==null) {
						toReturn = true;
						break;
					}
					else if (board[a-1][b-1]!=null) {
						boolean ch = capture(p,as,b,board[a-1][b-1].name.charAt(0),prev1s,prev2);
						if (ch) {
							board[prev1-1][prev2-1]=null;
							board[a-1][b-1]=p;
							toReturn = true;
							break;
						}
					}
				}
			}
		}
		else if (p.type.equals("queen")) {
			//queen logic
			for (int i = 0; i < 8; i++) {
				//diagonal
				if (((a==prev1-i)&&(b==prev2-i))||((a==prev1-i)&&(b==prev2+i))||((a==prev1+i)&&(b==prev2-i))||((a==prev1+i)&&(b==prev2+i))) {
					if (board[a-1][b-1]==null) {
						toReturn = true;
					}
					else if (board[a-1][b-1]!=null) {
						boolean ch = capture(p,as,b,board[a-1][b-1].name.charAt(0),prev1s,prev2);
						if (ch) {
							board[prev1-1][prev2-1]=null;
							board[a-1][b-1]=p;
							toReturn = true;
						}
					}
				}
				//straight
				else if (((b==prev2-i)&&(a==prev1))||((b==prev2+i)&&(a==prev1))||((a==prev1-i)&&(b==prev2))||((a==prev1+i)&&(b==prev2))) {
					if (board[a-1][b-1]==null) {
						toReturn = true;
					}
					else if (board[a-1][b-1]!=null) {
						boolean ch = capture(p,as,b,board[a-1][b-1].name.charAt(0),prev1s,prev2);
						if (ch) {
							board[prev1-1][prev2-1]=null;
							board[a-1][b-1]=p;
							toReturn = true;
						}
					}
				}
			}
		}
		return toReturn;
	}
	
	public static boolean capture(Piece p, String as, int b, char c,String prev1s,int prev2) {
		//capture piece
		boolean ret = false;
		String n = p.name;
		char n1 = n.charAt(0);
		int a = getNum(as);
		//check to make sure it's opposite colored piece
		if (n1 == 'b') {
			if (c == 'w') {
				for (int i = 0; i < 16; i++) {
					if (black[i]==null) {
						black[i] = p;
						ret = true;
						break;
					}
				}
			}
		}
		else if (n1 == 'w') {
			if (c == 'b') {
				for (int i = 0; i < 16; i++) {
					if (white[i]==null) {
						white[i] = p;
						ret = true;
						break;
					}
				}
			}
		}
		return ret;
	}
	
	public static boolean movingIntoCheck(Piece p, int a, int b, int prev1, int prev2) {
		//a = [x][] number
		//b = [][x] correct number
		//prev1 = previous [x][] number
		//prev2 = previous [][x] correct number
		//-1 to variables if dealing with board, otherwise only +- to check equalities
		boolean toReturn = false;
		boolean retthis = false;
		char firstLetter = p.name.charAt(0);
		Piece pc;
		if (p.type.equals("king")) {
			//pawn
			//a+1,b-1
			if (b-2>=0 && a<8) {
				pc = board[a][b-2];
				toReturn = checkCheckMethod(pc,p,"pawn");
				if (toReturn == true) {
					retthis = true;
				}
			}
			//a-1,b-1
			if (a-2>=0 && b-2>=0) {
				pc=board[a-2][b-2];
				toReturn = checkCheckMethod(pc,p,"pawn");
				if (toReturn == true) {
					retthis = true;
				}
			}
			//a-1,b+1
			if (a-2>=0 && b<8) {
				pc=board[a-2][b];
				toReturn = checkCheckMethod(pc,p,"pawn");
				if (toReturn == true) {
					retthis = true;
				}
			}
			//a+1,b+1
			if (a<8 && b<8) {
				pc=board[a][b];
				toReturn = checkCheckMethod(pc,p,"pawn");
				if (toReturn == true) {
					retthis = true;
				}
			}
			
			//knight
			//a+1,b-2
			if (b-3>=0 && a<8) {
				pc=board[a][b-3];
				toReturn = checkCheckMethod(pc,p,"knight");
				if (toReturn == true) {
					retthis = true;
				}
			}
			//a+2,b-1
			if (a+1<8 && b-2>=0) {
				pc=board[a+1][b-2];
				toReturn = checkCheckMethod(pc,p,"knight");
				if (toReturn == true) {
					retthis = true;
				}
			}
			//a+2,b+1
			if (a+1<8 && b<8) {
				pc=board[a+1][b];
				toReturn = checkCheckMethod(pc,p,"knight");
				if (toReturn == true) {
					retthis = true;
				}
			}
			//a+1,b+2
			if (b+1<8 && a<8) {
				pc=board[a][b+1];
				toReturn = checkCheckMethod(pc,p,"knight");
				if (toReturn == true) {
					retthis = true;
				}
			}
			//a-1,b-2
			if (a-2>=0 && b-3>=0) {
				pc=board[a-2][b-3];
				toReturn = checkCheckMethod(pc,p,"knight");
				if (toReturn == true) {
					retthis = true;
				}
			}
			//a-2,b-1
			if (a-3>=0 && b-2>=0) {
				pc=board[a-3][b-2];
				toReturn = checkCheckMethod(pc,p,"knight");
				if (toReturn == true) {
					retthis = true;
				}
			}
			//a-2,b+1
			if (a-3>=0 && b<8) {
				pc=board[a-3][b];
				toReturn = checkCheckMethod(pc,p,"knight");
				if (toReturn == true) {
					retthis = true;
				}
			}
			//a-1,b+2
			if (b+1<8 && a-2>=0) {
				pc=board[a-2][b+1];
				toReturn = checkCheckMethod(pc,p,"knight");
				if (toReturn == true) {
					retthis = true;
				}
			}
			
			
			//king
			//a,b-1
			if (a-1>=0 && b-2>=0) {
				pc=board[a-1][b-2];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
			//a,b+1
			if (a-1>=0 && b<8) {
				pc=board[a-1][b];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
			//a+1,b
			if (b-1>=0 && a<8) {
				pc=board[a][b-1];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
			//a-1,b
			if (a-2>=0 && b-1>=0) {
				pc=board[a-2][b-1];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
			//a-1,b-1
			if (a-2>=0 && b-2>=0) {
				pc=board[a-2][b-2];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
			//a+1,b-1
			if (b-2>=0 && a<8) {
				pc=board[a][b-2];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
			//a+1,b+1
			if (a<8 && b<8) {
				pc=board[a][b];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
			//a-1,b+1
			if (a-2>=0 && b<8) {
				pc=board[a-2][b];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
			
			
			//rook
			//+a
			for (int i = 0; i < 8; i++) {
				if (a-1+i<8 && b-1>=0) {
					pc=board[a-1+i][b-1];
					toReturn = checkCheckMethod(pc,p,"rook");
					if (toReturn == true) {
						retthis = true;
					}
				}
			}
			//-a
			for (int i = 0; i < 8; i++) {
				if (a-1-i>=0 && b-1>=0) {
					pc=board[a-1-i][b-1];
					toReturn = checkCheckMethod(pc,p,"rook");
					if (toReturn == true) {
						retthis = true;
					}
				}
			}
			//+b
			for (int i = 0; i < 8; i++) {
				if (a-1>=0 && b-1+i<8) {
					pc=board[a-1][b-1+i];
					toReturn = checkCheckMethod(pc,p,"rook");
					if (toReturn == true) {
						retthis = true;
					}
				}
			}
			//-b
			for (int i = 0; i < 8; i++) {
				if (a-1>=0 && b-1-i>=0) {
					pc=board[a-1][b-1-i];
					toReturn = checkCheckMethod(pc,p,"rook");
					if (toReturn == true) {
						retthis = true;
					}
				}
			}
			
			//bishop
			for (int i = 0; i < 8; i++) {
			//+a,+b
				if (a-1+i<8 && b-1+i<8) {
					pc=board[a-1+i][b-1+i];
					toReturn = checkCheckMethod(pc,p,"bishop");
					if (toReturn == true) {
						retthis = true;
					}
				}
			//-a,-b
				if (a-1-i>=0 && b-1-i>=0) {
					pc=board[a-1-i][b-1-i];
					toReturn = checkCheckMethod(pc,p,"bishop");
					if (toReturn == true) {
						retthis = true;
					}
				}
			//+a,-b
				if (a-1+i<8 && b-1-i>=0) {
					pc=board[a-1+i][b-1-i];
					toReturn = checkCheckMethod(pc,p,"bishop");
					if (toReturn == true) {
						retthis = true;
					}
				}
			//-a,+b
				if (a-1-i>=0 && b-1+i<8) {
					pc=board[a-1-i][b-1+i];
					toReturn = checkCheckMethod(pc,p,"bishop");
					if (toReturn == true) {
						retthis = true;
					}
				}
			}
			
			//queen
			for (int i = 0; i < 8; i++) {
				//+a
				if (a-1+i<8 && b-1>=0) {
					pc=board[a-1+i][b-1];
					toReturn = checkCheckMethod(pc,p,"queen");
					if (toReturn == true) {
						retthis = true;
					}
				}
				//-a
				if (a-1-i>=0 && b-1>=0) {
					pc=board[a-1-i][b-1];
					toReturn = checkCheckMethod(pc,p,"queen");
					if (toReturn == true) {
						retthis = true;
					}
				}
				//+b
				if (a-1>=0 && b-1+i<8) {
					pc=board[a-1][b-1+i];
					toReturn = checkCheckMethod(pc,p,"queen");
					if (toReturn == true) {
						retthis = true;
					}
				}
				//-b
				if (a-1>=0 && b-1-i>=0) {
					pc=board[a-1][b-1-i];
					toReturn = checkCheckMethod(pc,p,"queen");
					if (toReturn == true) {
						retthis = true;
					}
				}
				//+a,+b
				if (a-1+i<8 && b-1+i<8) {
					pc=board[a-1+i][b-1+i];
					toReturn = checkCheckMethod(pc,p,"queen");
					if (toReturn == true) {
						retthis = true;
					}
				}
			//-a,-b
				if (a-1-i>=0 && b-1-i>=0) {
					pc=board[a-1-i][b-1-i];
					toReturn = checkCheckMethod(pc,p,"queen");
					if (toReturn == true) {
						retthis = true;
					}
				}
			//+a,-b
				if (a-1+i<8 && b-1-i>=0) {
					pc=board[a-1+i][b-1-i];
					toReturn = checkCheckMethod(pc,p,"queen");
					if (toReturn == true) {
						retthis = true;
					}
				}
			//-a,+b
				if (a-1-i>=0 && b-1+i<8) {
					pc=board[a-1-i][b-1+i];
					toReturn = checkCheckMethod(pc,p,"queen");
					if (toReturn == true) {
						retthis = true;
					}
				}
			}
			
		}
		kingInCheck = retthis;
		return retthis;
	}
	
	
	public static boolean isKingInCheckNow(Piece p, int a, int b) {
		boolean toReturn = false;
		boolean retthis = false;
		//can use checkCheck method with pc numbers below, cCM(pc,p,"king")
		char firstLetter = p.name.charAt(0);
		Piece pc;
		//pawn
		//+a,-b
		if (a<8 && b-2>=0) {
			pc=board[a][b-2];
			toReturn = checkCheckMethod(pc,p,"king");
			if (toReturn == true) {
				retthis = true;
			}
		}
		//+a,+b
		if (a<8 && b<8) {
			pc=board[a][b];
			toReturn = checkCheckMethod(pc,p,"king");
			if (toReturn == true) {
				retthis = true;
			}
		}
		//-a,-b
		if (a-2>=0 && b-2>=0) {
			pc=board[a-2][b-2];
			toReturn = checkCheckMethod(pc,p,"king");
			if (toReturn == true) {
				retthis = true;
			}
		}
		//-a,+b
		if (a-2>=0 && b<8) {
			pc=board[a-2][b];
			toReturn = checkCheckMethod(pc,p,"king");
			if (toReturn == true) {
				retthis = true;
			}
		}
		
		//knight
		//a+1,b-2
		if (a<8 && b-3>=0) {
			pc=board[a][b-3];
			toReturn = checkCheckMethod(pc,p,"king");
			if (toReturn == true) {
				retthis = true;
			}
		}
		//a+2,b-1
		if (a+1<8 && b-2>=0) {
			pc=board[a+1][b-2];
			toReturn = checkCheckMethod(pc,p,"king");
			if (toReturn == true) {
				retthis = true;
			}
		}
		//a+2,b+1
		if (a+1<8 && b<8) {
			pc=board[a+1][b];
			toReturn = checkCheckMethod(pc,p,"king");
			if (toReturn == true) {
				retthis = true;
			}
		}
		//a+1,b+1
		if (a<8 && b<8) {
			pc=board[a][b];
			toReturn = checkCheckMethod(pc,p,"king");
			if (toReturn == true) {
				retthis = true;
			}
		}
		//a+1,b+2
		if (a<8 && b+1<8) {
			pc=board[a][b+1];
			toReturn = checkCheckMethod(pc,p,"king");
			if (toReturn == true) {
				retthis = true;
			}
		}
		//a-1,b-2
		if (a-2>=0 && b-3>=0) {
			pc=board[a-2][b-3];
			toReturn = checkCheckMethod(pc,p,"king");
			if (toReturn == true) {
				retthis = true;
			}
		}
		//a-2,b-1
		if (a-3>=0 && b-2>=0) {
			pc=board[a-3][b-2];
			toReturn = checkCheckMethod(pc,p,"king");
			if (toReturn == true) {
				retthis = true;
			}
		}
		//a-1,b+2
		if (a-2>=0 && b+1<8) {
			pc=board[a-2][b+1];
			toReturn = checkCheckMethod(pc,p,"king");
			if (toReturn == true) {
				retthis = true;
			}
		}
		
		//king
		//+a
		if (a<8 && b-1>=0) {
			pc=board[a][b-1];
			toReturn = checkCheckMethod(pc,p,"king");
			if (toReturn == true) {
				retthis = true;
			}
		}
		//-a
		if (a-2>=0 && b-1>=0) {
			pc=board[a-2][b-1];
			toReturn = checkCheckMethod(pc,p,"king");
			if (toReturn == true) {
				retthis = true;
			}
		}
		//+b
		if (a-1>=0 && b<8) {
			pc=board[a-1][b];
			toReturn = checkCheckMethod(pc,p,"king");
			if (toReturn == true) {
				retthis = true;
			}
		}
		//-b
		if (a-1>=0 && b-2>=0) {
			pc=board[a-1][b-2];
			toReturn = checkCheckMethod(pc,p,"king");
			if (toReturn == true) {
				retthis = true;
			}
		}
		//+a,-b
		if (a<8 && b-2>=0) {
			pc=board[a][b-2];
			toReturn = checkCheckMethod(pc,p,"king");
			if (toReturn == true) {
				retthis = true;
			}
		}
		//+a,+b
		if (a<8 && b<8) {
			pc=board[a][b];
			toReturn = checkCheckMethod(pc,p,"king");
			if (toReturn == true) {
				retthis = true;
			}
		}
		//-a,-b
		if (a-2>=0 && b-2>=0) {
			pc=board[a-2][b-2];
			toReturn = checkCheckMethod(pc,p,"king");
			if (toReturn == true) {
				retthis = true;
			}
		}
		//-a,+b
		if (a-2>=0 && b<8) {
			pc=board[a-2][b];
			toReturn = checkCheckMethod(pc,p,"king");
			if (toReturn == true) {
				retthis = true;
			}
		}
		
		//rook
		for (int i = 0; i < 8; i++) {
		//a+i
			if (a-1+i<8 && b-1>=0) {
				pc=board[a-1+i][b-1];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
		//a-i
			if (a-1-i>=0 && b-1>=0) {
				pc=board[a-1-i][b-1];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
		//b+i
			if (b-1+i<8 && a-1>=0) {
				pc=board[a-1][b-1+i];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
		//b-i
			if (b-1-i >=0 && a-1>=0) {
				pc=board[a-1][b-1-i];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
		}
		
		//bishop
		for (int i = 0; i < 8; i++) {
		//a+i,b+i
			if (a-1+i<8 && b-1+i<8) {
				pc=board[a-i+i][b-1+i];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
		//a+i,b-i
			if (a-1+i<8 && b-1-i>=0) {
				pc=board[a-1+i][b-1-i];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
		//a-i,b+i
			if (a-1-i>=0 && b-1+i<8) {
				pc=board[a-1-i][b-1+i];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
		//a-i,b-i
			if (a-1-i>=0 && b-1-i>=0) {
				pc=board[a-1-i][b-1-i];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
		}
		
		//queen
		for (int i = 0; i < 8; i++) {
			//a+i
			if (a-1+i<8 && b-1>=0) {
				pc=board[a-1+i][b-1];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
		//a-i
			if (a-1-i>=0 && b-1>=0) {
				pc=board[a-1-i][b-1];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
		//b+i
			if (b-1+i<8 && a-1>=0) {
				pc=board[a-1][b-1+i];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
		//b-i
			if (b-1-i >=0 && a-1>=0) {
				pc=board[a-1][b-1-i];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
			//a+i,b+i
			if (a-1+i<8 && b-1+i<8) {
				pc=board[a-i+i][b-1+i];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
		//a+i,b-i
			if (a-1+i<8 && b-1-i>=0) {
				pc=board[a-1+i][b-1-i];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
		//a-i,b+i
			if (a-1-i>=0 && b-1+i<8) {
				pc=board[a-1-i][b-1+i];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
		//a-i,b-i
			if (a-1-i>=0 && b-1-i>=0) {
				pc=board[a-1-i][b-1-i];
				toReturn = checkCheckMethod(pc,p,"king");
				if (toReturn == true) {
					retthis = true;
				}
			}
		}
		kingInCheck = retthis;
		return retthis;
	}
	
	public static boolean checkCheckMethod (Piece pc, Piece p, String ps) {
		boolean toReturn = false;
		if (pc != null) {
			if (pc.type.equals(ps)) {
				if (pc.name.charAt(0) != p.name.charAt(0)) {
					toReturn = true;
				}
			}
		}
		return toReturn;
	}
	
	
	public static int getNum(String x) {
		//letter to first [x][] array
		int ret = 0;
		char let = x.charAt(0);
		switch (let) {
		case 'A': ret = 1;
		break;
		case 'B': ret = 2;
		break;
		case 'C': ret = 3;
		break;
		case 'D': ret = 4;
		break;
		case 'E': ret = 5;
		break;
		case 'F': ret = 6;
		break;
		case 'G': ret = 7;
		break;
		case 'H': ret = 8;
		break;
		}
		return ret;
	}
	
	public static String getLet(int x) {
		//make [x][] number to a letter
		String toReturn = "none";
		switch (x) {
		case 1: toReturn="A";
		break;
		case 2: toReturn="B";
		break;
		case 3: toReturn="C";
		break;
		case 4: toReturn="D";
		break;
		case 5: toReturn="E";
		break;
		case 6: toReturn="F";
		break;
		case 7: toReturn="G";
		break;
		case 8: toReturn="H";
		break;
		}
		return toReturn;
	}
	
	public static int switchNum(int x) {
		//switch [][x] to make it compatible with java arrays and matricies
		int ret = 0;
		switch (x) {
		case 1: ret = 8;
		break;
		case 2: ret = 7;
		break;
		case 3: ret = 6;
		break;
		case 4: ret = 5;
		break;
		case 5: ret = 4;
		break;
		case 6: ret = 3;
		break;
		case 7: ret = 2;
		break;
		case 8: ret = 1;
		break;
		}
		return ret;
	}
	
	public static void printBoard() {
		//print out the board
		System.out.println();
		String nameb;
		if (board[0][0]==null) {
			nameb="NA";
		}
		else {
			nameb=board[0][0].name;
		}
		System.out.print("8  |"+nameb);
		for (int i = 1;i<8;i++) {
			String name;
			if (board[i][0]==null) {
				name = "NA";
			}
			else {
				name = board[i][0].name;
			}
			System.out.print("|"+name);
		}
		if (board[0][1]==null) {
			nameb="NA";
		}
		else {
			nameb=board[0][1].name;
		}
		System.out.println();
		System.out.print("7  |"+nameb);
		for (int i = 1;i<8;i++) {
			String name;
			if (board[i][1]==null) {
				name = "NA";
			}
			else {
				name = board[i][1].name;
			}
			System.out.print("|"+name);
		}
		if (board[0][2]==null) {
			nameb="NA";
		}
		else {
			nameb=board[0][2].name;
		}
		System.out.println();
		System.out.print("6  |"+nameb);
		for (int i = 1;i<8;i++) {
			String name;
			if (board[i][2]==null) {
				name = "NA";
			}
			else {
				name = board[i][2].name;
			}
			System.out.print("|"+name);
		}
		if (board[0][3]==null) {
			nameb="NA";
		}
		else {
			nameb=board[0][3].name;
		}
		System.out.println();
		System.out.print("5  |"+nameb);
		for (int i = 1;i<8;i++) {
			String name;
			if (board[i][3]==null) {
				name = "NA";
			}
			else {
				name = board[i][3].name;
			}
			System.out.print("|"+name);
		}
		if (board[0][4]==null) {
			nameb="NA";
		}
		else {
			nameb=board[0][4].name;
		}
		System.out.println();
		System.out.print("4  |"+nameb);
		for (int i = 1;i<8;i++) {
			String name;
			if (board[i][4]==null) {
				name = "NA";
			}
			else {
				name = board[i][4].name;
			}
			System.out.print("|"+name);
		}
		if (board[0][5]==null) {
			nameb="NA";
		}
		else {
			nameb=board[0][5].name;
		}
		System.out.println();
		System.out.print("3  |"+nameb);
		for (int i = 1;i<8;i++) {
			String name;
			if (board[i][5]==null) {
				name = "NA";
			}
			else {
				name = board[i][5].name;
			}
			System.out.print("|"+name);
		}
		if (board[0][6]==null) {
			nameb="NA";
		}
		else {
			nameb=board[0][6].name;
		}
		System.out.println();
		System.out.print("2  |"+nameb);
		for (int i = 1;i<8;i++) {
			String name;
			if (board[i][6]==null) {
				name = "NA";
			}
			else {
				name = board[i][6].name;
			}
			System.out.print("|"+name);
		}
		if (board[0][7]==null) {
			nameb="NA";
		}
		else {
			nameb=board[0][7].name;
		}
		System.out.println();
		System.out.print("1  |"+nameb);
		for (int i = 1;i<8;i++) {
			String name;
			if (board[i][7]==null) {
				name = "NA";
			}
			else {
				name = board[i][7].name;
			}
			System.out.print("|"+name);
		}
		System.out.println();
		System.out.print("     A  B  C  D  E  F  G  H");
	}
	
	public static int[] getCoords(int p) {
		int[] coords = null;
		int x = -1;
		int y = -1;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (cds[i][j] == p) {
					x=i;
					y=j;
				}
			}
		}
		coords[0] = x;
		coords[1] = y;
		return coords;
	}
	
	public static void setup(String p) {
		String n = "N";
		String a = "A";
		String b = "B";
		String c = "C";
		String d = "D";
		String e = "E";
		String f = "F";
		String g = "G";
		String h = "H";
		if (p.equals("white")) {
			insertPiece(wr1,a,1,n,0);
			insertPiece(wk1,b,1,n,0);
			insertPiece(wb1,c,1,n,0);
			insertPiece(wq,d,1,n,0);
			insertPiece(wk,e,1,n,0);
			insertPiece(wb2,f,1,n,0);
			insertPiece(wk2,g,1,n,0);
			insertPiece(wr2,h,1,n,0);
			insertPiece(wp1,a,2,n,0);
			insertPiece(wp2,b,2,n,0);
			insertPiece(wp3,c,2,n,0);
			insertPiece(wp4,d,2,n,0);
			insertPiece(wp5,e,2,n,0);
			insertPiece(wp6,f,2,n,0);
			insertPiece(wp7,g,2,n,0);
			insertPiece(wp8,h,2,n,0);
			
			insertPiece(br1,a,8,n,0);
			insertPiece(bk1,b,8,n,0);
			insertPiece(bb1,c,8,n,0);
			insertPiece(bq,d,8,n,0);
			insertPiece(bk,e,8,n,0);
			insertPiece(bb2,f,8,n,0);
			insertPiece(bk2,g,8,n,0);
			insertPiece(br2,h,8,n,0);
			insertPiece(bp1,a,7,n,0);
			insertPiece(bp2,b,7,n,0);
			insertPiece(bp3,c,7,n,0);
			insertPiece(bp4,d,7,n,0);
			insertPiece(bp5,e,7,n,0);
			insertPiece(bp6,f,7,n,0);
			insertPiece(bp7,g,7,n,0);
			insertPiece(bp8,h,7,n,0);
		}
		else if (p.equals("black")) {
			insertPiece(wr1,a,8,n,0);
			insertPiece(wk1,b,8,n,0);
			insertPiece(wb1,c,8,n,0);
			insertPiece(wk,d,8,n,0);
			insertPiece(wq,e,8,n,0);
			insertPiece(wb2,f,8,n,0);
			insertPiece(wk2,g,8,n,0);
			insertPiece(wr2,h,8,n,0);
			insertPiece(wp1,a,7,n,0);
			insertPiece(wp2,b,7,n,0);
			insertPiece(wp3,c,7,n,0);
			insertPiece(wp4,d,7,n,0);
			insertPiece(wp5,e,7,n,0);
			insertPiece(wp6,f,7,n,0);
			insertPiece(wp7,g,7,n,0);
			insertPiece(wp8,h,7,n,0);
			
			insertPiece(br1,a,1,n,0);
			insertPiece(bk1,b,1,n,0);
			insertPiece(bb1,c,1,n,0);
			insertPiece(bk,d,1,n,0);
			insertPiece(bq,e,1,n,0);
			insertPiece(bb2,f,1,n,0);
			insertPiece(bk2,g,1,n,0);
			insertPiece(br2,h,1,n,0);
			insertPiece(bp1,a,2,n,0);
			insertPiece(bp2,b,2,n,0);
			insertPiece(bp3,c,2,n,0);
			insertPiece(bp4,d,2,n,0);
			insertPiece(bp5,e,2,n,0);
			insertPiece(bp6,f,2,n,0);
			insertPiece(bp7,g,2,n,0);
			insertPiece(bp8,h,2,n,0);
		}
	}

	
}


//-a,-b    -b    +a,-b
// -a      a,b    +a
//-a,+b    +b    +a,+b