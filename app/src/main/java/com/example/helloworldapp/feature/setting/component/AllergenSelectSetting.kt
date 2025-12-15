
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.helloworldapp.feature.setting.component.Allergen
import com.example.helloworldapp.feature.setting.component.SettingItem

@Composable
fun AllergenSelectSection(
    allAllergens: List<Allergen>,
    selectedAllergens: Set<String>,
    onToggle: (String) -> Unit
) {
    // データを義務と推奨に分割する（Kotlinの便利な関数）
    val (mandatory, recommended) = allAllergens.partition { it.isMandatory }

    SettingItem(
        isRow = false,
        content = {
            Column {
                // 1. 特定原材料（義務 8品目）は常に表示
                Text(
                    text = "特定原材料（表示義務 8品目）",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(bottom = 8.dp, top = 16.dp)
                )
                AllergensSelectByChip(
                    allergens = mandatory,
                    selectedAllergens = selectedAllergens,
                    onToggle = onToggle
                )

                // 2. 準ずるもの（推奨 20品目）はアコーディオンに収納
                // スペーサーを入れて少し間隔を空ける
                Spacer(modifier = Modifier.height(16.dp))

                AccordionSection(
                    title = "特定原材料に準ずるもの（20品目）を表示",
                    defaultExpanded = false // 最初は閉じておく
                ) {
                    // ここに任意のコンポーネントを入れられる
                    AllergensSelectByChip(
                        allergens = recommended,
                        selectedAllergens = selectedAllergens,
                        onToggle = onToggle
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                AccordionSection(
                    title = "その他",
                    defaultExpanded = false
                ) {
                    ChipTextField(
                        chips = listOf(""),
                        onChipsChange = {}
                    )
                }
            }
        }
    )

}