/**
 * @author Siu Hin Nicholas Cheng
 * SBU ID: 116564445
 * email: Siuhin.Cheng@stonybrook.edu
 * Assignment #: HW1
 * Course: CSE 214
 * Recitation: R03 (Kailash Anand & Kevin Zheng)(Monday 5:00 P.M. - 5:55 P.M.)
 */
public class Applicant extends HiringTable{
    private String[] companyName = new String[super.getMaxCompanies()];
    private String applicantName;
    private double applicantGPA;
    private String applicantCollege;
    private String[] applicantSkills = new String[super.getMaxSkills()];
    private int skillNum;
    private int companyNum;

    /**
     * Basic constructor for applicant
     */
    public Applicant(){
        applicantName = "";
        applicantGPA = 0;
        applicantCollege = "";
        companyName[0] = "";
        applicantSkills[0] = "";
        skillNum = 0;
        companyNum = 0;
    }

    /**
     * Constructor with parameters for applicant
     * @param company
     * An array that has all the names of companies the applicant has or is currently working in.
     * @param name
     * A string that represents the name of the applicant.
     * @param gpa
     * A double that represents the applicant gpa.
     * @param college
     * A string that represents the name of the applicant's college.
     * @param skills
     * An array that has all of the skills of the applicant.
     */
    public Applicant(String[] company, String name, double gpa, String college, String[] skills){
        if(company.length <= super.getMaxCompanies() && skills.length <= super.getMaxSkills()){
            applicantName = name;
            applicantGPA = gpa;
            applicantCollege = college;
            skillNum = skills.length;
            companyNum = company.length;
            for(int i = 0; i < company.length; i++){
                companyName[i] = company[i];
            }
            for(int i = 0; i < skills.length; i++){
                applicantSkills[i] = skills[i];
            }
        } else{
            System.out.println("Company or skills is greater than max");
        }
    }

    /**
     * Getter for company name array.
     * @return
     * Company name array.
     */
    public String[] getCompanyName(){
        return companyName;
    }

    /**
     * Setter for company array.
     * @param company
     * An array with all the updated names of the company the applicant has or is currently working in.
     */
    public void setCompanyName(String[] company){
        if(company.length <= super.getMaxCompanies()){
            for(int i = 0; i < company.length; i++){
                companyName[i] = company[i];
            }
            companyNum = company.length;
       } else{
            System.out.println("Company is greater than max");
       }
    }

    /**
     * Getter for applicant name.
     * @return
     * Applicant name.
     */
    public String getApplicantName(){
        return applicantName;
    }

    /**
     * Setter for applicant name.
     * @param newApplicantName
     * A string that represents the updated applicant's name.
     */
    public void setApplicantName(String newApplicantName){
        applicantName = newApplicantName;
    }

    /**
     * Getter for applicant GPA.
     * @return
     * Applicant GPA.
     */
    public double getApplicantGPA(){
        return applicantGPA;
    }

    /**
     * Setter for applicant GPA.
     * @param newApplicantGPA
     * A double that represents the applicant's updated GPA.
     * @throws InvalidGPAException
     * An exception that occurs when gpa is either less than 0 or greater than 4.
     */
    public void setApplicantGPA(double newApplicantGPA) throws InvalidGPAException{
        if(newApplicantGPA < 0 || newApplicantGPA > 4){
            throw new InvalidGPAException("Invalid GPA");
        } else{
            applicantGPA = newApplicantGPA;
        }
    }

    /**
     * Getter for applicant college.
     * @return
     * Applicant college.
     */
    public String getApplicantCollege(){
        return applicantCollege;
    }

    /**
     * Setter for applicant college.
     * @param newApplicantCollege
     * A string that represents the applicant's new college.
     */
    public void setApplicantCollege(String newApplicantCollege){
        applicantCollege = newApplicantCollege;
    }

    /**
     * Getter for applicant skills array.
     * @return
     * Applicant skills array.
     */
    public String[] getApplicantSkills(){
        return applicantSkills;
    }

    /**
     * Setter for applicant skills.
     * @param skills
     * An array that has all the updated skills of the applicant.
     */
    public void setApplicantSkills(String[] skills){
       if(skills.length <= super.getMaxSkills()){
            for(int i = 0; i < skills.length; i++){
                applicantSkills[i] = skills[i];
            }
            skillNum = skills.length;
       } else{
           System.out.println("Skills is greater than max");
       }
    }

    /**
     * Getter for number of skills.
     * @return
     * Number of applicant skills.
     */
    public int numSkill(){
        return skillNum;
    }

    /**
     * Getter for number of companies.
     * @return
     * Number of applicant companies.
     */
    public int numCompany(){
        return companyNum;
    }

    /**
     * the return value is a copy of this Applicant object. Any further changes to the copy will not affect the original and vice versa. Note that the return value must be typecast to an Applicant object before it can be used.
     * @return
     * A copy of this Applicant object.
     */
    public Object clone(){
        Applicant clone;
        clone = new Applicant(this.getCompanyName(), this.getApplicantName(), this.getApplicantGPA(), this.getApplicantCollege(), this.getApplicantSkills());
        return clone;
    }
    /**
     * Returns a value of true if the object that this Applicant is being compared to has the same attributes. Returns false if the attributes are not the same.
     * @param obj
     * Another object, preferably an Applicant.
     * @return
     * True if the specified applicant is equal to this applicant, false otherwise.
     */
    public boolean equals(Object obj){
        if(obj instanceof Applicant){
            Applicant resume = (Applicant) obj;
            boolean flag = true;
            if(!(this.getApplicantName().equals(resume.getApplicantName()))){
                flag = false;
            }
            if(!(this.getApplicantCollege().equals(resume.getApplicantCollege()))){
                flag = false;
            }
            if(!(this.getApplicantGPA() == resume.getApplicantGPA())){
                flag = false;
            }
            for(int i = 0; i < skillNum && i < resume.numSkill(); i++){
                if(!(this.getApplicantSkills()[i].equals(resume.getApplicantSkills()[i]))){
                    flag = false;
                }
            }
            for(int i = 0; i < companyNum && i < resume.numCompany(); i++){
                if(!(this.getCompanyName()[i].equals(resume.getCompanyName()[i]))){
                    flag = false;
                }
            }
            return flag;
        } else{
            return false;
        }
    }

    /**
     * Returns a String representation of the Applicant object.
     * @return
     * A string representation of this applicant.
     */
    public String toString(){
        return "Applicant Name: " + applicantName + "\nApplicant Applying From: " + printCompany()
          + "\nApplicant GPA: " + applicantGPA + "\nApplicant College: " + applicantCollege + "\nApplicant Skills: "
          + printSkill();
    }

    /**
     * Adds a skill to the applicant's skill array
     * @param skill
     * A string that represents an applicant's new skill.
     */
    public void addSkill(String skill){
        if(skillNum >= super.getMaxSkills()){
            System.out.println("Skills is already full");
        } else{
            applicantSkills[skillNum] = skill;
            skillNum++;
        }
    }

    /**
     * Adds a company to the applicant's company array
     * @param company
     * A string that represents the applicant's new company.
     */
    public void addCompany(String company){
        if(companyNum >= super.getMaxCompanies()){
            System.out.println("Company is already full");
        } else{
            companyName[companyNum] = company;
            companyNum++;
        }
    }

    /**
     * Finds the location of a specific company, returns -1 if not found.
     * @param company
     * A string that represents the specified company we are looking for.
     * @return
     * Index of company, -1 if not in array.
     */
    public int findCompany(String company){
        for(int i = 0; i < companyNum; i++){
            if(companyName[i].equals(company)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the location of a specific skill, returns -1 if not found.
     * @param skill
     * A string that represents the specified skill we are looking for.
     * @return
     * Index of skill, -1 if not in array.
     */
    public int findSkill(String skill){
        for(int i = 0; i < skillNum; i++){
            if(applicantSkills[i].equals(skill)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Prints out a string with all skills.
     * @return
     * String with all skills.
     */
    public String printSkill(){
        String str = "";
        int point = 0;
        while(point < skillNum - 1){
            str += applicantSkills[point] + ", ";
            point++;
        }
        if(skillNum > 0){
            str += applicantSkills[skillNum - 1];
        }
        return str;
    }

    /**
     * Prints out a format with all companies.
     * @return
     * String with all companies.
     */
    public String printCompany(){
        String str = "";
        int point = 0;
        while(point < companyNum - 1){
            str += companyName[point] + ", ";
            point++;
        }
        if(companyNum > 0){
            str += companyName[companyNum - 1];
        }
        return str;
    }
}