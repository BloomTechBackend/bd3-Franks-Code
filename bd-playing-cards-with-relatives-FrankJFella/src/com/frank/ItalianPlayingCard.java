package com.frank;

// This a subclass PlayingCard
public class ItalianPlayingCard extends PlayingCard {

        // 2-arg ctor to take a value and a suit
        public ItalianPlayingCard(int value, String suit) {
                // We need to call the super class ctor to initialize teh super class values
                super(value, suit, "Yellow");
        }
        // Override the Object class toString() since it doesn't do we want it to do


        @Override
        public String toString() {
                return "ItalianPlayingCard{} " + super.toString();
        }

        // Display the object
        public void showCard() {
                System.out.println(this.toString()); // use the toString() to display
        }
}  // End of ItalianPlayingCard class
