package com.uttara.info;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import com.uttara.connection.DatabaseConnections;
import com.uttara.vo.GithubJsonSchemaVO;

public class PhaseOneCode {
	/**
	 * Method to create the table
	 * @param null
	 * @return creation success or not
	 */
	private int totalCountFD = 0;
	public boolean createFlatTable(){
		String creatingSchema = "CREATE TABLE FLAT_TABLE(";
		StringBuilder stringToCreateTable = new StringBuilder(creatingSchema);
		stringToCreateTable.append("url TEXT,");
		stringToCreateTable.append("id BIGINT,");
		stringToCreateTable.append("html_url TEXT,");
		stringToCreateTable.append("diff_url TEXT,");
		stringToCreateTable.append("patch_url TEXT,");
		stringToCreateTable.append("issue_url TEXT,");
		stringToCreateTable.append("num BIGINT,");
		stringToCreateTable.append("state TEXT,");
		stringToCreateTable.append("locked BOOLEAN,");
		stringToCreateTable.append("title TEXT,");
		stringToCreateTable.append("user__login TEXT,");
		stringToCreateTable.append("user__id BIGINT,");
		stringToCreateTable.append("user__avatar_url TEXT,");
		stringToCreateTable.append("user__gravatar_id TEXT,");
		stringToCreateTable.append("user__url TEXT,");
		stringToCreateTable.append("user__html_url TEXT,");
		stringToCreateTable.append("user__followers_url TEXT,");
		stringToCreateTable.append("user__following_url TEXT,");
		stringToCreateTable.append("user__gists_url TEXT,");
		stringToCreateTable.append("user__starred_url TEXT,");
		stringToCreateTable.append("user__subscriptions_url TEXT,");
		stringToCreateTable.append("user__organizations_url TEXT,");
		stringToCreateTable.append("user__repos_url TEXT,");
		stringToCreateTable.append("user__events_url TEXT,");
		stringToCreateTable.append("user__received_events_url TEXT,");
		stringToCreateTable.append("user__type TEXT,");
		stringToCreateTable.append("user__site_admin BOOLEAN,");
		stringToCreateTable.append("body TEXT,");
		stringToCreateTable.append("created_at TEXT,");
		stringToCreateTable.append("updated_at TEXT,");
		stringToCreateTable.append("closed_at TEXT,");
		stringToCreateTable.append("merged_at TEXT,");
		stringToCreateTable.append("merge_commit TEXT,");
		stringToCreateTable.append("assignee__login TEXT,");
		stringToCreateTable.append("assignee__id BIGINT,");
		stringToCreateTable.append("assignee__avatar_url TEXT,");
		stringToCreateTable.append("assignee__gravatar_id TEXT,");
		stringToCreateTable.append("assignee__url TEXT,");
		stringToCreateTable.append("assignee__html_url TEXT,");
		stringToCreateTable.append("assignee__followers_url TEXT,");
		stringToCreateTable.append("assignee__following_url TEXT,");
		stringToCreateTable.append("assignee__gists_url TEXT,");
		stringToCreateTable.append("assignee__starred_url TEXT,");
		stringToCreateTable.append("assignee__subscriptions_url TEXT,");
		stringToCreateTable.append("assignee__organizations_url TEXT,");
		stringToCreateTable.append("assignee__repos_url TEXT,");
		stringToCreateTable.append("assignee__events_url TEXT,");
		stringToCreateTable.append("assignee__received_events_url TEXT,");
		stringToCreateTable.append("assignee__type TEXT,");
		stringToCreateTable.append("assignee__site_admin BOOLEAN,");
		stringToCreateTable.append("assignees TEXT,");
		stringToCreateTable.append("milestone__url TEXT,");
		stringToCreateTable.append("milestone__html_url TEXT,");
		stringToCreateTable.append("milestone__labels_url TEXT,");
		stringToCreateTable.append("milestone__id BIGINT,");
		stringToCreateTable.append("milestone__number BIGINT,");
		stringToCreateTable.append("milestone__title TEXT,");
		stringToCreateTable.append("milestone__description TEXT,");
		stringToCreateTable.append("milestone__creator__login TEXT,");
		stringToCreateTable.append("milestone__creator__id BIGINT,");
		stringToCreateTable.append("milestone__creator__avatar_url TEXT,");
		stringToCreateTable.append("milestone__creator__gravatar_id TEXT,");
		stringToCreateTable.append("milestone__creator__url TEXT,");
		stringToCreateTable.append("milestone__creator__html_url TEXT,");
		stringToCreateTable.append("milestone__creator__followers_url TEXT,");
		stringToCreateTable.append("milestone__creator__following_url TEXT,");
		stringToCreateTable.append("milestone__creator__gists_url TEXT,");
		stringToCreateTable.append("milestone__creator__starred_url TEXT,");
		stringToCreateTable.append("milestone__creator__subscriptions_url TEXT,");
		stringToCreateTable.append("milestone__creator__organizations_url TEXT,");
		stringToCreateTable.append("milestone__creator__repos_url TEXT,");
		stringToCreateTable.append("milestone__creator__events_url TEXT,");
		stringToCreateTable.append("milestone__creator__received_events_url TEXT,");
		stringToCreateTable.append("milestone__creator__type TEXT,");
		stringToCreateTable.append("milestone__creator__site_admin BOOLEAN,");
		stringToCreateTable.append("milestone__open_issues BIGINT,");
		stringToCreateTable.append("milestone__closed_issues BIGINT,");
		stringToCreateTable.append("milestone__state TEXT,");
		stringToCreateTable.append("milestone__created_at TEXT,");
		stringToCreateTable.append("milestone__updated_at TEXT,");
		stringToCreateTable.append("milestone__due_on TEXT,");
		stringToCreateTable.append("milestone__closed_at TEXT,");
		stringToCreateTable.append("commits_url TEXT,");
		stringToCreateTable.append("review_comments_url TEXT,");
		stringToCreateTable.append("review_comment_url TEXT,");
		stringToCreateTable.append("comments_url TEXT,");
		stringToCreateTable.append("statuses_url TEXT,");
		stringToCreateTable.append("head__label VARCHAR(50),");
		stringToCreateTable.append("head__ref TEXT,");
		stringToCreateTable.append("head__sha TEXT,");
		stringToCreateTable.append("head__user__login TEXT,");
		stringToCreateTable.append("head__user__id BIGINT,");
		stringToCreateTable.append("head__user__avatar_url TEXT,");
		stringToCreateTable.append("head__user__gravatar_id TEXT,");
		stringToCreateTable.append("head__user__url TEXT,");
		stringToCreateTable.append("head__user__html_url TEXT,");
		stringToCreateTable.append("head__user__followers_url TEXT,");
		stringToCreateTable.append("head__user__following_url TEXT,");
		stringToCreateTable.append("head__user__gists_url TEXT,");
		stringToCreateTable.append("head__user__starred_url TEXT,");
		stringToCreateTable.append("head__user__subscriptions_url TEXT,");
		stringToCreateTable.append("head__user__organizations_url TEXT,");
		stringToCreateTable.append("head__user__repos_url TEXT,");
		stringToCreateTable.append("head__user__events_url TEXT,");
		stringToCreateTable.append("head__user__received_events_url TEXT,");
		stringToCreateTable.append("head__user__type TEXT,");
		stringToCreateTable.append("head__user__site_admin BOOLEAN,");
		stringToCreateTable.append("head__repo__id BIGINT,");
		stringToCreateTable.append("head__repo__name TEXT,");
		stringToCreateTable.append("head__repo__fullname VARCHAR(50),");
		stringToCreateTable.append("head__repo__owner__login TEXT,");
		stringToCreateTable.append("head__repo__owner__id BIGINT,");
		stringToCreateTable.append("head__repo__owner__avatar_url TEXT,");
		stringToCreateTable.append("head__repo__owner__gravatar_id TEXT,");
		stringToCreateTable.append("head__repo__owner__url TEXT,");
		stringToCreateTable.append("head__repo__owner__html_url TEXT,");
		stringToCreateTable.append("head__repo__owner__followers_url TEXT,");
		stringToCreateTable.append("head__repo__owner__following_url TEXT,");
		stringToCreateTable.append("head__repo__owner__gists_url TEXT,");
		stringToCreateTable.append("head__repo__owner__starred_url TEXT,");
		stringToCreateTable.append("head__repo__owner__subscriptions_url TEXT,");
		stringToCreateTable.append("head__repo__owner__organizations_url TEXT,");
		stringToCreateTable.append("head__repo__owner__repos_url TEXT,");
		stringToCreateTable.append("head__repo__owner__events_url TEXT,");
		stringToCreateTable.append("head__repo__owner__received_events_url TEXT,");
		stringToCreateTable.append("head__repo__owner__type TEXT,");
		stringToCreateTable.append("head__repo__owner__site_admin BOOLEAN,");
		stringToCreateTable.append("head__private BOOLEAN,");
		stringToCreateTable.append("head__html_url TEXT,");
		stringToCreateTable.append("head__description TEXT,");
		stringToCreateTable.append("head__fork BOOLEAN,");
		stringToCreateTable.append("head__url TEXT,");
		stringToCreateTable.append("head__forks_url TEXT,");
		stringToCreateTable.append("head__keys_url TEXT,");
		stringToCreateTable.append("head__collaborators_url TEXT,");
		stringToCreateTable.append("head__teams_url TEXT,");
		stringToCreateTable.append("head__hooks_url TEXT,");
		stringToCreateTable.append("head__issue_events_url TEXT,");
		stringToCreateTable.append("head__events_url TEXT,");
		stringToCreateTable.append("head__assignees_url TEXT,");
		stringToCreateTable.append("head__branches_url TEXT,");
		stringToCreateTable.append("head__tags_url TEXT,");
		stringToCreateTable.append("head__blobs_url TEXT,");
		stringToCreateTable.append("head__git_tags_url TEXT,");
		stringToCreateTable.append("head__git_refs_url TEXT,");
		stringToCreateTable.append("head__trees_url TEXT,");
		stringToCreateTable.append("head__statuses_url TEXT,");
		stringToCreateTable.append("head__languages_url TEXT,");
		stringToCreateTable.append("head__stargazers_url TEXT,");
		stringToCreateTable.append("head__contributors_url TEXT,");
		stringToCreateTable.append("head__subscribers_url TEXT,");
		stringToCreateTable.append("head__subscription_url TEXT,");
		stringToCreateTable.append("head__commits_url TEXT,");
		stringToCreateTable.append("head__git_commits_url TEXT,");
		stringToCreateTable.append("head__comments_url TEXT,");
		stringToCreateTable.append("head__contents_url TEXT,");
		stringToCreateTable.append("head__compare_url TEXT,");
		stringToCreateTable.append("head__merges_url TEXT,");
		stringToCreateTable.append("head__downloads_url TEXT,");
		stringToCreateTable.append("head__issues_url TEXT,");
		stringToCreateTable.append("head__pulls_url TEXT,");
		stringToCreateTable.append("head__milestones_url TEXT,");
		stringToCreateTable.append("head__notification_url TEXT,");
		stringToCreateTable.append("head__labels_url TEXT,");
		stringToCreateTable.append("head__releases_url TEXT,");
		stringToCreateTable.append("head__deployments_url TEXT,");
		stringToCreateTable.append("head__created_at TEXT,");
		stringToCreateTable.append("head__pushed_at TEXT,");
		stringToCreateTable.append("head__ssh_url TEXT,");
		stringToCreateTable.append("head__clone_url TEXT,");
		stringToCreateTable.append("head__svn_url TEXT,");
		stringToCreateTable.append("head__homepage TEXT,");
		stringToCreateTable.append("head__size BIGINT,");
		stringToCreateTable.append("head__stargazers_count BIGINT,");
		stringToCreateTable.append("head__watchers_count BIGINT,");
		stringToCreateTable.append("head__language TEXT,");
		stringToCreateTable.append("head__has_issues BOOLEAN,");
		stringToCreateTable.append("head__has_downloads BOOLEAN,");
		stringToCreateTable.append("head__has_wiki BOOLEAN,");
		stringToCreateTable.append("head__has_pages BOOLEAN,");
		stringToCreateTable.append("head__forks_count BIGINT,");
		stringToCreateTable.append("head__mirror_url TEXT,");
		stringToCreateTable.append("head__open_issues_count BIGINT,");
		stringToCreateTable.append("head__forks BIGINT,");
		stringToCreateTable.append("head__open_issues BIGINT,");
		stringToCreateTable.append("head__watchers BIGINT,");
		stringToCreateTable.append("head__default_branch TEXT,");
		stringToCreateTable.append("base__label VARCHAR(50),");
		stringToCreateTable.append("base__ref TEXT,");
		stringToCreateTable.append("base__sha TEXT,");
		stringToCreateTable.append("base__user__login TEXT,");
		stringToCreateTable.append("base__user__id BIGINT,");
		stringToCreateTable.append("base__user__avatar_url TEXT,");
		stringToCreateTable.append("base__user__gravatar_id TEXT,");
		stringToCreateTable.append("base__user__url TEXT,");
		stringToCreateTable.append("base__user__html_url TEXT,");
		stringToCreateTable.append("base__user__followers_url TEXT,");
		stringToCreateTable.append("base__user__following_url TEXT,");
		stringToCreateTable.append("base__user__gists_url TEXT,");
		stringToCreateTable.append("base__user__starred_url TEXT,");
		stringToCreateTable.append("base__user__subscriptions_url TEXT,");
		stringToCreateTable.append("base__user__organizations_url TEXT,");
		stringToCreateTable.append("base__user__repos_url TEXT,");
		stringToCreateTable.append("base__user__events_url TEXT,");
		stringToCreateTable.append("base__user__received_events_url TEXT,");
		stringToCreateTable.append("base__user__type TEXT,");
		stringToCreateTable.append("base__user__site_admin BOOLEAN,");
		stringToCreateTable.append("base__repo__id BIGINT,");
		stringToCreateTable.append("base__repo__name TEXT,");
		stringToCreateTable.append("base__repo__fullname TEXT,");
		stringToCreateTable.append("base__repo__owner__login TEXT,");
		stringToCreateTable.append("base__repo__owner__id BIGINT,");
		stringToCreateTable.append("base__repo__owner__avatar_url TEXT,");
		stringToCreateTable.append("base__repo__owner__gravatar_id TEXT,");
		stringToCreateTable.append("base__repo__owner__url TEXT,");
		stringToCreateTable.append("base__repo__owner__html_url TEXT,");
		stringToCreateTable.append("base__repo__owner__followers_url TEXT,");
		stringToCreateTable.append("base__repo__owner__following_url TEXT,");
		stringToCreateTable.append("base__repo__owner__gists_url TEXT,");
		stringToCreateTable.append("base__repo__owner__starred_url TEXT,");
		stringToCreateTable.append("base__repo__owner__subscriptions_url TEXT,");
		stringToCreateTable.append("base__repo__owner__organizations_url TEXT,");
		stringToCreateTable.append("base__repo__owner__repos_url TEXT,");
		stringToCreateTable.append("base__repo__owner__events_url TEXT,");
		stringToCreateTable.append("base__repo__owner__received_events_url TEXT,");
		stringToCreateTable.append("base__repo__owner__type TEXT,");
		stringToCreateTable.append("base__repo__owner__site_admin BOOLEAN,");
		stringToCreateTable.append("base__private BOOLEAN,");
		stringToCreateTable.append("base__html_url TEXT,");
		stringToCreateTable.append("base__description TEXT,");
		stringToCreateTable.append("base__fork BOOLEAN,");
		stringToCreateTable.append("base__url TEXT,");
		stringToCreateTable.append("base__forks_url TEXT,");
		stringToCreateTable.append("base__keys_url TEXT,");
		stringToCreateTable.append("base__collaborators_url TEXT,");
		stringToCreateTable.append("base__teams_url TEXT,");
		stringToCreateTable.append("base__hooks_url TEXT,");
		stringToCreateTable.append("base__issue_events_url TEXT,");
		stringToCreateTable.append("base__events_url TEXT,");
		stringToCreateTable.append("base__assignees_url TEXT,");
		stringToCreateTable.append("base__branches_url TEXT,");
		stringToCreateTable.append("base__tags_url TEXT,");
		stringToCreateTable.append("base__blobs_url TEXT,");
		stringToCreateTable.append("base__git_tags_url TEXT,");
		stringToCreateTable.append("base__git_refs_url TEXT,");
		stringToCreateTable.append("base__trees_url TEXT,");
		stringToCreateTable.append("base__statuses_url TEXT,");
		stringToCreateTable.append("base__languages_url TEXT,");
		stringToCreateTable.append("base__stargazers_url TEXT,");
		stringToCreateTable.append("base__contributors_url TEXT,");
		stringToCreateTable.append("base__subscribers_url TEXT,");
		stringToCreateTable.append("base__subscription_url TEXT,");
		stringToCreateTable.append("base__commits_url TEXT,");
		stringToCreateTable.append("base__git_commits_url TEXT,");
		stringToCreateTable.append("base__comments_url TEXT,");
		stringToCreateTable.append("base__contents_url TEXT,");
		stringToCreateTable.append("base__compare_url TEXT,");
		stringToCreateTable.append("base__merges_url TEXT,");
		stringToCreateTable.append("base__downloads_url TEXT,");
		stringToCreateTable.append("base__issues_url TEXT,");
		stringToCreateTable.append("base__pulls_url TEXT,");
		stringToCreateTable.append("base__milestones_url TEXT,");
		stringToCreateTable.append("base__notification_url TEXT,");
		stringToCreateTable.append("base__labels_url TEXT,");
		stringToCreateTable.append("base__releases_url TEXT,");
		stringToCreateTable.append("base__deployments_url TEXT,");
		stringToCreateTable.append("base__created_at TEXT,");
		stringToCreateTable.append("base__pushed_at TEXT,");
		stringToCreateTable.append("base__ssh_url TEXT,");
		stringToCreateTable.append("base__clone_url TEXT,");
		stringToCreateTable.append("base__svn_url TEXT,");
		stringToCreateTable.append("base__homepage TEXT,");
		stringToCreateTable.append("base__size BIGINT,");
		stringToCreateTable.append("base__stargazers_count BIGINT,");
		stringToCreateTable.append("base__watchers_count BIGINT,");
		stringToCreateTable.append("base__language TEXT,");
		stringToCreateTable.append("base__has_issues BOOLEAN,");
		stringToCreateTable.append("base__has_downloads BOOLEAN,");
		stringToCreateTable.append("base__has_wiki BOOLEAN,");
		stringToCreateTable.append("base__has_pages BOOLEAN,");
		stringToCreateTable.append("base__forks_count BIGINT,");
		stringToCreateTable.append("base__mirror_url TEXT,");
		stringToCreateTable.append("base__open_issues_count BIGINT,");
		stringToCreateTable.append("base__forks BIGINT,");
		stringToCreateTable.append("base__open_issues BIGINT,");
		stringToCreateTable.append("base__watchers BIGINT,");
		stringToCreateTable.append("base__default_branch TEXT,");
		stringToCreateTable.append("links__self__href TEXT,");
		stringToCreateTable.append("links__html__href TEXT,");
		stringToCreateTable.append("links__issue__href TEXT,");
		stringToCreateTable.append("links__comments__href TEXT,");
		stringToCreateTable.append("links__review_comments__href TEXT,");
		stringToCreateTable.append("links__review_comment__href TEXT,");
		stringToCreateTable.append("links__commits__href TEXT,");
		stringToCreateTable.append("links__statuses__href TEXT,");
		stringToCreateTable.append("merged BOOLEAN,");
		stringToCreateTable.append("mergeable TEXT,");
		stringToCreateTable.append("mergeable_state TEXT,");
		stringToCreateTable.append("merged_by__login TEXT,");
		stringToCreateTable.append("merged_by__id BIGINT,");
		stringToCreateTable.append("merged_by__avatar_url TEXT,");
		stringToCreateTable.append("merged_by__gravatar_id TEXT,");
		stringToCreateTable.append("merged_by__url TEXT,");
		stringToCreateTable.append("merged_by__html_url TEXT,");
		stringToCreateTable.append("merged_by__followers_url TEXT,");
		stringToCreateTable.append("merged_by__following_url TEXT,");
		stringToCreateTable.append("merged_by__gists_url TEXT,");
		stringToCreateTable.append("merged_by__starred_url TEXT,");
		stringToCreateTable.append("merged_by__subscriptions_url TEXT,");
		stringToCreateTable.append("merged_by__organizations_url TEXT,");
		stringToCreateTable.append("merged_by__repos_url TEXT,");
		stringToCreateTable.append("merged_by__events_url TEXT,");
		stringToCreateTable.append("merged_by__received_events_url TEXT,");
		stringToCreateTable.append("merged_by__type TEXT,");
		stringToCreateTable.append("merged_by__site_admin BOOLEAN,");
		stringToCreateTable.append("comments BIGINT,");
		stringToCreateTable.append("review_comments BIGINT,");
		stringToCreateTable.append("maintainer_can_modify BOOLEAN,");
		stringToCreateTable.append("commits BIGINT,");
		stringToCreateTable.append("additions BIGINT,");
		stringToCreateTable.append("deletions BIGINT,");
		stringToCreateTable.append("changed_files BIGINT,");
		stringToCreateTable.append("owner TEXT,");
		stringToCreateTable.append("repo TEXT)ENGINE = MyISAM;");
			System.out.println("Create table statement looks like this:"+stringToCreateTable);
			DatabaseConnections.createTable(stringToCreateTable.toString());
			return true;
	}
	/**
	 * Method to insert values into the tables
	 * @param githubJson
	 * @return insertion success or not
	 */
	public boolean insertValueIntoTable(GithubJsonSchemaVO githubJson) {
		String returnString= "url,id,html_url,diff_url,patch_url,issue_url,num,state,locked,title";
		returnString += ",user__login,user__id,user__avatar_url,user__gravatar_id,user__url,user__html_url,user__followers_url,user__following_url"
				+ ",user__gists_url,user__starred_url,user__subscriptions_url,user__organizations_url,user__repos_url,user__events_url"
				+ ",user__received_events_url,user__type,user__site_admin";
		returnString +=",body,created_at,updated_at,closed_at,merged_at,merge_commit";
		returnString += ",assignee__login,assignee__id,assignee__avatar_url,assignee__gravatar_id,assignee__url,assignee__html_url,assignee__followers_url,assignee__following_url"
				+ ",assignee__gists_url,assignee__starred_url,assignee__subscriptions_url,assignee__organizations_url,assignee__repos_url,assignee__events_url"
				+ ",assignee__received_events_url,assignee__type,assignee__site_admin";
		returnString += ",assignees,";
		returnString += "milestone__url,milestone__html_url"
				+ ",milestone__labels_url,milestone__id"
				+ ",milestone__number,milestone__title,milestone__description";
		returnString += ",milestone__creator__login,milestone__creator__id,milestone__creator__avatar_url," +
				"milestone__creator__gravatar_id,milestone__creator__url,milestone__creator__html_url," +
				"milestone__creator__followers_url,milestone__creator__following_url"
				+ ",milestone__creator__gists_url,milestone__creator__starred_url,milestone__creator__subscriptions_url,milestone__creator__organizations_url,milestone__creator__repos_url,milestone__creator__events_url"
				+ ",milestone__creator__received_events_url,milestone__creator__type,milestone__creator__site_admin";
		returnString+= ",milestone__open_issues,milestone__closed_issues,milestone__state,milestone__created_at,milestone__updated_at,milestone__due_on,milestone__closed_at";
		returnString += ",commits_url,review_comments_url,review_comment_url,comments_url,statuses_url";
		returnString += ",head__label,head__ref,head__sha";
		returnString += ",head__user__login,head__user__id,head__user__avatar_url,head__user__gravatar_id,head__user__url,head__user__html_url,head__user__followers_url,head__user__following_url"
				+ ",head__user__gists_url,head__user__starred_url,head__user__subscriptions_url,head__user__organizations_url,head__user__repos_url,head__user__events_url"
				+ ",head__user__received_events_url,head__user__type,head__user__site_admin";
		returnString += ",head__repo__id,head__repo__name,head__repo__fullname";
		returnString += ",head__repo__owner__login,head__repo__owner__id,head__repo__owner__avatar_url,head__repo__owner__gravatar_id,head__repo__owner__url,head__repo__owner__html_url,head__repo__owner__followers_url,head__repo__owner__following_url"
				+ ",head__repo__owner__gists_url,head__repo__owner__starred_url,head__repo__owner__subscriptions_url,head__repo__owner__organizations_url,head__repo__owner__repos_url,head__repo__owner__events_url"
				+ ",head__repo__owner__received_events_url,head__repo__owner__type,head__repo__owner__site_admin";
		returnString += ",head__private, head__html_url,head__description,head__fork,head__url"
		+ ",head__forks_url,head__keys_url,head__collaborators_url"
		+ ",head__teams_url,head__hooks_url,head__issue_events_url"
		+ ",head__events_url,head__assignees_url,head__branches_url,head__tags_url"
		+ ",head__blobs_url,head__git_tags_url,head__git_refs_url,head__trees_url"
		+ ",head__statuses_url,head__languages_url,head__stargazers_url"
		+ ",head__contributors_url,head__subscribers_url"
		+ ",head__subscription_url,head__commits_url,head__git_commits_url"
		+ ",head__comments_url,head__contents_url,head__compare_url"
		+ ",head__merges_url,head__downloads_url,head__issues_url"
		+ ",head__pulls_url,head__milestones_url,head__notification_url"
		+ ",head__labels_url,head__releases_url,head__deployments_url"
		+ ",head__created_at,head__pushed_at,head__ssh_url,head__clone_url,head__svn_url"
		+ ",head__homepage,head__size,head__stargazers_count"
		+ ",head__watchers_count,head__language,head__has_issues"
		+ ",head__has_downloads,head__has_wiki,head__has_pages,head__forks_count"
		+ ",head__mirror_url,head__open_issues_count,head__forks,head__open_issues"
		+ ",head__watchers,head__default_branch";
		returnString += ",base__label,base__ref,base__sha";
		returnString += ",base__user__login,base__user__id,base__user__avatar_url,base__user__gravatar_id,base__user__url,base__user__html_url,base__user__followers_url,base__user__following_url"
				+ ",base__user__gists_url,base__user__starred_url,base__user__subscriptions_url,base__user__organizations_url,base__user__repos_url,base__user__events_url"
				+ ",base__user__received_events_url,base__user__type,base__user__site_admin";
		returnString += ",base__repo__id,base__repo__name,base__repo__fullname";
		returnString += ",base__repo__owner__login,base__repo__owner__id,base__repo__owner__avatar_url,base__repo__owner__gravatar_id,base__repo__owner__url,base__repo__owner__html_url,base__repo__owner__followers_url,base__repo__owner__following_url"
				+ ",base__repo__owner__gists_url,base__repo__owner__starred_url,base__repo__owner__subscriptions_url,base__repo__owner__organizations_url,base__repo__owner__repos_url,base__repo__owner__events_url"
				+ ",base__repo__owner__received_events_url,base__repo__owner__type,base__repo__owner__site_admin";
		returnString += ",base__private, base__html_url,base__description,base__fork,base__url"
		+ ",base__forks_url,base__keys_url,base__collaborators_url"
		+ ",base__teams_url,base__hooks_url,base__issue_events_url"
		+ ",base__events_url,base__assignees_url,base__branches_url,base__tags_url"
		+ ",base__blobs_url,base__git_tags_url,base__git_refs_url,base__trees_url"
		+ ",base__statuses_url,base__languages_url,base__stargazers_url"
		+ ",base__contributors_url,base__subscribers_url"
		+ ",base__subscription_url,base__commits_url,base__git_commits_url"
		+ ",base__comments_url,base__contents_url,base__compare_url"
		+ ",base__merges_url,base__downloads_url,base__issues_url"
		+ ",base__pulls_url,base__milestones_url,base__notification_url"
		+ ",base__labels_url,base__releases_url,base__deployments_url"
		+ ",base__created_at,base__pushed_at,base__ssh_url,base__clone_url,base__svn_url"
		+ ",base__homepage,base__size,base__stargazers_count"
		+ ",base__watchers_count,base__language,base__has_issues"
		+ ",base__has_downloads,base__has_wiki,base__has_pages,base__forks_count"
		+ ",base__mirror_url,base__open_issues_count,base__forks,base__open_issues"
		+ ",base__watchers,base__default_branch";
		returnString += ",links__self__href,links__html__href,links__issue__href,links__comments__href,links__review_comments__href,links__review_comment__href,links__commits__href,links__statuses__href";
		returnString += ",merged,mergeable,mergeable_state";
		returnString += ",merged_by__login,merged_by__id,merged_by__avatar_url,merged_by__gravatar_id,merged_by__url,merged_by__html_url,merged_by__followers_url,merged_by__following_url"
				+ ",merged_by__gists_url,merged_by__starred_url,merged_by__subscriptions_url,merged_by__organizations_url,merged_by__repos_url,merged_by__events_url"
				+ ",merged_by__received_events_url,merged_by__type,merged_by__site_admin";
		returnString += ",comments,review_comments,maintainer_can_modify,commits,additions,deletions,changed_files,owner,repo";
		if(githubJson != null){
			String insertionStatement = "INSERT INTO FLAT_TABLE(";
			StringBuilder insertingString = new StringBuilder(insertionStatement);
			/*Variables list*/
			insertingString.append(returnString);
			insertingString.append(") VALUES (");
			insertingString.append(githubJson.toString()+")");
			String insertingFinal = insertingString.toString();
			String seqToMatch = "\'\',";
			String seqToChange = "null,";
			insertingFinal.replaceAll(seqToMatch, seqToChange);
			System.out.println("INSERT STATEMENT LOOKS LIKE :\t"+insertingString);
			DatabaseConnections.insertIntoTable(insertingFinal);
			return true;
		}
		return false;
	}
	
	/**
	 * Method to obtain columnInfo
	 */
	public Map<String,String> columnInfo(){
		Map<String,String> columnNames = DatabaseConnections.getMetaDataFlatTable();
		if(columnNames != null && columnNames.size() != 0){
			System.out.println("Count of columns in flat table:"+columnNames.size());
		}
		return columnNames;
	}
	/**
	 * Method to populate the final GraphMap dependencing on there structure in json
	 */
	public Map<String,LinkedHashSet<String>> populateFinalGraphMap(Map<String,LinkedHashSet<String>>graphMap,Map<String,String> columnInfo){
		for(Map.Entry<String, String> entrySet:columnInfo.entrySet()){
			String[] splitingColumnName = entrySet.getKey().split("__");
			if(splitingColumnName.length == 1){
				LinkedHashSet<String> conString = graphMap.get("pullrequest");
				conString.add(entrySet.getKey());
			}
			else if(splitingColumnName.length == 2){
				LinkedHashSet<String> conString = graphMap.get(splitingColumnName[0]);
				conString.add(entrySet.getKey());			
			}else if(splitingColumnName.length == 3){
				if(splitingColumnName[0].equalsIgnoreCase("links")){
					LinkedHashSet<String> conString = graphMap.get(splitingColumnName[0]);
					conString.add(entrySet.getKey());
				}else{
					LinkedHashSet<String> conString = graphMap.get(splitingColumnName[0]);
					conString.add(entrySet.getKey());
				}
			}else if(splitingColumnName.length == 4){
				if(splitingColumnName[2].equalsIgnoreCase("owner")){
					LinkedHashSet<String> conString = graphMap.get(splitingColumnName[0]);
					conString.add(entrySet.getKey());
				}
			}
		}
		return graphMap;
	}
	/**
	 * Method to obtain the outermost objects for now
	 */
	public void dependencyOnHeadBaseObjects(Map<String,LinkedHashSet<String>> graphMap,Map<String,String> columnNames){
		//First get all the columns from the flat table.
		double thresholdValue = 0.99;
		String root = "id";
		String typeForDependencyChecking = "BIGINT";
		//Set<String> setOfValueTypes= (Set)columnNames.values();
		for(Map.Entry<String, String> mapEntry:columnNames.entrySet()){
			//Optimized Phase 1
			//totalCountFD = totalCountFD+1;
			String checkingDependencyFor = mapEntry.getKey();
			//to obtain unique count
			double uniqueCount = DatabaseConnections.getUniqueCountForColumn(checkingDependencyFor);
			if(!checkingDependencyFor.equals(root)){
				String variableTypeForCheck = mapEntry.getValue();
				//get unique Count for on which dependency is checking
				String dependencyOn = mapEntry.getKey();
				String[] splitingColumnName = checkingDependencyFor.split("__");
				//If there is any child JSON primary key
				if(splitingColumnName.length == 2 || splitingColumnName.length == 3){
					String subRoot;
					if(splitingColumnName[0].equals("head") || splitingColumnName[0].equals("base")){
						subRoot = splitingColumnName[0]+"__label";
						if(!subRoot.equals(checkingDependencyFor)){
							double modalSubCount = DatabaseConnections.getModalValueCount(subRoot,checkingDependencyFor,variableTypeForCheck);
							double uniqueSubPairCount = DatabaseConnections.getUniquePairCount(checkingDependencyFor,root);
							//Soft dependency Formula
							if((uniqueSubPairCount - modalSubCount)!=0){
								//step 1
								double StrengthWithSub = (uniqueCount - modalSubCount)/(uniqueSubPairCount - modalSubCount);
								//step 2
								if(StrengthWithSub > thresholdValue){
									totalCountFD = totalCountFD + 1;
									double densityOfa1 = DatabaseConnections.densityOfColumn(subRoot, typeForDependencyChecking);
									double densityOfa2 = DatabaseConnections.densityOfColumn(dependencyOn, variableTypeForCheck);
									//step 3
									if(densityOfa1 >= densityOfa2){
										if(splitingColumnName[0].equals("head")){
											LinkedHashSet<String> rootList = graphMap.get("head");
											rootList.add(checkingDependencyFor);
										}else if(splitingColumnName[0].equals("base")){
											LinkedHashSet<String> rootList = graphMap.get("base");
											rootList.add(checkingDependencyFor);
										}
									}
								}
							}
						}
					}			
				}
			}
		}
	}
	/**
	 * Method to work check dependency on inner objects
	 */
	public void dependencyOnMilestoneLinkObjects(Map<String,LinkedHashSet<String>> graphMap,Map<String,String> columnNames){
		//First get all the columns from the flat table.
		double thresholdValue = 0.99;
		String root = "id";
		String typeForDependencyChecking = "BIGINT";
		//Set<String> setOfValueTypes= (Set)columnNames.values();
		for(Map.Entry<String, String> mapEntry:columnNames.entrySet()){
			//totalCountFD = totalCountFD+1;
			//Optimized Phase 1
			String checkingDependencyFor = mapEntry.getKey();
			//to obtain unique count
			double uniqueCount = DatabaseConnections.getUniqueCountForColumn(checkingDependencyFor);
			String variableTypeForCheck = mapEntry.getValue();
			//get unique Count for on which dependency is checking
			String dependencyOn = mapEntry.getKey();
			String[] splitingColumnName = checkingDependencyFor.split("__");
			//If there is any child JSON primary key
			if(splitingColumnName.length == 2 || splitingColumnName.length == 3){
				if(splitingColumnName[0].equals("links")){
					LinkedHashSet<String> linkMap = graphMap.get("links");
					linkMap.add(checkingDependencyFor);
				}
				if(splitingColumnName[0].equals("milestone")){
					String subRoot = splitingColumnName[0]+"__id";
					if(!checkingDependencyFor.equals(root)){
						double modalSubCount = DatabaseConnections.getModalValueCount(subRoot,checkingDependencyFor,variableTypeForCheck);
						double uniqueSubPairCount = DatabaseConnections.getUniquePairCount(checkingDependencyFor,root);
						//Soft dependency Formula
						if((uniqueSubPairCount - modalSubCount)!=0){
							//step 1
							double StrengthWithSub = (uniqueCount - modalSubCount)/(uniqueSubPairCount - modalSubCount);
							//step 2
							if(StrengthWithSub > thresholdValue){
								totalCountFD = totalCountFD + 1;
								double densityOfa1 = DatabaseConnections.densityOfColumn(subRoot, typeForDependencyChecking);
								double densityOfa2 = DatabaseConnections.densityOfColumn(dependencyOn, variableTypeForCheck);
								//step 3
								if(densityOfa1 >= densityOfa2){
									if(splitingColumnName[0].equals("milestone")){
										LinkedHashSet<String> rootList = graphMap.get("milestone");
										rootList.add(checkingDependencyFor);
									}
								}
							}
						}
					}
				}			
			}
		}
	}
	/**
	 * Method to object user objects
	 */
	public void dependencyUserTypeObjects(Map<String,LinkedHashSet<String>> graphMap,Map<String,String> columnNames){
		//First get all the columns from the flat table.
		double thresholdValue = 0.99;
		String root = "id";
		String typeForDependencyChecking = "BIGINT";
		//Set<String> setOfValueTypes= (Set)columnNames.values();
		for(Map.Entry<String, String> mapEntry:columnNames.entrySet()){
			//totalCountFD = totalCountFD+1;
			//Optimized Phase 1
			String checkingDependencyFor = mapEntry.getKey();
			//to obtain unique count
			double uniqueCount = DatabaseConnections.getUniqueCountForColumn(checkingDependencyFor);
			if(!checkingDependencyFor.equals(root)){
				String variableTypeForCheck = mapEntry.getValue();
				//get unique Count for on which dependency is checking
				String dependencyOn = mapEntry.getKey();
				String[] splitingColumnName = checkingDependencyFor.split("__");
				//If there is any child JSON primary key
				if(splitingColumnName.length == 2 || splitingColumnName.length == 3){
					if(splitingColumnName[0].equals("user") || splitingColumnName[0].equals("assignee")
							|| splitingColumnName[0].equals("merged_by")){
						String subRoot = splitingColumnName[0]+"__id";
						if(!checkingDependencyFor.equals(root)){
							double modalSubCount = DatabaseConnections.getModalValueCount(subRoot,checkingDependencyFor,variableTypeForCheck);
							double uniqueSubPairCount = DatabaseConnections.getUniquePairCount(checkingDependencyFor,root);
							//Soft dependency Formula
							if((uniqueSubPairCount - modalSubCount)!=0){
								//step 1
								double StrengthWithSub = (uniqueCount - modalSubCount)/(uniqueSubPairCount - modalSubCount);
								//step 2
								if(StrengthWithSub > thresholdValue){
									totalCountFD = totalCountFD + 1;
									double densityOfa1 = DatabaseConnections.densityOfColumn(subRoot, typeForDependencyChecking);
									double densityOfa2 = DatabaseConnections.densityOfColumn(dependencyOn, variableTypeForCheck);
									//step 3
									if(densityOfa1 >= densityOfa2){
										if(splitingColumnName[0].equals("user")){
											LinkedHashSet<String> rootList = graphMap.get("user");
											rootList.add(checkingDependencyFor);
										}else if(splitingColumnName[0].equals("assignee")){
											LinkedHashSet<String> rootList = graphMap.get("assignee");
											rootList.add(checkingDependencyFor);
										}else if(splitingColumnName[0].equals("merged_by")){
											LinkedHashSet<String> rootList = graphMap.get("merged_by");
											rootList.add(checkingDependencyFor);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	/**
	 * Method for root pull request respective attributes
	 */
	public void dependencyForRootObjects(Map<String,LinkedHashSet<String>> graphMap,Map<String,String> columnNames){
		//First get all the columns from the flat table.
		double thresholdValue = 0.99;
		String root = "id";
		String typeForDependencyChecking = "BIGINT";
		//Set<String> setOfValueTypes= (Set)columnNames.values();
		for(Map.Entry<String, String> mapEntry:columnNames.entrySet()){
			//Optimized Phase 1
			String checkingDependencyFor = mapEntry.getKey();
			//to obtain unique count
			double uniqueCount = DatabaseConnections.getUniqueCountForColumn(checkingDependencyFor);
			if(!checkingDependencyFor.equals(root)){
					String variableTypeForCheck = mapEntry.getValue();
					//get Modal values for the variable on which dependency is being checked.
					double modalCount = DatabaseConnections.getModalValueCount(root,checkingDependencyFor,variableTypeForCheck);
					//get unique Count for on which dependency is checking
					//totalCountFD = totalCountFD+1;
					String dependencyOn = mapEntry.getKey();
					double uniquePairCount = DatabaseConnections.getUniquePairCount(checkingDependencyFor,root);
					//Soft dependency Formula
					if((uniquePairCount - modalCount)!=0){
						//step 1
						double Strength = (uniqueCount - modalCount)/(uniquePairCount - modalCount);
						//step 2
						if(Strength >= thresholdValue){
							totalCountFD = totalCountFD + 1;
							double densityOfa1 = DatabaseConnections.densityOfColumn(root, typeForDependencyChecking);
							double densityOfa2 = DatabaseConnections.densityOfColumn(dependencyOn, variableTypeForCheck);
							//step 3
							if(densityOfa1 >= densityOfa2){
								//We can say dependency exist between a1 to a2
								if(!graphMap.containsKey(checkingDependencyFor)){
									LinkedHashSet<String> rootList = graphMap.get("pullrequest");
									rootList.add(checkingDependencyFor);
								}
							}
						}
					}
				//}
			}
		}
	}
	/**
	 * Method to construct Directed Acyclic Graph and going objects into trees with group of subtree attributes
	 */
	public void constructingDAGraphFromAttributeDependency(Map<String,ArrayList<String>> finalGraphMap){
		//Since this is NP hard Problem we went with the longestPath Concept
		//Any attribute if its present other than root then we considered that to go into that.
		ArrayList<String> rootList = finalGraphMap.get("pullrequest");
		for (Map.Entry<String,ArrayList<String>> entry : finalGraphMap.entrySet()) {
			if(!entry.getKey().equals("pullrequest")){
				for(String attribute:entry.getValue()){ 
					if(rootList.contains(attribute)){
						rootList.remove(attribute);
					}
				}
			}
		}
	}
	/**
	 * Method which executes the steps that we follow in phase 1
	 */
	public void phase1ExecutionSequence(){
		Map<String,String> columnNames = columnInfo();
		Map<String,ArrayList<String>> finalGraphMap = new LinkedHashMap<String,ArrayList<String>>();
		Map<String,LinkedHashSet<String>> finalGraphMap1 = new LinkedHashMap<String,LinkedHashSet<String>>();
		finalGraphMap1.put("pullrequest",new LinkedHashSet<String>());
		finalGraphMap1.put("user",new LinkedHashSet<String>());
		finalGraphMap1.put("assignee",new LinkedHashSet<String>());
		finalGraphMap1.put("head",new LinkedHashSet<String>());
		finalGraphMap1.put("base",new LinkedHashSet<String>());
		finalGraphMap1.put("links",new LinkedHashSet<String>());
		finalGraphMap1.put("milestone", new LinkedHashSet<String>());
		finalGraphMap1.put("merged_by",new LinkedHashSet<String>());
		//Dependency Check for RootObject
		dependencyForRootObjects(finalGraphMap1,columnNames);
		//Dependency Check for HeadBaseObject
		dependencyOnHeadBaseObjects(finalGraphMap1,columnNames);
		//Dependency Check for MilestoneObject
		dependencyOnMilestoneLinkObjects(finalGraphMap1,columnNames);
		//Dependency Check for UserObject
		dependencyUserTypeObjects(finalGraphMap1,columnNames);
		//Populate final graph map
		populateFinalGraphMap(finalGraphMap1, columnNames);
		for(Map.Entry<String, LinkedHashSet<String>> graphs:finalGraphMap1.entrySet()){
			finalGraphMap.put(graphs.getKey(), new ArrayList<String>(graphs.getValue()));
		}
		//We construct graph out of functional dependencies and group within the attributes
		constructingDAGraphFromAttributeDependency(finalGraphMap);
	}
	/**
	 * Method to provide the Count for FunctionalDependency using this modified method
	 * @param columnNames
	 */
	public void countFunctionalDependency(Map<String, String> columnNames) {
		double thresholdValue = 0.99;
		double totalCountFD = 0;
		Map<String,ArrayList<String>> functionalDependencies = new LinkedHashMap<String,ArrayList<String>>();
		for(String column:columnNames.keySet()){
			//Optimized Phase 1
			String checkingDependencyFor = column;
			String[] checkingDependencyForArray = column.split("__");
			if((checkingDependencyForArray.length == 1 && checkingDependencyFor.equals("id"))
					|| checkingDependencyForArray[checkingDependencyForArray.length-1].equals("id")
					|| (checkingDependencyForArray[0].equals("head") 
							&& checkingDependencyForArray.length ==4 
							&& checkingDependencyForArray[checkingDependencyForArray.length-1].equals("id"))
					|| (checkingDependencyForArray[0].equals("head") 
							&& checkingDependencyForArray.length == 3 
							&& checkingDependencyForArray[checkingDependencyForArray.length-1].equals("id"))
					|| (checkingDependencyForArray[0].equals("base")
							&&checkingDependencyForArray.length ==4 
							&& checkingDependencyForArray[checkingDependencyForArray.length-1].equals("id"))
					|| (checkingDependencyForArray[0].equals("base")
							&&checkingDependencyForArray.length ==3 
							&& checkingDependencyForArray[checkingDependencyForArray.length-1].equals("id"))
					|| checkingDependencyForArray[0].equals("links")
					|| (checkingDependencyForArray[0].equals("milestone") 
							&& checkingDependencyForArray.length ==3
							&& checkingDependencyForArray[checkingDependencyForArray.length-1].equals("id"))){
				String checkingAttributeColumnType = columnNames.get(column);
				//to obtain unique count
				double uniqueCount = DatabaseConnections.getUniqueCountForColumn(checkingDependencyFor);
				for(String dependColumn:columnNames.keySet()){
					if(!dependColumn.equals(checkingDependencyFor)){
						String variableTypeForCheck = columnNames.get(dependColumn);
						//get Modal values for the variable on which dependency is being checked.
						double modalCount = DatabaseConnections.getModalValueCount(checkingDependencyFor,dependColumn,variableTypeForCheck);
						//get unique Count for on which dependency is checking
						double uniquePairCount = DatabaseConnections.getUniquePairCount(checkingDependencyFor,dependColumn);
						//Soft dependency Formula
						if((uniquePairCount - modalCount)!=0){
							//step 1
							double Strength = (uniqueCount - modalCount)/(uniquePairCount - modalCount);
							//step 2
							if(Strength > thresholdValue){
								double densityOfa1 = DatabaseConnections.densityOfColumn(checkingDependencyFor, checkingAttributeColumnType);
								double densityOfa2 = DatabaseConnections.densityOfColumn(dependColumn, variableTypeForCheck);
								//step 3
								if(densityOfa1 >= densityOfa2){
									totalCountFD = totalCountFD + 1;
									//We can say dependency exist between a1 to a2
									if(functionalDependencies.containsKey(checkingDependencyFor)){
										ArrayList<String> rootList = functionalDependencies.get(checkingDependencyFor);
										rootList.add(dependColumn);
									}else{
										ArrayList<String> listOfDependency = new ArrayList<String>();
										listOfDependency.add(dependColumn);
										functionalDependencies.put(checkingDependencyFor,listOfDependency);
									}
								}
							}
						}
					}
				}
			}
		}
		System.out.println("Total Count FD is : "+totalCountFD);
	}
}