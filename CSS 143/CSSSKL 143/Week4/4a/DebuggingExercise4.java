package lab4;

class DebuggingExercise4 {

    public static void main(String[] args) {
        Account a = null;
        a.deposit(100);
        System.out.println(a.getOwner() + " has $" + a.getBalance());
        a.withdraw(200);
        System.out.println("After trying to withdraw $200, " + a.getOwner() + " has $" + a.getBalance());
    }
}

