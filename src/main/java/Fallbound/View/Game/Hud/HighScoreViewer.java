package Fallbound.View.Game.Hud;


import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Hud.HighScore;

import static Fallbound.View.Theme.FALLBOUND_LIGHT_GRAY;

public class HighScoreViewer implements HudViewer<HighScore> {
    @Override
    public void draw(GUI gui, HighScore highScore){
        gui.drawText(highScore.getPosition(), "highscore: " + highScore.getScore(), FALLBOUND_LIGHT_GRAY);
    }

}
