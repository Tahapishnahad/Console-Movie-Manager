import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import java.util.Base64
import javax.crypto.KeyGenerator

class Encryption {

    fun encrypt(text: String): Pair<String, String> {
        val keyGen = KeyGenerator.getInstance("AES")
        keyGen.init(128)
        val key = keyGen.generateKey()

        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, key)

        val encrypted = cipher.doFinal(text.toByteArray())
        val encryptedBase64 = Base64.getEncoder().encodeToString(encrypted)
        val keyBase64 = Base64.getEncoder().encodeToString(key.encoded)
        return Pair(encryptedBase64, keyBase64)
    }

    fun decrypt(encryptedBase64: String, keyBase64: String): String {
        val keyBytes = Base64.getDecoder().decode(keyBase64)
        val key = SecretKeySpec(keyBytes, "AES")

        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, key)

        val decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedBase64))
        return String(decrypted)
    }
}
