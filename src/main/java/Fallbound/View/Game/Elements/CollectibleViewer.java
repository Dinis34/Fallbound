package Fallbound.View.Game.Elements;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Elements.Collectibles.Collectible;
import Fallbound.Model.Vector;
import Fallbound.View.Theme;

public class CollectibleViewer implements ElementViewer<Collectible> {
    
    @Override
    public void draw(GUI gui, Collectible element, int offset) {
        gui.drawText(element.getPosition().toPosition().applyOffset(offset), element.getIcon(), Theme.FALLBOUND_GOLD);
        if (element.shouldShowDescription()) {
            gui.drawText(new Vector(2, element.getPosition().getY() - 2).toPosition().applyOffset(offset), element.getDescription(), Theme.FALLBOUND_WHITE);
            gui.drawText(new Vector(2, element.getPosition().getY() - 1).toPosition().applyOffset(offset), element.getCost() + " COINS | shoot to purchase", Theme.FALLBOUND_GOLD);
        }
    }
}
