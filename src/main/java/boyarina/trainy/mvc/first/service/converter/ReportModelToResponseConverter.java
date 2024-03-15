package boyarina.trainy.mvc.first.service.converter;

import boyarina.trainy.mvc.first.api.dto.ReportResponse;
import boyarina.trainy.mvc.first.model.ReportModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ReportModelToResponseConverter implements Converter<ReportModel, ReportResponse> {
    @Override
    public ReportResponse convert(ReportModel reportModel) {
        return new ReportResponse(reportModel.getName(), reportModel.getEmail(), reportModel.getOrders());
    }
}