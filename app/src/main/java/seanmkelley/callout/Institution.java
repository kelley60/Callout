package seanmkelley.callout;
import java.util.ArrayList;
/**
 * Created by Austin on 3/3/2015.
 */
public class Institution {
    public String name;
    public ArrayList<Club> clubs;
    public Institution(String n) {
        name = n;
        ArrayList<Club> list = new ArrayList<Club>();
        clubs = list;
    }



}
