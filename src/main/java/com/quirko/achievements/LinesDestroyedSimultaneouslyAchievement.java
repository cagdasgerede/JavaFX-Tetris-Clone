package com.quirko.achievements;
public class LinesDestroyedSimultaneouslyAchievement extends Achievement{
    public LinesDestroyedSimultaneouslyAchievement(int currentState,int goal, boolean completed) {
        super(currentState,goal, completed);
    }
}
/*
    Lines destroyed simultaneously achievement is achieved by destroying a number of lines at the same time.
    Playing more than one game does not matter in this achievement because we cannot achieve this without destroying
    a minimum amount of lines at the same time.
*/