package lk.ijse.dep.app.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class JPAUtil {
    private static EntityManagerFactory emf=buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory(){

        try {
            File file = new File("Application Architecture +JPA+ Native Hibernate/settings/application.properties");
            System.out.println(file.exists()+"File Found");
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(file);
            properties.load(fileInputStream);
            fileInputStream.close();
            return Persistence.createEntityManagerFactory("unit1",properties);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static EntityManagerFactory getEmf(){
        return emf;
    }
}
