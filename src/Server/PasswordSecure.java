package Server;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * PasswordSecure class for the registration and Login processes of the minigame
 *
 * @author Florence
 * @version 9/3/2018
 */

public class PasswordSecure {
    /**
     *this generates a random value for salt which is unique for every user
     * @returna byte array which is unique for each new user
     * @throws NoSuchAlgorithmException
     */

    public static byte[] createSalt() throws NoSuchAlgorithmException {

        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[8];
        random.nextBytes(salt);

        return salt;

    }

    /**
     *this class encrypts a password inputted as a string using a unique salt value and PBKDF2 with SHA-1 as the hashing algorithm
     * @param password the password inputted by the user into the gui for the registration process
     * @param salt the unique byte array used to encrypt the user password
     * @return a byte array which is stored in the userdatabase as their password
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static byte[] EncryptPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {

        String algorithm = "PBKDF2WithHmacSHA1";

        int derivedKeyLength = 160;

        int iterations = 1800;

        PBEKeySpec x = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);

        SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

        return f.generateSecret(x).getEncoded();

    }

    /**
     *this method checks an inputted password in the string format against the password saved as a byte array in the database and its respective salt value
     * @param attemptedPassword te string inputted to the gui when the user is logging in- this is to be checked
     * @param encryptedPassword the password stored in the database as a byte[]
     * @param salt the unique byte array stored in the database with the user record
     * @return a boolean true or false, true if the password entered is correct and false if the password is incorrect
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */

    public static boolean passwordCheck(String attemptedPassword, byte[] encryptedPassword,
                                 byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {

        byte[] encryptedAttemptedPassword = EncryptPassword(attemptedPassword, salt);

        return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);

    }



}
