import java.util.*;
import java.util.function.Predicate;

class AssetUtil{

    final static List<Asset> assets = Arrays.asList(
        new Asset(Asset.AssetType.BOND, 1000),
        new Asset(Asset.AssetType.BOND, 2000),
        new Asset(Asset.AssetType.STOCK, 3000),
        new Asset(Asset.AssetType.STOCK, 4000)
    );

    public static void main(String[] args){
        System.out.println("total: " + totalAssetValues(assets, asset -> asset.getType()==Asset.AssetType.STOCK));
    }

    private static int totalAssetValues(final List<Asset> assetsList, Predicate<Asset> assetSelector){
        return assetsList.stream().filter(assetSelector).mapToInt(asset -> asset.getValue()).sum();
    }
}