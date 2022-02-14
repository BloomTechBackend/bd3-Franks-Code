package com.amazon.ata.hashingset.partsmanager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class PartManager {
    // HashSet used to hold the parts used in a device
    // HashSet stores based on a unique Hash Code
    // If a second element is attempted to be added with an existing Hash Code,
    ///            it is not added - a HashCode collision is ignored by HashSet
    // If a HashCode for an object being added is already in the set, it is NOT added to the set
    //    (more about HashSets in the next session)
    private Set<DevicePart> deviceParts = new HashSet<>();

    // We are leaving the HashSet example in this code for comparison
    //  In the real world you would use either a HashSet (no duplicate HashCodes)
    //     or an array of ArrayList (allow duplicate HashCodes), not both

    // Use an array of ArrayList to store the objects (instead of a HashSet)
    // We will use the HashCode to determine the index for the array
    // Each element in the array is an ArrayList of the objects to be stored

    // Determine the number of elements in the array
    // We will use 10 because we have a small number of objects and we want collisions
    //                        and that was the number used in the Warm-up material

    private final int NUMPARTS = 10;  // number of elements in the array as a constant

    // define an array of ArrayList<DevicePart>
    //      each element will be an ArrayList of DeviceParts
    // to define an array:
    //              datatype[] name  = new datatype[size];
    //                   int[] nums  = new int[10];
    //       interface/super[] name  = new  subclass[size];
    private List<DevicePart>[] parts = new ArrayList[NUMPARTS]; // each element will be an ArrayList of DeviceParts

    // Where we add a DevicePart to our data store (HashSet or array of ArrayList<DevicePart>
    public void addDevicePart(DevicePart devicePart) {
        // .add() method is used to add an element to a HashSet (just like it was for ArrayList)
        // .add() automatically calls the hashCode() method to determine the Hash Code for the object
        // .add() for HashSet will not add a duplicate hash code to the set
        boolean isAdded = deviceParts.add(devicePart);

        // Add a DevicePart object to the appropriate ArrayList<DevicePart> in our array

        // To add an object to the appropriate ArrayList in the array using the HashCode
        //    1. Find the HashCode to the object
        //    2. Determine the array index indicated by the HashCode (use the modulus (%))
        //       The modulus of the HashCode and the size of th array will give us an index
        //           modulus returns the remainder of integer dividing the first value by the second
        //           integer division returns a quotient and a remainder
        //             5 / 2 - quotient=2, remainder=1
        //             modulus gives you the remainder
        //
        // Since HashCodes may be negative values and indexes cannot be negative
        // use the Math.abs() to be sure our indexes are not negative
        int partIndex = Math.abs(devicePart.hashCode() % NUMPARTS); // array index based on the HashCode for the object

        //    3. Use the index to store the object in the array
        //       Do we already have an ArrayList at the element in the array the index references?
        //          yes - add the new DevicePart to the ArrayList
        //          no  -  Instantiate a new ArrayList for the array element
        //                 add the DevicePart to ArrayList
        if (parts[partIndex] == null) {            // if the element does NOT already have an ArrayList
            parts[partIndex] = new ArrayList<>();  //    Instantiate a new ArrayList for the array element
            parts[partIndex].add(devicePart);      //    Add the DevicePart to ArrayList
        } else {   // if the element DOES already have an ArrayList
            parts[partIndex].add(devicePart);      //    Add the DevicePart to ArrayList
        }
// Alternate coding techniaue to implement logic
//        if(parts[partIndex]  == null) {            // if the element does NOT already have an ArrayList
//            parts[partIndex] = new ArrayList<>();  //    Instantiate a new ArrayList for the array element
//        }
//            parts[partIndex].add(devicePart);      //    Add the DevicePart to ArrayList

        return; // not required - included for academic/training purpose of stopping the debugger here

    }  // End of addDevicePart()
    public void printDeviceParts() {
        for (DevicePart devicePart : deviceParts) {
            System.out.println(devicePart);
        }
    }

        // method to find a DevicePart in the array and  return
        public DevicePart findPart(DevicePart requestedPart) {
            DevicePart returnedObject = null;  // Define an object to hold the return value - initially we don't have it

            int partIndex = Math.abs(requestedPart.hashCode() % NUMPARTS); // array index based on the HashCode for the object

            // Check to see if the part to find is in the ArrayList at the element based on HashCode
            // parts[partIndex] - the ArrayList containing the DeviceParts
            //   if the DevicePart is in the ArrayList, retrieve it
            //      .get() - used to retreive an element from an ArrayList based on it's index in the ArrayList
            //      .indexOf() - returns the index of an element in the ArrayList
            if (parts[partIndex] != null) {             // if there is an ArrayList for the index based on HashCode
                if (parts[partIndex].contains(requestedPart)) { //    and the ArrayList contains the requested part
                    int foundIndex = parts[partIndex].indexOf(requestedPart);    // Determine the index of the requested part
                    returnedObject = parts[partIndex].get(foundIndex);   //    and retreive it
                }
            }
            // no else is required because we initialize the returnedObject to null
            return returnedObject;             // return the object with the return value or null if not found
        }

    }  // End of Part Manager


