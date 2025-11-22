package project2;
/**
 * Member.java
 *
 * Group Members:
 * - Meshal Alhusaini  (ID: 445100204)
 * - Abdullah alshedayed (ID: 446106970)
 * - [Add 3rd Member Name] (ID: [Add ID])
 *
 * This class represents a library member account. It manages individual
 * session statistics and contributes to global library statistics.
 */

public class Member {

    // ========================================================================
    // --- Attributes (Private Instance Variables) ---
    // ========================================================================
    private int id;
    private String name;
    private int borrowedCount;

    // Session-based statistics (reset after logout)
    private int numViewBorrowed;
    private int numBorrows;
    private int numReturns;
    private double sessionFees;

    // ========================================================================
    // --- Global Statistics (Public Static Variables) ---
    // ========================================================================
    public static double TotalRevenue = 0.0;
    public static int TotalViewBorrowed = 0;
    public static int TotalBorrows = 0;
    public static int TotalReturns = 0;

    // Constants
    private static final double BORROW_FEE = 0.50;
    private static final int MAX_BOOKS = 5;

    // ========================================================================
    // --- Constructor ---
    // ========================================================================
    public Member(int id, String name, int borrowedCount) {
        this.id = id;
        this.name = name;
        this.borrowedCount = borrowedCount;
        // Initialize session stats to 0
        this.numViewBorrowed = 0;
        this.numBorrows = 0;
        this.numReturns = 0;
        this.sessionFees = 0.0;
    }

    // ========================================================================
    // --- Helper Methods (Private) ---
    // ========================================================================
    private boolean canBorrow() {
        return borrowedCount < MAX_BOOKS;
    }

    private boolean canReturn() {
        return borrowedCount > 0;
    }

    // ========================================================================
    // --- Operations (Public Methods) ---
    // ========================================================================

    /**
     * Prints the member's current borrowed book count.
     * Updates view statistics.
     */
    public void viewBorrowedCount() {
        // Update stats
        this.numViewBorrowed++;
        TotalViewBorrowed++;
        
        System.out.printf("📚 Books currently borrowed: %d\n", this.borrowedCount);
    }

    /**
     * Simulates borrowing a book.
     * @return true if successful, false otherwise.
     */
    public boolean borrowOne() {
        if (canBorrow()) {
            // Update state
            this.borrowedCount++;
            
            // Update session stats
            this.numBorrows++;
            this.sessionFees += BORROW_FEE;
            
            // Update global stats
            TotalBorrows++;
            TotalRevenue += BORROW_FEE;
            
            System.out.printf("✅ Successfully borrowed a book. Current count: %d\n", this.borrowedCount);
            System.out.printf("   A fee of $%.2f was charged for this borrow.\n", BORROW_FEE);
            return true;
        } else {
            System.out.printf("❌ Error: Cannot borrow book. You have reached the limit of %d books.\n", MAX_BOOKS);
            return false;
        }
    }

    /**
     * Simulates returning a book.
     * @return true if successful, false otherwise.
     */
    public boolean returnOne() {
        if (canReturn()) {
            // Update state
            this.borrowedCount--;
            
            // Update session stats
            this.numReturns++;
            
            // Update global stats
            TotalReturns++;
            
            System.out.printf("✅ Successfully returned a book. Current count: %d\n", this.borrowedCount);
            System.out.println("   No fee was charged for this return.");
            return true;
        } else {
            System.out.println("❌ Error: Cannot return book. You have no books currently borrowed.");
            return false;
        }
    }

    /**
     * Prints the member's session statistics.
     */
    public void displayStatistics() {
        System.out.println("--- Session Activity Summary ---");
        System.out.printf("Times 'View Count' used: \t%d\n", this.numViewBorrowed);
        System.out.printf("Borrowed Books: \t\t%d\n", this.numBorrows);
        System.out.printf("Returned Books: \t\t%d\n", this.numReturns);
        System.out.printf("Total Fees Incurred:\t\t$%.2f\n", this.sessionFees);
    }

    /**
     * Resets the session statistics to zero.
     */
    public void reset() {
        this.numViewBorrowed = 0;
        this.numBorrows = 0;
        this.numReturns = 0;
        this.sessionFees = 0.0;
    }

    // ========================================================================
    // --- Getters ---
    // ========================================================================
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}