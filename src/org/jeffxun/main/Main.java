package org.jeffxun.main;

import java.util.Scanner;
import java.util.Timer;

import org.jeffxun.util.ConsoleGraphis;
import org.jeffxun.util.GraphisType;

public class Main {

	public static void main(String[] args) throws InterruptedException 
	{
		Timer tm = new Timer();
		ConsoleGraphis cg = new ConsoleGraphis();
		tm.scheduleAtFixedRate(cg, 300, 300);
		Scanner input = new Scanner(System.in);
		while (true)
		{
			String value = input.next(); 
			if(value.equalsIgnoreCase("w"))
			{
				cg.SetEffectType(GraphisType.WAVE);
			}else if(value.equalsIgnoreCase("s"))
			{
				cg.SetEffectType(GraphisType.SWIM);
			}else if(value.equalsIgnoreCase("g"))
			{
				cg.SetBackDisplay(true);
			}else if(value.equalsIgnoreCase("h"))
			{
				cg.SetBackDisplay(false);
			}else if(value.equalsIgnoreCase("q"))
			{
				tm.cancel();
				break;
			}
		}
		input.close();
	}

}
