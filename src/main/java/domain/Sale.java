package domain;

import config.Characters;
import lombok.Builder;
import lombok.Data;

@Data
public class Sale {

    private String saleId;
    private Double salePrice;
    private String salesmanName;

    public Sale(String saleId, String soldProducts, String salesmanName){
        this.saleId         = saleId;
        this.salesmanName   = salesmanName;
        this.salePrice      = generateSalePrice(soldProducts);
    }

    @Builder(builderMethodName = "builder")
    public static Sale saleBuilder(String saleId, String soldProducts, String salesmanName) {
        return new Sale(saleId, soldProducts, salesmanName);
    }

    /**
     * Generates the sale price based on the string of products in the .dat file
     * @param soldProducts The String of products
     * @return double
     */
    private Double generateSalePrice(String soldProducts){
        Double total = 0.00;
        String[] products = soldProducts.replaceAll("[\\[\\](){}]","").split(Characters.PRODUCTS_SEPARATOR);
        for(String product : products){
            String[] productInfo = product.split(Characters.PRODUCT_INFO_SEPARATOR);
            total += (Integer.parseInt(productInfo[1]) * Double.parseDouble(productInfo[2]));
        }
        return total;
    }

}
