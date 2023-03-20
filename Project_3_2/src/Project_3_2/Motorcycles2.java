package Project_3_2;

import java.io.IOException;

//For 2nd FNCD
class Motorcycles2 extends Vehicle2{

    double engine_size_gen = 0.0;
    public double setEngineSize(){
        return rand.nextGaussian()*300+700;
    }
    public void buyVehicle(FNCDdata2 fnc) throws IOException {
        int cp1 = rand.nextInt(10000, 20001);
        cost_price[3].add(cp1);
        sales_price[3].add(cp1 * 2);
        // Setting the Engine size for Motorcycles using truncated Normal Distribution with mean 700 and std dev 300.
        while (engine_size.size() < 6) {
            engine_size_gen = setEngineSize();
            if(engine_size_gen>=50){
                engine_size.add(engine_size_gen);
            }
        }
        if(getCondition(vehicle[3].get(vehicle[3].size()-1))==0){//If vehicle is initially broken, reducing its cost price by 50%.
            cost_price[3].set((vehicle[3].size()-1), (int)(cost_price[3].get(vehicle[3].size()-1)*0.5));
        } else if (getCondition(vehicle[3].get(vehicle[3].size()-1))==1){//If vehicle is initially used, reducing its cost price by 20%.
            cost_price[3].set((vehicle[3].size()-1), (int)(cost_price[3].get(vehicle[3].size()-1)*0.8));
        }
        Operation2.addBudget(3,fnc);
        Operation2.budget = Operation2.budget - cost_price[3].get(vehicle[3].size()-1);
        System.out.println("Bought "+carType[3]+" "+vehicle[3].get(vehicle[3].size()-1)+" for $"+cost_price[3].get(cost_price[3].size()-1));
        fnc.dayEnd(3,vehicle[3].size()-1,3);//obs
    }
}