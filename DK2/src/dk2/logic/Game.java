package dk2.logic;

public class Game {
	
	
	private Map[] levels;
	private int currentMap;
	
	
	public Game(){
		
		Map1 map1 = new Map1(10);
		Map2 map2 = new Map2(10);
		
		map1.buildMaze();
		map2.buildMaze();
		
		this.levels = new Map[2];
		levels[0] = map1;
		levels[1] = map2;
		
		this.currentMap = 0;
	}
	

	public boolean hasHeroWon() {
		
		char b[][] = levels[currentMap].getBoard();
		Hero h = levels[currentMap].getHero();
		Door[] nextLvLDoors = levels[currentMap].getDoor();
		
		for(int i = 0; i < nextLvLDoors.length; i++){
			
			if(nextLvLDoors[i].isOpen())
				if(currentMap == levels.length - 1)
					return true;
				else {
					currentMap++;
					return false;
				}
			
		}
		
		return false;		
	}
	
	public boolean isHeroDead(){
		
		char b[][] = levels[currentMap].getBoard();
		Hero h = levels[currentMap].getHero();
		
		//hero never is at the boundaries of the map, except when he wins so he ain't dead
		if(h.getCol() == 0 || h.getLin() == 0)
			return false;
		
		//check 3 positions above hero
		
		if((b[h.getLin()-1][h.getCol()-1] == 'G') | (b[h.getLin()-1][h.getCol()-1] == 'O') | (b[h.getLin()-1][h.getCol()-1] == '*'))
			return true;
		
		if((b[h.getLin()-1][h.getCol()] == 'G') | (b[h.getLin()-1][h.getCol()] == 'O') | (b[h.getLin()-1][h.getCol()] == '*'))
			return true;
		
		if((b[h.getLin()-1][h.getCol()+1] == 'G') | (b[h.getLin()-1][h.getCol()+1] == 'O') | (b[h.getLin()-1][h.getCol()+1] == '*'))
			return true;
		
		//check 3 positions below hero
		
		if((b[h.getLin()+1][h.getCol()-1] == 'G') | (b[h.getLin()+1][h.getCol()-1] == 'O') | (b[h.getLin()+1][h.getCol()-1] == '*'))
			return true;
		
		if((b[h.getLin()+1][h.getCol()] == 'G') | (b[h.getLin()+1][h.getCol()] == 'O') | (b[h.getLin()+1][h.getCol()] == '*'))
			return true;
		
		if((b[h.getLin()+1][h.getCol()+1] == 'G') | (b[h.getLin()+1][h.getCol()+1] == 'O') | (b[h.getLin()+1][h.getCol()+1] == '*'))
			return true;
		
		//check remaining positions
		
		if((b[h.getLin()][h.getCol()-1] == 'G') | (b[h.getLin()][h.getCol()-1] == 'O') | (b[h.getLin()][h.getCol()-1] == '*'))
			return true;
		
		if((b[h.getLin()][h.getCol()+1] == 'G') | (b[h.getLin()][h.getCol()+1] == 'O') | (b[h.getLin()][h.getCol()+1] == '*'))
			return true;
		
		
		return false;		
		
	}

	public Map getMap(){
		return levels[currentMap];
	}
}