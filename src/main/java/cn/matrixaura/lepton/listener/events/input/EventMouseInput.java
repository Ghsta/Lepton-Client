package cn.matrixaura.lepton.listener.events.input;

public class EventMouseInput {
    private final int button;

    public EventMouseInput(int button) {
        this.button = button;
    }

    public int getButton() {
        return button;
    }
}
