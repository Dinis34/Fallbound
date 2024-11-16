package Fallbound.View.Game.Elements;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Elements.Element;

public interface ElementViewer<T extends Element> {
    void draw(GUI gui, T element);
}
