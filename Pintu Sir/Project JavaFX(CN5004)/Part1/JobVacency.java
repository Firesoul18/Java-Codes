import java.util.ArrayList;

// This class is for storing Jobs
public class JobVacency {
    private static int id =1;
    private int JobId;
    private String title;
    private ArrayList<String> requiredSkills;
    private String location;
    private String company;



    public JobVacency(int Jobid, String title, ArrayList<String> requiredSkills, String location, String company){
        this.title = title;
        this.JobId = Jobid;
        this.requiredSkills=requiredSkills;
        this.location=location;
        this.company=company;
        id=JobId+1;
    }
    
    public JobVacency(String title, ArrayList<String> requiredSkills, String location, String company){
        this(id,title,requiredSkills,location,company);
    }

    
    public int getJobId() {
        return JobId;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public ArrayList<String> getRequiredSkills() {
        return requiredSkills;
    }
    public void setRequiredSkills(ArrayList<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public void addRequiredSkills(String requiredSkill){
        this.requiredSkills.add(requiredSkill);
    }

    public void removeRequiredSkills(String requiredSkill){
        this.requiredSkills.remove(requiredSkill);
    }

    //This method prints Details of Job
    public String printJobDetails(){
        String str="";
        str+=("Job Id: "+this.JobId+" Title: "+this.title+" Location: "+this.location+" Company: "+this.company);
        str+=("\nRequired Skills:\n");
        for(int i=0; i<this.requiredSkills.size(); i++){
            str+=(this.requiredSkills.get(i)+"\n");
        }
        System.out.println(str);
        return str;
    }

}