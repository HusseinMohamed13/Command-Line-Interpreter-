package task;

import java.util.Scanner;

public class Parser {

	private String[] tempArray;
	private String[] args;
	private String s;
	private String cmd;
	private String cmd2;
	private String cmd3;
	public Parser(String r) {
		s=r;
	}
	public String parse() {
		/*int found3 = 0,index=0;
		for(int x=0;x<s.length();x++) {
			if(s.charAt(x)=='|') {
				found3 = 1;
				index = x;
			}
		}
		*/
		String delimiter = "";
		int found = 0;
		int found1 = 0;
		int found2 = 0;
		for(int x=0;x<s.length();x++) {
			if(s.charAt(x)=='>' && s.charAt(x+1)!='>' && s.charAt(x-1)!='>') {
				found = 1; 
			}
		}
		for(int x=0;x<s.length();x++) {
			if(s.charAt(x)=='>' && s.charAt(x+1)=='>') {
				found1 = 1; 
			}
		}
		for(int x=0;x<s.length();x++) {
			if(s.charAt(x)==':') {
				found2=1;
				}			
		}
		
		/*if(found3==1) {
			//cmd2 = s.substring(0, index-1);
			//cmd3 = s.substring(index+1);
			delimiter = "|";
		}*/
		if(found2==1) {
			tempArray = s.split(" ");
	        args = new String[tempArray.length-1];
	        cmd = tempArray[0];
	        args[0] = tempArray[1];
		}
		else if(found==1) {
			delimiter = ">";
			tempArray = s.split(delimiter);
	        args = new String[tempArray.length-1];
	        cmd = tempArray[0];
	        for(int x=0;x<cmd.length();x++) {
	        	if(cmd.charAt(x)=='/') {
	        		
	        	}
	        }
	        args[0] = tempArray[1];
		}
		else if(found1==1) {
			delimiter = ">>";
			tempArray = s.split(delimiter);
	        args = new String[tempArray.length-1];
	        cmd = tempArray[0];
	        args[0] = tempArray[1];
		}
		else {
		
				delimiter ="/";
				tempArray = s.split(delimiter);
		        args = new String[tempArray.length-1];
		        cmd = tempArray[0]; 
		        for(int i=1;i<tempArray.length;i++) {
		        	args[i-1]=tempArray[i];
		        }
		}  
		return delimiter;
	}
   
	public String getCmd() {
		return cmd;
	}
	/*public String getCmd2() {
		return cmd2;
	}
	public String getCmd3() {
		return cmd3;
	}*/
	public String[] getArguments() {
		return args; 
	}
	public void show() {
		System.out.println(cmd);
		for(int i=0;i<tempArray.length-1;i++) {
			System.out.println(args[i-1]);
		}
	}
	
}