package aiss.bitbucketminer.model.bitBucket.issue;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Links {

    @JsonProperty("self")
    private Self self;
    @JsonProperty("html")
    private HtmlLink html;
    @JsonProperty("comments")
    private Comments comments;
    @JsonProperty("attachments")
    private Attachments attachments;
    @JsonProperty("watch")
    private Watch watch;
    @JsonProperty("vote")
    private Vote vote;

    @JsonProperty("self")
    public Self getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(Self self) {
        this.self = self;
    }

    @JsonProperty("html")
    public HtmlLink getHtml() {
        return html;
    }

    @JsonProperty("html")
    public void setHtml(HtmlLink html) {
        this.html = html;
    }

    @JsonProperty("comments")
    public Comments getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(Comments comments) {
        this.comments = comments;
    }

    @JsonProperty("attachments")
    public Attachments getAttachments() {
        return attachments;
    }

    @JsonProperty("attachments")
    public void setAttachments(Attachments attachments) {
        this.attachments = attachments;
    }

    @JsonProperty("watch")
    public Watch getWatch() {
        return watch;
    }

    @JsonProperty("watch")
    public void setWatch(Watch watch) {
        this.watch = watch;
    }

    @JsonProperty("vote")
    public Vote getVote() {
        return vote;
    }

    @JsonProperty("vote")
    public void setVote(Vote vote) {
        this.vote = vote;
    }

    /*@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Links.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("self");
        sb.append('=');
        sb.append(((this.self == null)?"<null>":this.self));
        sb.append(',');
        sb.append("html");
        sb.append('=');
        sb.append(((this.html == null)?"<null>":this.html));
        sb.append(',');
        sb.append("comments");
        sb.append('=');
        sb.append(((this.comments == null)?"<null>":this.comments));
        sb.append(',');
        sb.append("attachments");
        sb.append('=');
        sb.append(((this.attachments == null)?"<null>":this.attachments));
        sb.append(',');
        sb.append("watch");
        sb.append('=');
        sb.append(((this.watch == null)?"<null>":this.watch));
        sb.append(',');
        sb.append("vote");
        sb.append('=');
        sb.append(((this.vote == null)?"<null>":this.vote));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }*/
    @Override
    public String toString() {
        return "Links {\n" +
                "  self: " + (self != null ? self : "null") + ",\n" +
                "  html: " + (html != null ? html : "null") + ",\n" +
                "  comments: " + (comments != null ? comments : "null") + ",\n" +
                "  attachments: " + (attachments != null ? attachments : "null") + ",\n" +
                "  watch: " + (watch != null ? watch : "null") + ",\n" +
                "  vote: " + (vote != null ? vote : "null") + "\n" +
                "}";
    }


}
