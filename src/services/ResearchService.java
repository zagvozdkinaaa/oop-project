package services;

import research.Researcher;
import research.ResearchPaper;
import research.ResearchProject;
import java.util.*;
import java.util.stream.Collectors;


public class ResearchService {
    private static ResearchService instance;
    private List<Researcher> researchers;

    private ResearchService() {
        this.researchers = new ArrayList<>();
    }

    public static ResearchService getInstance() {
        if (instance == null) {
            instance = new ResearchService();
        }
        return instance;
    }

    public void registerResearcher(Researcher researcher) {
        if (researcher != null && !researchers.contains(researcher)) {
            researchers.add(researcher);
        }
    }

    public List<ResearchPaper> getAllPapers(Comparator<ResearchPaper> comparator) {
        List<ResearchPaper> allPapers = new ArrayList<>();
        for (Researcher researcher : researchers) {
            List<ResearchPaper> papers = researcher.getResearchPapers();
            if (papers != null) {
                allPapers.addAll(papers);
            }
        }

        if (comparator != null) {
            allPapers.sort(comparator);
        }
        return allPapers;
    }

    public void printAllPapers(Comparator<ResearchPaper> comparator) {
        List<ResearchPaper> allPapers = getAllPapers(comparator);
        System.out.println("=== ALL RESEARCH PAPERS ===");
        for (ResearchPaper paper : allPapers) {
            System.out.println(paper);
        }
    }

    public List<Researcher> getTopResearchersByCitations(int count) {
        return researchers.stream()
                .sorted((r1, r2) -> {
                    int totalCitations1 = r1.getResearchPapers().stream()
                            .mapToInt(ResearchPaper::getCitations).sum();
                    int totalCitations2 = r2.getResearchPapers().stream()
                            .mapToInt(ResearchPaper::getCitations).sum();
                    return Integer.compare(totalCitations2, totalCitations1);
                })
                .limit(count)
                .collect(Collectors.toList());
    }

    public List<Researcher> getTopResearchersByHIndex(int count) {
        return researchers.stream()
                .sorted((r1, r2) -> Integer.compare(r2.getHIndex(), r1.getHIndex()))
                .limit(count)
                .collect(Collectors.toList());
    }

    public List<Researcher> getResearchersByYear(int year) {
        return researchers.stream()
                .filter(r -> r.getResearchPapers().stream()
                        .anyMatch(p -> {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(p.getDate());
                            return cal.get(Calendar.YEAR) == year;
                        }))
                .collect(Collectors.toList());
    }

    public List<ResearchPaper> searchPapersByTitle(String title) {
        return getAllPapers(null).stream()
                .filter(p -> p.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<ResearchPaper> searchPapersByAuthor(String author) {
        return getAllPapers(null).stream()
                .filter(p -> p.getAuthors().stream()
                        .anyMatch(a -> a.toLowerCase().contains(author.toLowerCase())))
                .collect(Collectors.toList());
    }

    public List<Researcher> getAllResearchers() {
        return new ArrayList<>(researchers);
    }

    public void printStatistics() {
        System.out.println("=== RESEARCH STATISTICS ===");
        System.out.println("Total Researchers: " + researchers.size());

        int totalPapers = 0;
        double avgHIndex = 0;
        int totalCitations = 0;

        for (Researcher r : researchers) {
            totalPapers += r.getResearchPapers().size();
            avgHIndex += r.getHIndex();
            totalCitations += r.getResearchPapers().stream()
                    .mapToInt(ResearchPaper::getCitations).sum();
        }

        System.out.println("Total Papers: " + totalPapers);
        System.out.println("Average H-Index: " + (researchers.isEmpty() ? 0 : avgHIndex / researchers.size()));
        System.out.println("Total Citations: " + totalCitations);
    }

    public Researcher getTopResearcherOfYear(int year) {
        return researchers.stream()
                .max(Comparator.comparingInt(r -> r.getResearchPapers().stream()
                        .filter(p -> {
                            if (p.getDate() == null) return false;
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(p.getDate());
                            return cal.get(Calendar.YEAR) == year;
                        })
                        .mapToInt(ResearchPaper::getCitations).sum()))
                .orElse(null);
    }

    public Researcher getTopResearcherOfSchool(String school) {
        return researchers.stream()
                .filter(r -> {
                    if (r instanceof core.User) {
                        return ((core.User) r).toString().toLowerCase().contains(school.toLowerCase());
                    }
                    return false;
                })
                .max(Comparator.comparingInt(r -> r.getResearchPapers().stream()
                        .mapToInt(ResearchPaper::getCitations).sum()))
                .orElse(null);
    }
}
