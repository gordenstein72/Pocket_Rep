package edu.wit.greentriangle.pocketrep;


import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.Scanner;



public class Politician {
    private String office, fname, lname, party, phone, bio;
    private Bitmap pic;

//    public static void main(String args[]){
////        Scanner userin = new Scanner(System.in);
////        String state = "new";
////        while (true){
////            System.out.print("Enter state (q to quit): ");
////            state = userin.next();
////            if(state.compareTo("q") == 0) {
////                userin.close();
////                System.exit(0);
////            }
////            System.out.println();
////            ArrayList<Politician> polis = queryFile(state);
////            for(Politician p : polis){
////                System.out.println(p.toString());
////            }
////        }
////    }

    public Politician(String office, String fname, String lname, String party, String phone){
        this.office = office;
        this.party = party;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;

        //this.setBio();
    }

    public String getFname(){
        return fname;
    }

    public String getParty(){
        return party;
    }

    public String getOffice(){
        return office;
    }

    public String getLname(){
        return lname;
    }

    public String getPhone(){
        return phone;
    }



    public Bitmap getPic() {
        return pic;
    }

    public void setPic(Bitmap pic) {
        this.pic = pic;
    }

  /*  public void setBio() {
        public String getBio() { return bio; }
    }
*/
    public String toString(){
        String ret = String.format("%s %s, %s, %s, %s", this.getFname(), this.getLname(), this.getOffice(), this.getParty(), this.getPhone());
        return ret;
    }

    public static ArrayList<Politician> queryFile(Scanner fileScan){

        Log.v("Appo", "queryFile Called");
        ArrayList<Politician> politicians = new ArrayList<Politician>();

        fileScan.nextLine(); // skip state name
        while(fileScan.hasNextLine()){
            String office = fileScan.nextLine();
            String fname = fileScan.nextLine();
            String lname = fileScan.nextLine();
            String party = fileScan.nextLine();
            String phone = fileScan.nextLine();
            politicians.add(new Politician(office,fname,lname,party,phone));
        }
        fileScan.close();

        Log.v("Appo", "exiting query");
        return politicians;
    }
}
