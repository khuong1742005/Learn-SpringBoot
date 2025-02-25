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
    private int id;
    @NotEmpty(message = "First Name không được để trống")
    private String firstName;

    @NotEmpty(message = "Last Name không được để trống")
    private String lastName;

    @Min(value = 1, message = "Tuổi phải lớn hơn 0")
    private int age;

    @NotEmpty(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotEmpty(message = "Gender không được để trống")
    private String gender;

}
