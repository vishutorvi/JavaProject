package com.uttara.vo;

public class LinkVO{
	
	private LinkRefVO self;
	private LinkRefVO html;
	private LinkRefVO issue;
	private LinkRefVO comments;
	private LinkRefVO review_comments;
	private LinkRefVO review_comment;
	private LinkRefVO commits;
	private LinkRefVO statuses;
	public LinkRefVO getSelf() {
		return self;
	}
	public void setSelf(LinkRefVO self) {
		this.self = self;
	}
	public LinkRefVO getHtml() {
		return html;
	}
	public void setHtml(LinkRefVO html) {
		this.html = html;
	}
	public LinkRefVO getIssue() {
		return issue;
	}
	public void setIssue(LinkRefVO issue) {
		this.issue = issue;
	}
	public LinkRefVO getComments() {
		return comments;
	}
	public void setComments(LinkRefVO comments) {
		this.comments = comments;
	}
	public LinkRefVO getReview_comments() {
		return review_comments;
	}
	public void setReview_comments(LinkRefVO review_comments) {
		this.review_comments = review_comments;
	}
	public LinkRefVO getReview_comment() {
		return review_comment;
	}
	public void setReview_comment(LinkRefVO review_comment) {
		this.review_comment = review_comment;
	}
	public LinkRefVO getCommits() {
		return commits;
	}
	public void setCommits(LinkRefVO commits) {
		this.commits = commits;
	}
	public LinkRefVO getStatuses() {
		return statuses;
	}
	public void setStatuses(LinkRefVO statuses) {
		this.statuses = statuses;
	}
	@Override
	public String toString() {
		return self + "," + html + "," + issue + "," + comments + ","
				+ review_comments + "," + review_comment + "," + commits + "," + statuses;
	}
	/**
	 * Method to insert null for each parameter if the object is null 
	 * @return string
	 */
	public static String nullStrings(){
		String returnString;
		returnString = "null,null,null,null,null,null,null,null";
		return returnString;
	}
}