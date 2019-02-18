package lk.ijse.dep.app.business.custom.impl;

import lk.ijse.dep.app.business.Converter;
import lk.ijse.dep.app.business.custom.ManageItemsBO;
import lk.ijse.dep.app.dao.DAOFactory;
import lk.ijse.dep.app.dao.custom.ItemDAO;
import lk.ijse.dep.app.dto.CustomerDTO;
import lk.ijse.dep.app.dto.ItemDTO;

import lk.ijse.dep.app.util.JPAUtil;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;



public class ManageItemsBOImpl implements ManageItemsBO {
    private ItemDAO itemDAO;

    public ManageItemsBOImpl() {
        itemDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
    }

    @Override
    public List<ItemDTO> getItems() throws Exception {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        try{

            itemDAO.setEntityManager(entityManager);
            System.out.println(1);
            entityManager.getTransaction().begin();
            List<ItemDTO> itemDTOS = itemDAO.findAll().map(Converter::<ItemDTO>getDTOList).get();
            entityManager.getTransaction().commit();
            return itemDTOS;
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public void createItem(ItemDTO dto) throws Exception {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        try{
           itemDAO.setEntityManager(entityManager);
            entityManager.getTransaction().begin();
            itemDAO.save(Converter.getEntity(dto));
            entityManager.getTransaction().commit();
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public void updateItem(ItemDTO dto) throws Exception {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        try{
            itemDAO.setEntityManager(entityManager);
            entityManager.getTransaction().begin();
            itemDAO.update(Converter.getEntity(dto));
            entityManager.getTransaction().commit();
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public void deleteItem(String code) throws Exception {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        try{
            itemDAO.setEntityManager(entityManager);
            entityManager.getTransaction().begin();
           itemDAO.delete(code);
            entityManager.getTransaction().commit();
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public ItemDTO findItem(String itemCode) throws Exception {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        try{
            itemDAO.setEntityManager(entityManager);
            entityManager.getTransaction().begin();
            ItemDTO itemDTO = itemDAO.find(itemCode).map(Converter::<ItemDTO>getDTO).orElse(null);
            entityManager.getTransaction().commit();
            return itemDTO;
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }
    }

//    private ItemDAO itemDAO;
//
//    public ManageItemsBOImpl() {
//        itemDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
//    }
//
//    public List<ItemDTO> getItems() throws Exception {
//        return itemDAO.findAll().map(Converter::<ItemDTO>getDTOList).get();
//    }
//
//    public boolean createItem(ItemDTO dto) throws Exception {
//        return itemDAO.save(Converter.getEntity(dto));
//    }
//
//    public boolean updateItem(ItemDTO dto) throws Exception {
//        return itemDAO.update(Converter.getEntity(dto));
//    }
//
//    public boolean deleteItem(String code) throws Exception {
//        return itemDAO.delete(code);
//
//    }
//
//    public ItemDTO findItem(String itemCode) throws Exception {
//        return itemDAO.find(itemCode).map(Converter::<ItemDTO>getDTO).orElse(null);
//    }
}
