package aiss.bitbucketminer.model.bitBucket.repositories;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoriesContainer {

    @JsonProperty("values")
    private List<Repositories> values;

    public List<Repositories> getValues() {
        return values;
    }

    public void setValues(List<Repositories> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"values\": [\n");
        if (values != null) {
            for (int i = 0; i < values.size(); i++) {
                sb.append(values.get(i).toString());
                if (i < values.size() - 1) sb.append(",");
                sb.append("\n");
            }
        }
        sb.append("  ]\n");
        sb.append("}");
        return sb.toString();
    }
}
