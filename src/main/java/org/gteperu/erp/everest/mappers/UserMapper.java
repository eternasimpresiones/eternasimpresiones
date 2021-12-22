/**
 *
 */
package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @author Siva
 *
 */
@Mapper
public interface UserMapper {

    Integer insertUser(User user);

    User findUserById(Integer id);

    User findByUsername(String id);

    List<User> findAllUsers();

}
