package com.frank;

import java.util.Arrays;

public class aClass {
        private int[] anArray;

        public aClass(int[] intArray) {

                // anArray = intArray;   // replaced by a defensive copy (aka deep copy)
                //                    source-array, size-of-new-arry
                anArray = Arrays.copyOf(intArray  , intArray.length);  // make a copy of the array reference passed in
        }

        public int[] getAnArray() {
                // return anArray;  // replaced by a defensive return due to returning reference allows
                                    // anyone with the reference to change the data
                return Arrays.copyOf(anArray, anArray.length);  // return a copy of the member data
        }

        public void showClass() {
                System.out.println(("\nContents of array in aClass: "));
                for (int i = 0; i < anArray.length; i++) {
                        System.out.println("Element " + i + ": " + anArray[i]);
                }
        }
}
