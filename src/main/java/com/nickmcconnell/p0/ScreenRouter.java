package com.nickmcconnell.p0;

import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.screens.Screen;

/**
 * Utilizes custom Linked List data structure to create list of screen instances which can be recalled
 * and rendered via the navigate method.
 */
public class ScreenRouter {

    private LinkedList<Screen> screens = new LinkedList<>();
    private AppUser currentUser;

    public ScreenRouter addScreen(Screen screen) {
        screens.add(screen);
        return this;
    }
    //renders screens when called from screen view classes
    public void navigate(String route) {
        for (int i = 0; i < screens.size(); i++) {
            Screen screen = screens.get(i);
            if (screen.getRoute().equals(route)) {
                screen.render();
            }
        }
    }

    public void setCurrentUser(AppUser user) {
        currentUser = user;
    }

    public AppUser getCurrentUser() {
        return currentUser;
    }
}
