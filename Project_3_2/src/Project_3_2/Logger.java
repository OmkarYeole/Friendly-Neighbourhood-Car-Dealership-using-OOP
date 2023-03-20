package Project_3_2;

import java.io.IOException;

//Singleton Pattern using a Lazy instantiation
class Logger implements Subscriber,Subscriber2{
    private static Publisher fncdData;
    private static Publisher2 fncdData2;
    private static Logger logger;
    private static Logger logger2;
    private Logger(Publisher fncdData){
        this.fncdData = fncdData;
        this.fncdData.registerSubscriber(this);
    }
    public static Logger getInstance(Publisher fncdData){
        if (logger==null) {
            logger = new Logger(fncdData);
        }
        return logger;
    }
    private Logger(Publisher2 fncdData){
        this.fncdData2 = fncdData;
        this.fncdData2.registerSubscriber((Subscriber2) this);
    }
    public static Logger getInstance2(Publisher2 fncdData){
        if (logger2==null) {
            logger2 = new Logger(fncdData);
        }
        return logger2;
    }
    public void updateAct(Subscriber s,int i,int j) throws IOException {
        if (i==0){
            Operation.writer1.write("Current Budget in North FNCD: "+j+"\n");
        }else if (i==1){
            Operation.writer1.write("\nWashing in North FNCD\n");
        }else if (i==2){
            Operation.writer1.write("\nRepairing in North FNCD\n");
        }else if (i==3){
            Operation.writer1.write("\nSelling in North FNCD\n");
        }else if (i==4){
            Operation.writer1.write("\nRacing in North FNCD\n");
        }else if (i==5){
            Operation.writer1.write("\nDay End in North FNCD\n");
        }else if (i==6){
            Operation.writer1.write("\nTotal sales for the day in North FNCD: "+j+"\n");
            Operation.FNCD_money=Operation.FNCD_money+Operation.total_sales;
        }
    }
    public void updateWash(int i,int j,int k,Vehicle obj,String vehicle,int l,int m,int n,int bonus) throws IOException {
        if (n!=2){      //cleanliness not equal to sparkling
            Operation.writer1.write(Staff.staffType[i]+" " +Staff.staff[i].get(j)+" washed a "+Vehicle.carCleanliness[k]+" "+Vehicle.carType[obj.getCarType(vehicle)]+" "+vehicle+" using "+Intern.wash_method[l]+" method"+Intern.clean_outcome[m]+Vehicle.carCleanliness[n]+"\n");
        }
        else if (n==2){ //cleanliness equal to sparkling
            Operation.writer1.write(Staff.staffType[i]+" " +Staff.staff[i].get(j)+" washed a "+Vehicle.carCleanliness[k]+" "+Vehicle.carType[obj.getCarType(vehicle)]+" "+vehicle+" using "+Intern.wash_method[l]+" method"+Intern.clean_outcome[m]+Vehicle.carCleanliness[n]+" (and earned bonus of $"+bonus+")\n");
        }
    }
    public void updateWash(String vehicle,int i) throws IOException {
        Operation.writer1.write("The "+vehicle+" has become "+Vehicle.carCondition[i]+"\n");
    }
    public void updateRepair(Subscriber s,int i,int j, Vehicle obj,String vehicle,int k,int bonus) throws IOException {
        if(k==0){              //vehicle is fixed
            Operation.writer1.write(Staff.staffType[i]+" "+Staff.staff[i].get(j)+" repaired a "+Vehicle.carCondition[obj.getCondition(vehicle)]+" "+Vehicle.carType[obj.getCarType(vehicle)]+" "+vehicle+Intern.clean_outcome[k]+Vehicle.carCondition[obj.getCondition(vehicle)+1]+" (and earned a bonus of $"+bonus+")");
        }else if(k==1){        //vehicle is not fixed
            Operation.writer1.write(Staff.staffType[1]+" "+Staff.staff[1].get(j)+" repaired a "+Vehicle.carCondition[obj.getCondition(vehicle)]+" "+Vehicle.carType[obj.getCarType(vehicle)]+" "+vehicle+" but it was still "+Vehicle.carCondition[obj.getCondition(vehicle)]);
        }
    }
    public void updateRepair(Subscriber s,Vehicle obj,String vehicle) throws IOException {
        Operation.writer1.write(" and the vehicle became "+Vehicle.carCleanliness[obj.getCleanliness(vehicle)]+"\n");
    }
    public void updateSell(Subscriber s,int i,String person,Vehicle obj,String vehicle,int buyer_choice,String buyer,int vehicle_choice,int bonus) throws IOException {
        Operation.writer1.write(Staff.staffType[i]+" "+person +" has sold a "+Vehicle.carCleanliness[obj.getCleanliness(vehicle)]+" "+Vehicle.carCondition[obj.getCondition(vehicle)]+" "+Vehicle.carType[buyer_choice] +" "+ vehicle +" to "+buyer+" for $"+Vehicle.sales_price[buyer_choice].get(vehicle_choice)+" (and earned a bonus of $"+bonus+")\n");
    }
    public void updateSell(Subscriber s,int i,int j,int k,int l) throws IOException {
        if(k==0){       //Sales Price before addon
            Operation.writer1.write("Sales Price of the "+Vehicle.carType[i]+" "+Vehicle.vehicle[i].get(j)+" increased from $"+Vehicle.sales_price[i].get(j));
        }else if(k==1){     //Sales Price after addon
            Operation.writer1.write(" to $"+Vehicle.sales_price[i].get(j)+ " after adding on "+Vehicle.addon[l]+"\n");
        }
    }
    public void updateRace(Subscriber s,int j,int i,int choice,int bonus,int k) throws IOException {
        if(k==0){             //won the race
            Operation.writer1.write(Staff.staffType[3]+" "+Staff.staff[3].get(i)+" finished the race at position "+Driver.race_position.get(i)+" with the "+Vehicle.carType[choice]+" "+Driver.race_vehicles.get(i)+" and Won the race (and earned a bonus of $"+bonus+")\n");
        }else if(k==2){       //finished bottom 5
            Operation.writer1.write(Staff.staffType[3]+" "+Staff.staff[3].get(i)+" finished the race at position "+Driver.race_position.get(i)+" with the "+Vehicle.carType[choice]+" "+Driver.race_vehicles.get(i)+" and the Vehicle became Broken \n");
        }else if(k==1){       //finished 4-15
            Operation.writer1.write(Staff.staffType[3]+" "+Staff.staff[3].get(i)+" finished the race at position "+Driver.race_position.get(i)+" with the "+Vehicle.carType[choice]+" "+Driver.race_vehicles.get(i)+"\n");
        }
    }
    public void updateRace(Subscriber s,int j,int i) throws IOException {
        Operation.writer1.write("Driver "+Staff.staff[3].get(i)+" got injured and quit the FNCD\n");
    }
    public void updateEnd(Subscriber s,int i,int j,int k) throws IOException {
        if (k==0){
            Operation.writer1.write(Staff.staffType[i]+" " +Staff.staff[i].get(j)+" quit the FNCD\n");
        }else if (k==1){
            Operation.writer1.write("Hired a new Intern "+(Staff.staff[i].get(j))+"\n");
        }else if (k==2){
            Operation.writer1.write("Hired a new Driver "+(Staff.staff[i].get(j))+"\n");
        }else if (k==3){
            Operation.writer1.write("Bought "+Vehicle.carType[i]+" "+Vehicle.vehicle[i].get(j)+" for $"+Vehicle.cost_price[i].get(j)+"\n");
        }else if (k==4){
            Operation.writer1.write("Added $250000 in FNCD budget due to low budget while processing Salary for Staff\n");
        }else if (k==5){
            Operation.writer1.write("Added $250000 in FNCD budget due to low budget while buying required Vehicles\n");
        }
    }
    public void update(Subscriber s,int i,int j){
        //Do nothing
    }
    public void updateAct(Subscriber2 s,int i,int j) throws IOException {
        if (i==0){
            Operation.writer1.write("Current Budget in South FNCD: "+j+"\n");
        }else if (i==1){
            Operation.writer1.write("\nWashing in South FNCD\n");
        }else if (i==2){
            Operation.writer1.write("\nRepairing in South FNCD\n");
        }else if (i==3){
            Operation.writer1.write("\nSelling in South FNCD\n");
        }else if (i==4){
            Operation.writer1.write("\nRacing in South FNCD\n");
        }else if (i==5){
            Operation.writer1.write("\nDay End in South FNCD\n");
        }else if (i==6){
            Operation.writer1.write("Total sales for the day in South FNCD: "+j+"\n");
            Operation2.FNCD_money=Operation2.FNCD_money+Operation2.total_sales;
        }
    }
    public void updateWash(int i,int j,int k,Vehicle2 obj,String vehicle,int l,int m,int n,int bonus) throws IOException {
        if (n!=2){      //cleanliness not equal to sparkling
            Operation.writer1.write(Staff2.staffType[i]+" " +Staff2.staff[i].get(j)+" washed a "+Vehicle2.carCleanliness[k]+" "+Vehicle2.carType[obj.getCarType(vehicle)]+" "+vehicle+" using "+Intern2.wash_method[l]+" method"+Intern2.clean_outcome[m]+Vehicle2.carCleanliness[n]+"\n");
        }
        else if (n==2){ //cleanliness equal to sparkling
            Operation.writer1.write(Staff2.staffType[i]+" " +Staff2.staff[i].get(j)+" washed a "+Vehicle2.carCleanliness[k]+" "+Vehicle2.carType[obj.getCarType(vehicle)]+" "+vehicle+" using "+Intern2.wash_method[l]+" method"+Intern2.clean_outcome[m]+Vehicle2.carCleanliness[n]+" (and earned bonus of $"+bonus+")\n");
        }
    }
    public void updateWash(String vehicle,int i,int j) throws IOException {
        Operation.writer1.write("The "+vehicle+" has become "+Vehicle2.carCondition[i]+"\n");
    }
    public void updateRepair(Subscriber2 s,int i,int j, Vehicle2 obj,String vehicle,int k,int bonus) throws IOException {
        if(k==0){              //vehicle is fixed
            Operation.writer1.write(Staff2.staffType[i]+" "+Staff2.staff[i].get(j)+" repaired a "+Vehicle2.carCondition[obj.getCondition(vehicle)]+" "+Vehicle2.carType[obj.getCarType(vehicle)]+" "+vehicle+Intern2.clean_outcome[k]+Vehicle2.carCondition[obj.getCondition(vehicle)+1]+" (and earned a bonus of $"+bonus+")");
        }else if(k==1){        //vehicle is not fixed
            Operation.writer1.write(Staff2.staffType[1]+" "+Staff2.staff[1].get(j)+" repaired a "+Vehicle2.carCondition[obj.getCondition(vehicle)]+" "+Vehicle2.carType[obj.getCarType(vehicle)]+" "+vehicle+" but it was still "+Vehicle2.carCondition[obj.getCondition(vehicle)]);
        }
    }
    public void updateRepair(Subscriber2 s,Vehicle2 obj,String vehicle) throws IOException {
        Operation.writer1.write(" and the vehicle became "+Vehicle2.carCleanliness[obj.getCleanliness(vehicle)]+"\n");
    }
    public void updateSell(Subscriber2 s,int i,String person,Vehicle2 obj,String vehicle,int buyer_choice,String buyer,int vehicle_choice,int bonus) throws IOException {
        Operation.writer1.write(Staff2.staffType[i]+" "+person +" has sold a "+Vehicle2.carCleanliness[obj.getCleanliness(vehicle)]+" "+Vehicle2.carCondition[obj.getCondition(vehicle)]+" "+Vehicle2.carType[buyer_choice] +" "+ vehicle +" to "+buyer+" for $"+Vehicle2.sales_price[buyer_choice].get(vehicle_choice)+" (and earned a bonus of $"+bonus+")\n");
    }
    public void updateSell(Subscriber2 s,int i,int j,int k,int l) throws IOException {
        if(k==0){       //Sales Price before addon
            Operation.writer1.write("Sales Price of the "+Vehicle2.carType[i]+" "+Vehicle2.vehicle[i].get(j)+" increased from $"+Vehicle2.sales_price[i].get(j));
        }else if(k==1){     //Sales Price after addon
            Operation.writer1.write(" to $"+Vehicle2.sales_price[i].get(j)+ " after adding on "+Vehicle2.addon[l]+"\n");
        }
    }
    public void updateRace(Subscriber2 s,int j,int i,int choice,int bonus,int k) throws IOException {
        if(k==0){             //won the race
            Operation.writer1.write(Staff2.staffType[3]+" "+Staff2.staff[3].get(i)+" finished the race at position "+Driver2.race_position.get(i)+" with the "+Vehicle2.carType[choice]+" "+Driver2.race_vehicles.get(i)+" and Won the race (and earned a bonus of $"+bonus+")\n");
        }else if(k==2){       //finished bottom 5
            Operation.writer1.write(Staff2.staffType[3]+" "+Staff2.staff[3].get(i)+" finished the race at position "+Driver2.race_position.get(i)+" with the "+Vehicle2.carType[choice]+" "+Driver2.race_vehicles.get(i)+" and the Vehicle became Broken \n");
        }else if(k==1){       //finished 4-15
            Operation.writer1.write(Staff2.staffType[3]+" "+Staff2.staff[3].get(i)+" finished the race at position "+Driver2.race_position.get(i)+" with the "+Vehicle2.carType[choice]+" "+Driver2.race_vehicles.get(i)+"\n");
        }
    }
    public void updateRace(Subscriber2 s,int j,int i) throws IOException {
        Operation.writer1.write("Driver "+Staff2.staff[3].get(i)+" got injured and quit the FNCD\n");
    }
    public void updateEnd(Subscriber2 s,int i,int j,int k) throws IOException {
        if (k==0){
            Operation.writer1.write(Staff2.staffType[i]+" " +Staff2.staff[i].get(j)+" quit the FNCD\n");
        }else if (k==1){
            Operation.writer1.write("Hired a new Intern "+(Staff2.staff[i].get(j))+"\n");
        }else if (k==2){
            Operation.writer1.write("Hired a new Driver "+(Staff2.staff[i].get(j))+"\n");
        }else if (k==3){
            Operation.writer1.write("Bought "+Vehicle2.carType[i]+" "+Vehicle2.vehicle[i].get(j)+" for $"+Vehicle2.cost_price[i].get(j)+"\n");
        }else if (k==4){
            Operation.writer1.write("Added $250000 in FNCD budget due to low budget while processing Salary for Staff\n");
        }else if (k==5){
            Operation.writer1.write("Added $250000 in FNCD budget due to low budget while buying required Vehicles\n");
        }
    }
    public void update(Subscriber2 s,int i,int j){
        //Do nothing
    }
}
