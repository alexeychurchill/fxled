package led;

import com.sun.javafx.css.converters.PaintConverter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.*;
import javafx.scene.control.Control;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Led extends Control {
    private static final String DEFAULT_STYLE_CLASS = "led";
    private static final Color DEFAULT_ON_COLOR = Color.rgb(145, 35, 255);
    private static final Color DEFAULT_OFF_COLOR = Color.rgb(30, 0, 60);
    private static final Color DEFAULT_BACKGROUND_COLOR = Color.rgb(200, 200, 200);
    private static final double DEFAULT_LED_SIZE = 0.75;
    //...
    private BooleanProperty state = new SimpleBooleanProperty(false);
    //Colors
    private ObjectProperty<Paint> onColor;
    private ObjectProperty<Paint> offColor;
    private ObjectProperty<Paint> backgroundColor;
    private DoubleProperty ledSize;

    public Led() {
        initialize();
    }

    private void initialize() {
        setMinWidth(0.0);
        setMinHeight(0.0);
        initializeColors();
        initializeLedSize();
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
        setSkin(new LedSkin(this));
    }

    //Initialization
    private void initializeColors() {
        //ON Color
        onColor = new StyleableObjectProperty<Paint>(DEFAULT_ON_COLOR) {
            @Override
            public Object getBean() {
                return this;
            }

            @Override
            public String getName() {
                return "onColor";
            }

            @Override
            public CssMetaData<? extends Styleable, Paint> getCssMetaData() {
                return StylesheetProperties.CSS_ON_COLOR;
            }
        };
        //OFF Color
        offColor = new StyleableObjectProperty<Paint>(DEFAULT_OFF_COLOR) {
            @Override
            public Object getBean() {
                return this;
            }

            @Override
            public String getName() {
                return "offColor";
            }

            @Override
            public CssMetaData<? extends Styleable, Paint> getCssMetaData() {
                return StylesheetProperties.CSS_OFF_COLOR;
            }
        };
        //BACKGROUND Color
        backgroundColor = new StyleableObjectProperty<Paint>(DEFAULT_BACKGROUND_COLOR) {
            @Override
            public Object getBean() {
                return this;
            }

            @Override
            public String getName() {
                return "backgroundColor";
            }

            @Override
            public CssMetaData<? extends Styleable, Paint> getCssMetaData() {
                return StylesheetProperties.CSS_BACKGROUND_COLOR;
            }
        };
    }

    private void initializeLedSize() {
        ledSize = new StyleableDoubleProperty() {
            @Override
            public Object getBean() {
                return this;
            }

            @Override
            public String getName() {
                return "ledSize";
            }

            @Override
            public CssMetaData<? extends Styleable, Number> getCssMetaData() {
                return StylesheetProperties.CSS_LED_SIZE;
            }
        };
    }

    //Getters and setters
    public boolean getState() {
        return state.get();
    }

    public BooleanProperty stateProperty() {
        return state;
    }

    public void setState(boolean state) {
        this.state.set(state);
    }

    public void on() {
        setState(true);
    }

    public void off() {
        setState(false);
    }

    public Paint getOnColor() {
        return onColor.get();
    }

    public ObjectProperty<Paint> onColorProperty() {
        return onColor;
    }

    public void setOnColor(Paint onColor) {
        this.onColor.set(onColor);
    }

    public Paint getOffColor() {
        return offColor.get();
    }

    public ObjectProperty<Paint> offColorProperty() {
        return offColor;
    }

    public void setOffColor(Paint offColor) {
        this.offColor.set(offColor);
    }

    public Paint getBackgroundColor() {
        return backgroundColor.get();
    }

    public ObjectProperty<Paint> backgroundColorProperty() {
        return backgroundColor;
    }

    public void setBackgroundColor(Paint backgroundColor) {
        this.backgroundColor.set(backgroundColor);
    }

    public double getLedSize() {
        return ledSize.get();
    }

    public DoubleProperty ledSizeProperty() {
        return ledSize;
    }

    public void setLedSize(double ledSize) {
        this.ledSize.set(ledSize);
    }

    //Stylesheet properties
    private static class StylesheetProperties {
        //CSS background color
        private static final CssMetaData<Led, Paint> CSS_BACKGROUND_COLOR =
                new CssMetaData<Led, Paint>("-led-background-color", PaintConverter.getPaintConverter(), DEFAULT_BACKGROUND_COLOR) {
            @Override
            public boolean isSettable(Led styleable) {
                return !styleable.backgroundColorProperty().isBound();
            }

            @Override
            public StyleableProperty<Paint> getStyleableProperty(Led styleable) {
                return (StyleableProperty) styleable.backgroundColorProperty();
            }
        };
        //CSS on color
        private static final CssMetaData<Led, Paint> CSS_ON_COLOR =
                new CssMetaData<Led, Paint>("-led-on-color", PaintConverter.getPaintConverter(), DEFAULT_ON_COLOR) {
                    @Override
                    public boolean isSettable(Led styleable) {
                        return !styleable.onColorProperty().isBound();
                    }

                    @Override
                    public StyleableProperty<Paint> getStyleableProperty(Led styleable) {
                        return (StyleableProperty) styleable.onColorProperty();
                    }
                };
        //CSS off color
        private static final CssMetaData<Led, Paint> CSS_OFF_COLOR =
                new CssMetaData<Led, Paint>("-led-off-color", PaintConverter.getPaintConverter(), DEFAULT_OFF_COLOR) {
                    @Override
                    public boolean isSettable(Led styleable) {
                        return !styleable.offColorProperty().isBound();
                    }

                    @Override
                    public StyleableProperty<Paint> getStyleableProperty(Led styleable) {
                        return (StyleableProperty) styleable.offColorProperty();
                    }
                };
        private static final CssMetaData<Led, Number> CSS_LED_SIZE =
                new CssMetaData<Led, Number>("-led-size", StyleConverter.getSizeConverter(), DEFAULT_LED_SIZE) {
                    @Override
                    public boolean isSettable(Led styleable) {
                        return !styleable.ledSizeProperty().isBound();
                    }

                    @Override
                    public StyleableProperty<Number> getStyleableProperty(Led styleable) {
                        return (StyleableProperty) styleable.ledSizeProperty();
                    }
                };
    }

    //Stylesheet list
    private static final List<CssMetaData<? extends Styleable, ?>> CSS_META_DATA_LIST;
    static {
        List<CssMetaData<? extends Styleable, ?>> tempCssMetaDataList =
                new ArrayList<>(Control.getClassCssMetaData());
        tempCssMetaDataList.add(StylesheetProperties.CSS_BACKGROUND_COLOR);
        tempCssMetaDataList.add(StylesheetProperties.CSS_ON_COLOR);
        tempCssMetaDataList.add(StylesheetProperties.CSS_OFF_COLOR);
        tempCssMetaDataList.add(StylesheetProperties.CSS_LED_SIZE);
        CSS_META_DATA_LIST = Collections.unmodifiableList(tempCssMetaDataList);
    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return CSS_META_DATA_LIST;
    }

    @Override
    protected List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return getClassCssMetaData();
    }
}
