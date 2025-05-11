package aiss.bitbucketminer.etl;


import aiss.bitbucketminer.model.bitBucket.commit.Participant;

import aiss.bitbucketminer.model.gitMiner.*;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Transformer {


    public Project transformProject(aiss.bitbucketminer.model.bitBucket.repositories.Repository externalModel) {

        Project project = new Project();

        project.setId(externalModel.getUuid());
        project.setName(externalModel.getName());
        project.setWebUrl(externalModel.getLinks().getHtml().getHref());

        // Crea y devuelve una nueva instancia de InternalModel
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

    public static Issue toGitMinerIssue(aiss.bitbucketminer.model.bitBucket.issue.Issue bitbucketIssue) {
        if (bitbucketIssue == null) return null;

        Issue result = new Issue();

        // Title y Description (content.raw)
        result.setTitle(bitbucketIssue.getTitle());
        result.setDescription(bitbucketIssue.getContent() != null ? bitbucketIssue.getContent().getRaw() : null);

        // Estado (open/closed/etc.)
        result.setState(bitbucketIssue.getState());

        // Fechas
        result.setCreatedAt(bitbucketIssue.getCreatedOn());
        result.setUpdatedAt(bitbucketIssue.getUpdatedOn());

        // Fecha de cierre (solo si el estado es "closed")
        result.setClosedAt("closed".equalsIgnoreCase(bitbucketIssue.getState()) ? bitbucketIssue.getUpdatedOn() : null);

        // Etiquetas vacías (Bitbucket no proporciona)
        result.setLabels(new java.util.ArrayList<>());

        // Autor del issue (reporter)
        aiss.bitbucketminer.model.bitBucket.issue.Reporter reporter = bitbucketIssue.getReporter();

        return result;
        }

    public Issue transformIssue(aiss.bitbucketminer.model.bitBucket.issue.Issue issue) {
        return null;
    }
}