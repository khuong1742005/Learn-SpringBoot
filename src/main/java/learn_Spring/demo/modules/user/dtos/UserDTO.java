package learn_Spring.demo.modules.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    @NotEmpty(message = "Id không được để trống")
    private int id;

    @NotEmpty(message = "First Name không được để trống")
    private String username;

    @NotEmpty(message = "Số điện thoại không được để trống")
    private String phoneNumber;

    @Min(value = 1, message = "Tuổi phải lớn hơn 0")
    private int age;

    @NotEmpty(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotEmpty(message = "Role không được để trống")
    private String role;

}
