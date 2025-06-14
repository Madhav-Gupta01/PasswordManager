import javax.crypto.SecretKey;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PasswordManagerCore {
    private static final String FILE = "vault.dat";
    private Map<String, AccountEntry> vault;
    private SecretKey key;
    private byte[] salt;

    public PasswordManagerCore(char[] masterPassword) throws Exception {
        File file = new File(FILE);
        if (!file.exists()) {
            salt = EncryptionUtil.generateSalt();
            key = EncryptionUtil.deriveKey(masterPassword, salt);
            vault = new HashMap<>();
            save();
        } else {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                salt = (byte[]) ois.readObject();
                key = EncryptionUtil.deriveKey(masterPassword, salt);
                vault = (Map<String, AccountEntry>) ois.readObject();
            }
        }
    }

    public void add(String account, String username, String password) throws Exception {
        byte[] iv = EncryptionUtil.generateIv();
        String enc = EncryptionUtil.encrypt(password, key, iv);
        vault.put(account, new AccountEntry(account, username, enc, iv));
        save();
    }

    public String get(String account) throws Exception {
        AccountEntry e = vault.get(account);
        if (e == null) return null;
        String dec = EncryptionUtil.decrypt(e.passwordEnc, key, e.iv);
        return "Account: " + e.accountName + "\nUsername: " + e.username + "\nPassword: " + dec;
    }

    private void save() throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(salt);
            oos.writeObject(vault);
        }
    }
}
