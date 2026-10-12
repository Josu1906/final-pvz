package pvz.logic.gameobjects;
import pvz.logic.Game;
import pvz.logic.gameobjects.Peashooter;
import pvz.utils.Position;

public class PeashooterList {
	
	private Peashooter[] peashooter_list;
	private int cont = 0;
	private Game game;
	
	public PeashooterList(Game game) {
		this.game = game;
		peashooter_list = new Peashooter[32];
	}

	public boolean isEmpty(Position position) {
		int i = 0;
		boolean empty = true;
		
		while(i < cont && empty) {
			if(peashooter_list[i].isInPosition(position)) {
				empty = false;
			}
			
			i++;
		}
		
		return empty;
	}
	
	public void removeDead() {
		for(int i = 0; i < cont; i++) {
			
			if(!peashooter_list[i].isAlive()) {
				
				for(int j = i; j < cont - 1; j++) {
					peashooter_list[j] = peashooter_list[j + 1];
				}
				
				peashooter_list[cont - 1] = null;
				cont--;
				i--;
				
			}
			
		}
		
		
	}
	
	public void update() {
		
		removeDead();
		
		for(int j = 0; j < cont ; j++) {
			peashooter_list[j].update();
		}
	}
	
	public void add(Peashooter peashooter) {
		peashooter_list[cont] = peashooter;
		cont++;
		
	}
	
	/*public String iconInPosition(Position position) {
			
		boolean encontrado = false;
		int i = 0;
		String ret = null;
		
		while(i < cont && !peashooter_list[i].isInPosition(position)) i++;
		
		if(peashooter_list[i].isInPosition(position)){
			
			ret = peashooter_list[i].getIcon();
		}
		
		return ret;
			
	}*/

	public String iconInPosition(Position position) {
			
		int i = 0;
		String ret = null;
		
		while(i < cont && !peashooter_list[i].isInPosition(position)) i++;
		
		if(i<cont) {
			
			ret = peashooter_list[i].getIcon();
		}
		
		return ret;
			
	}
		
	/*public void receiveDamage(Position p, int damage) {
		int i = 0;
		while(i < cont && !peashooter_list[i].isInPosition(p)) {
			i++;
		}
		
		if(peashooter_list[i].isInPosition(p)) {
			peashooter_list[i].receiveDamage(damage);
		}
	}*/
	
	public void receiveDamage(Position p, int damage) {
		
		int i = 0;		
		while(i < cont && !peashooter_list[i].isInPosition(p)) i++;
		if(i<cont) peashooter_list[i].receiveDamage(damage);
	}
}
