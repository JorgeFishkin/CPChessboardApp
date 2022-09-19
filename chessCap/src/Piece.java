public class Piece {
	String name;
	String type;
	
	public Piece(String n, String t) {
		name = n;
		type = t;
	}
	
	static Piece[][] board = new Piece[8][8];
	static Piece[] black = new Piece[16];
	static Piece[] white = new Piece[16];
	static int moveCount = 0;
	
	public static void main(String[] args) {
		
		Piece wp1 = new Piece("wp1","pawn");
		Piece wp2 = new Piece("wp2","pawn");
		Piece wp3 = new Piece("wp3","pawn");
		Piece wp4 = new Piece("wp4","pawn");
		Piece wp5 = new Piece("wp5","pawn");
		Piece wp6 = new Piece("wp6","pawn");
		Piece wp7 = new Piece("wp7","pawn");
		Piece wp8 = new Piece("wp8","pawn");
		Piece wr1 = new Piece("wr1","rook");
		Piece wr2 = new Piece("wr2","rook");
		Piece wk1 = new Piece("wk1","knight");
		Piece wk2 = new Piece("wk2","knight");
		Piece wb1 = new Piece("wb1","bishop");
		Piece wb2 = new Piece("wb2","bishop");
		Piece wq = new Piece("wq","queen");
		Piece wk = new Piece("wk","king");
		
		Piece bp1 = new Piece("bp1","pawn");
		Piece bp2 = new Piece("bp2","pawn");
		Piece bp3 = new Piece("bp3","pawn");
		Piece bp4 = new Piece("bp4","pawn");
		Piece bp5 = new Piece("bp5","pawn");
		Piece bp6 = new Piece("bp6","pawn");
		Piece bp7 = new Piece("bp7","pawn");
		Piece bp8 = new Piece("bp8","pawn");
		Piece br1 = new Piece("br1","rook");
		Piece br2 = new Piece("br2","rook");
		Piece bk1 = new Piece("bk1","knight");
		Piece bk2 = new Piece("bk2","knight");
		Piece bb1 = new Piece("bb1","bishop");
		Piece bb2 = new Piece("bb2","bishop");
		Piece bq = new Piece("bq","queen");
		Piece bk = new Piece("bk","king");
		
		//insertPiece(bk,"E",4,"N",0);
		//System.out.println(getSquare(bk));
		//insertPiece(bq,"H",8,"N",0);
		//System.out.println(getSquare(bq));
		
		//printBoard();
		
		//System.out.println();
		
		//insertPiece(bp1,"A",1,"N",0);
		//printBoard();
		//insertPiece(bp1,"B",2,"A",1);
		//printBoard();
		//insertPiece(bp1,"A",2,"A",1);
		//printBoard();
		//insertPiece(wp1,"B",2,"N",0);
		//printBoard();
		//insertPiece(bp1,"B",2,"A",1);
		//printBoard();
		//insertPiece(bp8,"H",1,"N",0);
		//printBoard();
		//insertPiece(bp8,"H",3,"H",1);
		//printBoard();
		
		/*insertPiece(wp1,"B",2,"N",0);
		printBoard();
		insertPiece(bp1,"A",3,"N",0);
		printBoard();
		insertPiece(wp1,"A",3,"B",2);
		printBoard();*/
	}
	
	public static String getPieceName(Piece p) {
		return p.name;
	}
	
	public static String getPieceType(Piece p) {
		return p.type;
	}
	
	public static String getSquare(Piece p) {
		String toReturn = "none";
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				String let = getLet(i+1);
				if (getPiece(let,j).name.equals(getPieceName(p))) {
					toReturn = ""+let+String.valueOf(j+1);
				}
			}
		}
		return toReturn;
	}
	
	public static Piece getPiece(String a, int b) {
		int x = getNum(a);
		Piece p = new Piece("none","none");
		if (board[x-1][b]!=null) {
			p.name = board[x-1][b].name;
			p.type = board[x-1][b].type;
			p = board[x-1][b];
		}
		return p;
	}
	

	
	public static void insertPiece(Piece p, String a, int b, String prev1, int prev2b) {
		moveCount+=1;
		int x = getNum(a);
		int b2=switchNum(b);
		int prev2 = switchNum(prev2b);
		boolean check = pieceCheck(p, a, b, prev1, prev2);
		System.out.println(check);
		if (prev1.equals("N")) {
			check = true;
			moveCount-=1;
		}
		if (check) {
			board[x-1][b2-1] = p;
			if (prev2b!=0) {
				deletePiece(prev1,prev2b);
			}
		}
		else if (!check) {
			System.out.println("Invalid move.");
			moveCount-=1;
		}
	}
	
	public static void deletePiece(String a, int b) {
		int x = getNum(a);
		b = switchNum(b);
		board[x-1][b-1]=null;
	}
	
	public static boolean pieceCheck(Piece p, String as, int b, String prev1s, int prev2) {
		boolean toReturn = false;
		int a = getNum(as);
		int prev1 = getNum(prev1s);
		b=switchNum(b);
		System.out.println();
		System.out.println(a);
		System.out.println(b);
		System.out.println(prev1);
		System.out.println(prev2);
		System.out.println();
		if (p.type.equals("pawn")) {
			if ((a==prev1-1&&b==prev2-1)||(a==prev1+1&&b==prev2+1)) {
				//pawn diagonal
				if (board[a-1][b-1]!=null) {
					boolean ch = capture(p,as,b,board[a-1][b-1].name.charAt(0),prev1s,prev2);
					if (ch) {
						board[prev1-1][prev2-1]=null;
						board[a-1][b-1]=p;
						toReturn = true;
					}
				}
			}
			else if (b == prev2-1) {
				//pawn straight up
					if (board[a-1][b-1]==null) {
						toReturn = true;
					}
				}
			else if (b == prev2-2) {
				if (moveCount == 1) {
					if (board[a-1][b-2]==null) {
						toReturn = true;
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
	
	public static int getNum(String x) {
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
	
}

//connect to github