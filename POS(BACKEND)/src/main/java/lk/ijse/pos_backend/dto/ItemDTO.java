package lk.ijse.pos_backend.dto;

import lk.ijse.pos_backend.entity.ItemEntity;
import lombok.Data;


@Data
public class ItemDTO {
    private String ItemCode;
    private String ItemName;
    private int Quantity;
    private double Price;

    public ItemEntity ToEntity(){
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setCode(this.ItemCode);
        itemEntity.setName(this.ItemName);
        itemEntity.setQty(this.Quantity);
        itemEntity.setPrice(this.Price);
        return itemEntity;
    }
}
