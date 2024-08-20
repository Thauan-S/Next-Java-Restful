package com.tropical.data.dto;

import java.util.List;

public record ClientListDto(List<ClientItemDto>customersList,int page,int size, int totalPage,long totalElements) {

}
