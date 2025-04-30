package tech.buildrun.orderms.controller.dto;

import org.springframework.data.domain.Page;

public record PaginationResponse(
  Integer page,
  Integer pageSize,
  Integer totalElements,
  Integer totalPages)  {

    public static PaginationResponse fromPage(Page<?> page) {
      return new PaginationResponse(
        page.getNumber(),
        page.getSize(),
        (int) page.getTotalElements(),
        page.getTotalPages()
      );
    }

}
