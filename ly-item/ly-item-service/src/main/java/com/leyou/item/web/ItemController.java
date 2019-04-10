package com.leyou.item.web;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.pojo.Item;
import com.leyou.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//@RestController是@ResponseBody和@Controller的组合注解
@RequestMapping("item")//@RequestMapping此注解即可以作用在控制器的某个方法上，
// 也可以作用在此控制器类上。当控制器在类级别上添加@RequestMapping注解时，
// 这个注解会应用到控制器的所有处理器方法上。处理器方法上的@RequestMapping注解会对类级别上的
// @RequestMapping的声明进行补充。
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> saveItem(Item item){
        if (item.getPrice() == null){
            throw new LyException(ExceptionEnum.PRICE_CANNOT_BE_NULL);
        }
        item = itemService.saveItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);

    }
}
