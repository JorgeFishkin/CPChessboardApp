import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI implements ActionListener {
	
	//first ever
	int count1 = 0;
	int count2 = 0;
	private JLabel label;
	private JLabel label2;
	private JFrame frame;
	private JPanel panel;
	private JButton button1;
	private JButton button2;
	
	//in person or virtual
	private JFrame introFrame;
	private JPanel introPanel;
	private JButton inPerson;
	private JButton virtual;
	private JLabel introLabel;
	
	//user name
	private static JTextField name;
	private JButton nameEntered;
	private JFrame nameFrame;
	private JPanel namePanel;
	private JLabel nameLabel;
	
	//black or white
	private JFrame colorFrame;
	private JPanel colorPanel;
	private JButton white;
	private JButton black;
	private JLabel color;
	
	//timer
	private JFrame timerFrame;
	private JPanel timerPanel;
	private JLabel timer;
	private JButton yestime;
	private JButton notime;
	
	//set up
	private JFrame boardFrame;
	private JPanel boardPanel;
	private JLabel board;
	
	private Object[] arrayToGive = new Object[4];

	public GUI() throws IOException {
		
		//frames
		frame = new JFrame();
		introFrame = new JFrame();
		nameFrame = new JFrame();
		colorFrame = new JFrame();
		timerFrame = new JFrame();
		boardFrame = new JFrame();
		
		//text field
		name = new JTextField();
		
		//buttons
		button1 = new JButton("Click me");
		button1.addActionListener(this);
		
		button2 = new JButton("Click me");
		button2.addActionListener(this);
		
		inPerson = new JButton("In Person");
		inPerson.addActionListener(this);
		
		virtual = new JButton("Virtual");
		virtual.addActionListener(this);
		
		white = new JButton("White");
		white.addActionListener(this);
		
		black = new JButton("Black");
		black.addActionListener(this);
		
		yestime = new JButton("Yes");
		yestime.addActionListener(this);
		
		notime = new JButton("No");
		notime.addActionListener(this);
		
		nameEntered = new JButton("Submit");
		nameEntered.addActionListener(this);
		
		//labels
		label = new JLabel("Number of clicks: 0");
		label2 = new JLabel("Number of clicks: 0");
		
		introLabel = new JLabel("Are you playing...");
		
		color = new JLabel("Are you playing as...");
		
		timer = new JLabel("Would you like a timer?");
		
		nameLabel = new JLabel("Please enter your name:");
		
		//first ever
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panel.setLayout(new GridLayout(0,1));
		panel.add(button1);
		panel.add(label);
		panel.add(button2);
		panel.add(label2);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Our GUI");
		frame.pack();
		frame.setVisible(true);
		
		//set up
				boardPanel = new JPanel();
				boardPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
				boardPanel.setLayout(new FlowLayout());
				BufferedImage myPicture = ImageIO.read(new File("C:\\Users\\edmei\\Documents\\CSEE\\board.jpg"));
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				boardPanel.add(picLabel);
				
				boardFrame.add(boardPanel);
				boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				boardFrame.setTitle("Set up the board");
				boardFrame.pack();
				boardFrame.setVisible(true);
		
		
		//timer
				timerPanel = new JPanel();
				timerPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
				timerPanel.setLayout(new GridLayout(0,1));
				timerPanel.add(timer);
				timerPanel.add(yestime);
				timerPanel.add(notime);
				
				timerFrame.add(timerPanel, BorderLayout.CENTER);
				timerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				timerFrame.setTitle("Timer Choice");
				timerFrame.pack();
				timerFrame.setVisible(true);
		
		
		//black or white
				colorPanel = new JPanel();
				colorPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
				colorPanel.setLayout(new GridLayout(0,1));
				colorPanel.add(color);
				colorPanel.add(white);
				colorPanel.add(black);
				
				colorFrame.add(colorPanel, BorderLayout.CENTER);
				colorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				colorFrame.setTitle("Choose Your Color");
				colorFrame.pack();
				colorFrame.setVisible(true);
		
		
		//user name
				namePanel = new JPanel();
				namePanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
				namePanel.setLayout(new GridLayout(0,1));
				namePanel.add(nameLabel);
				namePanel.add(name);
				namePanel.add(nameEntered);
				
				nameFrame.add(namePanel, BorderLayout.CENTER);
				nameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				nameFrame.setTitle("Welcome!");
				nameFrame.pack();
				nameFrame.setVisible(true);
		
		//in person or virtual
				introPanel = new JPanel();
				introPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
				introPanel.setLayout(new GridLayout(0,1));
				introPanel.add(introLabel);
				introPanel.add(inPerson);
				introPanel.add(virtual);
				
				introFrame.add(introPanel, BorderLayout.CENTER);
				introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				introFrame.setTitle("Welcome!");
				introFrame.pack();
				introFrame.setVisible(true);
	}
	
	public static void main(String[] args) throws IOException {
		new GUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton actionSource = (JButton)e.getSource();
		
		
		if(actionSource.equals(button1)){
	        // YOU BUTTON 1 CODE HERE
			count1++;
			label.setText("Number of clicks: " + count1);
	    } else if (actionSource.equals(button2)) {
	        // YOU BUTTON 2 CODE HERE
	    	count2++;
			label2.setText("Number of clicks: " + count2);
			frame.dispose();
	    } else if (actionSource.equals(inPerson)) {
	    	System.out.println("in person");
	    	introFrame.dispose();
	    	arrayToGive[0] = "Physically";
	    } else if (actionSource.equals(virtual)) {
	    	System.out.println("virtual");
	    	introFrame.dispose();
	    	arrayToGive[0] = "Virtually";
	    } else if (actionSource.equals(nameEntered)) {
	    	System.out.println(name.getText());
	    	nameFrame.dispose();
	    	arrayToGive[1] = name.getText();
	    }
	    else if (actionSource.equals(white)) {
	    	System.out.println("white");
	    	colorFrame.dispose();
	    	arrayToGive[2] = "White";
	    }
	    else if (actionSource.equals(black)) {
	    	System.out.println("black");
	    	colorFrame.dispose();
	    	arrayToGive[2] = "Black";
	    }
	    else if (actionSource.equals(yestime)) {
	    	System.out.println("yes timer");
	    	timerFrame.dispose();
	    	arrayToGive[3] = "Timer";
	    }
	    else if (actionSource.equals(notime)) {
	    	System.out.println("no timer");
	    	timerFrame.dispose();
	    	arrayToGive[3] = "No Timer";
	    }
		
	}


}
