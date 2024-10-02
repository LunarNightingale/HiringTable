/**
 * @author Siu Hin Nicholas Cheng
 * SBU ID: 116564445
 * email: Siuhin.Cheng@stonybrook.edu
 * Assignment #: HW1
 * Course: CSE 214
 * Recitation: R03 (Kailash Anand & Kevin Zheng)(Monday 5:00 P.M. - 5:55 P.M.)
 */
public class HiringTable {
    private Applicant[] data;
    private final int MAX_SKILLS = 3;
    private final int MAX_COMPANIES = 3;
    private final int MAX_APPLICANTS = 50;
    private int applicants;

    /**
     * Constructs an instance of the HiringTable with no Applicant objects in it.
     *
     * @custom.postcondition
     * The HiringTable has been initialized to an empty list of Applicants.
     */
    public HiringTable() {
        data = new Applicant[50];
        applicants = 0;
    }

    /**
     * Determines the number of Applicant objects currently in the list. This method should run in O(1) time.
     *
     * @return
     * The number of Applicants objects in this HiringTable.
     * @custom.precondition
     * The HiringTable has been instantiated.
     */
    public int size() {
        return applicants;
    }

    /**
     * Adds a new applicant to the list.
     *
     * @param newApplicant
     * An Applicant that is attempted to be inserted into the HiringTable.
     * @throws FullTableException
     * An exception that occurs when the table is full, and we are attempting to add another applicant.
     * @throws ApplicantNotFoundException
     * An exception that occurs when we are searching for an applicant that is not within the HiringTable.
     * @custom.precondition
     * This Applicant object has been instantiated and the number of applicants in the HiringTable is less than MAX_APPLICANTS.
     * @custom.postcondition
     * The new Applicant is now inserted at the end of the list.
     */
    public void addApplicant(Applicant newApplicant) throws FullTableException, ApplicantNotFoundException {
        if (newApplicant == null) {
            throw new ApplicantNotFoundException("No applicant was entered");
        }
        if (applicants >= MAX_APPLICANTS) {
            throw new FullTableException("Table is full already.");
        } else {
            data[applicants] = newApplicant;
            applicants++;
        }
    }

    /**
     * Removes a specified applicant from the list.
     *
     * @param name
     * A string that represents the name of the specified Applicant we want to remove.
     * @throws ApplicantNotFoundException
     * An exception that occurs when the applicant we are searching for is not found within the HiringTable.
     * @custom.precondition
     * The HiringTable has been instantiated.
     * @custom.postcondition
     * he Applicant with the name given has been removed from the list. Any Applicant that was in a spot after the removed Applicant is shifted upwards one spot. To put simply, make sure that there are no gaps in the array when removing an applicant.
     */
    public void removeApplicant(String name) throws ApplicantNotFoundException {
        boolean flag = false;
        int point = -1;
        for (int i = 0; i < applicants; i++) {
            if (data[i].getApplicantName().equals(name)) {
                flag = true;
                point = i;
                System.out.println("Applicant " + data[i].getApplicantName() + " has been successfully removed from the hiring system.");
            }
        }
        if (flag) {
            while (point < MAX_APPLICANTS - 1) {
                data[point] = data[point + 1];
                point++;
            }
            data[49] = null;
            applicants--;
        } else {
            throw new ApplicantNotFoundException("Applicant was not found.");
        }
    }

    /**
     * Returns the specified applicant.
     *
     * @param name
     * A string that represents the name of the specified Applicant we are looking for.
     * @return
     * The Applicant with the corresponding name.
     * @throws ApplicantNotFoundException
     * An exception that occurs when the applicant is not found within the HiringTable.
     * @custom.precondition
     * The HiringTable object has been instantiated.
     */
    public Applicant getApplicant(String name) throws ApplicantNotFoundException {
        for (int i = 0; i < applicants; i++) {
            if (data[i].getApplicantName().equals(name)) {
                return data[i];
            }
        }
        throw new ApplicantNotFoundException("Applicant was not found.");
    }

    /**
     * Returns the applicant at a specified index.
     *
     * @param index
     * The specified index of an array we are looking for.
     * @return
     * Applicant in the specified index.
     */
    public Applicant getApplicant(int index) {
        return data[index];
    }

    /**
     * Prints out a list of applicants that meet the requirements.
     *
     * @param table
     * An instantiated hiring table we are refining.
     * @param company
     * A string that represents the specified company we are searching for.
     * @param skill
     * A string that represents the specified skill we are searching for.
     * @param college
     * A string that represents the specified college we are searching for.
     * @param gpa
     * A double that represents the minimum GPA we are searching for.
     * @throws FullTableException
     * An exception thrown when the table is full, and we are adding another applicant.
     * @throws ApplicantNotFoundException An exception that occurs when we are searching for an applicant not in the HiringTable.
     * @custom.precondition
     * The HiringTable object has been instantiated.
     * @custom.postcondition
     * Displays a neatly formatted table of each Applicant filtered from the HiringTable.
     */
    public static void refineSearch(HiringTable table, String company, String skill, String college, double gpa) throws FullTableException, ApplicantNotFoundException {
        HiringTable refined = new HiringTable();
        Applicant resume;
        for (int i = 0; i < table.size(); i++) {
            boolean flag = true;
            resume = table.getApplicant(i);
            if ((resume.findCompany(company) == -1) && (!company.isEmpty())) {
                flag = false;
            }
            if ((resume.findSkill(skill) == -1) && (!skill.isEmpty())) {
                flag = false;
            }
            if ((!(resume.getApplicantCollege().equals(college)) && (!college.isEmpty()))) {
                flag = false;
            }
            if ((!(resume.getApplicantGPA() >= gpa || (gpa == -1)))) {
                flag = false;
            }
            if (flag) {
                refined.addApplicant(resume);
            }
        }
        refined.printApplicantTable();
    }

    /**
     * Clones the hiring table.
     *
     * @return
     * A copy of this HiringTable object.
     * @custom.precondition
     * This HiringTable has been instantiated.
     */
    public Object clone() {
        HiringTable dupe = new HiringTable();
        for (int i = 0; i < applicants; i++) {
            dupe.data[i] = (Applicant) data[i].clone();
        }
        return dupe;
    }

    /**
     * Prints a neatly formatted applicant table.
     *
     * @custom.precondition
     * This HiringTable has been instantiated.
     * @custom.postcondition
     * Displays a neatly formatted table of each Applicant from the HiringTable.
     */
    public void printApplicantTable() {
        String header = "Company Name %-30s %-20s %-10s %-10s %-10s";
        String fbody = "%-43s %-21s";
        String lbody = "%-6s %-10s %-10s";
        String finalHeader = String.format(header, "", "Applicant", "GPA", "College", "Skills");
        String firstBody, lastBody;
        System.out.println(finalHeader);
        System.out.println(new String(new char[100]).replace("\0", "-"));
        for (int i = 0; i < applicants && data[i] != null; i++) {
            firstBody = String.format(fbody, data[i].printCompany(), data[i].getApplicantName());
            double gpa = data[i].getApplicantGPA();
            String strGPA = String.format("%.2f", gpa).replaceAll("0*$", "");
            if (strGPA.length() == 2) {
                strGPA += "00";
            } else if (strGPA.length() == 3) {
                strGPA += "0";
            }
            lastBody = String.format(lbody, "", data[i].getApplicantCollege(), data[i].printSkill());
            System.out.println(firstBody + strGPA + lastBody);
        }
    }

    /**
     * Getter for max skills.
     *
     * @return
     * Max skills.
     */
    public int getMaxSkills() {
        return MAX_SKILLS;
    }

    /**
     * Getter for max companies.
     *
     * @return
     * Max companies.
     */
    public int getMaxCompanies() {
        return MAX_COMPANIES;
    }

    /**
     * Returns the fields of a specified applicant.
     *
     * @param name
     * A string that represents the applicant's name of whom we are looking to print their fields.
     * @return
     * String with the fields of the corresponding applicant.
     * @throws ApplicantNotFoundException
     * An exception that occurs when the applicant we are searching for is not within the HiringTable.
     */
    public String toString(String name) throws ApplicantNotFoundException {
        return this.getApplicant(name).toString();
    }
}