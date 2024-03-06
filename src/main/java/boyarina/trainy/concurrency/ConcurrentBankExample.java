package boyarina.trainy.concurrency;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentBankExample {
    public static void main(String[] args) {
        ConcurrentBank bank = new ConcurrentBank();

        // Создание счетов
        BankAccount account1 = bank.createAccount(1000);
        BankAccount account2 = bank.createAccount(500);

        // Перевод между счетами
        Thread transferThread1 = new Thread(() -> bank.transfer(account1, account2, 200));
        Thread transferThread2 = new Thread(() -> bank.transfer(account2, account1, 100));

        transferThread1.start();
        transferThread2.start();

        try {
            transferThread1.join();
            transferThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Account1 balance: " + account1.balance);
        System.out.println("Account2 balance: " + account2.balance);

        // Вывод общего баланса
        System.out.println("Total balance: " + bank.getTotalBalance());
    }

    public static class ConcurrentBank {
        private final List<BankAccount> accountList = new ArrayList<>();
        private final Lock lock = new ReentrantLock();

        public BankAccount createAccount(int amount) {
            BankAccount bankAccount = new BankAccount(UUID.randomUUID(), new AtomicInteger(amount));
            accountList.add(bankAccount);
            return bankAccount;
        }

        public void transfer(BankAccount account1, BankAccount account2, int amount) {
            lock.lock();
            try {
                if (Integer.parseInt(account1.getBalance().toString()) < amount) {
                    throw new IllegalArgumentException("insufficient funds");
                }
                account1.setBalance(account1.withdraw(amount));
                account2.setBalance(account2.deposit(amount));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                lock.unlock();
            }
        }

        public int getTotalBalance() {
            int totalBalance = 0;
            for (BankAccount account : accountList) {
                totalBalance += Integer.parseInt(account.getBalance().toString());
            }
            return totalBalance;
        }
    }

    @AllArgsConstructor
    @Data
    public static class BankAccount {

        public UUID uuid;
        public AtomicInteger balance;

        public AtomicInteger deposit(int amount) {
            return new AtomicInteger(balance.addAndGet(amount));
        }

        public AtomicInteger withdraw(int amount) {
            return new AtomicInteger(balance.addAndGet(-amount));
        }

        public AtomicInteger getBalance() {
            return balance;
        }
    }
}
