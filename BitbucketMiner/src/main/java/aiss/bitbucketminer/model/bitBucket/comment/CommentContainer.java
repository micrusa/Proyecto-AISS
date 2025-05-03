package aiss.bitbucketminer.model.bitBucket.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentContainer {
    private List<Comment> values;

    public List<Comment> getValues() {
        return values;
    }

    public void setValues(List<Comment> values) {
        this.values = values;
    }
}
