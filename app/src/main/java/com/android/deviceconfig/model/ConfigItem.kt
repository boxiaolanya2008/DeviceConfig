package com.android.deviceconfig.model

data class ConfigItem(
    val key: String,
    val value: String?,
    val namespace: String
)

data class NamespaceConfig(
    val namespace: String,
    val items: List<ConfigItem>
)
