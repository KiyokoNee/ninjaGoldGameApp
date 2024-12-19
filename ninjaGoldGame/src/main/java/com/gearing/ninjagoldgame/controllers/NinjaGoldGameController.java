package com.gearing.ninjagoldgame.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.tags.shaded.org.apache.bcel.generic.NEW;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.EnumNamingStrategies.CamelCaseStrategy;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class NinjaGoldGameController {
	
	@GetMapping("")
	public String index(HttpSession session) {
		if(session.getAttribute("gold") == null)
			session.setAttribute("gold", 0);
		if(session.getAttribute("activities") == null)
			session.setAttribute("activities", new ArrayList<String>());
		
		if((Integer)session.getAttribute("gold") < -100)
			return "jail.jsp";
		
		return "index.jsp";
	}
	
	@PostMapping("/update")
	public String update(@RequestParam String action, HttpSession session) {
		int goldEarned;
		ArrayList<String> activities = (ArrayList<String>)session.getAttribute("activities");
		String activity;
		
		switch (action) {
			case "farm":
				goldEarned = calculateGold(20, 10, 1);
				activity = "You entered a farm and earned " + goldEarned + " gold. " + getTimeStamp();
				activities.add(activity);
			
				session.setAttribute("gold", (Integer)session.getAttribute("gold") + goldEarned);
				session.setAttribute("activities", activities);
			
				break;
			case "cave":
				goldEarned = calculateGold(10, 5, 1);
				activity = "You entered a cave and earned " + goldEarned + " gold. " + getTimeStamp();
				activities.add(activity);
			
				session.setAttribute("gold", (Integer)session.getAttribute("gold") + goldEarned);
				session.setAttribute("activities", activities);
				
				break;
			case "house":
				goldEarned = calculateGold(5, 2, 1);
				activity = "You entered a house and earned " + goldEarned + " gold. " + getTimeStamp();
				activities.add(activity);
			
				session.setAttribute("gold", (Integer)session.getAttribute("gold") + goldEarned);
				session.setAttribute("activities", activities);
				
				break;
			case "quest":
				goldEarned = calculateGold(50, 0, giveOrTake());
				if(goldEarned < 0)
					activity = "You failed a quest and lost " + Math.abs(goldEarned) + " gold. " + getTimeStamp();
				else
					activity = "You completed a quest and earned " + goldEarned + " gold. " + getTimeStamp();
				activities.add(activity);
			
				session.setAttribute("gold", (Integer)session.getAttribute("gold") + goldEarned);
				session.setAttribute("activities", activities);
				
				break;
			case "spa":
				goldEarned = calculateGold(50, 0, -1);
				activity = "You spend some time at the spa, spending " + Math.abs(goldEarned) + " gold to do so. " + getTimeStamp();
				activities.add(activity);
			
				session.setAttribute("gold", (Integer)session.getAttribute("gold") + goldEarned);
				session.setAttribute("activities", activities);
				
				break;
			case "reset":
				goldEarned = 0;
				activities.clear();
				
				session.setAttribute("gold", goldEarned);
				session.setAttribute("activities", activities);
				
				break;
			}
			
		
		return "redirect:/";
	}
	
	private static int calculateGold(int max, int min, int posOrNeg) {
		int range = max - min + 1;
		
		return posOrNeg * ((int)(Math.random() * range) + min);
	}
	
	private static int giveOrTake() {
		if(Math.random() < .5)
			return 1;
		return -1;
	}
	
	private static String getTimeStamp() {
		SimpleDateFormat format = new SimpleDateFormat("M d YYYY h:mm a");
		
		return "(" +format.format(new Date()) + ")";
	}

}
