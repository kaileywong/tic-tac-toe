import java.awt.*;
import java.awt.event.*;


public class Board {
	public final static int OFFSET_X = 40, OFFSET_Y = 200;
	private GameObject[][] grid;
	private int clicks, games;
	private String p1, p2;

	public Board() {
		grid = new GameObject[3][3];
		games = 1;
		fill();
		reset();
	}

	public void reset() {
		setClicks(0);
		clearGrid();
		p1 = "x";
		p2 = "o";		
		incrementGames();
	}

	private void clearGrid() {
		for(GameObject[] r : grid) {
			for(GameObject go : r) {
				go.setO(false);
				go.setX(false);
			}
		}
	}

	private void fill() {
		for(int r=0; r<grid.length; r++) {
			for(int c=0 ; c<grid[r].length; c++) {
				grid[r][c] = new GameObject(r,c);
			}
		}		
	}

	public void draw(Graphics g) {
		for(int r=0; r<grid.length; r++) {
			for(int c=0 ; c<grid[r].length; c++) {
				GameObject go = grid[r][c];
				go.draw(g);
			}
		}	
	}

	public void handleClick(MouseEvent e) {
		int r = (e.getY()-OFFSET_Y)/GameObject.SQUARE_SIZE;
		int c = (e.getX()-OFFSET_X)/GameObject.SQUARE_SIZE;
		if(inbounds(r,c)) {
			if(clicks%2==0 && !grid[r][c].hasX() && !grid[r][c].hasO()) {
				if(p1.equals("x")) {
					grid[r][c].setX(true);
					clicks++;
				}
				else if(p1.equals("o")) {
					grid[r][c].setO(true);
					clicks++;
				}
			}
			if(clicks%2==1 && !grid[r][c].hasX() && !grid[r][c].hasO()) {
				if(p2.equals("o")) {
					grid[r][c].setO(true);
					clicks++;
				}
				else if(p2.equals("x")) {
					grid[r][c].setX(true);
					clicks++;
				}
			}
		}
	}

	public boolean checkGameOver() {
		if(grid[0][0].hasX() && grid[1][1].hasX() && grid[2][2].hasX())
			return true;
		if(grid[0][0].hasO() && grid[1][1].hasO() && grid[2][2].hasO())
			return true;
		if(grid[0][2].hasX() && grid[1][1].hasX() && grid[2][0].hasX())
			return true;
		if(grid[0][2].hasO() && grid[1][1].hasO() && grid[2][0].hasO())
			return true;
		if(grid[0][0].hasX() && grid[0][1].hasX() && grid[0][2].hasX())
			return true;
		if(grid[0][0].hasO() && grid[0][1].hasO() && grid[0][2].hasO())
			return true;
		if(grid[1][0].hasX() && grid[1][1].hasX() && grid[1][2].hasX())
			return true;
		if(grid[1][0].hasO() && grid[1][1].hasO() && grid[1][2].hasO())
			return true;
		if(grid[2][0].hasX() && grid[2][1].hasX() && grid[2][2].hasX())
			return true;
		if(grid[2][0].hasO() && grid[2][1].hasO() && grid[2][2].hasO())
			return true;
		if(grid[0][0].hasX() && grid[1][0].hasX() && grid[2][0].hasX())
			return true;
		if(grid[0][0].hasO() && grid[1][0].hasO() && grid[2][0].hasO())
			return true;
		if(grid[0][1].hasX() && grid[1][1].hasX() && grid[2][1].hasX())
			return true;
		if(grid[0][1].hasO() && grid[1][1].hasO() && grid[2][1].hasO())
			return true;
		if(grid[0][2].hasX() && grid[1][2].hasX() && grid[2][2].hasX())
			return true;
		if(grid[0][2].hasO() && grid[1][2].hasO() && grid[2][2].hasO())
			return true;
		return false;
	}

	private boolean inbounds(int r, int c) {
		return r>=0 && c>=0 && r<=2 && c<=2;
	}

	public void oClicked(ActionEvent e) {
		if(clicks==0) {
			p1 = "o";
			p2 = "x";
		}
	}

	public void xClicked(ActionEvent e) {
		if(clicks==0) {
			p1 = "x";
			p2 = "o";
		}
	}

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public String getP1() {
		return p1;
	}

	public String getP2() {
		return p2;
	}

	public int getGames() {
		return games;
	}

	public void incrementGames() {
		this.games++;
	}

}
