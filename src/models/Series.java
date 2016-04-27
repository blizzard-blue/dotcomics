package models;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Jessica on 3/30/2016.
 */
public class Series implements java.io.Serializable {
    private String title;
    private LinkedHashMap<String, Issue> issues;
    private String coverImg;
    private String author;
    private String description;

    public String getTitle(){
        return title;
    }

    public Issue getIssue(String title){
        return issues.get(title);
    }

    public int getNumIssues(){
        return issues.size();
    }

    public Map<String, Issue> getIssues(){
        return issues;
    }

    public void addIssue(String title, Issue issue){
        if(issues == null)
            issues = new LinkedHashMap<String, Issue>();

        issues.put(title, issue);
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
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
}
