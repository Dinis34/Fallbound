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
    public PlayerController(Player player) {
        super(player);
    }

    @Override
    public void step(Game game, Set<Integer> keys, long time) throws IOException {
        handlePlayerMovement(keys);
        handlePlayerInteractions(game);
    }

    private void handlePlayerMovement(Set<Integer> keys) {
        if (keys.contains(KeyEvent.VK_SPACE)) {
            if (getModel().getOnGround()) {
                getModel().jump();
            } else {
                getModel().shoot();
            }
        }
        if (keys.contains(KeyEvent.VK_LEFT) || keys.contains(KeyEvent.VK_A)) {
            getModel().moveLeft();
        }
        if (keys.contains(KeyEvent.VK_RIGHT) || keys.contains(KeyEvent.VK_D)) {
            getModel().moveRight();
        }
        if (!keys.contains(KeyEvent.VK_LEFT) && !keys.contains(KeyEvent.VK_RIGHT)
                && !keys.contains(KeyEvent.VK_A) && !keys.contains(KeyEvent.VK_D)) {
            getModel().stop();
        }
    }

    private void handlePlayerInteractions(Game game) throws IOException {
        Player player = getModel();
        player.update();

        boolean onGround = checkBottomCollision();
        player.setOnGround(onGround);

        checkCoinCollision();
        checkEnemyCollision();

        player.getScene().updateEnemies();

        if (player.getHealth() <= 0) {
            game.setState(GameState.GAME_OVER);
        }
    }

    private boolean checkBottomCollision() {
        Player player = getModel();
        for (Element element : player.getScene().getWalls()) {
            if (player.getScene().isColliding(player.getPosition(), element.getPosition().add(new Vector(0, -1)))) {
                player.getVelocity().setY(0);
                if (player.getNumBullets() != player.getMaxNumBullets()) {
                    SoundController.getInstance().playSound(SoundOption.MENU_MOVE);
                }
                player.setNumBullets(player.getMaxNumBullets());
                return true;
            }
        }
        return false;
    }

    private void checkCoinCollision() {
        Player player = getModel();
        for (Element coin : player.getScene().getCoins()) {
            if (player.getScene().isColliding(coin.getPosition(), player.getPosition())) {
                player.getScene().removeCoin((Coin) coin);
                SoundController.getInstance().playSound(SoundOption.COIN);
                player.setCollectedCoins(player.getCollectedCoins() + 1);
                break;
            }
        }
    }

    private void checkEnemyCollision() {
        Player player = getModel();
        for (Element enemy : player.getScene().getEnemies()) {
            if (player.getScene().isColliding(player.getPosition(), enemy.getPosition())) {
                boolean isStomping = player.getLastPosition().getY() < enemy.getPosition().getY() - 0.1;
                if (isStomping) {
                    if (enemy instanceof Stompable) {
                        player.getScene().removeEnemy((Enemy) enemy);
                        SoundController.getInstance().playSound(SoundOption.ENEMY_DEATH);
                        player.getVelocity().setY(player.getJumpForce() / 1.5);
                    } else {
                        player.takeDamage();
                        player.getVelocity().setY(player.getJumpForce() / 1.5);
                    }
                } else {
                    player.takeDamage();
                    player.getVelocity().setY(player.getJumpForce() / 1.5);
                    player.getVelocity().setX(0);
                }
                break;
            }
        }
    }
}