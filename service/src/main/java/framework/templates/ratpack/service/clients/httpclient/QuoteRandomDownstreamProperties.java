package framework.templates.ratpack.service.clients.httpclient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuoteRandomDownstreamProperties {
    @NotNull
    private String scheme;
    @NotNull
    private String host;
    @NotNull
    @Positive
    private Integer port;
    @NotBlank
    private String path;
}
