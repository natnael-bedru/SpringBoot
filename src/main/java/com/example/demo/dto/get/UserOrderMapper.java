package com.example.demo.dto.get;

import com.example.demo.domain.Order;
import com.example.demo.service.OrderService;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class UserOrderMapper {

    @NonNull
    private final OrderService orderService;
    @NonNull
    private final UserService userService;

        public static UserOrderDTO toDto(User user) {
            String userName = user.getUserName();
            List<String> productNames = user
                    .getOrders()
                    .stream()
                    .map(Order::getProductName)//order -> order.getProductName()
                    .collect(toList());

            return new UserOrderDTO(userName, productNames);
        }
        /*
         private String productName;
         private LocalDate orderDate;
        */

        public static OrderDTO toDto(Order order) {
            String productName = order.getProductName();
            LocalDate orderDate = order.getOrderDate();
            return new OrderDTO(productName, orderDate);
        }

//        public User toUser(UserOrderCreationDTO userOrderDTO) {
//      //      List<String> invalidProductNames = userOrderDTO.getProductName().stream()
//      //              .filter(productName -> orderService.findIdByProductName(productName) == null)
//      //              .collect(Collectors.toList());
//
//            //invalidProductNames.forEach(productName -> System.out.println("Invalid product name: " + productName));
//            //  boolean checkValidProductName = invalidProductNames.isEmpty();
//           // userService.
//
//              //            boolean checkValidProductName = userOrderDTO.getProductName().stream()
//             //                    .anyMatch(productName -> orderService.findIdByProductName(productName) != null);
//            /*
//                * stream() is used to convert the list of product names into a stream, which allows us to use the anyMatch method.
//                * anyMatch method checks if any element in the stream matches the given condition.
//                In this case, it checks if there is at least one product name for which orderService.findIdByProductName(productName)
//                returns a non-null value. If such a product name is found, it will return true, indicating that at least one valid product name exists.
//                Otherwise, it will return false.
//             */
//           // boolean checkValidUserName = userOrderDTO.getUser_name().
//            //orderService.findIdByProductName()
//            //userName productName
//            return new User(userOrderDTO.getProductName(), userOrderDTO.getUser_name(), new ArrayList<>());
//        }

}
