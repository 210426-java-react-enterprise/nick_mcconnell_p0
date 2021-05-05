package com.nickmcconnell.p0.screens;

//Abstract because we don't want to ever make just a screen
public abstract class Screen {
    //fields are not implicitly public static or final in class


    //not implicitly public or abstract
    //then it doesn't need to have a body

    //the do have a constructor because of call to the super()
    //  (super or parent class constructor)

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
