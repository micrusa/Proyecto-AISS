package aiss.bitbucketminer.model.bitBucket.issue;


import aiss.bitbucketminer.model.bitBucket.issue.Issue__1;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Issue {

    @JsonProperty("Issue")
    private Issue__1 issue;

    @JsonProperty("Issue")
    public Issue__1 getIssue() {
        return issue;
    }

    @JsonProperty("Issue")
    public void setIssue(Issue__1 issue) {
        this.issue = issue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Issue.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("issue");
        sb.append('=');
        sb.append(((this.issue == null)?"<null>":this.issue));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
