package research;

import java.util.*;

/**
 * 
 */
public interface Researcher {





    /**
     * @return
     */
    public int getHIndex();

    /**
     * @return
     */
    public List<ResearchPaper> getResearchPapers();

    /**
     * @return
     */
    public List<ResearchProject> getResearchProjects();

    /**
     * @param c 
     * @return
     */
    public void printPapers(Comparator<ResearchPaper> c);

    /**
     * @param paper 
     * @return
     */
    public void addResearchPaper(ResearchPaper paper);

    /**
     * @param project 
     * @return
     */
    public void addResearchProject(ResearchProject project);

}