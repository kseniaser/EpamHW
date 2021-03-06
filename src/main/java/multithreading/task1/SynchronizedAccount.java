package multithreading.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SynchronizedAccount {

    private Path accountFile;
    private int accountBalance;

    public SynchronizedAccount() {
        this.accountFile = Paths.get("e:\\Git\\java\\Epam\\src\\main\\java\\multithreading\\Account");
        this.accountBalance = 0;
        UploadAccountBalance();
    }

    public void makeOperation(int amount) {
        synchronized (this) {
            accountBalance += amount;
            WriteBalanceChanges(amount);
            WriteBalance();
        }

    }

    public void WriteBalance() {
        System.out.println(accountBalance);
    }

    private void UploadAccountBalance() {
        try (BufferedReader reader = Files.newBufferedReader(accountFile)) {
            String s;
            while ((s = reader.readLine()) != null) {
                accountBalance += Integer.parseInt(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void WriteBalanceChanges(int amount) {
        try (BufferedWriter writer = Files.newBufferedWriter(accountFile, StandardOpenOption.APPEND)) {
            writer.newLine();
            writer.write(String.valueOf(amount));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
