
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import java.util.UUID

// 1. 表示・編集用のデータクラス（IDは不変！）
// これがあるおかげで、keyやvalueが変わってもComposeは「同じ行」だと認識できます
data class EditableItem(
    val id: String = UUID.randomUUID().toString(),
    var key: String,
    var value: String,
    val focusRequester: FocusRequester = FocusRequester(),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapDataInput(
    mapData: Map<String, String>,
    onDataChange: (Map<String, String>) -> Unit
) {
    // 2. Mapを「編集可能なリスト」に変換してStateとして持つ
    // remember { ... } の中に書くことで、親から再描画が走っても
    // このリスト（とフォーカス状態）は破棄されずに生き残ります。
    val editableList = remember {
        mutableStateListOf<EditableItem>().apply {
            mapData.forEach { (k, v) ->
                add(EditableItem(key = k, value = v))
            }
            // もし空なら初期行を追加しておく等の処理もここで可能
        }
    }

    val focusManager = LocalFocusManager.current

    // リストの内容が変わるたびにMapに変換して親へ通知する関数
    fun syncToParent() {
        // List -> Map 変換
        // ※同じキーがある場合は後勝ちになります（Mapの仕様）
        val newMap = editableList.associate { it.key to it.value }
        onDataChange(newMap)
    }

    Column(modifier = Modifier.fillMaxWidth()) {

        // 3. UIはMapではなく、この「List」に基づいて描画する
        editableList.forEachIndexed { index, item ->
            // 【最重要】item.key ではなく item.id をkeyに指定する！
            // これにより、テキスト入力でkeyが変わってもリコンポーズ時に同一コンポーネントとみなされる
            key(item.id) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // --- Key（項目名）入力 ---
                    OutlinedTextField(
                        value = item.key,
                        onValueChange = { newKey ->
                            // 描画用のデータ変更
                            editableList[index] = item.copy(key = newKey)
                            // 3. 親のMapへ同期
                            syncToParent()
                        },
                        label = { Text("項目名") },
                        modifier = Modifier
                            .weight(1f)
                            .focusRequester(item.focusRequester),
                        singleLine = true,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // --- Value（内容）入力 ---
                    OutlinedTextField(
                        value = item.value,
                        onValueChange = { newValue ->
                            editableList[index] = item.copy(value = newValue)
                            syncToParent()
                        },
                        label = { Text("内容") },
                        modifier = Modifier
                            .weight(1f),
                        singleLine = true,
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                    )

                    // --- 削除 ---
                    IconButton(onClick = {
                        editableList.removeAt(index)
                        syncToParent()
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "削除")
                    }
                }
            }
        }

        // --- 追加 ---
        OutlinedButton(
            onClick = {
                // 1. リストの最後のアイテムのキーが空文字でないかチェックする
                //    (リストが空の場合も条件を満たす)
                val canAdd = editableList.lastOrNull()?.key?.isNotEmpty() ?: true

                if (canAdd) {
                    // 2. 最後の行が入力済みの場合にのみ、新しい空の行を追加する
                    editableList.add(EditableItem(key = "", value = ""))
                    syncToParent()
                } else {
                    editableList.lastOrNull()?.focusRequester?.requestFocus()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Add, contentDescription = "追加")
            Text(text = "項目を追加")
        }
    }
}