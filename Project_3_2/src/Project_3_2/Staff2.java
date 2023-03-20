  package Project_3_2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;

//Factory pattern
interface Staffs2{
    void init();
    void add_names();
    String getName();
    void addStaff();
    void updateDepartedStaff(FNCDdata2 fnc) throws IOException;
}

interface factoryStaff2{
    Staffs2 createStaff();
}

class Internfactory2 implements factoryStaff2{

    @Override
    public Staffs2 createStaff() {
        return (Staffs2) new Intern2();
    } // Creating Intern implementing createStaff()
}
class Mechanicfactory2 implements factoryStaff2{

    @Override
    public Staffs2 createStaff() {
        return (Staffs2) new Mechanic2();
    } // Creating Mechanic implementing createStaff()
}
class Salespersonfactory2 implements factoryStaff2{

    @Override
    public Staffs2 createStaff() { return (Staffs2) new Salesperson2(); } // Creating Salesperson implementing createStaff()
}
class Driverfactory2 implements factoryStaff2{

    @Override
    public Staffs2 createStaff() {
        return (Staffs2) new Driver2();
    } // Creating Driver implementing createStaff()
}
class Buyerfactory2 implements factoryStaff2{

    @Override
    public Staffs2 createStaff() {
        return (Staffs2) new Buyer2();
    }// Creating Buyer implementing createStaff()
}

//For 2nd FNCD
public class Staff2 implements Staffs2{
    static String[] staffType = {"Intern", "Mechanic", "Salesperson","Driver"};
    static ArrayList<String> names = new ArrayList<>();
    static ArrayList<String>[] staff = new ArrayList[4];
    static ArrayList<String>[] staff_status = new ArrayList[4];
    static ArrayList<String>[] dep_staff = new ArrayList[4];
    static int[] normal_pay = {500, 1000, 1500, 2000};
    static ArrayList<Integer>[] total_normal_pay = new ArrayList[4];
    static ArrayList<Integer>[] bonus = new ArrayList[4];
    static ArrayList<Integer>[] total_bonus = new ArrayList[4];
    static ArrayList<Integer>[] dep_total_bonus = new ArrayList[4];
    static ArrayList<Integer>[] dep_total_normal_pay = new ArrayList[4];
    static ArrayList<Integer>[] salary = new ArrayList[4];
    static ArrayList<Integer>[] total_salary = new ArrayList[4];
    static ArrayList<Integer>[] dep_total_salary = new ArrayList[4];
    static ArrayList<Integer>[] days_worked = new ArrayList[4];
    static ArrayList<Integer>[] dep_days_worked = new ArrayList[4];
    static ArrayList<Integer> race_won = new ArrayList<>();//only for drivers
    static ArrayList<Integer> dep_race_won = new ArrayList<>();//only for drivers
    static double prob = 0;
    static ArrayList<Integer> quit_status = new ArrayList<>(3);
    static int bonus_val=0;//for individual bonus of any action
    Random rand = new Random();

    //Initializing the variables
    public void init() {
        for (int i = 0; i < 4; i++) {
            staff[i] = new ArrayList<>();
            staff_status[i] = new ArrayList<>();
            days_worked[i] = new ArrayList<>();
            total_bonus[i] = new ArrayList<>();
            bonus[i] = new ArrayList<>();
            total_normal_pay[i]= new ArrayList<>();
            salary[i] = new ArrayList<>();
            total_salary[i] = new ArrayList<>();
            dep_staff[i] = new ArrayList<>();
            dep_days_worked[i] = new ArrayList<>();
            dep_total_bonus[i] = new ArrayList<>();
            dep_total_normal_pay[i] = new ArrayList<>();
            dep_total_salary[i] = new ArrayList<>();
            quit_status.add(0);
        }
        race_won = new ArrayList<>();
        dep_race_won = new ArrayList<>();
    }
    //The following method gives a set of names for staff
    public void add_names() {
        names.addAll(Arrays.asList(
                "Adam", "Alexis", "Angel", "Ashley", "Brianna", "Cameron", "Carter", "Cecilia", "Chase", "Christian",
                "Christopher", "Cole", "Connor", "Dakota", "Daniel", "David", "Dylan", "Elizabeth", "Ethan", "Evan",
                "Faith", "Gabriel", "Gavin", "Grace", "Haley", "Hunter", "Isaac", "Jaden", "Jasmine", "Jason",
                "Jayden", "Jocelyn", "Jordan", "Joseph", "Joshua", "Julia", "Justin", "Kaitlyn", "Katherine", "Kendall",
                "Kevin", "Lauren", "Leah", "Logan", "Luis", "Luke", "Mackenzie", "Madison", "Makayla", "Maria",
                "Mason", "Matthew", "Max", "Megan", "Melanie", "Melissa", "Mia", "Mikayla", "Miranda", "Natalie",
                "Nathan", "Nicholas", "Oliver", "Olivia", "Oscar", "Paige", "Patrick", "Rachel", "Rebecca", "Riley",
                "Ryan", "Samantha", "Santiago", "Sarah", "Savannah", "Sebastian", "Sierra", "Sophie", "Sydney", "Taylor",
                "Tristan", "Tyler", "Valeria", "Victoria", "Vincent", "William", "Wyatt", "Xavier", "Zachary", "Shawn"));
    }

    public String getName() {
        String name;
        int i = rand.nextInt(names.size());
        name = names.get(i);
        names.remove(i);
        return name;
    }
    //Adding initial staff members and their data for the first day
    public void addStaff() {
        staff[0].add("Thibaut");
        staff[0].add("Daniel");
        staff[0].add("David");
        staff[1].add("Antonio");
        staff[1].add("Ferland");
        staff[1].add("Eduardo");
        staff[2].add("Toni");
        staff[2].add("Luka");
        staff[2].add("Federico");
        staff[3].add("Vinicius");
        staff[3].add("Karim");
        staff[3].add("Carlo");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                staff_status[i].add("Active");
                days_worked[i].add(0);
                total_bonus[i].add(0);
                bonus[i].add(0);
                salary[i].add(0);
                total_normal_pay[i].add(0);
                total_salary[i].add(0);
            }
        }
        for (int i = 0; i < 3; i++) {
            race_won.add(0);
        }
    }
    //method to maintain the staff data at the end of the day if any staff quits
    public void updateDepartedStaff(FNCDdata2 fnc) throws IOException {
        //General case where any worker quits, he/she is removed from the staff list and added to departed staff list
        for (int i = 0; i < 3; i++) { //Drivers do not quit so, it is not 4
            for (int j = 0; j < staff_status[i].size(); j++) {
                prob = rand.nextDouble();
                if (prob < 0.1) {
                    if(quit_status.get(i)==0){//If one person from same staff type has already quit then another one will not quit for that day.
                        System.out.println(staffType[i]+" " +staff[i].get(j)+" quit the FNCD.");//printing staff status
                        fnc.dayEnd(i,j,0);//obs
                        dep_staff[i].add(staff[i].get(j));//adding all staff details to departed staff list
                        staff[i].remove(j);//removing the staff details after the staff quits and adding to departed staff list.
                        staff_status[i].remove(j);
                        dep_days_worked[i].add(days_worked[i].get(j));
                        days_worked[i].remove(j);
                        dep_total_bonus[i].add(total_bonus[i].get(j));
                        total_bonus[i].remove(j);
                        bonus[i].remove(j);
                        dep_total_normal_pay[i].add(total_normal_pay[i].get(j));
                        total_normal_pay[i].remove(j);
                        dep_total_salary[i].add(total_salary[i].get(j));
                        total_salary[i].remove(j);
                        salary[i].remove(j);
                        quit_status.set(i,1);//so that at the most only one from each staff type may quit
                        j--;
                    }
                }
            }
        }
    }
}