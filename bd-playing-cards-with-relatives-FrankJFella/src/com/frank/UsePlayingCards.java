package com.frank;

import java.util.ArrayList;

public class  UsePlayingCards {
// This is our application program which will instantiate object and use their methods to manipulate them
// We know this is the application program because it has the main() method
	public static void main(String[] args) {

		PlayingCard aCard = new PlayingCard(1, "Spades", "Purple");  // Instantiate a PlayingCard
		System.out.println("Our Playing Card is: " + aCard);
		// System.out.println() - display String values
		//      everything it displays needs to be converted to a String
		//                 "Our Playing Card is: " + aCard
		//                          String         + PlayingCard Object
		//                          String         + create a String version of PlayingCardObject
		//                                           (looks for toString() in PlayingCard Class)
		//                                           if no toString() exists in PlayingCard Class
		//                                              Java looks to any super class for toString()
		//                                              Since every class is a subclass of Object
		//                                                    and Object has a toString() method
		//                                                    Java uses the Object toString() to create String
		//                                              The Object toString() generates a String containing:
		//                                                "package.class@location" for the object

		AmericanPlayingCard aUSACard = new AmericanPlayingCard(AmericanPlayingCard.CardValue.ONE, AmericanPlayingCard.CardSuit.HEART);
		System.out.println("aUSACard is :");
		aUSACard.showCard();  // runs the AmericanPlayingCard showCard()

		AmericanPlayingCard aUSACard2 = new AmericanPlayingCard(AmericanPlayingCard.CardValue.KING, AmericanPlayingCard.CardSuit.SPADE);
		System.out.println("aUSACard2 is :");
		aUSACard2.showCard();  // runs the AmericanPlayingCard showCard()

		ItalianPlayingCard pizza = new ItalianPlayingCard(4, "Coins");
		System.out.println("Our Italian card is: " + pizza);

		System.out.println("Value in pizza is: " + pizza.getValue());  // the super class "get" methods are used
		System.out.println(" Suit in pizza is: " + pizza.getSuit());
		System.out.println("Color in pizza is: " + pizza.getColor());

		pizza.setValue(13);   // Use the super class "set" method
		System.out.println("Value in pizza is: " + pizza.getValue());  // the super class "get" methods are used

		pizza.showCard();

	}  // End of main()

}  // End of class that holds main()
