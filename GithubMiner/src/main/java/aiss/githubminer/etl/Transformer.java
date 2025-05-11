package aiss.githubminer.etl;


import aiss.githubminer.model.github.comment.User;
import aiss.githubminer.model.gitminer.Comment;
import aiss.githubminer.model.gitminer.Commit;
import aiss.githubminer.model.gitminer.Issue;
import aiss.githubminer.model.gitminer.Project;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class Transformer {

    public Project transformProject(aiss.githubminer.model.github.project.Project externalModel) {
        Project project = new Project();

        project.setId(String.valueOf(externalModel.getId()));
        project.setName(externalModel.getName());
        project.setWebUrl(externalModel.getUrl());

        return project;
    }

    public Commit transformCommit(aiss.githubminer.model.github.commit.Commit externalModel) {
        Commit commit = new Commit();

        commit.setId(String.valueOf(externalModel.getSha()));
        commit.setTitle(externalModel.getCommit().getMessage().split("\n")[0]);
        commit.setMessage(externalModel.getCommit().getMessage());
        commit.setAuthorName(externalModel.getAuthor().getLogin());
        commit.setAuthorEmail(externalModel.getAuthor().getEmail());
        commit.setAuthoredDate(externalModel.getCommit().getAuthor().getDate());
        commit.setCommitterName(externalModel.getCommitter().getName());
        commit.setCommitterEmail(externalModel.getCommitter().getEmail());
        String committedDate = externalModel.getCommitter().getDate();
        commit.setCommittedDate(committedDate);
        commit.setWebUrl(externalModel.getHtmlUrl());

        if(commit.getCommitterName() == null || commit.getCommitterName().isEmpty() || committedDate == null || committedDate.isEmpty())
            return null; // Cumplimos las restricciones de GitMiner
        commit.setWebUrl(externalModel.getHtmlUrl());
        return commit;
    }

    public Comment transformComment(aiss.githubminer.model.github.comment.Comment externalModel, String issueUrl) {
        Comment comment = new Comment();

        comment.setId(String.valueOf(externalModel.getId()));
        comment.setBody(externalModel.getBody());
        comment.setCreatedAt(externalModel.getCreatedAt());
        comment.setUpdatedAt(externalModel.getUpdatedAt());

        if(issueUrl == null) // Parece que no viene el issue_url nunca, por lo que lo tomamos del issue
            issueUrl = externalModel.getIssueUrl();
        comment.setIssueUrl(issueUrl);

        User user = externalModel.getUser();
        aiss.githubminer.model.gitminer.User author = transformCommentUser(user);
        comment.setAuthor(author);
        return comment;
    }

    public Issue transformIssue(aiss.githubminer.model.github.issue.Issue externalModel) {
        Issue issue = new Issue();

        issue.setId(String.valueOf(externalModel.getId()));
        issue.setRefId(String.valueOf(externalModel.getNumber()));
        issue.setTitle(externalModel.getTitle());
        issue.setDescription(externalModel.getBody());
        issue.setState(externalModel.getState());
        issue.setCreatedAt(externalModel.getCreatedAt());
        issue.setUpdatedAt(externalModel.getUpdatedAt());

        String closedAt = null;
        if (Objects.equals(externalModel.getState(), "open")) {
            closedAt = null;
        } else if (Objects.equals(externalModel.getState(), "closed")) {
            closedAt = (String) externalModel.getClosedAt();
        }
        issue.setClosedAt(closedAt);

        List<Object> labels = externalModel.getLabels();
        List<String> stringLabels = labels.stream()
                .filter(obj -> obj instanceof String)
                .map(obj -> (String) obj)
                .collect(Collectors.toList());
        issue.setLabels(stringLabels);

        aiss.githubminer.model.github.issue.User user = externalModel.getUser();
        aiss.githubminer.model.gitminer.User author = transformIssueUser(user);
        issue.setAuthor(author);
        issue.setUpvotes(externalModel.getReactions().getPlusOne());
        issue.setDownvotes(externalModel.getReactions().getMinusOne());
        issue.setWebUrl(externalModel.getUrl());

        return issue;
    }

    public aiss.githubminer.model.gitminer.User transformCommentUser(aiss.githubminer.model.github.comment.User externalModel) {
        aiss.githubminer.model.gitminer.User user = new aiss.githubminer.model.gitminer.User();

        user.setId(String.valueOf(externalModel.getId()));
        user.setUsername(externalModel.getLogin());
        user.setName(externalModel.getLogin());
        user.setAvatarUrl(externalModel.getAvatarUrl());
        user.setWebUrl(externalModel.getHtmlUrl());

        return user;
    }

    public aiss.githubminer.model.gitminer.User transformIssueUser(aiss.githubminer.model.github.issue.User externalModel) {
        aiss.githubminer.model.gitminer.User user = new aiss.githubminer.model.gitminer.User();

        user.setId(String.valueOf(externalModel.getId()));
        user.setUsername(externalModel.getLogin());
        user.setName(externalModel.getLogin());
        user.setAvatarUrl(externalModel.getAvatarUrl());
        user.setWebUrl(externalModel.getHtmlUrl());

        return user;
    }

}
