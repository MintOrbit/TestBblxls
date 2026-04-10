-repackageclasses ''
-allowaccessmodification
-overloadaggressively
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}
-keep public class * extends android.app.Activity
-keep public class * extends android.content.BroadcastReceiver
