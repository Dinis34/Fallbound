package Fallbound.Model.Game.Elements.Collectibles;

import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectibleFactory {
    public static List<Collectible> getRandomCollectibles(Vector basePosition, Scene scene) {
        List<Collectible> allCollectibles = List.of(
                new HealthCollectible(basePosition, scene),
                new MaxHealthCollectible(basePosition, scene),
                new SpeedCollectible(basePosition, scene),
                new BulletCountCollectible(basePosition, scene),
                new BulletSpeedCollectible(basePosition, scene),
                new JumpCollectible(basePosition, scene)
        );

        List<Collectible> selectedCollectibles = new ArrayList<>(allCollectibles);
        Collections.shuffle(selectedCollectibles);
        return selectedCollectibles.subList(0, 3);
    }
}
