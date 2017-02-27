package dk2.logic;

public abstract class Map {

	// -------------ATTRIBUTES-------------//
	private char[][] board;
	private int size;
	Hero mieic_student;

	// --------------METHODS---------------//

	// Map constructor
	public Map(int mapSize) {
		this.size = mapSize;

		char[][] b = new char[mapSize][mapSize];

		for (int i = 0; i < mapSize - 1; i++) {
			for (int j = 0; j < mapSize - 1; j++) {
				b[i][j] = ' ';
			}
		}

		this.board = b;
	}

	// Getters
	public char[][] getBoard() {
		return board;
	}

	public int getSize() {
		return this.size;
	}

	public void setBoardCell(int lin, int col, char symbol) {

		board[lin][col] = symbol;
	}

	public Hero getHero() {
		return this.mieic_student;
	};

	public Door[] getDoor() {
		Door[] d = {};

		return d;
	}

	public void buildExtWalls() {

		// filling off the top and bottom lines
		for (int i = 0; i < this.size; i++) {
			board[0][i] = 'X';
		}

		for (int i = 0; i < size; i++) {
			board[board.length - 1][i] = 'X';
		}

		// filling right and left column
		for (int i = 0; i < size; i++) {
			board[i][0] = 'X';
		}

		for (int i = 0; i < size; i++) {
			board[i][board.length - 1] = 'X';
		}
	}

	public void setDoors(Door d) {
		setBoardCell(d.getLin(), d.getCol(), d.getSymbol());
	}

	public void buildMaze() {
	};

	public void drawHero() {

		this.setBoardCell(this.mieic_student.getLin(), this.mieic_student.getCol(), this.mieic_student.getSymbol());
	}

	public void eraseHero() {
		this.setBoardCell(this.mieic_student.getLin(), this.mieic_student.getCol(), ' ');
	}

	public boolean canHeroMove(char direction) {

		Hero h = this.getHero();
		
		switch (direction) {

		case 'W':
			if((board[h.getLin()-1][h.getCol()] == 'X') | (board[h.getLin()-1][h.getCol()] == 'I'))
				return false;
			else
				this.getHero().under = board[h.getLin()-1][h.getCol()];
			
			break;

		case 'S':
			if((board[h.getLin()+1][h.getCol()] == 'X') | (board[h.getLin()+1][h.getCol()] == 'I'))
				return false;
			else
				this.getHero().under = board[h.getLin()+1][h.getCol()];
			
			break;

		case 'A':
			if((board[h.getLin()][h.getCol()-1] == 'X') | (board[h.getLin()][h.getCol()-1] == 'I'))
				return false;
			else
				this.getHero().under = board[h.getLin()][h.getCol()-1];
			
			break;

		case 'D':
			if((board[h.getLin()][h.getCol()+1] == 'X') | (board[h.getLin()][h.getCol()+1] == 'I'))
				return false;
			else
				this.getHero().under = board[h.getLin()][h.getCol()+1];
			
			break;

		}
		
					
		return true;

	}

	public void moveHero(char direction) {

		switch (direction) {

		case 'W':
			if(!canHeroMove(direction))
				return;
			this.eraseHero();
			this.getHero().moveUp();
			this.drawHero();
			break;

		case 'S':
			if(!canHeroMove(direction))
				return;
			this.eraseHero();
			this.getHero().moveDown();
			this.drawHero();
			break;

		case 'A':
			if(!canHeroMove(direction))
				return;
			this.eraseHero();
			this.getHero().moveLeft();
			this.drawHero();
			break;

		case 'D':
			if(!canHeroMove(direction))
				return;
			this.eraseHero();
			this.getHero().moveRight();
			this.drawHero();
			break;

		}
	}
}
