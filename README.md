# Android Wizard
 
Take it as a plugin for wizards to track the change in pages  
The dependency is available on **jcenter()** and **mavenCentral()**

![Image](screenshots/1.png)

# Integration


Add the following to your **build.gradle(App)**
```gradle{
dependencies {
    //other dependencies
    compile 'com.jswiftdev.wizard:Indicator:1.0.4'
}
```
In your layout add the following view
```xml{
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

```java{
import com.jswiftdev.wizard.Indicator;

@Override
protected void onCreate(Bundle savedInstanceState) {
    WizardIndicator indicator = (WizardIndicator)findViewById(R.id.indicator);

    //to change from one active page to another
    indicator.setActivePage(1);
    
    //set the number of pages
    indicator.setNumberOfPages(5);
}
```

while using it with viewpager add **indicator.setActivePage(position);** in **onTabSelected(...)** method  
as shown below



# License

    Copyright 2017 Jswiftdev.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.