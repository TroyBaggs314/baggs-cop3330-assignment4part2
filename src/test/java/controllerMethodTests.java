/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Troy Baggs
 */

import org.junit.jupiter.api.Test;

import java.io.File;

public class controllerMethodTests
{
    @Test
    public void testAddList()
    {
        //num = current number of accordions
        //call addListClicked()
        //assert.equals(num+1,highest indexed accordion);
    }

    @Test
    public void testRemoveList()
    {
        //num = current number of accordions
        //int temp = (int)Math.random()*10; //random index to remove
        //call removeListClicked(at index of temp)
        //assert.equals(temp-1,highest indexed accordion)
    }

    @Test
    public void testEditListTitle()
    {
        //retrieve title of any accordion
        //assertEquals("Go to store", editListTitle("Go to store");
    }

    @Test
    public void testAddToList()
    {
        //num = current number of index of an accordions
        //call addToListClicked()
        //assert.equals(num+1,accordion.getPanels().size()
    }

    @Test
    public void testRemoveFromList()
    {
        //num = current number of indexes in accordion
        //int temp = (int)Math.random()*10; //random number to remove
        //call removeFromListClicked(at index of temp)
        //assert.equals(num-1,accordion.getPanels().size())
    }

    @Test
    public void testEditDescOfItem()
    {
        //int temp = (int)Math.random()*10; //random number
        //call editDescOfItemClicked(index of temp, "This is the new description")
        //assert.equals("This is the new description",itemFormat[temp].desc)
    }

    @Test
    public void testEditDateOfItem()
    {
        //int temp = (int)Math.random()*10; //random number
        //call editDescOfItemClicked(index of temp, "This is the new description")
        //assert.equals("20010507",itemFormat[temp].date)
    }

    @Test
    public void testMarkComplete()
    {
        //int temp = (int)Math.random()*10; //random number
        //call markCompleteClicked(temp)
        //assert.equals(true,itemFormat[i].complete)
    }


    @Test
    public void testImportList()
    {
        //create 3 new test files to import
        //call importFileClicked() single file
        //import first test file
        //check index of accordion increased
        //assert.equals("2001-05-07",accordion.getPanels().get(accordion.getPanels().size()))
        //import second and third test file
        //check index of accordion increased by 2
        //assert.equals("2021-06-30",accordion.getPanels().get(accordion.getPanels().size()))
    }

    @Test
    public void testAllListVisible()
    {
        //increment through all entries of an accordion
        //if entry.disabled = true
            //then assert.fail()
    }

    @Test
    public void testCompleteListVisible()
    {
        //increment through all entries of an accordion
        //if entry class.complete = false && entry.disabled = false
            //then assert.fail()
    }

    @Test
    public void testIncompleteListVisible()
    {
        //increment through all entries of an accordion
        //if entry class.complete = true && entry.disabled = false
            //then assert.fail()
    }

    @Test
    public void testParse()
    {
        //File file = tf; //test file for testing
        //ArrayList<itemFormat> iF = parse(file);
        //assertEquals(20010507, iF.get(0).date);
    }


}
