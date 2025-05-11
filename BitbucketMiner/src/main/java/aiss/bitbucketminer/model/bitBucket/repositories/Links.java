
package aiss.bitbucketminer.model.bitBucket.repositories;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "self",
    "html",
    "avatar",
    "pullrequests",
    "commits",
    "forks",
    "watchers",
    "downloads",
    "clone",
    "hooks"
})
public class Links {

    @JsonProperty("self")
    private Self self;
    @JsonProperty("html")
    private Html html;
    @JsonProperty("avatar")
    private Avatar avatar;
    @JsonProperty("pullrequests")
    private Pullrequests pullrequests;
    @JsonProperty("commits")
    private Commits commits;
    @JsonProperty("forks")
    private Forks forks;
    @JsonProperty("watchers")
    private Watchers watchers;
    @JsonProperty("downloads")
    private Downloads downloads;
    @JsonProperty("clone")
    private List<Clone> clone;
    @JsonProperty("hooks")
    private Hooks hooks;

    @JsonProperty("self")
    public Self getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(Self self) {
        this.self = self;
    }

    @JsonProperty("html")
    public Html getHtml() {
        return html;
    }

    @JsonProperty("html")
    public void setHtml(Html html) {
        this.html = html;
    }

    @JsonProperty("avatar")
    public Avatar getAvatar() {
        return avatar;
    }

    @JsonProperty("avatar")
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    @JsonProperty("pullrequests")
    public Pullrequests getPullrequests() {
        return pullrequests;
    }

    @JsonProperty("pullrequests")
    public void setPullrequests(Pullrequests pullrequests) {
        this.pullrequests = pullrequests;
    }

    @JsonProperty("commits")
    public Commits getCommits() {
        return commits;
    }

    @JsonProperty("commits")
    public void setCommits(Commits commits) {
        this.commits = commits;
    }

    @JsonProperty("forks")
    public Forks getForks() {
        return forks;
    }

    @JsonProperty("forks")
    public void setForks(Forks forks) {
        this.forks = forks;
    }

    @JsonProperty("watchers")
    public Watchers getWatchers() {
        return watchers;
    }

    @JsonProperty("watchers")
    public void setWatchers(Watchers watchers) {
        this.watchers = watchers;
    }

    @JsonProperty("downloads")
    public Downloads getDownloads() {
        return downloads;
    }

    @JsonProperty("downloads")
    public void setDownloads(Downloads downloads) {
        this.downloads = downloads;
    }

    @JsonProperty("clone")
    public List<Clone> getClone() {
        return clone;
    }

    @JsonProperty("clone")
    public void setClone(List<Clone> clone) {
        this.clone = clone;
    }

    @JsonProperty("hooks")
    public Hooks getHooks() {
        return hooks;
    }

    @JsonProperty("hooks")
    public void setHooks(Hooks hooks) {
        this.hooks = hooks;
    }

    @Override
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
        sb.append("avatar");
        sb.append('=');
        sb.append(((this.avatar == null)?"<null>":this.avatar));
        sb.append(',');
        sb.append("pullrequests");
        sb.append('=');
        sb.append(((this.pullrequests == null)?"<null>":this.pullrequests));
        sb.append(',');
        sb.append("commits");
        sb.append('=');
        sb.append(((this.commits == null)?"<null>":this.commits));
        sb.append(',');
        sb.append("forks");
        sb.append('=');
        sb.append(((this.forks == null)?"<null>":this.forks));
        sb.append(',');
        sb.append("watchers");
        sb.append('=');
        sb.append(((this.watchers == null)?"<null>":this.watchers));
        sb.append(',');
        sb.append("downloads");
        sb.append('=');
        sb.append(((this.downloads == null)?"<null>":this.downloads));
        sb.append(',');
        sb.append("clone");
        sb.append('=');
        sb.append(((this.clone == null)?"<null>":this.clone));
        sb.append(',');
        sb.append("hooks");
        sb.append('=');
        sb.append(((this.hooks == null)?"<null>":this.hooks));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
