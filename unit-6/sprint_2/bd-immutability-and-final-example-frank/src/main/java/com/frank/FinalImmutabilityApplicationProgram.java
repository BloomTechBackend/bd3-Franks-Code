package com.frank;

public class FinalImmutabilityApplicationProgram {

        private  static int nums[] = {1, 2, 3, 4};

        public static void main(String[] args) {
                System.out.println("Hello from main()");

                showArray();

                aClass anObject = new aClass(nums);

                showArray();
                anObject.showClass();

                System.out.println("-".repeat(50) + "\nAfter changing a value in main() array");
                nums[0] = 999;        // set the first element in main() to a new value
                showArray();          // display the values in main() array
                anObject.showClass(); // display the values in aClass object array

                System.out.println("-".repeat(50) + "\nAfter defining a new array in main() from aClass Object array");
                int [] Charles = anObject.getAnArray();  // Define a new array with the contents of aClass object array
                Charles[0] = -48;                        // Changing the value of the first element in new array in main()
                anObject.showClass();                    // Display the array in the object
        }

        public static void showArray() {
                System.out.println(("\nContents of array in main(): "));
                for (int i = 0; i < nums.length; i++) {
                        System.out.println("Element " + i + ": " + nums[i]);
                }
        }

}
