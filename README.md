**Android Wizard** 
 
Take it as a plugin for wizards to track the change in pages
Soon this will be available as a dependency. 

Keep watch

![Image](screenshots/1.png)

integration 

Add the following to your **build.gradle(App)**

```bash
coming soon
```
In your layout add the following view
```xml
<com.jswiftdev.wizard.views.WizardIndicator
        android:layout_width="match_parent"
        android:layout_height="100dp"
        android:layout_centerInParent="true"
        app:lineColor="@color/deep_silver"
        app:numberOfPages="5"
        app:circleRadius="35"
        app:textSize="30"
        app:activePage="5"
        app:activeCircleColor="@color/apple_orange"
        app:circleColor="@color/deep_silver"
        app:lineWidth="3" />
```
to manipulate the changes from page to page, you can use
```java
...
private WizardIndicator indicator;

@Override
protected void onCreate(Bundle savedInstanceState) {
...
indicator = (WizardIndicator)findViewById(R.id.indicator);

//to change from one active page to another
indicator.setActivePage(1);
...
}
...
```

more stuff will be coming soon!!!