package frc.robot.Movement;

import edu.wpi.first.networktables.BooleanSubscriber;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.PubSubOption;
import frc.robot.States;

public class Net {
    public static final String topicPrefix = "/remote/movement/";

    private final NetworkTableInstance net;
    public final States.Movement states; // 🇺🇸

    private final BooleanSubscriber forward;
    private final BooleanSubscriber backward;
    private final BooleanSubscriber left;
    private final BooleanSubscriber right;

    public Net() {
        net = NetworkTableInstance.getDefault();
        net.startServer();

        forward = net.getBooleanTopic(topicPrefix + "forward").subscribe(false, PubSubOption.keepDuplicates(true));
        backward = net.getBooleanTopic(topicPrefix + "backward").subscribe(false, PubSubOption.keepDuplicates(true));
        left = net.getBooleanTopic(topicPrefix + "left").subscribe(false, PubSubOption.keepDuplicates(true));
        right = net.getBooleanTopic(topicPrefix + "right").subscribe(false, PubSubOption.keepDuplicates(true));

        states = new States.Movement();
    }

    public void update() {
        states.forward = forward.get();
        states.backward = backward.get();
        states.left = left.get();
        states.right = right.get();
    }
}
