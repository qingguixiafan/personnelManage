package util;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/12/18.
 */
public class RegexUtil {

    public static final String REGEX_MOBILE = "^[1][3,4,5,7,8][0-9]{9}$";

    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    public static void main(String[] args) {
//        System.out.println(isMobile("17755458660"));
        System.out.println(isEmail("234@163.cn"));
    }
}
