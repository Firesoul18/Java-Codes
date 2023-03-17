import java.util.ArrayList;

public class JobSeeker {
    private static int id=1;
    private String name;
    private int JSid;
    private ArrayList<String> skills;
    private String location;

    public JobSeeker(int JSid, String name, ArrayList<String> skills, String location) {
        this.name = name;
        this.JSid = JSid;
        this.location = location;
        this.skills = skills;
        JobSeeker.id=JSid+1;
    }

    public JobSeeker(String name, ArrayList<String> skills, String location){
        this(id,name,skills,location);    
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJSid() {
        return JSid;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void addSkill(String skill) {
        this.skills.add(skill);
    }

    public void removeSkill(String skill) {
        this.skills.remove(skill);
    }

    public String printDetails() {
        String str ="";
        str+=("JobSeeker Id: "+this.JSid+" Name: "+this.name+" Location: "+this.location+"\n");
        str+=("Skills:\n");
        for(int i=0; i<this.skills.size(); i++){
            str+=(this.skills.get(i)+"\n");
        }
        System.out.println(str);
        return str;
    }
    
}