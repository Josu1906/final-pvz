package pvz.control;

import pvz.logic.Game;
import pvz.logic.gameobjects.Peashooter;
import pvz.logic.gameobjects.Sunflower;
import pvz.utils.Position;
import pvz.view.GamePrinter;
import pvz.view.GameView;
import pvz.view.Messages;

/**
 * Input/output coordinator of the game (the C in MVC).
 *
 * <p>Owns the game loop: reads a line from stdin, parses it into a
 * command, validates parameters (plant type, position), delegates
 * state changes to {@link Game}, and triggers a board reprint via
 * {@link tp1.pvz.view.GameView>} when the cycle advances. It holds no game
 * state of its own; the source of truth is always {@link Game}.
 */
public class Controller {
	
	private enum Commands{
		ADD, LIST, NONE, HELP, RESET, EXIT
	}

	private final Game game;
	private final GameView view;

	public Controller(Game game) {
		this.game = game;
		this.view = new GamePrinter(game);
	}

	/**
	 * Runs the game logic.
	 */
	public void run() {
		view.showGame();

		// TODO fill your code
		while(!game.hasGameFinished() && !game.playerQuits()) {
			String[] str = this.view.getPrompt();
			if(str[0].equalsIgnoreCase("reset") || str[0].equalsIgnoreCase("r")) executeReset();
			else if (str[0].equalsIgnoreCase("list") || str[0].equalsIgnoreCase("l")) executeList();
			else if (str[0].equalsIgnoreCase("none") || str[0].equalsIgnoreCase("n") || str[0].equalsIgnoreCase("") ) executeNONE();
			else if	(str[0].equalsIgnoreCase("exit") || str[0].equalsIgnoreCase("e")) executeExit();
			else if	(str[0].equalsIgnoreCase("help") || str[0].equalsIgnoreCase("h")) executeHelp();
			else if (str[0].equalsIgnoreCase("add") || str[0].equalsIgnoreCase("a")) executeADD(str);	
			else view.showMessage(Messages.error(Messages.UNKNOWN_COMMAND));
			view.showGame();

//			if(checkCommand(comand)) {				
//				Commands comand = parseCommand(words[0]);
//				
//				switch(comand) {
//					case ADD -> executeADD(words);
//					case RESET -> executeReset();
//					case LIST -> executeList();
//					case HELP -> executeHelp();
//					case EXIT -> executeExit();
//					case NONE -> executeNONE();
//				}
//			} else view.showMessage(Messages.error(Messages.UNKNOWN_COMMAND));
//			view.showGame();
		}
		view.showEndMessage();
		
	}
	
	private void executeExit() {
		// TODO Auto-generated method stub
		game.quit();
	}

	private void executeHelp() {
		// TODO Auto-generated method stub
		System.out.println(Messages.HELP);
	}

	private void executeList() {
		// TODO Auto-generated method stub
		//game.getDescription();
		view.showMessage(Sunflower.getDescription());
		view.showMessage(Peashooter.getDescription());
	}

	private void executeReset() {
		// TODO Auto-generated method stub
		game.reset();
	}

	private void executeADD(String[] words) {
		if (words.length<4) view.showMessage(Messages.error(Messages.COMMAND_PARAMETERS_MISSING));
		else if (words.length >4) view.showMessage(Messages.error(Messages.INVALID_COMMAND));
		else {
			Position position = new Position(Integer.parseInt(words[3]), Integer.parseInt(words[2]));
			if (!game.checkGameObject(words[1])) view.showMessage(Messages.error(Messages.INVALID_GAME_OBJECT));
			else if (!game.isInsideBoard(position) || !game.isEmpty(position)) view.showMessage(Messages.error(Messages.INVALID_POSITION));
			else if(!game.hasEnoughCoins(words[1])) view.showMessage(Messages.error(Messages.NOT_ENOUGH_COINS));
			else {
				game.addGameObject(words[1], position);
				game.update();
			} 
	    }
	}
	
	private void executeNONE() {
		game.update();
	}

//	private Commands parseCommand(String w) {
//		switch(w) {
//		case "add", "a" -> {return Commands.ADD;}
//		case "list", "l" -> {return Commands.LIST;}
//		case "help", "h" -> {return Commands.HELP;}
//		case "exit", "e" -> {return Commands.EXIT;}
//		case "reset", "r"  -> {return Commands.RESET;}
//		default -> {return Commands.NONE;}
//		}	
//	}
	
//	private boolean checkCommand(String[] str) {
//	
//		boolean ret = false;
//		
//		if(str[0].equalsIgnoreCase("reset") || str[0].equalsIgnoreCase("r") || 
//				str[0].equalsIgnoreCase("list") || str[0].equalsIgnoreCase("l") || 
//				str[0].equalsIgnoreCase("none") || str[0].equalsIgnoreCase("n") || 
//				str[0].equalsIgnoreCase("exit") || str[0].equalsIgnoreCase("e") || 
//				str[0].equalsIgnoreCase("help") || str[0].equalsIgnoreCase("h") ||
//				str[0].equalsIgnoreCase("add") || str[0].equalsIgnoreCase("a"))	{		
//					ret = true;
//		}
//		return ret;
//	}
}