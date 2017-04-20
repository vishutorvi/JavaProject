package com.uttara.info;

import java.util.ArrayList;
import java.util.Map;

import com.uttara.connection.DatabaseConnections;

public class PhaseTwoCode {

	//Since the error also squares up we use the threshold for match as 0.9 * 0.9
	private final double threshold = (0.99 * 0.99);
	public int countPairs = 0;
	public static final String DELIMITER = "__";
	/**
	 * This Method is used to compare the user objects within the all the sub attribute groups
	 * using the match function which we define in the databaseconnections class.
	 * @param finalGraphMap
	 * @param finalObjectStructure
	 * @return count of pairs which satisfy the user attributes sub tree.
	 */
	public int phaseTwoUserObjectsAlgorithm(Map<String, ArrayList<String>> finalGraphMap,Map<String, ArrayList<String>> finalObjectStructure) {
		/**
		 * Step 1: joining on subtrees using unique identifiers
		 */
		ArrayList<String> userEntity = finalGraphMap.get("user");
		ArrayList<String> mergedByEntity = finalGraphMap.get("merged_by");
		ArrayList<String> assigneeEntity = finalGraphMap.get("assignee");
		ArrayList<String> milestoneEntity = finalGraphMap.get("milestone");
		ArrayList<String> headEntity = finalGraphMap.get("head");
		ArrayList<String> baseEntity = finalGraphMap.get("base");
		ArrayList<String> milestoneCreatorEntity = new ArrayList<String>();
		ArrayList<String> headRepoOwnerEntity = new ArrayList<String>();
		ArrayList<String> baseRepoOwnerEntity = new ArrayList<String>();
		ArrayList<String> headUserEntity = new ArrayList<String>();
		ArrayList<String> baseUserEntity = new ArrayList<String>();
		ArrayList<String> headRepoEntity = new ArrayList<String>();
		ArrayList<String> baseRepoEntity = new ArrayList<String>();
		/**
		 * Creating Creator entity from milestoneEntity
		 */
		for(String milestoneAttribute:milestoneEntity){
			String[] splitString = milestoneAttribute.split("__");
			if(splitString.length == 3 && splitString[1].equals("creator")){
				milestoneCreatorEntity.add(milestoneAttribute);
			}
		}
		/**
		 * Creating head user entity from headEntity
		 */
		for(String headUser:headEntity){
			String[] splitString = headUser.split("__");
			if(splitString.length == 3 && splitString[1].equals("user")){
				headUserEntity.add(headUser);
			}
		}
		/**
		 * Creating base user entity from baseEntity
		 */
		for(String baseUser:baseEntity){
			String[] splitString = baseUser.split("__");
			if(splitString.length == 3 && splitString[1].equals("user")){
				baseUserEntity.add(baseUser);
			}
		}
		/**
		 * Creating head repo entity from headEntity
		 */
		for(String headUser:headEntity){
			String[] splitString = headUser.split("__");
			if(splitString.length == 3 && splitString[1].equals("repo")){
				headRepoEntity.add(headUser);
			}else if(splitString.length == 4 && splitString[2].equals("owner")){
				headRepoOwnerEntity.add(headUser);
			}else if(splitString.length == 2 && splitString[0].equals("head")){
				headRepoEntity.add(headUser);
			}
		}
		/**
		 * Creating base repo entity from headEntity
		 */
		for(String baseUser:baseEntity){
			String[] splitString = baseUser.split("__");
			if(splitString.length == 3 && splitString[1].equals("repo")){
				baseRepoEntity.add(baseUser);
			}else if(splitString.length == 4 && splitString[2].equals("owner")){
				baseRepoOwnerEntity.add(baseUser);
			}else if(splitString.length == 2 && splitString[0].equals("base")){
				baseRepoEntity.add(baseUser);
			}
		}
		//finding pairs of similar objects
		//first between user and merged_by
		String finalUserObject = null;
		String finalMergedObject = null;
		String finalBaseRepoOwnerObject = null;
		String finalHeadRepoOwnerObject = null;
		String finalHeadUserObject = null;
		String finalBaseUserObject = null;
		String finalAssigneeObject = null;
		String finalCreatorObject = null;
		ArrayList<String> users = finalObjectStructure.get("users");
		//This call will return the value of comparison result and 
		double attrMatrixByJoinSet1 = DatabaseConnections.comparisionOfUserBasedEntities(userEntity,mergedByEntity);
		if(attrMatrixByJoinSet1 >= threshold){
			for(String user:userEntity){
				countPairs = countPairs + 1;
				if(finalUserObject == null){
					finalUserObject = user;
				}else{
					finalUserObject += ","+user;
				}
			}
			//ArrayList<String> users = finalObjectStructure.get("users");
			users.add(finalUserObject);
			for(String mergedUser:mergedByEntity){
				if(finalUserObject == null){
					finalMergedObject = mergedUser;
				}else{
					finalMergedObject += ","+mergedUser;
				}
			}
			users.add(finalMergedObject);
		}
		double attrMatrixByJoinSet2 = DatabaseConnections.comparisionOfUserBasedEntities(userEntity,assigneeEntity);
		if(attrMatrixByJoinSet2 >= threshold){
			for(String ownerEntity:assigneeEntity){
				if(finalAssigneeObject == null){
					finalAssigneeObject = ownerEntity;
				}else{
					finalAssigneeObject += ","+ownerEntity;
				}
			}
			users.add(finalAssigneeObject);
		}
		double attrMatrixByJoinSet3 = DatabaseConnections.comparisionOfUserBasedEntities(userEntity,milestoneCreatorEntity);
		if(attrMatrixByJoinSet3 >= threshold){
			for(String mergedUser:milestoneCreatorEntity){
				if(finalUserObject == null){
					finalCreatorObject = mergedUser;
				}else{
					finalCreatorObject += ","+mergedUser;
				}
			}
			users.add(finalCreatorObject);
		}
		double attrMatrixByJoinSet4 = DatabaseConnections.comparisionOfUserBasedEntities(userEntity,baseRepoOwnerEntity);
		if(attrMatrixByJoinSet4 >= threshold){
			for(String ownerEntity:baseRepoOwnerEntity){
				if(finalBaseRepoOwnerObject == null){
					finalBaseRepoOwnerObject = ownerEntity;
				}else{
					finalBaseRepoOwnerObject += ","+ownerEntity;
				}
			}
			users.add(finalBaseRepoOwnerObject);
		}
		double attrMatrixByJoinSet5 = DatabaseConnections.comparisionOfUserBasedEntities(userEntity,headRepoOwnerEntity);
		if(	attrMatrixByJoinSet5 >= threshold){
			for(String ownerEntity:headRepoOwnerEntity){
				if(finalHeadRepoOwnerObject == null){
					finalHeadRepoOwnerObject = ownerEntity;
				}else{
					finalHeadRepoOwnerObject += ","+ownerEntity;
				}
			}
			users.add(finalHeadRepoOwnerObject);
		}
		double attrMatrixByJoinSet6 = DatabaseConnections.comparisionOfUserBasedEntities(userEntity,headUserEntity);
		if(attrMatrixByJoinSet6 >= threshold){
			for(String ownerEntity:headUserEntity){
				if(finalHeadUserObject == null){
					finalHeadUserObject = ownerEntity;
				}else{
					finalHeadUserObject += ","+ownerEntity;
				}
			}
			users.add(finalHeadUserObject);
		}
		for(String ownerEntity:baseUserEntity){
			if(finalBaseUserObject == null){
				finalBaseUserObject = ownerEntity;
			}else{
				finalBaseUserObject += ","+ownerEntity;
			}
		}
		users.add(finalHeadUserObject);
		System.out.println("UserEntity Correct Attribute Pairs:"+countPairs);
		return countPairs;
	}
	/**
	 * This Method is used to compare Similarity objects for sub tree attribute group for Head attribute group.
	 * using the match function which we define in the databaseconnections class. 
	 * @param finalGraphMap
	 * @param finalObjectStructure
	 * @return count of pairs which matches with the head attribute group
	 */
	public int phaseTwoHeadRepoObjectSimliar(Map<String, ArrayList<String>> finalGraphMap,Map<String, ArrayList<String>> finalObjectStructure){
		ArrayList<String> headRepoEntity = new ArrayList<String>();
		ArrayList<String> baseRepoEntity = new ArrayList<String>();
		String finalHeadRepoObject = null;
		String finalHeadLabelObject = null;
		String finalBaseRepoObject = null;
		String finalBaseLabelObject = null;
		ArrayList<String> headEntity = finalGraphMap.get("head");
		ArrayList<String> baseEntity = finalGraphMap.get("base");
		/**
		 * Creating head repo entity from headEntity
		 */
		for(String headUser:headEntity){
			String[] splitString = headUser.split("__");
			if(splitString.length == 3 && splitString[1].equals("repo")){
				headRepoEntity.add(headUser);
			}else if(splitString.length == 2 && splitString[0].equals("head")){
				headRepoEntity.add(headUser);
			}
		}
		/**
		 * Creating base repo entity from headEntity
		 */
		for(String baseUser:baseEntity){
			String[] splitString = baseUser.split("__");
			if(splitString.length == 3 && splitString[1].equals("repo")){
				baseRepoEntity.add(baseUser);
			}else if(splitString.length == 2 && splitString[0].equals("base")){
				baseRepoEntity.add(baseUser);
			}
		}
		double attrMatrixByRepoJoinSet1 = DatabaseConnections.comparisionOfHeadBaseEntities(headRepoEntity,baseRepoEntity);
		if(attrMatrixByRepoJoinSet1 >= threshold){
			for(String headRepo:headRepoEntity){
				if(!(headRepo.equals("head__label") || headRepo.equals("head__ref"))){
					if(finalHeadRepoObject == null){
						finalHeadRepoObject = headRepo;
					}else{
						finalHeadRepoObject += ","+headRepo;
					}
				}else{
					if(finalHeadLabelObject == null){
						finalHeadLabelObject = headRepo;
					}else{
						finalHeadLabelObject += ","+headRepo;
					}
				}
			}
			for(String baseRepo:baseRepoEntity){
				countPairs = countPairs + 1;
				if(!(baseRepo.equals("base__label") || baseRepo.equals("base__ref"))){
					if(finalBaseRepoObject == null){
						finalBaseRepoObject = baseRepo;
					}else{
						finalBaseRepoObject += ","+baseRepo;
					}
				}else{
					if(finalBaseLabelObject == null){
						finalBaseLabelObject = baseRepo;
					}else{
						finalBaseLabelObject += ","+baseRepo;
					}
				}
			}
			if(finalObjectStructure != null){
				ArrayList<String> labels = finalObjectStructure.get("labels");
				labels.add(finalHeadLabelObject);
				labels.add(finalBaseLabelObject);
				ArrayList<String> repos = finalObjectStructure.get("repos");
				repos.add(finalHeadRepoObject);
				repos.add(finalBaseRepoObject);
			}
			System.out.println("headBase correct attribute pairs are:"+countPairs);
		}
		return countPairs;
	}
	/**
	 * This method is used to provide the number of Missing attribute from the subtrees
	 * which we added in the root pullrequest attribute group
	 * @param finalObjectStructure
	 * @return count of miss attributes
	 */
	public int phaseTwoMissingAttributes(
			Map<String, ArrayList<String>> finalObjectStructure) {
		int missingAttributePair = 0;
		if(finalObjectStructure != null){
			ArrayList<String> pullRequestAttributeGroup = finalObjectStructure.get("pullrequest");
			if(pullRequestAttributeGroup != null){
				for(String attribute:pullRequestAttributeGroup){
					if(attribute.contains(DELIMITER)){
						missingAttributePair = missingAttributePair + 1;
					}
				}
			}
			pullRequestAttributeGroup = finalObjectStructure.get("repos");
			if(pullRequestAttributeGroup != null){
				String attributeMapContent = pullRequestAttributeGroup.get(0);
				String[] attributes = attributeMapContent.split(","); 
				for(String attribute:attributes){
					if(attribute.contains("repo")){
						missingAttributePair = missingAttributePair + 1;
					}
				}
			}
		}
		return missingAttributePair;
	}
}