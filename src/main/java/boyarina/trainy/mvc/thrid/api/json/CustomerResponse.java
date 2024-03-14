package boyarina.trainy.mvc.thrid.api.json;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerResponse {
    private String firstname;
    private String lastName;
    private String email;
    private String contactNumber;
}