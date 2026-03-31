-dontshrink
 -dontoptimize
 -dontobfuscate
 -keep class com.mindumine.** { *; }
 -keep class androidx.** { *; }
 -keep class com.google.** { *; }
 -keep class android.app.** { *; }
 -keep class android.widget.** { *; }
 -keep class android.view.** { *; }
 -keep class android.webkit.** { *; }
 -keep class android.os.** { *; }
 -keep class android.graphics.** { *; }
 # Mantener los constructores de vistas personalizadas
 -keep class * extends android.view.View {
     <init>(android.content.Context);
     <init>(android.content.Context, android.util.AttributeSet);
     <init>(android.content.Context, android.util.AttributeSet, int);
     <init>(android.content.Context, android.util.AttributeSet, int, int);
 }
 # Mantener los callbacks de JNI/reflection (si usas código nativo)
 -keepclasseswithmembers class * {
     native <methods>;
 }
 -keepclassmembers class * {
     void <init>(android.content.Context, android.util.AttributeSet);
     void <init>(android.content.Context, android.util.AttributeSet, int);
 }
 -keep enum ** { *; }
 # Mantener clases que son usadas por reflexión (p.ej., para serialización/deserialización)
 -keep class com.mindumine.items.Item { *; }
 -keep class com.mindumine.items.ItemStack { *; }
 -keep class com.mindumine.player.Player { *; }
 -keep class com.mindumine.player.PlayerInventory { *; }
