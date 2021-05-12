package com.nickmcconnell.p0.util;

import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.screens.Screen;

public class ScreenRouter {

    private LinkedList<Screen> screens = new LinkedList<>();
    private AppUser currentUser;

    public ScreenRouter addScreen(Screen screen) {
        screens.add(screen);
        return this;
    }

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
