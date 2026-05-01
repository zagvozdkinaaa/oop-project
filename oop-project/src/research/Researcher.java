package research;

import java.io.Serializable;
import java.util.*;

public interface Researcher extends Serializable {

    int getHIndex();

    List<ResearchPaper> getResearchPapers();

    List<ResearchProject> getResearchProjects();

    void printPapers(Comparator<ResearchPaper> c);

    void addResearchPaper(ResearchPaper paper);

    void addResearchProject(ResearchProject project);

    // сортировка статей
    static Comparator<ResearchPaper> compareByDate() {
        return Comparator.comparing(ResearchPaper::getDate);
    }

    static Comparator<ResearchPaper> compareByCitations() {
        return Comparator.comparing(ResearchPaper::getCitations).reversed();
    }

    static Comparator<ResearchPaper> compareByPages() {
        return Comparator.comparing(ResearchPaper::getPages);
    }
}
