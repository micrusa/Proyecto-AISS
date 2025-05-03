
package aiss.githubminer.model.github.issue;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Issue {

    @JsonProperty("url")
    private String url;
    @JsonProperty("repository_url")
    private String repositoryUrl;
    @JsonProperty("labels_url")
    private String labelsUrl;
    @JsonProperty("comments_url")
    private String commentsUrl;
    @JsonProperty("events_url")
    private String eventsUrl;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("number")
    private Integer number;
    @JsonProperty("title")
    private String title;
    @JsonProperty("user")
    private User user;
    @JsonProperty("labels")
    private List<Object> labels;
    @JsonProperty("state")
    private String state;
    @JsonProperty("locked")
    private Boolean locked;
    @JsonProperty("assignee")
    private Object assignee;
    @JsonProperty("assignees")
    private List<Object> assignees;
    @JsonProperty("milestone")
    private Object milestone;
    @JsonProperty("comments")
    private Integer comments;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("closed_at")
    private Object closedAt;
    @JsonProperty("author_association")
    private String authorAssociation;
    @JsonProperty("active_lock_reason")
    private Object activeLockReason;
    @JsonProperty("sub_issues_summary")
    private SubIssuesSummary subIssuesSummary;
    @JsonProperty("body")
    private String body;
    @JsonProperty("closed_by")
    private Object closedBy;
    @JsonProperty("reactions")
    private Reactions reactions;
    @JsonProperty("timeline_url")
    private String timelineUrl;
    @JsonProperty("performed_via_github_app")
    private Object performedViaGithubApp;
    @JsonProperty("state_reason")
    private Object stateReason;

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("repository_url")
    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    @JsonProperty("repository_url")
    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    @JsonProperty("labels_url")
    public String getLabelsUrl() {
        return labelsUrl;
    }

    @JsonProperty("labels_url")
    public void setLabelsUrl(String labelsUrl) {
        this.labelsUrl = labelsUrl;
    }

    @JsonProperty("comments_url")
    public String getCommentsUrl() {
        return commentsUrl;
    }

    @JsonProperty("comments_url")
    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    @JsonProperty("events_url")
    public String getEventsUrl() {
        return eventsUrl;
    }

    @JsonProperty("events_url")
    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    @JsonProperty("html_url")
    public String getHtmlUrl() {
        return htmlUrl;
    }

    @JsonProperty("html_url")
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("node_id")
    public String getNodeId() {
        return nodeId;
    }

    @JsonProperty("node_id")
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @JsonProperty("number")
    public Integer getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(Integer number) {
        this.number = number;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }

    @JsonProperty("labels")
    public List<Object> getLabels() {
        return labels;
    }

    @JsonProperty("labels")
    public void setLabels(List<Object> labels) {
        this.labels = labels;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("locked")
    public Boolean getLocked() {
        return locked;
    }

    @JsonProperty("locked")
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @JsonProperty("assignee")
    public Object getAssignee() {
        return assignee;
    }

    @JsonProperty("assignee")
    public void setAssignee(Object assignee) {
        this.assignee = assignee;
    }

    @JsonProperty("assignees")
    public List<Object> getAssignees() {
        return assignees;
    }

    @JsonProperty("assignees")
    public void setAssignees(List<Object> assignees) {
        this.assignees = assignees;
    }

    @JsonProperty("milestone")
    public Object getMilestone() {
        return milestone;
    }

    @JsonProperty("milestone")
    public void setMilestone(Object milestone) {
        this.milestone = milestone;
    }

    @JsonProperty("comments")
    public Integer getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(Integer comments) {
        this.comments = comments;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("closed_at")
    public Object getClosedAt() {
        return closedAt;
    }

    @JsonProperty("closed_at")
    public void setClosedAt(Object closedAt) {
        this.closedAt = closedAt;
    }

    @JsonProperty("author_association")
    public String getAuthorAssociation() {
        return authorAssociation;
    }

    @JsonProperty("author_association")
    public void setAuthorAssociation(String authorAssociation) {
        this.authorAssociation = authorAssociation;
    }

    @JsonProperty("active_lock_reason")
    public Object getActiveLockReason() {
        return activeLockReason;
    }

    @JsonProperty("active_lock_reason")
    public void setActiveLockReason(Object activeLockReason) {
        this.activeLockReason = activeLockReason;
    }

    @JsonProperty("sub_issues_summary")
    public SubIssuesSummary getSubIssuesSummary() {
        return subIssuesSummary;
    }

    @JsonProperty("sub_issues_summary")
    public void setSubIssuesSummary(SubIssuesSummary subIssuesSummary) {
        this.subIssuesSummary = subIssuesSummary;
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty("closed_by")
    public Object getClosedBy() {
        return closedBy;
    }

    @JsonProperty("closed_by")
    public void setClosedBy(Object closedBy) {
        this.closedBy = closedBy;
    }

    @JsonProperty("reactions")
    public Reactions getReactions() {
        return reactions;
    }

    @JsonProperty("reactions")
    public void setReactions(Reactions reactions) {
        this.reactions = reactions;
    }

    @JsonProperty("timeline_url")
    public String getTimelineUrl() {
        return timelineUrl;
    }

    @JsonProperty("timeline_url")
    public void setTimelineUrl(String timelineUrl) {
        this.timelineUrl = timelineUrl;
    }

    @JsonProperty("performed_via_github_app")
    public Object getPerformedViaGithubApp() {
        return performedViaGithubApp;
    }

    @JsonProperty("performed_via_github_app")
    public void setPerformedViaGithubApp(Object performedViaGithubApp) {
        this.performedViaGithubApp = performedViaGithubApp;
    }

    @JsonProperty("state_reason")
    public Object getStateReason() {
        return stateReason;
    }

    @JsonProperty("state_reason")
    public void setStateReason(Object stateReason) {
        this.stateReason = stateReason;
    }

    @Override
    public String toString() {
        return "Issue {\n" +
                "  url = " + (url == null ? "<null>" : url) + ",\n" +
                "  repositoryUrl = " + (repositoryUrl == null ? "<null>" : repositoryUrl) + ",\n" +
                "  labelsUrl = " + (labelsUrl == null ? "<null>" : labelsUrl) + ",\n" +
                "  commentsUrl = " + (commentsUrl == null ? "<null>" : commentsUrl) + ",\n" +
                "  eventsUrl = " + (eventsUrl == null ? "<null>" : eventsUrl) + ",\n" +
                "  htmlUrl = " + (htmlUrl == null ? "<null>" : htmlUrl) + ",\n" +
                "  id = " + (id == null ? "<null>" : id) + ",\n" +
                "  nodeId = " + (nodeId == null ? "<null>" : nodeId) + ",\n" +
                "  number = " + (number == null ? "<null>" : number) + ",\n" +
                "  title = " + (title == null ? "<null>" : title) + ",\n" +
                "  user = " + (user == null ? "<null>" : user) + ",\n" +
                "  labels = " + (labels == null ? "<null>" : labels) + ",\n" +
                "  state = " + (state == null ? "<null>" : state) + ",\n" +
                "  locked = " + (locked == null ? "<null>" : locked) + ",\n" +
                "  assignee = " + (assignee == null ? "<null>" : assignee) + ",\n" +
                "  assignees = " + (assignees == null ? "<null>" : assignees) + ",\n" +
                "  milestone = " + (milestone == null ? "<null>" : milestone) + ",\n" +
                "  comments = " + (comments == null ? "<null>" : comments) + ",\n" +
                "  createdAt = " + (createdAt == null ? "<null>" : createdAt) + ",\n" +
                "  updatedAt = " + (updatedAt == null ? "<null>" : updatedAt) + ",\n" +
                "  closedAt = " + (closedAt == null ? "<null>" : closedAt) + ",\n" +
                "  authorAssociation = " + (authorAssociation == null ? "<null>" : authorAssociation) + ",\n" +
                "  activeLockReason = " + (activeLockReason == null ? "<null>" : activeLockReason) + ",\n" +
                "  subIssuesSummary = " + (subIssuesSummary == null ? "<null>" : subIssuesSummary) + ",\n" +
                "  body = " + (body == null ? "<null>" : body) + ",\n" +
                "  closedBy = " + (closedBy == null ? "<null>" : closedBy) + ",\n" +
                "  reactions = " + (reactions == null ? "<null>" : reactions) + ",\n" +
                "  timelineUrl = " + (timelineUrl == null ? "<null>" : timelineUrl) + ",\n" +
                "  performedViaGithubApp = " + (performedViaGithubApp == null ? "<null>" : performedViaGithubApp) + ",\n" +
                "  stateReason = " + (stateReason == null ? "<null>" : stateReason) + "\n" +
                '}';
    }

}
