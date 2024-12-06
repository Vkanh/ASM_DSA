import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagement management = new StudentManagement();

        while (true) {
            try {
                System.out.println("\n=== MENU ===");
                System.out.println("1. Add Student");
                System.out.println("2. Display the student list.");
                System.out.println("3. Update the student's score");
                System.out.println("4. Remove the student");
                System.out.println("5. Search for a student.");
                System.out.println("6. Sort students by score.");
                System.out.println("7. Sort students by name");
                System.out.println("8. Print the list of removed students.");
                System.out.println("9. Exit");
                System.out.print("Choose a function from 1 to 9. ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter student ID: ");
                        String id = scanner.nextLine();
                        String name;
                        while (true) {
                            System.out.print("Enter student name: ");
                            name = scanner.nextLine();
                            if (name.matches("[a-zA-Z\\s]+")) {
                                break;
                            } else {
                                System.out.println("Name must not contain numbers or special characters. Please enter again!");
                            }
                        }
                        double score;
                        while (true){
                        try {
                            System.out.print("Enter student score: ");
                            score = Double.parseDouble(scanner.nextLine());
                            if (score < 0 || score > 10) {
                                System.out.println("Score must be between 0 and 10. Please enter again!");
                            }
                            else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid score! Please enter a number.");
                            continue;
                        }
                        }

                        management.addLast(new Student(id, name, score));
                        break;
                    case 2:
                        management.traverse();
                        break;
                    case 3:
                        System.out.print("Enter the student ID to update: ");
                        id = scanner.nextLine();
                        System.out.print("Enter new score: ");
                        score = Double.parseDouble(scanner.nextLine());
                        management.updateStudent(id, score);
                        break;
                    case 4:
                        System.out.print("Enter the student ID to remove: ");
                        id = scanner.nextLine();
                        management.deleteById(id);
                        break;
                    case 5:
                        System.out.print("Enter the student ID to search: ");
                        id = scanner.nextLine();
                        Student student = management.searchById(id);
                        if (student != null) {
                            System.out.println(student);
                        } else {
                            System.out.println("Student not found!");
                        }
                        break;
                    case 6:
                        management.bubbleSortByScore();
                        break;
                    case 7:
                        management.insertionSortByName();
                        break;
                    case  8:
                        management.printDeletedStudents();
                        break;
                    case 9:
                        System.out.println("Exit the program.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input error! Please enter an integer.");
            } catch (Exception e) {
                System.out.println("An error has occurred: " + e.getMessage());
            }
        }
    }
}