package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * A class that has 10% to generate ShoutAction 
 * 
 * 
 * @author Peir Jing Chuang
 *
 */
public class ShoutBehaviour implements Behaviour {
	private ArrayList<String> words = new ArrayList<String>();
	protected Random random = new Random();
	
	/**
	 * Returns a ShoutAction that shout a word if the length of 
	 * word is less than 10 and the probability of shout a word
	 * which is 10 % is achieved
	 * 
	 * @param actor actor that carry out shoutAction
	 */
	
	private Action shout(Actor actor) {
		ArrayList<Action> actions = new ArrayList <Action>();
		words.add("Brainnn");
		for (String word : words) {
			if ((word.length() < 10) && (random.nextDouble() < 0.1)) {
				actions.add(new ShoutAction(words.get(0)));
				
			}
		}
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		else {return null;}
	}
	
	/**
	 * Returns an shout action .
	 * 
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		
		return shout(actor);
	}

}
