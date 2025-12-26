package com.example.helloworldapp.feature.profile.component

/**
 * アレルギー品目データ
 * AIプロンプト生成用にシンプル化
 *
 * @property value 管理用ID
 * @property name 食材名（AIに渡す文字列）
 * @property isMandatory 表示義務があるかどうか（UI強調用）
 */
data class Allergen(
    val value: String,
    val name: String,
    val isMandatory: Boolean
)

object AllergenMockData {

    // 特定原材料（義務 8品目）
    private val mandatoryItems = listOf(
        Allergen("shrimp", "えび", true),
        Allergen("crab", "かに", true),
        Allergen("walnut", "くるみ", true),
        Allergen("wheat", "小麦", true),
        Allergen("buckwheat", "そば", true),
        Allergen("egg", "卵", true),
        Allergen("milk", "乳", true),
        Allergen("peanut", "落花生（ピーナッツ）", true)
    )

    // 特定原材料に準ずるもの（推奨 20品目）
    private val recommendedItems = listOf(
        Allergen("almond", "アーモンド", false),
        Allergen("abalone", "あわび", false),
        Allergen("squid", "いか", false),
        Allergen("salmon_roe", "いくら", false),
        Allergen("orange", "オレンジ", false),
        Allergen("cashew_nut", "カシューナッツ", false),
        Allergen("kiwi", "キウイフルーツ", false),
        Allergen("beef", "牛肉", false),
        Allergen("sesame", "ごま", false),
        Allergen("salmon", "さけ", false),
        Allergen("mackerel", "さば", false),
        Allergen("soybean", "大豆", false),
        Allergen("chicken", "鶏肉", false),
        Allergen("banana", "バナナ", false),
        Allergen("pork", "豚肉", false),
        Allergen("matsutake", "まつたけ", false),
        Allergen("peach", "もも", false),
        Allergen("yam", "やまいも", false),
        Allergen("apple", "りんご", false),
        Allergen("gelatin", "ゼラチン", false)
    )

    // アプリで表示する全リスト
    val all: List<Allergen> = mandatoryItems + recommendedItems
}