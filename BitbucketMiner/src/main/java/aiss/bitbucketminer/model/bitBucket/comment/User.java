package aiss.bitbucketminer.model.bitBucket.comment;


import aiss.bitbucketminer.model.bitBucket.commit.Links__2;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)


public class User {

    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("links")
    private Links__2 links;
    @JsonProperty("type")
    private String type;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("display_name")
    public String getDisplayName() {
        return displayName;
    }

    @JsonProperty("display_name")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonProperty("uuid")
    public String getUuid() {
        return uuid;
    }

    @JsonProperty("uuid")
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @JsonProperty("links")
    public Links__2 getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(Links__2 links) {
        this.links = links;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("nickname")
    public String getNickname() {
        return nickname;
    }

    @JsonProperty("nickname")
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @JsonProperty("account_id")
    public String getAccountId() {
        return accountId;
    }

    @JsonProperty("account_id")
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String toString() {
        return "User {\n" +
                "  displayName: \"" + (displayName != null ? displayName : "") + "\",\n" +
                "  uuid: \"" + (uuid != null ? uuid : "") + "\",\n" +
                "  links: " + (links != null ? links.toString() : "null") + ",\n" +
                "  type: \"" + (type != null ? type : "") + "\",\n" +
                "  nickname: \"" + (nickname != null ? nickname : "") + "\",\n" +
                "  accountId: \"" + (accountId != null ? accountId : "") + "\"\n" +
                "}";
    }

   /* @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(User.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }*/

}
