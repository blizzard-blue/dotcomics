package models;

import java.util.*;

/**
 * Created by Jessica on 3/30/2016.
 */
public class Series implements java.io.Serializable {
    private String title;
    private List<Issue> issues;
    private String author;
    private String description;
    private String genre;
    private String coverimgpath;

    public String getTitle(){
        return title;
    }

    public Issue getIssue(String title){
        Issue i = null;

        for(int j=0; j<issues.size(); j++){
            i = issues.get(j);
            if(i.getTitle().equals(title))
                return i;
        }

        return null;
    }

    public int getNumIssues(){
        return issues.size();
    }

    public List<Issue> getIssues(){
        return issues;
    }

    public void addIssue(String title, Issue issue){
        if(issues == null)
            issues = new ArrayList<Issue>();
        issues.add(issue);
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre(){
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCoverimgpath() {
        return coverimgpath;
    }

    public void setCoverimgpath(String coverimgpath) {
        this.coverimgpath = coverimgpath;
    }
}
