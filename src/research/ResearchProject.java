package research;

import java.util.*;

public class ResearchProject {

    private String projectId;
    private String topic;
    private List<Researcher> participants;
    private List<ResearchPaper> publishedPapers;
    private Date startDate;
    private String status;

    public ResearchProject(String topic) {
        this.topic = topic;
        this.participants = new ArrayList<>();
        this.publishedPapers = new ArrayList<>();
    }

    public String getTopic() {
        return topic;
    }

    public List<Researcher> getParticipants() {
        return participants;
    }

    public void addParticipant(Researcher r) {
        participants.add(r);
    }

    public void addPaper(ResearchPaper paper) {
        publishedPapers.add(paper);
    }

    public List<ResearchPaper> getPublishedPapers() {
        return publishedPapers;
    }

    @Override
    public String toString() {
        return "Project: " + topic + ", Papers: " + publishedPapers.size();
    }
}
