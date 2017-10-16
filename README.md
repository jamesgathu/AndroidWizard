**Android Wizard** 
 
Take it as a plugin for wizards to track the change in pages
Soon this will be available as a dependency. 

Keep watch

![Image](screenshots/1.png)

integration 

Add the following to your **build.gradle(App)**
```bash
compile 'com.jswiftdev.wizard:Indicator:1.0.4'
```
In your layout add the following view
```xml
<com.jswiftdev.wizard.Indicator
        android:id="@+id/indicator"
        android:layout_width="match_parent"
        android:layout_height="100dp"
        app:lineColor="@color/deep_silver"
        app:numberOfPages="5"
        app:circleRadius="35"
        app:textSize="30"
        app:activePage="3"
        app:activeCircleColor="@color/apple_orange"
        app:circleColor="@color/deep_silver"
        app:lineWidth="3" />
```
to manipulate the changes from page to page, you can use

```java
import com.jswiftdev.wizard.Indicator;

@Override
protected void onCreate(Bundle savedInstanceState) {
    WizardIndicator indicator = (WizardIndicator)findViewById(R.id.indicator);

    //to change from one active page to another
    indicator.setActivePage(1);
}
```