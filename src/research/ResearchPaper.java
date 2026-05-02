package research;

import java.util.*;

public class ResearchPaper implements Comparable<ResearchPaper> {

    private String paperId;
    private String title;
    private List<String> authors;
    private String journal;
    private String doi;
    private Date date;
    private int citations;
    private int pages;
    private List<String> keywords;
    private String topic;

    public ResearchPaper(String title, int citations, int pages, Date date) {
        this.title = title;
        this.citations = citations;
        this.pages = pages;
        this.date = date;
        this.authors = new ArrayList<>();
        this.keywords = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public int getCitations() {
        return citations;
    }

    public int getPages() {
        return pages;
    }

    public Date getDate() {
        return date;
    }

    public String getDoi() {
        return doi;
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

    @Override
    public String toString() {
        return title + " (" + citations + " citations)";
    }

    @Override
    public int compareTo(ResearchPaper other) {
        return Integer.compare(other.citations, this.citations); 
    }
}
