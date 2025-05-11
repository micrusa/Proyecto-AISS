package aiss.bitbucketminer.model.bitBucket.comment;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Comment {

    @JsonProperty("type")
    private String type;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("created_on")
    private String createdOn;
    @JsonProperty("updated_on")
    private String updatedOn;
    @JsonProperty("content")
    private Content content;
    @JsonProperty("user")
    private User user;
    @JsonProperty("deleted")
    private Boolean deleted;
    @JsonProperty("inline")
    private Inline inline;
    @JsonProperty("links")
    private Links links;
    @JsonProperty("commit")
    private Issue issue;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("created_on")
    public String getCreatedOn() {
        return createdOn;
    }

    @JsonProperty("created_on")
    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    @JsonProperty("updated_on")
    public String getUpdatedOn() {
        return updatedOn;
    }

    @JsonProperty("updated_on")
    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    @JsonProperty("content")
    public Content getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(Content content) {
        this.content = content;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }

    @JsonProperty("deleted")
    public Boolean getDeleted() {
        return deleted;
    }

    @JsonProperty("deleted")
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @JsonProperty("inline")
    public Inline getInline() {
        return inline;
    }

    @JsonProperty("inline")
    public void setInline(Inline inline) {
        this.inline = inline;
    }

    @JsonProperty("links")
    public Links getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(Links links) {
        this.links = links;
    }

    @JsonProperty("commit")
    public Issue getCommit() {
        return issue;
    }

    @JsonProperty("commit")
    public void setCommit(Issue commit) {
        this.issue = commit;
    }

    public String toString() {
        return "Comment {\n" +
                "  type: \"" + (type != null ? type : "") + "\",\n" +
                "  id: " + (id != null ? id : "null") + ",\n" +
                "  createdOn: \"" + (createdOn != null ? createdOn : "") + "\",\n" +
                "  updatedOn: \"" + (updatedOn != null ? updatedOn : "") + "\",\n" +
                "  content: " + indent(content != null ? content.toString() : "null") + ",\n" +
                "  user: " + indent(user != null ? user.toString() : "null") + ",\n" +
                "  deleted: " + (deleted != null ? deleted : "null") + ",\n" +
                "  inline: " + indent(inline != null ? inline.toString() : "null") + ",\n" +
                "  links: " + indent(links != null ? links.toString() : "null") + ",\n" +
                "  commit: " + indent(issue != null ? issue.toString() : "null") + "\n" +
                "}";
    }

    private String indent(String text) {
        return text.replace("\n", "\n    ");
    }

   /* @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Comment.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("createdOn");
        sb.append('=');
        sb.append(((this.createdOn == null)?"<null>":this.createdOn));
        sb.append(',');
        sb.append("updatedOn");
        sb.append('=');
        sb.append(((this.updatedOn == null)?"<null>":this.updatedOn));
        sb.append(',');
        sb.append("content");
        sb.append('=');
        sb.append(((this.content == null)?"<null>":this.content));
        sb.append(',');
        sb.append("user");
        sb.append('=');
        sb.append(((this.user == null)?"<null>":this.user));
        sb.append(',');
        sb.append("deleted");
        sb.append('=');
        sb.append(((this.deleted == null)?"<null>":this.deleted));
        sb.append(',');
        sb.append("inline");
        sb.append('=');
        sb.append(((this.inline == null)?"<null>":this.inline));
        sb.append(',');
        sb.append("links");
        sb.append('=');
        sb.append(((this.links == null)?"<null>":this.links));
        sb.append(',');
        sb.append("commit");
        sb.append('=');
        sb.append(((this.commit == null)?"<null>":this.commit));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }*/

    /*public String toString() {
        return "Comment {\n" +
                "  type: \"" + (type != null ? type : "") + "\",\n" +
                "  id: " + (id != null ? id : "null") + ",\n" +
                "  createdOn: \"" + (createdOn != null ? createdOn : "") + "\",\n" +
                "  updatedOn: \"" + (updatedOn != null ? updatedOn : "") + "\",\n" +
                "  content: " + (content != null ? content.toString() : "null") + ",\n" +
                "  user: " + (user != null ? user.toString() : "null") + ",\n" +
                "  deleted: " + (deleted != null ? deleted : "null") + ",\n" +
                "  inline: " + (inline != null ? inline.toString() : "null") + ",\n" +
                "  links: " + (links != null ? links.toString() : "null") + ",\n" +
                "  commit: " + (commit != null ? commit.toString() : "null") + "\n" +
                "}";
    }
*/
}
