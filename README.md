# Najva kotling example
This is a simple sample for using NajvaSDK in your app.

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

### Code changes

Open `MainActivity` class and in import section add this code

```
import com.najva.najvasdk.Class.Najva
```

And in `onCreate` method add 

```
Najva.initialize(this, YOUR-CAMPAIGN-ID, YOUR-WEBSITE-ID, YOUR-API-KEY, true)
```

* This code will be automatically generated in your Application Panel and you just need to copy it.

* To make sure that you did everything right install your app in any device and after few minutes your device should be displayed in your Panel
Also you can send a notification and check if you can receive it.

#### Now Application is ready for push notifications

## Get JSON notifications

You can instead of getting new `android notifiaction` receive a `JSON` and do your own job instead of displaying `android notification` or create your custom notification.
To do it add thease codes to `AndroidManifest.xml` file

```

<service android:name="com.najva.najvasdk.Class.NajvaPushNotificationHandler"
    tools:node="remove" />

<service android:name=".MyAppService">
    <intent-filter>
        <action android:name="com.google.firebase.MESSAGING_EVENT" />
    </intent-filter>
</service>
```

In next step you need to declare a service in your application called `MyAppService` , to do it just create the class and add the following code in it

```
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.FirebaseMessagingService

class MyAppService : FirebaseMessagingService(){

   override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        if(canNajvaHandleNotification)
            NajvaPushNotificationHandler.handleMessage(applicationContext,remoteMessage)
        else{
            val map = remoteMessage?.data
            doMyCustomAction(data?.get('json-data')
        }
    }
}
```
Now you have access to the `JSON` and you can write your own code

## Synchronize your app with Najva users for purposeful notifications

You can send notification to your users based on their behaviour, and sync your Najva users. for do it you need to create a class and extend `NajvaUserHandler` class then `override` method `najvaUserSubscribed` and here you can map your Najva users with your application

```
class MyUserHandler(val newUser : (String)->Unit) : NajvaUserHandler() {
   override fun najvaUserSubscribed(token: String?) {
       if (token!=null) newUser(token)
   }
}
```

At the end you need to add the following code in your `MainActivity` class after initializing Najva

```
Najva.setUserHandler(MyUserHandler(handlerResult))
```