package com.uttara.info;

import java.util.ArrayList;
import java.util.Map;

import com.uttara.connection.DatabaseConnections;

public class PhaseThreeCode {
	
	public void creatingAndInsertingTables(Map<String,ArrayList<String>> finalObjectStructure){
		//First for User Objects inserting
		creatingAndInsertingUserTable(finalObjectStructure);
		//Fourth for label Objects inserting
		creatingAndInsertingLabelTable(finalObjectStructure);
		//Second for Head Objects inserting
		creatingAndInsertingHeadTable(finalObjectStructure);
		//Third for Milestone Objects inserting
		creatingAndInsertingMilestoneTable(finalObjectStructure);	
		//fifth for links objects inserting
		creatingAndInsertingLinkTable(finalObjectStructure);
		//sixth for root pullrequest object inserting
		creatingAndInsertingPullRequestTable(finalObjectStructure);
	}
	/**
	 * Method to create and insert Pull request object into the database
	 * @param finalObjectStructure
	 */
	public void creatingAndInsertingPullRequestTable(
			Map<String, ArrayList<String>> finalObjectStructure) {
		String createPullRequestTable = "CREATE TABLE PULL_REQUEST(url TEXT,id BIGINT,html_url TEXT,diff_url TEXT," +
				"patch_url TEXT,issue_url TEXT,num BIGINT,state TEXT,locked BOOLEAN,title TEXT,user_id BIGINT,body TEXT," +
				"created_at TEXT,updated_at TEXT,closed_at TEXT,merged_at TEXT,merge_commit TEXT,assignee_id BIGINT," +
				"assignees TEXT,milestone_id BIGINT,commits_url TEXT,review_comments_url TEXT,review_comment_url TEXT," +
				"comments_url TEXT,statuses_url TEXT,head_repo_name VARCHAR(60),head_repo_id BIGINT," +
				"base_repo_name VARCHAR(60),base_repo_id BIGINT,links_id BIGINT,merged BOOLEAN,mergeable TEXT," +
				"mergeable_state TEXT,merged_by_id BIGINT,comments BIGINT,review_comments BIGINT," +
				"maintainer_can_modify BOOLEAN,commits BIGINT,additions BIGINT,deletions BIGINT,changed_files BIGINT," +
				"owner TEXT,repo TEXT,primary key(id),foreign key (user_id) references users (id)," +
				"foreign key (assignee_id) references users (id),foreign key (milestone_id) references milestone (id)," +
				"foreign key (head_repo_id,head_repo_name) references head_base_repo (repo_id,repo_name)," +
				"foreign key (base_repo_id,base_repo_name) references head_base_repo (repo_id,repo_name)," +
				"foreign key (links_id) references links (id),foreign key (merged_by_id) references users (id))";
		String insertPullRequestTable = "url,id,html_url,diff_url,patch_url,issue_url,num,state,locked,title,user__id,body," +
				"created_at,updated_at,closed_at,merged_at,merge_commit,assignee__id,assignees,milestone__id,commits_url,review_comments_url," +
				"review_comment_url,comments_url,statuses_url,head__repo__id,head__repo__name,base__repo__id,base__repo__name,links__self__href," +
				"merged,mergeable,mergeable_state,merged_by__id,comments,review_comments,maintainer_can_modify,commits,additions," +
				"deletions,changed_files,owner,repo";
		if(DatabaseConnections.createPullRequestTable(createPullRequestTable)){
			DatabaseConnections.insertIntoPullRequestTable(insertPullRequestTable);
		}
	}
	/**
	 * Method to create and insert link objects in the database
	 * @param finalObjectStructure
	 */
	public void creatingAndInsertingLinkTable(
			Map<String, ArrayList<String>> finalObjectStructure) {
		String creatingLinkTable = "CREATE TABLE LINKS(";
		creatingLinkTable += "id BIGINT AUTO_INCREMENT, self_href TEXT, html_href TEXT, issue_href TEXT, comments_href TEXT, reviewcomments_href TEXT," +
				"commits_href TEXT, statuses_href TEXT, primary key(id))";
		String valuesToInsertLink = "links__self__href,links__html__href,links__issue__href,links__comments__href," +
				"links__review_comments__href,links__review_comment__href,links__commits__href,links__statuses__href";
		if(DatabaseConnections.createLinkTable(creatingLinkTable)){
			DatabaseConnections.insertingIntoLinkTable(valuesToInsertLink);
		}
	}
	/**
	 * Method to create and insert label table
	 */
	public void creatingAndInsertingLabelTable(Map<String,ArrayList<String>> finalObjectStructure){
		String creatingLabelTable = "CREATE TABLE LABEL(";
		creatingLabelTable += "id BIGINT AUTO_INCREMENT,hu_id BIGINT,label VARCHAR(50),ref TEXT,primary key(id,hu_id))";
		String insertLabelTable = "head__label, head__ref,head__user__id";
		String insertBaseLabelTable = "base__label, base__ref,base__user__id";
		//Creating label table in database
		if(DatabaseConnections.createLabelTable(creatingLabelTable)){
			//inserting label table in database
			DatabaseConnections.insertLabelTable(insertLabelTable);
			DatabaseConnections.insertLabelTable(insertBaseLabelTable);
		}
	}
	/**
	 * Method to create and insert milestone table
	 */
	public void creatingAndInsertingMilestoneTable(Map<String,ArrayList<String>> finalObjectStructure){
		String creatingMileStoneTable = "CREATE TABLE MILESTONE(";
		creatingMileStoneTable += "url TEXT, html_url TEXT, labels_url TEXT, id BIGINT," +
				"number BIGINT, title TEXT, description TEXT, creator_id BIGINT, " +
				"open_issues BIGINT, closed_issues BIGINT, state TEXT, created_at TEXT," +
				" updated_at TEXT, due_on TEXT, closed_at TEXT, primary key(id), foreign key (creator_id) references users (id))";
		String insertingIntoMilestone = "milestone__url, milestone__html_url, milestone__labels_url, milestone__id, milestone__number," +
				" milestone__title, milestone__description, milestone__creator__id," +
				" milestone__open_issues, milestone__closed_issues, milestone__state, milestone__created_at, " +
				" milestone__updated_at, milestone__due_on, milestone__closed_at"; 
		if(DatabaseConnections.createMilestoneTable(creatingMileStoneTable)){
			DatabaseConnections.insertIntoMilestoneTable(insertingIntoMilestone);
		}
	}
	/**
	 * Method to create Head Table and insert Head and Base details
	 */
	public void creatingAndInsertingHeadTable(Map<String,ArrayList<String>> finalObjectStructure){
		String creatingHeadBaseTable = "CREATE TABLE HEAD_BASE_REPO(";
		creatingHeadBaseTable += "sha TEXT, user_id BIGINT, repo_id BIGINT, repo_name VARCHAR(60), repo_fullname TEXT, private BOOLEAN, html_url TEXT, description TEXT," +
				" fork BOOLEAN, url TEXT, forks_url TEXT, keys_url TEXT, collaborators_url TEXT, teams_url TEXT,hooks_url TEXT, issue_events_url TEXT," +
				" events_url TEXT, assignees_url TEXT, branches_url TEXT, tags_url TEXT, blobs_url TEXT, git_tags_url TEXT, git_refs_url TEXT," +
				" trees_url TEXT, statuses_url TEXT, languages_url TEXT, stargazers_url TEXT, contributors_url TEXT, subscribers_url TEXT," +
				" subscription_url TEXT, commits_url TEXT, git_commits_url TEXT, comments_url TEXT, contents_url TEXT, compare_url TEXT," +
				" merges_url TEXT, downloads_url TEXT, issues_url TEXT, pulls_url TEXT, milestones_url TEXT, notification_url TEXT, labels_url TEXT," +
				" releases_url TEXT, deployments_url TEXT, created_at TEXT, pushed_at TEXT, ssh_url TEXT, clone_url TEXT, svn_url TEXT," +
				" homepage TEXT, size BIGINT, stargazers_count BIGINT, watchers_count BIGINT, language TEXT, has_issues BOOLEAN, has_downloads BOOLEAN," +
				" has_wiki BOOLEAN, has_pages BOOLEAN, forks_count BIGINT, mirror_url TEXT, open_issues_count BIGINT, forks BIGINT," +
				" open_issues BIGINT, watchers BIGINT, default_branch TEXT,label_id BIGINT,primary key(repo_id,repo_name),foreign key (user_id) references users (id),foreign key(label_id) references label(id))";
		String insertingHeadTable = "head__label, head__ref, head__sha,head__user__id, head__repo__id, head__repo__name, head__repo__fullname, head__private, head__html_url," +
				"head__description, head__fork, head__url, head__forks_url, head__keys_url, head__collaborators_url, head__teams_url, " +
				"head__hooks_url, head__issue_events_url, head__events_url, head__assignees_url, head__branches_url, head__tags_url," +
				" head__blobs_url, head__git_tags_url, head__git_refs_url, head__trees_url, head__statuses_url, head__languages_url," +
				" head__stargazers_url, head__contributors_url, head__subscribers_url, head__subscription_url, head__commits_url," +
				" head__git_commits_url, head__comments_url, head__contents_url, head__compare_url, head__merges_url, head__downloads_url," +
				" head__issues_url, head__pulls_url, head__milestones_url, head__notification_url, head__labels_url, head__releases_url," +
				" head__deployments_url, head__created_at, head__pushed_at, head__ssh_url, head__clone_url, head__svn_url, head__homepage," +
				" head__size, head__stargazers_count, head__watchers_count, head__language, head__has_issues, head__has_downloads, " +
				"head__has_wiki, head__has_pages, head__forks_count, head__mirror_url, head__open_issues_count, head__forks, head__open_issues," +
				" head__watchers, head__default_branch";
		String insertingBaseTable = "base__label, base__ref, base__sha,base__user__id,base__repo__id, base__repo__name, base__repo__fullname," +
				" base__private, base__html_url, base__description, base__fork, base__url, base__forks_url, base__keys_url, " +
				"base__collaborators_url, base__teams_url, base__hooks_url, base__issue_events_url, base__events_url, base__assignees_url," +
				" base__branches_url, base__tags_url, base__blobs_url, base__git_tags_url, base__git_refs_url, base__trees_url," +
				" base__statuses_url, base__languages_url, base__stargazers_url, base__contributors_url, base__subscribers_url, " +
				"base__subscription_url, base__commits_url, base__git_commits_url, base__comments_url, base__contents_url, base__compare_url," +
				" base__merges_url, base__downloads_url, base__issues_url, base__pulls_url, base__milestones_url, base__notification_url," +
				" base__labels_url, base__releases_url, base__deployments_url, base__created_at, base__pushed_at, base__ssh_url, base__clone_url," +
				" base__svn_url, base__homepage, base__size, base__stargazers_count, base__watchers_count, base__language, base__has_issues," +
				" base__has_downloads, base__has_wiki, base__has_pages, base__forks_count, base__mirror_url, base__open_issues_count," +
				" base__forks, base__open_issues, base__watchers, base__default_branch";
		if(DatabaseConnections.createHeadBaseTable(creatingHeadBaseTable)){
			//inserting headbase objects into database
			DatabaseConnections.insertHeadBaseTable(insertingHeadTable);
			DatabaseConnections.insertHeadBaseTable(insertingBaseTable);
		}
	}
	/**
	 * Method to create User Table and insert user details into it
	 */
	public void creatingAndInsertingUserTable(Map<String,ArrayList<String>> finalObjectStructure){
		String creatingUserTable = "CREATE TABLE USERS(login TEXT,id BIGINT,avatar_url TEXT,gravatar_id BIGINT,url TEXT,html_url TEXT,followers_url TEXT,following_url TEXT," +
				"gists_url TEXT,starred_url TEXT,subscriptions_url TEXT,organizations_url TEXT,repos_url TEXT,events_url TEXT,received_events_url TEXT,type TEXT,site_admin BOOLEAN, PRIMARY KEY (id))";
		ArrayList<String> allUserTableVariables = finalObjectStructure.get("users");
		String user="";
		String merged_by="";
		String assignee="";
		String milestone_creator="";
		String base_repo_owner="";
		String head_repo_owner="";
		String head_user="";
		String base_user="";
		//generate user strings
		for(String userString:allUserTableVariables){
			if(userString.contains("head__user")){
				head_user = head_user + "," +userString;
			}
			else if(userString.contains("base__user")){
				base_user = base_user + "," +userString;
			}
			else if(userString.contains("merged_by")){
				merged_by = merged_by + "," +userString;
			}
			else if(userString.contains("assignee")){
				assignee = assignee + "," +userString;
			}
			else if(userString.contains("milestone__creator")){
				milestone_creator = milestone_creator + "," +userString;
			}
			else if(userString.contains("head__repo__owner")){
				head_repo_owner = head_repo_owner + "," +userString;
			}
			else if(userString.contains("base__repo__owner")){
				base_repo_owner = base_repo_owner + "," +userString;
			}
			else if(userString.contains("user")){
				user = user + "," +userString;
			}
		}
		//For time being we will hard code the values of the user objects
		user="user__login,user__id,user__avatar_url,user__gravatar_id,user__url,user__html_url,user__followers_url,user__following_url,user__gists_url,user__starred_url,user__subscriptions_url,user__organizations_url,user__repos_url,user__events_url,user__received_events_url,user__type,user__site_admin";
		merged_by="merged_by__login,merged_by__id,merged_by__avatar_url,merged_by__gravatar_id,merged_by__url,merged_by__html_url,merged_by__followers_url,merged_by__following_url,merged_by__gists_url,merged_by__starred_url,merged_by__subscriptions_url,merged_by__organizations_url,merged_by__repos_url,merged_by__events_url,merged_by__received_events_url,merged_by__type,merged_by__site_admin";
		assignee="assignee__login,assignee__id,assignee__avatar_url,assignee__gravatar_id,assignee__url,assignee__html_url,assignee__followers_url,assignee__following_url,assignee__gists_url,assignee__starred_url,assignee__subscriptions_url,assignee__organizations_url,assignee__repos_url,assignee__events_url,assignee__received_events_url,assignee__type,assignee__site_admin";
		milestone_creator="milestone__creator__login,milestone__creator__id,milestone__creator__avatar_url,milestone__creator__gravatar_id,milestone__creator__url,milestone__creator__html_url,milestone__creator__followers_url,milestone__creator__following_url,milestone__creator__gists_url,milestone__creator__starred_url,milestone__creator__subscriptions_url,milestone__creator__organizations_url,milestone__creator__repos_url,milestone__creator__events_url,milestone__creator__received_events_url,milestone__creator__type,milestone__creator__site_admin";
		base_repo_owner="base__repo__owner__login,base__repo__owner__id,base__repo__owner__avatar_url,base__repo__owner__gravatar_id,base__repo__owner__url,base__repo__owner__html_url,base__repo__owner__followers_url,base__repo__owner__following_url,base__repo__owner__gists_url,base__repo__owner__starred_url,base__repo__owner__subscriptions_url,base__repo__owner__organizations_url,base__repo__owner__repos_url,base__repo__owner__events_url,base__repo__owner__received_events_url,base__repo__owner__type,base__repo__owner__site_admin";
		head_repo_owner="head__repo__owner__login,head__repo__owner__id,head__repo__owner__avatar_url,head__repo__owner__gravatar_id,head__repo__owner__url,head__repo__owner__html_url,head__repo__owner__followers_url,head__repo__owner__following_url,head__repo__owner__gists_url,head__repo__owner__starred_url,head__repo__owner__subscriptions_url,head__repo__owner__organizations_url,head__repo__owner__repos_url,head__repo__owner__events_url,head__repo__owner__received_events_url,head__repo__owner__type,head__repo__owner__site_admin";
		head_user="head__user__login,head__user__id,head__user__avatar_url,head__user__gravatar_id,head__user__url,head__user__html_url,head__user__followers_url,head__user__following_url,head__user__gists_url,head__user__starred_url,head__user__subscriptions_url,head__user__organizations_url,head__user__repos_url,head__user__events_url,head__user__received_events_url,head__user__type,head__user__site_admin";
		base_user="base__user__login,base__user__id,base__user__avatar_url,base__user__gravatar_id,base__user__url,base__user__html_url,base__user__followers_url,base__user__following_url,base__user__gists_url,base__user__starred_url,base__user__subscriptions_url,base__user__organizations_url,base__user__repos_url,base__user__events_url,base__user__received_events_url,base__user__type,base__user__site_admin";
		//Creating User table in database
		if(DatabaseConnections.createUserTable(creatingUserTable)){
			//Inserting values into user table in database
			DatabaseConnections.insertingUserTable(user);
			DatabaseConnections.insertingUserTable(merged_by);
			DatabaseConnections.insertingUserTable(assignee);
			DatabaseConnections.insertingUserTable(milestone_creator);
			DatabaseConnections.insertingUserTable(base_repo_owner);
			DatabaseConnections.insertingUserTable(head_repo_owner);
			DatabaseConnections.insertingUserTable(head_user);
			DatabaseConnections.insertingUserTable(base_user);
		}
	}
}