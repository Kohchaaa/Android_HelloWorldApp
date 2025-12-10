
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.helloworldapp.ui.userSettingInput.Allergen


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AllergensSelectByChip(
    allergens: List<Allergen>,       // マスタデータ（全リスト）
    selectedAllergens: Set<String>,        // 選択されたID（Slug）のセット
    onToggle: (String) -> Unit,      // タップ時のコールバック
    modifier: Modifier = Modifier
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp), // 行間のスペースも追加
        modifier = modifier.fillMaxWidth()
    ) {
        allergens.forEach { allergen ->
            val isSelected = selectedAllergens.contains(allergen.value)

            FilterChip(
                selected = isSelected,
                onClick = { onToggle(allergen.value) },
                label = {
                    Text(
                        text = allergen.name,
                        // 義務（Mandatory）品目は太字にして強調する
                        fontWeight = if (allergen.isMandatory) FontWeight.Bold else FontWeight.Normal,
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                leadingIcon = if (isSelected) {
                    {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Unselected",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                },
                // 選択時の色味などをカスタマイズしたい場合はここでcolorsを指定
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    }
}