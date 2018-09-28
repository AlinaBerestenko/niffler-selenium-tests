package Data;

public class PasswordData {
    private String oldPassword;
    private String newPassword;
    private String newConfirmPassword;


    public PasswordData(String oldPassword, String newPassword, String newConfirmPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.newConfirmPassword = newConfirmPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getNewConfirmPassword() {
        return newConfirmPassword;
    }
}

