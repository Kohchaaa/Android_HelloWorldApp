package com.example.helloworldapp.feature.setting

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.helloworldapp.feature.setting.component.Gender
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID

@Serializable
data class UserInput(
    val userId: String = UUID.randomUUID().toString(),
    val displayName: String,
    @Serializable(with = LocalDateSerializer::class)
    val birthDate: LocalDate?,
    val gender: Gender?,
    val allergies: Set<String>,
    val dislikeIngredients: List<String>,
    val dislikeDishes: List<String>,
    val customAttributes: Map<String, String>?, // Key-value形式で柔軟に選択肢して保存（カスタムアトリビュート型作った方が良いのかな）
    val intakeTargetVersion: String
)

// 1. LocalDate用のカスタムシリアライザーを作成
object LocalDateSerializer : KSerializer<LocalDate> {
    // 日付のフォーマットを定義 (YYYY-MM-DD)
    @RequiresApi(Build.VERSION_CODES.O)
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    // このシリアライザーがどのようなデータ型（プリミティブ型）に変換されるかを定義
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)

    // シリアライズ処理：LocalDateオブジェクトをStringに変換する
    @RequiresApi(Build.VERSION_CODES.O)
    override fun serialize(encoder: Encoder, value: LocalDate) {
        encoder.encodeString(value.format(formatter))
    }

    // デシリアライズ処理：StringをLocalDateオブジェクトに変換する
    @RequiresApi(Build.VERSION_CODES.O)
    override fun deserialize(decoder: Decoder): LocalDate {
        return LocalDate.parse(decoder.decodeString(), formatter)
    }
}