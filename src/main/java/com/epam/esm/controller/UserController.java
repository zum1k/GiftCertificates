package com.epam.esm.controller;

import com.epam.esm.controller.resource.order.OrderLinkModifier;
import com.epam.esm.controller.resource.user.UserLinkModifier;
import com.epam.esm.entity.dto.OrderDto;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.entity.dto.UserDto;
import com.epam.esm.service.order.OrderService;
import com.epam.esm.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
  private final UserLinkModifier linkModifier;
  private final OrderLinkModifier orderLinkModifier;
  private final UserService userService;
  private final OrderService orderService;

  @RequestMapping(
      value = "/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasAuthority('admin') or hasAuthority('user')")
  public ResponseEntity<UserDto> findUser(@PathVariable("id")
                                          @Min(value = 1, message = "id must be positive") final long id) {
    log.info("find user {}", id);
    UserDto userDto = userService.findUser(id);
    linkModifier.withTagLocation(userDto);
    return ResponseEntity.ok().body(userDto);
  }

  @RequestMapping(
      value = "/{id}/orders",
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasAuthority('user')")
  public ResponseEntity<OrderDto> addOrder(@PathVariable("id")
                                           @Min(value = 1, message = "id must be positive") final long id, @Valid @RequestBody OrderDto dto) {
    log.info("add order {}", id);
    OrderDto orderDto = orderService.createOrder(id, dto);
    long dtoId = orderDto.getOrderId();
    URI resourceUri =
        ServletUriComponentsBuilder.fromCurrentContextPath().path("/" + dtoId).build().toUri();
    return ResponseEntity.created(resourceUri).build();
  }

  @RequestMapping(
      value = "/{user_id}/orders/{order_id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
 @PreAuthorize("hasAuthority('user')")
  public ResponseEntity<OrderDto> orderById(
      @PathVariable("user_id") final long userId, @PathVariable("order_id") final long orderId) {
    log.info("find user {} order by {}", userId, orderId);
    OrderDto orderDto = orderService.findOrderById(userId, orderId);
    orderLinkModifier.withTagLocation(orderDto);
    return ResponseEntity.ok().body(orderDto);
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasAuthority('admin')")
  public ResponseEntity<CollectionModel<UserDto>> findAll(@RequestParam(required = false, defaultValue = "1")
                                                          @Min(value = 1, message = "page must be positive") Integer page,
                                                          @Min(value = 1, message = "pageSize should be positive")
                                                          @Max(value = 100, message = "pageSize must not be greater than 100")
                                                          @RequestParam(required = false, defaultValue = "50") Integer pageSize) {
    log.info("find all users");
    RequestParametersDto dto = new RequestParametersDto();
    dto.setPage(page);
    dto.setPageLimit(pageSize);
    List<UserDto> userDtos = userService.findAll(dto);
    return ResponseEntity.ok().body(linkModifier.allWithPagination(userDtos, dto));
  }

  @RequestMapping(
      value = "/{id}/orders",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasAuthority('user')")
  public ResponseEntity<CollectionModel<OrderDto>> ordersByUserId(@PathVariable("id")
                                                                  @Min(value = 1, message = "id must be positive") final long id,
                                                                  @RequestParam(required = false, defaultValue = "1")
                                                                  @Min(value = 1, message = "page must be positive") Integer page,
                                                                  @Min(value = 1, message = "pageSize should be positive")
                                                                  @Max(value = 100, message = "pageSize must not be greater than 100")
                                                                  @RequestParam(required = false, defaultValue = "50") Integer pageSize) {
    log.info("find all orders by user id {}", id);
    RequestParametersDto dto = new RequestParametersDto();
    dto.setPage(page);
    dto.setPageLimit(pageSize);
    List<OrderDto> orderDtos = orderService.findUserOrders(id, dto);
    return ResponseEntity.ok().body(orderLinkModifier.allWithPagination(orderDtos, dto));
  }

  @RequestMapping(
      value = "/{id}/tag",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public TagDto mostPopularTagFromRichestUser(@PathVariable("id")
                                              @Min(value = 1, message = "id must be positive") long id) {
    log.info("find most widely used tag");
    return userService.findWidelyUsedTagByAllOrdersCost(id);
  }
}
