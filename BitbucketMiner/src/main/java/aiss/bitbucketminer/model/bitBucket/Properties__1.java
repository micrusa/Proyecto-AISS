package aiss.bitbucketminer.model.bitBucket;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)


public class Properties__1 {

    @JsonProperty("id")
    private Id__1 id;
    @JsonProperty("title")
    private Title title;
    @JsonProperty("message")
    private Message message;
    @JsonProperty("author_name")
    private AuthorName authorName;
    @JsonProperty("author_email")
    private AuthorEmail authorEmail;
    @JsonProperty("authored_date")
    private AuthoredDate authoredDate;
    @JsonProperty("web_url")
    private WebUrl__1 webUrl;

    @JsonProperty("id")
    public Id__1 getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Id__1 id) {
        this.id = id;
    }

    @JsonProperty("title")
    public Title getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(Title title) {
        this.title = title;
    }

    @JsonProperty("message")
    public Message getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(Message message) {
        this.message = message;
    }

    @JsonProperty("author_name")
    public AuthorName getAuthorName() {
        return authorName;
    }

    @JsonProperty("author_name")
    public void setAuthorName(AuthorName authorName) {
        this.authorName = authorName;
    }

    @JsonProperty("author_email")
    public AuthorEmail getAuthorEmail() {
        return authorEmail;
    }

    @JsonProperty("author_email")
    public void setAuthorEmail(AuthorEmail authorEmail) {
        this.authorEmail = authorEmail;
    }

    @JsonProperty("authored_date")
    public AuthoredDate getAuthoredDate() {
        return authoredDate;
    }

    @JsonProperty("authored_date")
    public void setAuthoredDate(AuthoredDate authoredDate) {
        this.authoredDate = authoredDate;
    }

    @JsonProperty("web_url")
    public WebUrl__1 getWebUrl() {
        return webUrl;
    }

    @JsonProperty("web_url")
    public void setWebUrl(WebUrl__1 webUrl) {
        this.webUrl = webUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Properties__1 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null)?"<null>":this.title));
        sb.append(',');
        sb.append("message");
        sb.append('=');
        sb.append(((this.message == null)?"<null>":this.message));
        sb.append(',');
        sb.append("authorName");
        sb.append('=');
        sb.append(((this.authorName == null)?"<null>":this.authorName));
        sb.append(',');
        sb.append("authorEmail");
        sb.append('=');
        sb.append(((this.authorEmail == null)?"<null>":this.authorEmail));
        sb.append(',');
        sb.append("authoredDate");
        sb.append('=');
        sb.append(((this.authoredDate == null)?"<null>":this.authoredDate));
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
