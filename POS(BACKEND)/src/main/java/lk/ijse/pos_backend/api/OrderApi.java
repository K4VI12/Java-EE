package lk.ijse.pos_backend.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;
import lk.ijse.pos_backend.bo.BOFactory;
import lk.ijse.pos_backend.bo.custom.OrderBO;
import lk.ijse.pos_backend.dto.ItemDTO;
import lk.ijse.pos_backend.dto.OrderDTO;
import lk.ijse.pos_backend.dto.OrderDetailsDTO;
import lk.ijse.pos_backend.model.Item;
import lk.ijse.pos_backend.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet(name = "Order", urlPatterns = "/order")
public class OrderApi extends HttpServlet {

    OrderBO orderBO = BOFactory.getBoFactory().getBo(BOFactory.BOtype.ORDER);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Jsonb jsonb = JsonbBuilder.create();
            Order order = jsonb.fromJson(req.getReader(), Order.class);
            //System.out.println("no");

            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderId(order.getOrderId());
            orderDTO.setDate(DateConverter(order.getDate()));
            orderDTO.setCustomerId(order.getCustomerId());

            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
            orderDetailsDTO.setOrderDetailsID(order.getOrderDetailsId());
            orderDetailsDTO.setItemName(order.getItemName());
            orderDetailsDTO.setQuantity(order.getQty());
            orderDetailsDTO.setUnitPrice(order.getUnitPrice());
            orderDetailsDTO.setItemID(order.getItemId());
            orderDetailsDTO.setOrderID(order.getOrderId());

            System.out.println("Order:"+order);

            Boolean bool = orderBO.SaveOrder(orderDTO,orderDetailsDTO);
            if(bool){
                resp.setStatus(200);
            }else {
                resp.setStatus(500);
            }
        }catch (Exception e){
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Jsonb jsonb = JsonbBuilder.create();
            Order order = jsonb.fromJson(req.getReader(), Order.class);

            Boolean bool = orderBO.DeleteOrder(order.getOrderId());
            if(bool){
                resp.setStatus(200);
            }else {
                resp.setStatus(500);
            }
        }catch (Exception e){
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

    private Date DateConverter(String date){
        java.sql.Date sqlDate = null;
        String dateString = "2022-02-08";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date parsedDate = dateFormat.parse(dateString);

            sqlDate = new java.sql.Date(parsedDate.getTime());
            System.out.println("Parsed SQL Date: " + sqlDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sqlDate;
    }
}
