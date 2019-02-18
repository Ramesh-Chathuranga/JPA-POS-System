package lk.ijse.dep.app.dao.custom.impl;

import lk.ijse.dep.app.business.Converter;
import lk.ijse.dep.app.dao.custom.QueryDAO;
import lk.ijse.dep.app.entity.CustomEntity;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class QuerydAOImpl implements QueryDAO {
    private EntityManager em;
    private Object o;


    @Override
    public Optional<List<CustomEntity>> findOrderDetailsWithItemDescriptions(String orderId) throws Exception {
//String sql="SELECT o.id,o.date,o.customer,od.orders,od.item,od.qty,od.unitPrice,i.description FROM Orders  o JOIN OrderDetail od on o.id = od.orders left join Item i on od.item = i.code WHERE o.id=:id";
//        List list = session.createQuery(sql).setParameter("id", orderId).list();
String sql="SELECT o.id,o.date,o.customer,od.itemCode,od.qty,od.unitPrice,i.description FROM orders as o JOIN orderdetail od on o.id = od.orderId left join item i on od.itemCode = i.code WHERE o.id='"+ orderId+"'";
        List<Object[]> list = em.createNativeQuery(sql)
//                .setParameter("id", orderId)
               .getResultList();
        List<CustomEntity>customEntities=new ArrayList<>();
        for (Object[] b:list ) {
           String id=b[0].toString();
           Date date=(Date)b[1];
          //  Date date2 = new Date(Long.valueOf(b[1].toString()));
            LocalDate date1 = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
             String customer=b[2].toString();
            String code=b[3].toString();
            int qty=Integer.parseInt(b[4].toString());
            double up=Double.parseDouble(b[5].toString());

            String  description=b[6].toString();
            CustomEntity customEntity = new CustomEntity(id, date1, customer, code, qty, up, description);
            System.out.println(date1);
            customEntities.add(customEntity);
        }
        return Optional.ofNullable(customEntities);
    }

    @Override
    public Optional<List<CustomEntity>> findAllOrdersWithCustomerNameAndTotal() throws Exception {
        String sql="SELECT o.id,o.date,o.customer,od.itemCode,od.qty,od.unitPrice,i.description,c.name FROM orders as o JOIN orderdetail od on o.id = od.orderId left join item i on od.itemCode = i.code left join Customer c on o.customer=c.id";
        List<Object[]> list = em.createNativeQuery(sql).getResultList();
        List<CustomEntity>customEntities=new ArrayList<>();
        for (Object[] b:list ) {
            String id=b[0].toString();
            Date date=(Date)b[1];
            //  Date date2 = new Date(Long.valueOf(b[1].toString()));
            LocalDate date1 = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            String customer=b[2].toString();
            String code=b[3].toString();
            double total=Integer.parseInt(b[4].toString())*Double.parseDouble(b[5].toString());


            String name=b[7].toString();


            CustomEntity customEntity = new CustomEntity(id, date1, customer, name,  total);
            customEntities.add(customEntity);
        }
        return Optional.ofNullable(customEntities);
    }

    @Override
    public void setEntityManager(EntityManager em) {
      this.em=em;
    }
}
