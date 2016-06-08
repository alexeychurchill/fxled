package led;

import com.sun.javafx.scene.control.behavior.BehaviorBase;
import com.sun.javafx.scene.control.behavior.KeyBinding;

import java.util.List;

public class LedBehavior extends BehaviorBase<Led> {
    public LedBehavior(Led control, List<KeyBinding> keyBindings) {
        super(control, keyBindings);
    }
}
