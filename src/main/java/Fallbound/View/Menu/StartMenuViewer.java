package Fallbound.View.Menu;

import Fallbound.GUI.GUI;
import Fallbound.Model.Menu.StartMenu;
import Fallbound.Model.Position;
import Fallbound.View.Theme;

public class StartMenuViewer extends MenuViewer<StartMenu> {

    public StartMenuViewer(StartMenu menu) {
        super(menu, new Position(4, 26));
    }

    protected void drawBanner(GUI gui, Position position, String color) {
        String[] bannerLines = {
                "                                         .-'''-.",
                "                   .---..---.           '   _    \\                   _______",
                "                   |   ||   |/|       /   /` '.   \\          _..._   \\  ___ `'.",
                "     _.._          |   ||   |||      .   |     \\  '        .'     '.  ' |--.\\  \\",
                "   .' .._|         |   ||   |||      |   '      |  '      .   .-.   . | |    \\  '",
                "   | '       __    |   ||   |||  __  \\    \\     / /       |  '   '  | | |     |  '",
                " __| |__  .:--.'.  |   ||   |||/'__ '.`.   ` ..' /_    _  |  |   |  | | |     |  |",
                "|__   __|/ |   \\ | |   ||   ||:/`  '. '  '-...-'`| '  / | |  |   |  | | |     ' .'",
                "   | |   `\" __ | | |   ||   |||     | |         .' | .' | |  |   |  | | |___.' /'",
                "   | |    .'.''| | |   ||   |||\\    / '         /  | /  | |  |   |  |/_______.'/",
                "   | |   / /   | |_'---''---'|/\\'..' /         |   `'.  | |  |   |  |\\_______|/",
                "   | |   \\ \\._,\\ '/          '  `'-'`          '   .'|  '/|  |   |  |",
                "   |_|    `--'  `\"                              `-'  `--' '--'   '--'"
        };

        for (int i = 0; i < bannerLines.length; i++) {
            gui.drawText(new Position(position.getX(), position.getY() + i), bannerLines[i], color);
        }
    }

    @Override
    protected void drawElements(GUI gui, long time) {
        drawOptions(gui);
        drawBanner(gui, new Position(4, 6), Theme.FALLBOUND_RED);
        drawMenuTitle(gui, "⁜ START MENU ⁜", Theme.FALLBOUND_RED, new Position(4, 24));
    }
}
