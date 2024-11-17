package Fallbound.Controller.Menu;

import Fallbound.Controller.Controller;
import Fallbound.Model.Menu.Menu;

public abstract class MenuController<T extends Menu> extends Controller<T> {
    public MenuController(T model) {
        super(model);
    }
}
