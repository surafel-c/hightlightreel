#include <iostream>
#include <string>
#include <limits>
#include <fstream> // For file input/output
using namespace std;

const int MAX_TRANSACTIONS = 100;

struct Transaction {
    string description;
    double amount;
};

class FinanceTracker {
private:
    Transaction transactions[MAX_TRANSACTIONS];
    int transactionCount;
    double balance;

public:
    FinanceTracker() : transactionCount(0), balance(0) {}

    void addTransaction(const string& description, double amount) {
        if (transactionCount < MAX_TRANSACTIONS) {
            transactions[transactionCount].description = description;
            transactions[transactionCount].amount = amount;
            transactionCount++;
            balance += amount;
        } else {
            cout << "Transaction limit reached. Cannot add more transactions.\n";
        }
    }

    void showTransactions() const {
        cout << "Transaction History:\n";
        for (int i = 0; i < transactionCount; ++i) {
            cout << "[" << i + 1 << "] ";
            cout << transactions[i].description << ": ";
            cout << (transactions[i].amount >= 0 ? "+" : "-") << "$";
            cout << (transactions[i].amount) << "\n";
        }
    }

    double getBalance() const {
        return balance;
    }

    int getTransactionCount() const {
        return transactionCount;
    }

    void saveTransactionsToFile() {
        ofstream outfile("transactions.txt");
        for (int i = 0; i < transactionCount; ++i) {
            outfile << transactions[i].description << " " << transactions[i].amount << "\n";
        }
        outfile.close();
    }
};

int main() {
    string userName;
    cout << "Welcome to the Finance Tracker!\n";
    cout << "Please enter your name: ";
    getline(cin, userName);

    FinanceTracker tracker;

    // Load previous transactions from file (if available)
    ifstream file("transactions.txt");
    if (file.is_open()) {
        while (!file.eof()) {
            string description;
            double amount;
            file >> description >> amount;
            tracker.addTransaction(description, amount);
        }
        file.close();
    }

    // Budget Management Section
    double monthlyBudget = 0.0;
    cout << "\nHello, " << userName << "! Let's set up your budget.\n";
    cout << "Enter your monthly budget: $";
    cin >> monthlyBudget;
    cout << "Budget set successfully.\n";

    // Savings Goals Section
    double savingsGoal = 0.0;
    cout << "Enter your savings goal: $";
    cin >> savingsGoal;
    cout << "Savings goal set successfully.\n";

    // Main Finance Tracker Loop
    while (true) {
        cout << "\nHello, " << userName << "! What would you like to do?\n";
         cout<< "1. Add income\n";
        cout << "2. Add expense\n";
        cout << "3. Show transactions\n";
        cout << "4. Show balance\n";
        cout << "5. Set budget\n";
        cout << "6. Set savings goal\n";
        cout << "7. Exit\n";
        cout << "Choose an option: ";

        int choice;
        if (!(cin >> choice)) {
            // Handle non-numeric input
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
            cout << "Invalid input. Please enter a numeric choice.\n";
            continue;
        }

        switch (choice) {
            case 1:{
		
                // Add income
                cout << "Enter income description: ";
                string incomeDesc;
                cin.ignore();
                getline(cin, incomeDesc);
                cout << "Enter income amount: $";
                double incomeAmount;
                cin >> incomeAmount;
                tracker.addTransaction(incomeDesc, incomeAmount);
                break;}

            case 2:{   // Add expense
                cout << "Enter expense description: ";
                string expenseDesc;
                cin.ignore();
                getline(cin, expenseDesc);
                cout << "Enter expense amount: $";
                double expenseAmount;
                cin >> expenseAmount;
                tracker.addTransaction(expenseDesc, -expenseAmount); // Negative amount indicates an expense
                break;
}
            case 3:{
            	// Show transactions
                tracker.showTransactions();
                break;

				
			}
                
            case 4:{
		  // Show balance
                cout << "Your current balance is: $" << tracker.getBalance() << "\n";
                break;
}
            case 5:{
		
                // Set budget
                cout << "Enter your new monthly budget: $";
                double newBudget;
                cin >> newBudget;
                monthlyBudget = newBudget;
                break;}

            case 6:{
				
			
                // Set savings goal
                cout << "Enter your new savings goal: $";
                double newSavingsGoal;
                cin >> newSavingsGoal;
                savingsGoal = newSavingsGoal;
                break;}

            case 7:{
		
                // Save transactions to file before exiting
                tracker.saveTransactionsToFile();

                cout << "Exiting the budget tracker.\n";
                return 0;}

            default:{
                // Invalid option
                cout << "Invalid option. Please try again.\n";}
        }
    }

    return 0;
}
