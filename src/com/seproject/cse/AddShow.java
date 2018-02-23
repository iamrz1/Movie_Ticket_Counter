package com.seproject.cse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AddShow {
	private String[] timeSlot = new String[100];
	private String[] timeSlotCode = new String[100];
	
	private String[] movieList = new String[100];
	private int size=0;
	private int slotSize=0;

	public AddShow(){
		try {
			readSlotFromFile();
			readMovieFromFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		runUI();
	}
	private void runUI(){
		JFrame frame=new JFrame();//creating instance of JFrame
		
		JLabel timeSlotLabel = new JLabel ();
		timeSlotLabel.setText("Select Time Slot: ");
		timeSlotLabel.setBounds(150,50,150, 33);//x axis, y axis, width, height
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox timeSlotComboBox = new JComboBox(timeSlot);;
		timeSlotComboBox.setBounds(25,90, 340, 33);
		
		JLabel movieLabel = new JLabel ();
		movieLabel.setText("Select Movie: ");
		movieLabel.setBounds(160,150,150, 33);//x axis, y axis, width, height
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox movieListComboBox = new JComboBox(movieList);;
		movieListComboBox.setBounds(25, 190, 340, 33);
		
		
		//Button and its Action
				JButton addButton=new JButton();//creating instance of JButton  
				addButton.setText("Add Show");
				addButton.setBounds(150,270,100, 33);//x axis, y axis, width, height		
				ActionListener addShowAction= new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent a) {
						@SuppressWarnings("unused")
						String targetSlot = (String) timeSlotComboBox.getSelectedItem();
						String targetMovie = (String) movieListComboBox.getSelectedItem();
						int targetIndex= movieListComboBox.getSelectedIndex();
						int targetSlotIndex= timeSlotComboBox.getSelectedIndex();
						
						
						//JOptionPane.showMessageDialog(null,size);
						
						String targetSlotCode = timeSlotCode[targetSlotIndex];
						if(!targetSlot.toLowerCase().equals("booked")){
							
							String newShow=targetSlotCode+"++"+targetMovie;
							timeSlotCode[targetSlotIndex]=targetSlotCode+"++";
							JOptionPane.showMessageDialog(null, "Show Added.");
							try {
								writeToFile(newShow);
							} catch (Exception e) {
								e.printStackTrace();
							}
							AddShow as =new AddShow ();
							frame.dispose();
						}
						else
							JOptionPane.showMessageDialog(null, "Select valid time slot.");
						
					}
				};
				addButton.addActionListener(addShowAction);

		
		
		
		//finishing up jframe
		frame.add(timeSlotLabel);
		frame.add(timeSlotComboBox);
		frame.add(movieLabel);
		frame.add(movieListComboBox);
		frame.add(addButton);
		frame.setSize(400,500);//400 width and 500 height  
		frame.setLocationRelativeTo(null);//using no layout managers
		frame.setLayout(null);//using no layout managers 
		frame.setVisible(true);//making the frame visible
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void readSlotFromFile() throws IOException{
		//reads from file and stores the data in MovieList array
		
        FileReader timeSlotFile=new FileReader("TimeSlots.txt");   
        Scanner scanTimeSlot = new Scanner (timeSlotFile);
        
        for(int i=0;scanTimeSlot.hasNext();i++){
        	String slot=scanTimeSlot.nextLine();
        	if (slot.equals("t1")){
        		
        		timeSlot[i]="09:00 AM - 11:00 AM";}
        	else if (slot.equals("t2")){
        		
            	timeSlot[i]="12:00 PM - 02:00 PM";}
        	else if (slot.equals("t3")){
        		
            	timeSlot[i]="03:00 PM - 05:00 PM";}
        	else if (slot.equals("t4")){
        		
            	timeSlot[i]="06:00 PM - 08:00 PM";}
        	else if (slot.equals("t5")){
        		
            	timeSlot[i]="09:00 PM - 11:00 PM";}
        	else {
        		timeSlot[i]="Booked";
        	}
        	timeSlotCode[i]=slot;
        	slotSize++;
        	
        }
        scanTimeSlot.close();
        timeSlotFile.close();
        //size is one more than total number of movies
        //for(int j=0;j<size;j++) System.out.println(movieList[j]);
		
	}
	
	private void readMovieFromFile() throws IOException{
		//reads from file and stores the data in MovieList array
		
        FileReader movieFile=new FileReader("Movies.txt");   
        Scanner scan = new Scanner (movieFile);
        
        for(size=0;scan.hasNext();size++){
        	movieList[size]=scan.nextLine();
        	
        }

        scan.close();
        movieFile.close();
        //size is one more than total number of movies
        //for(int j=0;j<size;j++) System.out.println(movieList[j]);
		
	}
	private void writeToFile(String newShow) throws Exception{
		//
		FileWriter writer1 = new FileWriter("Schedule.txt",true); 
		PrintWriter writer = new PrintWriter(writer1); 
		writer.println(newShow);
		writer.flush();  
		writer.close();
		
		String[] sortArray = {"nullValue", "nullValue", "nullValue", "nullValue","nullValue"};
		//sort the movies based on time
		FileReader scFile=new FileReader("Schedule.txt");   
        Scanner scan = new Scanner (scFile);
        
        for(size=0;scan.hasNext();size++){
        	sortArray[size]=scan.nextLine();
        	//JOptionPane.showMessageDialog(null,sortArray[size]);
        }
        scan.close();
        scFile.close();
        Arrays.sort(sortArray);
        
		FileWriter writer21 = new FileWriter("Schedule.txt"); 
		PrintWriter writer2 = new PrintWriter(writer21); 
		for(int i=0;i<sortArray.length;i++){
			if(!sortArray[i].equals("nullValue"))
				writer2.println(sortArray[i]);
		}
		writer2.flush();  
		writer2.close();
		

		FileWriter writerS1 = new FileWriter("TimeSlots.txt"); 
		PrintWriter writerS = new PrintWriter(writerS1);

		
		for (int i=0;i<5;i++)
			writerS.println(timeSlotCode[i]);
		
		
		writerS.flush();  
		writerS.close();

	}
	
}

