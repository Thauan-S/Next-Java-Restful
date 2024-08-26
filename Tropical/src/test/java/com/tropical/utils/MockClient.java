package com.tropical.utils;

import com.tropical.model.Client;
import com.tropical.model.Reserve;
import com.tropical.model.User;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class MockClient {

    public static  final Reserve RESERVE=new Reserve();
    public static final  List reserveList=List.of(RESERVE);

    public  static final Client CLIENT=new Client(1L,"thauan","123",new Date(2024-8-11),"3333333",new User(),reserveList);


}
