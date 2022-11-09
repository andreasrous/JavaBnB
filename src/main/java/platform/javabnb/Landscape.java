package platform.javabnb;

// απαρίθμηση Θέα
public enum Landscape {
    STREET("Street"),
    MOUNTAIN("Mountain"),
    SEA("Sea");

    // η θέα μπορεί να είναι "Street", "Mountain" ή "Sea"
    private final String landscape;

    private Landscape(String landscape) {
        this.landscape = landscape;
    }

    public String getLandscape() {
        return landscape;
    }

    @Override
    public String toString() {
        return "Landscape: " + landscape;
    }
}
