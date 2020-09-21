/**
* @author Khmelevtsova Mariia m.khmelevtsova@student.csn.khai.edu
* @version 1.0
*/

public class Main {

    private static String GroupNumber = "545A";
    private static String FullName = "Khmelevtsova Mariia Aleksadrovna";
    private static int VariantNumber = 15;
    private static String Initials =    "*   * **   **  **\n" +
                                        "*  *  * * * * *  *\n" +
                                        "***   *  *  * ****\n" +
                                        "*  *  *     * *  *\n" +
                                        "*   * *     * *  *\n";

    public static void main(String[] args) {
        System.out.println("Group: " + GroupNumber);
        System.out.println("Student: " + FullName);
        System.out.println("Variant: " + VariantNumber);
        System.out.println(Initials);
    }
}