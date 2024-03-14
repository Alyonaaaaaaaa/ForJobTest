package boyarina.trainy.mvc.first.model;

import boyarina.trainy.mvc.first.entity.Order;
import boyarina.trainy.mvc.first.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ReportModel {

    @JsonView(Views.USERDetails.class)
    private String name;

    @JsonView(Views.USERDetails.class)
    private String email;

    @JsonView(Views.USERDetails.class)
    private List<Order> orders;
}