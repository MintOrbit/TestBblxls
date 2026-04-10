-repackageclasses ''
-allowaccessmodification
-overloadaggressively
-repackageclasses 'com.android.internal.util'
-flattenpackagehierarchy 'com.android.internal.util'
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

-renamesourcefileattribute ''
-keepattributes *Annotation*,Signature,InnerClasses
-keepattributes SourceFile,LineNumberTable

-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** e(...);
}

-keep public class * extends android.app.Activity
-keep public class * extends android.content.BroadcastReceiver
