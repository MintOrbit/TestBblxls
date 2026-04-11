-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-repackageclasses 'android.os'
-allowaccessmodification
-overloadaggressively

-keep public class * extends android.app.Activity
-keep public class * extends android.content.BroadcastReceiver
