package aiss.githubminer.etl;


import aiss.githubminer.model.github.comment.User;
import aiss.githubminer.model.gitminer.Comment;
import aiss.githubminer.model.gitminer.Commit;
import aiss.githubminer.model.gitminer.Issue;
import aiss.githubminer.model.gitminer.Project;

import java.util.List;

public class Transformer {

    public Project githubTransformProject(aiss.githubminer.model.github.project.Project externalModel){
        Project project = new Project();

        String id = String.valueOf(externalModel.getId());
        project.setId(id);
        String name = externalModel.getName();
        project.setName(name);
        String webUrl = externalModel.getUrl();
        project.setWebUrl(webUrl);
        List<Commit> commits = externalModel.getCommits();
        project.setCommits(commits);
        List<Issue> issues = externalModel.getIssues();
        project.setIssues(issues);

        return project;
    }

    public Commit githubTransformCommit(aiss.githubminer.model.github.commit.Commit externalModel){
        Commit commit = new Commit();

        String id = String.valueOf(externalModel.getSha());
        commit.setId(id);
        String title = externalModel.getTitle();
        commit.setTitle(title);
        String message = externalModel.getCommit().getMessage();
        commit.setMessage(message);
        String authorName = externalModel.getAuthor().getLogin();
        commit.setAuthorName(authorName);
        String authorEmail = externalModel.getAuthor().getEmail();
        commit.setAuthorEmail(authorEmail);
        String authoredDate = externalModel.getAuthor().getDate();
        commit.setAuthoredDate(authoredDate);
        String committerName = externalModel.getCommitter().getName();
        commit.setCommitterName(committerName);
        String committerEmail = externalModel.getCommitter().getEmail();
        commit.setCommitterEmail(committerEmail);
        String committedDate = externalModel.getCommitter().getDate();
        commit.setCommittedDate(committedDate);
        String webUrl = externalModel.getHtmlUrl();
        commit.setWebUrl(webUrl);

        return commit;
    }

    public Comment githubTransformComment(aiss.githubminer.model.github.comment.Comment externalModel){
        Comment comment = new Comment();

        String id = String.valueOf(externalModel.getId());
        comment.setId(id);
        String body = externalModel.getBody();
        comment.setBody(body);
        String createdAt = externalModel.getCreatedAt();
        comment.setCreatedAt(createdAt);
        String updatedAt = externalModel.getUpdatedAt();
        comment.setUpdatedAt(updatedAt);

        User user = externalModel.getUser();
        aiss.githubminer.model.gitminer.User author = new aiss.githubminer.model.gitminer.User();
        author.setName(user.getLogin());

        comment.setAuthor(author);

        return comment;
    }

    public Issue githubTransformIssue(aiss.githubminer.model.github.issue.Issue externalModel){
        Issue issue = new Issue();

        String id = String.valueOf(externalModel.getId());
        issue.setId(id);
        String refId = String.valueOf(externalModel.getNumber());
        issue.setRefId(refId);
        String title = externalModel.getTitle();
        issue.setTitle(title);
        String description = externalModel.getBody();
        issue.setDescription(description);
        String state = externalModel.getState();
        issue.setState(state);
        String createdAt = externalModel.getCreatedAt();
        issue.setCreatedAt(createdAt);
        String updatedAt = externalModel.getUpdatedAt();
        issue.setUpdatedAt(updatedAt);
        String closedAt = externalModel.getClosedAt();
        issue.setClosedAt(closedAt);
        List<String> labels = externalModel.getLabels();
        issue.setLabels(labels);

        User user = externalModel.getUser();
        aiss.githubminer.model.gitminer.User author = new aiss.githubminer.model.gitminer.User();
        author.setName(user.getLogin());

        issue.setAuthor(author);




        return issue;
    }
}
