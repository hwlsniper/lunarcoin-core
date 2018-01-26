package nebular.core

import nebular.util.CryptoUtil
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.junit.Assert
import org.junit.Test
import org.joda.time.format.DateTimeFormat.forPattern
import kotlin.test.assertEquals


class CryptoUtilTest {

  @Test
  fun privateKeyEncryptDecryptTest() {
    val kp = CryptoUtil.generateKeyPair()
    val privateKey = kp.private
    val password = "pleasechangeit"

    val encrypted = CryptoUtil.encryptPrivateKey(privateKey, password)
    val decrypted = CryptoUtil.decryptPrivateKey(encrypted, password)

    Assert.assertArrayEquals(privateKey.encoded, decrypted.encoded)
  }

}
