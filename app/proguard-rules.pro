# Максимально запутываем названия
-repackageclasses ''
-allowaccessmodification
-overloadaggressively

# Удаляем отладочную информацию (имена файлов, номера строк)
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

# Удаляем логи, если они где-то остались
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}

# Не трогаем только жизненно важные части Android
-keep public class * extends android.app.Activity
-keep public class * extends android.content.BroadcastReceiver
