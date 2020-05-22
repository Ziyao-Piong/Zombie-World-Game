//package game;
//
//import edu.monash.fit2099.engine.*;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class RipeCrop extends Item {
//    private Location ripeCropLocation;
//
//    public RipeCrop(Location ripeCropLocation) {
//        super("ripe crop", 'C', false);
//        this.ripeCropLocation = ripeCropLocation;
//        this.allowableActions=setAllowableActions(ripeCropLocation);
//
//    }
//
//    public Actions setAllowableActions(Actor actor,Location ripeCropLocation) {
//        List<Exit> exits = new ArrayList<Exit>(ripeCropLocation.getExits());
//        Collections.shuffle(exits);
//        for (Exit e : exits) {
//            if (e.getDestination().getItems().contains(this)) {
//                allowableActions.add(new HarvestAction(ripeCropLocation));
//            }
//        }
//        return allowableActions;
////        if ripeCropLocation.map().locationOf
////        if (ripeCropLocation.getItems() == 'C') {
////            actions.add(new HarvestAction(location));
////        }
////        if (location.map().locationOf(actor).getGround().getDisplayChar() == 'C')
////            actions.add(new HarvestAction(location));
////        return actions;
////        allowableActions.add(new HarvestAction(ripeCropLocation));
//    }
//}
