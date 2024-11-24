/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.tut.utils;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

import za.tut.models.Announcement;

/**
 *
 * @author fredre
 */
public class AnnounceStore {
    
    //Would it not be better to use a file object?
    private List<Announcement> announcements;
    
    private String filename ="astore.txt"; //Been better to use the File Object
    
    public AnnounceStore()
    {
        announcements = new ArrayList<>();  //Why first a List then ArrayList !
        loadAnnouncments();
    }
    
    public void addAnnouncement(Announcement announcement)
    {
        announcements.add(announcement);
    }
    
    public void loadAnnouncments(){
        
        //What if there is no announcment file to load?
        
        try(BufferedReader br=new BufferedReader(new FileReader(filename))){
            String line;
            
            while( (line=br.readLine())!=null )
            {
                String[] parts = line.split("\\|");
                
                Announcement tempAnnounce = new Announcement(parts[0],parts[1] ,parts[2]);
                
                addAnnouncement(tempAnnounce);
                
            }
        }
        //What is the file is not writable
         catch(IOException e)
        {
            //Fail without saying anything
            System.err.print("No announcment file found ! Could not load announcments from file!");
            
            //If the announcment file is not found Allow the user to select one from disk using jFileChooser and try again.
        }
        
    }
    
    public void saveAnnouncement()
    {
        try(PrintWriter pw = new PrintWriter(new FileWriter(filename))){
            
            //Some check if the file is valid?
            //How do we override instead of appending?
            
            for(Announcement announcement : announcements)
            {
                pw.println(announcement.toString());
            }
         
        }
        catch(IOException e)
        {
            //Fail without saying anything
            System.err.print("This should display in red ?");
        }
    }
    
            
            
    
}
