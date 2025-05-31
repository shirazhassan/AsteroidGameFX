package shantAFX.common.data.components;

public class FactionComponent {
    public enum Faction {
        PLAYER,
        ENEMY,
        NEUTRAL
    }

    private final Faction faction;

    public FactionComponent(Faction faction) {
        this.faction = faction;
    }

    public Faction getFaction() {
        return faction;
    }
}
