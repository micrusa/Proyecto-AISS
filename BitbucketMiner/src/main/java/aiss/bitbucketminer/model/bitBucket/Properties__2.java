package aiss.bitbucketminer.model.bitBucket;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)


public class Properties__2 {

    @JsonProperty("id")
    private Id__2 id;
    @JsonProperty("title")
    private Title__1 title;
    @JsonProperty("description")
    private Description description;
    @JsonProperty("state")
    private State state;
    @JsonProperty("created_at")
    private CreatedAt createdAt;
    @JsonProperty("updated_at")
    private UpdatedAt updatedAt;
    @JsonProperty("closed_at")
    private ClosedAt closedAt;
    @JsonProperty("labels")
    private Labels labels;
    @JsonProperty("votes")
    private Votes votes;

    @JsonProperty("id")
    public Id__2 getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Id__2 id) {
        this.id = id;
    }

    @JsonProperty("title")
    public Title__1 getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(Title__1 title) {
        this.title = title;
    }

    @JsonProperty("description")
    public Description getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(Description description) {
        this.description = description;
    }

    @JsonProperty("state")
    public State getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(State state) {
        this.state = state;
    }

    @JsonProperty("created_at")
    public CreatedAt getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(CreatedAt createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public UpdatedAt getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(UpdatedAt updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("closed_at")
    public ClosedAt getClosedAt() {
        return closedAt;
    }

    @JsonProperty("closed_at")
    public void setClosedAt(ClosedAt closedAt) {
        this.closedAt = closedAt;
    }

    @JsonProperty("labels")
    public Labels getLabels() {
        return labels;
    }

    @JsonProperty("labels")
    public void setLabels(Labels labels) {
        this.labels = labels;
    }

    @JsonProperty("votes")
    public Votes getVotes() {
        return votes;
    }

    @JsonProperty("votes")
    public void setVotes(Votes votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Properties__2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null)?"<null>":this.title));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("state");
        sb.append('=');
        sb.append(((this.state == null)?"<null>":this.state));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.createdAt == null)?"<null>":this.createdAt));
        sb.append(',');
        sb.append("updatedAt");
        sb.append('=');
        sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
        sb.append(',');
        sb.append("closedAt");
        sb.append('=');
        sb.append(((this.closedAt == null)?"<null>":this.closedAt));
        sb.append(',');
        sb.append("labels");
        sb.append('=');
        sb.append(((this.labels == null)?"<null>":this.labels));
        sb.append(',');
        sb.append("votes");
        sb.append('=');
        sb.append(((this.votes == null)?"<null>":this.votes));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
