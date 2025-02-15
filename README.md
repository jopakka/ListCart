# Android Project Template

My template for Android projects.

## Useful gradle commands

Generate compose compiler metrics and reports:
```gradle
assembleRelease -PenableComposeCompilerMetrics=true -PenableComposeCompilerReports=true
```

Run all JUnit tests in debug mode:
```gradle
cleanTestDebugUnitTest testDebugUnitTest
```