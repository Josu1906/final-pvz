package pvz.logic.gameobjects;

import pvz.logic.Game;
import pvz.utils.Position;

public class ZombieList {
	private Zombie[] list_zombie;
	private int cont = 0;
	
	public ZombieList() {
		list_zombie = new Zombie[32];
	}
	
	public int size() {
		
		return cont;
	}
	
	public String iconInPosition(Position p) {
		int i = 0;
		String ret = "";
		while(i < cont && !list_zombie[i].isInPosition(p)) {
			i++;
		}
		
		if(i < cont && list_zombie[i].isInPosition(p)) {
			
			ret = list_zombie[i].getIcon();
		}
		
		return ret;
	}
	
	public void add(Zombie z) {
		list_zombie[cont] = z;
		cont++;
	}
	
	/*public boolean damage(Position p, int damage) {
		int i = 0;
		boolean ret = false;
		
		while(i < cont && !list_zombie[i].isInPosition(p)) {
			i++;
		}
		
		if(list_zombie[i].isInPosition(p)) {
			list_zombie[i].receiveAttack(damage);
			ret = true;
		}
		
		return ret;
	}*/
	
	public boolean damage(Position p, int damage) {
		int i=0;
		int colMasDerecha=Game.NUM_COLS;
		int zombieMasDerecha=-1;
		
		while(i<cont){
			if(list_zombie[i].isHorizontallyAligned(p) && 
					list_zombie[i].getCol()<colMasDerecha &&
					list_zombie[i].getCol()> p.col() &&
					list_zombie[i].isAlive() ) {
				colMasDerecha=list_zombie[i].getCol();
				zombieMasDerecha=i;
			}
			i++;
		}
		if (zombieMasDerecha!=-1) {
			list_zombie[zombieMasDerecha].receiveAttack(damage);
		}
		
		return zombieMasDerecha!=-1;
	}
	
	public boolean isEmpty(Position p) {
		int i = 0;
		boolean empty = true;
		
		while(i < cont && empty) {
			if(list_zombie[i].isInPosition(p)) {
				empty = false;
			}
			
			i++;
		}
		
		return empty;
	}
	
	public void removeDead() {
		for(int i = 0; i < cont; i++) {
			
			if(!list_zombie[i].isAlive()) {
				
				for(int j = i; j < cont - 1; j++) {
					list_zombie[j] = list_zombie[j + 1];
				}
				
				list_zombie[cont - 1] = null;
				cont--;
				
			}
			
		}
	}
	
	public boolean anyInColumn(int col) {
		
		int i = 0;
		boolean ret = false;
		Position new_position = new Position(0, col);
		
		
		while(i < cont && !ret) {
			if(list_zombie[i].isVerticallyAligned(new_position)) {
				ret = true;
			}
			i++;
		}
		
		return ret;
	}
	
	public void update() {
		
		for(int i = 0; i < cont; i++) {
			list_zombie[i].update();
		}
	}
}
