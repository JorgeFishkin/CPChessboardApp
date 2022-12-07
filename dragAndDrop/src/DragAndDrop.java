
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Color;
import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

public class DragAndDrop extends JFrame implements MouseListener{

	//array of prev images 9x9
	//   H   G   F   E   D   C   B   A
	//8
	//7
	//6
	//5
	//4
	//3
	//2
	//1
	
	Container parent;
	JLabel replacement;
	JLabel dummy;
	
	//board for prev images
	static Object[][] board = new Object[9][9];
	
	//boolean for mouseEmtered to not always act when cursor hovering
	boolean dot = false;
	boolean dot2 = false;
	boolean dot3 = false;
	
	String squareNameFrom;
	String squareNameTo;
	Icon squareIcon;
	
	//initialize squares
	JLabel pic1,pic2,pic3;
	JLabel a8,a7,a6,a5,a4,a3,a2,a1;
	JLabel b8,b7,b6,b5,b4,b3,b2,b1;
	JLabel c8,c7,c6,c5,c4,c3,c2,c1;
	JLabel d8,d7,d6,d5,d4,d3,d2,d1;
	JLabel e8,e7,e6,e5,e4,e3,e2,e1;
	JLabel f8,f7,f6,f5,f4,f3,f2,f1;
	JLabel g8,g7,g6,g5,g4,g3,g2,g1;
	JLabel h8,h7,h6,h5,h4,h3,h2,h1;
	
	//initialize colors
	Color white = Color.white;
	Color black = Color.black;
	Color gray = Color.gray;
	
	//board size, can change
	int top = 810;
	int side = 850;
	
	//size of each square
	int s = 100;
	
	//x and y for label location in mousePressed
	static int x;
	static int y;
	int newx;
	int newy;
	static int oldx = -1;
	static int oldy = -1;
	static int movecount = 0;

	public DragAndDrop() {
		super ("Drag and Drop");
		/*pic1 = new JLabel();
		pic2 = new JLabel();
		pic3 = new JLabel();
		pic1.setBounds(20, 30, 100, 100);
		pic2.setBounds(250,30,100,100);
		pic3.setBounds(20,140,100,100);
		pic1.setIcon(new ImageIcon("C:\\Users\\edmei\\Desktop\\pawn.jpg"));
		pic2.setIcon(new ImageIcon("C:\\Users\\edmei\\Desktop\\knight.png"));
		pic3.setIcon(new ImageIcon("C:\\Users\\edmei\\Desktop\\blue.jfif"));*/
		
		//https://www.veryicon.com/icons/system/ios7-minimal/chess-pawn-1.html
		
		//initialize set board components
		board[0][0] = null;
		board[0][1] = "A";
		board[0][2] = "B";
		board[0][3] = "C";
		board[0][4] = "D";
		board[0][5] = "E";
		board[0][6] = "F";
		board[0][7] = "G";
		board[0][8] = "H";
		
		board[1][0] = 8;
		board[2][0] = 7;
		board[3][0] = 6;
		board[4][0] = 5;
		board[5][0] = 4;
		board[6][0] = 3;
		board[7][0] = 2;
		board[8][0] = 1;
		
		//a8
		//b8
		//c8
		//etc.
		
		//a8-h8
		a8 = new JLabel();
		a8.setBounds(0,0,s,s);
		a8.setBackground(white);
		a8.setOpaque(true);
		a8.setName("a8");
		b8 = new JLabel();
		b8.setBounds(s,0,s,s);
		b8.setBackground(gray);
		b8.setOpaque(true);
		b8.setName("b8");
		c8 = new JLabel();
		c8.setBounds(2*s,0,s,s);
		c8.setBackground(white);
		c8.setOpaque(true);
		c8.setName("c8");
		d8 = new JLabel();
		d8.setBounds(3*s,0,s,s);
		d8.setBackground(gray);
		d8.setOpaque(true);
		d8.setName("d8");
		e8 = new JLabel();
		e8.setBounds(4*s,0,s,s);
		e8.setBackground(white);
		e8.setOpaque(true);
		e8.setName("e8");
		f8 = new JLabel();
		f8.setBounds(5*s,0,s,s);
		f8.setBackground(gray);
		f8.setOpaque(true);
		f8.setName("f8");
		g8 = new JLabel();
		g8.setBounds(6*s,0,s,s);
		g8.setBackground(white);
		g8.setOpaque(true);
		g8.setName("g8");
		h8 = new JLabel();
		h8.setBounds(7*s,0,s,s);
		h8.setBackground(gray);
		h8.setOpaque(true);
		h8.setName("h8");
		
		//put icons on chess board, will need moved
		ImageIcon wp = new ImageIcon(new ImageIcon("src\\p.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		wp.setDescription("wpawn");
		ImageIcon wb = new ImageIcon(new ImageIcon("src\\b.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		wb.setDescription("wbishop");
		ImageIcon wk = new ImageIcon(new ImageIcon("src\\k.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		wk.setDescription("wknight");
		ImageIcon wr = new ImageIcon(new ImageIcon("src\\r.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		wr.setDescription("wrook");
		ImageIcon wki = new ImageIcon(new ImageIcon("src\\ki.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		wki.setDescription("wking");
		ImageIcon wq = new ImageIcon(new ImageIcon("src\\q.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		wq.setDescription("wqueen");
		a8.setIcon(wp);
		b8.setIcon(wb);
		c8.setIcon(wk);
		d8.setIcon(wr);
		e8.setIcon(wki);
		f8.setIcon(wq);
		
		//a7-h7
		a7 = new JLabel();
		a7.setBounds(0,s,s,s);
		a7.setBackground(gray);
		a7.setOpaque(true);
		a7.setName("a7");
		b7 = new JLabel();
		b7.setBounds(s,s,s,s);
		b7.setBackground(white);
		b7.setOpaque(true);
		b7.setName("b7");
		c7 = new JLabel();
		c7.setBounds(2*s,s,s,s);
		c7.setBackground(gray);
		c7.setOpaque(true);
		c7.setName("c7");
		d7 = new JLabel();
		d7.setBounds(3*s,s,s,s);
		d7.setBackground(white);
		d7.setOpaque(true);
		d7.setName("d7");
		e7 = new JLabel();
		e7.setBounds(4*s,s,s,s);
		e7.setBackground(gray);
		e7.setOpaque(true);
		e7.setName("e7");
		f7 = new JLabel();
		f7.setBounds(5*s,s,s,s);
		f7.setBackground(white);
		f7.setOpaque(true);
		f7.setName("f7");
		g7 = new JLabel();
		g7.setBounds(6*s,s,s,s);
		g7.setBackground(gray);
		g7.setOpaque(true);
		g7.setName("g7");
		h7 = new JLabel();
		h7.setBounds(7*s,s,s,s);
		h7.setBackground(white);
		h7.setOpaque(true);
		h7.setName("h7");
		
		//a6-h6
		a6 = new JLabel();
		a6.setBounds(0,2*s,s,s);
		a6.setBackground(white);
		a6.setOpaque(true);
		a6.setName("a6");
		b6 = new JLabel();
		b6.setBounds(s,2*s,s,s);
		b6.setBackground(gray);
		b6.setOpaque(true);
		b6.setName("b6");
		c6 = new JLabel();
		c6.setBounds(2*s,2*s,s,s);
		c6.setBackground(white);
		c6.setOpaque(true);
		c6.setName("c6");
		d6 = new JLabel();
		d6.setBounds(3*s,2*s,s,s);
		d6.setBackground(gray);
		d6.setOpaque(true);
		d6.setName("d6");
		e6 = new JLabel();
		e6.setBounds(4*s,2*s,s,s);
		e6.setBackground(white);
		e6.setOpaque(true);
		e6.setName("e6");
		f6 = new JLabel();
		f6.setBounds(5*s,2*s,s,s);
		f6.setBackground(gray);
		f6.setOpaque(true);
		f6.setName("f6");
		g6 = new JLabel();
		g6.setBounds(6*s,2*s,s,s);
		g6.setBackground(white);
		g6.setOpaque(true);
		g6.setName("g6");
		h6 = new JLabel();
		h6.setBounds(7*s,2*s,s,s);
		h6.setBackground(gray);
		h6.setOpaque(true);
		h6.setName("h6");
		
		//a5-h5
		a5 = new JLabel();
		a5.setBounds(0,3*s,s,s);
		a5.setBackground(gray);
		a5.setOpaque(true);
		a5.setName("a5");
		b5 = new JLabel();
		b5.setBounds(s,3*s,s,s);
		b5.setBackground(white);
		b5.setOpaque(true);
		b5.setName("b5");
		c5 = new JLabel();
		c5.setBounds(2*s,3*s,s,s);
		c5.setBackground(gray);
		c5.setOpaque(true);
		c5.setName("c5");
		d5 = new JLabel();
		d5.setBounds(3*s,3*s,s,s);
		d5.setBackground(white);
		d5.setOpaque(true);
		d5.setName("d5");
		e5 = new JLabel();
		e5.setBounds(4*s,3*s,s,s);
		e5.setBackground(gray);
		e5.setOpaque(true);
		e5.setName("e5");
		f5 = new JLabel();
		f5.setBounds(5*s,3*s,s,s);
		f5.setBackground(white);
		f5.setOpaque(true);
		f5.setName("f5");
		g5 = new JLabel();
		g5.setBounds(6*s,3*s,s,s);
		g5.setBackground(gray);
		g5.setOpaque(true);
		g5.setName("g5");
		h5 = new JLabel();
		h5.setBounds(7*s,3*s,s,s);
		h5.setBackground(white);
		h5.setOpaque(true);
		h5.setName("h5");
		
		//a4-h4
		a4 = new JLabel();
		a4.setBounds(0,4*s,s,s);
		a4.setBackground(white);
		a4.setOpaque(true);
		a4.setName("a4");
		b4 = new JLabel();
		b4.setBounds(s,4*s,s,s);
		b4.setBackground(gray);
		b4.setOpaque(true);
		b4.setName("b4");
		c4 = new JLabel();
		c4.setBounds(2*s,4*s,s,s);
		c4.setBackground(white);
		c4.setOpaque(true);
		c4.setName("c4");
		d4 = new JLabel();
		d4.setBounds(3*s,4*s,s,s);
		d4.setBackground(gray);
		d4.setOpaque(true);
		d4.setName("d4");
		e4 = new JLabel();
		e4.setBounds(4*s,4*s,s,s);
		e4.setBackground(white);
		e4.setOpaque(true);
		e4.setName("e4");
		f4 = new JLabel();
		f4.setBounds(5*s,4*s,s,s);
		f4.setBackground(gray);
		f4.setOpaque(true);
		f4.setName("f4");
		g4 = new JLabel();
		g4.setBounds(6*s,4*s,s,s);
		g4.setBackground(white);
		g4.setOpaque(true);
		g4.setName("g4");
		h4 = new JLabel();
		h4.setBounds(7*s,4*s,s,s);
		h4.setBackground(gray);
		h4.setOpaque(true);
		h4.setName("h4");
		
		//a3-h3
		a3 = new JLabel();
		a3.setBounds(0,5*s,s,s);
		a3.setBackground(gray);
		a3.setOpaque(true);
		a3.setName("a3");
		b3 = new JLabel();
		b3.setBounds(s,5*s,s,s);
		b3.setBackground(white);
		b3.setOpaque(true);
		b3.setName("b3");
		c3 = new JLabel();
		c3.setBounds(2*s,5*s,s,s);
		c3.setBackground(gray);
		c3.setOpaque(true);
		c3.setName("c3");
		d3 = new JLabel();
		d3.setBounds(3*s,5*s,s,s);
		d3.setBackground(white);
		d3.setOpaque(true);
		d3.setName("d3");
		e3 = new JLabel();
		e3.setBounds(4*s,5*s,s,s);
		e3.setBackground(gray);
		e3.setOpaque(true);
		e3.setName("e3");
		f3 = new JLabel();
		f3.setBounds(5*s,5*s,s,s);
		f3.setBackground(white);
		f3.setOpaque(true);
		f3.setName("f3");
		g3 = new JLabel();
		g3.setBounds(6*s,5*s,s,s);
		g3.setBackground(gray);
		g3.setOpaque(true);
		g3.setName("g3");
		h3 = new JLabel();
		h3.setBounds(7*s,5*s,s,s);
		h3.setBackground(white);
		h3.setOpaque(true);
		h3.setName("h3");
		
		//a2-h2
		a2 = new JLabel();
		a2.setBounds(0,6*s,s,s);
		a2.setBackground(white);
		a2.setOpaque(true);
		a2.setName("a2");
		b2 = new JLabel();
		b2.setBounds(s,6*s,s,s);
		b2.setBackground(gray);
		b2.setOpaque(true);
		b2.setName("b2");
		c2 = new JLabel();
		c2.setBounds(2*s,6*s,s,s);
		c2.setBackground(white);
		c2.setOpaque(true);
		c2.setName("c2");
		d2 = new JLabel();
		d2.setBounds(3*s,6*s,s,s);
		d2.setBackground(gray);
		d2.setOpaque(true);
		d2.setName("d2");
		e2 = new JLabel();
		e2.setBounds(4*s,6*s,s,s);
		e2.setBackground(white);
		e2.setOpaque(true);
		e2.setName("e2");
		f2 = new JLabel();
		f2.setBounds(5*s,6*s,s,s);
		f2.setBackground(gray);
		f2.setOpaque(true);
		f2.setName("f2");
		g2 = new JLabel();
		g2.setBounds(6*s,6*s,s,s);
		g2.setBackground(white);
		g2.setOpaque(true);
		g2.setName("g2");
		h2 = new JLabel();
		h2.setBounds(7*s,6*s,s,s);
		h2.setBackground(gray);
		h2.setOpaque(true);
		h2.setName("h2");
		
		//a1-h1
		a1 = new JLabel();
		a1.setBounds(0,7*s,s,s);
		a1.setBackground(gray);
		a1.setOpaque(true);
		a1.setName("a1");
		b1 = new JLabel();
		b1.setBounds(s,7*s,s,s);
		b1.setBackground(white);
		b1.setOpaque(true);
		b1.setName("b1");
		c1 = new JLabel();
		c1.setBounds(2*s,7*s,s,s);
		c1.setBackground(gray);
		c1.setOpaque(true);
		c1.setName("c1");
		d1 = new JLabel();
		d1.setBounds(3*s,7*s,s,s);
		d1.setBackground(white);
		d1.setOpaque(true);
		d1.setName("d1");
		e1 = new JLabel();
		e1.setBounds(4*s,7*s,s,s);
		e1.setBackground(gray);
		e1.setOpaque(true);
		e1.setName("e1");
		f1 = new JLabel();
		f1.setBounds(5*s,7*s,s,s);
		f1.setBackground(white);
		f1.setOpaque(true);
		f1.setName("f1");
		g1 = new JLabel();
		g1.setBounds(6*s,7*s,s,s);
		g1.setBackground(gray);
		g1.setOpaque(true);
		g1.setName("g1");
		h1 = new JLabel();
		h1.setBounds(7*s,7*s,s,s);
		h1.setBackground(white);
		h1.setOpaque(true);
		h1.setName("h1");
		
		ImageIcon bp = new ImageIcon(new ImageIcon("src\\bp.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		bp.setDescription("bpawn");
		ImageIcon bb = new ImageIcon(new ImageIcon("src\\bb.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		bb.setDescription("bbishop");
		ImageIcon bk = new ImageIcon(new ImageIcon("src\\bk.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		bk.setDescription("bknight");
		ImageIcon br = new ImageIcon(new ImageIcon("src\\br.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		br.setDescription("brook");
		ImageIcon bki = new ImageIcon(new ImageIcon("src\\bki.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		bki.setDescription("bking");
		ImageIcon bq = new ImageIcon(new ImageIcon("src\\bq.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		bq.setDescription("bqueen");
		a1.setIcon(bp);
		b1.setIcon(bb);
		c1.setIcon(bk);
		d1.setIcon(br);
		e1.setIcon(bki);
		f1.setIcon(bq);


		/*pic1.addMouseListener(this);
		pic2.addMouseListener(this);
		pic3.addMouseListener(this);



		pic1.setTransferHandler(new TransferHandler("icon"));
		pic2.setTransferHandler(new TransferHandler("icon"));
		pic3.setTransferHandler(new TransferHandler("icon"));


		add(pic1);
		add(pic2);
		add(pic3);*/
		
		//add mouse listener to each square
		a8.addMouseListener(this);
		a7.addMouseListener(this);
		a6.addMouseListener(this);
		a5.addMouseListener(this);
		a4.addMouseListener(this);
		a3.addMouseListener(this);
		a2.addMouseListener(this);
		a1.addMouseListener(this);
		
		b8.addMouseListener(this);
		b7.addMouseListener(this);
		b6.addMouseListener(this);
		b5.addMouseListener(this);
		b4.addMouseListener(this);
		b3.addMouseListener(this);
		b2.addMouseListener(this);
		b1.addMouseListener(this);
		
		c8.addMouseListener(this);
		c7.addMouseListener(this);
		c6.addMouseListener(this);
		c5.addMouseListener(this);
		c4.addMouseListener(this);
		c3.addMouseListener(this);
		c2.addMouseListener(this);
		c1.addMouseListener(this);
		
		d8.addMouseListener(this);
		d7.addMouseListener(this);
		d6.addMouseListener(this);
		d5.addMouseListener(this);
		d4.addMouseListener(this);
		d3.addMouseListener(this);
		d2.addMouseListener(this);
		d1.addMouseListener(this);
		
		e8.addMouseListener(this);
		e7.addMouseListener(this);
		e6.addMouseListener(this);
		e5.addMouseListener(this);
		e4.addMouseListener(this);
		e3.addMouseListener(this);
		e2.addMouseListener(this);
		e1.addMouseListener(this);
		
		f8.addMouseListener(this);
		f7.addMouseListener(this);
		f6.addMouseListener(this);
		f5.addMouseListener(this);
		f4.addMouseListener(this);
		f3.addMouseListener(this);
		f2.addMouseListener(this);
		f1.addMouseListener(this);
		
		g8.addMouseListener(this);
		g7.addMouseListener(this);
		g6.addMouseListener(this);
		g5.addMouseListener(this);
		g4.addMouseListener(this);
		g3.addMouseListener(this);
		g2.addMouseListener(this);
		g1.addMouseListener(this);
		
		h8.addMouseListener(this);
		h7.addMouseListener(this);
		h6.addMouseListener(this);
		h5.addMouseListener(this);
		h4.addMouseListener(this);
		h3.addMouseListener(this);
		h2.addMouseListener(this);
		h1.addMouseListener(this);
		
		a8.setTransferHandler(new TransferHandler("icon"));
		a7.setTransferHandler(new TransferHandler("icon"));
		a6.setTransferHandler(new TransferHandler("icon"));
		a5.setTransferHandler(new TransferHandler("icon"));
		a4.setTransferHandler(new TransferHandler("icon"));
		a3.setTransferHandler(new TransferHandler("icon"));
		a2.setTransferHandler(new TransferHandler("icon"));
		a1.setTransferHandler(new TransferHandler("icon"));
		
		b8.setTransferHandler(new TransferHandler("icon"));
		b7.setTransferHandler(new TransferHandler("icon"));
		b6.setTransferHandler(new TransferHandler("icon"));
		b5.setTransferHandler(new TransferHandler("icon"));
		b4.setTransferHandler(new TransferHandler("icon"));
		b3.setTransferHandler(new TransferHandler("icon"));
		b2.setTransferHandler(new TransferHandler("icon"));
		b1.setTransferHandler(new TransferHandler("icon"));
		
		c8.setTransferHandler(new TransferHandler("icon"));
		c7.setTransferHandler(new TransferHandler("icon"));
		c6.setTransferHandler(new TransferHandler("icon"));
		c5.setTransferHandler(new TransferHandler("icon"));
		c4.setTransferHandler(new TransferHandler("icon"));
		c3.setTransferHandler(new TransferHandler("icon"));
		c2.setTransferHandler(new TransferHandler("icon"));
		c1.setTransferHandler(new TransferHandler("icon"));
		
		d8.setTransferHandler(new TransferHandler("icon"));
		d7.setTransferHandler(new TransferHandler("icon"));
		d6.setTransferHandler(new TransferHandler("icon"));
		d5.setTransferHandler(new TransferHandler("icon"));
		d4.setTransferHandler(new TransferHandler("icon"));
		d3.setTransferHandler(new TransferHandler("icon"));
		d2.setTransferHandler(new TransferHandler("icon"));
		d1.setTransferHandler(new TransferHandler("icon"));
		
		e8.setTransferHandler(new TransferHandler("icon"));
		e7.setTransferHandler(new TransferHandler("icon"));
		e6.setTransferHandler(new TransferHandler("icon"));
		e5.setTransferHandler(new TransferHandler("icon"));
		e4.setTransferHandler(new TransferHandler("icon"));
		e3.setTransferHandler(new TransferHandler("icon"));
		e2.setTransferHandler(new TransferHandler("icon"));
		e1.setTransferHandler(new TransferHandler("icon"));
		
		f8.setTransferHandler(new TransferHandler("icon"));
		f7.setTransferHandler(new TransferHandler("icon"));
		f6.setTransferHandler(new TransferHandler("icon"));
		f5.setTransferHandler(new TransferHandler("icon"));
		f4.setTransferHandler(new TransferHandler("icon"));
		f3.setTransferHandler(new TransferHandler("icon"));
		f2.setTransferHandler(new TransferHandler("icon"));
		f1.setTransferHandler(new TransferHandler("icon"));
		
		g8.setTransferHandler(new TransferHandler("icon"));
		g7.setTransferHandler(new TransferHandler("icon"));
		g6.setTransferHandler(new TransferHandler("icon"));
		g5.setTransferHandler(new TransferHandler("icon"));
		g4.setTransferHandler(new TransferHandler("icon"));
		g3.setTransferHandler(new TransferHandler("icon"));
		g2.setTransferHandler(new TransferHandler("icon"));
		g1.setTransferHandler(new TransferHandler("icon"));
		
		h8.setTransferHandler(new TransferHandler("icon"));
		h7.setTransferHandler(new TransferHandler("icon"));
		h6.setTransferHandler(new TransferHandler("icon"));
		h5.setTransferHandler(new TransferHandler("icon"));
		h4.setTransferHandler(new TransferHandler("icon"));
		h3.setTransferHandler(new TransferHandler("icon"));
		h2.setTransferHandler(new TransferHandler("icon"));
		h1.setTransferHandler(new TransferHandler("icon"));
		
		//add to component
		add(a8);
		add(a7);
		add(a6);
		add(a5);
		add(a4);
		add(a3);
		add(a2);
		add(a1);
		
		add(b8);
		add(b7);
		add(b6);
		add(b5);
		add(b4);
		add(b3);
		add(b2);
		add(b1);
		
		add(c8);
		add(c7);
		add(c6);
		add(c5);
		add(c4);
		add(c3);
		add(c2);
		add(c1);
		
		add(d8);
		add(d7);
		add(d6);
		add(d5);
		add(d4);
		add(d3);
		add(d2);
		add(d1);
		
		add(e8);
		add(e7);
		add(e6);
		add(e5);
		add(e4);
		add(e3);
		add(e2);
		add(e1);
		
		add(f8);
		add(f7);
		add(f6);
		add(f5);
		add(f4);
		add(f3);
		add(f2);
		add(f1);
		
		add(g8);
		add(g7);
		add(g6);
		add(g5);
		add(g4);
		add(g3);
		add(g2);
		add(g1);
		
		add(h8);
		add(h7);
		add(h6);
		add(h5);
		add(h4);
		add(h3);
		add(h2);
		add(h1);

		setLayout(null);
		setSize(top,side);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		//add to board, not GUI
		addToBoard(a8,0);
		addToBoard(b8,0);
		addToBoard(c8,0);
		addToBoard(d8,0);
		addToBoard(e8,0);
		addToBoard(f8,0);
		
		//printBoard();
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		//get source of where mouse was first pressed
		JComponent jc = (JComponent)e.getSource();
		//make image moveable
		TransferHandler th = jc.getTransferHandler();
		//copy image
		th.exportAsDrag(jc,e,TransferHandler.COPY);
		dot2 = true;
		//x and y of where it's coming from
		//System.out.println(jc.getName());
		squareNameFrom = jc.getName();
		x = jc.getX();
		y = jc.getY();
		//System.out.println(x);
		oldxy(x,y);
		//System.out.println(y);
		//convert to label to get attributes
		JLabel jl = (JLabel)jc;
		Icon ii = jl.getIcon();
		//System.out.println(jl.getIcon());
		squareIcon = jl.getIcon();
		Color jcc = jc.getBackground();
		//get container to add replacement, a new square
		parent = jc.getParent();
		replacement = new JLabel();
		replacement.setBounds(jc.getBounds());
		replacement.addMouseListener(this);
		replacement.setTransferHandler(new TransferHandler("icon"));
		//remove old label
		parent.remove(jc);
		parent.repaint();
		replacement.setBackground(jcc);
		replacement.setOpaque(true);
		dummy = replacement;
		parent.add(dummy);
		
		//dot is true for mouse entered
		dot = true;
		//add replacement to board, working
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		//dot is true after replacement is added to GUI
		while (dot) {
			//get info about new square, specifically location
			JComponent jc = (JComponent)e.getSource();
			//System.out.println(jc.getName());
			squareNameTo = jc.getName();
			JLabel jl = (JLabel)jc;
			//System.out.println(jl.getLocation());
			newx = jc.getX();
			newy = jc.getY();
			//System.out.println(newx);
			//System.out.println(newy);
			/*if (jl.getIcon() != null) {
				System.out.println(jl.getIcon().toString());
			}
			else {
				System.out.println("null");
			}*/

			replacement.setName(squareNameTo);
			replacement.setIcon(squareIcon);
			addToBoard(replacement, 1);
			replacement.setIcon(null);
			parent.remove(dummy);
			parent.add(replacement);
			parent.repaint();
			
			dot = false;
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//System.out.println("exited");
	}

	//scales images to fit squares
	 public ImageIcon scaleImage(ImageIcon icon, int w, int h)
	    {
	        int nw = icon.getIconWidth();
	        int nh = icon.getIconHeight();

	        if(icon.getIconWidth() > w)
	        {
	          nw = w;
	          nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
	        }

	        if(nh > h)
	        {
	          nh = h;
	          nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
	        }

	        return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
	    }
	 
	 //prints board, not GUI
	 public static void printBoard() {
		 for (int i = 0; i < 9; i++) {
			 for (int j = 0; j < 9; j++) {
				 if (board[i][j] == null) {

					 System.out.print("NA");
					 System.out.print("                   ");
				 }
				 else {

					 System.out.print(board[i][j].toString());
					 System.out.print("                   ");
				 }
				 if (j == 8) {
					 System.out.println("\n");
				 }
			 }
		 }
	 }

	 public static void addToBoard(JLabel jl, int mv) {
		 char f;
		 int s;
		 f = jl.getName().charAt(0);
		 //System.out.println(f);
		 int x = ctoiBoard(f);
		 
		 s = Character.getNumericValue(jl.getName().charAt(1));
		 //System.out.println(s);
		 int y = switchNum(s);
		 
		 System.out.println(oldx);
		 System.out.println(oldy);
		 
		 
		 Icon i = jl.getIcon();
		 //System.out.println(i);
		 board[y][x] = i;
		 
		 if (mv == 1) {
			 board[oldy][oldx] = null;
		 }
		 
		 
		 printBoard();
	 }
	 
	 
	 public static int ctoiBoard(char c) {
		 int toReturn = 0;
		 switch (c) {
		 case 'a': toReturn = 1;
		 break;
		 case 'b': toReturn = 2;
		 break;	
		 case 'c': toReturn = 3;
		 break;
		 case 'd': toReturn = 4;
		 break;
		 case 'e': toReturn = 5;
		 break;
		 case 'f': toReturn = 6;
		 break;	
		 case 'g': toReturn = 7;
		 break;
		 case 'h': toReturn = 8;
		 break;
		 }
		 return toReturn;
	 }
	 
	 public static int switchNum (int x) {
		 int toReturn = 0;
		 switch (x) {
		 case 8: toReturn = 1;
		 break;
		 case 7: toReturn = 2;
		 break;
		 case 6: toReturn = 3;
		 break;
		 case 5: toReturn = 4;
		 break;
		 case 4: toReturn = 5;
		 break;
		 case 3: toReturn = 6;
		 break;
		 case 2: toReturn = 7;
		 break;
		 case 1: toReturn = 8;
		 break;
		 }
		 return toReturn;
	 }
	 
	 public static void oldxy(int x, int y) {
		 switch (x) {
		 case 0: oldx = 1;
		 break;
		 case 100: oldx = 2;
		 break;
		 case 200: oldx = 3;
		 break;
		 case 300: oldx = 4;
		 break;
		 case 400: oldx = 5;
		 break;
		 case 500: oldx = 6;
		 break;
		 case 600: oldx = 7;
		 break;
		 case 700: oldx = 8;
		 }
		 
		 switch (y) {
		 case 0: oldy = 1;
		 break;
		 case 100: oldy = 2;
		 break;
		 case 200: oldy = 3;
		 break;
		 case 300: oldy = 4;
		 break;
		 case 400: oldy = 5;
		 break;
		 case 500: oldy = 6;
		 break;
		 case 600: oldy = 7;
		 break;
		 case 700: oldy = 8;
		 }
	 }
	 
	public static void main(String[] args) {

		new DragAndDrop();
		//printBoard();
	}


}