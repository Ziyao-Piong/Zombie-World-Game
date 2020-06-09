package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.World;

public class NewWorld extends World{
	
	private MamboMarie mamboMarie = new MamboMarie("Mambo Marie");
	protected Random random = new Random();
	private ArrayList<Location> locations = new ArrayList<Location>();
	private int numberOfHumanleft = 0;
	private int numberOfZombieleft = 0;
	private boolean playerLeft = true;
	private boolean quit = false;
	public NewWorld(Display display) {
		super(display);
		
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * Run the game.
	 *
	 * On each iteration the gameloop does the following: - displays the player's
	 * map - processes the actions of every Actor in the game, regardless of map
	 *
	 * We could either only process the actors on the current map, which would make
	 * time stop on the other maps, or we could process all the actors. We chose to
	 * process all the actors.
	 *
	 * @throws IllegalStateException if the player doesn't exist
	 */
	@Override
	public void run() {
		if (player == null)
			throw new IllegalStateException();
		

		// initialize the last action map to nothing actions;
		for (Actor actor : actorLocations) {
			lastActionMap.put(actor, new DoNothingAction());
		}

		// This loop is basically the whole game
		int counter = 0;
		boolean pass = true;
		boolean appearance = false;
		while (stillRunning() && quit == false) {
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);
			GameMap gameMapCompound= gameMaps.get(0);
			
			// Process all the actors.
			for (Actor actor : actorLocations) {
				if (stillRunning())
					processActorTurn(actor);
					
			}
			if (!gameMapCompound.contains(mamboMarie)) {
				//AttackAction attackAction = new AttackAction(mamboMarie);
				if (pass) {
					Location firstEdge = new Location(gameMapCompound, 0, 0);
					Location secondEdge = new Location(gameMapCompound, gameMapCompound.getXRange().max(), 0);
					Location thirdEdge = new Location(gameMapCompound, 0, gameMapCompound.getYRange().max());
					Location fourthEdge = new Location(gameMapCompound, gameMapCompound.getXRange().max(), gameMapCompound.getYRange().max());
					locations.add(firstEdge);
					locations.add(secondEdge);
					locations.add(thirdEdge);
					locations.add(fourthEdge);
					int value = random.nextInt(20);
					if (value == 0) {
						gameMapCompound.at(locations.get(random.nextInt(locations.size())).x(),locations.get(random.nextInt(locations.size())).y()).addActor(mamboMarie);
						appearance = true;
						counter = 0;
					}
				}
					
			}
			if (gameMapCompound.contains(mamboMarie)) {
				counter +=1;
				
			}
			if (counter == 30) {
				gameMapCompound.removeActor(mamboMarie);
				
			}
			if(appearance) {
				if ((counter < 30) &&(!gameMapCompound.contains(mamboMarie))) {
					pass = false;
					}
			}

			// Tick over all the maps. For the map stuff.
			for (GameMap gameMap : gameMaps) {
				
			
				gameMap.tick();
			}
				

		}
		display.println(endGameMessage());
	}


	/**
	 * Returns true if the game is still running.
	 *
	 * The game is considered to still be running if the player is still around.
	 *
	 * @return true if the player is still on the map.
	 */

  	@Override 
  	protected boolean stillRunning() 
  	{ 
  		int numberOfHumanLeft = 0;
  		int numberOfZombieLeft = 0;
  		if (actorLocations.contains(player)){
  			playerLeft = true;
  			
  		}
  		else {
  			playerLeft = false;
  		}
  		
  		for (Actor actor : actorLocations) {
  			if (actor instanceof Human){
  				numberOfHumanLeft +=1;
  				continue;
  				
  			}	
  			else {
  				numberOfZombieLeft += 1;
  			}
  		}
  		if ((numberOfHumanLeft == 0)&&(numberOfZombieLeft >0)) {
  			return false;
  		}
  		if ((numberOfZombieLeft == 0)&&(numberOfHumanLeft >0)) {
  			return false;
  		}
  		
  		if (playerLeft) {
  			return true;
  		}
  		else {
  			return false;
  		}	
  
  	}
  	/**
	 * Return a string that can be displayed when the game ends.
	 *
	 * @return the string "Game Over"
	 */
  	@Override
	protected String endGameMessage() {
  		if (quit) {	
  			return "Quit Game";	
  		}
  		else if (numberOfHumanleft>0) {
  			return "Player wins";
  		}
  		else if (numberOfZombieleft>0){
  			return "Player loses";
  		}
  		else {
  			return "Player loses";
  		}
  			
  	}

  	public void quitGame() {
  		quit = true;
  	}
}
 

	
