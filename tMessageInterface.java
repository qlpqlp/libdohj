[1mdiff --git a/core/build.gradle b/core/build.gradle[m
[1mindex f9de088d..1a36bcb6 100644[m
[1m--- a/core/build.gradle[m
[1m+++ b/core/build.gradle[m
[36m@@ -1,22 +1,22 @@[m
 apply plugin: 'java-library'[m
[31m-apply plugin: 'maven'[m
[32m+[m[32mapply plugin: 'maven-publish'[m
 [m
[31m-version = '0.15-SNAPSHOT'[m
[32m+[m[32mversion = '0.17-SNAPSHOT'[m
 archivesBaseName = 'libdohj-core'[m
 [m
 dependencies {[m
[31m-    api 'org.bitcoinj:bitcoinj-core:0.15.10'[m
[32m+[m[32m    api 'org.bitcoinj:bitcoinj-core:0.17'[m
     implementation 'com.madgag.spongycastle:core:1.58.0.0'[m
[31m-    implementation 'com.google.guava:guava:30.0-android'[m
[32m+[m[32m    implementation 'com.google.guava:guava:32.1.3-android'[m
     implementation 'com.lambdaworks:scrypt:1.4.0'[m
[31m-    implementation 'com.google.protobuf:protobuf-java:3.13.0'[m
[31m-    implementation 'com.squareup.okhttp3:okhttp:3.12.12'[m
[31m-    implementation 'org.slf4j:slf4j-api:1.7.30'[m
[32m+[m[32m    implementation 'com.google.protobuf:protobuf-java:3.25.3'[m
[32m+[m[32m    implementation 'com.squareup.okhttp3:okhttp:4.12.0'[m
[32m+[m[32m    implementation 'org.slf4j:slf4j-api:2.0.9'[m
     implementation 'net.jcip:jcip-annotations:1.0'[m
     compileOnly 'org.fusesource.leveldbjni:leveldbjni-all:1.8'[m
[31m-    testImplementation 'junit:junit:4.13.1'[m
[31m-    testImplementation 'com.fasterxml.jackson.core:jackson-databind:2.5.2'[m
[31m-    testImplementation 'org.slf4j:slf4j-jdk14:1.7.30'[m
[32m+[m[32m    testImplementation 'junit:junit:4.13.2'[m
[32m+[m[32m    testImplementation 'com.fasterxml.jackson.core:jackson-databind:2.16.1'[m
[32m+[m[32m    testImplementation 'org.slf4j:slf4j-jdk14:2.0.9'[m
     testImplementation 'org.fusesource.leveldbjni:leveldbjni-all:1.8'[m
 }[m
 [m
[36m@@ -44,3 +44,13 @@[m [martifacts {[m
     archives sourcesJar[m
     archives javadocJar[m
 }[m
[32m+[m
[32m+[m[32mpublishing {[m
[32m+[m[32m    publications {[m
[32m+[m[32m        maven(MavenPublication) {[m
[32m+[m[32m            from components.java[m
[32m+[m[32m            artifact sourcesJar[m
[32m+[m[32m            artifact javadocJar[m
[32m+[m[32m        }[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
