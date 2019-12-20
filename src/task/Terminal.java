package task;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Terminal {

	private String []args1;
	
	public Terminal(String []args1) {
		this.args1 = new String[args1.length];
		for(int x=0;x<args1.length;x++) {
		this.args1[x] = args1[x];
		}
	}
	public Terminal() {
	
	}
	public String cd(String s) {
		if(args1.length==0) {
			File file=new File("");
	        s = file.getAbsolutePath();
			System.out.println(s);
    		return s;//discussion
    	}
		else {
    		if(args1[0].charAt(1)==':') {
    			s = args1[0];
    		}else {
    		for(int i=0;i<this.args1.length;i++) {
     	    s += '\\' + this.args1[i] ;
    	    }
    		}
    		System.out.println(s);
    	}
		return s;
    	} 
	public String[] ls(String s,String delimiter) throws IOException {
		
		String a[] ;
		a = new String[10];
		int x=0;
		
		File file=new File(s);
	        File[] filesList = file.listFiles();
	        if(delimiter==">") {
	        FileWriter fw = new FileWriter(args1[0], false);
	        for(File f : filesList){
	            if(f.isFile()){
	                System.out.println(f.getName());
	        	    	a[x++]=f.getName();
	                    fw.write(f.getName()+"\r\n"); 
	                    fw.flush();	
	            }
	        }
	        fw.close();
	        }
	        else if(delimiter==">>") {
	        FileWriter fw = new FileWriter(args1[0], true);//append
	        for(File f : filesList){
	            if(f.isFile()){
	                System.out.println(f.getName());
	                    a[x++]=f.getName();
	        	    	fw.write(f.getName()+"\r\n"); 
	                    fw.flush();	
	             }
	        }
	        fw.close();
	        }
	        else {
	        	for(File f : filesList){
		            if(f.isFile()){
		            	String t=f.getName();
		            	/*a[x] = t;
		            	x++;*/
		            	System.out.println(f.getName());
		                }
		            }
	        }
	        return a;
	}
	public void mkdir(String s) {
		if(args1.length<1) {
			System.out.println("Too few arguments");
		    return;
		}
		for(int x=0;x<args1.length;x++) {
			s+='\\'+args1[x];
		}
		File f = new File(s);
		f.mkdir();
	}
	public void cp() {
		if(args1.length<2) {
			System.out.println("Too few arguments");
		    return;
		}
		try {
            FileReader fr = new FileReader(this.args1[0]);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(this.args1[1], true);
            String s1;
 
            while ((s1 = br.readLine()) != null) { 
                fw.write(s1); 
                fw.flush();
            }
            br.close();
            fw.close();
                        System.out.println("file copied");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	public void date(String delimiter) throws IOException {
	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
 	   LocalDateTime now = LocalDateTime.now();
 	   System.out.println(dtf.format(now));
 	   if(delimiter==">") {
 		  FileWriter fw = new FileWriter(args1[0], false);
      	    	fw.write(dtf.format(now)+"\r\n"); 
                  fw.flush();	
           }
 	   else if(delimiter==">>") {
 		  FileWriter fw = new FileWriter(args1[0], true);//append
      	    	fw.write(dtf.format(now)+"\r\n"); 
                  fw.flush();	
          }
      }  
	public void rm(String s) {
		if(args1.length<1) {
			System.out.println("Too few arguments");
		    return;
		}
		File file = new File(s+"\\"+this.args1[0]);
		file.delete();
	}
	public void cat(String s,String arg,String delimiter) throws IOException {
		if(args1.length<1) {
			System.out.println("Too few arguments");
		    return;
		}
		if(delimiter==">") {
			FileWriter fw = new FileWriter(args1[0], false);
			BufferedReader in = new BufferedReader(new FileReader(s+'\\'+arg));
			String line;
			while((line = in.readLine()) != null)
			{
			    System.out.println(line);
			    fw.write(line+"\r\n"); 
                fw.flush();
			}
			in.close();	
	        fw.close();	
	        }
	    else if(delimiter==">>") {
	        FileWriter fw = new FileWriter(args1[0], true);//append
			BufferedReader in = new BufferedReader(new FileReader(s+'\\'+arg));
			String line;
			while((line = in.readLine()) != null)
			{
			    System.out.println(line);
			    fw.write(line+"\r\n"); 
                fw.flush();
			}
			in.close();	
	        fw.close();	
	        }
		 //File file = new File(s+'\\'+args1[0]);
	     //file.createNewFile();
	    else {
		BufferedReader in = new BufferedReader(new FileReader(s+'\\'+args1[0]));
		String line;
		while((line = in.readLine()) != null)
		{
		    System.out.println(line);
		}
		in.close();	
	    }
	}
	public void mv(String s) throws IOException {
		if(args1.length<4) {
			System.out.println("Too few arguments");
		    return;
		}
		Files.move 
		    (Paths.get(s+'\\'+args1[0]+'\\'+args1[1]),Paths.get(s+'\\'+args1[2]+'\\'+args1[3]));
	}	
	public void more(String s,String arg,String delimiter) throws IOException {
		if(args1.length<1) {
			System.out.println("Too few arguments");
		    return;
		}
		if(delimiter==">") {
			FileWriter fw = new FileWriter(args1[0], false);
			BufferedReader in = new BufferedReader(new FileReader(s+'\\'+arg));
			String line;
			int count=0;
			while((line = in.readLine()) != null)
			{
			    System.out.println(line);
			    fw.write(line+"\r\n"); 
                fw.flush();
			    count++;
			    if(count%5==0) {
			       System.out.print("Press Enter to continue... ");
			       System.in.read();
			    }
			    
		    }
			in.close();
			fw.close();
	        }
	    else if(delimiter==">>") {
	        FileWriter fw = new FileWriter(args1[0], true);//append
	        BufferedReader in = new BufferedReader(new FileReader(s+'\\'+arg));
			String line;
			int count=0;
			while((line = in.readLine()) != null)
			{
			    System.out.println(line);
			    fw.write(line+"\r\n"); 
                fw.flush();
			    count++;
			    if(count%5==0) {
			       System.out.print("Press Enter to continue... ");
			       System.in.read();
			    }
			    
		    }
			in.close();	
			fw.close();
	    }
	    else {
		BufferedReader in = new BufferedReader(new FileReader(s+'\\'+args1[0]));
		String line;
		int count=0;
		while((line = in.readLine()) != null)
		{
		    System.out.println(line);
		    count++;
		    if(count%5==0) {
		       System.out.println("Press Enter to continue... ");
		       System.in.read();
		    }
		    
		}
		in.close();	
	    }
}
	/*public void more1(String s) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(s));
		String line;
		while((line = in.readLine()) != null)
		{
		    System.out.println(line);
		}
		in.close();	
	}*/
	public void pwd(String s,String delimiter) throws IOException {
		
		System.out.println(s);
	        if(delimiter==">") {
	        FileWriter fw = new FileWriter(args1[0], false);
	                    fw.write(s+"\r\n"); 
	                    fw.flush();
	                    fw.close();	
	        }
	        else if(delimiter==">>") {
	        FileWriter fw = new FileWriter(args1[0], true);//append
	        	    	fw.write(s+"\r\n"); 
	                    fw.flush();	
	                    fw.close();
	        }
	} 
	public void clear() {
		for(int x=0;x<100;x++) {
			System.out.println();
		}
	}
	public void help() {

	 System.out.println("cp: If the last argument names an existing directory,cp copies each other given file into a file with thesame name in that directory. Otherwise, if onlytwo files are given, it copies the first onto thesecond.");
     System.out.println("clear:Ex: $clearThis command can be called to clear the currentterminal screen and it can be redirected to clearthe screen of some other terminal.");
	 System.out.println("cd:$cd /home This command changes the current directory toanother one.");
	 System.out.println("ls:$ls  $ls –a ; to display all information about the listed\r\n" + 
	 		"files\r\n" + 
	 		"$man ls ; to display the manual for ls to know\r\n" + 
	 		"another options.");
	System.out.println("pwd:$pwd Display current user directory."); 
	System.out.println("Mv:If the last argument names an existing directory,mv moves each other given file into a file with the\r\n" + 
			"same name in that directory. Otherwise, if only\r\n" + 
			"two files are given, it moves the first onto the\r\n" + 
			"second."); 
    System.out.println("Rm:"+"rm :$rm *.txt ; to delete a specific file $rm –r /home/mydir ; to delete the directory and recursively delete its content\r\n" + 
    		"rm removes each specified file.");
	System.out.println("Mkdir:$mkdir /home/mydir mkdir creates a directory with each given name.\r\n" + 
			"By default, the mode of created directories is\r\n" + 
			"0777 minus the bits set in the umask.");
	System.out.println("rmdir: $rmdir /home/mydir rmdir removes each given empty directory. If any\r\n" + 
			"nonoption argument does not refer to an existing\r\n" + 
			"empty directory, it is an error.");
	System.out.println("date : $date 1220141414.55\r\n" + 
			"To display or to set the date and time of the\r\n" + 
			"system. The format for setting date is\r\n" + 
			"[MMDDhhmm[[CC]YY][.ss]]");

	}
	public void args() {
	    
		System.out.println("cp: takes 2 args arg1:sourcePath , arg2:destinationPath");
		System.out.println("clear:takes no argumemts");
		System.out.println("cd:takes one argument");
		System.out.println("ls:takes no arguments");
		System.out.println("Mv: takes 2 args arg1:sourcePath , arg2:destinationPath");
		System.out.println("Rm: takes one argument");
		System.out.println("Mkdir: takes one argument");
		System.out.println("rmdir: takes one argument");
		System.out.println("date : takes no argument or takes one argument");
		System.out.println("more:takes one argument");
		System.out.println("cat:takes one arguments");
		System.out.println("pwd:takes no arguments or takes -a"); 
		
	}
}