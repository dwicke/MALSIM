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
    public TagList(int numTags)
    {
        tags = new Tag[numTags];
        this.numTags = numTags;
        // do 1 to n since don't want 0 to be a tag
        for (int i = 1; i <= numTags; i++)
        {
            tags[i-1] = new Tag(i);
        }
    }
    
    public Tag getFreeTag()
    {
        for (int i = 0; i < numTags; i++)
        {
            
            if (tags[i].useTag() == true)
            {
                return tags[i];
            }
        }
        return null;
    }
}
