package com.example.shoppy.serviceImplements;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppy.config.UsersClient;
import com.example.shoppy.dto.CreateUserDTO;
import com.example.shoppy.dto.OrderCreateDTO;
import com.example.shoppy.dto.OrderItemsDTO;
import com.example.shoppy.dto.Response;
import com.example.shoppy.entity.OrderItems;
import com.example.shoppy.entity.Orders;
import com.example.shoppy.exceptions.BusinessException;
import com.example.shoppy.repository.OrdersRepo;
import com.example.shoppy.service.OrdersService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class OrdersServiceImpl implements OrdersService {

	public static final Logger log = LoggerFactory.getLogger(OrdersServiceImpl.class);

	@Autowired
	private UsersClient usrClient;

	@Autowired
	private OrdersRepo orderRepo;

	@Override
	public Response createOrder(OrderCreateDTO order, HttpServletRequest req) {
		try {

			Response usrRes = usrClient.getUserById(order, req.getHeader("Authorization"));

			if (usrRes.getStatus().equals(1)) {

				Map<String, Object> usr = (Map<String, Object>) usrRes.getData();

				Orders ord = new Orders();
				ord.setUserId((Integer) usr.get("userId"));

				for (OrderItemsDTO item : order.getOrderItems()) {

					OrderItems orderItem = new OrderItems();

//					orderItem.setOrderId(ord.getOrderId());
					orderItem.setProductId(item.getProductId());
					orderItem.setQty(item.getQty());
					orderItem.setPriceAtThatTime(item.getPriceAtThatTime());

					ord.addOrderItem(orderItem);
				}

				int total = order.getOrderItems().stream().mapToInt(i -> i.getQty() * i.getPriceAtThatTime()).sum();

				ord.setTotal(total);

				Orders svd = orderRepo.save(ord);

				return new Response(1, "Order Placed Successfully", svd);
			}

			return new Response(0, "No User Found of UserId : ", order.getUserId());

		} catch (Exception e) {
			log.info("createOrder catch : ", e);
			return new Response(0, e.getMessage(), null);
		}
	}

	@Override
	public Response getOrderById(OrderCreateDTO order, HttpServletRequest req) {
		try {
			Optional<Orders> ord = orderRepo.findByOrderId(order.getOrderId());
			if(ord.isPresent())return new Response(1, "Order Fetched Successfully", ord.get());
			return new Response(0, "No Order of OrderId : "+order.getOrderId(), null);
		} catch (Exception e) {
			log.info("getOrderById catch : ", e);
			return new Response(0, e.getMessage(), null);
		}
	}

}
