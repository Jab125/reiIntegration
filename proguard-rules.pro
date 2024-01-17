-libraryjars <java.home>/jmods/java.base.jmod(!**.jar;!module-info.class)
#-keepattributes *Annotation*, Exceptions, Signature, Deprecated, SourceFile, SourceDir, LineNumberTable, LocalVariableTable, LocalVariableTypeTable, Synthetic, EnclosingMethod, RuntimeVisibleAnnotations, RuntimeInvisibleAnnotations, RuntimeVisibleParameterAnnotations, RuntimeInvisibleParameterAnnotations, AnnotationDefault, InnerClasses
-keepattributes InnerClasses, *Annotation*
-allowaccessmodification
-overloadaggressively
-dontusemixedcaseclassnames

#-optimizationpasses 0

-flattenpackagehierarchy
-repackageclasses 'com.jab125.o0lIo00Ll'

-keep class com.jab125.clothintegration.FabricMain {
    public *;
}

-keep class com.jab125.clothintegration.ForgeMain {
    public *;
}

-keep class com.jab125.clothintegration.mixin.** {
    *;
}

-keep class com.jab125.clothintegration.jei.JeiPlugin {
}

-keep class com.jab125.clothintegration.platform.ModMenuCompat {
}

-keep class com.jab125.clothintegration.MixinPlugin {
}

-keep,allowobfuscation class ** {
    *;
}

-keepclassmembers,allowoptimization enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}