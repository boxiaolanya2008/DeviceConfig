package com.android.deviceconfig.manager

import com.android.deviceconfig.model.ConfigItem
import com.android.deviceconfig.model.NamespaceConfig

class DeviceConfigManager {

    companion object {
        private const val TAG = "DeviceConfigManager"

        // 常见的 DeviceConfig 命名空间
        val COMMON_NAMESPACES = listOf(
            "activity_manager",
            "alarm_manager",
            "app_compat",
            "biometric",
            "camera",
            "content_capture",
            "content_capture_hints",
            "dalvik",
            "device_policy",
            "display_manager",
            "game_overlay",
            "input",
            "input_manager",
            "intelligence",
            "jobscheduler",
            "media",
            "media_adaptive_audio",
            "media_cronet",
            "ndk",
            "netd",
            "network_stack",
            "notifications",
            "privacy",
            "rollout",
            "runtime",
            "runtime_native",
            "safety_center",
            "sdk_sandbox",
            "statsd",
            "system_health",
            "systemui",
            "telephony",
            "translation",
            "window_manager"
        )

        // 使用反射获取 DeviceConfig 类
        private fun getDeviceConfigClass(): Class<*>? {
            return try {
                Class.forName("android.provider.DeviceConfig")
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        // 使用反射调用 DeviceConfig.getString
        fun getString(namespace: String, key: String, defaultValue: String?): String? {
            return try {
                val deviceConfigClass = getDeviceConfigClass()
                val method = deviceConfigClass?.getMethod(
                    "getString",
                    String::class.java,
                    String::class.java,
                    String::class.java
                )
                method?.invoke(null, namespace, key, defaultValue) as? String
            } catch (e: Exception) {
                e.printStackTrace()
                defaultValue
            }
        }
    }

    /**
     * 获取指定命名空间的所有配置项
     */
    fun getNamespaceConfig(namespace: String): NamespaceConfig {
        val items = mutableListOf<ConfigItem>()

        try {
            // 使用反射获取所有配置项
            val configNames = getConfigNames(namespace)

            for (key in configNames) {
                val value = Companion.getString(namespace, key, null)
                items.add(ConfigItem(key, value, namespace))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return NamespaceConfig(namespace, items)
    }

    /**
     * 获取多个命名空间的所有配置
     */
    fun getAllNamespacesConfig(namespaces: List<String> = COMMON_NAMESPACES): List<NamespaceConfig> {
        return namespaces.map { namespace ->
            getNamespaceConfig(namespace)
        }
    }

    /**
     * 尝试获取配置项的所有键名
     * 注意：DeviceConfig API 没有直接获取所有键的方法，
     * 这里使用反射来尝试读取
     */
    private fun getConfigNames(namespace: String): Set<String> {
        val keys = mutableSetOf<String>()

        try {
            // 使用反射调用 DeviceConfig 的内部方法
            val deviceConfigClass = Class.forName("android.provider.DeviceConfig")
            val getFlagsMethod = deviceConfigClass.getDeclaredMethod(
                "getFlags",
                String::class.java
            )
            getFlagsMethod.isAccessible = true

            @Suppress("UNCHECKED_CAST")
            val flags = getFlagsMethod.invoke(null, namespace) as? Map<String, String>
            flags?.keys?.let { keys.addAll(it) }

        } catch (e: Exception) {
            // 如果反射失败，返回空集合
            e.printStackTrace()
        }

        return keys
    }

    /**
     * 获取指定命名空间的指定配置项
     */
    fun getProperty(namespace: String, key: String): String? {
        return try {
            Companion.getString(namespace, key, null)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 检查命名空间是否存在
     */
    fun isNamespaceExists(namespace: String): Boolean {
        return try {
            Companion.getString(namespace, "test", null)
            true
        } catch (e: Exception) {
            false
        }
    }
}
