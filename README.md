# Broadcast-Receiver
Android BroadcastReceiver that is a very important component of Android Framework. Android BroadcastReceiver is a dormant component of android that listens to system-wide broadcast events or intents.
When any of these events occur it brings the application into action by either creating a status bar notification or performing a task.
 
Unlike activities, android BroadcastReceiver doesn’t contain any user interface. Broadcast receiver is generally implemented to delegate the tasks to services depending on the type of intent data that’s received.

**Following are some of the important system wide generated intents.**

1. android.intent.action.BATTERY_LOW : Indicates low battery condition on the device.
2. android.intent.action.BOOT_COMPLETED : This is broadcast once, after the system has finished booting
3. android.intent.action.CALL : To perform a call to someone specified by the data
4. android.intent.action.DATE_CHANGED : The date has changed
5. android.intent.action.REBOOT : Have the device reboot
6. android.net.conn.CONNECTIVITY_CHANGE : The mobile network or wifi connection is changed(or reset)

# Broadcast Receiver in Android
1. Creating a BroadcastReceiver
2. Registering a BroadcastReceiver

# Creating a BroadcastReceiver
Let’s quickly implement a custom BroadcastReceiver as shown below.

``` java
public class MyReceiver extends BroadcastReceiver {
        public MyReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Action: " + intent.getAction(), Toast.LENGTH_SHORT).show();
        }
    }
```

BroadcastReceiver is an abstract class with the onReceiver() method being abstract.

The onReceiver() method is first called on the registered Broadcast Receivers when any event occurs.

The intent object is passed with all the additional data. A Context object is also available and is used to start an activity or service using context.startActivity(myIntent); or context.startService(myService); respectively.

# Registering the BroadcastReceiver in android app
A BroadcastReceiver can be registered in two ways.
1. By defining it in the AndroidManifest.xml file as shown below.

```java
<receiver android:name=".ConnectionReceiver" >
             <intent-filter>
                 <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
             </intent-filter>
</receiver>
```

Using intent filters we tell the system any intent that matches our subelements should get delivered to that specific broadcast receiver.

2. By defining it programmatically
Following snippet shows a sample example to register broadcast receiver programmatically.

```java
IntentFilter filter = new IntentFilter();
intentFilter.addAction(getPackageName() + "android.net.conn.CONNECTIVITY_CHANGE");
 
MyReceiver myReceiver = new MyReceiver();
registerReceiver(myReceiver, filter);
```

To unregister a broadcast receiver in onStop() or onPause() of the activity the following snippet can be used.

```java
@Override
protected void onPause() {
    unregisterReceiver(myReceiver);
    super.onPause();
}
```

# Sending Broadcast intents from the Activity
The following snippet is used to send an intent to all the related BroadcastReceivers.
```java
Intent intent = new Intent();
      intent.setAction("com.journaldev.CUSTOM_INTENT");
      sendBroadcast(intent);
```

Don’t forget to add the above action in the intent filter tag of the manifest or programmatically.
