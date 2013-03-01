package com.team1160.feathersMcGraw.api;

import com.team1160.feathersMcGraw.input.InputManager;
import com.team1160.feathersMcGraw.teleop.TeleopManager;

/*
 * A class for printing, we chose to use a class
 * + static methods for this so we could print 
 * any where if we had to. I dont think we ever
 * did but ah well.
 * 
 * @Author preston
 */

public class Printer {

	
	public static void print(){
		System.out.println(InputManager.getInstance());
		System.out.println(TeleopManager.getInstance());
	}
	
	public static void send(String key, double robotAngle){
		System.out.println(key + ": " + robotAngle);
	}
	
}
