
package polynomialtester;

import java.util.ArrayList;


public class Polynomial {
    ArrayList<Integer> exponents = new ArrayList();
    ArrayList<Integer> coefficients = new ArrayList();
    
    public Polynomial(String p){
        
        ArrayList<Integer> indexX = new ArrayList();
        ArrayList<Integer> indexSigns = new ArrayList();
        
        for (int i = 0; i < p.length(); i++) {//Keeps track of index of "x"'s and "+"'s in the polynomial
            String letter = Character.toString(p.charAt(i));

            if (letter.equals("x")){
                indexX.add(i);
            }
            
            if (i==0 && letter.equals("-")){
                
            }
            else{
                if (letter.equals("+") || letter.equals("-")){
                indexSigns.add(i);
                }
            }
            

        }
        
        //Appends all of the exponents of x in the function
        
        //Set different run times for different functions
        int runTimes;
        
        //If the function ends with an x
        if (p.substring(p.length()-1, p.length()).equals("x")) {
            runTimes = indexX.size()-1;
        }
        
        //If the function does not end with an x
        else {
            runTimes = indexX.size();
        }
        
        for (int i = 0; i < runTimes; i++) {
            String check = Character.toString(p.charAt(indexX.get(i)+1));//Checks the character in the string after each x term
            String exponent;
            if (check.equals("^")) {//If the x term has an exponent > 1
                exponent = p.substring((indexX.get(i)+2),indexSigns.get(i));
            }
            else {
                exponent = "1";
            }
                exponents.add(Integer.parseInt(exponent)); 
        }
        
        if (runTimes == indexX.size()-1) {//If the function ends with a single x term, the exponent is 1
            exponents.add(1);
            
        }
        
        if (indexSigns.size()==indexX.size()){//last term has no x term, exponent of x is 0 because it is an integer term
                exponents.add(0);
            }
        
        
        
        //Appends all of the coefficients in the function
        String coef="";
        
        //Set different run times for different functions
        
        int runTimes2;
        if(indexSigns.size()==indexX.size()){//If the function ends with an integer term
            runTimes2 = indexSigns.size();
        }
        
        else {
            runTimes2 = indexX.size();
        }
        
        int indexSign, indexXterm;
        for (int i = 0; i < runTimes2; i++) {     
                if (i == 0) {//first term
                    if (p.substring(0, 1).equals("x")){//checks for cases where the coefficient is 1 for the first term
                        coef = "1";
                    }
                    else if (p.substring(0, 2).equals("-x")){//checks for cases where the coefficient for the first term is -1
                        coef = "-1";
                    }
                    else {
                        coef = p.substring(0, indexX.get(i));
                    }
                }
                
                else{
                    indexSign = indexSigns.get(i-1);
                    indexXterm = indexX.get(i);
                    if (!p.substring(indexSign+1, indexSign+2).equals("x")) {//checks for cases where the coefficient is not 1 for the rest of the terms
                        if (p.substring(indexSign, indexSign+1).equals("-")) {
                            coef = "-" + p.substring(indexSign+1, indexXterm);
                        }
                        else{
                            coef = p.substring(indexSign+1, indexXterm);
                        }
                        
                    }
                    else{
                        if (p.substring(indexSign, indexSign+1).equals("-")) {
                            coef = "-1";
                        }
                        else{
                        coef = "1";
                        }
                    }
                    
                }
                coefficients.add(Integer.parseInt(coef));
            }
                        
            
        
        if (indexSigns.size()==indexX.size()) {//adds the last integer if the last term is an integer term
            coef = p.substring (indexSigns.get(indexSigns.size()-1), p.length());
            coefficients.add(Integer.parseInt(coef));
        }
        

        for (int i = 0; i < exponents.size(); i++) {
            System.out.println("Exponent");
            System.out.println(exponents.get(i));
            
        }
        
        System.out.println("");
        for (int i = 0; i < coefficients.size(); i++) {
            System.out.println("Coefficient");
            System.out.println(coefficients.get(i));

        }
    }
        
        

    
}
