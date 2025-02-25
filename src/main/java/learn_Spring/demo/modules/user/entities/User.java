package learn_Spring.demo.modules.user.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String gender;
}
