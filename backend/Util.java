package backend;

public class Util {
    private double num1, num2;
    private char symbol;

    public char getSymbol(){
        return symbol;

    }

    public void setSymbol(char symbol){
        this.symbol= symbol;
    }

    public void setnum1(double num1){
        this.num1= num1;
    }

    public void setnum2(double num2){
        this.num2= num2;
    }

    public double add(){
        return this.num1+this.num2;
    }

    public double sub(){
        return this.num1-this.num2;
    }

    public double mul(){
        return this.num1*this.num2;
    }

    public double div(){
        return this.num1/this.num2;
    }
}
