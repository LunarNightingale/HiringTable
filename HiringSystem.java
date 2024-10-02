/**
 * @author Siu Hin Nicholas Cheng
 * SBU ID: 116564445
 * email: Siuhin.Cheng@stonybrook.edu
 * Assignment #: HW1
 * Course: CSE 214
 * Recitation: R03 (Kailash Anand & Kevin Zheng)(Monday 5:00 P.M. - 5:55 P.M.)
 */
import java.util.*;
public class HiringSystem
{
    /**
     * The main method runs a menu-driven application that first creates an empty HiringTable object. The program then prompts the user for a command in order to execute an operation. Once a command has been chosen, the program may ask the user for additional information if necessary to perform the operation.
     * @param args
     * Use of console to enter a string array into main method (unneeded).
     * @throws InvalidGPAException
     * A gpa was created that is either less than 0, more than 4, or not even a double.
     * @throws ApplicantNotFoundException
     * Searching for an applicant inside the table that doesn't exist.
     * @throws FullTableException
     * The table is full and there was an attempt to add another applicant.
     */
    public static void main(String args[]) throws InvalidGPAException, ApplicantNotFoundException, FullTableException{
        HiringTable current = new HiringTable();
        HiringTable backup = new HiringTable();
        String op;
        Scanner scan = new Scanner(System.in);
        System.out.println("(A)\tAdd Applicant\n(R)     "
          + "Remove Applicant\n(G)\tGet Applicant\n(P)  "
          + "\tPrint List\n(RS)\tRefine Search\n(S)     "
          + "Size\n(B)\tBackup\n(CB)\tCompare Backup\n"
          + "(RB)\tRevert Backup\n(Q)\tQuit\n");
        do{
            System.out.println("\nPlease enter a command: ");
            op = scan.next().toUpperCase();
            if(op.equals("A")){
                Applicant resume = new Applicant();
                scan.nextLine();
                System.out.println("Enter the applicant's name: ");
                String resumeName = scan.nextLine();
                
                resume.setApplicantName(resumeName);
                System.out.println("Enter the applicant's GPA: ");
                double gpa;
                while(scan.hasNext()){
                    if(scan.hasNextDouble()){
                        gpa = scan.nextDouble();
                        if(gpa >= 0 && gpa <= 4){
                            resume.setApplicantGPA(gpa);
                            break;
                        } else{
                            System.out.println("Enter a valid GPA: ");
                        }
                    } else{
                        System.out.println("Enter a valid GPA: ");
                        scan.next();
                    }
                }
                System.out.println("Enter the applicant's college: ");
                scan.nextLine();
                resume.setApplicantCollege(scan.nextLine());
                int count = current.getMaxCompanies();
                String company = "company";
                while(count > 0 && !company.isEmpty()){
                    System.out.println("Enter up to " + count + " Companies");
                    count--;
                    company = scan.nextLine();
                    if(!company.isEmpty()){
                        resume.addCompany(company);
                    }
                }
                count = current.getMaxSkills();
                String skill = "skill";
                while(count > 0 && !skill.isEmpty()){
                    System.out.println("Enter up to " + count + " Skills");
                    count--;
                    skill = scan.nextLine();
                    if(!skill.isEmpty()){
                        resume.addSkill(skill);
                    }
                }
                current.addApplicant(resume);
                System.out.println("Applicant " + resume.getApplicantName() + " has been successfully added to the hiring system.");
            } else if(op.equals("R")){
                System.out.println("Enter applicant name: ");
                scan.nextLine();
                String remove = scan.nextLine();
                current.removeApplicant(remove);
            } else if(op.equals("G")){
                System.out.println("Enter Applicant Name: ");
                scan.nextLine();
                String name = scan.nextLine();
                System.out.println(current.getApplicant(name).toString());
            } else if(op.equals("P")){
                current.printApplicantTable();
            } else if(op.equals("RS")){
                System.out.println("Enter a company to filter for: ");
                scan.nextLine();
                String company = scan.nextLine();
                System.out.println("Enter a skill to filter for: ");
                String skill = scan.nextLine();
                System.out.println("Enter a college to filter for: ");
                String college = scan.nextLine();
                System.out.println("Enter a GPA to filter for: ");
                double gpa = -1;
                String checkDouble = scan.nextLine();
                if(!checkDouble.isEmpty()) {
                    try {
                        gpa = Double.parseDouble(checkDouble);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid double entered.");
                    }
                }
                HiringTable.refineSearch(current, company, skill, college, gpa);
            } else if(op.equals("S")){
                System.out.println("There are " + current.size() + " applicants in the hiring system.");
            } else if(op.equals("B")){
                backup = new HiringTable();
                for(int i = 0; i < current.size(); i++){
                    backup.addApplicant(current.getApplicant(i));
                }
                System.out.println("Successfully created backup.");
            } else if(op.equals("CB")){
                boolean flag = true;
                for(int i = 0; i < backup.size() && i < current.size(); i++){
                    if(current.size() != backup.size()){
                        flag = false;
                    }
                    if(!(backup.getApplicant(i).equals(current.getApplicant(i)))){
                        flag = false;
                    }
                }
                if(flag){
                    System.out.println("Current list is the same as the backup copy.");
                } else{
                    System.out.println("Current list is not the same as the backup copy.");
                }
            } else if(op.equals("RB")){
                current = new HiringTable();
                for(int i = 0; i < backup.size(); i++){
                    current.addApplicant(backup.getApplicant(i));
                }
                System.out.println("Successfully reverted to the backup copy.");
            } else if(op.equals("Q")){
                System.out.println("Quitting program . . .");
                break;
            } else{
                System.out.println("Invalid command");
            }
        } while(!op.equals("Q") || op.isEmpty());
    }
}
