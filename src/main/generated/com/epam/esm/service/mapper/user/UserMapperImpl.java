package com.epam.esm.service.mapper.user;

import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import com.epam.esm.entity.dto.OrderDto;
import com.epam.esm.entity.dto.UserDto;
import com.epam.esm.service.mapper.order.OrderMapper;
import java.util.ArrayList;
import java.util.HashSet;
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
public class UserMapperImpl implements UserMapper {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public User toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( dto.getUserId() );
        user.setEmail( dto.getEmail() );
        user.setPassword( dto.getPassword() );
        user.setFirstName( dto.getFirstName() );
        user.setLastName( dto.getLastName() );
        user.setCreateDate( dto.getCreateDate() );
        user.setLastUpdateDate( dto.getLastUpdateDate() );

        return user;
    }

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUserId( user.getUserId() );
        userDto.setEmail( user.getEmail() );
        userDto.setPassword( user.getPassword() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );
        userDto.setCreateDate( user.getCreateDate() );
        userDto.setLastUpdateDate( user.getLastUpdateDate() );
        userDto.setOrders( orderSetToOrderDtoSet( user.getOrders() ) );

        return userDto;
    }

    @Override
    public List<UserDto> toDtoList(List<User> userList) {
        if ( userList == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( userList.size() );
        for ( User user : userList ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    protected Set<OrderDto> orderSetToOrderDtoSet(Set<Order> set) {
        if ( set == null ) {
            return null;
        }

        Set<OrderDto> set1 = new HashSet<OrderDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Order order : set ) {
            set1.add( orderMapper.toDto( order ) );
        }

        return set1;
    }
}
