package users;

import core.Employee;
import enums.UserRole;
import research.ResearchPaper;
import research.ResearchProject;
import research.Researcher;
import java.util.*;

public class ResearchAssociate extends Employee implements Researcher {
    private static final long serialVersionUID = 11L;

    private String department;
    private int hIndex;
    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;
    private boolean isResearcher = true;

    public ResearchAssociate(String id, String firstName, String lastName, String email, String password,
                            double salary, String department) {
        super(id, firstName, lastName, email, password, UserRole.TEACHER, salary);
        this.department = department;
        this.hIndex = 0;
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public int getHIndex() {
        return hIndex;
    }

    private void updateHIndex() {
        this.hIndex = researchPapers.size();
    }

    @Override
    public List<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    @Override
    public void addResearchPaper(ResearchPaper paper) {
        if (this.researchPapers == null) this.researchPapers = new ArrayList<>();
        this.researchPapers.add(paper);
        updateHIndex();
    }

    @Override
    public List<ResearchProject> getResearchProjects() {
        return researchProjects;
    }

    @Override
    public void addResearchProject(ResearchProject project) {
        if (this.researchProjects == null) this.researchProjects = new ArrayList<>();
        this.researchProjects.add(project);
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> c) {
        if (this.researchPapers != null) {
            this.researchPapers.sort(c);
            for (ResearchPaper p : this.researchPapers) {
                System.out.println(p);
            }
        }
    }

    @Override
    public boolean isResearcher() {
        return this.isResearcher;
    }

    @Override
    public String toString() {
        return String.format("%s | Department: %s | H-Index: %d | Papers: %d",
                super.toString(), department, hIndex, researchPapers.size());
    }
}

