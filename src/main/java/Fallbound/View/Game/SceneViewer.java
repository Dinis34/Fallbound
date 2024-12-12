package Fallbound.View.Game;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Elements.BreakableWall;
import Fallbound.Model.Game.Elements.Coin;
import Fallbound.Model.Game.Elements.Element;
import Fallbound.Model.Game.Elements.Enemies.FloatingEnemy;
import Fallbound.Model.Game.Elements.Wall;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Position;
import Fallbound.View.Game.Elements.*;
import Fallbound.View.Game.Elements.Enemies.FloatingEnemyViewer;
import Fallbound.View.Viewer;

import static Fallbound.View.Theme.*;

public class SceneViewer extends Viewer<Scene> {

    private final WallViewer wallViewer = new WallViewer();
    private final BreakableWallViewer breakableWallViewer = new BreakableWallViewer();
    private final PlayerViewer playerViewer = new PlayerViewer();
    private final CoinViewer coinViewer = new CoinViewer();
    private final FloatingEnemyViewer floatingEnemyViewer = new FloatingEnemyViewer();
    private final BulletViewer bulletViewer = new BulletViewer();

    public SceneViewer(Scene model) {
        super(model);
    }

    protected void drawHud(GUI gui) {
        // draw black background behind hud
        for (int x = 0; x < getModel().getWidth(); x++) {
            for (int y = 0; y < 5; y++) {
                gui.drawText(new Position(x, y), "█", FALLBOUND_BLACK);
            }
        }

        String coinCount = String.valueOf(getModel().getPlayer().getCollectedCoins());

        gui.drawText(new Position(2, 1), "TIME", FALLBOUND_WHITE);
        gui.drawText(new Position(7, 1), getModel().timeToString(getModel().getCurrentTime()), FALLBOUND_WHITE);
        gui.drawText(new Position(2, 2), "HIGHSCORE", FALLBOUND_LIGHT_GRAY);
        gui.drawText(new Position(12, 2), "17:21:11", FALLBOUND_LIGHT_GRAY); // placeholder
        gui.drawText(new Position(83, 1), "COINS", FALLBOUND_WHITE);
        gui.drawText(new Position(82 - coinCount.length(), 1), coinCount, FALLBOUND_GOLD);
        gui.drawText(new Position(2, 3), "\u2665 \u2665 \u2661", FALLBOUND_RED); // placeholder
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
            if (enemy instanceof FloatingEnemy) {
                floatingEnemyViewer.draw(gui, (FloatingEnemy) enemy, getModel().getCameraOffset());
            }
        });
        playerViewer.draw(gui, getModel().getPlayer(), getModel().getCameraOffset());
        drawHud(gui);
    }
}
