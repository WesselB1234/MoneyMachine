package MoneyMachine.mappers;

import org.springframework.stereotype.Component;

import MoneyMachine.dto.UserDTO;
import MoneyMachine.models.User;

@Component
public class UserMapper {
    public UserDTO toDTO(User user)
    {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setBsn(user.getBsn());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setRole(user.getRole());
        dto.setActive(user.getIsActive());
        dto.setApproved(user.getIsApproved());
        return dto;
    }

    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setBsn(dto.getBsn());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setRole(dto.getRole());
        user.setIsActive(dto.getIsActive());
        user.setIsApproved(dto.getIsApproved);
        return user;
    }
}
