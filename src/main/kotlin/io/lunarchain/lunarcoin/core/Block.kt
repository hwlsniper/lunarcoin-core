package io.lunarchain.lunarcoin.core

import io.lunarchain.lunarcoin.util.CodecUtil
import io.lunarchain.lunarcoin.util.CryptoUtil
import org.joda.time.DateTime
import org.spongycastle.util.encoders.Hex
import java.math.BigInteger

/**
 * 区块(Block)类，包含了区块高度(height)，上一个区块哈希值(parentHash)，旷工账户地址(coinBase)，交易列表(transactions)和时间戳(time)。
 */
class Block(
    val version: Int, val height: Long, val parentHash: ByteArray,
    val coinBase: ByteArray, val time: DateTime, val difficulty: Long,
    val nonce: Int, val totalDifficulty: BigInteger, val stateRoot: ByteArray, val trxTrieRoot: ByteArray,
    val transactions: List<Transaction>, val gasLimit: ByteArray
) {

    val header = BlockHeader(
        version, height, parentHash, coinBase, time, difficulty, nonce, totalDifficulty, stateRoot,
        trxTrieRoot
    )

    /**
     * 区块(Block)的哈希值(KECCAK-256)
     */
    val hash: ByteArray
        get() = CryptoUtil.hashBlock(this)

    fun encode(): ByteArray {
        return CodecUtil.encodeBlock(this)
    }

    override fun toString(): String {
        return "h:$height, nonce:$nonce, dt:$difficulty, tt: $totalDifficulty, time:$time, " +
                "${transactions.size} of transactions stateRoot:${Hex.toHexString(stateRoot)} trxTrieRoot: ${Hex.toHexString(trxTrieRoot)}."
    }
}
