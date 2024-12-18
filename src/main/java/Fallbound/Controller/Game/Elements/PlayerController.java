package Fallbound.Controller.Game.Elements;

import Fallbound.Controller.Controller;
import Fallbound.Controller.Sound.SoundController;
import Fallbound.Game;
import Fallbound.Model.Game.Elements.Coin;
import Fallbound.Model.Game.Elements.Element;
import Fallbound.Model.Game.Elements.Enemies.Enemy;
import Fallbound.Model.Game.Elements.Enemies.Stompable;
import Fallbound.Model.Game.Elements.Player;
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
        handlePlayerMovement(keys);
        handlePlayerInteractions(game);
    }

    private void handlePlayerMovement(Set<Integer> keys) {
        Player player = getModel();

        if (keys.contains(KeyEvent.VK_SPACE)) {
            if (player.isOnGround()) {
                player.jump();
            } else {
                player.shoot();
            }
        }

        handleDirectionalMovement(player, keys);
    }

    private void handleDirectionalMovement(Player player, Set<Integer> keys) {
        if (isAnyKeyPressed(keys, MOVE_LEFT_KEYS)) {
            player.moveLeft();
        }

        if (isAnyKeyPressed(keys, MOVE_RIGHT_KEYS)) {
            player.moveRight();
        }

        if (!isAnyKeyPressed(keys, MOVE_LEFT_KEYS) &&
                !isAnyKeyPressed(keys, MOVE_RIGHT_KEYS)) {
            player.stop();
        }
    }

    private boolean isAnyKeyPressed(Set<Integer> keys, int[] targetKeys) {
        for (int key : targetKeys) {
            if (keys.contains(key)) {
                return true;
            }
        }
        return false;
    }

    private void handlePlayerInteractions(Game game) throws IOException {
        Player player = getModel();

        player.update();

        updatePlayerGroundState(player);
        handleCoinInteractions(player);
        handleEnemyInteractions(player);

        player.getScene().updateEnemies();
        checkGameOver(game, player);
    }

    private void updatePlayerGroundState(Player player) {
        boolean onGround = checkBottomCollision(player);
        player.setOnGround(onGround);
    }

    private boolean checkBottomCollision(Player player) {
        for (Element element : player.getScene().getWalls()) {
            if (player.getScene().isColliding(
                    player.getPosition(),
                    element.getPosition().add(new Vector(0, -1))
            )) {
                player.getVelocity().setY(0);

                replenishBulletsIfNeeded(player);

                return true;
            }
        }
        return false;
    }

    private void replenishBulletsIfNeeded(Player player) {
        if (player.getNumBullets() != player.getMaxNumBullets()) {
            SoundController.getInstance().playSound(SoundOption.MENU_MOVE);
            player.setNumBullets(player.getMaxNumBullets());
        }
    }

    private void handleCoinInteractions(Player player) {
        for (Element coin : player.getScene().getCoins()) {
            if (player.getScene().isColliding(coin.getPosition(), player.getPosition())) {
                collectCoin(player, (Coin) coin);
                break;
            }
        }
    }

    private void collectCoin(Player player, Coin coin) {
        player.getScene().removeCoin(coin);
        SoundController.getInstance().playSound(SoundOption.COIN);
        player.setCollectedCoins(player.getCollectedCoins() + 1);
    }

    private void handleEnemyInteractions(Player player) {
        for (Element enemy : player.getScene().getEnemies()) {
            if (player.getScene().isColliding(player.getPosition(), enemy.getPosition())) {
                processEnemyCollision(player, enemy);
                break;
            }
        }
    }

    private void processEnemyCollision(Player player, Element enemy) {
        boolean isStomping = player.getLastPosition().getY() < enemy.getPosition().getY() - 0.1;

        if (isStomping) {
            handleStompingCollision(player, enemy);
        } else {
            handleDirectCollision(player);
        }
    }

    private void handleStompingCollision(Player player, Element enemy) {
        if (enemy instanceof Stompable) {
            player.getScene().removeEnemy((Enemy) enemy);
            SoundController.getInstance().playSound(SoundOption.ENEMY_DEATH);
            player.getVelocity().setY(player.getJumpForce() / 1.5);
        } else {
            player.takeDamage();
            player.getVelocity().setY(player.getJumpForce() / 1.5);
        }
    }

    private void handleDirectCollision(Player player) {
        player.takeDamage();
        player.getVelocity().setY(player.getJumpForce() / 1.5);
        player.getVelocity().setX(0);
    }

    private void checkGameOver(Game game, Player player) throws IOException {
        if (player.getHealth() <= 0) {
            game.setState(GameState.GAME_OVER);
        }
    }
}