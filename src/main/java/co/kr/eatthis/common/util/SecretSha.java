package co.kr.eatthis.common.util;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

public class SecretSha {

    /*
     * 입력 패스워드 값 암호화 (단방향)
     */
    public static String encryptPassword(String enteredPW) {
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm("SHA-512");
        return passwordEncryptor.encryptPassword(enteredPW);
    }

    /*
     * 패스워드 값 비교
     * submittedPassword : 입력 패스워드 값
     * encryptedPassword : DB에 저장된 패스워드 값
     */
//    public static boolean checkPassword(String submittedPassword, String encryptedPassword) {
//        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
//        passwordEncryptor.setAlgorithm("SHA-512");
//        return passwordEncryptor.checkPassword(submittedPassword, encryptedPassword);
//    }

}