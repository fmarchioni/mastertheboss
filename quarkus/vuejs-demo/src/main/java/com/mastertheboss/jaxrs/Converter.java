package com.mastertheboss.jaxrs;

import org.wildfly.common.iteration.ByteIterator;
import org.wildfly.security.WildFlyElytronProvider;
import org.wildfly.security.password.PasswordFactory;
import org.wildfly.security.password.interfaces.MaskedPassword;
import org.wildfly.security.password.spec.*;

import java.security.Provider;


public class Converter {
    static final Provider ELYTRON_PROVIDER = new WildFlyElytronProvider();

    static final String TEST_PASSWORD = "password";

    public static void main(String[] args) throws Exception {

        String algorithm = "masked-MD5-DES";
        char[] keyMaterial = "somearbitrarycrazystringthatdoesnotmatter".toCharArray();
        byte[] salt = "12345678".getBytes();
        int iterationCount = 12;
        String clearPassword = "password123";

        PasswordFactory passwordFactory = PasswordFactory.getInstance(MaskedPassword.ALGORITHM_MASKED_MD5_DES, ELYTRON_PROVIDER);

        MaskedPasswordAlgorithmSpec maskedAlgorithmSpec = new MaskedPasswordAlgorithmSpec(keyMaterial, iterationCount, salt);
        EncryptablePasswordSpec encryptableSpec = new EncryptablePasswordSpec(clearPassword.toCharArray(), maskedAlgorithmSpec);

        MaskedPassword original = (MaskedPassword) passwordFactory.generatePassword(encryptableSpec);
        byte[] masked = original.getMaskedPasswordBytes();
        MaskedPasswordSpec maskedPasswordSpec = new MaskedPasswordSpec(keyMaterial, iterationCount, salt, masked);

        //Get the masked password as a string
        String maskedPassword = ByteIterator.ofBytes(maskedPasswordSpec.getMaskedPasswordBytes()).base64Encode().drainToString();
        System.out.println(String.format("Masked Password: " + maskedPassword));

        //Verify the masked password is the encryption of the clear password
        MaskedPassword restored = (MaskedPassword) passwordFactory.generatePassword(maskedPasswordSpec);
        System.out.println(String.format("Password Verified '%b'", passwordFactory.verify(restored, clearPassword.toCharArray())));

    }

}