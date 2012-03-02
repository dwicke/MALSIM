/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

/**
 *
 * @author drew
 */
public class TagList {
    
    // So I need a list of tags
    Tag[] tags;
    int numTags;
    int start, end;
    public TagList(int startTag, int endTag)
    {
        System.out.println("The number of tags is " + numTags );
        numTags = endTag - startTag ;// since origanally included tag 0
        tags = new Tag[numTags];
        
       // this.numTags = numTags - 1;
        start = startTag;
        end = endTag;
        // do 1 to n since don't want 0 to be a tag
        for (int i = 0; i < numTags; i++)
        {
            tags[i] = new Tag(startTag++);
        }
    }
    
    public Tag getFreeTag()
    {
        for (int i = 0; i < numTags; i++)
        {
            System.out.println("I am getting a tag" + tags[i] + "  " + tags[i].getTag() + "  numTags " + numTags + " start " + start + " end " + end);
            if (tags[i].useTag() == true)
            {
                return tags[i];
            }
        }
        return null;
    }
}
