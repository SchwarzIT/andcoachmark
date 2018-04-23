[![](https://jitpack.io/v/Kaufland/andcoachmark.svg)](https://jitpack.io/#Kaufland/andcoachmark)
[![Build Status](https://travis-ci.org/Kaufland/andcoachmark.svg?branch=master)](https://travis-ci.org/Kaufland/andcoachmark)
[![codecov](https://codecov.io/gh/Kaufland/andcoachmark/branch/master/graph/badge.svg)](https://codecov.io/gh/Kaufland/andcoachmark)
[![KIS](https://img.shields.io/badge/KIS-awesome-red.svg)](http://www.spannende-it.de)

<img src="https://picload.org/image/rlpaprpr/coachmark-logo.png"/>

Is a library that provides a highly customizable CoachmarkView


## Demo

![](https://picload.org/image/rlrporga/ezgif-2-96da2400cb.gif)


## Features

* The Description Text is rendered  dynamically on top or bottom.
* The ActionDescriptionText (Text with arrow to the circle) is rendered dynamically left/top/bottom/right with this priority.
* Both views can be customized - the library takes inflated views as parameters.
* Above described rendering strategy can be replaced by your own implementations or the priority of the available strategies can be changed.
* Decide how the button that closes the coachmark should appear (cancel/ok on right side, ok button below description, no button just click to dismiss). It's also possible to write your own rendering.
* All colors and texts can be changed when setting up the Coachmark with the provided Builder.
* Decide how the CoachmarkView should appear (NoAnimation or Animation that animates the circle around the clicked view getting smaller until it reaches the clicked view). It's also possible to write your own startup animation.

## Implementation


1. Add it in your root build.gradle at the end of repositories:

	 ```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	```

2. Add gradle dependency

    ```
    compile 'com.github.Kaufland:andcoachmark:1.2.4'
    ```

3. Configure Coachmark 

	``` java
	LayoutInflater mInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
	        
	View actionDescription = mInflater.inflate(R.layout.test_action_description, null);
	View description = mInflater.inflate(R.layout.test_description, null);

	OkAndCancelAtRightCornerButtonRenderer buttonRenderer = new OkAndCancelAtRightCornerButtonRenderer.Builder(this)
         	.withCancelButton("Cancel", new CoachmarkClickListener() {
                	@Override
                    	public boolean onClicked() {
                        	Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_LONG).show();
                        	//return true to dismiss the coachmark
                        	return true;
                    	}
                	})
          	.withOkButton("OK", new CoachmarkClickListener() {
                    	@Override
                    	public boolean onClicked() {
                        	Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_LONG).show();
                        	//return true to dismiss the coachmark
                    	}
                	})
          	.build();

	new CoachmarkViewBuilder(MainActivity.this)
	.withAnimationRenderer(new ConcentricCircleAnimationRenderer.Builder().withDuration(500).build())
    	.withActionDescription(actionDescription)
    	.withDescription(description)
    	.withButtonRenderer(buttonRenderer)
    	.buildAroundView(clickedView).show();
	```

