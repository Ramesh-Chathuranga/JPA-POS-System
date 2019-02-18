package lk.ijse.dep.app.dao.custom;

import lk.ijse.dep.app.dao.CrudDAO;
import lk.ijse.dep.app.entity.Orders;

public interface OrderDAO extends CrudDAO<Orders, String> {

    int count() throws Exception;

}
