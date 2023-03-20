package Project_3_2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Operation extends Staff{
    static int budget = 500000;
    static int total_sales = 0;
    static int day_count;
    static int added_money=0;
    static int Staff_money=0;
    static int FNCD_money=0;
    static String file_name=null;
    static FileWriter writer1;
    //printing the summary of staff
    static void addBudget(int i,int j,FNCDdata fnc) throws IOException {
        if(budget-salary[i].get(j)<=0){       //If we run out of budget, $250000 is added to the budget
            budget=budget+250000;
            System.out.println("Added $250000 in North FNCD budget due to low budget while processing Salary for Staff");
            added_money=added_money+250000;
            fnc.dayEnd(0,0,4);//obs
        }
    }
    static void addBudget(int i,FNCDdata fnc) throws IOException {
        if(budget-Vehicle.cost_price[i].get(Vehicle.vehicle[i].size()-1)<=0){
            budget=budget+250000;       //If we run out of budget, $250000 is added to the budget
            System.out.println("Added $250000 in North FNCD budget due to low budget while buying required Vehicles");
            added_money=added_money+250000;
            fnc.dayEnd(0,0,5);//obs
        }
    }
    static void outputLogger() throws IOException {
        file_name="Logger-"+day_count+".txt";
        File file = new File(file_name);
        FileWriter writer = new FileWriter(file);
        writer1 = writer;
    }
    public void Print(){
        System.out.println("\n************************************************************SUMMARY OF NORTH FNCD************************************************************");
        System.out.println("List of Staff:");
        for (int i=0;i<4;i++){
            System.out.println(staffType[i]+"s: "+staff[i]);
        }
        System.out.println("\nStatus of Staff:");
        for (int i=0;i<4;i++){
            System.out.println(staffType[i]+"s: "+staff_status[i]);
        }
        System.out.println("\nTotal Days worked by Staff:");
        for (int i=0;i<4;i++){
            System.out.println(staffType[i]+"s: "+days_worked[i]);
        }
        System.out.println("\nTotal Bonus of Staff:");
        for (int i=0;i<4;i++){
            System.out.println(staffType[i]+"s: "+total_bonus[i]);
        }
        System.out.println("\nTotal Normal Pay of Staff:");
        for (int i=0;i<4;i++){
            System.out.println(staffType[i]+"s: "+total_normal_pay[i]);
        }
        System.out.println("\nList of Departed Staff:");
        for (int i=0;i<4;i++){
            System.out.println(staffType[i]+"s: "+dep_staff[i]);
        }
        System.out.println("\nTotal Days worked by Departed Staff:");
        for (int i=0;i<4;i++){
            System.out.println(staffType[i]+"s: "+dep_days_worked[i]);
        }
        System.out.println("\nTotal Bonus of Departed Staff:");
        for (int i=0;i<4;i++){
            System.out.println(staffType[i]+"s: "+dep_total_bonus[i]);
        }
        System.out.println("\nTotal Normal Pay of Departed Staff:");
        for (int i=0;i<4;i++){
            System.out.println(staffType[i]+"s: "+dep_total_normal_pay[i]);
        }
    }
}
