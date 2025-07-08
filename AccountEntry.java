import java.io.Serializable;

public class AccountEntry implements Serializable {
    private static final long serialVersionUID = 1L;
    public String accountName;
    public String username;
    public String passwordEnc;
    public byte[] iv;

    public AccountEntry(String accountName, String username, String passwordEnc, byte[] iv) {
        this.accountName = accountName;
        this.username = username;
        this.passwordEnc = passwordEnc;
        this.iv = iv;
    }
}