package Project_3_2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//Command Pattern
interface Command{
    public void execute();
}

class commandInvoker{
    private Command command;
    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}

class commandReceiver{
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);
    static String salesperson=null;
    static int FNCD_choice,user_choice,user_decision,user_decision2,vehicle_choice,races_won=0,sales_price=0;
    static String vehicle = null,vehicle_type = null,vehicle_condition = null,vehicle_cleanliness = null;
    // methods called as the concrete methods require them
    public void getFNCDLocation(){
        System.out.println("\n\n********************Selling with User inputs as Buyer********************\n");
        System.out.println("Enter the FNCD location (0 for North, 1 for South): ");
        FNCD_choice = scanner.nextInt();
        if (FNCD_choice == 0){
            System.out.println("\nFNCD Selected is: FNCD North");
        } else if (FNCD_choice == 1){
            System.out.println("\nFNCD Selected is: FNCD South");
        }
    }
    public void getSalesName(){
        if(FNCD_choice==0){ // Getting Salesperson from North FNCD
            Salesperson.index2 = rand.nextInt(Salesperson.staff[2].size());
            salesperson = Salesperson.staff[2].get(Salesperson.index2);
        } else if (FNCD_choice == 1){ // Getting Salesperson from South FNCD
            Salesperson2.index2 = rand.nextInt(Salesperson2.staff[2].size());
            salesperson = Salesperson2.staff[2].get(Salesperson2.index2);
        }
        System.out.println("\nSalesperson Selected is: " + salesperson);
    }

    //Getting System Time
    public void getTime(){
        System.out.println("\nCurrent time is: "+ java.time.LocalDateTime.now().toLocalTime());
    }
    public void getDifferentSalesperson(){
        int i,index,diff_sal;
        System.out.println("\nDo you want different Salesperson? (0 for Yes, 1 for No): ");
        diff_sal = scanner.nextInt();
        if (diff_sal == 0){
            if (FNCD_choice ==0){
                for (i = 0; i < Staff.staff[2].size(); i++) {
                    if(Staff.staff[2].get(i) == salesperson){
                        break;
                    }
                }
                index = rand.nextInt(3);
                while(index == i){
                    index = rand.nextInt(3);
                }
                salesperson = Staff.staff[2].get(index);
            } else if (FNCD_choice == 1){
                for (i = 0; i < Staff2.staff[2].size(); i++) {
                    if(Staff2.staff[2].get(i) == salesperson){
                        break;
                    }
                }
                index = rand.nextInt(3);
                while(index == i){
                    index = rand.nextInt(3);
                }
                salesperson = Staff2.staff[2].get(index);
            }
            System.out.println("\nDifferent Salesperson Selected is: "+ salesperson);
        }

    }
    public void getInventory(){
        System.out.println("\nCurrent available Inventory of Vehicles:");
        if (FNCD_choice ==0){
            for (int i=0;i<9;i++){// Displaying available inventory from North FNCD
                System.out.println(Vehicle.carType[i]+"s: "+Vehicle.vehicle[i]);
            }
        } else if (FNCD_choice == 1){// Displaying available inventory from South FNCD
            for (int i=0;i<9;i++){
                System.out.println(Vehicle2.carType[i]+"s: "+Vehicle2.vehicle[i]);
            }
        }
    }
    public void getSelectedInventoryDetails() {
        if (FNCD_choice == 0){// get details of selected Inventory from the North FNCD
            Vehicle obj = new Vehicle();
            System.out.println("\nEnter the choice of Vehicle Type (e.g. 0 for Car, 1 for Pickup, 8 for Super Car):   ");
            user_choice = scanner.nextInt();
            obj.VehicleTopPrice();      //getting car with top sales price from User's choice
            if(Vehicle.max_sale_price[user_choice] == 0) {
                user_choice = obj.totalVehicleTopPrice();
            }
            for(int j = 0; j< Vehicle.sales_price[user_choice].size(); j++){
                if(Vehicle.sales_price[user_choice].get(j)== Vehicle.max_sale_price[user_choice]){
                    vehicle= Vehicle.vehicle[user_choice].get(j);
                    vehicle_choice = j;
                    vehicle_type= Vehicle.carType[user_choice];
                    vehicle_condition = Vehicle.carCondition[obj.getCondition(vehicle)];
                    vehicle_cleanliness = Vehicle.carCleanliness[obj.getCleanliness(vehicle)];
                    races_won = Vehicle.race_won[user_choice].get(j);
                    sales_price = Vehicle.sales_price[user_choice].get(j);
                    break;
                }
            }
        } else if (FNCD_choice == 1){// get details of selected Inventory from the South FNCD
            Vehicle2 obj= new Vehicle2();
            System.out.println("\nEnter the choice of Vehicle Type (e.g. 0 for Car, 1 for Pickup, 8 for Super Car:   ");
            user_choice = scanner.nextInt();
            obj.VehicleTopPrice();      //getting car with top sales price from User's choice
            if(Vehicle2.max_sale_price[user_choice] == 0) {
                user_choice = obj.totalVehicleTopPrice();
            }
            for(int j = 0; j< Vehicle2.sales_price[user_choice].size(); j++){
                if(Vehicle2.sales_price[user_choice].get(j)== Vehicle2.max_sale_price[user_choice]){
                    vehicle= Vehicle2.vehicle[user_choice].get(j);
                    vehicle_choice = j;
                    vehicle_type= Vehicle2.carType[user_choice];
                    vehicle_condition = Vehicle2.carCondition[obj.getCondition(vehicle)];
                    vehicle_cleanliness = Vehicle2.carCleanliness[obj.getCleanliness(vehicle)];
                    races_won = Vehicle2.race_won[user_choice].get(j);
                    sales_price = Vehicle2.sales_price[user_choice].get(j);
                    break;
                }
            }
        }
        System.out.println("\nUser selected Vehicle Type: "+vehicle_type+"\nSelected Vehicle for selling: "+vehicle);
        System.out.println("Condition of Vehicle: "+vehicle_condition+"\nCleanliness of Vehicle: "+vehicle_cleanliness);
        System.out.println("Races won by Vehicle: "+races_won+"\nSales Price of Vehicle: $"+sales_price);
        System.out.println("\nWould you like to purchase the vehicle? (0 for Yes, 1 for No): ");
        user_decision = scanner.nextInt();
    }
    public void buyVehicleAddOn() {
        if (user_decision==0){
            System.out.println("\nWould you like to purchase Add-ons? (0 for Yes, 1 for No): ");
            user_decision2 = scanner.nextInt();
            if (user_decision2==0){// Assumption: if user wants add-ons, it will be purchased based on probability,
                if(FNCD_choice==0){// so it may happen that even if user says yes to buy add-ons, no add-ons will be added due to low probability
                    Vehicle vecl = Salesperson.vehicle_object[user_choice];//creating object of one of the car types of buyer's choice
                    vecl = new ExtendedWarranty(user_choice,vehicle_choice);//wrapping it with decorator components
                    vecl = new Undercoating(user_choice,vehicle_choice);
                    vecl = new RoadRescueCoverage(user_choice,vehicle_choice);
                    vecl = new SatelliteRadio(user_choice,vehicle_choice);
                }else if (FNCD_choice == 1){
                    Vehicle2 vecl = Salesperson2.vehicle_object[user_choice];//creating object of one of the car types of buyer's choice
                    vecl = new ExtendedWarranty2(user_choice,vehicle_choice);//wrapping it with decorator components
                    vecl = new Undercoating2(user_choice,vehicle_choice);
                    vecl = new RoadRescueCoverage2(user_choice,vehicle_choice);
                    vecl = new SatelliteRadio2(user_choice,vehicle_choice);
                }
            }
        }
    }
    public void buyVehicle(){
        if(user_decision==0) {
            System.out.println("\nUser Purchased the " + vehicle_type + " " + vehicle + " for: $" + sales_price);
        }
    }
    public void endInteraction(){
        System.out.println("\nUser Interaction ended\n");
    }
}

class concreteCommand implements Command{
    private commandReceiver receiver;

    public concreteCommand(commandReceiver receiver) {
        this.receiver = receiver;
    }
    public void execute() {
        receiver.getFNCDLocation();
        receiver.getSalesName();
        receiver.getTime();
        receiver.getDifferentSalesperson();
        receiver.getInventory();
        receiver.getSelectedInventoryDetails();
        receiver.buyVehicleAddOn();
        receiver.buyVehicle();
        receiver.endInteraction();
    }
}
//Factory Pattern
//2nd FNCD
interface Vehicles2{
    void init2();
    void add_vehicle_names();
    void add_MonsterTruck_names();
    String getMonsterTruckName();
    String getcarName();
    void addVehicles();
    void addVehicle(FNCDdata2 fnc) throws IOException;
    void setCondition(String chosenVehicle);
    void setCleanliness (String chosenVehicle);
    int getCarType(String vehicle1);
    int getCondition(String vehicle);
    int getCondition2(String vehicle);
    int getCleanliness(String vehicle);
    int getCleanliness2(String vehicle);
    void VehicleTopPrice();
    int totalVehicleTopPrice();
    void updateSalesPrice(int index, String req_vehicle);
    void updateCleanliness(String req_vehicle);
    void updateCondition(int index, String req_vehicle);
    void Print2();
}

interface factoryVehicle2{
    Vehicles2 createVehicle();
}

class Carfactory2 implements factoryVehicle2{

    @Override
    public Vehicles2 createVehicle() {
        return (Vehicles2) new Car2();
    }
}
class Pickupfactory2 implements factoryVehicle2{

    @Override
    public Vehicles2 createVehicle() {
        return (Vehicles2) new Pickup2();
    }
}
class PerformanceCarfactory2 implements factoryVehicle2{

    @Override
    public Vehicles2 createVehicle() {
        return (Vehicles2) new PerformanceCar2();
    }
}
class Motorcyclesfactory2 implements factoryVehicle2{

    @Override
    public Vehicles2 createVehicle() {
        return (Vehicles2) new Motorcycles2();
    }
}
class MonsterTrucksfactory2 implements factoryVehicle2{

    @Override
    public Vehicles2 createVehicle() {
        return (Vehicles2) new MonsterTrucks2();
    }
}
class ElectricCarfactory2 implements factoryVehicle2{

    @Override
    public Vehicles2 createVehicle() {
        return (Vehicles2) new ElectricCar2();
    }
}
class BudgetCarfactory2 implements factoryVehicle2{

    @Override
    public Vehicles2 createVehicle() {
        return (Vehicles2) new BudgetCar2();
    }
}
class LuxuryCarfactory2 implements factoryVehicle2{

    @Override
    public Vehicles2 createVehicle() {
        return (Vehicles2) new LuxuryCar2();
    }
}
class SuperCarfactory2 implements factoryVehicle2{

    @Override
    public Vehicles2 createVehicle() {
        return (Vehicles2) new SuperCar2();
    }
}
//For 2nd FNCD
class Vehicle2 implements Vehicles2{
    static ArrayList<String> car_names = new ArrayList<>();
    static ArrayList<String> monster_truck_names = new ArrayList<>();
    static String[] carType = {"Car", "Pickup", "Performance Car","Motorcycle","Monster Truck","Electric Car","Budget Car","Luxury Car","Super Car"};
    static String[] carCondition = {"Broken", "Used", "Like New"};
    static String[] carCleanliness = {"Dirty","Clean","Sparkling"};
    static ArrayList<String>[] vehicle = new ArrayList[9];
    static ArrayList<Integer>[] race_won = new ArrayList[9];
    static ArrayList<String>[] status = new ArrayList[9];
    static ArrayList<String>[] condition = new ArrayList[3];
    static ArrayList<String>[] cleanliness = new ArrayList[3];
    static ArrayList<Integer>[] cost_price = new ArrayList[9];
    static ArrayList<Integer>[] sales_price = new ArrayList[9];
    static ArrayList<String>[] soldVehicles = new ArrayList[9];
    static int[] max_sale_price = {0,0,0,0,0,0,0,0,0};
    String conditionSelected = null;
    String lastOccurrence = null;
    static ArrayList<Integer> range = new ArrayList<>();
    static ArrayList<Double> engine_size = new ArrayList<>();
    Random rand= new Random();
    static int[] vehicle_wash_bonus = {50, 75, 100, 50, 75, 100, 50, 75, 100};
    static int[] vehicle_repair_bonus = {100, 125, 150, 100, 125, 150, 100, 125, 150};
    static int[] vehicle_sale_bonus = {150, 175, 200, 150, 175, 200, 150, 175, 200};
    static int[] race_win_bonus = {0, 200, 225, 250, 275, 0, 0, 300, 325};
    static String[] addon={"Extended Warranty","Undercoating","Road Rescue Coverage","Satellite Radio"};

    //Initializing variables
    public void init2(){
        for(int i = 0; i < vehicle.length; i++){
            vehicle[i] = new ArrayList<>();
            status[i] = new ArrayList<>();
            cost_price[i] = new ArrayList<>();
            sales_price[i] = new ArrayList<>();
            soldVehicles[i] = new ArrayList<>();
            race_won[i] = new ArrayList<>();
        }
        for(int j = 0;j < 3;j++){
            condition[j] = new ArrayList<>();
            cleanliness[j] = new ArrayList<>();
        }
    }

    //this method gives a list of car names
    public void add_vehicle_names() {
        car_names.addAll(Arrays.asList(
                "Toyota Camry", "Honda Civic", "Ford Mustang", "Chevrolet Corvette", "Mazda MX-5 Miata",
                "Porsche 911", "Dodge Charger", "Nissan Altima", "Jeep Wrangler", "Audi A4",
                "BMW M3", "Mercedes-Benz S-Class", "Tesla Model S", "Kia Telluride", "Subaru Outback",
                "Lamborghini Aventador", "Ferrari F8 Tributo", "Maserati Levante", "Jaguar F-Type", "Land Rover Defender",
                "Rolls-Royce Ghost", "Bentley Continental GT", "Aston Martin DB11", "McLaren 720S", "Bugatti Chiron",
                "Ford F-150", "Chevrolet Silverado", "Dodge Ram", "GMC Sierra", "Toyota Tundra",
                "Nissan Titan", "Honda Ridgeline", "Jeep Gladiator", "Tesla Cybertruck", "Ford Escape",
                "Chevrolet Equinox", "Mazda CX-5", "Toyota RAV4", "Honda CR-V", "Jeep Grand Cherokee",
                "Subaru Forester", "Lexus RX", "Audi Q5", "BMW X5", "Mercedes-Benz GLE",
                "Tesla Model X", "Kia Sorento", "Ford Explorer", "Chevrolet Traverse", "Dodge Durango",
                "Nissan Pathfinder", "Honda Pilot", "Jeep Cherokee", "Toyota 4Runner", "Subaru Ascent",
                "GMC Acadia", "Volvo XC90", "Lincoln Navigator", "Cadillac Escalade", "Porsche Cayenne",
                "Land Rover Range Rover", "Bentley Bentayga", "Audi Q7", "BMW X7", "Mercedes-Benz GLS",
                "Tesla Model Y", "Kia Carnival", "Chrysler Pacifica", "Toyota Sienna", "Honda Odyssey",
                "Dodge Grand Caravan", "Nissan Quest", "Jeep Grand Wagoneer", "Ford Bronco", "Chevrolet Blazer",
                "Jeep Renegade", "Nissan Kicks", "Toyota C-HR", "Honda HR-V", "Ford EcoSport",
                "Kia Soul", "Mazda CX-30", "Subaru Crosstrek", "Hyundai Kona", "Chevrolet Trax",
                "Jeep Compass", "Nissan Rogue Sport", "Toyota Corolla Cross","Koenigsegg Jesko","Lexus LC 500"));
    }
    // 44 Monster Truck names
    public void add_MonsterTruck_names(){
        monster_truck_names.addAll(Arrays.asList("Air Force Afterburner", "Avenger", "Bad News Travels Fast", "Batman",
                "Backwards Bob","Bear Foot 1979", "Bear Foot F-150", "Bear Foot 2xtreme", "Bear Foot Silverado",
                "Bear Foot USA", "BigFoot", "Black Stallion", "Blacksmith", "Blue Thunder", "Bounty Hunter",
                "Brutus", "Bulldozer", "Captain's Curse", "Cyborg", "El Toro Loco", "Grave Digger", "Grinder", "Gunslinger",
                "Jurassic Attack", "King Krunch", "Lucas Oil Crusader", "Madusa", "Maximum Destruction Max-D", "Mohawk Warrior",
                "Monster Mutt", "Predator", "Shell Camino", "Raminator", "Snake Bite", "Stone Crusher",
                "Sudden Impact", "Swamp Thing", "The Destroyer", "The Felon", "USA-1", "War Wizard", "WCW Nitro Machine", "Zombie"));
    }

    public String getMonsterTruckName(){
        String mt_name;
        int i = rand.nextInt(monster_truck_names.size());
        mt_name = monster_truck_names.get(i);
        monster_truck_names.remove(i);
        return mt_name;
    }

    public String getcarName() {
        String name;
        int i = rand.nextInt(car_names.size());
        name = car_names.get(i);
        car_names.remove(i);
        return name;
    }
    //Adding initial vehicles and data for the first day
    public void addVehicles(){
        vehicle[0].addAll(Arrays.asList("Hyundai Elantra","Nissan Sentra","Chevrolet Spark","Kia Forte","Honda Accord","Hyundai Sonata"));
        vehicle[1].addAll(Arrays.asList("Ford Ranger","GMC Canyon","Chevrolet Colorado","Ram 1500 Classic","Nissan Frontier","Toyota Tacoma"));
        vehicle[2].addAll(Arrays.asList("Audi RS7","BMW M8","Mercedes-AMG GT","Porsche Panamera","Ferrari SF90 Stradale","Porsche 718 Cayman GT4"));
        vehicle[3].addAll(Arrays.asList("Aston Martin Vantage","McLaren Artura","Lamborghini Huracan","Porsche Taycan","Suzuki Hayabusa","Yamaha YZF-R6"));
        vehicle[4].addAll(Arrays.asList("Swamp Thing","Sudden Impact","USA-1","Bear Foot F-150","Iron Outlaw","Rampage"));
        vehicle[5].addAll(Arrays.asList("Audi R8","BMW i8","Mercedes-AMG GT 4-Door Coupe","Rolls-Royce Cullinan","Rivian R1T","Polestar 2"));
        vehicle[6].addAll(Arrays.asList("Toyota Yaris","Honda Fit","Kia Rio","Hyundai Accent","Nissan Versa","Suzuki Swift"));
        vehicle[7].addAll(Arrays.asList("Rolls-Royce Phantom","Lexus LS","BMW 7 Series","Jaguar XJ","Mercedes-Benz E-Class","Acura RLX"));
        vehicle[8].addAll(Arrays.asList("Bugatti Veyron","McLaren 765LT","Ferrari 812 Superfast","Pagani Huayra","Aston Martin Valkyrie","Rimac C2"));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 6; j++) {
                setCondition(vehicle[i].get(j));
                setCleanliness(vehicle[i].get(j));
                status[i].add("In Stock");
                race_won[i].add(0);
            }
        }
        //Initially added cost price and sales price using the logic sales price = cost price*2
        //But cost price is reduced according to the car condition So, after reducing the cost price, we added it to the list of cost price
        //And hence we see the difference between cost and sales price to be more that 2x for some cars
        cost_price[0].addAll(Arrays.asList(12738, 15840, 14099, 15548, 13875, 6660));
        cost_price[1].addAll(Arrays.asList(10138, 14950, 29560, 12342, 13175, 18538));
        cost_price[2].addAll(Arrays.asList(24427, 28972, 15907, 27326, 37178, 24469));
        cost_price[3].addAll(Arrays.asList(13981, 15556, 16421, 8289, 8417, 11666));
        cost_price[4].addAll(Arrays.asList(10600, 23285, 38495, 28509, 19278, 26031));
        cost_price[5].addAll(Arrays.asList(20170, 20619, 30528, 36973, 24910, 32723));
        cost_price[6].addAll(Arrays.asList(7812, 5678, 8937, 9543, 6789, 8520));
        cost_price[7].addAll(Arrays.asList(38754, 42678, 31905, 45621, 33987, 47500));
        cost_price[8].addAll(Arrays.asList(51543, 49810, 44237, 58401, 46695, 58931));
        sales_price[0].addAll(Arrays.asList(25476, 31680, 28198, 31096, 27750, 13320));
        sales_price[1].addAll(Arrays.asList(20276, 29900, 59120, 24684, 26350, 37076));
        sales_price[2].addAll(Arrays.asList(48854, 57944, 31814, 54652, 74356, 48938));
        sales_price[3].addAll(Arrays.asList(27962, 31112, 32842, 16578, 16834, 23332));
        sales_price[4].addAll(Arrays.asList(21200, 46570, 76990, 57018, 38556, 52062));
        sales_price[5].addAll(Arrays.asList(40340, 41238, 61056, 73946, 49820, 65446));
        sales_price[6].addAll(Arrays.asList(15624, 11356, 17874, 19086, 13578,17040));
        sales_price[7].addAll(Arrays.asList(77508, 85356, 63810, 91242, 67974,95000));
        sales_price[8].addAll(Arrays.asList(103086, 99620, 88474, 116802, 93390, 117862));
        Operation2.budget =500000;//Initial Day 1 beginning budget
    }
    //when the count of a vehicle type is less than 6, new vehicle is purchased
    public void addVehicle(FNCDdata2 fnc) throws IOException {
        for (int i = 0; i < 9; i++) {
            while (vehicle[i].size() < 6) {
                if (i == 4) {
                    String name = getMonsterTruckName();
                    for (int j = vehicle[i].size() - 1; j >= 0; j--) {
                        if (vehicle[i].get(j).contains(name)) {       //check if generated name already exists
                            lastOccurrence = vehicle[i].get(j);
                            char last_char = lastOccurrence.charAt(lastOccurrence.length()-1);
                            if(Character.isDigit(last_char)){         //if already a name exists more than once, e.g. "Viper 2"
                                name = name+" "+(Integer.parseInt(String.valueOf(last_char))+1);  //so now, adding new name as "Viper 3"
                            } else {                                  //name exists once, eg."Viper"
                                name = name + " 2";                   //so now, adding new name as "Viper 2"
                            }
                            break;
                        }                                             //if not, add the name as it is, e.g. "Viper"
                    }
                    vehicle[i].add(name);
                }
                else{
                    vehicle[i].add(getcarName());
                }
                setCondition(vehicle[i].get(vehicle[i].size()-1));
                setCleanliness(vehicle[i].get(vehicle[i].size()-1));
                status[i].add("In Stock");
                if(i == 0){
                    Carfactory2 c = new Carfactory2();
                    Car2 c1 = (Car2) c.createVehicle();
                    c1.buyVehicle(fnc);
                } else if(i == 1){
                    Pickupfactory2 c = new Pickupfactory2();
                    Pickup2 c1 = (Pickup2) c.createVehicle();
                    c1.buyVehicle(fnc);
                } else if(i == 2){
                    PerformanceCarfactory2 c = new PerformanceCarfactory2();
                    PerformanceCar2 c1 = (PerformanceCar2) c.createVehicle();
                    c1.buyVehicle(fnc);
                } else if (i == 3) {
                    Motorcyclesfactory2 c = new Motorcyclesfactory2();
                    Motorcycles2 c1 = (Motorcycles2) c.createVehicle();
                    c1.buyVehicle(fnc);
                } else if (i == 4) {
                    MonsterTrucksfactory2 c = new MonsterTrucksfactory2();
                    MonsterTrucks2 c1 = (MonsterTrucks2) c.createVehicle();
                    c1.buyVehicle(fnc);
                } else if (i == 5) {
                    ElectricCarfactory2 c = new ElectricCarfactory2();
                    ElectricCar2 c1 = (ElectricCar2) c.createVehicle();
                    c1.buyVehicle(fnc);
                } else if (i == 6) {//prj
                    BudgetCarfactory2 c = new BudgetCarfactory2();
                    BudgetCar2 c1 = (BudgetCar2) c.createVehicle();
                    c1.buyVehicle(fnc);
                } else if (i == 7) {
                    LuxuryCarfactory2 c = new LuxuryCarfactory2();
                    LuxuryCar2 c1 = (LuxuryCar2) c.createVehicle();
                    c1.buyVehicle(fnc);
                }
                else{
                    SuperCarfactory2 c = new SuperCarfactory2();
                    SuperCar2 c1 = (SuperCar2) c.createVehicle();
                    c1.buyVehicle(fnc);
                }
            }
        }
    }

    //randomly choose a condition for newly added vehicle
    public void setCondition(String chosenVehicle) {
        int i = rand.nextInt(carCondition.length);
        conditionSelected = carCondition[i];
        if (conditionSelected.equals("Broken")) {
            condition[0].add(chosenVehicle);//list of broken vehicles
        } else if (conditionSelected.equals("Used")) {
            condition[1].add(chosenVehicle);//list of used vehicles
        } else {
            condition[2].add(chosenVehicle);//list of like new vehicles
        }
    }
    //randomly choose a cleanliness for newly added vehicle
    public void setCleanliness (String chosenVehicle) {
        double prob = rand.nextDouble();
        if (prob < 0.60) {
            cleanliness[0].add(chosenVehicle);//list of Dirty vehicle
        } else if (prob < 0.95) {
            cleanliness[1].add(chosenVehicle);//list of Clean vehicle
        } else {
            cleanliness[2].add(chosenVehicle);//list of Sparkling vehicle
        }
    }
    //Finding the type of Vehicle
    public int getCarType(String vehicle1){
        //initializing to a value which is different from potential values of index (i.e. 0,1,2)
        int condition_index=10;
        for(int i=0; i<9;i++) {
            for (int j = 0; j < vehicle[i].size(); j++) {
                if (vehicle[i].get(j).equals(vehicle1)) {
                    condition_index = i;
                    break;
                }
            }
        }
        return condition_index;
    }
    //Finding the condition type of vehicle
    public int getCondition(String vehicle){
        //initializing to a value which is different from potential values of index (i.e. 0,1,2)
        int condition_index=10;
        for(int i=0; i<3;i++) {
            for (int j = 0; j < condition[i].size(); j++) {
                if (condition[i].get(j).equals(vehicle)) {
                    condition_index = i;
                    break;
                }
            }
        }
        return condition_index;
    }
    //Finding the position of vehicle in the list of selected condition type
    public int getCondition2(String vehicle){
        //initializing to a value which is different from potential values of index (i.e. 0,1,2)
        int condition_index=10;
        for(int i=0; i<3;i++) {
            for (int j = 0; j < condition[i].size(); j++) {
                if (condition[i].get(j).equals(vehicle)) {
                    condition_index = j;
                    break;
                }
            }
        }
        return condition_index;
    }
    //Finding the cleanliness type of vehicle
    public int getCleanliness(String vehicle){
        //initializing to a value which is different from potential values of index (i.e. 0,1,2)
        int cleanliness_index=10;
        for(int i=0; i<3;i++) {
            for (int j = 0; j < cleanliness[i].size(); j++) {
                if (cleanliness[i].get(j).equals(vehicle)) {
                    cleanliness_index = i;
                    break;
                }
            }
        }
        return cleanliness_index;
    }
    //Finding the position of vehicle in the list of selected cleanliness type
    public int getCleanliness2(String vehicle){
        //initializing to a value which is different from potential values of index (i.e. 0,1,2)
        int cleanliness_index=10;
        for(int i=0; i<3;i++) {
            for (int j = 0; j < cleanliness[i].size(); j++) {
                if (cleanliness[i].get(j).equals(vehicle)) {
                    cleanliness_index = j;
                    break;
                }
            }
        }
        return cleanliness_index;
    }
    //gets maximum sales price of vehicle from each car type
    public void VehicleTopPrice(){
        int maxValue = 0;
        // maximum sales_price in car
        for(int i=0;i<sales_price.length;i++){
            for(int j=0;j<sales_price[i].size();j++){
                // checking for maximum sales price of car where the car is not broken
                if((sales_price[i].get(j) > maxValue) && getCondition(vehicle[i].get(j))!= 0){
                    maxValue = sales_price[i].get(j);
                }
            }
            max_sale_price[i] = maxValue;
            maxValue = 0;
        }
    }
    //gets maximum sales price of vehicle from all car types
    public int totalVehicleTopPrice(){
        int maxValue = 0,top_price_index=0;
        for(int j=0;j<max_sale_price.length;j++){
            if(max_sale_price[j] >  maxValue){
                maxValue = max_sale_price[j];
                top_price_index = j;
            }
        }
        return top_price_index;
    }
    //update the sales price when condition of vehicle is fixed to next level
    public void updateSalesPrice(int index, String req_vehicle){
        if (index==0){
            for(int i=0; i<9;i++) {
                for (int j = 0; j < vehicle[i].size(); j++) {
                    if (vehicle[i].get(j).equals(req_vehicle)) {
                        sales_price[i].set(j, (int) (sales_price[i].get(j) * 1.5));
                        break;
                    }
                }
            }
        } else {
            for(int i=0; i<9;i++) {
                for (int j = 0; j < vehicle[i].size(); j++) {
                    if (vehicle[i].get(j).equals(req_vehicle)) {
                        sales_price[i].set(j, (int) (sales_price[i].get(j) * 1.25));
                        break;
                    }
                }
            }
        }
    }
    //update the cleanliness of vehicle to degrade by one level if not already dirty
    public void updateCleanliness(String req_vehicle){
        for(int i=0; i<3;i++){
            for(int j=0; j<cleanliness[i].size();j++){
                if(cleanliness[i].get(j).equals(req_vehicle)){
                    if (i != 0) {
                        cleanliness[i - 1].add(req_vehicle);
                        cleanliness[i].remove(j);
                    }
                    break;
                }
            }
        }
    }
    //update the condition of vehicle to desired one if it's not already in that condition
    public void updateCondition(int index, String req_vehicle){
        for(int i=0; i<3;i++){
            for(int j=0; j<condition[i].size();j++){
                if(condition[i].get(j).equals(req_vehicle)){  //searching for current condition
                    if (i != index) {                         //if it's not already in desired condition
                        condition[index].add(req_vehicle);    //update the condition
                        condition[i].remove(j);
                    }
                    break;
                }
            }
        }
    }
    //printing summary of vehicle data
    public void Print2(){
        System.out.println("\nList of Vehicles:");
        for (int i=0;i<9;i++){
            System.out.println(carType[i]+"s: "+vehicle[i]);
        }
        System.out.println("\nCondition of Vehicles:");
        for (int i=0;i<3;i++){
            System.out.println(carCondition[i]+"s: "+condition[i]);
        }
        for (int i=0;i<3;i++){
            System.out.println(carCleanliness[i]+"s: "+cleanliness[i]);
        }
        System.out.println("\nStatus of Vehicles:");
        for (int i=0;i<9;i++){
            System.out.println(carType[i]+"s: "+status[i]);
        }
        System.out.println("\nCost price of Vehicles:");
        for (int i=0;i<9;i++){
            System.out.println(carType[i]+"s: "+cost_price[i]);
        }
        System.out.println("\nSales price of Vehicles:");
        for (int i=0;i<9;i++){
            System.out.println(carType[i]+"s: "+sales_price[i]);
        }
        System.out.println("\nList of Sold Vehicles:");
        for (int i=0;i<9;i++){
            System.out.println(carType[i]+"s: "+soldVehicles[i]);
        }
        System.out.println("Remaining Budget of South FNCD: "+Operation2.budget);
    }
}