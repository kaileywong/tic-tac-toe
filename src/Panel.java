import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Panel extends JPanel  {
	private final int SIZE_PANEL = 700;
	private static final int BUTTON_SIZE = 60;
	private Board board;
	private JToolBar toolbar;
	private JButton Xbutton, Obutton;
	private String player1, player2;
	private int p1wins, p2wins, seconds, minutes, games;
	public static boolean CLOSE = false;


	public Panel() {
		games = 1;
		Timer t = new Timer(1000, null);
		t.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				seconds++;
				if(seconds==60) {
					minutes++;
					seconds = 0;
				}
				repaint();
			}
		});
		seconds = 0;
		minutes = 0;
		p1wins = 0;
		p2wins = 0;
		player1 = JOptionPane.showInputDialog(this, "Enter a name for player 1:");
		player2 = JOptionPane.showInputDialog(this, "Enter a name for player 2:");
		board = new Board();
		setUpToolbar();
		this.setBackground(new Color(239,176,12));
		this.setPreferredSize(new Dimension(SIZE_PANEL,SIZE_PANEL));
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int sel;
				board.handleClick(e);
				repaint();
				if(board.checkGameOver()) {
					if(board.getClicks()%2==0) {
						sel = JOptionPane.showConfirmDialog(null, player2 + " wins! \n"
								+ "Play again?");
						p2wins++;
					}
					else {
						sel = JOptionPane.showConfirmDialog(null, player1 + " wins! \n"
								+ "Play again?");
						p1wins++;
					}
					if(sel==0) {
						games++;
						board.reset();
						repaint();
					}
					else
						CLOSE = true;
				}
				else {
					if(board.getClicks()==9) {
						sel = JOptionPane.showConfirmDialog(null, "It's a draw! \n"
								+ "Play again?");	
						if(sel==0)
							board.reset();
						else
							Panel.CLOSE = true;
					}
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}

		});
		JOptionPane.showInternalMessageDialog(null, player1 + ", select your symbol.");
		t.start();

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Tic-tac-toe!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Panel p = new Panel();
		frame.add(p);
		frame.pack();
		frame.setVisible(true);
		frame.add(p.getToolbar(),BorderLayout.NORTH);
		Timer t = new Timer(10,null);
		t.start();
		while(t.isRunning()) {
			if(Panel.CLOSE) {
				frame.dispose();
				t.stop();
			}
		}
	}
	private void setUpToolbar() {
		toolbar = new JToolBar();
		setUpButtons();
		add(Obutton,BorderLayout.WEST);
		add(Xbutton, BorderLayout.WEST);
		add(toolbar);
	}


	private void setUpButtons() {
		Icon o, x;
		try {
			o = new ImageIcon(((BufferedImage)ImageIO.read(new File("o.png"))).getScaledInstance
					(BUTTON_SIZE, BUTTON_SIZE, BufferedImage.SCALE_SMOOTH));
			Obutton= new JButton(o); 
		} catch (IOException e) {
			Obutton= new JButton("O"); 

		} 
		try {
			x = new ImageIcon(((BufferedImage)ImageIO.read(new File("x.png"))).getScaledInstance
					(BUTTON_SIZE, BUTTON_SIZE, BufferedImage.SCALE_SMOOTH));
			Xbutton= new JButton(x); 

		} catch (IOException e) {
			Xbutton= new JButton("X"); 
		} 		
		Obutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				board.oClicked(e);			
			}

		});
		Xbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				board.xClicked(e);			
			}

		});
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(!this.hasFocus())
			this.requestFocusInWindow();
		board.draw(g);
		g.setFont(new Font("Impact", 0, 20));
		drawScores(g);
		g.setColor(Color.blue);
		drawTimer(g);
	}


	private void drawScores(Graphics g) {
		if(board.getClicks()%2==0)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.black);
		g.drawString(player1+": "+p1wins + " wins", 
				(int) (Board.OFFSET_X+GameObject.SQUARE_SIZE*3.25), Board.OFFSET_Y+20);
		if(board.getClicks()%2==1)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.black);
		g.drawString(player2+": "+p2wins + " wins", 
				(int) (Board.OFFSET_X+GameObject.SQUARE_SIZE*3.25), Board.OFFSET_Y+50);		
	}

	private void drawTimer(Graphics g) {
		String time = "";		
		if(minutes < 10)
			time += "0"+minutes+":";
		else
			time += minutes+":";
		if(seconds < 10)
			time += "0"+seconds;
		else
			time += seconds;
		g.drawString("Round: "+ games + "  " + time, 
				Board.OFFSET_X+GameObject.SQUARE_SIZE*3/2, Board.OFFSET_Y-20);
	}

	public JToolBar getToolbar() {
		return toolbar;
	}


	public void setToolbar(JToolBar toolbar) {
		this.toolbar = toolbar;
	}

}