package lk.ijse.pos_backend.dao;

import lk.ijse.pos_backend.dao.custom.impl.*;


public class DaoFactory {
    private static DaoFactory daoFactory;
    private static CustomerDaoImpl customerDaoImpl;
    private static ItemDaoImpl itemDaoImpl;
    private static OrderDaoImpl orderDaoImpl;
    private static OrderDetailsDaoImpl orderDetailsDaoImpl;

    private DaoFactory() {}

    public static DaoFactory getDaoFactory(){return (daoFactory==null) ? daoFactory = new DaoFactory() : daoFactory;}

    public enum DaoType {
        CUSTOMER,ITEM,ORDER,ORDERDETAILS
    }

    public <SuperDao> SuperDao getDao(DaoType type){
        switch (type) {
            case CUSTOMER:
                return (SuperDao) ((customerDaoImpl==null) ? customerDaoImpl = new CustomerDaoImpl():customerDaoImpl);
            case ITEM:
                return (SuperDao) ((itemDaoImpl==null) ? itemDaoImpl = new ItemDaoImpl():itemDaoImpl);
            case ORDER:
                return (SuperDao) ((orderDaoImpl==null) ? orderDaoImpl = new OrderDaoImpl():orderDaoImpl);
            case ORDERDETAILS:
                return (SuperDao) ((orderDetailsDaoImpl==null) ? orderDetailsDaoImpl = new OrderDetailsDaoImpl()
                        :orderDetailsDaoImpl);
            default:
                return null;
        }
    }
}
