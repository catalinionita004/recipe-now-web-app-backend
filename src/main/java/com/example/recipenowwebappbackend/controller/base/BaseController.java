package com.example.recipenowwebappbackend.controller.base;


import com.example.recipenowwebappbackend.service.base.BaseService;

public interface BaseController<Service extends BaseService> {
    Service getService();
}
