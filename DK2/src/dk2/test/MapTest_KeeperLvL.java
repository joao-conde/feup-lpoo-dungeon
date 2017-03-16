package dk2.test;

import dk2.logic.*;

public class MapTest_KeeperLvL extends Map {
	
	private Door d1;
	private Key key;
	private Ogre[] ogres;
	
	public MapTest_KeeperLvL(int size) {
		
		super(size);
		
		this.key = new Key();
			
		Ogre o = new Ogre();
		this.ogres = new Ogre[1];
		this.ogres[0] = o;
		
		this.d1 = new Door();		
		
	}
	
	public Door[] getDoor() {

		Door[] nextLvLDoors = { d1 };

		return nextLvLDoors;
	}
	
	public void buildMaze() {

		this.buildExtWalls();

		
		// set Key initial location 
		key.setLin(3);
		key.setCol(2);
		this.setBoardCell(key.getLin(), key.getCol(), key.getSymbol());
 
		// set Hero initial location
		this.getHero().setLin(2);
		this.getHero().setCol(2); 
		drawHero();

		// set ogres[0] initial location
		ogres[0].setLin(2);
		ogres[0].setCol(4);
		this.setBoardCell(ogres[0].getLin(), ogres[0].getCol(), ogres[0].getSymbol());
		
		// set ogres[0]'s club initial location
		ogres[0].getClub().setLin(2);
		ogres[0].getClub().setCol(5);
		this.setBoardCell(ogres[0].getClub().getLin(), ogres[0].getClub().getCol(), ogres[0].getClub().getSymbol());

		// set Doors
		d1.setLin(2);
		d1.setCol(0);				

		// Placing doors in map1
		this.setDoors(d1);

	}
	
	public void heroReachedKey(){
		
		if (getHero().getLin() == getKey().getLin() && getHero().getCol() == getKey().getCol()) {
			
			getHero().setHasKey(true);
			getHero().setSymbol('K'); 
			getKey().setSymbol(' ');
			
			setBoardCell(getKey().getLin(),getKey().getCol(), 'K'); 
		}

		
	}
	
	public Key getKey() {
		return this.key;
	}
	
	public Ogre getOgre(){
		return this.ogres[0];
	}
	
	public void stunOgres() {
		
		for (Ogre o : ogres) {
			if (this.getBoard()[o.getLin() - 1][o.getCol() - 1] == 'A'
					|| this.getBoard()[o.getLin() - 1][o.getCol()] == 'A'
					|| this.getBoard()[o.getLin() - 1][o.getCol() + 1] == 'A')
				o.setStunned(true);

			if (this.getBoard()[o.getLin()][o.getCol() - 1] == 'A'
					|| this.getBoard()[o.getLin()][o.getCol() + 1] == 'A')
				o.setStunned(true);

			if (this.getBoard()[o.getLin() + 1][o.getCol() - 1] == 'A'
					|| this.getBoard()[o.getLin() + 1][o.getCol()] == 'A'
					|| this.getBoard()[o.getLin() + 1][o.getCol() + 1] == 'A')
				o.setStunned(true);
			
			
			
			if (this.getBoard()[o.getLin() - 1][o.getCol() - 1] == 'K'
					|| this.getBoard()[o.getLin() - 1][o.getCol()] == 'K'
					|| this.getBoard()[o.getLin() - 1][o.getCol() + 1] == 'K')
				o.setStunned(true);

			if (this.getBoard()[o.getLin()][o.getCol() - 1] == 'K'
					|| this.getBoard()[o.getLin()][o.getCol() + 1] == 'K')
				o.setStunned(true);

			if (this.getBoard()[o.getLin() + 1][o.getCol() - 1] == 'K'
					|| this.getBoard()[o.getLin() + 1][o.getCol()] == 'K'
					|| this.getBoard()[o.getLin() + 1][o.getCol() + 1] == 'K')
				o.setStunned(true);

		}
	}

	public void openDoors() {

		for(Door d: getDoor()){
			if(getHero().getLin() == d.getLin() && getHero().getCol() == d.getCol()+1 && getHero().getHasKey()){
				d.openDoor();
			}
		}

	}
	
	public boolean hasHeroWon() {

		for(Door d: getDoor()){
			
			if(getHero().getLin() == d.getLin() && getHero().getCol() == d.getCol() && d.isOpen() && getHero().getHasKey()){
				return true;
			}
		}
		
		return false;

	}
}
