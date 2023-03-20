package Project_3_2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//Factory Pattern
interface Vehicles{
    void init2();
    void add_vehicle_names();
    void add_MonsterTruck_names();
    String getMonsterTruckName();
    String getcarName();
    void addVehicles();
    void addVehicle(FNCDdata fnc) throws IOException;
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

interface factoryVehicle{
    Vehicles createVehicle();
}

class Carfactory implements factoryVehicle{

    @Override
    public Vehicles createVehicle() {
        return (Vehicles) new Car();
    }
}
class Pickupfactory implements factoryVehicle{

    @Override
    public Vehicles createVehicle() {
        return (Vehicles) new Pickup();
    }
}
class PerformanceCarfactory implements factoryVehicle{

    @Override
    public Vehicles createVehicle() {
        return (Vehicles) new PerformanceCar();
    }
}
class Motorcyclesfactory implements factoryVehicle{

    @Override
    public Vehicles createVehicle() {
        return (Vehicles) new Motorcycles();
    }
}
class MonsterTrucksfactory implements factoryVehicle{

    @Override
    public Vehicles createVehicle() {
        return (Vehicles) new MonsterTrucks();
    }
}
class ElectricCarfactory implements factoryVehicle{

    @Override
    public Vehicles createVehicle() {
        return (Vehicles) new ElectricCar();
    }
}
class BudgetCarfactory implements factoryVehicle{

    @Override
    public Vehicles createVehicle() {
        return (Vehicles) new BudgetCar();
    }
}
class LuxuryCarfactory implements factoryVehicle{

    @Override
    public Vehicles createVehicle() {
        return (Vehicles) new LuxuryCar();
    }
}
class SuperCarfactory implements factoryVehicle{

    @Override
    public Vehicles createVehicle() {
        return (Vehicles) new SuperCar();
    }
}


public class Vehicle implements Vehicles{
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
    static int[] race_win_bonus = {0, 200, 225, 250, 275, 0, 0, 0, 300};
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
        Operation.budget =500000;//Initial Day 1 beginning budget
    }
    //when the count of a vehicle type is less than 6, new vehicle is purchased
    public void addVehicle(FNCDdata fnc) throws IOException {
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
                    Carfactory c = new Carfactory();
                    Car c1 = (Car) c.createVehicle();
                    c1.buyVehicle(fnc);
                } else if(i == 1){
                    Pickupfactory c = new Pickupfactory();
                    Pickup c1 = (Pickup) c.createVehicle();
                    c1.buyVehicle(fnc);
                } else if(i == 2){
                    PerformanceCarfactory c = new PerformanceCarfactory();
                    PerformanceCar c1 = (PerformanceCar) c.createVehicle();
                    c1.buyVehicle(fnc);
                } else if (i == 3) {
                    Motorcyclesfactory c = new Motorcyclesfactory();
                    Motorcycles c1 = (Motorcycles) c.createVehicle();
                    c1.buyVehicle(fnc);
                } else if (i == 4) {
                    MonsterTrucksfactory c = new MonsterTrucksfactory();
                    MonsterTrucks c1 = (MonsterTrucks) c.createVehicle();
                    c1.buyVehicle(fnc);
                } else if (i == 5) {
                    ElectricCarfactory c = new ElectricCarfactory();
                    ElectricCar c1 = (ElectricCar) c.createVehicle();
                    c1.buyVehicle(fnc);
                } else if (i == 6) {
                    BudgetCarfactory c = new BudgetCarfactory();
                    BudgetCar c1 = (BudgetCar) c.createVehicle();
                    c1.buyVehicle(fnc);
                } else if (i == 7) {
                    LuxuryCarfactory c = new LuxuryCarfactory();
                    LuxuryCar c1 = (LuxuryCar) c.createVehicle();
                    c1.buyVehicle(fnc);
                }
                else{
                    SuperCarfactory c = new SuperCarfactory();
                    SuperCar c1 = (SuperCar) c.createVehicle();
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
        System.out.println("Remaining Budget of North FNCD: "+Operation.budget);
    }

}
