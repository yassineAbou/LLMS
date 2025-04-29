-keep class kotlinx.coroutines.swing.** { *; }

# -------------------------- Sketch Privider ---------------------------- #
-keep class * implements com.github.panpf.sketch.util.DecoderProvider { *; }
-keep class * implements com.github.panpf.sketch.util.FetcherProvider { *; }


-dontwarn okhttp3.**
-dontwarn io.ktor.**
-dontwarn org.bouncycastle.**
-dontwarn org.conscrypt.**
-dontwarn org.openjsse.**
-dontwarn android.**

#OkHttp
-keepattributes Signature
-keepattributes Annotation
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**

# Ktor
-keep class io.ktor.** { *; }
-keepclassmembers class io.ktor.** { volatile <fields>; }
-keep class io.ktor.client.engine.cio.** { *; }
-keep class kotlinx.coroutines.** { *; }
-dontwarn kotlinx.atomicfu.**
-dontwarn io.netty.**
-dontwarn com.typesafe.**
-dontwarn org.slf4j.**

# Obfuscation breaks coroutines/ktor for some reason
-dontobfuscate

# Keep `Companion` object fields of serializable classes.
-if @kotlinx.serialization.Serializable class **
-keepclassmembers class <1> {
    static <1>$* Companion;
}

# Keep names for named companion object from obfuscation
-keepnames @kotlinx.serialization.internal.NamedCompanion class *
-if @kotlinx.serialization.internal.NamedCompanion class *
-keepclassmembernames class * {
    static <1> *;
}

# Keep `serializer()` on companion objects of serializable classes.
-if @kotlinx.serialization.Serializable class ** {
    static **$* *;
}
-keepclassmembers class <2>$<3> {
    kotlinx.serialization.KSerializer serializer(...);
}

# Keep `INSTANCE.serializer()` of serializable objects.
-if @kotlinx.serialization.Serializable class ** {
    public static ** INSTANCE;
}
-keepclassmembers class <1> {
    public static <1> INSTANCE;
    kotlinx.serialization.KSerializer serializer(...);
}

# Retain annotations used for polymorphic serialization
-keepattributes RuntimeVisibleAnnotations,AnnotationDefault

# Suppress notes about potential mistakes in serialization configuration
-dontnote kotlinx.serialization.**

# Disable warnings for Android-specific missing ClassValue (used in JVM)
-dontwarn kotlinx.serialization.internal.ClassValueReferences

# Prevent optimization of 'descriptor' field in $$serializer classes
# Fixes incorrect bytecode generation in some ProGuard versions
-keepclassmembers public class **$$serializer {
    private ** descriptor;
}

# Disable problematic optimization: method/specialization
-optimizations !method/specialization/**

# Keep all @Serializable annotated classes intact (fallback safety)
-keep @kotlinx.serialization.Serializable class * {*;}







