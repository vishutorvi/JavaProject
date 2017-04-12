package com.uttara.info;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		JSONParser parser = new JSONParser();
		try
		{
			/**
			 * First step we will be using only pull request repository which has 7 sub repositories inside it 
			 * this below file has only one json structure in it
			 * we build our logic on top of this and plug in all other data into the database using same logic.
			 */
			//I have to code for headVO nearly
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
			//BufferedReader br = new BufferedReader(new FileReader("G:\\Database_Project\\pullrequest\\pullrequest_json_1.json"));
			BufferedReader br = new BufferedReader(new FileReader("src/pullrequest_json_1.json"));
			//BufferedReader br = new BufferedReader(new FileReader("G:\\Database_Project\\Dataset\\pull_requestsdb\\pull_requests.json"));
			//BufferedReader br = new BufferedReader(new FileReader("G:\\Database_Project\\pull_requestsdb.pull_requests.json"));
			GithubJsonSchemaVO githubJson;
			phaseOne.createFlatTable();
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
			//Dependency Check for RootObject
			phaseOne.dependencyForRootObjects(finalGraphMap1,columnNames);
			//Dependency Check for HeadBaseObject
			phaseOne.dependencyOnHeadBaseObjects(finalGraphMap1,columnNames);
			//Dependency Check for MilestoneObject
			phaseOne.dependencyOnMilestoneLinkObjects(finalGraphMap1,columnNames);
			//Dependency Check for UserObject
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
			/*Phase one Code ends*/
			/*Phase two Code starts*/
			/*All similar User objects*/
			finalObjectStructure.put("labels", new ArrayList<String>());
			finalObjectStructure.put("repos", new ArrayList<String>());
			finalObjectStructure.put("users", new ArrayList<String>());
			//objects which are independent of each other 
			finalObjectStructure.put("pullrequest",finalGraphMap.get("pullrequest"));
			finalObjectStructure.put("milestone", finalGraphMap.get("milestone"));
			finalObjectStructure.put("links", finalGraphMap.get("links"));
			//This will populate all the user related similar objects into the map
			int countPairs = phaseTwo.phaseTwoUserObjectsAlgorithm(finalGraphMap,finalObjectStructure);
			phaseTwo = new PhaseTwoCode();
			//This will populate head similar objects into the finalObjectStructure map
			countPairs += phaseTwo.phaseTwoHeadRepoObjectSimliar(finalGraphMap, finalObjectStructure);
			System.out.println("Total Correct Paired Attributes are:"+countPairs);
			if(finalObjectStructure != null){
				System.out.println("Finalized similar objects are populated & user object counts are:"+finalObjectStructure.get("users").size());
			}
			/*Phase two Code ends*/
			/*Phase three Code starts*/
			PhaseThreeCode phaseThird = new PhaseThreeCode();
			phaseThird.creatingAndInsertingTables(finalObjectStructure);
			/*Phase three Code ends*/
			long stopTime = System.currentTimeMillis();
		    long elapsedTime = stopTime - startTime;
		    System.out.println("Time taken to complete the system execution: "+elapsedTime);
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			e.printStackTrace();
		}
	}
}