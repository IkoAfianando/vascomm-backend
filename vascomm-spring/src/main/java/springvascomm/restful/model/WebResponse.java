package springvascomm.restful.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebResponse<T> {

  private Integer code;

  private String message;

  private T data;

  private String errors;

  private PagingResponse paging;
}
