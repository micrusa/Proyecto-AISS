package aiss.bitbucketminer.model.bitBucket.issue;

import aiss.bitbucketminer.model.bitBucket.comment.Comment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueContainer {
    private List<Issue> values;

    public List<Issue
            > getValues() {
        return values;
    }

    public void setValues(List<Issue> values) {
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

