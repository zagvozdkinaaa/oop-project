package research;

import core.User;
import exceptions.NotResearcherException;
import java.io.Serializable;
import java.util.*;

public class ResearchProject implements Serializable {
    private static final long serialVersionUID = 11L;

    private String topic;
    private List<Researcher> participants = new ArrayList<>();
    private List<ResearchPaper> publishedPapers = new ArrayList<>();

    public ResearchProject(String topic) {
        this.topic = topic;
    }

    public void addParticipant(User user) throws NotResearcherException {
        if (!(user instanceof Researcher)) {
            throw new NotResearcherException(user);
        }
        participants.add((Researcher) user);
    }

    public void addPaper(ResearchPaper paper) {
        publishedPapers.add(paper);
    }

    public String getTopic() { return topic; }
    public List<Researcher> getParticipants() { return participants; }
    public List<ResearchPaper> getPublishedPapers() { return publishedPapers; }

    @Override
    public String toString() {
        return "Project: " + topic + " Participants: " + participants.size() + " Papers: " + publishedPapers.size();
    }
}
