package Fallbound.Controller.Game.Elements;

import Fallbound.Controller.Controller;
import Fallbound.Controller.Sound.SoundController;
import Fallbound.Game;
import Fallbound.Model.Game.Elements.*;
import Fallbound.Model.Game.Elements.Enemies.*;
import Fallbound.Model.Sound.SoundOption;
import Fallbound.Model.Vector;
import Fallbound.State.GameState;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;

public class PlayerController extends Controller<Player> {
    private static final int[] MOVE_LEFT_KEYS = {KeyEvent.VK_LEFT, KeyEvent.VK_A};
    private static final int[] MOVE_RIGHT_KEYS = {KeyEvent.VK_RIGHT, KeyEvent.VK_D};

    public PlayerController(Player player) {
        super(player);
    }

    @Override
    public void step(Game game, Set<Integer> keys, long time) throws IOException {
        Player player = getModel();

        if (keys.contains(KeyEvent.VK_SPACE)) {
            if (player.isOnGround()) player.jump();
            else player.shoot();
        }

        if (containsAnyKey(keys, MOVE_LEFT_KEYS)) player.moveLeft();
        if (containsAnyKey(keys, MOVE_RIGHT_KEYS)) player.moveRight();
        if (!containsAnyKey(keys, MOVE_LEFT_KEYS) && !containsAnyKey(keys, MOVE_RIGHT_KEYS)) player.stop();

        player.update();
        updateGroundState(player);

        player.getScene().getCoins().stream()
                .filter(coin -> player.getScene().isColliding(coin.getPosition(), player.getPosition()))
                .findFirst()
                .ifPresent(coin -> collectCoin(player, (Coin) coin));

        player.getScene().getEnemies().stream()
                .filter(enemy -> player.getScene().isColliding(player.getPosition(), enemy.getPosition()))
                .findFirst()
                .ifPresent(enemy -> handleEnemyCollision(player, enemy));

        player.getScene().updateEnemies();
        if (player.getHealth() <= 0) game.setState(GameState.GAME_OVER);
    }

    private boolean containsAnyKey(Set<Integer> keys, int[] targetKeys) {
        for (int key : targetKeys) {
            if (keys.contains(key)) return true;
        }
        return false;
    }

    private void updateGroundState(Player player) {
        for (Element element : player.getScene().getWalls()) {
            if (player.getScene().isColliding(
                    player.getPosition(),
                    element.getPosition().add(new Vector(0, -1)))) {
                player.getVelocity().setY(0);
                player.setOnGround(true);

                if (player.getNumBullets() != player.getMaxNumBullets()) {
                    SoundController.getInstance().playSound(SoundOption.MENU_MOVE);
                    player.setNumBullets(player.getMaxNumBullets());
                }
                return;
            }
        }
        player.setOnGround(false);
    }

    private void collectCoin(Player player, Coin coin) {
        player.getScene().removeCoin(coin);
        SoundController.getInstance().playSound(SoundOption.COIN);
        player.setCollectedCoins(player.getCollectedCoins() + 1);
    }

    private void handleEnemyCollision(Player player, Element enemy) {
        boolean isStomping = player.getLastPosition().getY() < enemy.getPosition().getY() - 0.1;

        if (isStomping && enemy instanceof Stompable) {
            player.getScene().removeEnemy((Enemy) enemy);
            SoundController.getInstance().playSound(SoundOption.ENEMY_DEATH);
        } else {
            player.takeDamage();
        }

        player.getVelocity().setY(player.getJumpForce() / 1.5);
        if (!isStomping) player.getVelocity().setX(0);
    }
}