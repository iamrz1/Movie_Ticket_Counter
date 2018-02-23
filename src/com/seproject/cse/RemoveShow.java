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

public class RemoveShow {
	private String[] showList = new String [5];
	private String[] scheduleList = new String [5];
	private String[] ticketMain= new String [240];
	private String[] tickets= new String [240];
	private String RemovableTimeSlot="";
	
	private String[] timeSlot = {"09:00 AM - 11:00 AM","12:00 PM - 02:00 PM","03:00 PM - 05:00 PM","06:00 PM - 08:00 PM","09:00 PM - 11:00 PM"};
	private String[] timeSlotCode = new String[100];
	private int[] timeSlotInt= {50,50,50,50,50};
	
	private String[] movieList = new String[100];
	private int size=0;
	private int slotSize=0;
	public RemoveShow() {
		try {
			readScheduleFromFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//runUI();

	}
	
	public void runUI(){
		JFrame frame=new JFrame();//creating instance of JFrame
		JLabel showLabel = new JLabel ();
		showLabel.setText("Select Show: ");
		showLabel.setBounds(160,50,150, 33);//x axis, y axis, width, height
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox showListComboBox = new JComboBox(showList);;
		showListComboBox.setBounds(25, 150, 340, 33);
		
		JButton removeButton=new JButton();//creating instance of JButton  
		removeButton.setText("Remove");
		removeButton.setBounds(150,250,100, 33);//x axis, y axis, width, height		
		
		ActionListener removeAction= new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent a) {
				@SuppressWarnings("unused")
				
				int targetShowIndex= showListComboBox.getSelectedIndex();
				RemovableTimeSlot=scheduleList[targetShowIndex].substring(0,2);
				scheduleList[targetShowIndex]="removed";
				
				try {
					writeToScheduleFile(scheduleList);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				int targetSlot = timeSlotInt[targetShowIndex];
				
				try {
					//JOptionPane.showMessageDialog(null,targetSlot);
					writeToSlotFile(targetSlot);
				} catch (Exception e1) {}
				

				RemoveShow rs =new RemoveShow ();
				removeTicketFromSlot();
				rs.runUI();
				frame.dispose();
				
			} 

		};
		removeButton.addActionListener(removeAction);

		
		
		frame.add(showLabel);
		frame.add(showListComboBox);
		frame.add(removeButton);
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
        	//JOptionPane.showMessageDialog(null,code+" "+ movie);
        	scheduleList[size]=text;
        	timeSlotInt[size]=code;
        	movieList[size]=movie;
        	showList[size]=timeSlot[code-1]+" - "+movie;
        	//JOptionPane.showMessageDialog(null,size+" "+timeSlotCode[size]);
        	
        }

        scan.close();
        scFile.close();
        
		
	}
	
	private void writeToScheduleFile(String[] timeSlotCode) throws Exception{
		//
		
		FileWriter writerS1 = new FileWriter("Schedule.txt"); 
		PrintWriter writerS = new PrintWriter(writerS1);
		for (int i=0;i<size;i++){
			if(timeSlotCode[i].contains("++")){
				writerS.println(timeSlotCode[i]);
			}
				
		} 			//
 
		writerS.flush();  
		writerS.close(); 

	}

	private void writeToSlotFile(int slot) throws Exception{
		
		FileReader scFile=new FileReader("TimeSlots.txt");
		String targetSlot="t"+slot;
		String[] outputList=new String[5];
        Scanner scan = new Scanner (scFile);
        for(size=0;scan.hasNext();size++){
        	String line=scan.nextLine();
        	if(line.contains(targetSlot+"++"))
        		outputList[size]=targetSlot;
        	else
        		outputList[size]=line;
        	
        }
        scan.close();
        scFile.close();
		//
		
		FileWriter writerS1 = new FileWriter("TimeSlots.txt"); 
		PrintWriter writerS = new PrintWriter(writerS1);

		for (int i=0;i<5;i++)
			writerS.println(outputList[i]);
 
		writerS.flush();  
		writerS.close(); 

	}
	
	private void removeTicketFromSlot(){
		String targetFile=RemovableTimeSlot+"Tickets.txt";
		
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
				tickets[i]=ticketMain[i].substring(0, 3);
			else
				tickets[i]=ticketMain[i];
		}
		writeToFile();
		
	}
	
	private void writeToFile(){
		String targetFile=RemovableTimeSlot+"Tickets.txt";
		try {
			FileWriter writer1 = new FileWriter(targetFile);
			PrintWriter writer = new PrintWriter(writer1); 
			for(int i=0;i <size;i++)
			writer.println(tickets[i]);
			writer.println();
			writer.flush();  
			writer.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		} 

	}
	
	
}
