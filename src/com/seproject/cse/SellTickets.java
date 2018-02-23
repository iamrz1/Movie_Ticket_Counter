package com.seproject.cse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class SellTickets {
	private int size;
	String targetFile=".txt";
	private String[] showList = new String [5];
	private String[] tsCode = new String[5];
	private String[] tickets= new String [240];//240 max tickets
	private String[] ticketMain= new String [240];
	private String[] timeSlot = {"09:00 AM - 11:00 AM","12:00 PM - 02:00 PM","03:00 PM - 05:00 PM","06:00 PM - 08:00 PM","09:00 PM - 11:00 PM"};
	

	public SellTickets() {
		try {
			readScheduleFromFile() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		runSelectMovieUI();
		
	}
	
	private void runSelectMovieUI(){
		JFrame frame=new JFrame();//creating instance of JFrame
		JLabel showLabel = new JLabel ();
		showLabel.setText("Select Show: ");
		showLabel.setBounds(160,50,150, 33);//x axis, y axis, width, height
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox showListComboBox = new JComboBox(showList);;
		showListComboBox.setBounds(25, 150, 340, 33);
		
		JButton selectButton=new JButton();//creating instance of JButton  
		selectButton.setText("Select");
		selectButton.setBounds(150,250,100, 33);//x axis, y axis, width, height		
		
		ActionListener selectAction= new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent a) {
				@SuppressWarnings("unused")
				int targetShowIndex= showListComboBox.getSelectedIndex();
				//JOptionPane.showMessageDialog(null,targetFile);
				String targetSlotCode=tsCode[targetShowIndex];
				//scheduleList[targetShowIndex]="removed";
				
				
				switch(targetSlotCode){    
				case "t1":    
					targetFile="t1Tickets.txt";    
				 break;  //optional  
				case "t2":    
					targetFile="t2Tickets.txt";   
				 break;
				case "t3":    
					targetFile="t3Tickets.txt";   
				 break;
				case "t4":    
					targetFile="t4Tickets.txt";   
				 break;
				case "t5":    
					targetFile="t5Tickets.txt";   
				 break;
				}
				frame.dispose();
				//JOptionPane.showMessageDialog(null,targetFile);
				readFromTicketFile(targetFile);
				runSelectTicketUI();
				
				
			} 

		};
		selectButton.addActionListener(selectAction);

		
		
		frame.add(showLabel);
		frame.add(showListComboBox);
		frame.add(selectButton);
		frame.setSize(400,500);//400 width and 500 height  
		frame.setLocationRelativeTo(null);//using no layout managers
		frame.setLayout(null);//using no layout managers 
		frame.setVisible(true);//making the frame visible
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	
	private void runSelectTicketUI(){
		JFrame frame=new JFrame();//creating instance of JFrame
		JLabel showLabel = new JLabel ();
		showLabel.setText("Select Seat: ");
		showLabel.setBounds(160,50,150, 33);//x axis, y axis, width, height
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox showListComboBox = new JComboBox(tickets);
		showListComboBox.setBounds(25, 150, 340, 33);
		
		JButton selectButton=new JButton();//creating instance of JButton  
		selectButton.setText("Select");
		selectButton.setBounds(150,250,100, 33);//x axis, y axis, width, height		
		
		
		ActionListener selectAction= new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent a) {
				@SuppressWarnings("unused")
				int targetIndex= showListComboBox.getSelectedIndex();
				
				String targetTicketReads=(String) showListComboBox.getSelectedItem();
				//JOptionPane.showMessageDialog(null,targetTicketReads);
				if (targetTicketReads.contains("Booked"))
					JOptionPane.showMessageDialog(null,"Ticket is Aleady Booked");
				else{
					ticketMain[targetIndex]=ticketMain[targetIndex]+"++";
					writeToFile();
					frame.dispose();
					JOptionPane.showMessageDialog(null,"Sale Confirmed");
					readFromTicketFile(targetFile);
					runSelectTicketUI();

				}
				
			} 

		};
		selectButton.addActionListener(selectAction);
		
		
		frame.add(showLabel);
		frame.add(showListComboBox);
		frame.add(selectButton);
		frame.setSize(400,500);//400 width and 500 height  
		frame.setLocationRelativeTo(null);//using no layout managers
		frame.setLayout(null);//using no layout managers 
		frame.setVisible(true);//making the frame visible
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
	}
	
	
	private void readScheduleFromFile() throws IOException{
		//reads from file and stores the data in MovieList array
		
        FileReader scFile=new FileReader("Schedule.txt");   
        Scanner scan = new Scanner (scFile);
        
        String text,movie;
        int code;
        
        for(size=0;scan.hasNext();size++){
        	text=scan.nextLine();
        	code=Integer.parseInt(text.substring(1,2));
        	movie=text.substring(4);
        	
			
			tsCode[size]=text.substring(0,2);
        	//JOptionPane.showMessageDialog(null,code+" "+ movie);
        	showList[size]=timeSlot[code-1]+" - "+movie;
        	
        	
        }

        scan.close();
        scFile.close();

        
		
	}
	private void readFromTicketFile(String targetFile){
		
		
		FileReader scFile;
		try {
			scFile = new FileReader(targetFile);
			Scanner scan = new Scanner (scFile);
			for (size=0;scan.hasNext();size++){
				ticketMain[size]=scan.nextLine();
			}
	        scan.close();
	        scFile.close();
		} 
		catch (Exception e) {

		}   
		
		for (int i=0;i<size;i++){
			//System.out.println(ticketMain[i]);
			if (ticketMain[i].contains("++"))
				tickets[i]=ticketMain[i].substring(0, 3).toUpperCase()+" - Booked";
			else
				tickets[i]=ticketMain[i].toUpperCase();
		}
       
	} 
	private void writeToFile(){
	
		try {
			FileWriter writer1 = new FileWriter(targetFile);
			PrintWriter writer = new PrintWriter(writer1); 
			for(int i=0;i <size;i++)
			writer.println(ticketMain[i]);
			writer.println();
			writer.flush();  
			writer.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		} 

	}

}
