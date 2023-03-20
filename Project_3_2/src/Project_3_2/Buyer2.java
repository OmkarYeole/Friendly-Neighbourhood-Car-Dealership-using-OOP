package Project_3_2;

import java.util.ArrayList;
import java.util.Random;

//For 2nd FNCD
class Buyer2 extends Staff2{
    static ArrayList<String>[] buyers = new ArrayList[3];
    static ArrayList<Integer>[] buyer_choice = new ArrayList[3];
    static ArrayList<Double>[] buyer_prob = new ArrayList[3];
    static int buyer_no;
    Random rand = new Random();
    //initializing the variables
    public void init3() {
        for (int i = 0; i < buyers.length; i++) {
            buyers[i] = new ArrayList<>();
            buyer_choice[i] = new ArrayList<>();
            buyer_prob[i] = new ArrayList<>();
        }
    }
    //adding the number of buyers according to the day of the week
    //1= Monday, 2=Tuesday and so on
    //Assuming same number of buyers will be added on 7=Sunday as on Monday-Thursday
    public void addBuyer() {
        if ((Operation2.day_count<=4)||(Operation2.day_count>=7 && Operation2.day_count<=11)||(Operation2.day_count>=14 && Operation2.day_count<=18)
                ||(Operation2.day_count>=21 && Operation2.day_count<=25)||(Operation2.day_count>=28 && Operation2.day_count<=30)){
            buyer_no = rand.nextInt(0, 6);
            for (int j = 0; j < buyer_no; j++) {
                addBuyerType(j);
            }
        } else if (Operation2.day_count==5 ||Operation2.day_count==6 ||Operation2.day_count==12||Operation2.day_count==13||
                Operation2.day_count==19||Operation2.day_count==20||Operation2.day_count==26||Operation2.day_count==27) {
            buyer_no = rand.nextInt(2, 9);
            for (int j = 0; j < buyer_no; j++) {
                addBuyerType(j);
            }
        }
    }
    //randomly adding buyer type to buyers
    public void addBuyerType(int j){
        int prob;
        prob = rand.nextInt(3);
        if(prob == 0){
            buyers[0].add("Buyer"+ (j+1));
            buyer_prob[0].add(0.1);
            addBuyerChoice(0); //buyer added to the type Just Looking
        }
        else if(prob == 1){
            buyers[1].add("Buyer"+ (j+1));
            buyer_prob[1].add(0.4);
            addBuyerChoice(1); //buyer added to the type Wants one
        }
        else{
            buyers[2].add("Buyer"+ (j+1));
            buyer_prob[2].add(0.7);
            addBuyerChoice(2); //buyer added to the type Needs one
        }
    }
    //adds choice of vehicle to the buyer
    public void addBuyerChoice(int j){
        int vehicleChoice = rand.nextInt(9);
        buyer_choice[j].add(vehicleChoice);
    }
    //getting the type of buyer
    public int getBuyerIndex1(String buyer){
        //initializing to a value which is different from potential values of index (i.e. 0,1,2)
        int buyer_index1 = 0;
        for(int i=0; i<3;i++) {
            for (int j = 0; j < buyers[i].size(); j++) {
                if (buyers[i].get(j).equals(buyer)){
                    buyer_index1 = i;
                    break;
                }
            }
        }
        return buyer_index1;
    }
    //getting the position of buyer in the list of the type of buyer
    public int getBuyerIndex2(String buyer){
        //initializing to a value which is different from potential values of index (i.e. 0,1,2)
        int buyer_index2=0;
        for(int i=0; i<3;i++) {
            for (int j = 0; j < buyers[i].size(); j++) {
                if (buyers[i].get(j).equals(buyer)) {
                    buyer_index2 = j;
                    break;
                }
            }
        }
        return buyer_index2;
    }
}