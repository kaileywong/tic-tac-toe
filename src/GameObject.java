import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

public class GameObject {
	public static Image x, o;
	public static final int  SQUARE_SIZE = 150;
	private int row, col;
	private boolean X, O;
	

	public GameObject(int r, int c) {
		this.row = r;
		this.col = c;
		setX(false);
		setO(false);
		setUpImages();
	}
	
	private void setUpImages() {
		if(x == null) {
			try {
				x = ((BufferedImage)ImageIO.read(new File("x.png"))).getScaledInstance
						(GameObject.SQUARE_SIZE, GameObject.SQUARE_SIZE, 
								BufferedImage.SCALE_SMOOTH);			
			}
			catch(IIOException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(o == null) {
			try {
				o = ((BufferedImage)ImageIO.read(new File("o.png"))).getScaledInstance
						(GameObject.SQUARE_SIZE, GameObject.SQUARE_SIZE, 
								BufferedImage.SCALE_SMOOTH);			
			}
			catch(IIOException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public boolean hasX() {
		return X;
	}

	public void setX(boolean X) {
		this.X = X;
	}

	public boolean hasO() {
		return O;
	}

	public void setO(boolean O) {
		this.O = O;
	}

	public void draw(Graphics g) {
		g.drawRect(Board.OFFSET_X+col*SQUARE_SIZE, Board.OFFSET_Y+row*SQUARE_SIZE,
				SQUARE_SIZE, SQUARE_SIZE);	
		if(X) {
			g.drawImage(x, Board.OFFSET_X+col*GameObject.SQUARE_SIZE, 
					Board.OFFSET_Y+row*GameObject.SQUARE_SIZE, null);
		}
		if(O) {
			g.drawImage(o, Board.OFFSET_X+col*GameObject.SQUARE_SIZE, 
					Board.OFFSET_Y+row*GameObject.SQUARE_SIZE, null);
		}
	}



}
