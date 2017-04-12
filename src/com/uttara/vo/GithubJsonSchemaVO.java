package com.uttara.vo;

import org.json.simple.JSONArray;

public class GithubJsonSchemaVO {
	
	public GithubJsonSchemaVO() {
		super();
		
	}
	private IdObject _id;
	private String url;
	private long id;
	private String html_url;
	private String diff_url;
	private String patch_url;
	private String issue_url;
	private long number;
	private String state;
	private boolean locked;
	private String title;
	private UserVO user;
	private String body;
	private String created_at;
	private String updated_at;
	private String closed_at;
	private String merged_at;
	private String merge_commit;
	private UserVO assignee;
	private JSONArray assignees;
	private MilestoneVO milestone;
	private String commits_url;
	private String review_comments_url;
	private String review_comment_url;
	private String comments_url;
	private String statuses_url;
	private HeadVO head;
	private HeadVO base;
	private LinkVO _links; 
	private boolean merged;
	private String mergeable;
	private String mergeable_state;
	private UserVO merged_by;
	private long comments;
	private long review_comments;
	private boolean maintainer_can_modify;
	private long commits;
	private long additions;
	private long deletions;
	private long changed_files;
	private String owner;
	private String repo;
	
	public IdObject get_id() {
		return _id;
	}
	public void set_id(IdObject _id) {
		this._id = _id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getHtml_url() {
		return html_url;
	}
	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}
	public String getDiff_url() {
		return diff_url;
	}
	public void setDiff_url(String diff_url) {
		this.diff_url = diff_url;
	}
	public String getPatch_url() {
		return patch_url;
	}
	public void setPatch_url(String patch_url) {
		this.patch_url = patch_url;
	}
	public String getIssue_url() {
		return issue_url;
	}
	public void setIssue_url(String issue_url) {
		this.issue_url = issue_url;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public UserVO getUser() {
		return user;
	}
	public void setUser(UserVO user) {
		this.user = user;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public String getClosed_at() {
		return closed_at;
	}
	public void setClosed_at(String closed_at) {
		this.closed_at = closed_at;
	}
	public String getMerged_at() {
		return merged_at;
	}
	public void setMerged_at(String merged_at) {
		this.merged_at = merged_at;
	}
	public String getMerge_commit() {
		return merge_commit;
	}
	public void setMerge_commit(String merge_commit) {
		this.merge_commit = merge_commit;
	}
	public UserVO getAssignee() {
		return assignee;
	}
	public void setAssignee(UserVO assignee) {
		this.assignee = assignee;
	}
	public JSONArray getAssignees() {
		return assignees;
	}
	public void setAssignees(JSONArray assignees) {
		this.assignees = assignees;
	}
	public MilestoneVO getMilestone() {
		return milestone;
	}
	public void setMilestone(MilestoneVO milestone) {
		this.milestone = milestone;
	}
	public String getCommits_url() {
		return commits_url;
	}
	public void setCommits_url(String commits_url) {
		this.commits_url = commits_url;
	}
	public String getReview_comments_url() {
		return review_comments_url;
	}
	public void setReview_comments_url(String review_comments_url) {
		this.review_comments_url = review_comments_url;
	}
	public String getReview_comment_url() {
		return review_comment_url;
	}
	public void setReview_comment_url(String review_comment_url) {
		this.review_comment_url = review_comment_url;
	}
	public String getComments_url() {
		return comments_url;
	}
	public void setComments_url(String comments_url) {
		this.comments_url = comments_url;
	}
	public String getStatuses_url() {
		return statuses_url;
	}
	public void setStatuses_url(String statuses_url) {
		this.statuses_url = statuses_url;
	}
	public HeadVO getHead() {
		return head;
	}
	public void setHead(HeadVO head) {
		this.head = head;
	}
	public HeadVO getBase() {
		return base;
	}
	public void setBase(HeadVO base) {
		this.base = base;
	}
	public LinkVO get_links() {
		return _links;
	}
	public void set_links(LinkVO _links) {
		this._links = _links;
	}
	public boolean isMerged() {
		return merged;
	}
	public void setMerged(boolean merged) {
		this.merged = merged;
	}
	public String getMergeable() {
		return mergeable;
	}
	public void setMergeable(String mergeable) {
		this.mergeable = mergeable;
	}
	public String getMergeable_state() {
		return mergeable_state;
	}
	public void setMergeable_state(String mergeable_state) {
		this.mergeable_state = mergeable_state;
	}
	public UserVO getMerged_by() {
		return merged_by;
	}
	public void setMerged_by(UserVO merged_by) {
		this.merged_by = merged_by;
	}
	public long getComments() {
		return comments;
	}
	public void setComments(long comments) {
		this.comments = comments;
	}
	public long getReview_comments() {
		return review_comments;
	}
	public void setReview_comments(long review_comments) {
		this.review_comments = review_comments;
	}
	public boolean isMaintainer_can_modify() {
		return maintainer_can_modify;
	}
	public void setMaintainer_can_modify(boolean maintainer_can_modify) {
		this.maintainer_can_modify = maintainer_can_modify;
	}
	public long getCommits() {
		return commits;
	}
	public void setCommits(long commits) {
		this.commits = commits;
	}
	public long getAdditions() {
		return additions;
	}
	public void setAdditions(long additions) {
		this.additions = additions;
	}
	public long getDeletions() {
		return deletions;
	}
	public void setDeletions(long deletions) {
		this.deletions = deletions;
	}
	public long getChanged_files() {
		return changed_files;
	}
	public void setChanged_files(long changed_files) {
		this.changed_files = changed_files;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getRepo() {
		return repo;
	}
	public void setRepo(String repo) {
		this.repo = repo;
	}
	@Override
	public String toString() {		
		String returnString= "\'"+url + "\'," + id + ",\'"+ html_url + "\',\'" + diff_url + "\',\'"+ patch_url + "\',\'" + issue_url + "\'," + number
				+ ",\'" + state + "\'," + locked + ",\'" + title + "\',";
		if(user != null){
			returnString += user.toString();
		}else{
			returnString += UserVO.nullStrings();
		}
		returnString +=",\'" + body + "\',\'"
				+ created_at + "\',\'" + updated_at + "\',\'"
				+ closed_at + "\',\'" + merged_at + "\',\'"+ merge_commit + "\',";
		if(assignee != null){
			returnString +=assignee.toString();
		}else{
			returnString += UserVO.nullStrings();
		}
		returnString += ","+ null + ",";
		if(milestone != null){
			returnString += milestone.toString();
		}else{
			returnString += MilestoneVO.nullStrings();
		}
		returnString += ",\'"+ commits_url + "\',\'" + review_comments_url
				+ "\',\'" + review_comment_url + "\',\'" + comments_url + "\',\'"+ statuses_url + "\',";
		if(head != null){
			returnString += head.toString();
		}else{
			returnString += HeadVO.nullStrings();
		}
		returnString += ",";
		if(base != null){
			returnString += base.toString();
		}else{
			returnString += HeadVO.nullStrings();
		}
		returnString += ",";
		if(_links != null){
			returnString += _links.toString();
		}else{
			returnString += LinkVO.nullStrings();
		}
		returnString += "," + merged + ",\'"	+ mergeable + "\',\'" + mergeable_state + "\',";
		if(merged_by != null){
			returnString += merged_by.toString();
		}else{
			returnString += UserVO.nullStrings();
		}
		returnString += "," + comments
				+ "," + review_comments	+ "," + maintainer_can_modify + "," + commits + "," + additions
				+ "," + deletions + ","	+ changed_files + ",\'" + owner + "\',\'" + repo+"\'";
		return returnString;
	}
}