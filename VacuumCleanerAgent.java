public class VacuumCleanerAgent {
    static String vacuumAgent(String location, String status) {
        if (status.equalsIgnoreCase("Dirty")) return "Suck";
        if (location.equalsIgnoreCase("A")) return "Move Right";
        return "Move Left";
    }

    public static void main(String[] args) {
        System.out.println(vacuumAgent("A", "Dirty"));
        System.out.println(vacuumAgent("A", "Clean"));
        System.out.println(vacuumAgent("B", "Dirty"));
        System.out.println(vacuumAgent("B", "Clean"));
    }
}
