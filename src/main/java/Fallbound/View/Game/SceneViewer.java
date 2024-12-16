package Fallbound.View.Game;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Elements.BreakableWall;
import Fallbound.Model.Game.Elements.Coin;
import Fallbound.Model.Game.Elements.Element;
import Fallbound.Model.Game.Elements.Enemies.NormalEnemy;
import Fallbound.Model.Game.Elements.Enemies.ShellEnemy;
import Fallbound.Model.Game.Elements.Enemies.SpikeEnemy;
import Fallbound.Model.Game.Elements.Wall;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Position;
import Fallbound.View.Game.Elements.*;
import Fallbound.View.Game.Elements.Enemies.NormalEnemyViewer;
import Fallbound.View.Game.Elements.Enemies.ShellEnemyViewer;
import Fallbound.View.Game.Elements.Enemies.SpikeEnemyViewer;
import Fallbound.View.Viewer;

import static Fallbound.View.Theme.*;

public class SceneViewer extends Viewer<Scene> {

    private final WallViewer wallViewer = new WallViewer();
    private final BreakableWallViewer breakableWallViewer = new BreakableWallViewer();
    private final PlayerViewer playerViewer = new PlayerViewer();
    private final CoinViewer coinViewer = new CoinViewer();
    private final NormalEnemyViewer normalEnemyViewer = new NormalEnemyViewer();
    private final BulletViewer bulletViewer = new BulletViewer();
    private final ShellEnemyViewer shellEnemyViewer = new ShellEnemyViewer();
    private final SpikeEnemyViewer spikeEnemyViewer = new SpikeEnemyViewer();
    private final CollectibleViewer collectibleViewer = new CollectibleViewer();

    public SceneViewer(Scene model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui, long time) {
        getModel().updateCameraOffset();
        getModel().handleBullets();
        getModel().getBullets().forEach(bullet -> bulletViewer.draw(gui, bullet, getModel().getCameraOffset()));
        getModel().getCoins().forEach(coin -> coinViewer.draw(gui, (Coin) coin, getModel().getCameraOffset()));
        for (Element wall : getModel().getWalls()) {
            if (wall.getClass() == Wall.class) {
                wallViewer.draw(gui, (Wall) wall, getModel().getCameraOffset());
            } else if (wall.getClass() == BreakableWall.class) {
                breakableWallViewer.draw(gui, (BreakableWall) wall, getModel().getCameraOffset());
            }
        }
        getModel().getEnemies().forEach(enemy -> {
            if (enemy instanceof NormalEnemy) {
                normalEnemyViewer.draw(gui, (NormalEnemy) enemy, getModel().getCameraOffset());
            } else if (enemy instanceof ShellEnemy) {
                shellEnemyViewer.draw(gui, (ShellEnemy) enemy, getModel().getCameraOffset());
            } else if (enemy instanceof SpikeEnemy) {
                spikeEnemyViewer.draw(gui, (SpikeEnemy) enemy, getModel().getCameraOffset());
            }
        });
        getModel().getCollectibles().forEach(collectible -> collectibleViewer.draw(gui, collectible, getModel().getCameraOffset()));
        playerViewer.draw(gui, getModel().getPlayer(), getModel().getCameraOffset());
        drawHud(gui);
    }

    protected void drawHud(GUI gui) {
        // draw black background behind hud
        for (int x = 0; x < getModel().getWidth(); x++) {
            for (int y = 0; y < 5; y++) {
                gui.drawText(new Position(x, y), "█", FALLBOUND_BLACK);
            }
        }

        String coinCount = String.valueOf(getModel().getPlayer().getCollectedCoins());

        StringBuilder playerHealth = new StringBuilder();
        for (int i = 0; i < getModel().getPlayer().getMaxHealth(); i++) {
            if (i >= getModel().getPlayer().getHealth()) {
                playerHealth.append("♡ ");
            } else {
                playerHealth.append("♥ ");
            }
        }

        StringBuilder bullets = new StringBuilder();
        for (int i = getModel().getPlayer().getMaxNumBullets() - 1; i >= 0; i--) {
            if (i >= getModel().getPlayer().getNumBullets()) {
                bullets.append("░");
            } else {
                bullets.append("█");
            }
        }

        gui.drawText(new Position(2, 1), "TIME", FALLBOUND_WHITE);
        gui.drawText(new Position(7, 1), getModel().timeToString(getModel().getCurrentTime()), FALLBOUND_WHITE);
        gui.drawText(new Position(2, 2), "HIGHSCORE", FALLBOUND_LIGHT_GRAY);
        gui.drawText(new Position(12, 2), "17:21:11", FALLBOUND_LIGHT_GRAY); // placeholder
        gui.drawText(new Position(83, 1), "COINS", FALLBOUND_WHITE);
        gui.drawText(new Position(82 - coinCount.length(), 1), coinCount, FALLBOUND_GOLD);
        gui.drawText(new Position(88 - bullets.length(), 2), bullets.toString(), FALLBOUND_GOLD);
        gui.drawText(new Position(2, 3), playerHealth.toString(), FALLBOUND_RED);
    }
}