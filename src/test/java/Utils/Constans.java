package Utils;

import Pages.Page;
import org.openqa.selenium.WebDriver;

public class Constans {

    public static final String SIGNUP_H1_EN = "Sign Up";
    public static final String SIGNUP_H1_ZH = "註冊";

    public static final String SIGNIN_H1_EN = "Sign In";
    public static final String SIGNIN_H1_ZH = "登入";

    public static final String PROFILE_H1_EN = "My Profile";
    public static final String PROFILE_H1_ZH = "我的个人信息";

    public static final String TERMS_H1_EN = "Terms and Conditions";
    public static final String TERMS_H1_ZH = "服務條款";

    public static final String FAQ_H1_EN = "Frequently Asked Questions";
    public static final String FAQ_H1_ZH = "常見問題";

    public static final String CONTACTS_H1_EN = "Contact Us";
    public static final String CONTACTS_H1_ZH = "聯絡我們";

    public static final String GAME_H1_EN = "Featured Games";
    public static final String GAME_H1_ZH = "抱歉!";


    public static final String WEALTH_H1_EN = "My wealth";
    public static final String WEALTH_H1_ZH = "我的財富";

    public static final String GED_CASH_OUT_H1_EN = "GED Cash Out";
    public static final String GED_CASH_OUT_H1_ZH = "GED兌現";

    public static final String BUY_GEC_H1_EN = "Buy GEC";
    public static final String BUY_GEC_H1_ZH = "購買GEC";

    public static final String FINANCIAL_DETAILS_H1_EN = "Financial Details";
    public static final String FINANCIAL_DETAILS_H1_ZH = "財務細節";

    public static final String RESET_TRX_PASSWORD_EN = "Reset Transaction Password";
    public static final String RESET_TRX_PASSWORD_ZH = "重置交易密碼";





    //ERRORS
    public static final String ATTEMPTS_EN = " password attempts left, If you exceed attempt limit, your account will be temporarily locked.";
    public static final String ATTEMPTS_ZH = " 次嘗試輸入密碼的機會，假如輸入錯誤密碼次數超過上限，您的賬戶將會暫時被封。";

    public static final String PASSPHRASE_2_ATTEMPTS_EN = "The passphrase you entered is wrong, you have 2 more tries left.";
    public static final String PASSPHRASE_2_ATTEMPTS_ZH = "您輸入的通行碼不正確，您尚餘2次機會。";

    public static final String PASSPHRASE_1_ATTEMPTS_EN = "The passphrase you entered is wrong, you have 1 more tries left.";
    public static final String PASSPHRASE_1_ATTEMPTS_ZH = "您輸入的通行碼不正確，您尚餘1次機會。";







    //SIGNUP

    public static final String REQUIRED_DATE_OF_BIRTH_EN = "REQUIRED DATE OF BIRTH";
    public static final String REQUIRED_DATE_OF_BIRTH_ZH = "必填 出生日期";

    public static final String INVALID_WITHOUT_UPPERCASE_PASSWORD_EN ="ONE UPPERCASE CHARACTER (A–Z)";
    public static final String INVALID_WITHOUT_UPPERCASE_PASSWORD_ZH ="一個大寫字母 (A-Z)";

    public static final String INVALID_WITHOUT_LOWERCASE_PASSWORD_EN ="ONE LOWERCASE CHARACTER (A–Z)";
    public static final String INVALID_WITHOUT_LOWERCASE_PASSWORD_ZH ="一個大寫字母 (A-Z)";

    public static final String INVALID_WITHOUT_NUMBERS_PASSWORD_EN ="ONE NUMERIC (0–9)";
    public static final String INVALID_WITHOUT_NUMBERS_PASSWORD_ZH ="一个数字(0–9)";

    public static final String INVALID_SHORT_PASSWORD_EN ="AT LEAST 8 CHARACTERS";
    public static final String INVALID_SHORT_PASSWORD_ZH ="至少 8 个字符";


    public static final String LONG_USERNAME_EN = "VERY LONG, NOT MORE 100 CHARACTERS";
    public static final String LONG_USERNAME_ZH = "很长，不能超过国100个字符";

    public static final String EXISTS_EMAIL_EN = "THIS EMAIL EXISTS";
    public static final String EXISTS_EMAIL_ZH = "该电邮地址已存在";

    public static final String INVALID_EMAIL_EN = "INVALID EMAIL ADDRESS";
    public static final String INVALID_EMAIL_ZH = "無效的電子郵箱地址";

    public static final String REQUIRED_AGREE_EN = "YOU NEED TO AGREE WITH TERMS AND CONDITIONS";
    public static final String REQUIRED_AGREE_ZH = "您需要同意条款和条件";

    public static final String WRONG_LINK_EN = "REFERRAL LINK WRONG";
    public static final String WRONG_LINK_ZH = "轉介鏈接錯誤";




    //SIGNIN

    public static final String BAD_CREDENTIALS_EN ="SORRY, YOU ENTERED WRONG LOGIN ID/PASSWORD, PLEASE TRY AGAIN";
    public static final String BAD_CREDENTIALS_ZH ="对不起，你输入的电子邮箱/密码不正确，\n" + "请重新尝试";


    //IMAGE_UPLOADER
    public static final String IMAGE_TEXT_EN = "Please upload only .*jpg, *.jpeg, *.png, .*gif file less than 5MB";
    public static final String IMAGE_TEXT_ZH = "请只上传* jpg，* .jpeg，* .png，。* gif文件少于5MB";

    public static final String IMAGE_ERROR_FORMAT_EN = "Incorrect image format. Please upload only .*jpg, *.jpeg, *.png, .*gif file";
    public static final String IMAGE_ERROR_FORMAT_ZH = "图片格式错误。请仅上传以下格式*jpg, *.jpeg, *.png, .*gif";

    public static final String IMAGE_ERROR_SIZE_EN = "Incorrect image size. Please upload images only less than 5MB";
    public static final String IMAGE_ERROR_SIZE_ZH = "图片大小出错。上传图片请小于5MB";


    //BUTTONS
    public static final String CONFIRM_BUTTON_EN = "CONFIRM";
    public static final String CONFIRM_BUTTON_ZH = "确认";

    public static final String SUCCESS_BUTTON_EN = "SUCCESS";
    public static final String SUCCESS_BUTTON_ZH = "成功";

    public static final String COPY_EN = "Copy";
    public static final String COPY_ZH = "複製";

    public static final String COPIED_EN = "Copied";
    public static final String COPIED_ZH = "已複製";

    public static final String I_HAVE_PAID_EN = "i've paid";
    public static final String I_HAVE_PAID_ZH = "我已經付款了";

    public static final String SUCCESS_BUY_GEC_EN = "transaction processed";
    public static final String SUCCESS_BUY_GEC_ZH = "交易處理";

    public static final String PROCCED_EN = "Proceed";
    public static final String PROCCED_ZH = "確認";



    //REQUIRED
    public static final String REQUIRED_EN = "REQUIRED PASSWORD";
    public static final String REQUIRED_ZH = "必须填写 确认新密码";


    public static final String REQUIRED_NEW_EN = "REQUIRED NEW PASSWORD";
    public static final String REQUIRED_NEW_ZH = "必須填寫 確認新密碼";

    public static final String REQUIRED_CONFIRM_EN = "REQUIRED CONFIRM NEW PASSWORD";
    public static final String REQUIRED_CONFIRM_ZH = "必须填写 新密码";

    public static final String REQUIRED_OLD_PASSWORD_EN = "REQUIRED OLD PASSWORD";
    public static final String REQUIRED_OLD_PASSWORD_ZH = "必须填写 旧密码";

    public static final String REQUIRED_GED_AMOUNT_EN = "REQUIRED GED TO CASH OUT";
    public static final String REQUIRED_GED_AMOUNT_ZH = "必須填寫 GED兌現數量";

    public static final String REQUIRED_GEC_AMOUNT_EN = "REQUIRED AMOUNT";
    public static final String REQUIRED_GEC_AMOUNT_ZH = "必須填寫 數量";


    public static final String REQUIRED_TRX_PASS_EN = "REQUIRED TRANSACTION PASSWORD";
    public static final String REQUIRED_TRX_PASS_ZH = "必須填寫 交易密碼";

    public static final String REQUIRED_PASSWORD_EN = "REQUIRED PASSWORD";
    public static final String REQUIRED_PASSWORD_ZH = "必須填寫 密碼";

    public static final String REQUIRED_PASSPHRASE_EN = "REQUIRED PASSPHRASE";
    public static final String REQUIRED_PASSPHRASE_ZH = "必填 密碼";

    public static final String REQUIRED_RESET_TRX_PASS_EN = "REQUIRED RESET TRANSACTION PASSWORD";
    public static final String REQUIRED_RESET_TRX_PASS_ZH = "必填 重置交易密碼";

    public static final String REQUIRED_CONFIRM_TRX_PASS_EN = "REQUIRED CONFIRM TRANSACTION PASSWORD";
    public static final String REQUIRED_CONFIRM_TRX_PASS_ZH = "必填 確認交易密碼";

    public static final String REQUIRED_SEQURITY_QUESTION_EN = "REQUIRED SECURITY QUESTION";
    public static final String REQUIRED_SEQURITY_QUESTION_ZH = "必填 密码安全问题";



    //WALLET
    public static final String INVALID_AMOUNT_OF_GED_EN = "INVALID AMOUNT OF GED";
    public static final String INVALID_AMOUNT_OF_GED_ZH = "無效的GED數目";

    public static final String INSUFFICIENT_AMOUNT_OF_GED_EN = "THE GED AMOUNT IN THIS ACCOUNT IS LESS THAN YOUR INPUTTED CASH OUT AMOUNT";
    public static final String INSUFFICIENT_AMOUNT_OF_GED_ZH = "賬號內的GED數量少於提現數量";

    public static final String INVALID_AMOUNT_OF_GEC_EN = "INVALID AMOUNT OF GEС";
    public static final String INVALID_AMOUNT_OF_GEC_ZH = "無效的GEC數量";

    public static final String INVALID_BTC_ADDRESS_EN = "INVALID BTC WALLET ADDRESS";
    public static final String INVALID_BTC_ADDRESS_ZH = "無效BTC電子錢包地址";

    public static final String INCORRECT_BTC_ADDRESS_EN = "INVALID BTC WALLET ADDRESS";
    public static final String INCORRECT_BTC_ADDRESS_ZH = "無效的BTC錢包地址";

    public static final String TRX_PASS_DO_NOT_MATCH_EN = "TRANSACTION PASSWORDS DON’T MATCH";
    public static final String TRX_PASS_DO_NOT_MATCH_ZH = "交易密碼不符";
//
//    public static final String REQUIRED_EN = "REQUIRED";
//    public static final String REQUIRED_ZH = "必填";

}
