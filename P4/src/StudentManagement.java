import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {
    private Node head; // Top of the link list
    private ArrayList<Student> deletedStudents;
    // Constructor
    public StudentManagement() {
        this.head = null;
        this.deletedStudents = new ArrayList<>();
    }

    //1. Add a student to the top of the list.
    public void addFirst(Student student) {
        Node newNode = new Node(student);
        newNode.next = head;
        head = newNode;
        System.out.println("students have been added.");
    }

    // 2. Add the student to the end of the list
    public void addLast(Student student) {
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
            System.out.println("The student has been added");
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;

    }

    // 3. Remove the student at the top of the list.
    public void deleteFirst() {
        if (head == null) {
            System.out.println("The list is empty!");
            return;
        }
        deletedStudents.add(head.data); // Save the deleted student.
        head = head.next;
    }

    // 4. Remove the student at the end of the list.
    public void deleteLast() {
        if (head == null) {
            System.out.println("The list is empty!");
            return;
        }
        if (head.next == null) {
            head = null;
            return;
        }
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        deletedStudents.add(head.data); // Save the deleted student.
        current.next = null;
    }

    // 5. Browse through the list and print student information.
    public void traverse() {
        if (head == null) {
            System.out.println("The list is empty!");
            return;
        }
        Node current = head;
        System.out.println("\nStudent list:");
        System.out.println(String.format("%-10s %-20s %-5s %-10s", "Student ID", "Student Name", "Score", "Rank"));
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    // 6. Search for a student by ID
    public Student searchById(String id) {
        Node current = head;
        while (current != null) {
            if (current.data.getId().equalsIgnoreCase(id)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }
    //7.Search for a student by score using binary search.
    public Student binarySearchByScore(double score) {
        // Convert the linked list to an array list for binary search
        List<Student> studentList = new ArrayList<>();
        Node current = head;
        while (current != null) {
            studentList.add(current.data);
            current = current.next;
        }

        // Perform binary search on the list
        int left = 0;
        int right = studentList.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Student midStudent = studentList.get(mid);
            if (midStudent.getScore() == score) {
                return midStudent;  // Found the student
            } else if (midStudent.getScore() < score) {
                left = mid + 1;  // Search in the right half
            } else {
                right = mid - 1;  // Search in the left half
            }
        }
        return null;  // Not found
    }

    // 8. Update the student's score by student ID.
    public void updateStudent(String id, double newScore) {
        Node current = head;
        while (current != null) {
            if (current.data.getId().equalsIgnoreCase(id)) {
                current.data = new Student(id, current.data.getName(), newScore);
                System.out.println("The student's score has been updated." + id);
                return;
            }
            current = current.next;
        }
        System.out.println("Student not found with ID: " + id);
    }

    // 9. Remove the student by student ID.
    public void deleteById(String id) {
        if (head == null) {
            System.out.println("The list is empty!");
            return;
        }
        if (head.data.getId().equalsIgnoreCase(id)) {
            head = head.next;
            System.out.println("The student has been removed. " + id);
            return;
        }
        Node current = head;
        while (current.next != null && !current.next.data.getId().equalsIgnoreCase(id)) {
            current = current.next;
        }
        if (current.next == null) {
            System.out.println("Student not found with ID:" + id);
        } else {
            deletedStudents.add(head.data); // Save the deleted student
            current.next = current.next.next;
            System.out.println("The student has been removed. " + id);
        }

    }
    // 10.Sort by score (bubblesort)."
    public void bubbleSortByScore() {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            while (current.next != null) {
                if (current.data.getScore() > current.next.data.getScore()) {
                    // Swap data between two nodes.
                    Student temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
        System.out.println("The list has been sorted by score in ascending order.");
    }

    // 11.Sort students by name (Insertion Sort)."
    public void insertionSortByName() {
        if (head == null || head.next == null) return;
        Node sorted = null;

        Node current = head;
        while (current != null) {
            Node next = current.next;

            // Insert a node into the sorted list."
            if (sorted == null || sorted.data.getName().compareToIgnoreCase(current.data.getName()) >= 0) {
                current.next = sorted;
                sorted = current;
            } else {
                Node temp = sorted;
                while (temp.next != null && temp.next.data.getName().compareToIgnoreCase(current.data.getName()) < 0) {
                    temp = temp.next;
                }
                current.next = temp.next;
                temp.next = current;
            }
            current = next;
        }
        head = sorted;
        System.out.println("The list has been sorted by name in alphabetical order.");
    }
    // 12. Print the list of removed students.
    public void printDeletedStudents() {
        if (deletedStudents.isEmpty()) {
            System.out.println("No students have been removed.");
            return;
        }
        System.out.println("\nList of removed students:");
        for (Student student : deletedStudents) {
            System.out.println(student);
        }
    }
}
