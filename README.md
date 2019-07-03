# Najva kotling example

## Implementation guide

here is a simple guide to implement Najva library to your project

### Gradle changes

Open `build.gradle` file from your `app` module and in dependencies section add the following code

```
implementation 'com.najva.sdk:najva-android-sdk:1.0.6'
```

Then open `build.gradle` file from your `project` and in `allproject` section add this codes

```
allprojects {
    repositories {
        google()
        jcenter()
    }
}
```
