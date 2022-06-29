class Asset{
    public enum AssetType{BOND, STOCK};
    private final int value;
    private final AssetType type;

    public Asset(final AssetType assetType, final int assetValue){
        value = assetValue;
        type = assetType;
    }
    public AssetType getType(){return type;}
    public int getValue(){return value;}
}