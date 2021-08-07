package com.SEproject.classScheduler;

import java.util.ArrayList;


import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


//Interface for Scheduler Class 
interface SchedulerInterface
{
	public String getCourse();
	public void setCourse(String course);
	public String getTime();
	public void setTime(String time);
	public int getRoom_no();
	public void setRoom_no(int roomno);
	public int getCapacity();
	public void setCapacity(int capacity);
	
}

//Interface for Input2 class
interface Input2Interface
{
	public boolean isAssigned();
	public void setAssigned(boolean isAssigned);
	public ArrayList<String> getPerferences();
	public void setPerferences(ArrayList<String> perferences);
}

//Interface for Course class
interface CourseInterface
{
	public String getCourse_name();
	public void setCourse_name(String course_name);
	public boolean isAllotment();
	public void setAllotment(boolean allotment);
}
//Class Scheduler which contains time,room number,course id and capacity scheduled classes 
class Scheduler implements SchedulerInterface
{
	String Time;
	int room_no;
	String course;
	int capacity;
	
	
	Scheduler(String Time,int room_no,int capacity)
	{
		this.Time=Time;
		this.room_no=room_no;
		this.capacity=capacity;
		this.course=" ";
		
	}
	Scheduler(String Time,int room_no,String course,int capacity)
	{
		this.Time=Time;
		this.room_no=room_no;
		this.course=course;
		this.capacity=capacity;
		
	}
	//Getters and setters of Scheduler class
	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public int getRoom_no() {
		return room_no;
	}
	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
}
//Class Input2 which contains courseID,number of enrolled students and perferences received from input file 2
class Input2 implements Input2Interface
{
	String course_id;
    int enrollment;
    ArrayList<String> perferences=new ArrayList<String>();
    boolean isAssigned;
    Input2(String course_id,int enrollment,ArrayList<String> perferences)
    {
    	this.course_id=course_id;
    	this.enrollment=enrollment;
    	this.perferences=perferences;
    	
    	this.isAssigned=false;
    }
    
	public boolean isAssigned() {
		return isAssigned;
	}

	public void setAssigned(boolean isAssigned) {
		this.isAssigned = isAssigned;
	}

	public ArrayList<String> getPerferences() {
		return perferences;
	}
	public void setPerferences(ArrayList<String> perferences) {
		this.perferences = perferences;
	}
    
    
}
//Class Course contains courseID and checks if the courses are scheduled or not using allotment 
class Course implements CourseInterface
{
	String course_name;
	boolean allotment;
	Course(String course_name)
	{
		this.course_name=course_name;
		this.allotment=false;
	}
	
	Course(String course_name,boolean allotment)
	{
		this.course_name=course_name;
		this.allotment=allotment;
	}
	
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public boolean isAllotment() {
		return allotment;
	}
	public void setAllotment(boolean allotment) {
		this.allotment = allotment;
	}
	
}

class Timings
{
	String time;
	boolean occupied;
	Timings(String time)
	{
		this.time=time;
		this.occupied=false;
	}
	Timings(String time,boolean occupied)
	{
		this.time=time;
		this.occupied=occupied;
	}
}
class Rooms
{
	int roomno;
	int capacity;
	Rooms(int roomno,int capacity)
	{
		this.roomno=roomno;
		this.capacity=capacity;
	}
	
}
@Controller
public class HomeController {
	@GetMapping("/")
	public String home(Model model) throws FileNotFoundException
	{
		
		  
	       File file = new File("input1.txt");	
	       Scanner scanner = new Scanner(file);
	       
		ArrayList<Timings>  TimingList= new ArrayList<Timings>();
		ArrayList<Rooms>  rooms= new ArrayList<Rooms>();
		ArrayList<Course> All_Courses=new ArrayList<Course>();
		ArrayList<Scheduler> Schedule=new ArrayList<Scheduler>(); 
        boolean flag=true;
        int rooms_count=0;
        //int j=0;
        while (scanner.hasNextLine()) 
        {
	        String line = scanner.nextLine();
	        if (line.equalsIgnoreCase("rooms"))
	        {
	        	 line = scanner.nextLine();
	        	 while (!line.equalsIgnoreCase("courses")) 
	        	 {
	                    rooms_count++;
	                    String delims = ":";
				        String[] arr3  = line.split(delims);
				        String roomnumber=arr3[0];
				        String capacity=arr3[1];
						if(capacity.charAt(capacity.length()-1)==';')
							capacity=capacity.substring(0,capacity.length()-1);
							
							
							
							System.out.println(roomnumber);
							
							 // To check if room number is 3 digit
		                   if (Integer.parseInt(roomnumber) <= 99 || Integer.parseInt(roomnumber)>= 1000) 
		                    {
		                        System.out.println("Room number is not three digit");
		                        System.exit(0);
		                    }
		                    // to check if there are 20 rooms only
		                    if (rooms_count > 20) 
		                    {
		                        System.out.println("Rooms are more than 20");
		                        break;
		                    }
		                    
		                    
		                    
		                    System.out.println(capacity);

		                    // to check if capacity is less than range [10,200]
		                    if (Integer.parseInt(capacity) < 10 || Integer.parseInt(capacity) > 200) 
		                    {
		                        System.out.println("Room capacity is wrong");
		                        System.exit(0);
		                    }
		                    Rooms r=new Rooms(Integer.parseInt(roomnumber),Integer.parseInt(capacity));
		                    rooms.add(r);
		                    line = scanner.nextLine();    
						}
							

	                   
	                    
	        	 
	        }
	        if(line.equalsIgnoreCase("courses"))
	        {
	        	 line = scanner.nextLine();
	        	while (!line.equalsIgnoreCase("times") )
	        	{
			       
					String delims = ",";
			        String[] arr3  = line.split(delims);
					for (String course : arr3)
						{
						if(course.charAt(course.length()-1)==';')
							course=course.substring(0,course.length()-1);   
						if(Pattern.matches("cs[0-9][0-9][0-9]",course))
						{ 	
							Course c=new Course(course);
							All_Courses.add(c);
							System.out.println("course id is "+course);
						}
						    else
					            {
						    	System.out.println("The course number has wrong format");
						    	System.exit(0);
					            }
								
					
						}
					line = scanner.nextLine();
		        }
	        }
	        
	        if(line.equalsIgnoreCase("times"))
	        {
	        	 line = scanner.nextLine();
	        	 System.out.print(line);
	        	
					String delims = ",";
			        String[] arr3  = line.split(delims);
			        
					for (String time : arr3)
						{
						if(time.charAt(time.length()-1)==';')
							time=time.substring(0,time.length()-1);  
						System.out.println(time);
						 if(Pattern.matches("MWF[0-9]",time)||Pattern.matches("MWF[0-9][0-9]",time)||Pattern.matches("TT[0-9]{2}:[0-9]{2}",time)||Pattern.matches("TT[0-9][0-9]",time)||Pattern.matches("TT[0-9]:[0-9][0-9]",time)||Pattern.matches("TT[0-9]",time))
				            {
				              Timings ts=new Timings(time,false); 
				              TimingList.add(ts);	
				             
				                
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
        if(flag)
        	System.out.println("Go to localhost 8080 to check out ur class schedule");
        
       
        
        File file2 = new File("input2.txt");
        scanner=new Scanner(file2);
    
        ArrayList<Input2> items=new ArrayList<Input2>();
        while(scanner.hasNextLine())
        {
        	String delimiters=",";
        	String line = scanner.nextLine();
        	
        	line=scanner.next();
        	
        	String courseid=line;
        	//courseid.add(line);
        	line=scanner.next();
        	
        	int enrollment=Integer.parseInt(line);
        	line=scanner.next();
        	String arr[]=line.split(delimiters);
        	ArrayList<String> perferences=new ArrayList<String>();
        	for(String t:arr)
        	{
        		if(t.charAt(t.length()-1)==';')
					t=t.substring(0,t.length()-1); 
        		
        		perferences.add(t);
        	}
        	Input2 i=new Input2(courseid,enrollment,perferences);
        	items.add(i);
        
        	for(int h=0;h<items.size();h++)
        	{
        		ArrayList<String> ran=items.get(h).perferences;
        		for(int f=0;f<ran.size();f++)
        			System.out.print(ran.get(f)+" ");
        		System.out.print("\n");
        	}
        	
        	
        }
        
        
        
        //Scheduling Logic
        System.out.println(TimingList.size());
        for(int i=0;i<rooms.size();i++)
        {
        	for(int j=0;j<TimingList.size();j++)
        	{
        		String s_time=TimingList.get(j).time;
        		int s_roomno=rooms.get(i).roomno;
        		int cap=rooms.get(i).capacity;
        		Scheduler sd=new Scheduler(s_time,s_roomno,cap);
        		Schedule.add(sd);
        		
        	}
        }
        System.out.println(Schedule.size());
    
        String cid="";
        for(int i=0;i<Schedule.size();i++)
        {
        	for(int j=0;j<items.size();j++)
        	{
        		
        		if(Schedule.get(i).capacity>=items.get(j).enrollment)
        		{
        		if(items.get(j).perferences.contains(Schedule.get(i).Time) && !items.get(j).isAssigned())
        			{cid=items.get(j).course_id;
        			Scheduler sd=new Scheduler(Schedule.get(i).Time,Schedule.get(i).room_no,cid,Schedule.get(i).capacity);
        			Schedule.set(i, sd);
        			items.get(j).setAssigned(true);
        			System.out.println(Schedule.get(i).Time+" "+Schedule.get(i).room_no+" "+Schedule.get(i).course);
        			}
        		}
        	}
        }
        System.out.println("Before:"+All_Courses.size());
        /*for(int k=0;k<items.size();k++)
        {
        	Course c=new Course(items.get(k).course_id);
        	
        	if(All_Courses.contains(c) && items.get(k).isAssigned)
        	{	All_Courses.remove(c);
        		System.out.print("Hello");
        	}
        }
        */
        for(int k=0;k<All_Courses.size();k++)
        {
        	for(int l=0;l<items.size();l++)
        	{
        		String c_it=items.get(l).course_id;
        		String c_aC=All_Courses.get(k).course_name;
        		if(c_it.equals(c_aC) && items.get(l).isAssigned)
        			All_Courses.get(k).setAllotment(true);
        			
        	}
        }
        
        
       
        
        		 for(int i=0;i<Schedule.size();i++)
        	        {
        	        
        			 for(int j=0;j<All_Courses.size();j++)
        	        	{ 
        	        if(Schedule.get(i).course==" " && !All_Courses.get(j).allotment)
        	        {
        	        	Schedule.get(i).setCourse(All_Courses.get(j).course_name);
        	        	All_Courses.get(j).setAllotment(true);
        	        }
        	        	
        	        }
        	}
        
        	
            for(int i=0;i<Schedule.size();i++)
            {
            	System.out.println(Schedule.get(i).Time+" "+Schedule.get(i).room_no+" "+Schedule.get(i).course);
            }
        System.out.println("After:"+All_Courses.size());
        model.addAttribute("list",Schedule);

        return "home";
	}
	
	
	
}
