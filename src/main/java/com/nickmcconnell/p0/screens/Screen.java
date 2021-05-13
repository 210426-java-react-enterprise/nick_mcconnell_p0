package com.nickmcconnell.p0.screens;

public abstract class Screen {

    protected String name;
    protected String route;

    public Screen(String name, String route){
        this.name = name;
        this.route = route;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public abstract void render();

}
