package aiss.bitbucketminer.model.bitBucket.commit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
}
