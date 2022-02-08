package com.frank;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
// This is a sub class of PlayingCard class - use the keyword extends to indicate that
public class AmericanPlayingCard extends PlayingCard{
	// static means there only shared by all
	// final means cannot be changed once it has been assigned a value
	// static final means only one occurrence, shared by all, that can't be changed
	private static final CardValue DEFAULTCARDVALUE = CardValue.JOKER;
	private static final CardColor DEFAULTCOLOR     = CardColor.BLACK;
	private static final CardSuit  DEFAULTSUIT      = CardSuit.JOKER;
	private static final CardValue MAXVALUE         = CardValue.KING;
	private static final CardValue MINVALUE         = CardValue.JOKER;

	// enumeration is the assignment of a numeric value to a word

	// enum allow us to define a specific set of values for a data type
	// use enum just like a class or a datatype (int, double, boolean, etc)
	// enum allow us to validate and control values in variables easily
	// In Java enum values are assigned intergers starting at 0

	// Making the enum public allows application programs to use the enum as constant too
	public static enum CardColor {  // define words to represent allowable card colors (instead of String)
		BLACK, RED                  // These are the only valid values Java will allow
	};
	public static enum CardSuit {          // public is OK since they are constants and cannot be changed
		SPADE, CLUB, HEART, DIAMOND, JOKER // static so it can be referenced using the class name. ie. no object required
	};
	public static enum CardValue {  // Using the fact that enums are really integers inside value to name our values
		JOKER, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
	//     0	1	 2     3      4     5    6     7      8      9    10    11    12    13
	};

	public AmericanPlayingCard() 
	{
	// super class ctor requires (int, String, String) parameters
	// so we need to convert enums when using them as arguments to the super class ctor
	//
	// Since the subclass must call the superclass ctor to initialize it's associated super class
	// the keyword super is used to reference the anything in the supr class.
	//    super() - super class ctor - whenever you see () after a word that's not id, while - its a method
	//    super. - reference something defined in the super class - class.object, class.method()
		super(DEFAULTCARDVALUE.ordinal(), DEFAULTSUIT.toString(), DEFAULTCOLOR.toString());
	} 

	public AmericanPlayingCard(CardValue value, CardSuit suit) {
        // super class ctor requires (int, String, String) parameters
		// so we need to convert enums when using them as arguments to the super class ctor
		//
		// Since we need to be sure the correct color is sent to the super class ctor for the suit
		// and the color is a parameter passed to the ctor
        // we use the conditional operator (?) to provide a conditional value for the colo based on the suit
	    //
		// The conditional operator is like an if-then-else for values
		//      condition ? value-if-true : value-if-false
		//
		// Use the conditional operator when an if-then-else cannot be coded - conditional parameter values
		//
		// Below: if suit is CLUB or SPADE use BLACK, else use RED
		//
		// Note use of .ordinal() and .toString() for enum to get the integer value as String for the enum
		//      because the super class ctor (PlayingCard) wants the value as an int and the suit and color as a String

		super(value.ordinal(),               // Call super ctor with value passed
			  suit.toString(),               // Call super ctor with suit passed
			 (suit == CardSuit.CLUB || suit == CardSuit.SPADE) ? CardColor.BLACK.toString() : CardColor.RED.toString());
	}

	// It is common for a subclass to call a super class method it overrides - in  this toString()
	// because the sub class doesn't know how to handle the super class data
	// the sub class is only responsible for any data it adds to the super class
	// so all the sub class has to do is decide how to handle it's new data
	// and use the super class method to handle super class data
	//
	@Override
	public String toString() {
		// the .values() method for any enum returns the enum values as an array
		// since PlayingCard stores the int value assigned to an enum value
		// we can use the value stored in the PlayingCard to convert the int value to its enum

		// super. indicates you want the super class version of the method
		//        super. is optional if subclass does not have method with the same name

		return "AmericanPlayingCard: "
	          +"Value: "    + getValue()     + " ("     // use the super class method to get the value
			  + CardValue.values()[super.getValue()] + ")"    //  and then use that value to determine enum it corressponds to
	          +" - Color: " + super.getColor()                // use the super class method to get the color
			  +" - Suit: "  + getSuit()                       // use the super class method to get the suit
				                                              //     super. optional because the sub class does not a have a method called getSuit()
			  +" - super.toString()=" + super.toString() + "\n";  // call the super class toString()
		                                                          // super. is required because we have a method with the same name
		                                                          // if omitted super. we would be calling ourselves recursively
		                                                          //    which will most likely result in an exception due to excessive resource use
	}

	public void showCard() {
		System.out.println(this.toString());
	}

}
