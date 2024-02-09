package lk.ijse.pos_backend.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.pos_backend.bo.BOFactory;
import lk.ijse.pos_backend.bo.custom.CustomerBO;
import lk.ijse.pos_backend.dto.CustomerDTO;
import lk.ijse.pos_backend.entity.CustomerEntity;
import lk.ijse.pos_backend.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;



@WebServlet(name = "Customer",urlPatterns = "/customer")
public class CustomerApi extends HttpServlet {

    CustomerBO customerBO = BOFactory.getBoFactory().getBo(BOFactory.BOtype.CUSTOMER);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get Invoked!");
        try {
            ArrayList<CustomerEntity> arrayList = customerBO.GetAll();
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
        Customer customer = jsonb.fromJson(req.getReader(), Customer.class);

        try {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setId(customer.getId());
            customerDTO.setName(customer.getName());
            customerDTO.setAddress(customer.getAddress());
            customerDTO.setSalary(customer.getSalary());

            Boolean bool = customerBO.SaveCustomer(customerDTO);
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
        Customer customer = jsonb.fromJson(req.getReader(), Customer.class);

        try {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setId(customer.getId());
            customerDTO.setName(customer.getName());
            customerDTO.setAddress(customer.getAddress());
            customerDTO.setSalary(customer.getSalary());

            Boolean bool = customerBO.UpdateCustomer(customerDTO);
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
        Customer customer = jsonb.fromJson(req.getReader(), Customer.class);

        try {
            CustomerEntity customerEntity = customerBO.getCustomer(customer.getId());
            System.out.println(customerEntity.getName());
            if(customerEntity.getName()!=(null)){
                Boolean bool = customerBO.DeleteCustomer(customer.getId());
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
