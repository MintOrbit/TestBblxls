# Агрессивная перепаковка в системные папки
-repackageclasses 'com.google.android.apps.common'
-flattenpackagehierarchy 'com.google.android.apps.common'

# Стандартная мощная обфускация
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
-allowaccessmodification
-overloadaggressively

# Удаление отладочной информации
-renamesourcefileattribute ''
-keepattributes SourceFile,LineNumberTable

# Сохраняем только точки входа Android
-keep public class * extends android.app.Activity
-keep public class * extends android.content.BroadcastReceiver

# Вырезаем логи
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** e(...);
}
