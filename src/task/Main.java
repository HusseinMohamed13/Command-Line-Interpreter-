                    //Team Members

//Hussein Mohamed Hamed            20176011
//Mostafa Abdelmotaal Abdelmoaaty  20176029
//ziyad wael mostafa               20176012

package task;

import java.util.Scanner;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
	
   
	
    public static void main(String[] args) throws IOException  
    {   
    	Parser p;
        Terminal t;
        File file=new File("");
        String s ;
        s = file.getAbsolutePath();
    	
		while(true) {
			
		Scanner input = new Scanner(System.in); 
        System.out.print("Enter Command: ");
    	String myString = input.nextLine();   	
    	//////////
    	int check=0;
    	for(int x=0;x<myString.length();x++) {
    		if(myString.charAt(x)=='|') {
    			check=1;
    		}
    	}
    	if(check==1) {
    		String ArrayOfCommands[] = new String[10];
    		int i=0,l=0,r,y;
    		//ArrayOfCommands = myString.split("|");
    		for(y=0;y<myString.length();y++) {
        		if(myString.charAt(y)=='|') {
        			r=y;
        			ArrayOfCommands[i++]=myString.substring(l,r);
        			r++;
        			l=y+1;
        		}
        	}
    		if(i>0) {
    			ArrayOfCommands[i++]=myString.substring(l,y);
    		}
    		
    		for(int x=0;x<i;x++) {
    			p = new Parser(ArrayOfCommands[x]);
    			String delimiter = p.parse();
    			String cmd = p.getCmd();
    			///////////////////special case for more&&cat
    			String split[];
                String cmd1;
                String arg=null;
    			for(int z=0;z<cmd.length();z++) {
    				if(cmd.charAt(z)=='/') {
    					split = cmd.split("/");
    	                cmd = cmd1 = split[0];
    	                arg  = split[1];
    				}
    			}
    			///////////////////
    			String array[] = p.getArguments();
    			t = new Terminal(array);
    	    	
    	        switch(cmd) {
    	    	case "$cd":
    	    		 s = t.cd(s);
    	            break;
    			case "$ls":
    				 t.ls(s,delimiter);
    	            break;
    	        case "$cp":
    	             t.cp();
    	            break;
    	        case "$cat":
    	             t.cat(s,arg,delimiter);
    	            break;
    	        case "$more":
    	        	 t.more(s,arg,delimiter);
    	            break;
    	        case "$mkdir":
    	        	 t.mkdir(s);
    	            break;  
    	        case "$rmdir":
    	        	 t.rm(s);
    	            break;  
    	        case  "$mv":
    	        	 t.mv(s);
    	            break; 
    	        case  "$date":
    	        	 t.date(delimiter);
    	            break;
    	        case  "$pwd":
    	        	 t.pwd(s,delimiter);
    	            break;    
    	        case  "$clear":
    	        	 t.clear();
    	            break;
    	        case  "$help":
   	        	     t.help();
   	                break;
    	        case  "$args":
   	        	     t.args();
   	                break;
    	        case  "$rm":
    	        	 t.rm(s);
    	        	break;
    	        case  "exit":
    	        	System.exit(0);
    	        	break;
    	        default:
    	        	System.out.println("Invalid Command");
    	        	break;
    	      }
    		}
    	}
    	//////////
    	else {
    	p = new Parser(myString);
        
    	String delimiter = p.parse();
    	
        String cmd = p.getCmd();
        
        String split[];
        String cmd1;
        String arg=null;
		for(int z=0;z<cmd.length();z++) {
			if(cmd.charAt(z)=='/') {
				split = cmd.split("/");
                cmd = cmd1 = split[0];
                arg  = split[1];
			}
		}
        //String cmd2 = p.getCmd2();
        //String cmd3 = p.getCmd3();
    	//String r[] = new String[10];
    	/*if(cmd2!=null&&cmd3!=null) {
    	Terminal t1 = new Terminal();
    	
    	switch(cmd2) {
    	case "$ls":
    		System.out.println("123");
    		r=t1.ls(s," ");
    		break;
    	 default:
    		 
    	}
    	switch(cmd3) {
    	case "$more":
    		for(int x=0;x<r.length && r[x]!=null;x++) {
    		  t1.more1(s+'\\'+r[x]);
    		}
    		break;
    	 default:
    	}
        
    	}
    	*/
    	String array[] = p.getArguments();
    	
    	t = new Terminal(array);
    	
        switch(cmd) {
    	case "$cd":
    		 s = t.cd(s);
            break;
		case "$ls":
			 t.ls(s,delimiter);
            break;
        case "$cp":
             t.cp();
            break;
        case "$cat":
             t.cat(s,arg,delimiter);
            break;
        case "$more":
        	 t.more(s,arg,delimiter);
            break;
        case "$mkdir":
        	 t.mkdir(s);
            break;  
        case "$rmdir":
        	 t.rm(s);
            break;  
        case  "$mv":
        	 t.mv(s);
            break; 
        case  "$date":
        	 t.date(delimiter);
            break;
        case  "$pwd":
        	 t.pwd(s,delimiter);
            break;    
        case  "$clear":
        	 t.clear();
            break;
        case  "$rm":
        	 t.rm(s);
        	break;
        case  "$help":
       	     t.help();
       	    break;
        case  "$args":
       	     t.args();
       	    break;
        case  "exit":
        	System.exit(0);
        	break;
        default:
        	System.out.println("Invalid Command");
        	break;
      }
   }//end else
    	}
        }
}