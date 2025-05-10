package aiss.bitbucketminer.etl;


import aiss.bitbucketminer.model.bitBucket.commit.Participant;

import aiss.bitbucketminer.model.gitMiner.*;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Transformer {


    public Project bitbucketTransformProject(aiss.bitbucketminer.model.bitBucket.project.Project externalModel) {

        Project project = new Project();

        String id = externalModel.getUuid();
        project.setId(id);

        String name = externalModel.getName();
        project.setName(name);

        String webUrl = externalModel.getLinks().getHtml().getHref();
        project.setWebUrl(webUrl);

        // Crea y devuelve una nueva instancia de InternalModel
        return project;
    }

    public Commit bitbucketTransformCommit(aiss.bitbucketminer.model.bitBucket.commit.Commit externalModel) {
        Commit commit = new Commit();

        String id = externalModel.getHash();
        commit.setId(id);

        String title = externalModel.getType();
        commit.setTitle(title);

        String message = externalModel.getMessage();
        commit.setMessage(message);

        String authorName = externalModel.getAuthor().getUser().getDisplayName();
        commit.setAuthorName(authorName);

        String authorEmail = externalModel.getAuthor().getRaw()
                .replace("${authorName} <", "")
                .replace(">", "");
        commit.setAuthorEmail(authorEmail);

        String authoredDate = externalModel.getDate();
        commit.setAuthoredDate(authoredDate);






        if (externalModel.getParticipants() != null && !externalModel.getParticipants().isEmpty()) {
            for (Participant participant : externalModel.getParticipants()) {
                if (participant.getRole().equals("committer")) {
                    String committerName = participant.getUser().getDisplayName();
                    commit.setCommitterName(committerName);
                    String committerEmail = participant.getUser().getNickname();
                    commit.setCommitterEmail(committerEmail);
                    String committedDate = externalModel.getDate();
                    commit.setCommittedDate(committedDate);
                }
            }
        } else {
            commit.setCommitterName(authorName);
            commit.setCommitterEmail(authorEmail);
            commit.setCommittedDate(authoredDate);
        }


        String webUrl = externalModel.getLinks().getHtml().getHref();

        commit.setWebUrl(webUrl);

        return commit;
    }

    public Comment bitbucketTransformComment(aiss.bitbucketminer.model.bitBucket.comment.Comment externalModel) {
        Comment comment = new Comment();

        String id = externalModel.getId().toString();
        comment.setId(id);

        String body = externalModel.getContent().toString();
        comment.setBody(body);

        //Hay que fixear el user.
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


    public Issue bitbucketTransformIssue(aiss.bitbucketminer.model.bitBucket.issue.Issue externalModel) {
        Issue issue = new Issue();

        String id = externalModel.getId().toString();
        issue.setId(id);





        return issue;
    }

    // Métodos para transformar listas
    public List<Commit> bitbucketTransformCommits(List<aiss.bitbucketminer.model.bitBucket.commit.Commit> externalModels) {
        return externalModels.stream()
                .map(this::bitbucketTransformCommit)
                .collect(Collectors.toList());
    }

    public List<Comment> bitbucketTransformComments(List<aiss.bitbucketminer.model.bitBucket.comment.Comment> externalModels) {
        return externalModels.stream()
                .map(this::bitbucketTransformComment)
                .collect(Collectors.toList());
    }


}