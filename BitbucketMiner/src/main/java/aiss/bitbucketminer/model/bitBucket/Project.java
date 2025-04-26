package aiss.bitbucketminer.model.bitBucket;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)


public class Project {

    @JsonProperty("Project")
    private Project__1 project;
    @JsonProperty("Commit")
    private Commit commit;
    @JsonProperty("Issue")
    private Issue issue;
    @JsonProperty("Comment")
    private Comment comment;
    @JsonProperty("User")
    private User user;

    @JsonProperty("Project")
    public Project__1 getProject() {
        return project;
    }

    @JsonProperty("Project")
    public void setProject(Project__1 project) {
        this.project = project;
    }

    @JsonProperty("Commit")
    public Commit getCommit() {
        return commit;
    }

    @JsonProperty("Commit")
    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    @JsonProperty("Issue")
    public Issue getIssue() {
        return issue;
    }

    @JsonProperty("Issue")
    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    @JsonProperty("Comment")
    public Comment getComment() {
        return comment;
    }

    @JsonProperty("Comment")
    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @JsonProperty("User")
    public User getUser() {
        return user;
    }

    @JsonProperty("User")
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Project.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("project");
        sb.append('=');
        sb.append(((this.project == null)?"<null>":this.project));
        sb.append(',');
        sb.append("commit");
        sb.append('=');
        sb.append(((this.commit == null)?"<null>":this.commit));
        sb.append(',');
        sb.append("issue");
        sb.append('=');
        sb.append(((this.issue == null)?"<null>":this.issue));
        sb.append(',');
        sb.append("comment");
        sb.append('=');
        sb.append(((this.comment == null)?"<null>":this.comment));
        sb.append(',');
        sb.append("user");
        sb.append('=');
        sb.append(((this.user == null)?"<null>":this.user));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
