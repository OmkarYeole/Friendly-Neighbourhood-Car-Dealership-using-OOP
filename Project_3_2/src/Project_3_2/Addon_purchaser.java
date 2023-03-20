package Project_3_2;

import java.io.IOException;

//Decorator Pattern
abstract class Addon_purchaser extends Vehicle{
    //    Vehicle vehicle;
    int i, j;
    abstract void addonPrice(FNCDdata fnc) throws IOException;
}

class ExtendedWarranty extends Addon_purchaser{
    public ExtendedWarranty(int i,int j) {
        this.i = i;
        this.j = j;
        addonPrice();
    }
    public ExtendedWarranty(int i,int j,FNCDdata fnc) throws IOException {
        this.i = i;
        this.j = j;
        addonPrice(fnc);
    }
    public void addonPrice() {
        double prob = rand.nextDouble();
        if(prob < 0.25){
            System.out.println("Purchased Add-on Extended Warranty");
            System.out.print("Sales Price of the "+carType[i]+" "+vehicle[i].get(j)+" increased from $"+sales_price[i].get(j));
            sales_price[i].set(j, (int)(sales_price[i].get(j)*1.2));
            System.out.println(" to $"+sales_price[i].get(j));
            commandReceiver.sales_price = sales_price[i].get(j);
        }
    }
    public void addonPrice(FNCDdata fnc) throws IOException {
        double prob = rand.nextDouble();
        if(prob < 0.25){
            System.out.print("Sales Price of the "+carType[i]+" "+vehicle[i].get(j)+" increased from $"+sales_price[i].get(j));
            fnc.sellOutcome(i,j,0,0);//obs
            sales_price[i].set(j, (int)(sales_price[i].get(j)*1.2));
            System.out.println(" to $"+sales_price[i].get(j)+ " after adding on Extended Warranty");
            fnc.sellOutcome(i,j,1,0);//obs
        }
    }
}

class Undercoating extends Addon_purchaser{
    public Undercoating(int i,int j) {
        this.i = i;
        this.j = j;
        addonPrice();
    }
    public Undercoating(int i,int j,FNCDdata fnc) throws IOException {
        this.i = i;
        this.j = j;
        addonPrice(fnc);
    }
    public void addonPrice() {
        double prob = rand.nextDouble();
        if(prob < 0.10){
            System.out.println("Purchased Add-on Undercoating");
            System.out.print("Sales Price of the "+carType[i]+" "+vehicle[i].get(j)+" increased from $"+sales_price[i].get(j));
            sales_price[i].set(j, (int)(sales_price[i].get(j)*1.05));
            System.out.println(" to $"+sales_price[i].get(j));
            commandReceiver.sales_price = sales_price[i].get(j);
        }
    }
    public void addonPrice(FNCDdata fnc) throws IOException {
        double prob = rand.nextDouble();
        if(prob < 0.10){
            System.out.print("Sales Price of the "+carType[i]+" "+vehicle[i].get(j)+" increased from $"+sales_price[i].get(j));
            fnc.sellOutcome(i,j,0,1);//obs
            sales_price[i].set(j, (int)(sales_price[i].get(j)*1.05));
            System.out.println(" to $"+sales_price[i].get(j)+ " after adding on Undercoating");
            fnc.sellOutcome(i,j,1,1);//obs
        }
    }
}

class RoadRescueCoverage extends Addon_purchaser{
    public RoadRescueCoverage(int i,int j) {
        this.i = i;
        this.j = j;
        addonPrice();
    }
    public RoadRescueCoverage(int i,int j,FNCDdata fnc) throws IOException {
        this.i = i;
        this.j = j;
        addonPrice(fnc);
    }
    public void addonPrice() {
        double prob = rand.nextDouble();
        if(prob <0.05){
            System.out.println("Purchased Add-on Road Rescue Coverage");
            System.out.print("Sales Price of the "+carType[i]+" "+vehicle[i].get(j)+" increased from $"+sales_price[i].get(j));
            sales_price[i].set(j, (int)(sales_price[i].get(j)*1.02));
            System.out.println(" to $"+sales_price[i].get(j));
            commandReceiver.sales_price = sales_price[i].get(j);
        }
    }
    public void addonPrice(FNCDdata fnc) throws IOException {
        double prob = rand.nextDouble();
        if(prob <0.05){
            System.out.print("Sales Price of the "+carType[i]+" "+vehicle[i].get(j)+" increased from $"+sales_price[i].get(j));
            fnc.sellOutcome(i,j,0,2);//obs
            sales_price[i].set(j, (int)(sales_price[i].get(j)*1.02));
            System.out.println(" to $"+sales_price[i].get(j)+ " after adding on Road Rescue Coverage");
            fnc.sellOutcome(i,j,1,2);//obs
        }
    }
}

class SatelliteRadio extends Addon_purchaser{
    public SatelliteRadio(int i,int j) {
        this.i = i;
        this.j = j;
        addonPrice();
    }
    public SatelliteRadio(int i,int j,FNCDdata fnc) throws IOException {
        this.i = i;
        this.j = j;
        addonPrice(fnc);
    }
    public void addonPrice() {
        double prob = rand.nextDouble();
        if(prob < 0.40){
            System.out.println("Purchased Add-on Satellite Radio");
            System.out.print("Sales Price of the "+carType[i]+" "+vehicle[i].get(j)+" increased from $"+sales_price[i].get(j));
            sales_price[i].set(j, (int)(sales_price[i].get(j)*1.05));
            System.out.println(" to $"+sales_price[i].get(j));
            commandReceiver.sales_price = sales_price[i].get(j);
        }
    }
    public void addonPrice(FNCDdata fnc) throws IOException {
        double prob = rand.nextDouble();
        if(prob < 0.40){
            System.out.print("Sales Price of the "+carType[i]+" "+vehicle[i].get(j)+" increased from $"+sales_price[i].get(j));
            fnc.sellOutcome(i,j,0,3);//obs
            sales_price[i].set(j, (int)(sales_price[i].get(j)*1.05));
            System.out.println(" to $"+sales_price[i].get(j)+ " after adding on Satellite Radio");
            fnc.sellOutcome(i,j,1,3);//obs
        }
    }
}
