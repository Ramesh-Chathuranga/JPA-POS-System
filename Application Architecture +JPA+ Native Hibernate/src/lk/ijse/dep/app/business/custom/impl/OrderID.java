package lk.ijse.dep.app.business.custom.impl;

import java.sql.SQLException;

public class OrderID {
    public static   String generateOrderId( String[]arry) {
        String setOrderId = null;

        int count = arry.length;


        if (count<0) {

            return "D001";
        }

        String orderid = arry[arry.length-1];
        String firstIndex = orderid.substring(0, 1);
        String otherIndexs = orderid.substring(1);
        int setOrdeIdInt = 0;
        int y=0;
        int x=0;
        int n=0;
        int calNumber = 0;
        System.out.println(orderid.length() + "" + otherIndexs.length());
        if (Integer.parseInt(orderid.substring(1, 2)) <9) {
            y=Integer.parseInt(orderid.substring(1, 2));
            if (Integer.parseInt(orderid.substring(2, 3)) < 9) {
                //x
                x=Integer.parseInt(orderid.substring(2, 3));
                if(Integer.parseInt(orderid.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(orderid.substring(3, 4))+1;
                    setOrderId = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    x=x+1;
                    setOrderId=firstIndex+y+x+n;
                }

            } else{
                //!x
                x=Integer.parseInt(orderid.substring(2, 3));

                if(Integer.parseInt(orderid.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(orderid.substring(3, 4))+1;
                    setOrderId = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    n=0;
                    y=y+1;
                    n=Integer.parseInt(orderid.substring(3, 4));
                    x=x+1;
                    setOrderId=firstIndex+y+x+n;
                }

            }
        } else {
            y=Integer.parseInt(orderid.substring(1, 2));

            if (Integer.parseInt(orderid.substring(2, 3)) < 9) {
                //x
                x=Integer.parseInt(orderid.substring(2, 3));
                if(Integer.parseInt(orderid.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(orderid.substring(3, 4))+1;
                    setOrderId = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    x=x+1;
                    setOrderId=firstIndex+y+x+n;
                }

            } else{
                //!x
                x=Integer.parseInt(orderid.substring(2, 3));

                if(Integer.parseInt(orderid.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(orderid.substring(3, 4))+1;
                    setOrderId = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    n=0;
                    y=y+1;
                    n=Integer.parseInt(orderid.substring(3, 4));
                    x=x+1;
                    setOrderId=firstIndex+y+x+n;
                }

            }
        }
        return setOrderId;
    }

}
