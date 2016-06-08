package led;

import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import javafx.animation.Transition;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class LedSkin extends BehaviorSkinBase<Led, LedBehavior> {
    private static final double STATE_SWITCH_DURATION = 70.0;
    private static final double COLOR_CHANGE_DURATION = 150.0;
    //...
    private Led led;
    private double currentSize = 1.0;
    //Nodes
    private StackPane container;
    private Circle backgroundCircle;
    private Circle ledCircle;

    public LedSkin(Led led) {
        super(led, new LedBehavior(led, null));
        this.led = led;
        initialize();
    }

    private void initialize() {
        //Pre-draw
        draw();
        //State handling
        registerChangeListener(led.stateProperty(), "LEDSTATE"); //on/off switch
        registerChangeListener(led.offColorProperty(), "OFFCOLORCHANGE"); //OFF color change
        registerChangeListener(led.onColorProperty(), "ONCOLORCHANGE"); //ON color change
        registerChangeListener(led.backgroundColorProperty(), "BGCOLORCHANGE"); //BACKGROUND color change
    }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        currentSize = Math.min(contentWidth, contentHeight);
        resize();
        super.layoutChildren(contentX, contentY, contentWidth, contentHeight);
    }

    private void draw() {
        container = new StackPane();
        double ledSize = led.ledSizeProperty().get();
        //Background
        backgroundCircle = new Circle(currentSize / 2.0);
        backgroundCircle.setFill(led.backgroundColorProperty().get());
        container.getChildren().add(backgroundCircle);
        //Foreground
        ledCircle = new Circle(ledSize * (currentSize / 2.0));
        ledCircle.setFill((led.stateProperty().get()?led.onColorProperty().get():led.offColorProperty().get()));
        container.getChildren().add(ledCircle);
        //...
        getChildren().clear();
        getChildren().add(container);
    }

    private void resize() {
        if (container == null || backgroundCircle == null || ledCircle == null) {
            draw();
        }
        double ledSize = led.ledSizeProperty().get();
        backgroundCircle.setRadius(currentSize / 2.0);
        ledCircle.setRadius(ledSize * (currentSize / 2.0));
    }

    @Override
    protected void handleControlPropertyChanged(String propertyReference) {
        super.handleControlPropertyChanged(propertyReference);
        if ("LEDSTATE".contentEquals(propertyReference)) { //on/off
            if (led.stateProperty().get()) {
                animateOn();
            } else {
                animateOff();
            }
        }
        if ("OFFCOLORCHANGE".contentEquals(propertyReference)) { //off color change
            offColorChange();
        }
        if ("ONCOLORCHANGE".contentEquals(propertyReference)) { //on color change
            onColorChange();
        }
        if ("BGCOLORCHANGE".contentEquals(propertyReference)) { //background color change
            backgroundColorChange();
        }
        if ("LEDSIZECHANGE".contentEquals(propertyReference)) { //ledSizeChange
            resize();
        }
    }

    private void offColorChange() {
        if (led.stateProperty().get()) { //if led is on it's no needed to change color
            return;
        }
        colorizeLed((Color) led.offColorProperty().get(), COLOR_CHANGE_DURATION);
    }

    private void onColorChange() {
        if (!led.stateProperty().get()) { //if led is off it's no needed to change color
            return;
        }
        colorizeLed((Color) led.onColorProperty().get(), COLOR_CHANGE_DURATION);
    }

    private void backgroundColorChange() {
        backgroundCircle.setFill(led.backgroundColorProperty().get());
    }

    private void animateOff() {
        colorizeLed((Color) led.offColorProperty().get(), STATE_SWITCH_DURATION);
    }

    private void animateOn() {
        colorizeLed((Color) led.onColorProperty().get(), STATE_SWITCH_DURATION);
    }

    private void colorizeLed(Color targetColor, double duration) {
        if (ledCircle == null) {
            return;
        }
        if (ledCircle.getFill() == null) {
            ledCircle.setFill(targetColor);
            return;
        }
        Color sourceColor = (Color) ledCircle.getFill();
        double rStart, gStart, bStart; //Source color components (rED, gREEN, bLUE)
        rStart = sourceColor.getRed();
        gStart = sourceColor.getGreen();
        bStart = sourceColor.getBlue();
        double rStop, gStop, bStop; //Target color components (rED, gREEN, bLUE)
        rStop = targetColor.getRed();
        gStop = targetColor.getGreen();
        bStop = targetColor.getBlue();
        //Transition
        Transition colorTransition = new Transition() {
            {
                setCycleDuration(Duration.millis(duration));
            }
            @Override
            protected void interpolate(double frac) {
                Color transColor = Color.color(
                        rStart + frac * (rStop - rStart),
                        gStart + frac * (gStop - gStart),
                        bStart + frac * (bStop - bStart)
                );
                ledCircle.setFill(transColor);
            }
        };
        colorTransition.play();
    }
}
