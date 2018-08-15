package com.curtisgetz.jokebook;


import java.util.ArrayList;
import java.util.List;


public class JokeBook {


    private List<String> mJokes;


    public JokeBook() {
        this.mJokes = new ArrayList<>();
    }




    public String getJoke(){
        initializeJokeList();
        int numberOfJokes = mJokes.size();
        //select a random joke from list
        int rand = (int) (Math.random() * numberOfJokes);
        //Return the joke
        return mJokes.get(rand);

    }


    private void initializeJokeList(){
        mJokes = new ArrayList<>();
        mJokes.add("What do lawyers wear to court?\n\nLawsuits!");
        mJokes.add("Why shouldn't you write with a broken pencil? \n\nBecause it's pointless.");
        mJokes.add("Why is there a gate around cemeteries? \n\nBecause people are dying to get in.");
        mJokes.add("Why wouldn't the shrimp share his treasure? \n\nBecause he was a little shellfish.");
        mJokes.add("When I die I want my body donated to science.\n\n But more specifically, a scientist " +
                "who is working on bringing dead guys back to life");
        mJokes.add("It's kind of a weird feeling watching the Flintstones lately, with their stone age " +
                "drive in movies, and their caveman bowling.\n\nIt just seems so dated.");
        mJokes.add("I had an uncle, mean guy.\nHe was a prize fighter. He once broke both his hands in a " +
                "fight against a washer/dryer that he got on The Price Is Right.");
        mJokes.add("I signed up for my company's 401k.\n\nBut I don't think I can run that far.");
        mJokes.add("I wouldn't call myself a fan of steampunk.\n\nBut I will say it's the healthiest way to prepare punk.");
        mJokes.add("To stop a pitbull attacking his daughter a texas man bit the dog.\n\nThat's an interesting story, but it's not what I would call news.");
        mJokes.add("A new study found that men with beards are more attractive.\n\nMore great work from the university of Bob Seger.");
        mJokes.add("They say curiosity killed the cat.\n\nBut I don't think we should rule out that shifty looking kid next door.");
        mJokes.add("I ordered a chicken and an egg from Amazon.\n\nI'll let you know.");

    }



}
