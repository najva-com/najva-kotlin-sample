# برنامه نمونه کاتلین با استفاده از کتابخانه نجوا
یک برنامه ساده که قادر است از سمت سرور نجوا نوتیفیکیشن دریافت کند.

## نحوه پیاده سازی 


### تغییرات گردل
برای پیاده سازی کتابخانه نجوا در پروژه خو ابتدا فایل `build.gradle` مربوط به پروژه خود را باز کرده و کد های زیر را به آن اضافه کنید.
```
allprojects {
    repositories {
        google()
        jcenter()
    }
}
```

سپس فایل `build.gradle` مربوط به ماژول اپلیکیشن خود را خط کد زیر را وارد کنید.

```
implementation 'com.najva.sdk:najva-android-sdk:1.0.6'
```

در نهایت پروژه خود را سینک کنید.

### تغییرات کد

داخل کلاس `MainActivity` در بخش `import`ها کد زیر را وارد کنید.
```
import com.najva.najvasdk.Class.Najva
```

سپس در داخل تابع `onCreate` کد زیر را قرار دهید تا کتابخانه نجوا شروع به کار کند.

```
Najva.initialize(this, YOUR-CAMPAIGN-ID, YOUR-WEBSITE-ID, YOUR-API-KEY, true)
```

* کد فوق به طور خودکار در پنل کاربری برای شما تولید میشود.
* برای اینکه مطمئن شوید همه چیز را به درستی انجام داده اید برنامه رو روی یک دستگاه نصب کنید، بعد از مدت کوتاهی باید یک دستگاه در پنل شما ثبت شود یا اینکه از قسمت پنل یک نوتیفیکیشن برای دستگاه خود ارسال کنید.


حالا برنامه شما آماده است برای اجرا

### دریافت نوتیفیکیشن بصورت JSON
برای دریافت نوتیفیکیشن بصورت `JSON` باید یک سرویس جدید تعریف کنید تا نوتیفیکیشن ها توسط سرویس شما دریافت شود. 
برای این کار باید این کد را به فایل `AndroidManifest.xml` اضافه کنید

```
<service android:name="com.najva.najvasdk.Class.NajvaPushNotificationHandler"
    tools:node="remove" />
```

کد فوق سرویس نجوا در اپلیکیشن شما رو غیر فعال میکند 

در مرحله بعدی باید یک سرویس جدید تعریف کنید تا در آن سرویس نوتیفیکیشن ها را دریافت کنید.

برای تعریف سرویس جدید کد زیر را در فایل `AndroidManifest.xml` اضافه کنید.

```
<service android:name=".MyAppService">
    <intent-filter>
        <action android:name="com.google.firebase.MESSAGING_EVENT" />
    </intent-filter>
</service>
```

کد فوق یک سرویس جدید با نام `MyAppService` برای شما تعریف میکند

حال باید یک کلاس جدید به نام `MyAppService` بنویسید و از کلاس `FirebaseMessagingService` ارث ببرید

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

در کد فوق متغییر `canNajvaHandleNotification` وجود ندارد و شما باید با توجه به نیاز خود آنرا تغییر دهید.
همچنین تابع `doMyCustomAction` نیز وجود ندارد و در صورتی که میخواهید کار دیگر با نوتیفیکیشن دریافتی خود اتجام دهد آنرا پیاده سازی کنید.

### دسته بندی کاربران

برای دسته بندی کاربران توسط خودتان باید یک کلاس جدید به نام `MyUserHandler` تعریف کنید  و در آن از کلاس `ٔNajvaUserHandler` ارث ببرید.
با پیاده سازی کردن تابع `najvaUserSubscribed` میتوانید به توکن نجوایی کاربر دسترسی داشته باشید و این توکن را در سرور خود ذخیره کنید.

```
class MyUserHandler(val newUser : (String)->Unit) : NajvaUserHandler() {
   override fun najvaUserSubscribed(token: String?) {
       if (token!=null) newUser(token)
   }
}
```

در نهایت یک خط کد زیر را بعد از دستور `init` در تابع `onCreate` در کلاس `MainActivity` اضافه کنید.

```
Najva.setUserHandler(MyUserHandler(handlerResult))
```