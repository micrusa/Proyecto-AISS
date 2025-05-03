package aiss.bitbucketminer.model.bitBucket.commit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommitContainer {
    private List<Commit> values;

    public List<Commit> getValues() {
        return values;
    }

    public void setValues(List<Commit> values) {
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

