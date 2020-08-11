package com.epam.ahnl.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PageDTO<T> {

  private List<T> dtos;

  private Long totalResults;

  private String url;

}
