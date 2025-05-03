package aiss.bitbucketminer.model.bitBucket.commit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Links__1 {

    @JsonProperty("self")
    private Self__1 self;
    @JsonProperty("comments")
    private Comments comments;
    @JsonProperty("patch")
    private Patch patch;
    @JsonProperty("html")
    private Html__1 html;
    @JsonProperty("diff")
    private Diff diff;
    @JsonProperty("approve")
    private Approve approve;
    @JsonProperty("statuses")
    private Statuses statuses;

    @JsonProperty("self")
    public Self__1 getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(Self__1 self) {
        this.self = self;
    }

    @JsonProperty("comments")
    public Comments getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(Comments comments) {
        this.comments = comments;
    }

    @JsonProperty("patch")
    public Patch getPatch() {
        return patch;
    }

    @JsonProperty("patch")
    public void setPatch(Patch patch) {
        this.patch = patch;
    }

    @JsonProperty("html")
    public Html__1 getHtml() {
        return html;
    }

    @JsonProperty("html")
    public void setHtml(Html__1 html) {
        this.html = html;
    }

    @JsonProperty("diff")
    public Diff getDiff() {
        return diff;
    }

    @JsonProperty("diff")
    public void setDiff(Diff diff) {
        this.diff = diff;
    }

    @JsonProperty("approve")
    public Approve getApprove() {
        return approve;
    }

    @JsonProperty("approve")
    public void setApprove(Approve approve) {
        this.approve = approve;
    }

    @JsonProperty("statuses")
    public Statuses getStatuses() {
        return statuses;
    }

    @JsonProperty("statuses")
    public void setStatuses(Statuses statuses) {
        this.statuses = statuses;
    }

    public String toString() {
        return "Links__1 {\n" +
                "  self: \"" + (self != null ? self : "<null>") + "\",\n" +
                "  comments: \"" + (comments != null ? comments : "<null>") + "\",\n" +
                "  patch: \"" + (patch != null ? patch : "<null>") + "\",\n" +
                "  html: \"" + (html != null ? html : "<null>") + "\",\n" +
                "  diff: \"" + (diff != null ? diff : "<null>") + "\",\n" +
                "  approve: \"" + (approve != null ? approve : "<null>") + "\",\n" +
                "  statuses: \"" + (statuses != null ? statuses : "<null>") + "\"\n" +
                "}";
    }

    /*@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Links__1 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("self");
        sb.append('=');
        sb.append(((this.self == null)?"<null>":this.self));
        sb.append(',');
        sb.append("comments");
        sb.append('=');
        sb.append(((this.comments == null)?"<null>":this.comments));
        sb.append(',');
        sb.append("patch");
        sb.append('=');
        sb.append(((this.patch == null)?"<null>":this.patch));
        sb.append(',');
        sb.append("html");
        sb.append('=');
        sb.append(((this.html == null)?"<null>":this.html));
        sb.append(',');
        sb.append("diff");
        sb.append('=');
        sb.append(((this.diff == null)?"<null>":this.diff));
        sb.append(',');
        sb.append("approve");
        sb.append('=');
        sb.append(((this.approve == null)?"<null>":this.approve));
        sb.append(',');
        sb.append("statuses");
        sb.append('=');
        sb.append(((this.statuses == null)?"<null>":this.statuses));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }*/

}
