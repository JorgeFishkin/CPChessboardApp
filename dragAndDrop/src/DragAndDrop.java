import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

public class DragAndDrop extends JFrame{

	boolean dot = false;
	JLabel pic1,pic2,pic3;
	JLabel h8,h7,h6,h5,h4,h3,h2,h1;
	JLabel g8,g7,g6,g5,g4,g3,g2,g1;
	JLabel f8,f7,f6,f5,f4,f3,f2,f1;
	JLabel e8,e7,e6,e5,e4,e3,e2,e1;
	JLabel d8,d7,d6,d5,d4,d3,d2,d1;
	JLabel c8,c7,c6,c5,c4,c3,c2,c1;
	JLabel b8,b7,b6,b5,b4,b3,b2,b1;
	JLabel a8,a7,a6,a5,a4,a3,a2,a1;
	Color white = Color.white;
	Color black = Color.black;
	Color gray = Color.gray;
	int top = 810;
	int side = 850;
	int s = 100;
	int x = 0;
	int y = 0;
	int newx = 0;
	int newy = 0;
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
		
		h8 = new JLabel();
		h8.setBounds(0,0,s,s);
		h8.setBackground(white);
		h8.setOpaque(true);
		h8.setName("H8");
		h7 = new JLabel();
		h7.setBounds(s,0,s,s);
		h7.setBackground(gray);
		h7.setOpaque(true);
		h7.setName("H7");
		h6 = new JLabel();
		h6.setBounds(2*s,0,s,s);
		h6.setBackground(white);
		h6.setOpaque(true);
		h6.setName("H6");
		h5 = new JLabel();
		h5.setBounds(3*s,0,s,s);
		h5.setBackground(gray);
		h5.setOpaque(true);
		h5.setName("H5");
		h4 = new JLabel();
		h4.setBounds(4*s,0,s,s);
		h4.setBackground(white);
		h4.setOpaque(true);
		h4.setName("H4");
		h3 = new JLabel();
		h3.setBounds(5*s,0,s,s);
		h3.setBackground(gray);
		h3.setOpaque(true);
		h3.setName("H3");
		h2 = new JLabel();
		h2.setBounds(6*s,0,s,s);
		h2.setBackground(white);
		h2.setOpaque(true);
		h2.setName("H2");
		h1 = new JLabel();
		h1.setBounds(7*s,0,s,s);
		h1.setBackground(gray);
		h1.setOpaque(true);
		h1.setName("H1");
		
		ImageIcon wp = new ImageIcon(new ImageIcon("src\\p.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		ImageIcon wb = new ImageIcon(new ImageIcon("src\\b.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		ImageIcon wk = new ImageIcon(new ImageIcon("src\\k.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		ImageIcon wr = new ImageIcon(new ImageIcon("src\\r.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		ImageIcon wki = new ImageIcon(new ImageIcon("src\\ki.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		ImageIcon wq = new ImageIcon(new ImageIcon("src\\q.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		h8.setIcon(wp);
		h7.setIcon(wb);
		h6.setIcon(wk);
		h5.setIcon(wr);
		h4.setIcon(wki);
		h3.setIcon(wq);
		
		g8 = new JLabel();
		g8.setBounds(0,s,s,s);
		g8.setBackground(gray);
		g8.setOpaque(true);
		g8.setName("G8");
		g7 = new JLabel();
		g7.setBounds(s,s,s,s);
		g7.setBackground(white);
		g7.setOpaque(true);
		g7.setName("G7");
		g6 = new JLabel();
		g6.setBounds(2*s,s,s,s);
		g6.setBackground(gray);
		g6.setOpaque(true);
		g6.setName("G6");
		g5 = new JLabel();
		g5.setBounds(3*s,s,s,s);
		g5.setBackground(white);
		g5.setOpaque(true);
		g5.setName("G5");
		g4 = new JLabel();
		g4.setBounds(4*s,s,s,s);
		g4.setBackground(gray);
		g4.setOpaque(true);
		g4.setName("G4");
		g3 = new JLabel();
		g3.setBounds(5*s,s,s,s);
		g3.setBackground(white);
		g3.setOpaque(true);
		g3.setName("G3");
		g2 = new JLabel();
		g2.setBounds(6*s,s,s,s);
		g2.setBackground(gray);
		g2.setOpaque(true);
		g2.setName("G2");
		g1 = new JLabel();
		g1.setBounds(7*s,s,s,s);
		g1.setBackground(white);
		g1.setOpaque(true);
		g1.setName("G1");
		
		f8 = new JLabel();
		f8.setBounds(0,2*s,s,s);
		f8.setBackground(white);
		f8.setOpaque(true);
		f8.setName("F8");
		f7 = new JLabel();
		f7.setBounds(s,2*s,s,s);
		f7.setBackground(gray);
		f7.setOpaque(true);
		f7.setName("F7");
		f6 = new JLabel();
		f6.setBounds(2*s,2*s,s,s);
		f6.setBackground(white);
		f6.setOpaque(true);
		f6.setName("F6");
		f5 = new JLabel();
		f5.setBounds(3*s,2*s,s,s);
		f5.setBackground(gray);
		f5.setOpaque(true);
		f5.setName("F5");
		f4 = new JLabel();
		f4.setBounds(4*s,2*s,s,s);
		f4.setBackground(white);
		f4.setOpaque(true);
		f4.setName("F4");
		f3 = new JLabel();
		f3.setBounds(5*s,2*s,s,s);
		f3.setBackground(gray);
		f3.setOpaque(true);
		f3.setName("F3");
		f2 = new JLabel();
		f2.setBounds(6*s,2*s,s,s);
		f2.setBackground(white);
		f2.setOpaque(true);
		f2.setName("F2");
		f1 = new JLabel();
		f1.setBounds(7*s,2*s,s,s);
		f1.setBackground(gray);
		f1.setOpaque(true);
		f1.setName("F1");
		
		e8 = new JLabel();
		e8.setBounds(0,3*s,s,s);
		e8.setBackground(gray);
		e8.setOpaque(true);
		e8.setName("E8");
		e7 = new JLabel();
		e7.setBounds(s,3*s,s,s);
		e7.setBackground(white);
		e7.setOpaque(true);
		e7.setName("E7");
		e6 = new JLabel();
		e6.setBounds(2*s,3*s,s,s);
		e6.setBackground(gray);
		e6.setOpaque(true);
		e6.setName("E6");
		e5 = new JLabel();
		e5.setBounds(3*s,3*s,s,s);
		e5.setBackground(white);
		e5.setOpaque(true);
		e5.setName("E5");
		e4 = new JLabel();
		e4.setBounds(4*s,3*s,s,s);
		e4.setBackground(gray);
		e4.setOpaque(true);
		e4.setName("E4");
		e3 = new JLabel();
		e3.setBounds(5*s,3*s,s,s);
		e3.setBackground(white);
		e3.setOpaque(true);
		e3.setName("E3");
		e2 = new JLabel();
		e2.setBounds(6*s,3*s,s,s);
		e2.setBackground(gray);
		e2.setOpaque(true);
		e2.setName("E2");
		e1 = new JLabel();
		e1.setBounds(7*s,3*s,s,s);
		e1.setBackground(white);
		e1.setOpaque(true);
		e1.setName("E1");
		
		d8 = new JLabel();
		d8.setBounds(0,4*s,s,s);
		d8.setBackground(white);
		d8.setOpaque(true);
		d8.setName("D8");
		d7 = new JLabel();
		d7.setBounds(s,4*s,s,s);
		d7.setBackground(gray);
		d7.setOpaque(true);
		d7.setName("D7");
		d6 = new JLabel();
		d6.setBounds(2*s,4*s,s,s);
		d6.setBackground(white);
		d6.setOpaque(true);
		d6.setName("D6");
		d5 = new JLabel();
		d5.setBounds(3*s,4*s,s,s);
		d5.setBackground(gray);
		d5.setOpaque(true);
		d5.setName("D5");
		d4 = new JLabel();
		d4.setBounds(4*s,4*s,s,s);
		d4.setBackground(white);
		d4.setOpaque(true);
		d4.setName("D4");
		d3 = new JLabel();
		d3.setBounds(5*s,4*s,s,s);
		d3.setBackground(gray);
		d3.setOpaque(true);
		d3.setName("D3");
		d2 = new JLabel();
		d2.setBounds(6*s,4*s,s,s);
		d2.setBackground(white);
		d2.setOpaque(true);
		d2.setName("D2");
		d1 = new JLabel();
		d1.setBounds(7*s,4*s,s,s);
		d1.setBackground(gray);
		d1.setOpaque(true);
		d1.setName("D1");
		
		c8 = new JLabel();
		c8.setBounds(0,5*s,s,s);
		c8.setBackground(gray);
		c8.setOpaque(true);
		c8.setName("C8");
		c7 = new JLabel();
		c7.setBounds(s,5*s,s,s);
		c7.setBackground(white);
		c7.setOpaque(true);
		c7.setName("C7");
		c6 = new JLabel();
		c6.setBounds(2*s,5*s,s,s);
		c6.setBackground(gray);
		c6.setOpaque(true);
		c6.setName("C6");
		c5 = new JLabel();
		c5.setBounds(3*s,5*s,s,s);
		c5.setBackground(white);
		c5.setOpaque(true);
		c5.setName("C5");
		c4 = new JLabel();
		c4.setBounds(4*s,5*s,s,s);
		c4.setBackground(gray);
		c4.setOpaque(true);
		c4.setName("C4");
		c3 = new JLabel();
		c3.setBounds(5*s,5*s,s,s);
		c3.setBackground(white);
		c3.setOpaque(true);
		c3.setName("C3");
		c2 = new JLabel();
		c2.setBounds(6*s,5*s,s,s);
		c2.setBackground(gray);
		c2.setOpaque(true);
		c2.setName("C2");
		c1 = new JLabel();
		c1.setBounds(7*s,5*s,s,s);
		c1.setBackground(white);
		c1.setOpaque(true);
		c1.setName("C1");
		
		b8 = new JLabel();
		b8.setBounds(0,6*s,s,s);
		b8.setBackground(white);
		b8.setOpaque(true);
		b8.setName("B8");
		b7 = new JLabel();
		b7.setBounds(s,6*s,s,s);
		b7.setBackground(gray);
		b7.setOpaque(true);
		b7.setName("B7");
		b6 = new JLabel();
		b6.setBounds(2*s,6*s,s,s);
		b6.setBackground(white);
		b6.setOpaque(true);
		b6.setName("B6");
		b5 = new JLabel();
		b5.setBounds(3*s,6*s,s,s);
		b5.setBackground(gray);
		b5.setOpaque(true);
		b5.setName("B5");
		b4 = new JLabel();
		b4.setBounds(4*s,6*s,s,s);
		b4.setBackground(white);
		b4.setOpaque(true);
		b4.setName("B4");
		b3 = new JLabel();
		b3.setBounds(5*s,6*s,s,s);
		b3.setBackground(gray);
		b3.setOpaque(true);
		b3.setName("B3");
		b2 = new JLabel();
		b2.setBounds(6*s,6*s,s,s);
		b2.setBackground(white);
		b2.setOpaque(true);
		b2.setName("B2");
		b1 = new JLabel();
		b1.setBounds(7*s,6*s,s,s);
		b1.setBackground(gray);
		b1.setOpaque(true);
		b1.setName("B1");
		
		a8 = new JLabel();
		a8.setBounds(0,7*s,s,s);
		a8.setBackground(gray);
		a8.setOpaque(true);
		a8.setName("A8");
		a7 = new JLabel();
		a7.setBounds(s,7*s,s,s);
		a7.setBackground(white);
		a7.setOpaque(true);
		a7.setName("A7");
		a6 = new JLabel();
		a6.setBounds(2*s,7*s,s,s);
		a6.setBackground(gray);
		a6.setOpaque(true);
		a6.setName("A6");
		a5 = new JLabel();
		a5.setBounds(3*s,7*s,s,s);
		a5.setBackground(white);
		a5.setOpaque(true);
		a5.setName("A5");
		a4 = new JLabel();
		a4.setBounds(4*s,7*s,s,s);
		a4.setBackground(gray);
		a4.setOpaque(true);
		a4.setName("A4");
		a3 = new JLabel();
		a3.setBounds(5*s,7*s,s,s);
		a3.setBackground(white);
		a3.setOpaque(true);
		a3.setName("A3");
		a2 = new JLabel();
		a2.setBounds(6*s,7*s,s,s);
		a2.setBackground(gray);
		a2.setOpaque(true);
		a2.setName("A2");
		a1 = new JLabel();
		a1.setBounds(7*s,7*s,s,s);
		a1.setBackground(white);
		a1.setOpaque(true);
		a1.setName("A1");
		
		ImageIcon bp = new ImageIcon(new ImageIcon("src\\bp.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		ImageIcon bb = new ImageIcon(new ImageIcon("src\\bb.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		ImageIcon bk = new ImageIcon(new ImageIcon("src\\bk.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		ImageIcon br = new ImageIcon(new ImageIcon("src\\br.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		ImageIcon bki = new ImageIcon(new ImageIcon("src\\bki.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		ImageIcon bq = new ImageIcon(new ImageIcon("src\\bq.png").getImage().getScaledInstance(s, s, Image.SCALE_DEFAULT));
		a8.setIcon(bp);
		a7.setIcon(bb);
		a6.setIcon(bk);
		a5.setIcon(br);
		a4.setIcon(bki);
		a3.setIcon(bq);


		MouseListener m1 = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {

				JComponent jc = (JComponent)e.getSource();
				TransferHandler th = jc.getTransferHandler();
				th.exportAsDrag(jc,e,TransferHandler.COPY);
				x = jc.getX();
				y = jc.getY();
				JLabel jl = (JLabel)jc;
				Icon ii = jl.getIcon();
				Color jcc = jc.getBackground();
				//System.out.println(jl.getIcon());
				//System.out.println(jl.getLocation());
				Container parent = jc.getParent();
				JLabel replacement = new JLabel();
				replacement.setBounds(jc.getBounds());
				replacement.addMouseListener(this);
				replacement.setTransferHandler(new TransferHandler("icon"));
				parent.remove(jc);
				replacement.setBackground(jcc);
				replacement.setOpaque(true);
				parent.add(replacement);
				parent.repaint();

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				JComponent jc = (JComponent)e.getSource();
				newx = jc.getX();
				newy = jc.getY();
				JLabel jl = (JLabel)jc;
				System.out.println(jl.getName());
				//get new x and y
			}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}


		};


		/*pic1.addMouseListener(m1);
		pic2.addMouseListener(m1);
		pic3.addMouseListener(m1);



		pic1.setTransferHandler(new TransferHandler("icon"));
		pic2.setTransferHandler(new TransferHandler("icon"));
		pic3.setTransferHandler(new TransferHandler("icon"));


		add(pic1);
		add(pic2);
		add(pic3);*/
		
		h8.addMouseListener(m1);
		h7.addMouseListener(m1);
		h6.addMouseListener(m1);
		h5.addMouseListener(m1);
		h4.addMouseListener(m1);
		h3.addMouseListener(m1);
		h2.addMouseListener(m1);
		h1.addMouseListener(m1);
		
		g8.addMouseListener(m1);
		g7.addMouseListener(m1);
		g6.addMouseListener(m1);
		g5.addMouseListener(m1);
		g4.addMouseListener(m1);
		g3.addMouseListener(m1);
		g2.addMouseListener(m1);
		g1.addMouseListener(m1);
		
		f8.addMouseListener(m1);
		f7.addMouseListener(m1);
		f6.addMouseListener(m1);
		f5.addMouseListener(m1);
		f4.addMouseListener(m1);
		f3.addMouseListener(m1);
		f2.addMouseListener(m1);
		f1.addMouseListener(m1);
		
		e8.addMouseListener(m1);
		e7.addMouseListener(m1);
		e6.addMouseListener(m1);
		e5.addMouseListener(m1);
		e4.addMouseListener(m1);
		e3.addMouseListener(m1);
		e2.addMouseListener(m1);
		e1.addMouseListener(m1);
		
		d8.addMouseListener(m1);
		d7.addMouseListener(m1);
		d6.addMouseListener(m1);
		d5.addMouseListener(m1);
		d4.addMouseListener(m1);
		d3.addMouseListener(m1);
		d2.addMouseListener(m1);
		d1.addMouseListener(m1);
		
		c8.addMouseListener(m1);
		c7.addMouseListener(m1);
		c6.addMouseListener(m1);
		c5.addMouseListener(m1);
		c4.addMouseListener(m1);
		c3.addMouseListener(m1);
		c2.addMouseListener(m1);
		c1.addMouseListener(m1);
		
		b8.addMouseListener(m1);
		b7.addMouseListener(m1);
		b6.addMouseListener(m1);
		b5.addMouseListener(m1);
		b4.addMouseListener(m1);
		b3.addMouseListener(m1);
		b2.addMouseListener(m1);
		b1.addMouseListener(m1);
		
		a8.addMouseListener(m1);
		a7.addMouseListener(m1);
		a6.addMouseListener(m1);
		a5.addMouseListener(m1);
		a4.addMouseListener(m1);
		a3.addMouseListener(m1);
		a2.addMouseListener(m1);
		a1.addMouseListener(m1);
		
		h8.setTransferHandler(new TransferHandler("icon"));
		h7.setTransferHandler(new TransferHandler("icon"));
		h6.setTransferHandler(new TransferHandler("icon"));
		h5.setTransferHandler(new TransferHandler("icon"));
		h4.setTransferHandler(new TransferHandler("icon"));
		h3.setTransferHandler(new TransferHandler("icon"));
		h2.setTransferHandler(new TransferHandler("icon"));
		h1.setTransferHandler(new TransferHandler("icon"));
		
		g8.setTransferHandler(new TransferHandler("icon"));
		g7.setTransferHandler(new TransferHandler("icon"));
		g6.setTransferHandler(new TransferHandler("icon"));
		g5.setTransferHandler(new TransferHandler("icon"));
		g4.setTransferHandler(new TransferHandler("icon"));
		g3.setTransferHandler(new TransferHandler("icon"));
		g2.setTransferHandler(new TransferHandler("icon"));
		g1.setTransferHandler(new TransferHandler("icon"));
		
		f8.setTransferHandler(new TransferHandler("icon"));
		f7.setTransferHandler(new TransferHandler("icon"));
		f6.setTransferHandler(new TransferHandler("icon"));
		f5.setTransferHandler(new TransferHandler("icon"));
		f4.setTransferHandler(new TransferHandler("icon"));
		f3.setTransferHandler(new TransferHandler("icon"));
		f2.setTransferHandler(new TransferHandler("icon"));
		f1.setTransferHandler(new TransferHandler("icon"));
		
		e8.setTransferHandler(new TransferHandler("icon"));
		e7.setTransferHandler(new TransferHandler("icon"));
		e6.setTransferHandler(new TransferHandler("icon"));
		e5.setTransferHandler(new TransferHandler("icon"));
		e4.setTransferHandler(new TransferHandler("icon"));
		e3.setTransferHandler(new TransferHandler("icon"));
		e2.setTransferHandler(new TransferHandler("icon"));
		e1.setTransferHandler(new TransferHandler("icon"));
		
		d8.setTransferHandler(new TransferHandler("icon"));
		d7.setTransferHandler(new TransferHandler("icon"));
		d6.setTransferHandler(new TransferHandler("icon"));
		d5.setTransferHandler(new TransferHandler("icon"));
		d4.setTransferHandler(new TransferHandler("icon"));
		d3.setTransferHandler(new TransferHandler("icon"));
		d2.setTransferHandler(new TransferHandler("icon"));
		d1.setTransferHandler(new TransferHandler("icon"));
		
		c8.setTransferHandler(new TransferHandler("icon"));
		c7.setTransferHandler(new TransferHandler("icon"));
		c6.setTransferHandler(new TransferHandler("icon"));
		c5.setTransferHandler(new TransferHandler("icon"));
		c4.setTransferHandler(new TransferHandler("icon"));
		c3.setTransferHandler(new TransferHandler("icon"));
		c2.setTransferHandler(new TransferHandler("icon"));
		c1.setTransferHandler(new TransferHandler("icon"));
		
		b8.setTransferHandler(new TransferHandler("icon"));
		b7.setTransferHandler(new TransferHandler("icon"));
		b6.setTransferHandler(new TransferHandler("icon"));
		b5.setTransferHandler(new TransferHandler("icon"));
		b4.setTransferHandler(new TransferHandler("icon"));
		b3.setTransferHandler(new TransferHandler("icon"));
		b2.setTransferHandler(new TransferHandler("icon"));
		b1.setTransferHandler(new TransferHandler("icon"));
		
		a8.setTransferHandler(new TransferHandler("icon"));
		a7.setTransferHandler(new TransferHandler("icon"));
		a6.setTransferHandler(new TransferHandler("icon"));
		a5.setTransferHandler(new TransferHandler("icon"));
		a4.setTransferHandler(new TransferHandler("icon"));
		a3.setTransferHandler(new TransferHandler("icon"));
		a2.setTransferHandler(new TransferHandler("icon"));
		a1.setTransferHandler(new TransferHandler("icon"));
		
		add(h8);
		add(h7);
		add(h6);
		add(h5);
		add(h4);
		add(h3);
		add(h2);
		add(h1);
		
		add(g8);
		add(g7);
		add(g6);
		add(g5);
		add(g4);
		add(g3);
		add(g2);
		add(g1);
		
		add(f8);
		add(f7);
		add(f6);
		add(f5);
		add(f4);
		add(f3);
		add(f2);
		add(f1);
		
		add(e8);
		add(e7);
		add(e6);
		add(e5);
		add(e4);
		add(e3);
		add(e2);
		add(e1);
		
		add(d8);
		add(d7);
		add(d6);
		add(d5);
		add(d4);
		add(d3);
		add(d2);
		add(d1);
		
		add(c8);
		add(c7);
		add(c6);
		add(c5);
		add(c4);
		add(c3);
		add(c2);
		add(c1);
		
		add(b8);
		add(b7);
		add(b6);
		add(b5);
		add(b4);
		add(b3);
		add(b2);
		add(b1);
		
		add(a8);
		add(a7);
		add(a6);
		add(a5);
		add(a4);
		add(a3);
		add(a2);
		add(a1);

		setLayout(null);
		setSize(top,side);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

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

	public static void main(String[] args) {

		new DragAndDrop();
	}


}