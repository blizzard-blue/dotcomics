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
}
