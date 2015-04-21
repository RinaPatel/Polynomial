
package polynomialtester;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import java.awt.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import java.io.*;
import static java.lang.Math.round;
import java.util.Collections;


public class Polynomial extends JFrame {
    ArrayList<Integer> exponents = new ArrayList();
    ArrayList<Integer> coefficients = new ArrayList();
    
    //Constructors
    public Polynomial(String p){
        fillArrayList(p);
    }
    
    public Polynomial (ArrayList<Integer> coef, ArrayList<Integer> expo){
        this.coefficients = coef;
        this.exponents = expo;
    }
    
    public void fillArrayList(String p){
        ArrayList<Integer> indexX = new ArrayList();
        ArrayList<Integer> indexSigns = new ArrayList();
        
        for (int i = 0; i < p.length(); i++) {//Keeps track of index of "x"'s and "+"'s in the polynomial
            String letter = Character.toString(p.charAt(i));

            if (letter.equals("x")){
                indexX.add(i);
            }
            
            if (i==0 && letter.equals("-")){}
            
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
                if (i!=runTimes-1){
                    exponent = p.substring((indexX.get(i)+2),indexSigns.get(i));
                }
                else if (i==runTimes-1 && indexX.size()!=indexSigns.size()){
                    exponent = p.substring((indexX.get(i)+2), p.length());
                }
                else{
                    exponent = p.substring((indexX.get(i)+2), indexSigns.get(i));
                }

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
        
        /*for (int i = 0; i < exponents.size(); i++) {
            System.out.println("Exponent");
            System.out.println(exponents.get(i));
            
        }*/
        
        
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
        

/*
        System.out.println("");
        for (int i = 0; i < coefficients.size(); i++) {
            System.out.println("Coefficient");
            System.out.println(coefficients.get(i));

        } */
    }
    

     public void AddPoly(Polynomial Other){ // WIP
       ArrayList<Integer> SumNums = new ArrayList();
       ArrayList<Integer> SumNumsCo = new ArrayList();
       ArrayList<Integer> SumNumsOld = new ArrayList();
       ArrayList<Integer> SumNumsCoNew = new ArrayList();
        for (int i = 0; i < this.exponents.size(); i++) {
            SumNums.add(this.exponents.get(i));
            SumNumsCo.add(this.coefficients.get(i));
            SumNumsOld.add(this.exponents.get(i));
        }
        for (int j = 0; j < Other.exponents.size(); j++) {
            SumNums.add(Other.exponents.get(j));
            SumNumsCo.add(Other.coefficients.get(j));
            SumNumsOld.add(Other.exponents.get(j));
            
        }
        Collections.sort(SumNums);
        Collections.reverse(SumNums);
        for (int i = 0; i < SumNums.size(); i++) {
            int j=0;
            while(SumNums.get(i)!=SumNumsOld.get(j)){
                j=j+1;
            }
            SumNumsCoNew.add(i, SumNumsCo.get(j));
            
        }
        
        
        for (int i = 0; i < SumNums.size()-1; i++) {
            if (SumNums.get(i)==SumNums.get(i+1)){
                SumNums.remove(i);
            }
            
        }
        
        for (int i = 0; i < this.exponents.size(); i++) {
            for (int j = 0; j < Other.exponents.size(); j++) {
                if(this.exponents.get(i)==Other.exponents.get(j)){
                    int Index = SumNums.indexOf(SumNums.get(i));
                    SumNumsCoNew.add(Index, this.coefficients.get(i)+Other.coefficients.get(j));
                }
                
            }
            
        }
//        for (int i = 0; i < SumNums.size()-1; i++) {
//            if (SumNums.get(i)==SumNums.get(i+1)){
//                SumNums.remove(i);
//                SumNumsCoNew.add(i+1, SumNumsCoNew.get(i)+SumNumsCoNew.get(i+1));
//                SumNumsCoNew.remove(i);
//                
//            }
//            
//        }
        
        // I removed the printing part of it and put this instead ~Rina
        Polynomial Sum = new Polynomial(SumNumsCoNew, SumNums);
        System.out.print("Sum: ");
        Sum.displayPolynomialFactored();
        
    }

    public Polynomial findPlaceHolders(Polynomial p) { //This is used for the multiplication and division methods only
        ArrayList<Integer> c = new ArrayList();
        ArrayList<Integer> e = new ArrayList();
        int numRuns;

        for(int i = p.exponents.get(0); i >= 0; i--) {
            e.add(i);
            numRuns = 0;
            for (int j = 0; j<p.exponents.size(); j++) {
                if (i == p.exponents.get(j)) {
                    c.add(p.coefficients.get(j));
                    numRuns = 1;
                }
                else if (j==p.exponents.size()-1 && numRuns != 1) c.add(0);
            }
        }
        
        c.add(0);
        e.add(0);

        Polynomial P3 = new Polynomial(c,e);
        return P3;
    }
    
    public void multiplyPolynomial(Polynomial p) { //Needs to be debugged
        // find placeholders
        Polynomial P1 = findPlaceHolders(this);
        Polynomial P2 = findPlaceHolders(p);

        ArrayList<Integer> c = new ArrayList();
        ArrayList<Integer> e = new ArrayList();

//	multiply like terms
        for (int i = 0; i < P1.exponents.size(); i++) {
            for (int j = 0; j < P2.exponents.size(); j++) {
                int indexExp = e.indexOf(P1.exponents.get(i) + P2.exponents.get(j));
                if (indexExp == -1) { // if exponent doesn't appear in list, make a new exponent
                    e.add(P1.exponents.get(i) + P2.exponents.get(j));
                    int m = P1.coefficients.get(i)*P2.coefficients.get(j);
                    c.add(m);
                    //c.add(P1.coefficients.get(i)*P2.exponents.get(j));
                    //System.out.println(P1.coefficients.get(i)*P2.coefficients.get(j));
                    //System.out.println(c.indexOf(P1.coefficients.get(i)*P2.coefficients.get(j)));
                }
                else { // otherwise, fix the coefficient at that exponent
                    int oldTerm = c.get(indexExp);
                    //System.out.println(indexExp);
                    //System.out.println(c.get(indexExp));
                    c.remove(indexExp);
                    c.add(indexExp, oldTerm + P1.coefficients.get(i)*P2.coefficients.get(j));
                    //System.out.println(indexExp);
                    //System.out.println(oldTerm + P1.coefficients.get(i)*P2.coefficients.get(j));
                }
            }
        }
        
        c.remove(c.size()-1);
        e.remove(e.size()-1);

        Polynomial product = new Polynomial(c,e);
//	Polynomial reducedP = ProductofPolynomials.reducePolynomial();;
        System.out.print("Product: ");
        product.displayPolynomialFactored();
    }
    
    public void dividePolynomial(Polynomial p) { // WIP, Still has to be debugged
        Polynomial P1 = findPlaceHolders(this);
        Polynomial P2 = findPlaceHolders(p);
        
        ArrayList<Integer> P3e = new ArrayList();
        ArrayList<Integer> P3c = new ArrayList();
        ArrayList<Integer> P4e = new ArrayList();
        ArrayList<Integer> P4c = new ArrayList();
        ArrayList<Integer> P5e = P2.exponents;
        ArrayList<Integer> P5c = P2.coefficients;
        
        for (int i =0; i< P2.coefficients.size(); i++) {
            if ((P5e.get(0) - P1.exponents.get(0)) >= 0) { //if the divident is greater than the divisor
                int divide = P5c.get(0) / P1.coefficients.get(0); // dividing
                P3c.add(i, divide);
                int e1 = P5e.get(0) - P1.exponents.get(0); // subtracting exponents when dividing
                P3e.add(i, e1);
                
                for (int j = 0; j < P1.coefficients.size()+1; j++) {
                    if (j < P1.coefficients.size()) {
                        int product = P1.coefficients.get(j) * P3c.get(i); // multiply quotient term by divisor
                        P4c.add(j, product);
                        int e2 = P1.exponents.get(j) + P3e.get(i); // add exponents when multiplying values
                        P4e.add(j, e2);
                    }
                    else {
                        P4c.add(j, 0); // adding zeros at end for ensurance
                        P4e.add(j, 0);
                        break;
                    }
                }
                
                if ((P5c.get(0) - P4c.get(0) == 0) && (P5e.get(0) - P4e.get(0) == 0)) {
                    int sizeofloopK = (P1.coefficients.size() - P3c.size()) + 1;
                    P5c.remove(0); // don't need any of these for future calculations since they all equal zero
                    P5e.remove(0);
                    P4c.remove(0);
                    P4e.remove(0);
                    
                    for (int k = 0; k < sizeofloopK; k++) {
                        int diff = P5c.get(k) - P4c.get(k);
                        P5c.remove(k);
                        P5e.remove(k);
                        P5c.add(k, diff);
                        P5e.add(k, P4e.get(k));
                    }
                    
                    P4c.clear();
                    P4e.clear();
                }
                else {
                    System.out.println("ERROR 2");
                }
            }
            else {
                System.out.println("DONE");
                break;
            }
        }
        
        P3c.remove(P3c.size()-1);
        
        P5c.remove(P5c.size()-1);
        
        Polynomial quotient = new Polynomial(P3c,P3e);
       // Polynomial reducedP = ProductofPolynomials.reducePolynomial();
        System.out.print("Quotient: ");
        quotient.displayPolynomialFactored();
        
        
        Polynomial remainder = new Polynomial(P5c,P5e);	
        System.out.print("Remainder: ");
        remainder.displayPolynomialFactored();
    }
    
    public void displayPolynomialFactored() { //WIP, can only factor SMALL quadratics; expand to factor cubic?
        if (this.exponents.get(0) == 2) {
            int a = this.coefficients.get(0);
            int b = this.coefficients.get(1);
            int c = this.coefficients.get(2);
            
            double check = Math.sqrt((b*b) - (4*a*c));
            
            if (check >= 0 && check == (int)check) { // if the square root is 0 or positive and ends up being an integer
                int factor1 = (int)((b*-1) + check)/(2*a);
                int factor2 = (int)((b*-1) - check)/(2*a);
                
                String sign1, sign2;
                
                if (factor1 >= 1) {
                    sign1 = "+";
                    if (factor2 >= 1) {
                        sign2 = "-";
                    }
                    else if (factor2 <= -1) {
                        sign2 = "+";
                    }
                    else {
                        sign2 = "";
                    }
                }
                else if (factor1 <= -1){
                    sign1 = "+";
                    if (factor2 >= 1) {
                        sign2 = "-";
                    }
                    else if (factor2 <= -1) {
                        sign2 = "+";
                    }
                    else {
                        sign2 = "";
                    }
                }
                else {
                    sign1 = "";
                    if (factor2 >= 1) {
                        sign2 = "-";
                    }
                    else if (factor2 <= -1) {
                        sign2 = "+";
                    }
                    else {
                        sign2 = "";
                    }
                }
                System.out.println("(x" + sign1 + Math.abs(factor1) + ")(x" + sign2 + Math.abs(factor2) + ")");
            }
            else {
            this.displayPolynomial();
            }
        }
        else {
           this.displayPolynomial();
        } //send to normal display function
    }   
    
    public void displayPolynomial(){ // this method displays polynomial in expanded form
        String term = "";
        String Print = "";
        String coef;
        String sign;
        String xTerm = "";
        
        for (int i = 0; i < this.exponents.size(); i++) {
            if (this.coefficients.get(i) < 0) {
                sign = "-";
            }
            else if (this.coefficients.get(i) > 0) {
                if (i==0) sign = "";

                else sign = "+";
            }
            else sign="";

                
            if (this.coefficients.get(i) == 1 || this.coefficients.get(i) == -1) coef = "";
            else if (this.coefficients.get(i) != 0){
                coef = String.valueOf(Math.abs(this.coefficients.get(i)));
            }
            else coef="";
            
            if (this.exponents.get(i) > 1) {
                xTerm = "x^" + String.valueOf(this.exponents.get(i));
            }
            else if (this.exponents.get(i) == 1) {
                xTerm = "x";
            }
            else if (this.exponents.get(i) == 0){
                xTerm = "";
            }

            term = sign + coef + xTerm;
            Print += term;

        }
        System.out.println(Print);
    }
    
    
    
    /*public Polynomial reducePolynomials(Polynomial p){
        ArrayList<Integer> newcoef = new ArrayList();
        ArrayList<Integer> newexpo = new ArrayList();
        
        Polynomial reduced = new Polynomial(newcoef, newexpo);
        
        ArrayList<Integer> checkcoef = new ArrayList();
        ArrayList<Integer> checkexpo = new ArrayList();
        
        for (int i = 0; i < p.exponents.size(); i++) {
            checkcoef.add( p.coefficients.get(i));
            checkexpo.add( p.exponents.get(i));
        }
        int c=0;
        int i = 0;
        int j = 0;
        while (p.coefficients.size() >= 0) {
            while (p.coefficients.size() >= 0) {
                if (checkexpo.get(j) == p.exponents.get(i)){
                    c = p.coefficients.get(i)+checkcoef.get(j);
                    System.out.println(c);
                    p.coefficients.remove(i);
                    p.coefficients.remove(j);
                    checkcoef.remove(i);
                    checkexpo.remove(j);
                }
                /*else{
                    c = p.coefficients.get(i);
                    p.coefficients.remove(i);
                }
                j++;
                
            }
            i++;
            newcoef.add(c);
            newexpo.add(p.exponents.get(i));
        }*/
        
        
        /*int
        for (int i = 0; i < p.coefficients.size(); i++) {
            int index = p.coefficients.get(i);
            
        }
        
        HashMap hm = new HashMap();
        
        for (int i = 0; i < p.coefficients.size(); i++) {
            hm.put(p.exponents.get(i), p.coefficients.get(i));
        }
        
        
        for (int i = 0; i < newexpo.size(); i++) {
            System.out.println("Coefficient");
            System.out.println(newcoef.get(i));
        }
        for (int i = 0; i < newexpo.size(); i++) {
            System.out.println("Exponent");
            System.out.println(newexpo.get(i));
            
            
        }
        
        return reduced;
    }*/
    
    /*******************************Graphing Portion*****************************************/
    int width = 800;
    int length = 800;
    int xMin = -20;
    int xMax = 20;
    int yMin = -20;
    int yMax = 20;
    Color[][] function = new Color[length][width];
    double[] yval = new double[width];
    
    double increY = ((double)(yMax-yMin))/(double)length;
    double increX = ((double)(xMax-xMin))/(double)width;
    
    public double calcY(double x){
        double y = 0;
        double term;
        for (int k = 0; k < coefficients.size(); k++){
            term = coefficients.get(k)*(Math.pow(x, exponents.get(k)));
            y = y + term;
        }
        return y;
    }
    
    public void plotPoints(){
        int yCor, xCor;
        for (int i = 0; i < width; i++){
            yval[i] = calcY(xMin + i*increX);
        }
    }
    
    public void paint(Graphics g){
        g.setColor( Color.gray );
        g.drawLine(0, length/2, width, length/2);
        g.drawLine(width/2, 0, width/2, length);
        for(int i = 0; i < width-1; i++){
            g.setColor( Color.BLACK );
            g.drawLine(i, length - (int)((yval[i]-yMin)*length/(yMax-yMin)), i+1, length - (int)((yval[i+1]-yMin)*length/(yMax-yMin)));
            g.fillRect(i, length - (int)((yval[i]-yMin)*length/(yMax-yMin)), 1, 1);
            
        }    
    }
    
    public void initializeWindow() {
        setTitle("Polynomial");
        setSize(width, length);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.white);
        setVisible(true); //calls paint() for the first time
    }

    
}
