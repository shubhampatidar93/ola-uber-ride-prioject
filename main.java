import java.util.Scanner;

// Custom exception for invalid ride types
class InvalidRideTypeException extends Exception {
    public InvalidRideTypeException(String message) {
        super(message);
    }
}

// Abstract Ride class
abstract class Ride {
    private String driverName;
    private String vehicleNumber;
    protected double distance;

    public Ride(String driverName, String vehicleNumber, double distance) {
        this.driverName = driverName;
        this.vehicleNumber = vehicleNumber;
        this.distance = distance;
    }

    // Getters
    public String getDriverName() {
        return driverName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public double getDistance() {
        return distance;
    }

    // Abstract method to calculate fare
    public abstract double calculateFare();
}

// BikeRide class
class BikeRide extends Ride {
    public BikeRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }

    @Override
    public double calculateFare() {
        return distance * 10;
    }
}

// CarRide class
class CarRide extends Ride {
    public CarRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }

    @Override
    public double calculateFare() {
        return distance * 20;
    }
}

// Main class
public class RideSharingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Input: ride type
            String rideType = scanner.nextLine().trim().toLowerCase();

            // Input: distance
            double distance = Double.parseDouble(scanner.nextLine().trim());

            if (distance <= 0) {
                System.out.println("Distance must be greater than 0.");
                return;
            }

            Ride ride;

            // Choose based on ride type
            switch (rideType) {
                case "bike":
                    ride = new BikeRide("Ravi", "BIKE1234", distance);
                    break;
                case "car":
                    ride = new CarRide("Amit", "CAR5678", distance);
                    break;
                default:
                    throw new InvalidRideTypeException("Invalid ride type: " + rideType);
            }

            // Output
            System.out.println("Driver: " + ride.getDriverName());
            System.out.println("Vehicle No: " + ride.getVehicleNumber());
            System.out.printf("Distance: %.1f km\n", ride.getDistance());
            System.out.printf("Fare: ₹%.2f\n", ride.calculateFare());

        } catch (InvalidRideTypeException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for distance.");
        } finally {
            scanner.close();
        }
    }
}
