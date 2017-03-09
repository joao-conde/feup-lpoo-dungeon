package dk2.logic;

import dk2.test.*;

public class Game {
	private int nOgres = 4;
	private Map[] levels;
	private int currentMap;

	public Game() {
		
//		Random nGen = new Random();
//		int n = nGen.nextInt(4);
		
		Map1 map1 = new Map1(10);
		Map2 map2 = new Map2(10, nOgres);
		MapTest_DungeonLvL test_map = new MapTest_DungeonLvL(5);

		map1.buildMaze();
		map2.buildMaze();
		test_map.buildMaze();

		this.levels = new Map[3];
		levels[0] = map1;
		levels[1] = map2;
		levels[2] = test_map;

		this.currentMap = 2;
		
	}
	public int getNOgres(){
		return nOgres;
	}

	public boolean hasHeroWon() {

		Hero h = levels[currentMap].getHero();
		Door[] nextLvLDoors = levels[currentMap].getDoor();

		for (int i = 0; i < nextLvLDoors.length; i++) {

			if (levels[currentMap] instanceof Map1) {
				if (((Map1) levels[currentMap]).isOnLever()) {
					for (int j = 0; j < nextLvLDoors.length; j++) {
						nextLvLDoors[j].openDoor();
						levels[currentMap].setBoardCell(nextLvLDoors[j].getLin(), nextLvLDoors[j].getCol(),
								nextLvLDoors[j].getSymbol());
					}
				}

				if (nextLvLDoors[i].getLin() == h.getLin() && nextLvLDoors[i].getCol() == (h.getCol() - 1) && nextLvLDoors[i].isOpen()) {
					currentMap++;
					return false;
				}

			}
			
			
			if (levels[currentMap] instanceof Map2) {
				
								
				if(h.getHasKey() && h.getLin() == nextLvLDoors[i].getLin() && h.getCol() == nextLvLDoors[i].getCol()+1){
					nextLvLDoors[i].openDoor();
				}
				
				
			}
			
						
				
			if (nextLvLDoors[i].getLin() == h.getLin() && nextLvLDoors[i].getCol() == h.getCol())
				if (currentMap == levels.length - 1)
					return true;
				else {
					currentMap++;
					return false;
				}

		}

		return false;
	}

	public boolean isHeroDead() {

		char b[][] = levels[currentMap].getBoard();
		Hero h = levels[currentMap].getHero();

		// hero never is at the boundaries of the map, except when he wins so he
		// ain't dead
		if (h.getCol() == 0 || h.getLin() == 0)
			return false;

		// check 3 positions above hero

		if ((b[h.getLin() - 1][h.getCol() - 1] == 'G') || (b[h.getLin() - 1][h.getCol() - 1] == 'O')
				|| (b[h.getLin() - 1][h.getCol() - 1] == '*'))
			return true;

		if ((b[h.getLin() - 1][h.getCol()] == 'G') || (b[h.getLin() - 1][h.getCol()] == 'O') 
				|| (b[h.getLin() - 1][h.getCol()] == '*'))
			return true;

		if ((b[h.getLin() - 1][h.getCol() + 1] == 'G') || (b[h.getLin() - 1][h.getCol() + 1] == 'O')
				|| (b[h.getLin() - 1][h.getCol() + 1] == '*'))
			return true;

		// check 3 positions below hero

		if ((b[h.getLin() + 1][h.getCol() - 1] == 'G') || (b[h.getLin() + 1][h.getCol() - 1] == 'O')
				|| (b[h.getLin() + 1][h.getCol() - 1] == '*'))
			return true;

		if ((b[h.getLin() + 1][h.getCol()] == 'G') || (b[h.getLin() + 1][h.getCol()] == 'O')
				|| (b[h.getLin() + 1][h.getCol()] == '*'))
			return true;

		if ((b[h.getLin() + 1][h.getCol() + 1] == 'G') || (b[h.getLin() + 1][h.getCol() + 1] == 'O')
				|| (b[h.getLin() + 1][h.getCol() + 1] == '*'))
			return true;

		// check remaining positions

		if ((b[h.getLin()][h.getCol() - 1] == 'G') || (b[h.getLin()][h.getCol() - 1] == 'O')
				|| (b[h.getLin()][h.getCol() - 1] == '*'))
			return true;

		if ((b[h.getLin()][h.getCol() + 1] == 'G') || (b[h.getLin()][h.getCol() + 1] == 'O')
				|| (b[h.getLin()][h.getCol() + 1] == '*'))
			return true;

		return false;

	}

	public Map getMap() {
		return levels[currentMap];
	}

	public void heroReachedKey() {

		if (levels[currentMap] instanceof Map1) {

			if (levels[currentMap].getHero().getLin() == ((Map1) levels[currentMap]).getLever().getLin()
					&& levels[currentMap].getHero().getCol() == ((Map1) levels[currentMap]).getLever().getCol()) {

				levels[currentMap].openDoors();

			}

			return;
		}

		if (levels[currentMap] instanceof Map2) {

			if (levels[currentMap].getHero().getLin() == ((Map2) levels[currentMap]).getKey().getLin()
					&& levels[currentMap].getHero().getCol() == ((Map2) levels[currentMap]).getKey().getCol()) {
				
				levels[currentMap].getHero().setHasKey(true);
				((Map2)levels[currentMap]).getKey().setSymbol(' ');
				
				((Map2)levels[currentMap]).setBoardCell(((Map2)levels[currentMap]).getKey().getLin(),((Map2)levels[currentMap]).getKey().getCol(), 'K'); 
			}

			return;
		}

	}
}
