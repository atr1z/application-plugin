# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a **Gradle plugin** (`mx.com.atriz.application`) that provides standardized Android application build configuration. When applied to an Android project, it automatically configures SDK versions, build features (ViewBinding, Compose, BuildConfig), ProGuard, MultiDex, and Java/Kotlin compilation targets.

## Build Commands

```bash
./gradlew build      # Build and package the plugin
./gradlew publish    # Publish to Maven Central (Sonatype) and Gradle Plugin Portal
```

There is no separate test or lint command — `build` covers compilation and validation.

## Architecture

The plugin has two source files:

- **`src/main/kotlin/mx/com/atriz/Plugin.kt`** — Main plugin class (`Application`). Applies `com.android.application` and `kotlin-android`, then configures the Android extension (compile/target/min SDK, build features, build types, Java compatibility).
- **`src/main/kotlin/mx/com/atriz/core/Version.kt`** — Central object holding SDK version constants (compileSdk, targetSdk, minSdk) used by the plugin.

## Publishing

Published to two targets via `com.vanniktech.maven.publish`:
- **Gradle Plugin Portal** (primary)
- **Maven Central via Sonatype** (automatic release)

### Versioning

Version is derived automatically from git tags using `git describe --tags --abbrev=0`. No hardcoded version in `build.gradle.kts`.

- Tags must follow the `v<semver>` format (e.g., `v0.1.6`)
- Without a tag, version defaults to `0.0.0-SNAPSHOT`

### Release flow

```bash
git tag v0.1.6
git push origin v0.1.6
```

CI runs build on all pushes/PRs; publish runs **only** on `v*` tag pushes.

### Secrets

GPG signing uses `SIGNING_KEY` and `SIGNING_PASSWORD` environment variables. Maven Central credentials are `ORG_GRADLE_PROJECT_mavenCentralUsername` / `ORG_GRADLE_PROJECT_mavenCentralPassword`.

## Toolchain

- Gradle 8.8 (Kotlin DSL)
- Kotlin 2.0.20
- Android Gradle Plugin 8.8.1
- JVM target: Java 21
