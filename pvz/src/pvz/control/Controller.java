package pvz.control;

import pvz.logic.Game;
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
		// TODO fill your code
		while(!game.hasGameFinished() && !game.playerQuits()) {
			
			
			this.view.showGame();
			String[] words = this.view.getPrompt();
			if(game.checkGameObject(words)) {				
				Commands comand = parseCommand(words[0].toLowerCase());
				
				switch(comand) {
					case ADD -> executeADD(words);
					case RESET -> executeReset();
					case LIST -> executeList();
					case HELP -> executeHelp();
					case EXIT -> executeExit();
					case NONE -> executeNONE();
				}
			}
			
			
		}
		
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
		game.getDescription();
	}

	private void executeReset() {
		// TODO Auto-generated method stub
		game.reset();
	}

	private void executeADD(String[] words) {
		Position position = new Position(Integer.parseInt(words[2]), Integer.parseInt(words[3]));
		game.addGameObject(words[1], position);
		game.update();
	}
	
	private void executeNONE() {
		game.update();
	}

	private Commands parseCommand(String w) {
		switch(w) {
		case "add", "a" -> {return Commands.ADD;}
		case "list", "l" -> {return Commands.LIST;}
		case "help", "h" -> {return Commands.HELP;}
		case "exit", "e" -> {return Commands.EXIT;}
		default -> {return Commands.NONE;}
		}	
	}

}