/*
 * GlybokyjSon.java
 * 
 * Copyright 2025 Ivo Sives ( ivo.sives@gmail.com )
 * 
 * Not for evil purposes.
 * Ne dlia zlyh cilej.
 * 
 */
 
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.Console;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets; 
import java.util.Scanner;
import java.util.*;
import java.util.Random;

public class GlybokyjSon {
	
	static Random rand=new Random();
	
	static int command=0, hlybyna_snu=rand.nextInt(Integer.MAX_VALUE);
	static long hodyny=0, ho=0, hwylyny=0, hw=0, chas=0;
	static double skik_raziw=0;
	static String zaraz="", hlybyna_snu_s="";
	static String[] hlybyna_snu_m=new String[20];
	static String[] hlybyna_snu2_m=new String[20];
	static boolean na_powerhni=false;
	
    public static void main(String[] args) {
		
		for(int i=0; i<hlybyna_snu_m.length; i++){hlybyna_snu_m[i]=""+-1; hlybyna_snu2_m[i]=""+-1;}

		if(hlybyna_snu>9999){hlybyna_snu_s=""+hlybyna_snu;
			
			for(int i=0; i<hlybyna_snu_m.length; i++){if(hlybyna_snu_s.length()>=(i+1)){hlybyna_snu_m[i]=hlybyna_snu_s.substring(i,i+1);} }
				
			skik_raziw=(double)hlybyna_snu_s.length()/3;
			
			int z=hlybyna_snu_s.length()-1, y=-1;
			
			for(int a=0; a<hlybyna_snu_s.length(); a++){if(z-a>=0){hlybyna_snu2_m[a]=hlybyna_snu_m[z-a];}
				y++;
				
				if(y==3&&skik_raziw>=1){hlybyna_snu2_m[a]=hlybyna_snu2_m[a]+","; y=0; skik_raziw-=1;} }
			
			for(int x=0; x<hlybyna_snu_s.length(); x++){hlybyna_snu_m[x]=hlybyna_snu2_m[(hlybyna_snu_s.length()-1)-x];}			
			 }
		else{na_powerhni=true;}
		
        System.out.println("GlybokyjSon v1.0 (VI.2025), awtor Ivo Sives ( ivo.sives@gmail.com ).\nPrawylo korystuwannia: ne dlia zlyh cilej.\n\nIchthus\n\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
        
        if(na_powerhni){na_powerhni=false; System.out.println("...hlybyna snu: "+hlybyna_snu+" km");}
        else{
		System.out.print("...hlybyna snu: ");
		for(int p=0; p<hlybyna_snu_s.length(); p++){System.out.print(hlybyna_snu_m[p]);}
        System.out.print(" km\n");}
        
		Scanner scanner=new Scanner(System.in, "UTF-8");    
        boolean running=true;
        
        while (running) {
				System.out.print("\nhodyny (0, 1, 2...)\n> ");
				command=scanner.nextInt();
				
				if(command!=0){hodyny=((command*60)*60)*1000; chas+=hodyny; ho=command;}
				
				System.out.print("hwylyny (0, 1, 59.)\n> ");
				command=scanner.nextInt();
				
				if(command<60&&command!=0){hwylyny=(command*60)*1000; chas+=hwylyny; hw=command;}
				
				scanner.nextLine();
				
				if(chas==0){
					System.out.print("chas doriwnjuje 0:00, hybernuwaty zaraz? (t\\n)\n> ");
					zaraz=scanner.nextLine();
					if(zaraz.toLowerCase().equals("n")){command=-1;}
					else if(zaraz.toLowerCase().equals("t")){command=1;}}
				else {System.out.print("\nhybernacija cherez: "+ho+":"+hw+" (ho:hw), potochnyj chas: "+skilbkyZaraz()+"."+"\n\ndlia skasuwannia zakryj programu, abo natysny Ctrl+C");
					command=1;}
				
			switch (command) {
				case -1:
					break;
				case 1:
					running=false;
					Timer timer=new Timer();
					timer.schedule(new TimerTask() {
					public void run() {
						try {
							System.out.println("\n\n. . .zanurjujemosia na hlybynu: "+skilbkyZaraz()+".");
							
							Runtime.getRuntime().exec("Rundll32.exe powrprof.dll,SetSuspendState Sleep");} catch (Exception e) {e.printStackTrace();}}}, chas);
					break;
				default:
					System.out.print("shosb ne tak, sprobujemo she raz...\n");} } }
					
	static public String skilbkyZaraz(){
		LocalDateTime currentDateTime=LocalDateTime.now();
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime=currentDateTime.format(formatter);
		return formattedDateTime;}
}
