# DeviceConfig

<div align="center">

  <img src="fastlane/metadata/android/icon.png" alt="DeviceConfig Logo" width="120" height="120">

  **Android系统配置查看器**

  [![API](https://img.shields.io/badge/API-31%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=31)
  [![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
  [![Kotlin](https://img.shields.io/badge/Kotlin-2.1.0-purple.svg)](https://kotlinlang.org)

  无需任何权限，查看Android底层系统配置

</div>

---

## 📖 项目简介

**DeviceConfig** 是一款强大的Android系统配置查看工具，允许用户直接访问和浏览Android系统底层的 `DeviceConfig` 配置参数。这些配置通常只对系统开发者或通过代码访问，现在您可以通过直观的界面轻松查看。

### ✨ 核心特性

- 🔍 **无需任何权限** - 使用标准API读取系统配置
- 📱 **支持多个命名空间** - 覆盖Activity Manager、Camera、Notification等30+系统命名空间
- 🔎 **快速搜索** - 支持按键名或值搜索配置项
- 🎨 **Material Design 3** - 现代化UI设计，支持深色/浅色主题
- 📊 **实时显示** - 直接从系统读取最新配置值
- 🚀 **Shizuku支持** - 通过Shizuku获取更高级的配置访问能力

---

## 🎯 主要功能

### 1. 浏览系统配置
查看Android系统各个命名空间的配置参数，包括但不限于：

- **Activity Manager** (`activity_manager`) - 活动管理器配置
- **Alarm Manager** (`alarm_manager`) - 闹钟管理器配置
- **Camera** (`camera`) - 相机服务配置
- **Notifications** (`notifications`) - 通知系统配置
- **Privacy** (`privacy`) - 隐私设置配置
- **Telephony** (`telephony`) - 电话服务配置
- **Window Manager** (`window_manager`) - 窗口管理器配置
- **Biometric** (`biometric`) - 生物识别配置
- 以及更多...

### 2. 搜索功能
快速定位您需要的配置项，支持：
- 按键名搜索
- 按配置值搜索
- 实时过滤结果

### 3. Shizuku增强访问
通过集成Shizuku框架，应用可以获得更强大的配置访问能力：
- 📋 读取受保护的配置项
- 🔧 访问需要更高权限的命名空间
- ⚡ 更快速、更稳定的配置读取

---

## 📸 应用截图

<!-- 待添加截图 -->
<div align="center">
  <img src="fastlane/metadata/android/phoneScreenshots/1-main.png" alt="主界面" width="300">
  <img src="fastlane/metadata/android/phoneScreenshots/2-search.png" alt="搜索功能" width="300">
  <img src="fastlane/metadata/android/phoneScreenshots/3-detail.png" alt="配置详情" width="300">
</div>

---

## 🛠️ 技术栈

- **语言**: Kotlin 100%
- **UI框架**: Jetpack Compose + Material Design 3
- **架构**: MVVM + Repository Pattern
- **构建工具**: Gradle (Kotlin DSL)
- **最低API级别**: 31 (Android 12)
- **目标API级别**: 36 (Android 14)

---

## 📥 下载安装

### 方式1: 从GitHub Releases下载

访问 [Releases页面](../../releases) 下载最新的APK文件。

### 方式2: 从源码构建

```bash
# 克隆仓库
git clone https://github.com/yourusername/DeviceConfig.git
cd DeviceConfig

# 构建Debug版本
./gradlew assembleDebug

# 构建Release版本
./gradlew assembleRelease

# APK输出位置
# Debug: app/build/outputs/apk/debug/app-debug.apk
# Release: app/build/outputs/apk/release/app-release.apk
```

---

## 🔐 Shizuku 权限使用指南

### 什么是Shizuku？

[Shizuku](https://github.com/RikkaApps/Shizuku) 是一个强大的Android框架，允许普通应用以更高的权限级别运行，无需root即可执行系统级操作。

### 为什么使用Shizuku？

普通的 `DeviceConfig` API访问存在以下限制：
- ❌ 无法读取某些受保护的配置项
- ❌ 部分命名空间访问被限制
- ❌ 无法修改系统配置

通过Shizuku，您可以：
- ✅ 读取所有可用的配置项（包括受保护的）
- ✅ 访问更多系统命名空间
- ✅ 获得更快的读取速度
- ✅ 支持配置修改（未来功能）

### Shizuku安装步骤

#### 1. 安装Shizuku应用

从以下渠道下载并安装Shizuku：

- [Google Play](https://play.google.com/store/apps/details?id=moe.shizuku.privileged.api)
- [GitHub Releases](https://github.com/RikkaApps/Shizuku/releases)
- [F-Droid](https://f-droid.org/packages/moe.shizuku.privileged.api/)

#### 2. 启动Shizuku服务

**对于Android 11（API 30）及以上版本：**

1. 进入手机 **设置** → **无线调试**（开发者选项）
2. 启用 **无线调试**
3. 点击 **无线调试** 中的配对信息
4. 在Shizuku应用中选择 **通过无线调试启动**
5. 输入配对码完成连接

**对于Android 10（API 29）及以下版本：**

1. 连接ADB：`adb shell sh /sdcard/Android/data/moe.shizuku.privileged.api/start.sh`
2. 或在Shizuku应用中查看更多启动方式

#### 3. 授权DeviceConfig访问Shizuku

1. 打开 **DeviceConfig** 应用
2. 应用会自动检测Shizuku服务状态
3. 点击 **请求Shizuku权限**
4. 在弹出的Shizuku授权界面中点击 **授权**
5. 返回应用，享受增强的配置访问能力

### Shizuku权限验证

应用会显示当前的Shizuku连接状态：

| 状态 | 说明 |
|------|------|
| 🔴 未连接 | Shizuku服务未运行或未安装 |
| 🟡 未授权 | Shizuku服务运行中，但未授权给本应用 |
| 🟢 已授权 | 已获得Shizuku权限，可访问所有配置 |

### 注意事项

- ⚠️ Shizuku需要每次重启后重新启动服务
- ⚠️ 使用Shizuku不会使应用获得root权限
- ✅ 不使用Shizuku时，应用仍可正常工作（功能受限）
- 🔒 应用不会收集或上传任何配置数据

---

## 📖 使用说明

### 基础使用

1. **启动应用**
   - 安装后打开应用，自动加载所有可用的命名空间

2. **浏览配置**
   - 滚动查看各个命名空间卡片
   - 点击卡片展开该命名空间下的所有配置项

3. **搜索配置**
   - 点击顶部搜索图标
   - 输入键名或值进行搜索
   - 实时显示匹配结果

4. **查看详情**
   - 点击任意配置项查看完整信息
   - 长按配置项可复制键名或值

### Shizuku增强模式

1. **启用Shizuku**
   - 确保Shizuku服务运行中
   - 在应用内点击 **请求Shizuku权限**
   - 授权后即可查看更多配置项

2. **识别增强内容**
   - 标有 🔒 图标的配置项需要Shizuku权限
   - 未授权时这些项会显示为 "受限访问"

---

## 🏗️ 项目架构

```
app/
├── src/main/java/com/android/deviceconfig/
│   ├── MainActivity.kt                    # 应用入口
│   ├── manager/
│   │   └── DeviceConfigManager.kt        # DeviceConfig管理器（反射访问）
│   ├── model/
│   │   ├── ConfigItem.kt                 # 配置项数据模型
│   │   └── NamespaceConfig.kt            # 命名空间配置模型
│   └── ui/
│       ├── screen/
│       │   └── DeviceConfigScreen.kt     # 主UI界面
│       └── theme/
│           ├── Color.kt                  # 颜色定义
│           ├── Theme.kt                  # 主题配置
│           └── Type.kt                   # 字体排版
├── build.gradle.kts                       # 应用级构建配置
└── AndroidManifest.xml                    # 应用清单
```

### 架构模式

本项目采用 **MVVM (Model-View-ViewModel)** 架构模式：

- **Model层**: `ConfigItem`, `NamespaceConfig` - 数据模型
- **View层**: `DeviceConfigScreen` - Jetpack Compose UI
- **ViewModel/Repository层**: `DeviceConfigManager` - 业务逻辑

---

## 🔧 开发说明

### 环境要求

- **Android Studio**: Hedgehog (2023.1.1) 或更高版本
- **JDK**: 17 或 21 (推荐使用Android Studio内置JDK)
- **Gradle**: 8.13.2
- **Kotlin**: 2.1.0

### 构建配置

项目使用Android Studio的JDK（Java 21）进行构建，以避免Java 25兼容性问题：

```properties
# gradle.properties
org.gradle.java.home=C:\\Program Files\\Android\\Android Studio\\jbr
```

### 关键实现

#### DeviceConfig反射访问

由于 `android.provider.DeviceConfig` 在某些编译环境下无法直接导入，本项目使用反射方式访问：

```kotlin
private fun getDeviceConfigClass(): Class<*>? {
    return try {
        Class.forName("android.provider.DeviceConfig")
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

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
```

---

## 🤝 贡献指南

欢迎贡献代码、报告问题或提出新功能建议！

1. Fork 本仓库
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 提交Pull Request

### 代码规范

- 遵循 [Kotlin官方编码规范](https://kotlinlang.org/docs/coding-conventions.html)
- 使用ktlint进行代码格式化
- 为新功能添加单元测试
- 更新相关文档

---

## 📋 待办事项

- [ ] 添加配置项导出功能（JSON/TXT）
- [ ] 支持配置项收藏
- [ ] 添加配置项说明文档
- [ ] 实现Shizuku配置修改功能
- [ ] 添加暗黑模式自动切换
- [ ] 支持配置历史记录查看
- [ ] 添加配置项对比功能

---

## 📄 开源协议

```
MIT License

Copyright (c) 2025 DeviceConfig Contributors

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

---

## 📞 联系方式

- **Issues**: [GitHub Issues](../../issues)
- **Discussions**: [GitHub Discussions](../../discussions)
- **Email**: your-email@example.com

---

## 🙏 致谢

- [Android Open Source Project](https://source.android.com/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Shizuku](https://github.com/RikkaApps/Shizuku) - 提供强大的权限管理框架
- [Material Design 3](https://m3.material.io/)

---

<div align="center">

  **如果这个项目对您有帮助，请给个 ⭐️ Star 支持！**

  [⬆ 返回顶部](#deviceconfig)

</div>
