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
	private int numberOfHumanLeft = 0;
	private int numberOfZombieLeft = 0;
	private boolean playerLeft = true;
	private boolean quit = false;
	private boolean mamboMarieAllowedToAppear = true;
	
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
	 * Add a Mambo Marie on the compound map if there is no Mambo Marie on the specific map. 
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
		boolean appearanceOfMamboMarie = false;
		
		while (stillRunning()) {
			GameMap gameMapCompound= gameMaps.get(0);
			if (!actorLocations.contains(player)) {
				playerLeft = false;
				break;
			}
			else {
				GameMap playersMap = actorLocations.locationOf(player).map();
				playersMap.draw(display);
			}
			// Process all the actors.
			for (Actor actor : actorLocations) {
				if (stillRunning())
					processActorTurn(actor);
			}
			if (!gameMapCompound.contains(mamboMarie)) {
				//AttackAction attackAction = new AttackAction(mamboMarie);
				if (mamboMarieAllowedToAppear) {
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
						appearanceOfMamboMarie = true;
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
			if(appearanceOfMamboMarie) {
				if ((counter < 30) &&(!gameMapCompound.contains(mamboMarie))) {
					mamboMarieAllowedToAppear = false;
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
	 * The game is considered to still be running if the player is still around and at least 1 human is still on the map and
	 * at least 1 zombie or 1 Mambo Marie is on the map.
	 * 
	 * 
	 *
	 * @return true if the player is still on the map and at least a human is on the map and there is at least 1 zombie or 1 Mambo Marie is on the map.
	 */

  	@Override 
  	protected boolean stillRunning() 
  	{ 
  		if (quit) {
  			return false;
  		}
  		numberOfHumanLeft =0;
  		numberOfZombieLeft = 0;
  		for (Actor actor : actorLocations) {
  			if (!(actor instanceof Player)) {
	  			if (actor instanceof Human || actor instanceof Farmer){
	  				if (gameMaps.get(0).contains(actor)){
	  					//System.out.println(numberOfHumanLeft);
	  					numberOfHumanLeft +=1;
	  				}
	  			}
  			}
  			if (actor instanceof Zombie || actor instanceof MamboMarie) {
  				if (gameMaps.get(0).contains(actor)) {
  					numberOfZombieLeft +=1;
  				}
  			}
  			if (actor instanceof Player) {
  				if (gameMaps.get(0).contains(actor) || gameMaps.get(1).contains(actor)) {
  					playerLeft = true;
  				} else {	
  					playerLeft = false;
  				}
  			}
  		}
  		if (playerLeft== false || numberOfHumanLeft ==0) {
  			return false;	
  		}
  		else if (numberOfZombieLeft==0) {
  			return false;
  		}
  		else {
  			return true;
  		}	
  	}
  	
  	/**
	 * Return a string that can be displayed when the game ends.
	 *
	 * @return the string "Quit Game" if player decides to quit game, the string "Player loses" when player is dead or there is no human left on the map
	 * the string" Player wins" when there's no zombie and Mambo Marie on the map.
	 */
  	@Override
	protected String endGameMessage() {
  		if (quit) {
  			return "Quit Game";
  		}
  		else if (numberOfHumanLeft==0 || !playerLeft) {
  			return "Player loses";
  		}
  		else{
  			return "Player wins";
  		}
  	}

  	/**
  	 * A setter that set quit to true.
  	 */
  	public void quitGame() {
  		quit = true;
  	}
  	

}
 

	
