package aiss.bitbucketminer.model.bitBucket.project;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Project {

    @JsonProperty("type")
    private String type;
    @JsonProperty("links")
    private Links links;
    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("key")
    private String key;
    @JsonProperty("owner")
    private Owner owner;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("is_private")
    private Boolean isPrivate;
    @JsonProperty("created_on")
    private String createdOn;
    @JsonProperty("updated_on")
    private String updatedOn;
    @JsonProperty("has_publicly_visible_repos")
    private Boolean hasPubliclyVisibleRepos;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("links")
    public Links getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(Links links) {
        this.links = links;
    }

    @JsonProperty("uuid")
    public String getUuid() {
        return uuid;
    }

    @JsonProperty("uuid")
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    @JsonProperty("owner")
    public Owner getOwner() {
        return owner;
    }

    @JsonProperty("owner")
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("is_private")
    public Boolean getIsPrivate() {
        return isPrivate;
    }

    @JsonProperty("is_private")
    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
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

    @JsonProperty("has_publicly_visible_repos")
    public Boolean getHasPubliclyVisibleRepos() {
        return hasPubliclyVisibleRepos;
    }

    @JsonProperty("has_publicly_visible_repos")
    public void setHasPubliclyVisibleRepos(Boolean hasPubliclyVisibleRepos) {
        this.hasPubliclyVisibleRepos = hasPubliclyVisibleRepos;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"type\": ").append(type).append(",\n");
        sb.append("  \"links\": {\n");
        sb.append("    \"html\": {\n");
        sb.append("      \"href\": ").append(links != null && links.getHtml() != null ? links.getHtml().getHref() : null).append(",\n");
        sb.append("      \"name\": ").append(links != null && links.getHtml() != null ? links.getHtml().getName() : null).append("\n");
        sb.append("    },\n");
        sb.append("    \"avatar\": {\n");
        sb.append("      \"href\": ").append(links != null && links.getAvatar() != null ? links.getAvatar().getHref() : null).append(",\n");
        sb.append("      \"name\": ").append(links != null && links.getAvatar() != null ? links.getAvatar().getName() : null).append("\n");
        sb.append("    }\n");
        sb.append("  },\n");
        sb.append("  \"uuid\": ").append(uuid).append(",\n");
        sb.append("  \"key\": ").append(key).append(",\n");
        sb.append("  \"owner\": {\n");
        sb.append("    \"type\": ").append(owner != null ? owner.getType() : null).append("\n");
        sb.append("  },\n");
        sb.append("  \"name\": ").append(name).append(",\n");
        sb.append("  \"description\": ").append(description == null ? "" : description).append(",\n");
        sb.append("  \"is_private\": ").append(isPrivate != null ? isPrivate : "null").append(",\n");
        sb.append("  \"created_on\": ").append(createdOn).append(",\n");
        sb.append("  \"updated_on\": ").append(updatedOn).append(",\n");
        sb.append("  \"has_publicly_visible_repos\": ").append(hasPubliclyVisibleRepos != null ? hasPubliclyVisibleRepos : "null").append("\n");
        sb.append("}");
        return sb.toString();
    }


   /* @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Project.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("links");
        sb.append('=');
        sb.append(((this.links == null)?"<null>":this.links));
        sb.append(',');
        sb.append("uuid");
        sb.append('=');
        sb.append(((this.uuid == null)?"<null>":this.uuid));
        sb.append(',');
        sb.append("key");
        sb.append('=');
        sb.append(((this.key == null)?"<null>":this.key));
        sb.append(',');
        sb.append("owner");
        sb.append('=');
        sb.append(((this.owner == null)?"<null>":this.owner));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("isPrivate");
        sb.append('=');
        sb.append(((this.isPrivate == null)?"<null>":this.isPrivate));
        sb.append(',');
        sb.append("createdOn");
        sb.append('=');
        sb.append(((this.createdOn == null)?"<null>":this.createdOn));
        sb.append(',');
        sb.append("updatedOn");
        sb.append('=');
        sb.append(((this.updatedOn == null)?"<null>":this.updatedOn));
        sb.append(',');
        sb.append("hasPubliclyVisibleRepos");
        sb.append('=');
        sb.append(((this.hasPubliclyVisibleRepos == null)?"<null>":this.hasPubliclyVisibleRepos));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }*/

}
