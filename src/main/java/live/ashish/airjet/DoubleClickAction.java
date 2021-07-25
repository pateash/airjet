package live.ashish.airjet;

public enum DoubleClickAction {

    TRIGGER_BUILD,
    LOAD_BUILDS,
    SHOW_LAST_LOG;

    public static final DoubleClickAction DEFAULT = DoubleClickAction.TRIGGER_BUILD;
}
