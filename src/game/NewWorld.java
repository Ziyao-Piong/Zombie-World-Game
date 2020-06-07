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
	private Random random = new Random();
	private ArrayList<Location> locations = new ArrayList<Location>();
	//private Location firstEdge = new Location(gameMaps.get(0), 0, 0);
	//private Location secondEdge = new Location(gameMaps.get(0), gameMaps.get(0).getXRange().max(), 0);
	//private Location thirdEdge = new Location(gameMaps.get(0), 0, gameMaps.get(0).getYRange().max());
	//private Location fourthEdge = new Location(gameMaps.get(0), gameMaps.get(0).getXRange().max(), gameMaps.get(0).getYRange().max());

	public NewWorld(Display display) {
		super(display);
		//locations.add(firstEdge);
		//locations.add(secondEdge);
		//locations.add(thirdEdge);
		//locations.add(fourthEdge);
		
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
		while (stillRunning()) {
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);
			
			GameMap gameMapCompound= gameMaps.get(0);
			
			if (!gameMapCompound.contains(mamboMarie)) {
				int percentage = random.nextInt(20);
				if (percentage == 0) {
					Location firstEdge = new Location(gameMaps.get(0), 0, 0);
					Location secondEdge = new Location(gameMaps.get(0), gameMaps.get(0).getXRange().max(), 0);
					Location thirdEdge = new Location(gameMaps.get(0), 0, gameMaps.get(0).getYRange().max());
					Location fourthEdge = new Location(gameMaps.get(0), gameMaps.get(0).getXRange().max(), gameMaps.get(0).getYRange().max());
					locations.add(firstEdge);
					locations.add(secondEdge);
					locations.add(thirdEdge);
					locations.add(fourthEdge);
					//gameMapCompound.addActor(mamboMarie, locations.get(random.nextInt(locations.size())));
					//lastActionMap.put(mamboMarie, new DoNothingAction());
					actorLocations.add(mamboMarie, locations.get(random.nextInt(locations.size())));
				}
				
			}

			// Process all the actors.
			for (Actor actor : actorLocations) {
				if (stillRunning())
					processActorTurn(actor);
			}

			// Tick over all the maps. For the map stuff.
			for (GameMap gameMap : gameMaps) {
				
			
				gameMap.tick();
			}
				

		}
		display.println(endGameMessage());
	}
	
}
	
