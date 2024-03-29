/**
 * This class allows users to create a variable of type RomanNumeral, and also get its Arabic value
 * @author Justin Espinal
 */
public class RomanNumeral { //start of class RomanNumeral
    private String Roman; //initialize a private instance variable to hold the RomanNumeral
    private int Arabic; //Initialize a private instance variable to hold the converted Arabic number

    /**
     * 
     * @param r The String inputed by the user to be converter to a Roman Numeral
     */
    public RomanNumeral(String r) //constructor that takes in a String as a parameter to convert to type RomanNumeral
    {
        if (!r.matches("[MDCLXVI]+")) {//if the new Roman Numeral has characters besides MDCLXVI then it is incorrect
            throw new IllegalRomanNumeralException(r);//since it is invalid we throw a new IllegalRomanNumeralException
        }
        //if valid we continue creating the Roman Numeral
        Roman = r; //set Roman equal to parameter
        Arabic = romanToInt(Roman); //convert said Roman input to Arabic using the romanToInt method and store it in Arabic
    }

    /**
     * 
     * @return String In this method we return the value of Roman to the user for later use
     */
    public String getRoman() //get method for Roman
    {
        return Roman; //return the Roman value to the user
    }

    /**
     * 
     * @param r setting the current value of Roman to the new value passed by the user into the parameters
     */
    public void setRoman(String r) //set method for Roman
    {
        Roman = r; //set Roman equal to the parameter given by the user
    }

    /**
     * 
     * @return int returns the Arabic value to the user
     */
    public int getArabic() //get method for Arabic
    {
        return Arabic; //return the Arabic value to the user
    }

    /**
     * @param other new object passed into the equals method by the user to check if it is equal to the current object
     *@return returns true if both objects are equal, false if not
     */
    public boolean equals(Object other) { //equals method for RomanNumeral
        return (other != null &&
            getClass() == other.getClass() &&
            Roman.equals(((RomanNumeral) other).Roman)
        ); //this checks that the Object does not equal null, is of the same class, and if both hold the same value. if this is all true then hey are equal!
    } //end of equals method

    /**
     * 
     * @param other newly passed RomaNumeral that we would like to compare to our current Numeral
     * @return int we return a integer that corresponds to a less than, greater than, or equal to value. 1:greater, 0:equal, -1:less than
     */
    public int compareTo(RomanNumeral other) { //compareTo method for RomanNumeral
        if (Arabic > other.Arabic) //if the current Arabic value if greater then the Arabic value passed in the parameters return 1
        {
            return 1;
        } 
        else if (Arabic == other.Arabic) //else if the current arabic value is equal to the arabic value passed in the parameters return 0
        {
            return 0;
        } 
        else return -1; //if the current arabic value is less than the arabic value passed in the parameters return -1
    }

    /**
     * @return String here we just return the Roman Numeral value
     */
    public String toString() { //toString method for RomanNumeral
        return Roman; //here we simply just return the String value of Roman
    }

    /**
     * 
     * @param s pass a Roman Numeral of type string to the function and return its Arabic value
     * @return int Arabic value is returned to the user
     */
    public static int romanToInt(String s) //Method to convert Roman numerals to Arabic numerals
    {
        int sum = 0; //make an integer to track the value of the Roman Numeral String

        for (int i = 0; i < s.length(); i++) //make a for loop to iterate through each part of the Roman numeral
        {
            /*
            Roman numerals work as I = 1 , V = 5, X = 10, L=50, C=100,D=500, and M=1000! Reading from left
            to right, we would just add these numbers and get the Arabic Number value! But, this is not
            so straight forward, as if a lesser value such as I comes first and then V, then we must 
            subtract instead of add! So we the below if statements, we check the current letter, and
            the letter ahead to see if it is a larger value
			 */
            if ((s.charAt(i)) == 'I') {
                if (i + 1 != s.length()) //we do this so that we don't check an out of bounds index and cause an error
                {
                    if (s.charAt(i + 1) == 'V' || s.charAt(i + 1) == 'X') {
                        sum = sum - 1; //we subtract 1 instead of adding 1 if there is a V or X in front of I
                    } 
                    else {
                        sum = sum + 1;
                    }
                } 
                else {
                    sum = sum + 1; //if there is not a larger value in front of I then we just add 1 like normal
                }
            } 
            else if (s.charAt(i) == 'V') {
                sum += 5; //there cannot be a greater value after V so we just add +5 and dont worry about -5
            } 
            else if (s.charAt(i) == 'X') {
                if (i + 1 != s.length()) {
                    if (s.charAt(i + 1) == 'L' || s.charAt(i + 1) == 'C') {
                        sum = sum - 10; //we subtract 10 instead of adding 10 if there is a L or C in front of X
                    } 
                    else {
                        sum = sum + 10;
                    }
                } 
                else sum += 10; //if there is not a larger value in front of I then we just add 1 like normal
            } 
            else if (s.charAt(i) == 'L') {
                sum += 50; //there cannot be a greater value after L so we just add 50 and don't worry about subtraction
            } 
            else if (s.charAt(i) == 'C') {
                if (i + 1 != s.length()) {
                    if (s.charAt(i + 1) == 'D' || s.charAt(i + 1) == 'M') {
                        sum = sum - 100; //we subtract 100 if there is a value of D or M after C
                    } 
                    else {
                        sum = sum + 100; //if not then we add 100
                    }
                } 
                else {
                    sum = sum + 100;
                }
            } 
            else if (s.charAt(i) == 'D') {
                sum += 500; //there cannot be a greater value after D so we just add 500
            } 
            else if (s.charAt(i) == 'M') {
                sum += 1000; //there cannot be a greater value after M so we just add 1000
            }
        }
        return sum;
        //if you notice after our if(i+1!=s.length()) there is an additional repeating else statement, this is done
        //because if i+1 is = length that would make us go out of the index range, meaning we are already on the last index
    } //end of romanToInt method
}