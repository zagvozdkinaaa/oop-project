package research;

import java.io.Serializable;
import java.util.*;
import java.text.SimpleDateFormat;

public class ResearchPaper implements Comparable<ResearchPaper>, Serializable {
    private static final long serialVersionUID = 10L;

    private String title;
    private List<String> authors;
    private String journal;
    private String doi;
    private Date date;
    private int citations;
    private int pages;

    public ResearchPaper(String title, List<String> authors, String journal, String doi, int citations, int pages, Date date) {
        this.title = title;
        this.authors = authors;
        this.journal = journal;
        this.doi = doi;
        this.citations = citations;
        this.pages = pages;
        this.date = date;
    }

    public String getTitle() { return title; }
    public int getCitations() { return citations; }
    public int getPages() { return pages; }
    public Date getDate() { return date; }
    public String getDoi() { return doi; }
    public List<String> getAuthors() { return authors; }

    @Override
    public int compareTo(ResearchPaper other) {
        return Integer.compare(other.citations, this.citations);
    }

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String authorsList = String.join(", ", authors);
        return String.format("%s, \"%s,\" %s, pp. %d, %s, DOI: %s. [Citations: %d]", 
            authorsList, title, journal, pages, df.format(date), doi, citations);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResearchPaper)) return false;
        ResearchPaper that = (ResearchPaper) o;
        return Objects.equals(doi, that.doi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doi);
    }
}
