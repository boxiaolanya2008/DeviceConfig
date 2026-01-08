package com.android.deviceconfig.ui.screen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.android.deviceconfig.manager.DeviceConfigManager
import com.android.deviceconfig.model.NamespaceConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceConfigScreen() {
    val manager = remember { DeviceConfigManager() }

    var selectedNamespace by remember { mutableStateOf<String?>(null) }
    var namespacesConfig by remember { mutableStateOf<List<NamespaceConfig>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DeviceConfig Reader") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // 搜索框
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("搜索配置项...") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 加载按钮
            Button(
                onClick = {
                    isLoading = true
                    // 在协程中加载配置
                    // 这里只是示例，实际加载在下面处理
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading
            ) {
                Text(if (isLoading) "加载中..." else "加载所有 DeviceConfig")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 显示内容
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (namespacesConfig.isNotEmpty()) {
                // 显示命名空间列表或配置详情
                if (selectedNamespace == null) {
                    // 显示所有命名空间
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(namespacesConfig) { namespace ->
                            NamespaceCard(
                                namespace = namespace,
                                searchQuery = searchQuery,
                                onClick = { selectedNamespace = namespace.namespace }
                            )
                        }
                    }
                } else {
                    // 显示选中命名空间的详细配置
                    val namespace = namespacesConfig.firstOrNull {
                        it.namespace == selectedNamespace
                    }
                    namespace?.let {
                        Column {
                            Button(
                                onClick = { selectedNamespace = null },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("返回列表")
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "命名空间: ${it.namespace}",
                                style = MaterialTheme.typography.titleLarge
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "配置项数量: ${it.items.size}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.secondary
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            LazyColumn(
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                val filteredItems = if (searchQuery.isNotBlank()) {
                                    it.items.filter { item ->
                                        item.key.contains(searchQuery, ignoreCase = true) ||
                                                (item.value?.contains(
                                                    searchQuery,
                                                    ignoreCase = true
                                                ) ?: false)
                                    }
                                } else {
                                    it.items
                                }

                                items(filteredItems) { item ->
                                    ConfigItemCard(item)
                                }
                            }
                        }
                    }
                }
            } else {
                // 初始提示
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "点击上方按钮加载 DeviceConfig\n\n无需任何权限",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }

    // 使用 LaunchedEffect 在后台加载配置
    LaunchedEffect(isLoading) {
        if (isLoading && namespacesConfig.isEmpty()) {
            val config = withContext(Dispatchers.IO) {
                manager.getAllNamespacesConfig()
            }
            namespacesConfig = config
            isLoading = false
        }
    }
}

@Composable
fun NamespaceCard(
    namespace: NamespaceConfig,
    searchQuery: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = namespace.namespace,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${namespace.items.size} 个配置项",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // 显示前几个配置项
            if (searchQuery.isNotBlank()) {
                val previewItems = namespace.items.take(3)
                previewItems.forEach { item ->
                    if (item.key.contains(searchQuery, ignoreCase = true)) {
                        Text(
                            text = "  • ${item.key}",
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ConfigItemCard(item: com.android.deviceconfig.model.ConfigItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = item.key,
                style = MaterialTheme.typography.titleSmall,
                fontFamily = FontFamily.Monospace,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                Text(
                    text = item.value ?: "null",
                    style = MaterialTheme.typography.bodySmall,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
