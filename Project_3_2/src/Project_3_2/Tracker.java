package Project_3_2;

import java.io.IOException;

//Singleton Pattern using an Eager instantiation
class Tracker implements Subscriber,Subscriber2{
    static Publisher fncdData;
    static Publisher2 fncdData2;
    static FNCDdata fnc = new FNCDdata();//obs
    static FNCDdata2 fnc2 = new FNCDdata2();//obs
    private static Tracker tracker = new Tracker(fnc);
    private static Tracker tracker2 = new Tracker(fnc2);
    private Tracker(Publisher fncdData){
        this.fncdData = fncdData;
        this.fncdData.registerSubscriber(this);
    }
    public static Tracker getInstance(){
        return tracker;
    }
    private Tracker(Publisher2 fncdData){
        this.fncdData2 = fncdData;
        this.fncdData2.registerSubscriber(this);
    }
    public static Tracker getInstance2(){
        return tracker2;
    }
    public void update(Subscriber s,int i,int j) throws IOException {
        if(j==0){
            System.out.println("Tracker: Day "+i);
        }if(j==1){
            System.out.println("Total money earned by all Staff of North FNCD: $"+Operation.Staff_money);
            System.out.println("Total money earned by the North FNCD: $"+Operation.FNCD_money);
        }
    }
    public void updateAct(Subscriber s,int i,int j){
        //Do nothing
    }
    public void updateWash(int i,int j,int k,Vehicle obj,String vehicle,int l,int m,int n,int bonus){
        //Do nothing
    }
    public void updateWash(String vehicle,int i){
        //Do nothing
    }
    public void updateRepair(Subscriber s,int i,int j, Vehicle obj,String vehicle,int k,int bonus){
        //Do nothing
    }
    public void updateRepair(Subscriber s,Vehicle obj,String vehicle){
        //Do nothing
    }
    public void updateSell(Subscriber s,int i,String person,Vehicle obj,String vehicle,int buyer_choice,String buyer,int vehicle_choice,int bonus){
        //Do nothing
    }
    public void updateSell(Subscriber s,int i,int j,int k,int l){
        //Do nothing
    }
    public void updateRace(Subscriber s,int j,int i,int choice,int bonus,int k){
        //Do nothing
    }
    public void updateRace(Subscriber s,int j,int i){
        //Do nothing
    }
    public void updateEnd(Subscriber s,int i,int j,int k){
        //Do nothing
    }
    public void update(Subscriber2 s,int i,int j) throws IOException {
        if(j==0){
            System.out.println("Tracker: Day "+i);
        }if(j==1){
            System.out.println("Total money earned by all Staff of South FNCD: $"+Operation2.Staff_money);
            System.out.println("Total money earned by the South FNCD: $"+Operation2.FNCD_money);
        }
    }
    public void updateAct(Subscriber2 s,int i,int j){
        //Do nothing
    }
    public void updateWash(int i,int j,int k,Vehicle2 obj,String vehicle,int l,int m,int n,int bonus){
        //Do nothing
    }
    public void updateWash(String vehicle,int i,int j){
        //Do nothing
    }
    public void updateRepair(Subscriber2 s,int i,int j, Vehicle2 obj,String vehicle,int k,int bonus){
        //Do nothing
    }
    public void updateRepair(Subscriber2 s,Vehicle2 obj,String vehicle){
        //Do nothing
    }
    public void updateSell(Subscriber2 s,int i,String person,Vehicle2 obj,String vehicle,int buyer_choice,String buyer,int vehicle_choice,int bonus){
        //Do nothing
    }
    public void updateSell(Subscriber2 s,int i,int j,int k,int l){
        //Do nothing
    }
    public void updateRace(Subscriber2 s,int j,int i,int choice,int bonus,int k){
        //Do nothing
    }
    public void updateRace(Subscriber2 s,int j,int i){
        //Do nothing
    }
    public void updateEnd(Subscriber2 s,int i,int j,int k){
        //Do nothing
    }
}
//Obs end
