package components;

public enum AnimationType {
    IDLE,
    RUN,
    CHOP,
    LIFT,
    DROP;

    private String atlasKey;

    private AnimationType() {
        this.atlasKey = this.toString().toLowerCase();
    }

    public String getAtlasKey() {
        return atlasKey;
    }
}
