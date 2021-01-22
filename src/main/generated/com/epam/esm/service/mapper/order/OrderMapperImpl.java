package com.epam.esm.service.mapper.order;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.entity.dto.OrderDto;
import com.epam.esm.service.mapper.certificate.CertificateMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-06T01:45:48+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 13.0.2 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private CertificateMapper certificateMapper;

    @Override
    public Order toEntity(OrderDto dto) {
        if ( dto == null ) {
            return null;
        }

        Order order = new Order();

        order.setOrderId( dto.getOrderId() );
        order.setPrice( dto.getPrice() );
        order.setPurchaseDate( dto.getPurchaseDate() );
        order.setCreateDate( dto.getCreateDate() );
        order.setLastUpdateDate( dto.getLastUpdateDate() );

        return order;
    }

    @Override
    public OrderDto toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        long userId = orderUserUserId( order );
        orderDto.setUserId( userId );
        orderDto.setOrderId( order.getOrderId() );
        orderDto.setPrice( order.getPrice() );
        orderDto.setPurchaseDate( order.getPurchaseDate() );
        orderDto.setCreateDate( order.getCreateDate() );
        orderDto.setLastUpdateDate( order.getLastUpdateDate() );
        orderDto.setGifts( giftCertificateSetToGiftCertificateDtoList( order.getGifts() ) );

        return orderDto;
    }

    @Override
    public List<OrderDto> toDtos(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderDto> list = new ArrayList<OrderDto>( orders.size() );
        for ( Order order : orders ) {
            list.add( toDto( order ) );
        }

        return list;
    }

    private long orderUserUserId(Order order) {
        if ( order == null ) {
            return 0L;
        }
        User user = order.getUser();
        if ( user == null ) {
            return 0L;
        }
        long userId = user.getUserId();
        return userId;
    }

    protected List<GiftCertificateDto> giftCertificateSetToGiftCertificateDtoList(Set<GiftCertificate> set) {
        if ( set == null ) {
            return null;
        }

        List<GiftCertificateDto> list = new ArrayList<GiftCertificateDto>( set.size() );
        for ( GiftCertificate giftCertificate : set ) {
            list.add( certificateMapper.toDto( giftCertificate ) );
        }

        return list;
    }
}
