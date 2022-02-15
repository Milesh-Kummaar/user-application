package userapplication.dto;
;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import userapplication.io.StatusMessage;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionResponseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String status;
    private StatusMessage statusMessage;

}
