package research;

import java.util.*;

public class ResearchProject {

    private String projectId;
    private String topic;
    private List<Researcher> participants;
    private List<ResearchPaper> publishedPapers;
    private Date startDate;
    private String status;
    private Researcher principalInvestigator;

    public ResearchProject(String projectId, String topic) {
        this.projectId = projectId;
        this.topic = topic;
        this.participants = new ArrayList<>();
        this.publishedPapers = new ArrayList<>();
        this.startDate = new Date();
        this.status = "ACTIVE";
    }

    // Компактный конструктор для совместимости
    public ResearchProject(String topic) {
        this(UUID.randomUUID().toString(), topic);
    }

    public String getProjectId() {
        return projectId;
    }

    public String getTopic() {
        return topic;
    }

    public List<Researcher> getParticipants() {
        return participants;
    }

    /**
     * Добавляет участника проекта с проверкой
     * @throws exceptions.NotResearcherException если не Researcher
     */
    public void addParticipant(Researcher r) throws exceptions.NotResearcherException {
        if (r == null) {
            throw new exceptions.NotResearcherException("Researcher cannot be null");
        }
        if (!r.isResearcher()) {
            throw new exceptions.NotResearcherException("Only active researchers can join projects");
        }
        participants.add(r);
    }

    public void setPrincipalInvestigator(Researcher r) throws exceptions.NotResearcherException {
        if (r == null) {
            throw new exceptions.NotResearcherException("Principal Investigator cannot be null");
        }
        if (!r.isResearcher()) {
            throw new exceptions.NotResearcherException("Principal Investigator must be an active researcher");
        }
        this.principalInvestigator = r;
        if (!participants.contains(r)) {
            addParticipant(r);
        }
    }

    public Researcher getPrincipalInvestigator() {
        return principalInvestigator;
    }

    public void addPaper(ResearchPaper paper) {
        if (paper != null) {
            publishedPapers.add(paper);
        }
    }

    public List<ResearchPaper> getPublishedPapers() {
        return publishedPapers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    @Override
    public String toString() {
        return "Project: " + topic + " | Status: " + status + " | Papers: " + publishedPapers.size()
               + " | Participants: " + participants.size();
    }
}
