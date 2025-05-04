package aiss.bitbucketminer.model.bitBucket.commit;

import aiss.bitbucketminer.model.bitBucket.commit.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Participants {

    @JsonProperty("user")
    private User user;
    @JsonProperty("approved")
    private Boolean approved;
    @JsonProperty("role")
    private String role;

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }

    @JsonProperty("approved")
    public Boolean getApproved() {
        return approved;
    }

    @JsonProperty("approved")
    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    @JsonProperty("role")
    public String getRole() {
        return role;
    }

    @JsonProperty("role")
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "{\n" +
                "  \"user\": " + (user != null ? user.toString() : "null") + ",\n" +
                "  \"approved\": " + (approved != null ? approved.toString() : "null") + ",\n" +
                "  \"role\": \"" + (role != null ? role : "null") + "\"\n" +
                "}";
    }





}

