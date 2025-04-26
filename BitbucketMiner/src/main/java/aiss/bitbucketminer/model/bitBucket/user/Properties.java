package aiss.bitbucketminer.model.bitBucket.user;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Properties {

    @JsonProperty("id")
    private Id id;
    @JsonProperty("username")
    private Username username;
    @JsonProperty("name")
    private Name name;
    @JsonProperty("avatar_url")
    private AvatarUrl avatarUrl;
    @JsonProperty("web_url")
    private WebUrl webUrl;

    @JsonProperty("id")
    public Id getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Id id) {
        this.id = id;
    }

    @JsonProperty("username")
    public Username getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(Username username) {
        this.username = username;
    }

    @JsonProperty("name")
    public Name getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(Name name) {
        this.name = name;
    }

    @JsonProperty("avatar_url")
    public AvatarUrl getAvatarUrl() {
        return avatarUrl;
    }

    @JsonProperty("avatar_url")
    public void setAvatarUrl(AvatarUrl avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @JsonProperty("web_url")
    public WebUrl getWebUrl() {
        return webUrl;
    }

    @JsonProperty("web_url")
    public void setWebUrl(WebUrl webUrl) {
        this.webUrl = webUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Properties.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("username");
        sb.append('=');
        sb.append(((this.username == null)?"<null>":this.username));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("avatarUrl");
        sb.append('=');
        sb.append(((this.avatarUrl == null)?"<null>":this.avatarUrl));
        sb.append(',');
        sb.append("webUrl");
        sb.append('=');
        sb.append(((this.webUrl == null)?"<null>":this.webUrl));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
