package lk.ijse.pos_backend.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.pos_backend.bo.BOFactory;
import lk.ijse.pos_backend.bo.custom.ItemBO;
import lk.ijse.pos_backend.dto.ItemDTO;
import lk.ijse.pos_backend.entity.ItemEntity;
import lk.ijse.pos_backend.model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(name = "Item",urlPatterns = "/item")
public class ItemApi extends HttpServlet {

    ItemBO itemBO = BOFactory.getBoFactory().getBo(BOFactory.BOtype.ITEM);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get Invoked!");
        try {
            ArrayList<ItemEntity> arrayList = itemBO.GetAll();
            Jsonb jsonb = JsonbBuilder.create();

            if (arrayList != null && !arrayList.isEmpty()) {
                System.out.println(arrayList);
            }
            jsonb.toJson(arrayList,resp.getWriter());;

            resp.setContentType("application/json");
            resp.setStatus(200);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        Item item = jsonb.fromJson(req.getReader(), Item.class);

        try {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setItemCode(item.getCode());
            itemDTO.setItemName(item.getName());
            itemDTO.setQuantity(item.getQty());
            itemDTO.setPrice(item.getPrice());

            Boolean bool = itemBO.SaveItem(itemDTO);
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        Item item = jsonb.fromJson(req.getReader(), Item.class);

        try {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setItemCode(item.getCode());
            itemDTO.setItemName(item.getName());
            itemDTO.setQuantity(item.getQty());
            itemDTO.setPrice(item.getPrice());

            Boolean bool = itemBO.UpdateItem(itemDTO);
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
        Jsonb jsonb = JsonbBuilder.create();
        Item item = jsonb.fromJson(req.getReader(), Item.class);

        try {
            ItemEntity itemEntity = itemBO.getItem(item.getCode());
            System.out.println(itemEntity.getName());
            if(itemEntity.getName()!=(null)){
                Boolean bool = itemBO.DeleteItem(item.getCode());
                if(bool){
                    resp.setStatus(200);
                }else {
                    resp.setStatus(500);
                }
            }else {
                resp.setStatus(400);
            }
        }catch (Exception e){
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
