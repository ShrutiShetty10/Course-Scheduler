package com.SEproject.classScheduler;

import java.io.File;

import java.util.Scanner;
import java.util.regex.*;
public class Times 
{
    public static void main(String[] args) throws Exception 
    {
        System.out.println("Enter the file name");
        try
        {
        Scanner terminal = new Scanner(System.in);
        String filename = terminal.next();
        File file = new File(filename);	
        Scanner scanner = new Scanner(file);
        
        
        while (scanner.hasNextLine()) 
        {
	        String line = scanner.nextLine();
            
            if(Pattern.matches("MWF[0-9]",line)||Pattern.matches("MWF[0-9]{2}",line)||Pattern.matches("TT[0-9]{2}:[0-9]{2}",line)||Pattern.matches("TT[0-9][0-9]",line)||Pattern.matches("TT[0-9]:[0-9][0-9]",line))
            {
               continue;
                
            }
            else
            {
                
               
                System.out.println("Some lecture time has wrong format");
                break;
                
            }
           
            
        }

        }
        catch(Exception e)
        {
            System.out.println("File does not exist");
        }
    }
}