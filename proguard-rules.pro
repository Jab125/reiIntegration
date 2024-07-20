-libraryjars <java.home>/jmods/java.base.jmod(!**.jar;!module-info.class)
#-keepattributes *Annotation*, Exceptions, Signature, Deprecated, SourceFile, SourceDir, LineNumberTable, LocalVariableTable, LocalVariableTypeTable, Synthetic, EnclosingMethod, RuntimeVisibleAnnotations, RuntimeInvisibleAnnotations, RuntimeVisibleParameterAnnotations, RuntimeInvisibleParameterAnnotations, AnnotationDefault, InnerClasses
-keepattributes InnerClasses, *Annotation*
-allowaccessmodification
-overloadaggressively
-dontusemixedcaseclassnames

#-optimizationpasses 0

-flattenpackagehierarchy
-repackageclasses 'dev.jab125.l0lIo00Ll'

-keep class dev.jab125.reiintegration.ReiIntegration {
    public *;
}

-keep class dev.jab125.reiintegration.plugin.rfm.RfmPluginClient {
    public *;
}

-keep class dev.jab125.reiintegration.plugin.rfm.RfmPluginServer {
    public *;
}


-keep,allowobfuscation class ** {
    *;
}
-keepclassmembers,allowoptimization enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}