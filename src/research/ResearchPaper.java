package research;

import java.util.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class ResearchPaper implements Comparable<ResearchPaper>,Serializable {

	
	private static final long serialVersionUID = 1L;
    private String paperId;
    private String title;
    private List<String> authors;
    private String journal;
    private String doi;
    private Date date;
    private int citations;
    private int pages;
    private List<String> keywords;
    private String volume;
    private String issue;
    private String url;
    private String conference;
    private String abstract_text;

  
    public ResearchPaper(String paperId, String title, List<String> authors, String journal,
                        int pages, Date date, int citations, String doi) {
        this.paperId = paperId;
        this.title = title;
        this.authors = authors != null ? authors : new ArrayList<>();
        this.journal = journal;
        this.pages = pages;
        this.date = date;
        this.citations = citations;
        this.doi = doi;
        this.keywords = new ArrayList<>();
    }

    // Компактный конструктор для совместимости
    public ResearchPaper(String title, int citations, int pages, Date date) {
        this(UUID.randomUUID().toString(), title, new ArrayList<>(), "", pages, date, citations, "");
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

    public List<String> getAuthors() {
        return authors;
    }

    public String getJournal() {
        return journal;
    }

    public String getPaperId() {
        return paperId;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public void setCitations(int citations) {
        if (citations < 0) {
            throw new IllegalArgumentException("Citations cannot be negative");
        }
        this.citations = citations;
    }
    
    public String getAbstractText() {
        return abstract_text;
    }

    public void setAbstractText(String abstractText) {
        this.abstract_text = abstractText;
    }

    /**
     * Компаратор по количеству цитат (по убыванию)
     */
    public static Comparator<ResearchPaper> byCitationsDesc() {
        return (p1, p2) -> Integer.compare(p2.citations, p1.citations);
    }

    /**
     * Компаратор по дате публикации (новые первыми)
     */
    public static Comparator<ResearchPaper> byDateDesc() {
        return (p1, p2) -> {
            if (p1.date == null || p2.date == null) return 0;
            return p2.date.compareTo(p1.date);
        };
    }

    /**
     * Компаратор по количеству страниц (по убыванию)
     */
    public static Comparator<ResearchPaper> byPagesDesc() {
        return (p1, p2) -> Integer.compare(p2.pages, p1.pages);
    }

    /**
     * Компаратор по названию (алфавитно)
     */
    public static Comparator<ResearchPaper> byTitle() {
        return Comparator.comparing(ResearchPaper::getTitle);
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = date != null ? sdf.format(date) : "N/A";
        return String.format("%s | Authors: %s | Journal: %s | %d citations | %d pages | %s",
                title, String.join(", ", authors), journal, citations, pages, dateStr);
    }

    @Override
    public int compareTo(ResearchPaper other) {
        return Integer.compare(other.citations, this.citations); 
    }
}
