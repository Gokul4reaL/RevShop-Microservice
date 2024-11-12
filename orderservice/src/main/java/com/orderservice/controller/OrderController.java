package com.orderservice.controller;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orderservice.dto.OrderDTO;
import com.orderservice.model.CartItem;
import com.orderservice.model.OrderDetails;
import com.orderservice.model.Orders;
import com.orderservice.model.Product;
import com.orderservice.model.Seller;
import com.orderservice.model.Session;
import com.orderservice.model.User;
import com.orderservice.repository.CartItemRepository;
import com.orderservice.repository.OrderDetailsRepository;
import com.orderservice.repository.OrdersRepository;
import com.orderservice.repository.ProductRepository;
import com.orderservice.repository.SessionRepository;
import com.orderservice.repository.UserRepository;
import com.orderservice.service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
    private ProductRepository productRepo;
	@Autowired
    private OrdersRepository ordersRepo;    
    @Autowired
    private OrderDetailsRepository orderDetailsRepo;
	@Autowired
	private OrderService OrderService;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private SessionRepository sessionRepo;
	
	
	@PostMapping("/checkout")
	public String handleCheckout(@RequestParam("totalPrice") double totalPrice,
		
			HttpSession session, Model model) {
	    
//	    String email = (String)session.getAttribute("email");
//	    if (email == null) {
//            return "redirect:/login";
//        }
		Optional<Session> ses = sessionRepo.findById(1L);
		int value = ses.get().getValue();
		String email = ses.get().getEmail();
		if(value == 0)
		{
			return "redirect:http://localhost:9090/buyer/login";
		}
	    model.addAttribute("totalPrice", totalPrice);
       // model.addAttribute("userId", userId);
        return "order-address";
	}
	
	@PostMapping("/submitAddress")
	public String submitAddress(@RequestParam("name") String name, 
			@RequestParam("phone") String phone,
			@RequestParam("address") String address,
			@RequestParam("state") String state,
			@RequestParam("zipcode") String zipcode, 
			@RequestParam("totalPrice") double totalPrice,
			HttpSession session, Model model)
	{
		//String email = (String)session.getAttribute("email");
//	    if (email == null) {
//            return "redirect:/login";
//        }	
		Optional<Session> ses = sessionRepo.findById(1L);
		int value = ses.get().getValue();
		String email = ses.get().getEmail();
		if(value == 0)
		{
			return "redirect:http://localhost:9090/buyer/login";
		}
	    Optional<User> user = userRepo.findByEmail(email);
	    OrderService.createOrders(email, totalPrice, name,
			 phone, address, state, zipcode);
		return "orderPlaced";
	}
	
	@GetMapping("/viewOrders")
    public String viewOrders(HttpSession session, Model model) 
	{
		Optional<Session> ses = sessionRepo.findById(1L);
		int value = ses.get().getValue();
		String email = ses.get().getEmail();
		if(value == 0)
		{
			return "redirect:http://localhost:9090/buyer/login";
		}
		
        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isPresent()) {
            Long userId = userOpt.get().getUserId();
            List<Orders> orders = ordersRepo.findOrdersByUser_UserId(userId);
            List<OrderDTO> buyerOrders = new ArrayList<>();
            for (Orders order : orders) {
                var orderDetails = orderDetailsRepo.findByOrder_OrderId(order.getOrderId());
                LocalDate orderDate = order.getOrderDate();
                for (var orderDetail : orderDetails) {
                    var productOpt = productRepo.findById(orderDetail.getProduct().getProductId());
                    if (productOpt.isPresent()) {
                        var product = productOpt.get();
                        OrderDTO orderDTO = new OrderDTO(
                        		product.getProductId(),
                                product.getName(),
                                product.getImage(),
                                product.getDiscountprice(),
                                orderDetail.getQuantity(),
                                orderDate
                        );
                        buyerOrders.add(orderDTO);
                    }
                }
            }
            model.addAttribute("buyerOrders", buyerOrders);
            return "BuyerOrders"; // The view to display order details
        } else {
            return "redirect:/login";
        }
    }
	@GetMapping("/viewSellerOrders")
    public String viewSellerOrders(HttpSession session, Model model) 
	{
        //Seller loggedInSeller = (Seller) session.getAttribute("loggedInSeller");
        //Long sellerId = loggedInSeller.getSellerId();
		Long sellerId = (long) 14;
        if (sellerId != null) {
        	List<Orders> orders = ordersRepo.findAll(); // Fetch all orders
        	List<OrderDTO> sellerOrders = new ArrayList<>();
        	for (Orders order : orders) {
        	    LocalDate orderDate = order.getOrderDate();
        	    List<OrderDetails> orderDetails = orderDetailsRepo.findByOrder_OrderId(order.getOrderId());
        	    for (OrderDetails orderDetail : orderDetails) {
        	        Product product = orderDetail.getProduct();
        	        if (product.getSeller().getSellerId().equals(sellerId)) {
        	            OrderDTO orderDTO = new OrderDTO(
        	                    product.getProductId(),
        	                    product.getName(),
        	                    product.getImage(),
        	                    product.getDiscountprice(),
        	                    orderDetail.getQuantity(),
        	                    orderDate 
        	            );
        	            sellerOrders.add(orderDTO);
        	        }
        	    }
        	}
            model.addAttribute("sellerOrders", sellerOrders);
            return "SellerOrders";
        } else {
            return "seller-login";
        }
		
    }

	
}