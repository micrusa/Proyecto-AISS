package aiss.bitbucketminer.model.bitBucket.commit;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Commit {

    @JsonProperty("rendered")
    private Rendered rendered;
    @JsonProperty("hash")
    private String hash;
    @JsonProperty("repository")
    private Repository repository;
    @JsonProperty("links")
    private Links__1 links;
    @JsonProperty("author")
    private Author author;
    @JsonProperty("summary")
    private Summary summary;
    @JsonProperty("participants")
    private List<Participants> participants;
    @JsonProperty("parents")
    private List<Parent> parents;
    @JsonProperty("date")
    private String date;
    @JsonProperty("message")
    private String message;
    @JsonProperty("type")
    private String type;

    @JsonProperty("rendered")
    public Rendered getRendered() {
        return rendered;
    }

    @JsonProperty("rendered")
    public void setRendered(Rendered rendered) {
        this.rendered = rendered;
    }

    @JsonProperty("hash")
    public String getHash() {
        return hash;
    }

    @JsonProperty("hash")
    public void setHash(String hash) {
        this.hash = hash;
    }

    @JsonProperty("repository")
    public Repository getRepository() {
        return repository;
    }

    @JsonProperty("repository")
    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    @JsonProperty("links")
    public Links__1 getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(Links__1 links) {
        this.links = links;
    }

    @JsonProperty("author")
    public Author getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(Author author) {
        this.author = author;
    }

    @JsonProperty("summary")
    public Summary getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    @JsonProperty("participants")
    public List<Participants> getParticipants() {
        return participants;
    }

    @JsonProperty("participants")
    public void setParticipants(List<Participants> participants) {
        this.participants = participants;
    }

    @JsonProperty("parents")
    public List<Parent> getParents() {
        return parents;
    }

    @JsonProperty("parents")
    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"type\": \"").append(type != null ? type : "<null>").append("\",\n");
        sb.append("  \"hash\": \"").append(hash != null ? hash : "<null>").append("\",\n");
        sb.append("  \"date\": \"").append(date != null ? date : "<null>").append("\",\n");
        sb.append("  \"author\": ").append(author != null ? author.toString() : "null").append(",\n");
        sb.append("  \"committer\": ").append(participants != null ? participants.toString() : "null").append(",\n");
        sb.append("  \"message\": \"").append(message != null ? message : "<null>").append("\",\n");
        sb.append("  \"summary\": ").append(summary != null ? summary.toString() : "null").append(",\n");
        sb.append("  \"parents\": ").append(parents != null ? parents.toString() : "[]").append("\n");
        sb.append("}");
        return sb.toString();
    }

    /*@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("    {\n");
        sb.append("      \"type\": \"").append(type).append("\",\n");
        sb.append("      \"hash\": \"").append(hash).append("\",\n");
        sb.append("      \"date\": \"").append(date).append("\",\n");
        sb.append("      \"author\": ").append(author != null ? author.toString() : "null").append(",\n");
        sb.append("      \"committer\": ").append(participants != null ? participants.toString() : "null").append(",\n");
        sb.append("      \"message\": \"").append(message).append("\",\n");
        sb.append("      \"summary\": ").append(summary != null ? summary.toString() : "null").append(",\n");
        sb.append("      \"parents\": ").append(parents != null ? parents.toString() : "[]").append("\n");
        sb.append("    }");
        return sb.toString();
    }
*/

}
