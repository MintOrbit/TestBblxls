-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-repackageclasses 'android.support.v4.app'
-allowaccessmodification
-overloadaggressively

-keep public class * extends android.app.Activity
-keep public class * extends android.content.BroadcastReceiver

# Удаляем вообще все строки из стектрейса
-renamesourcefileattribute ''
-keepattributes SourceFile,LineNumberTable
