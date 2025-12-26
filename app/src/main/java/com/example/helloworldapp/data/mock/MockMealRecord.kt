package com.example.helloworldapp.data.mock

import com.example.helloworldapp.type.Ingredient
import com.example.helloworldapp.type.InputMethod
import com.example.helloworldapp.type.MealRecord
import com.example.helloworldapp.type.MealType
import com.example.helloworldapp.type.Nutrients
import java.time.LocalDateTime
import java.time.Month
import java.util.UUID

// ---------------------------------------------------------
// モックデータ生成
// ---------------------------------------------------------

val currentUserId = UUID.randomUUID().toString()

val mockMealRecord: List<MealRecord> = listOf(
    // --- 2025年12月15日 (平日・通常ルーチン) ---
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 15, 7, 30),
        mealType = MealType.BREAKFAST,
        inputMethod = InputMethod.TEXT,
        inputData = "トーストとコーヒー",
        imagePath = null,
        mealName = "バタートーストセット",
        nutrients = Nutrients(350f, 8.0f, 12.0f, 45.0f, 0.8f),
        estimationDetail = listOf(
            Ingredient("食パン", "1枚", "6枚切り"),
            Ingredient("バター", "10g", "有塩"),
            Ingredient("コーヒー", "200ml", "ブラック")
        )
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 15, 12, 15),
        mealType = MealType.LUNCH,
        inputMethod = InputMethod.IMAGE,
        inputData = null,
        imagePath = "/images/20251215_lunch.jpg",
        mealName = "唐揚げ定食",
        nutrients = Nutrients(850f, 32.0f, 40.0f, 90.0f, 3.5f),
        estimationDetail = listOf(
            Ingredient("鶏もも肉", "150g", null),
            Ingredient("白米", "200g", "中盛り"),
            Ingredient("味噌汁", "1杯", "ワカメ・豆腐"),
            Ingredient("キャベツ千切り", "30g", null)
        )
    ),

    // ---------------------------------------------------------
    // 2025-12-16: 5種類全部入り (朝・昼・夕・間食・夜食)
    // ---------------------------------------------------------
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 16, 7, 30),
        mealType = MealType.BREAKFAST,
        inputMethod = InputMethod.IMAGE,
        inputData = null,
        imagePath = "/images/20251216_breakfast.jpg",
        mealName = "トーストセット",
        nutrients = Nutrients(450f, 15f, 12f, 60f, 1.2f),
        estimationDetail = listOf(Ingredient("食パン", "1枚"), Ingredient("目玉焼き", "1個"), Ingredient("コーヒー", "1杯"))
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 16, 12, 15),
        mealType = MealType.LUNCH,
        inputMethod = InputMethod.TEXT,
        inputData = "生姜焼き定食 ご飯大盛り",
        imagePath = null,
        mealName = "豚の生姜焼き定食",
        nutrients = Nutrients(850f, 30f, 40f, 90f, 3.5f),
        estimationDetail = listOf(Ingredient("豚肉", "150g"), Ingredient("ご飯", "250g"), Ingredient("味噌汁", "1杯"))
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 16, 15, 0),
        mealType = MealType.SNACK,
        inputMethod = InputMethod.IMAGE,
        inputData = null,
        imagePath = "/images/20251216_snack.jpg",
        mealName = "シュークリーム",
        nutrients = Nutrients(250f, 4f, 15f, 20f, 0.2f),
        estimationDetail = listOf(Ingredient("シュークリーム", "1個"))
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 16, 19, 30),
        mealType = MealType.DINNER,
        inputMethod = InputMethod.TEXT,
        inputData = "コンビニのサラダチキンとサラダ",
        imagePath = null,
        mealName = "ヘルシーサラダセット",
        nutrients = Nutrients(300f, 25f, 5f, 10f, 1.5f),
        estimationDetail = listOf(Ingredient("サラダチキン", "1個"), Ingredient("レタスミックス", "1袋"))
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 16, 23, 45),
        mealType = MealType.MIDNIGHT,
        inputMethod = InputMethod.TEXT,
        inputData = "カップヌードル",
        imagePath = null,
        mealName = "カップラーメン",
        nutrients = Nutrients(350f, 8f, 14f, 45f, 4.5f),
        estimationDetail = listOf(Ingredient("カップ麺", "1個"))
    ),

    // --- 2025年12月18日 (飲み会・深夜) ---
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 18, 19, 0),
        mealType = MealType.DINNER,
        inputMethod = InputMethod.IMAGE,
        inputData = null,
        imagePath = "/images/20251218_dinner.jpg",
        mealName = "居酒屋コース",
        nutrients = Nutrients(600f, 45.0f, 20.0f, 10.0f, 2.5f),
        estimationDetail = listOf(
            Ingredient("刺身盛り合わせ", "1人前", "マグロ・サーモン他"),
            Ingredient("焼き鳥", "3本", "タレ・塩"),
            Ingredient("ビール", "350ml", "中ジョッキ")
        )
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 18, 23, 45),
        mealType = MealType.MIDNIGHT,
        inputMethod = InputMethod.TEXT,
        inputData = "締めのラーメン",
        imagePath = null,
        mealName = "豚骨ラーメン",
        nutrients = Nutrients(950f, 28.0f, 55.0f, 80.0f, 6.5f),
        estimationDetail = listOf(
            Ingredient("中華麺", "1玉", "硬め"),
            Ingredient("豚骨スープ", "400ml", "背脂多め"),
            Ingredient("チャーシュー", "2枚", "バラ肉")
        )
    ),

    // ---------------------------------------------------------
    // 2025-12-20: 同じMealTypeが複数ある日 (SNACK x 2)
    // ---------------------------------------------------------
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 20, 8, 0),
        mealType = MealType.BREAKFAST,
        inputMethod = InputMethod.TEXT,
        inputData = "プロテインのみ",
        imagePath = null,
        mealName = "プロテイン",
        nutrients = Nutrients(120f, 20f, 1f, 5f, 0.1f),
        estimationDetail = listOf(Ingredient("ホエイプロテイン", "30g"))
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 20, 10, 30), // 朝のおやつ
        mealType = MealType.SNACK,
        inputMethod = InputMethod.TEXT,
        inputData = "ナッツ類",
        imagePath = null,
        mealName = "ミックスナッツ",
        nutrients = Nutrients(180f, 5f, 15f, 4f, 0.0f),
        estimationDetail = listOf(Ingredient("アーモンド", "10粒"), Ingredient("くるみ", "2個"))
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 20, 13, 0),
        mealType = MealType.LUNCH,
        inputMethod = InputMethod.IMAGE,
        inputData = null,
        imagePath = "/images/20251220_lunch.jpg",
        mealName = "ボロネーゼパスタ",
        nutrients = Nutrients(700f, 20f, 25f, 80f, 2.8f),
        estimationDetail = listOf(Ingredient("パスタ", "100g"), Ingredient("ミートソース", "150g"))
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 20, 16, 0), // 午後のおやつ
        mealType = MealType.SNACK,
        inputMethod = InputMethod.IMAGE,
        inputData = null,
        imagePath = "/images/20251220_snack2.jpg",
        mealName = "ショートケーキ",
        nutrients = Nutrients(400f, 3f, 25f, 35f, 0.3f),
        estimationDetail = listOf(Ingredient("ショートケーキ", "1個"))
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 20, 19, 0),
        mealType = MealType.DINNER,
        inputMethod = InputMethod.TEXT,
        inputData = "自炊：野菜炒め",
        imagePath = null,
        mealName = "肉野菜炒め",
        nutrients = Nutrients(500f, 18f, 30f, 20f, 2.0f),
        estimationDetail = listOf(Ingredient("豚肉", "80g"), Ingredient("キャベツ", "100g"), Ingredient("もやし", "1袋"))
    ),

    // ---------------------------------------------------------
    // 2025-12-21: 同じMealTypeが複数ある日 (DINNER x 2)
    // ---------------------------------------------------------
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 21, 9, 0),
        mealType = MealType.BREAKFAST,
        inputMethod = InputMethod.TEXT,
        inputData = "おにぎり",
        imagePath = null,
        mealName = "鮭おにぎり",
        nutrients = Nutrients(200f, 4f, 1f, 40f, 1.2f),
        estimationDetail = listOf(Ingredient("おにぎり", "1個"))
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 21, 13, 30),
        mealType = MealType.LUNCH,
        inputMethod = InputMethod.TEXT,
        inputData = "うどん",
        imagePath = null,
        mealName = "きつねうどん",
        nutrients = Nutrients(450f, 10f, 5f, 80f, 4.0f),
        estimationDetail = listOf(Ingredient("うどん", "1玉"), Ingredient("油揚げ", "1枚"))
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 21, 18, 0), // 夕食1回目
        mealType = MealType.DINNER,
        inputMethod = InputMethod.IMAGE,
        inputData = null,
        imagePath = "/images/20251221_dinner1.jpg",
        mealName = "焼肉",
        nutrients = Nutrients(900f, 40f, 60f, 10f, 2.5f),
        estimationDetail = listOf(Ingredient("カルビ", "100g"), Ingredient("ロース", "100g"), Ingredient("ビール", "2杯"))
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 21, 21, 30), // 夕食2回目（締め）
        mealType = MealType.DINNER, // 夜食(MIDNIGHT)ではなくあえて夕食(DINNER)として記録されたケース
        inputMethod = InputMethod.TEXT,
        inputData = "締めのラーメン",
        imagePath = null,
        mealName = "豚骨ラーメン",
        nutrients = Nutrients(600f, 15f, 20f, 70f, 5.0f),
        estimationDetail = listOf(Ingredient("ラーメン", "1杯"), Ingredient("替え玉", "なし"))
    ),

    // --- 2025年12月24日 (クリスマスイブ) ---
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 24, 8, 0),
        mealType = MealType.BREAKFAST,
        inputMethod = InputMethod.TEXT,
        inputData = "ヨーグルト",
        imagePath = null,
        mealName = "フルーツヨーグルト",
        nutrients = Nutrients(120f, 6.0f, 0.5f, 20.0f, 0.1f),
        estimationDetail = listOf(
            Ingredient("プレーンヨーグルト", "150g", null),
            Ingredient("バナナ", "0.5本", null),
            Ingredient("ハチミツ", "小さじ1", null)
        )
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 24, 19, 30),
        mealType = MealType.DINNER,
        inputMethod = InputMethod.IMAGE,
        inputData = null,
        imagePath = "/images/xmas_chicken.jpg",
        mealName = "クリスマスディナー",
        nutrients = Nutrients(1200f, 40.0f, 60.0f, 110.0f, 2.0f),
        estimationDetail = listOf(
            Ingredient("ローストチキン", "1本", "骨付き"),
            Ingredient("ショートケーキ", "1個", "イチゴ"),
            Ingredient("シャンパン", "1杯", null),
            Ingredient("ポテトサラダ", "100g", null)
        )
    ),

    // --- 2025年12月25日 (クリスマス) ---
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 25, 14, 0),
        mealType = MealType.LUNCH,
        inputMethod = InputMethod.IMAGE,
        inputData = null,
        imagePath = "/images/pasta_carbonara.jpg",
        mealName = "カルボナーラ",
        nutrients = Nutrients(780f, 25.0f, 35.0f, 85.0f, 2.2f),
        estimationDetail = listOf(
            Ingredient("パスタ", "100g", "乾麺"),
            Ingredient("ベーコン", "30g", null),
            Ingredient("卵黄", "1個", null),
            Ingredient("粉チーズ", "大さじ2", null)
        )
    ),

    // ---------------------------------------------------------
    // 2025-12-27: 通常日
    // ---------------------------------------------------------
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 27, 7, 15),
        mealType = MealType.BREAKFAST,
        inputMethod = InputMethod.IMAGE,
        inputData = null,
        imagePath = "/images/20251227_breakfast.jpg",
        mealName = "スムージーとバナナ",
        nutrients = Nutrients(200f, 2f, 1f, 45f, 0.0f),
        estimationDetail = listOf(Ingredient("グリーンスムージー", "1杯"), Ingredient("バナナ", "1本"))
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 27, 12, 0),
        mealType = MealType.LUNCH,
        inputMethod = InputMethod.TEXT,
        inputData = "コンビニ弁当",
        imagePath = null,
        mealName = "幕の内弁当",
        nutrients = Nutrients(750f, 25f, 20f, 110f, 4.2f),
        estimationDetail = listOf(Ingredient("ご飯", "普通盛り"), Ingredient("焼き魚", "1切れ"), Ingredient("コロッケ", "1個"))
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 27, 20, 0),
        mealType = MealType.DINNER,
        inputMethod = InputMethod.IMAGE,
        inputData = null,
        imagePath = "/images/20251227_dinner.jpg",
        mealName = "カレーライス",
        nutrients = Nutrients(850f, 15f, 30f, 120f, 3.0f),
        estimationDetail = listOf(Ingredient("カレー", "1皿"), Ingredient("福神漬け", "少々"))
    ),

    // --- 2025年12月28日 (年末) ---
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 28, 12, 30),
        mealType = MealType.LUNCH,
        inputMethod = InputMethod.TEXT,
        inputData = "蕎麦屋",
        imagePath = null,
        mealName = "天ぷらそば",
        nutrients = Nutrients(650f, 18.0f, 22.0f, 90.0f, 4.5f),
        estimationDetail = listOf(
            Ingredient("蕎麦", "1玉", null),
            Ingredient("海老天", "2本", null),
            Ingredient("そばつゆ", "300ml", "関東風")
        )
    ),

    // --- 2025年12月31日 (大晦日) ---
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2025, Month.DECEMBER, 31, 21, 0),
        mealType = MealType.DINNER,
        inputMethod = InputMethod.IMAGE,
        inputData = null,
        imagePath = "/images/toshikoshi_soba.jpg",
        mealName = "年越しそば",
        nutrients = Nutrients(500f, 15.0f, 5.0f, 95.0f, 3.8f),
        estimationDetail = listOf(
            Ingredient("蕎麦", "1玉", "十割"),
            Ingredient("ネギ", "少々", null),
            Ingredient("かまぼこ", "2切れ", "紅白")
        )
    ),

    // --- 2026年1月1日 (元旦) ---
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2026, Month.JANUARY, 1, 9, 0),
        mealType = MealType.BREAKFAST,
        inputMethod = InputMethod.IMAGE,
        inputData = null,
        imagePath = "/images/osechi.jpg",
        mealName = "おせち料理とお雑煮",
        nutrients = Nutrients(900f, 35.0f, 20.0f, 140.0f, 5.0f),
        estimationDetail = listOf(
            Ingredient("角餅", "2個", "焼き"),
            Ingredient("雑煮汁", "200ml", "すまし汁"),
            Ingredient("数の子", "2切れ", null),
            Ingredient("黒豆", "20g", null),
            Ingredient("伊達巻", "2切れ", null)
        )
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2026, Month.JANUARY, 1, 19, 0),
        mealType = MealType.DINNER,
        inputMethod = InputMethod.IMAGE,
        inputData = null,
        imagePath = "/images/sushi_party.jpg",
        mealName = "特上寿司桶",
        nutrients = Nutrients(800f, 40.0f, 15.0f, 110.0f, 3.0f),
        estimationDetail = listOf(
            Ingredient("マグロ赤身", "2貫", null),
            Ingredient("イカ", "2貫", null),
            Ingredient("サーモン", "2貫", null),
            Ingredient("いくら軍艦", "2貫", null),
            Ingredient("玉子", "2貫", null)
        )
    ),

    // --- 2026年1月2日 (正月) ---
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2026, Month.JANUARY, 2, 10, 30),
        mealType = MealType.BREAKFAST,
        inputMethod = InputMethod.TEXT,
        inputData = "磯辺焼き",
        imagePath = null,
        mealName = "磯辺焼き",
        nutrients = Nutrients(350f, 6.0f, 1.0f, 75.0f, 1.2f),
        estimationDetail = listOf(
            Ingredient("切り餅", "2個", null),
            Ingredient("焼き海苔", "2枚", null),
            Ingredient("醤油", "大さじ1", null)
        )
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2026, Month.JANUARY, 2, 18, 0),
        mealType = MealType.DINNER,
        inputMethod = InputMethod.IMAGE,
        inputData = null,
        imagePath = "/images/sukiyaki.jpg",
        mealName = "すき焼き",
        nutrients = Nutrients(950f, 35.0f, 60.0f, 50.0f, 4.0f),
        estimationDetail = listOf(
            Ingredient("牛肩ロース", "150g", "和牛"),
            Ingredient("白菜", "100g", null),
            Ingredient("焼き豆腐", "0.5丁", null),
            Ingredient("生卵", "1個", "つけダレ用")
        )
    ),

    // --- 2026年1月5日 (仕事始め) ---
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2026, Month.JANUARY, 5, 12, 5),
        mealType = MealType.LUNCH,
        inputMethod = InputMethod.TEXT,
        inputData = "コンビニ弁当",
        imagePath = null,
        mealName = "幕の内弁当",
        nutrients = Nutrients(700f, 20.0f, 25.0f, 95.0f, 3.2f),
        estimationDetail = listOf(
            Ingredient("白米", "200g", "ごま塩"),
            Ingredient("焼き鮭", "1切れ", null),
            Ingredient("玉子焼き", "1切れ", null),
            Ingredient("コロッケ", "0.5個", null)
        )
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2026, Month.JANUARY, 5, 20, 0),
        mealType = MealType.DINNER,
        inputMethod = InputMethod.TEXT,
        inputData = "自炊：野菜炒め",
        imagePath = null,
        mealName = "肉野菜炒め定食",
        nutrients = Nutrients(550f, 25.0f, 30.0f, 15.0f, 1.8f),
        estimationDetail = listOf(
            Ingredient("豚こま切れ肉", "100g", null),
            Ingredient("もやし", "0.5袋", null),
            Ingredient("ニラ", "0.5束", null),
            Ingredient("中華だし", "小さじ1", null)
        )
    ),

    // --- 2026年1月10日 (週末) ---
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2026, Month.JANUARY, 10, 8, 30),
        mealType = MealType.BREAKFAST,
        inputMethod = InputMethod.IMAGE,
        inputData = null,
        imagePath = "/images/pancakes.jpg",
        mealName = "パンケーキ",
        nutrients = Nutrients(600f, 10.0f, 25.0f, 80.0f, 0.9f),
        estimationDetail = listOf(
            Ingredient("パンケーキ", "2枚", null),
            Ingredient("メープルシロップ", "大さじ2", null),
            Ingredient("バター", "10g", null)
        )
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2026, Month.JANUARY, 10, 19, 0),
        mealType = MealType.DINNER,
        inputMethod = InputMethod.TEXT,
        inputData = "カレーライス",
        imagePath = null,
        mealName = "チキンカレー",
        nutrients = Nutrients(880f, 25.0f, 30.0f, 120.0f, 2.8f),
        estimationDetail = listOf(
            Ingredient("白米", "250g", null),
            Ingredient("カレールー", "150g", "鶏肉入り"),
            Ingredient("福神漬け", "少々", null)
        )
    ),

    // --- 1月中旬以降 ---
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2026, Month.JANUARY, 15, 7, 15),
        mealType = MealType.BREAKFAST,
        inputMethod = InputMethod.TEXT,
        inputData = "シリアル",
        imagePath = null,
        mealName = "フルーツグラノーラ",
        nutrients = Nutrients(220f, 4.0f, 8.0f, 35.0f, 0.3f),
        estimationDetail = listOf(
            Ingredient("グラノーラ", "50g", null),
            Ingredient("牛乳", "150ml", null)
        )
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2026, Month.JANUARY, 16, 12, 10),
        mealType = MealType.LUNCH,
        inputMethod = InputMethod.TEXT,
        inputData = "サンドイッチ",
        imagePath = null,
        mealName = "ミックスサンド",
        nutrients = Nutrients(400f, 12.0f, 18.0f, 45.0f, 1.6f),
        estimationDetail = listOf(
            Ingredient("食パン", "2枚", "耳なし"),
            Ingredient("ハム", "1枚", null),
            Ingredient("レタス", "1枚", null),
            Ingredient("たまごサラダ", "30g", null)
        )
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2026, Month.JANUARY, 18, 18, 45),
        mealType = MealType.DINNER,
        inputMethod = InputMethod.IMAGE,
        inputData = null,
        imagePath = "/images/nabe.jpg",
        mealName = "キムチ鍋",
        nutrients = Nutrients(550f, 28.0f, 20.0f, 40.0f, 4.5f),
        estimationDetail = listOf(
            Ingredient("豚バラ肉", "80g", null),
            Ingredient("白菜", "150g", null),
            Ingredient("豆腐", "0.5丁", null),
            Ingredient("キムチ鍋スープ", "300ml", null)
        )
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2026, Month.JANUARY, 20, 12, 0),
        mealType = MealType.LUNCH,
        inputMethod = InputMethod.TEXT,
        inputData = "うどん",
        imagePath = null,
        mealName = "きつねうどん",
        nutrients = Nutrients(480f, 12.0f, 5.0f, 85.0f, 3.8f),
        estimationDetail = listOf(
            Ingredient("うどん玉", "1玉", null),
            Ingredient("油揚げ", "1枚", "甘辛煮"),
            Ingredient("うどんつゆ", "300ml", "関西風")
        )
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2026, Month.JANUARY, 25, 15, 0),
        mealType = MealType.SNACK,
        inputMethod = InputMethod.TEXT,
        inputData = "コーヒーブレイク",
        imagePath = null,
        mealName = "カフェラテ",
        nutrients = Nutrients(120f, 6.0f, 7.0f, 10.0f, 0.2f),
        estimationDetail = listOf(
            Ingredient("エスプレッソ", "30ml", null),
            Ingredient("牛乳", "150ml", "スチーム")
        )
    ),
    MealRecord(
        id = UUID.randomUUID().toString(),
        userId = currentUserId,
        date = LocalDateTime.of(2026, Month.JANUARY, 28, 19, 15),
        mealType = MealType.DINNER,
        inputMethod = InputMethod.IMAGE,
        inputData = null,
        imagePath = "/images/hamburg.jpg",
        mealName = "ハンバーグ定食",
        nutrients = Nutrients(900f, 30.0f, 50.0f, 80.0f, 2.5f),
        estimationDetail = listOf(
            Ingredient("合い挽き肉ハンバーグ", "150g", "デミグラスソース"),
            Ingredient("ポテトフライ", "50g", "付け合わせ"),
            Ingredient("白米", "200g", null)
        )
    )
)