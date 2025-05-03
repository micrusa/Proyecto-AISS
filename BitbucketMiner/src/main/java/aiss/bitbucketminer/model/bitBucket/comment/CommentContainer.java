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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"values\": [\n");
        for (int i = 0; i < values.size(); i++) {
            sb.append(values.get(i).toString());
            if (i < values.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("  ]\n");
        sb.append("}");
        return sb.toString();
    }
}
