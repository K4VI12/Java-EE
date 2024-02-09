package lk.ijse.pos_backend.bo;

import lk.ijse.pos_backend.bo.custom.impl.*;


public class BOFactory {
    private static BOFactory boFactory;
    private static CustomerBOIMPL customerBOIMPL;
    private static ItemBOIMPL itemBOIMPL;
    private static OrderBOIMPL orderBOIMPL;
    private static OrderDetailsBOIMPL orderDetailsBOIMPL;

    private BOFactory() {}

    public static BOFactory getBoFactory(){return (boFactory==null) ? boFactory = new BOFactory() : boFactory;}

    public enum BOtype {
        CUSTOMER,ITEM,ORDER,ORDERDETAILS
    }

    public <SuperBO> SuperBO getBo(BOtype type){
        switch (type){
            case CUSTOMER:
                return (SuperBO) ((customerBOIMPL==null) ? customerBOIMPL=new CustomerBOIMPL():customerBOIMPL);
            case ITEM:
                return (SuperBO) ((itemBOIMPL==null) ? itemBOIMPL = new ItemBOIMPL():itemBOIMPL);
            case ORDER:
                return (SuperBO) ((orderBOIMPL==null) ? orderBOIMPL = new OrderBOIMPL():orderBOIMPL);
            case ORDERDETAILS:
                return (SuperBO) ((orderDetailsBOIMPL==null) ? orderDetailsBOIMPL = new OrderDetailsBOIMPL()
                        :orderDetailsBOIMPL);
            default:
                return null;
        }
    }
}
