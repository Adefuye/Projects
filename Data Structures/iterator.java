import java.util.*;
import java.util.LinkedList;

public class iterator
{
    public static void main(String[] args)
    {
        LinkedList<String> colors = new LinkedList<>();
        colors.add("blue");
        colors.add("red");
        colors.add("yellow");
        colors.add("black");
        //add element,s to colors (red, gree, blue,yel,,blak white)

        Iterator<String> it = colors.iterator();
        int index = 0;
        //create an iterator for list of colours
        
        while(it.hasNext())
        {
            String current = it.next();
            if(current.equals("red"))
            {
               it.remove();    
            }
            else
                System.out.println(current);   
        }

        
        //iterate through the list using iterator and print the list
        //itterate through the list using (for each) and print the list
      /*  index = 0;
        for(String color : colors)
        {
            if(color.equals("blue"))
               colors.remove(index);
            System.out.println(color);
            index ++;
        }*/
    }
}
