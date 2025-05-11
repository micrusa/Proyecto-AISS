package aiss.bitbucketminer.model.bitBucket.issue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @JsonProperty("type")
    private String type;
    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("links")
    private UserLinks links;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public UserLinks getLinks() {
        return links;
    }

    public void setLinks(UserLinks links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "Reporter {\n" +
                "  type: " + (type != null ? type : "null") + ",\n" +
                "  uuid: " + (uuid != null ? uuid : "null") + ",\n" +
                "  display_name: " + (displayName != null ? displayName : "null") + "\n" +
                "}";
    }
}
