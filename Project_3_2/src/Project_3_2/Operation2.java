package Project_3_2;

import java.io.IOException;

//Obs end
//For 2nd FNCD
class Operation2 extends Staff2{
    static int budget = 500000;
    static int total_sales = 0;
    static int day_count;
    static int added_money=0;
    static int Staff_money=0;
    static int FNCD_money=0;
    static String file_name=null;
    //printing the summary of staff
    static void addBudget(int i,int j,FNCDdata2 fnc) throws IOException {
        if(budget-salary[i].get(j)<=0){       //If we run out of budget, $250000 is added to the budget
            budget=budget+250000;
            System.out.println("Added $250000 in South FNCD budget due to low budget while processing Salary for Staff");
            added_money=added_money+250000;
            fnc.dayEnd(0,0,4);//obs
        }
    }
    static void addBudget(int i,FNCDdata2 fnc) throws IOException {
        if(budget-Vehicle2.cost_price[i].get(Vehicle2.vehicle[i].size()-1)<=0){
            budget=budget+250000;       //If we run out of budget, $250000 is added to the budget
            System.out.println("Added $250000 in South FNCD budget due to low budget while buying required Vehicles");
            added_money=added_money+250000;
            fnc.dayEnd(0,0,5);//obs
        }
    }
    public void Print(){
        System.out.println("\n************************************************************SUMMARY OF SOUTH FNCD************************************************************");
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

    public static void allOperations() throws IOException {
        Staff staff1 = new Staff();
        staff1.init();
        staff1.add_names();
        staff1.addStaff();
        Internfactory i = new Internfactory();
        Intern int1 = (Intern) i.createStaff();
        Mechanicfactory m = new Mechanicfactory();
        Mechanic mec1 = (Mechanic) m.createStaff();
        Salespersonfactory s = new Salespersonfactory();
        Salesperson sal1 = (Salesperson) s.createStaff();
        Buyerfactory b = new Buyerfactory();
        Buyer buy1 = (Buyer) b.createStaff();
        Driverfactory d = new Driverfactory();
        Driver dri1 = (Driver) d.createStaff();
        Vehicle vel1 = new Vehicle();
        vel1.init2();
        vel1.add_vehicle_names();
        vel1.add_MonsterTruck_names();
        vel1.addVehicles();
        Operation op1 = new Operation();
        FNCDdata fnc1 = new FNCDdata();//obs
        Staff2 staff2 = new Staff2();
        staff2.init();
        staff2.add_names();
        staff2.addStaff();
        Internfactory2 i2 = new Internfactory2();
        Intern2 int2 = (Intern2) i2.createStaff();
        Mechanicfactory2 m2 = new Mechanicfactory2();
        Mechanic2 mec2 = (Mechanic2) m2.createStaff();
        Salespersonfactory2 s2 = new Salespersonfactory2();
        Salesperson2 sal2 = (Salesperson2) s2.createStaff();
        Buyerfactory2 b2 = new Buyerfactory2();
        Buyer2 buy2 = (Buyer2) b2.createStaff();
        Driverfactory2 d2 = new Driverfactory2();
        Driver2 dri2 = (Driver2) d2.createStaff();
        Vehicle2 vel2 = new Vehicle2();
        vel2.init2();
        vel2.add_vehicle_names();
        vel2.add_MonsterTruck_names();
        vel2.addVehicles();
        Operation2 op2 = new Operation2();
        FNCDdata2 fnc2 = new FNCDdata2();//obs
        Tracker tra2 = Tracker.getInstance2();//obs
        Tester t = new Tester();
        for (day_count=1;day_count<32;day_count++){
            Operation.day_count = day_count;
            Logger log1 = Logger.getInstance(fnc1);//Obs
            Operation.outputLogger();
            Logger log2 = Logger.getInstance2(fnc2);//Obs
            buy1.init3();
            buy2.init3();
            System.out.println("\n***Day "+day_count+"***");//Display Day Number
            System.out.println("\nCurrent Budget of North FNCD: "+Operation.budget);
            System.out.println("Current Budget of South FNCD: "+budget);
            fnc1.dayAct(0,Operation.budget);//obs
            fnc2.dayAct(0,budget);//obs
            buy1.addBuyer();
            buy2.addBuyer();
            int1.setWashBehavior(fnc1);
            int2.setWashBehavior(fnc2);
            mec1.repair(fnc1);
            mec2.repair(fnc2);
            if (day_count ==31){ //if day is 31 then use command patter for user inputs to buy vehicle
                commandReceiver receiver = new commandReceiver();
                Command command = new concreteCommand(receiver);
                commandInvoker invoker = new commandInvoker();
                invoker.setCommand(command);
                invoker.executeCommand();
                if(commandReceiver.FNCD_choice==0 && commandReceiver.user_decision == 0) {
                    sal1.sale2(fnc1);
                } else if (commandReceiver.FNCD_choice==1 && commandReceiver.user_decision == 0) {
                    sal2.sale2(fnc2);
                }
            }
            else{
                sal1.sale(fnc1);
                sal2.sale(fnc2);
            }
            dri1.getRaceVehicles(fnc1);
            dri1.race(fnc1);
            dri2.getRaceVehicles(fnc2);
            dri2.race(fnc2);
            System.out.println("\nDay End in North FNCD");
            fnc1.dayAct(5,0);//obs
            int1.getSalary(fnc1);
            int1.getTotalDays();
            mec1.getSalary(fnc1);
            mec1.getTotalDays();
            sal1.getSalary(fnc1);
            sal1.getTotalDays();
            dri1.getSalary(fnc1);
            dri1.getTotalDays();
            staff1.updateDepartedStaff(fnc1);
            int1.dropIntern();
            int1.addIntern(fnc1);
            dri1.dropDriver();
            dri1.addDriver(fnc1);
            vel1.addVehicle(fnc1);
            System.out.println("\nDay End in South FNCD");
            fnc2.dayAct(5,0);//obs
            int2.getSalary(fnc2);
            int2.getTotalDays();
            mec2.getSalary(fnc2);
            mec2.getTotalDays();
            sal2.getSalary(fnc2);
            sal2.getTotalDays();
            dri2.getSalary(fnc2);
            dri2.getTotalDays();
            staff2.updateDepartedStaff(fnc2);
            int2.dropIntern();
            int2.addIntern(fnc2);
            dri2.dropDriver();
            dri2.addDriver(fnc2);
            vel2.addVehicle(fnc2);
            System.out.println("\nTotal sales for the day in North FNCD: "+Operation.total_sales);
            System.out.println("Total sales for the day in South FNCD: "+total_sales);
            fnc1.dayAct(6,Operation.total_sales);//obs
            fnc2.dayAct(6,total_sales);//obs
            System.out.println("Remaining Budget of North FNCD: "+Operation.budget);
            System.out.println("Remaining Budget of South FNCD: "+budget+"\n");
            fnc1.trackerOutcome(day_count,0);//obs
            fnc1.trackerOutcome(day_count,1);
            fnc2.trackerOutcome(day_count,1);
            Operation.writer1.close();
        }
        op1.Print();
        vel1.Print2();
        op2.Print();
        vel2.Print2();

        //junit testing
        t.checkCars();
        t.checkPickups();
        t.checkPerformanceCars();
        t.checkMotorcyclesCars();
        t.checkMonsterTrucks();
        t.checkElectricCars();
        t.checkBudgetCars();
        t.checkLuxuryCars();
        t.checkSuperCars();
        t.checkInterns();
        t.checkMechanics();
        t.checkSalespersons();
        t.checkDrivers();
        t.checkBudgetFNCDNorth();
        t.checkBudgetFNCDSouth();
    }
    //main method
    public static void main(String[] args) throws IOException {
        allOperations();
    }
}
