package com.SEproject.classScheduler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;





@SpringBootApplication
public class ClassSchedulerApplication 
{

	public static void main(String[] args) throws Exception {
		
		SpringApplication.run(ClassSchedulerApplication.class, args);
		
		//times();
	}

	 public static void times() throws Exception 
	    {
		 
		 System.out.println("Enter the file name");
	        try
	        {
		        Scanner terminal = new Scanner(System.in);
		        String filename = terminal.next();
		        File file = new File(filename);	
		        Scanner scanner = new Scanner(file);
		        
		        boolean flag=true;
		        int rooms_count=0;
		        while (scanner.hasNextLine()) 
		        {
			        String line = scanner.nextLine();
			        if (line.equalsIgnoreCase("rooms"))
			        {
			        	 line = scanner.nextLine();
			        	 while (!line.equalsIgnoreCase("courses")) 
			        	 {
			                    rooms_count++;
			                    StringTokenizer st = new StringTokenizer(line, " ");
			                    int roomnumber = Integer.parseInt(st.nextToken());
			                    System.out.println(roomnumber);
	
			                    // To check if room number is 3 digit
			                    if (roomnumber <= 99 || roomnumber >= 1000) 
			                    {
			                        System.out.println("Room number is not three digit");
			                        System.exit(0);
			                    }
			                    // to check if there are 20 rooms only
			                    if (rooms_count > 20) 
			                    {
			                        System.out.println("Rooms are more than 20");
			                    }
			                    st.nextToken();
			                    int capacity = Integer.parseInt(st.nextToken());
			                    System.out.println(capacity);
	
			                    // to check if capacity is less than range [10,200]
			                    if (capacity < 10 || capacity > 200) 
			                    {
			                        System.out.println("Room capacity is wrong");
			                        System.exit(0);
			                    }
			                    line = scanner.nextLine();
			        	 }
			        }
			        if(line.equalsIgnoreCase("courses"))
			        {
			        	 line = scanner.nextLine();
			        	while (!line.equalsIgnoreCase("times") )
			        	{
					        line = scanner.nextLine();
							String delims = "[,]+";
					        String[] arr3  = line.split(delims);
							for (String course : arr3)
								{
								    if(Pattern.matches("csc[0-9][0-9][0-9]",course))
								    	System.out.println("course id is "+course);
								    else
							            System.out.println("error");
							
								}
							line = scanner.nextLine();
				        }
			        }
			        
			        if(line.equalsIgnoreCase("times"))
			        {
			        	 line = scanner.nextLine();
			        	while (scanner.hasNextLine())
			        	{
					        line = scanner.nextLine();
							String delims = "[,]+";
					        String[] arr3  = line.split(delims);
							for (String time : arr3)
								{
								 if(Pattern.matches("MWF[0-9]",line)||Pattern.matches("MWF[0-9]{2}",line)||Pattern.matches("TT[0-9]{2}:[0-9]{2}",line)||Pattern.matches("TT[0-9][0-9]",line)||Pattern.matches("TT[0-9]:[0-9][0-9]",line))
						            {
						               
						            	continue;
						                
						            }
						            else
						            {
						                
						               
						                System.out.println("Some lecture time has wrong format");
						                flag=false;
						                System.exit(0);
						                
						            }
							
								}
							
				        }
			        }
			        
		            
		           
		            
		           
		            
		        }
		        if(flag)
		        	System.out.println("Go to localhost 8080 to check out ur class schedule");
	            
	
		        }
		      catch(Exception e)
		      {
		    	  System.out.println("File does not exist");
		      }
		    }

}
