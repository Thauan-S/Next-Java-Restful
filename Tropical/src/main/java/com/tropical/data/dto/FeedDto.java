package com.tropical.data.dto;

import java.util.List;
;
public record FeedDto(List<FeedItemDto>feedItens, int page,int size,int totalPage,long totalElements) {

}
