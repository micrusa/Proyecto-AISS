package aiss.bitbucketminer.etl;


import aiss.bitbucketminer.model.bitBucket.commit.Participant;

import aiss.bitbucketminer.model.gitMiner.*;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Transformer {


    public Project transformProject(aiss.bitbucketminer.model.bitBucket.repositories.Repository externalModel) {
        Project project = new Project();

        project.setId(externalModel.getUuid());
        project.setName(externalModel.getName());
        project.setWebUrl(externalModel.getLinks().getHtml().getHref());

        return project;
    }

    public Commit transformCommit(aiss.bitbucketminer.model.bitBucket.commit.Commit externalModel) {
        Commit commit = new Commit();

        commit.setId(externalModel.getHash());
        commit.setTitle(externalModel.getMessage().split("\n")[0]);
        commit.setMessage(externalModel.getMessage());

        String authorName = externalModel.getAuthor().getUser().getDisplayName();
        commit.setAuthorName(authorName);
        String authorEmail = externalModel.getAuthor().getRaw()
                .replace(authorName + " <", "")
                .replace(">", "");
        commit.setAuthorEmail(authorEmail);

        commit.setAuthoredDate(externalModel.getDate());
        commit.setCommittedDate(externalModel.getDate());

        if (externalModel.getParticipants() != null && !externalModel.getParticipants().isEmpty()) {
            for (Participant participant : externalModel.getParticipants()) {
                if (participant.getRole().equals("committer")) {
                    commit.setCommitterName(participant.getUser().getDisplayName());
                    String committerEmail = participant.getUser().getNickname();
                    commit.setCommitterEmail(committerEmail);
                }
            }
        } else {
            commit.setCommitterName(authorName);
            commit.setCommitterEmail(authorEmail);
        }

        String webUrl = externalModel.getLinks().getHtml().getHref();

        commit.setWebUrl(webUrl);

        return commit;
    }

    public Comment transformComment(aiss.bitbucketminer.model.bitBucket.comment.Comment externalModel) {
        Comment comment = new Comment();

        String id = externalModel.getId().toString();
        comment.setId(id);

        String body = externalModel.getContent().getRaw();
        if(body == null) return null;
        comment.setBody(body);

        User user = new User();
        user.setId(externalModel.getUser().getUuid());
        user.setName(externalModel.getUser().getDisplayName());
        user.setUsername(externalModel.getUser().getNickname());
        user.setAvatarUrl(externalModel.getUser().getLinks().getAvatar().getHref());
        user.setWebUrl(externalModel.getUser().getLinks().getHtml().getHref());

        comment.setAuthor(user);

        String createdAt = externalModel.getCreatedOn();
        comment.setCreatedAt(createdAt);

        String updatedAt = externalModel.getUpdatedOn();
        comment.setUpdatedAt(updatedAt);

        return comment;
    }

    public Issue transformIssue(aiss.bitbucketminer.model.bitBucket.issue.Issue externalModel) {
        if (externalModel == null) return null;

        Issue issue = new Issue();

        issue.setId(externalModel.getId().toString());
        issue.setTitle(externalModel.getTitle());
        issue.setDescription(externalModel.getContent() != null ? externalModel.getContent().getRaw() : null);
        issue.setLabels(List.of());
        issue.setClosedAt("closed".equalsIgnoreCase(externalModel.getState()) ? externalModel.getUpdatedOn() : null);
        issue.setState(externalModel.getState());

        issue.setCreatedAt(externalModel.getCreatedOn());
        issue.setUpdatedAt(externalModel.getUpdatedOn());
        issue.setUpvotes(externalModel.getVotes());
        issue.setWebUrl(externalModel.getLinks().getHtml().getHref());

        aiss.bitbucketminer.model.bitBucket.issue.User reporter = externalModel.getReporter();
        aiss.bitbucketminer.model.bitBucket.issue.User assignee = externalModel.getAssignee();

        if(assignee != null) {
            issue.setAssignee(transformIssueUser(assignee));
        }

        issue.setAuthor(transformIssueUser(reporter));

        return issue;
    }

    private User transformIssueUser(aiss.bitbucketminer.model.bitBucket.issue.User externalModel) {
        User user = new User();
        user.setId(externalModel.getUuid());
        user.setName(externalModel.getDisplayName());
        user.setUsername(externalModel.getDisplayName());
        user.setAvatarUrl(externalModel.getLinks().getAvatar().getHref());
        return user;
    }
}