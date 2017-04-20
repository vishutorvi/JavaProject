package com.uttara.info;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.uttara.vo.GithubJsonSchemaVO;

public class JsonMainConverter {
	/**
	 * Main Method for our project implementation
	 * We have divided our project into 3 phases
	 * 	1) Soft Dependency Check between attributes and group them to form attribute tree
	 *  2) Match Function for comparing attributes within the attribute groups.
	 *  3) Create Physical Schema for the Input JSON structure.
	 * 
	 */
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		Date startDate = calendar.getTime();
		JSONParser parser = new JSONParser();
		try
		{
			/**
			 * Prephase step we will be using only pull request repository which has 7 sub repositories inside it 
			 * this below file has only one json structure in it
			 * we build our logic on top of this and plug in all other data into the database using same logic.
			 */
			Object obj3;
			JSONObject pullrequests;
			Gson gson = new Gson();
			int count = 0;
			boolean insertionDone = true;
			String strLine;
			/*Phase one Code starts*/
			PhaseOneCode phaseOne = new PhaseOneCode();
			PhaseTwoCode phaseTwo = new PhaseTwoCode();
			/*Calling Method to create table*/
			/*If table is created then we need to insert into table or else we dont insert*/
			//BufferedReader br = new BufferedReader(new FileReader("F:\\pull_requests.json\\prs.json"));
			//BufferedReader br = new BufferedReader(new FileReader("G:\\Database_Project\\pullrequest\\pullrequest_json_1.json"));
			BufferedReader br = new BufferedReader(new FileReader("src/pullrequest_json_1.json"));
			//BufferedReader br = new BufferedReader(new FileReader("G:\\Database_Project\\Dataset\\pull_requestsdb\\pull_requests.json"));
			//BufferedReader br = new BufferedReader(new FileReader("G:\\Database_Project\\pull_requestsdb.pull_requests.json"));
			GithubJsonSchemaVO githubJson;
			phaseOne.createFlatTable();
			/**
			 * Here we insert all the json objects which we get from the dataset
			 * We connect to database and insert into the flattable.
			 */
			while ((strLine = br.readLine()) != null){
				if(strLine != null){
					count = count + 1;
					strLine = strLine.replace('\'', '_');
					strLine = strLine.replaceAll("\'\',", "null");
					strLine = strLine.replaceAll("-","");
					int length = strLine.length();
					strLine = strLine.substring(0, length-1);
					obj3 = parser.parse(strLine);
					pullrequests = (JSONObject) obj3;
					githubJson = gson.fromJson(strLine, GithubJsonSchemaVO.class);
					/*If possible need transaction*/
					insertionDone = phaseOne.insertValueIntoTable(githubJson);
					System.out.println("count = "+count);
				}
			}
			Map<String,String> columnNames = phaseOne.columnInfo();
			//phaseOne.countFunctionalDependency(columnNames);//time consumer
			/**
			 * Step 1: create attribute tree for each of the parent attributes 
			 * by checking the functional dependencies among all the attributes
			 * we create a attribute tree, and group the attributes with respect to 
			 * longest path, Eg: if we have one attribute which satisfies the functional
			 * dependence condition for root parent attribute and also for subtree 
			 * parent attribute, then we group this attribute to the subtree parent attribute 
			 * We used the finalGraphMap as our final Map structure for this step.
			 */
			Map<String,LinkedHashSet<String>> finalGraphMap1 = new LinkedHashMap<String,LinkedHashSet<String>>();
			Map<String,ArrayList<String>> finalObjectStructure = new LinkedHashMap<String,ArrayList<String>>();
			Map<String,ArrayList<String>> finalGraphMap = new LinkedHashMap<String,ArrayList<String>>();
			finalGraphMap1.put("pullrequest",new LinkedHashSet<String>());
			finalGraphMap1.put("user",new LinkedHashSet<String>());
			finalGraphMap1.put("assignee",new LinkedHashSet<String>());
			finalGraphMap1.put("head",new LinkedHashSet<String>());
			finalGraphMap1.put("base",new LinkedHashSet<String>());
			finalGraphMap1.put("links",new LinkedHashSet<String>());
			finalGraphMap1.put("milestone", new LinkedHashSet<String>());
			finalGraphMap1.put("merged_by",new LinkedHashSet<String>());
			//Functional Dependency Check for RootObject
			phaseOne.dependencyForRootObjects(finalGraphMap1,columnNames);
			//Functional Dependency Check for HeadBaseObject
			phaseOne.dependencyOnHeadBaseObjects(finalGraphMap1,columnNames);
			//Functional Dependency Check for MilestoneObject
			phaseOne.dependencyOnMilestoneLinkObjects(finalGraphMap1,columnNames);
			//Functional Dependency Check for UserObject
			phaseOne.dependencyUserTypeObjects(finalGraphMap1,columnNames);
			//Populate final graph map
			phaseOne.populateFinalGraphMap(finalGraphMap1, columnNames);
			for(Map.Entry<String, LinkedHashSet<String>> graphs:finalGraphMap1.entrySet()){
				finalGraphMap.put(graphs.getKey(), new ArrayList<String>(graphs.getValue()));
			}
			//Constructing Directed Acyclic Graph using finalgraphMap object and grouping attributes and constructing tree structure
			phaseOne.constructingDAGraphFromAttributeDependency(finalGraphMap);
			phaseOne = null;
			finalGraphMap1 = null;
			/*
			 * Phase one Code ends
			 */
			/*Phase two Code starts*/
			/*All similar User objects*/
			/**
			 * Step 2: Once we obtain the attribute tree with attributes groups
			 * we match the subtrees within this attribute tree in our case we have used
			 * map which we call as finalGraphMap.
			 * Using the Match function we check the similar subtrees
			 * we group the attribute subtrees into one group and this is
			 * populated into the finalObjectStructure map structure.
			 */
			finalObjectStructure.put("labels", new ArrayList<String>());
			finalObjectStructure.put("repos", new ArrayList<String>());
			finalObjectStructure.put("users", new ArrayList<String>());
			//objects which are independent of each other 
			finalObjectStructure.put("pullrequest",finalGraphMap.get("pullrequest"));
			finalObjectStructure.put("milestone", finalGraphMap.get("milestone"));
			finalObjectStructure.put("links", finalGraphMap.get("links"));
			//Completion of phase 1
			Calendar calendar2 = Calendar.getInstance();
			// Get start time (this needs to be a global variable).
			Date endDate = calendar2.getTime();
			//To print the time
			printDifference(startDate,endDate,"phase 1:");
			//Phase 2
			Calendar calendarPhase2Start = Calendar.getInstance();
			Date startDatePhase2 = calendarPhase2Start.getTime();
			//This will populate all the user related similar objects into the map
			int countPairs = phaseTwo.phaseTwoUserObjectsAlgorithm(finalGraphMap,finalObjectStructure);
			phaseTwo = new PhaseTwoCode();
			//This will populate head similar objects into the finalObjectStructure map
			countPairs += phaseTwo.phaseTwoHeadRepoObjectSimliar(finalGraphMap, finalObjectStructure);
			System.out.println("Total Correct Paired Attributes are:"+countPairs);
			//To calculate missing attribute pairs
			int missingAttributes = phaseTwo.phaseTwoMissingAttributes(finalObjectStructure);
			System.out.println("Total missing attribute pairs:"+missingAttributes);
			if(finalObjectStructure != null){
				System.out.println("Finalized similar objects are populated & user object counts are:"+finalObjectStructure.get("users").size());
			}
			Calendar calendarPhase2End = Calendar.getInstance();
			Date endDatePhase2 = calendarPhase2End.getTime();
			printDifference(startDatePhase2,endDatePhase2,"phase 2:");
			/*
			 * Phase two Code ends
			 */
			/*
			 * Phase three Code starts
			 */
			/**
			 * Step 3: We generate the physical schema from the attribute tree
			 * by creating the grouped attribute subtrees as one table in our schema
			 */
			Calendar calendarPhase3Start = Calendar.getInstance();
			Date startDatePhase3 = calendarPhase3Start.getTime();
			PhaseThreeCode phaseThird = new PhaseThreeCode();
			phaseThird.creatingAndInsertingTables(finalObjectStructure);
			
			Calendar calendarPhase3end = Calendar.getInstance();
			Date endDatePhase3 = calendarPhase3end.getTime();
			printDifference(startDatePhase3, endDatePhase3, "phase 3:");
			//overall system time
			printDifference(startDate, endDatePhase3, "Overall System:");
			/*Phase three Code ends*/
			long stopTime = System.currentTimeMillis();
		    long elapsedTime = stopTime - startTime;
		   // System.out.println("Time taken to complete the system execution: "+elapsedTime);
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Method to evaluate the execution time of this project
	 * @param startDate
	 * @param endDate
	 * @param phases
	 */
	public static void printDifference(Date startDate, Date endDate,String phases){

		//milliseconds
		long different = endDate.getTime() - startDate.getTime();

		System.out.println("startDate : " + startDate);
		System.out.println("endDate : "+ endDate);
		System.out.println("different : " + different);

		long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;

		long elapsedDays = different / daysInMilli;
		different = different % daysInMilli;

		long elapsedHours = different / hoursInMilli;
		different = different % hoursInMilli;

		long elapsedMinutes = different / minutesInMilli;
		different = different % minutesInMilli;

		long elapsedSeconds = different / secondsInMilli;

		System.out.printf(phases+" execution time:"+
		    "%d days, %d hours, %d minutes, %d seconds%n",
		    elapsedDays,
		    elapsedHours, elapsedMinutes, elapsedSeconds);

	}
}