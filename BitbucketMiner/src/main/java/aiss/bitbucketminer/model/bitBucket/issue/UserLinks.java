package aiss.bitbucketminer.model.bitBucket.issue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLinks {
    @JsonProperty("avatar")
    private HtmlLink avatar;

    public HtmlLink getAvatar() {
        return avatar;
    }

    public void setAvatar(HtmlLink avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "ReporterLinks{" +
                "avatar=" + avatar +
                '}';
    }
}
