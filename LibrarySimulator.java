package project2;
/**
 * LibrarySimulator.java
 *
 * Group Members:
 * - Meshal Alhusaini  (ID: 445100204)
 * - Abdullah alshedayed (ID: 446106970)
 *
 * This program implements a Library Simulation System in Java.
 * It utilizes the Member class to manage user accounts and statistics.
 */

import java.util.Scanner;

public class LibrarySimulator {

    // ========================================================================
    // --- Data Storage (Using Member Objects) ---
    // ========================================================================

    // Instantiate the 3 members
    private static Member member1 = new Member(101, "abdullah alshedayed", 2);
    private static Member member2 = new Member(202, "Meshal Alhusaini", 0);
    private static Member member3 = new Member(303, "fahad salem", 5);

    private static Scanner scanner = new Scanner(System.in);

    // ========================================================================
    // --- Main Method ---
    // ========================================================================

    public static void main(String[] args) {
        accountSelectionMenu();
    }

    // ========================================================================
    // --- Main Menu ---
    // ========================================================================

    public static void accountSelectionMenu() {
        System.out.println("=================================================");
        System.out.println("   Welcome to KING SAUD UNIVERSITY Library Simulation");
        System.out.println("=================================================");

        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- Account Selection Menu ---");
            System.out.printf("1. Select User Account %d (%s)\n", member1.getId(), member1.getName());
            System.out.printf("2. Select User Account %d (%s)\n", member2.getId(), member2.getName());
            System.out.printf("3. Select User Account %d (%s)\n", member3.getId(), member3.getName());
            System.out.println("4. Login as Administrator");
            System.out.println("0. Exit Program");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                System.out.println("-------------------------------------------------");
                switch (choice) {
                    case 1:
                        userOperationsMenu(member1);
                        break;
                    case 2:
                        userOperationsMenu(member2);
                        break;
                    case 3:
                        userOperationsMenu(member3);
                        break;
                    case 4:
                        administratorMenu();
                        break;
                    case 0:
                        System.out.println("Thank you for using the Library Simulation System. Goodbye! 👋");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid menu option.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume invalid input
                choice = -1;
            }
        }
    }

    // ========================================================================
    // --- User Operations Menu ---
    // ========================================================================

    /**
     * Displays the operations menu for a specific Member.
     * @param member The Member object currently logged in.
     */
    public static void userOperationsMenu(Member member) {
        System.out.printf("Logged in as: %s\n", member.getName());

        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- Account Operations Menu ---");
            System.out.println("1. View Borrowed Books Count");
            System.out.println("2. Borrow Book (Fee: $0.50)");
            System.out.println("3. Return Book (No Fee)");
            System.out.println("4. View Session Summary");
            System.out.println("0. Exit to Main Menu");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                System.out.println("-------------------------------------------------");
                switch (choice) {
                    case 1:
                        member.viewBorrowedCount();
                        break;
                    case 2:
                        member.borrowOne();
                        break;
                    case 3:
                        member.returnOne();
                        break;
                    case 4:
                        member.displayStatistics();
                        break;
                    case 0:
                        System.out.printf("Ending session for %s. Returning to Main Menu.\n", member.getName());
                        // Reset session stats upon exit
                        member.reset(); 
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid menu option.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); 
                choice = -1;
            }
        }
    }

    // ========================================================================
    // --- Administrator Menu ---
    // ========================================================================

    public static void administratorMenu() {
        System.out.println("--- Logged in as Administrator ---");
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- Administrator Menu ---");
            System.out.println("1. View Total Revenue");
            System.out.println("2. Most Frequent Operation");
            System.out.println("0. Exit to Main Menu");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                System.out.println("-------------------------------------------------");
                switch (choice) {
                    case 1:
                        viewTotalRevenue();
                        break;
                    case 2:
                        mostFrequentOperation();
                        break;
                    case 0:
                        System.out.println("Returning to Main Menu.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid menu option.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); 
                choice = -1;
            }
        }
    }

    private static void viewTotalRevenue() {
        // Access the static variable from Member class
        System.out.printf("💰 Total Revenue Collected: $%.2f\n", Member.TotalRevenue);
    }

    private static void mostFrequentOperation() {
        System.out.println("--- Most Frequent User Operation ---");

        // Access static variables from Member class
        int borrows = Member.TotalBorrows;
        int returns = Member.TotalReturns;

        if (borrows > returns) {
            System.out.printf("Most Frequent: Borrow Operation (Total: %d)\n", borrows);
            System.out.printf("Return Operations Total: %d\n", returns);
        } else if (returns > borrows) {
            System.out.printf("Most Frequent: Return Operation (Total: %d)\n", returns);
            System.out.printf("Borrow Operations Total: %d\n", borrows);
        } else if (borrows > 0) {
            System.out.printf("Most Frequent: Borrow and Return Operations (Tied at %d each)\n", borrows);
        } else {
            System.out.println("No user operations (borrow or return) have been performed yet.");
        }
    }

}
