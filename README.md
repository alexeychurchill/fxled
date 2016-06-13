# fxled
### Description
-------------------------------
This is simple LED control for JavaFX. It has two states - *ON* and *OFF*.

### Properties
-------------------------------
* `state` - LED state property (`false` - *OFF*, `true` - *ON*);
* `onColor` - LED color if turned *ON*;
* `offColor` - LED color if turned *OFF*;
* `backgroundColor` - LED background color (stroke color);
* `ledSize` - LED size in range [0.0; 1.0].

### Methods
-------------------------------
* `getState()` - returns state of the LED (`false` - *OFF*, `true` - *ON*);
* `setState(boolean state)` - sets state of the LED (`false` - *OFF*, `true` - *ON*);
* `stateProperty()` - returns state property;
* `on()` - turns LED *ON*;
* `off()` - turns LED *OFF*;
* `getLedSize()` - returns LED size;
* `setLedSize(double ledSize)` - sets LED size;
* `ledSizeProperty()` - returns LED size property;
* `getOffColor()` - returns LED *OFF* color;
* `setOffColor(Paint offColor)` - sets LED *OFF* color;
* `offColorProperty()` - returns LED *OFF* color property;
* `getOnColor()` - returns LED *ON* color;
* `setOnColor(Paint onColor)` - sets LED *ON* color;
* `onColorProperty()` - returns LED *ON* color property;
* `getBackgroundColor()` - returns LED background color;
* `setBackgroundColor(Paint backgroundColor)` - sets LED background color;
* `backgroundColorProperty()` - returns LED background color property.

### CSS Properties
-------------------------------
* `-led-background-color` - LED base (border) color;
* `-led-on-color` - LED *ON* state color;
* `-led-off-color` - LED *OFF* state color;
* `-led-size` - LED size (in range from 0.0 to 1.0).
