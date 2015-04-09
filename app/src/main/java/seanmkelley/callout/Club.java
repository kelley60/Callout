package seanmkelley.callout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Austin on 2/26/2015.
 */
public class Club {

    public String name;
    public int members; //maybe not needed
    public ArrayList<String> cat; //categories
    public String bio; //bio/description of the club
    public Club(String n, String b, ArrayList<String> categories)
    {
        name = n;
        //members = m;
        cat = categories;
        bio = b;
    }

    public String getName()
    {
        return name;
    }
    public String getBio()
    {
        return bio;

    }
    private void setBio(String b){
        this.bio = b;
    }

    private void setName(String n){
        this.name = n;
    }

    public ArrayList getCat()
    {
        return cat;
    }
    public void addCat(String newCat)
    {
        //add a new category to this club
        //to do: look into using addAll(), figure out how collections work
        //this way it could add multiple categories at once
        cat.add(newCat);
    }
    public void removeCat(String badCat)
    {
        //remove badCat from the club's categories
        //to do: look into using removeAll(), as above figure out collections
        cat.remove(badCat);
    }
    public void changeName(String newName)
    {
        //change the club's name
        name = newName;
    }
    /*public void changeMembers(int numMembers)
    {
        //change number of members. maybe not needed
        members = numMembers;
    }
    public void addMem()
    {
        //add just one member. maybe not needed
        members++;
    }*/
    public void editBio(String newBio)
    {
        //edit he bio of the club. Maybe find a way to open a textbox to edit the previous bio
        //instead of just assigning a new string to it
        bio = newBio;
    }


}
