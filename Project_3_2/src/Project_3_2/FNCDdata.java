package Project_3_2;

import java.io.IOException;
import java.util.ArrayList;

//Obs start
interface Publisher{
    public void registerSubscriber(Subscriber s);
    public void removeSubscriber(Subscriber s);
    public void notifySubscriberAct(Subscriber s,int i,int j) throws IOException;
    //example of Polymorphism (method overloading)
    public void notifySubscriberWash(Subscriber s,int i,int j,int k,Vehicle obj,String vehicle,int l,int m,int n,int bonus) throws IOException;
    //example of Polymorphism (method overloading)
    public void notifySubscriberWash(Subscriber s,String vehicle,int i) throws IOException;
    public void notifySubscriberRepair(Subscriber s,int i,int j, Vehicle obj,String vehicle,int k,int bonus) throws IOException;
    public void notifySubscriberRepair(Subscriber s,Vehicle obj,String vehicle) throws IOException;
    public void notifySubscriberSell(Subscriber s,int i,String person,Vehicle obj,String vehicle,int buyer_choice,String buyer,int vehicle_choice,int bonus) throws IOException;
    public void notifySubscriberSell(Subscriber s,int i,int j,int k,int l) throws IOException;
    public void notifySubscriberRace(Subscriber s,int j,int i,int choice,int bonus,int k) throws IOException;
    public void notifySubscriberRace(Subscriber s,int j,int i) throws IOException;
    public void notifySubscriberEnd(Subscriber s,int i,int j,int k) throws IOException;
    public void notifySubscriberTracker(Subscriber s,int i,int j) throws IOException;
}
interface Subscriber{
    public void update(Subscriber s,int i,int j) throws IOException;
    public void updateAct(Subscriber s,int i,int j) throws IOException;
    public void updateWash(int i, int j, int k,Vehicle obj, String vehicle, int l, int m, int n, int bonus) throws IOException;
    public void updateWash(String vehicle,int i) throws IOException;
    public void updateRepair(Subscriber s,int i,int j,Vehicle obj,String vehicle,int k,int bonus) throws IOException;
    public void updateRepair(Subscriber s,Vehicle obj,String vehicle) throws IOException;
    public void updateSell(Subscriber s,int i,String person,Vehicle obj,String vehicle,int buyer_choice,String buyer,int vehicle_choice,int bonus_val) throws IOException;
    public void updateSell(Subscriber s,int i,int j,int k,int l) throws IOException;
    public void updateRace(Subscriber s,int j,int i,int choice,int bonus,int k) throws IOException;
    public void updateRace(Subscriber s,int j,int i) throws IOException;
    public void updateEnd(Subscriber s,int i,int j,int k) throws IOException;
}
class FNCDdata implements Publisher{
    Subscriber s;
    static ArrayList<Subscriber> subscribers;
    public FNCDdata(){
        subscribers = new ArrayList<Subscriber>();
    }
    public void registerSubscriber(Subscriber s){
        subscribers.add(s);
    }
    public void removeSubscriber(Subscriber s){
        int i = subscribers.indexOf(s);
        if (i >= 0) {
            subscribers.remove(i);
        }
    }
    public void dayAct(int i,int j) throws IOException {
        s= subscribers.get(1);
        notifySubscriberAct(s,i,j);         //To add daily activities e.g. washing, repairing.
    }
    public void notifySubscriberAct(Subscriber s,int i,int j) throws IOException {
        s.updateAct(s,i,j);         //Update the subscriber with activities
    }
    public void washOutcome(int i,int j,int k,Vehicle obj,String vehicle,int l,int m,int n,int bonus) throws IOException {
        s= subscribers.get(1);
        notifySubscriberWash(s,i,j,k,obj,vehicle,l,m,n,bonus);      //To add details of washing method, intern bonus, cleanliness
    }
    public void washOutcome(String vehicle,int i) throws IOException {
        s= subscribers.get(1);
        notifySubscriberWash(s,vehicle,i);      //To add change in the vehicle condition after washing
    }
    public void notifySubscriberWash(Subscriber s,int i,int j,int k,Vehicle obj,String vehicle,int l,int m,int n,int bonus) throws IOException {
        s.updateWash(i,j,k,obj,vehicle,l,m,n,bonus);        //Update the subscriber with wash activity
    }
    public void notifySubscriberWash(Subscriber s,String vehicle,int i) throws IOException {
        s.updateWash(vehicle,i);        //Update the subscriber with wash activity
    }
    public void repairOutcome(int i,int j, Vehicle obj,String vehicle,int k,int bonus) throws IOException {
        s= subscribers.get(1);
        notifySubscriberRepair(s,i,j,obj,vehicle,k,bonus);      //To add details of repairing, condition, mechanic bonus
    }
    public void repairOutcome(Vehicle obj,String vehicle) throws IOException {
        s= subscribers.get(1);
        notifySubscriberRepair(s,obj,vehicle);      //To add change in vehicle cleanliness after repairing
    }
    public void notifySubscriberRepair(Subscriber s,int i,int j,Vehicle obj,String vehicle,int k,int bonus) throws IOException {
        s.updateRepair(s,i,j,obj,vehicle,k,bonus);          //Update the subscriber with repair activity
    }
    public void notifySubscriberRepair(Subscriber s,Vehicle obj,String vehicle) throws IOException {
        s.updateRepair(s,obj,vehicle);          //Update the subscriber with repair activity
    }
    public void sellOutcome(int i,String person,Vehicle obj,String vehicle,int buyer_choice,String buyer,int vehicle_choice,int bonus) throws IOException {
        s= subscribers.get(1);
        notifySubscriberSell(s,2,person,obj,vehicle,buyer_choice,buyer,vehicle_choice,bonus);   //To add details of selling, salesperson bonus
    }
    public void sellOutcome(int i,int j,int k,int l) throws IOException {
        s= subscribers.get(1);
        notifySubscriberSell(s,i,j,k,l);        //To add change in salesprice of vehicle after addons
    }
    public void notifySubscriberSell(Subscriber s,int i,String person,Vehicle obj,String vehicle,int buyer_choice,String buyer,int vehicle_choice,int bonus) throws IOException {
        s.updateSell(s,2,person,obj,vehicle,buyer_choice,buyer,vehicle_choice,bonus);   //Update the subscriber with sale activity
    }
    public void notifySubscriberSell(Subscriber s,int i,int j,int k,int l) throws IOException {
        s.updateSell(s,i,j,k,l);        //Update the subscriber with sale activity
    }
    public void raceOutcome(int j,int i,int choice,int bonus,int k) throws IOException {
        s= subscribers.get(1);
        notifySubscriberRace(s,j,i,choice,bonus,k);     //To add details of racing events, driver bonus
    }
    public void raceOutcome(int j,int i) throws IOException {
        s= subscribers.get(1);
        notifySubscriberRace(s,j,i);        //To add change in vehicle condition and injury of drivers
    }
    public void notifySubscriberRace(Subscriber s,int j,int i,int choice,int bonus,int k) throws IOException {
        s.updateRace(s,j,i,choice,bonus,k);     //Update the subscriber with race activity
    }
    public void notifySubscriberRace(Subscriber s,int j,int i) throws IOException {
        s.updateRace(s,j,i);        //Update the subscriber with race activity
    }
    public void dayEnd(int i,int j,int k) throws IOException {
        s= subscribers.get(1);
        notifySubscriberEnd(s,i,j,k);       //To add details of staff quitting, hiring new staff, buying new vehicles
    }
    public void notifySubscriberEnd(Subscriber s,int i,int j,int k) throws IOException {
        s.updateEnd(s,i,j,k);       //Update the subscriber with the day end activities
    }
    public void trackerOutcome(int i,int j) throws IOException {
        s= subscribers.get(0);
        notifySubscriberTracker(s,i,j);     //To add total money earned by staff and FNCD
    }
    public void notifySubscriberTracker(Subscriber s,int i,int j) throws IOException {
        s.update(s,i,j);        //Update the subscriber with the tracker activity
    }
}
