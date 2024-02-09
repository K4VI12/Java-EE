package lk.ijse.pos_backend.filter;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.pos_backend.model.Customer;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebFilter(filterName = "CORSFilter",urlPatterns = "/*")
public class CorsFIlter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("invog");
        System.out.println(req.getParameter("id"));
        res.setHeader("Access-Control-Allow-Origin","*");
        res.setHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE,OPTIONS");
        res.setHeader("Access-Control-Allow-Headers","Content-Type,Access-Control-Allow-Headers, Authorization, X-Requested-With");
        chain.doFilter(req, res);
    }
}
