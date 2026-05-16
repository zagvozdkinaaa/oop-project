package research;

import java.util.*;

public interface Researcher {

    int getHIndex();

    List<ResearchPaper> getResearchPapers();

    List<ResearchProject> getResearchProjects();

    void printPapers(Comparator<ResearchPaper> c);

    void addResearchPaper(ResearchPaper paper);

    void addResearchProject(ResearchProject project);

    boolean isResearcher();
}
