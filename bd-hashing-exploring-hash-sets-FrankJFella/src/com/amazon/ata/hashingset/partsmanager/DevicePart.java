package com.amazon.ata.hashingset.partsmanager;

import java.util.List;
import java.util.Objects;

public class DevicePart {
    // immutable - value cannot be changed once its assigned (no setter in the class for the attribute)
    //   mutable - value can be changed anytime (setter defined in the class for the attribute)

    private String manufacturer;             // read-only, non-changeable (no setter defined in the class)
    private String manufacturersPartNumber;  // read-only, non-changeable (no setter defined in the class)
    private List<AmazonDevice> devicesUsedIn;

    public DevicePart(String manufacturer, String manufacturersPartNumber, List<AmazonDevice> devicesUsedIn) {
        this.manufacturer = manufacturer;
        this.manufacturersPartNumber = manufacturersPartNumber;
        this.devicesUsedIn = devicesUsedIn;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getManufacturersPartNumber() {
        return manufacturersPartNumber;
    }

    public List<AmazonDevice> getDevicesUsedIn() {
        return devicesUsedIn;
    }

    public void setDevicesUsedIn(List<AmazonDevice> devicesUsedIn) {
        this.devicesUsedIn = devicesUsedIn;
    }

    public void addDeviceUsedIn(AmazonDevice amazonDevice) {
        devicesUsedIn.add(amazonDevice);
    }

    public void removeDeviceUsedIn(AmazonDevice amazonDevice) {
        devicesUsedIn.remove(amazonDevice);
    }

    @Override
    public String toString() {
        return String.format("Device Part: {manufacturer: %s, manufacturersPartNumber: %s, devicesUsedIn: %s}",
                manufacturer, manufacturersPartNumber, devicesUsedIn);
    }
    // Initially - since there was no equals() nor hashCode() method defined for the class, the Object equals method will be used
    //       the Object class equals() and hashCode() methods use the location of objects NOT the content
    // If we want to have the content of two DevicePart objects compared for equals or used in hasCOde
    //       we need to be provide an equals() method override

    @Override
    public boolean equals(Object o) {
        // Code added for academic/demo purposes
        System.out.println("equals() in DevicePart class....");

        // if the object being compared is the same as the object being compared to, they are equal
        if (this == o) return true;

        // if the object being compared to is null or not the same class as the object being compared they are NOT equal
        if (o == null || getClass() != o.getClass()) return false;

        // instantiate an object of the class from the object being compared to since a generic Object is used as an argument
        DevicePart that = (DevicePart) o;  // cast the object passed as an argument to an object of this class

        // Note: use of String class .equals() method since == does not compare teh contents of Strings
        return getManufacturer().equals(that.getManufacturer()) && getManufacturersPartNumber().equals(that.getManufacturersPartNumber());
    }

    @Override
    public int hashCode() {
        // Code added for academic/demo purposes
        System.out.println("hashCode() in DevicePart class....");
        // use the Java provided hash() method with the immutable attributes to generate a Hash Code
        return Objects.hash(getManufacturer(), getManufacturersPartNumber());
    }
} // end of the DevicePart class
